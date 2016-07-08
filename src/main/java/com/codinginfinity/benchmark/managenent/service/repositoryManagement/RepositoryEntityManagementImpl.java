package com.codinginfinity.benchmark.managenent.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.domain.binary.*;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.repository.binary.ArchiveRepository;
import com.codinginfinity.benchmark.managenent.repository.binary.FileRepository;
import com.codinginfinity.benchmark.managenent.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.managenent.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.managenent.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.response.*;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.request.GetUserWithAuthoritiesRequest;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by reinhardt on 2016/06/29.
 */
public abstract class RepositoryEntityManagementImpl<C extends Category,
        T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        S extends CategoryManagement<C>> implements RepositoryEntityManagement<C,T>{
    protected abstract R getRepository();

    protected abstract T newRepoEntity();

    protected abstract S getCategoryManagement();

    protected abstract NonExistentRepoEntityException getNonExistentRepoEntityException();

    @Inject
    private FileRepository fileRepository;

    @Inject
    private ArchiveRepository archiveRepository;

    @Inject
    private UserManagement userManagement;

    @Override
    public AddRepoEntityResponse<T> addRepoEntity(AddRepoEntityRequest<C, T> request) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException {
        R repository =  getRepository();
        T repoEntity = newRepoEntity();

        try (BufferedInputStream bufferedInputStream =
                     new BufferedInputStream(request.getFile().getInputStream())) {

            String tarBallName = request.getFile().getOriginalFilename();
            Archive archive = new Archive();
            archive.setName(tarBallName);
            ArchiveEntry entry;

            /* First try to decompress the file */
            try (CompressorInputStream compressorInputStream =
                         new CompressorStreamFactory().createCompressorInputStream(bufferedInputStream)) {

                // Now try to extract the archive inside of the compressed file
                try (BufferedInputStream decompressedBufferedInputStream =
                            new BufferedInputStream(compressorInputStream);
                        ArchiveInputStream archiveInputStream =
                            new ArchiveStreamFactory().createArchiveInputStream(decompressedBufferedInputStream)) {


                    while ((entry = archiveInputStream.getNextEntry()) != null) {
                        byte[] buffer = new byte[(int)entry.getSize()];
                        archiveInputStream.read(buffer, 0, buffer.length);
                        addFileToFileSystem(archive, entry.getName(), buffer);
                    }
                } catch (ArchiveException | IllegalArgumentException e) {
                    throw new FileFormatNotSupportedException("The uploaded file format is not supported");
                }
            } catch (CompressorException e) {

                // No compressed file is present, lets assume it is an archive and try to extract files
                try (ArchiveInputStream archiveInputStream =
                             new ArchiveStreamFactory().createArchiveInputStream(bufferedInputStream)) {

                    while ((entry = archiveInputStream.getNextEntry()) != null) {
                        if (!entry.isDirectory()) {
                            byte[] buffer = new byte[(int) entry.getSize()];
                            archiveInputStream.read(buffer, 0, buffer.length);
                            addFileToFileSystem(archive, entry.getName(), buffer);
                        }
                    }
                } catch (ArchiveException e1) {
                    throw new FileFormatNotSupportedException("The uploaded file format is not supported");
                }
            }

            repoEntity.setName(request.getName());
            repoEntity.setDescription(request.getDescription());
            User currentUser = userManagement.getUserWithAuthorities(new GetUserWithAuthoritiesRequest()).getUser();
            repoEntity.setUser(currentUser);
            for (Long categoryId : request.getCategories()) {
                C category = getCategoryManagement().getCategoryById(new GetCategoryByIdRequest<C>(categoryId)).getCategory();
                repoEntity.addCategory(category);
            }
            repository.save(repoEntity);

            StringBuilder prefix = new StringBuilder();
            prefix.append(repoEntity.getClass().getSimpleName().subSequence(0,3));
            prefix.append("-");
            prefix.append(repoEntity.getId());
            prefix.append("_");

            saveFilesInArchive(archive, prefix.toString());

            archive.setId(prefix + archive.getName());
            archiveRepository.save(archive);

            File tarBall = new File();
            tarBall.setName(tarBallName);
            tarBall.setId(prefix + tarBallName);
            tarBall.setContents(request.getFile().getBytes());
            fileRepository.save(tarBall);

            repoEntity.setDocuments(true);
            repository.save(repoEntity);

        } catch (IOException e) {
            throw new CorruptedFileException("The uploaded file is corrupted");
        } catch (NullPointerException e) {
            throw new NoFileUploadedException("No file uploaded");
        }
        return new AddRepoEntityResponse<T>(repoEntity);
    }

    private void saveFilesInArchive(Directory dir, String path) {
        path += dir.getName() + "_";
        List<INode> iNodeList = new ArrayList<>(dir.getNodeList().size());
        for (INode node : dir.getNodeList()) {
            if (node instanceof Directory) {
                saveFilesInArchive((Directory)node, path);
                iNodeList.add(node);
            } else {
                ((File)node).setId(path + node.getName());
                fileRepository.save((File)node);
                FileProperties fp = (FileProperties)node;
                iNodeList.add(new FileProperties((File)node));
            }
        }
        dir.setNodeList(iNodeList);
    }

    private void addFileToFileSystem(Directory root, String fileName, byte[] contents) {

        String[] filepath = fileName.split("/");

        Directory current = root;
        boolean done = false;
        for (int i = 0; i < filepath.length; i++) {
            if (i == filepath.length - 1) {
                File file = new File();
                file.setName(filepath[i]);
                file.setContents(contents);
                current.getNodeList().add(file);
            } else {
                for (INode dir : current.getNodeList()) {
                    if (dir instanceof Directory && dir.getName().equals(filepath[i])) {
                        current = (Directory) dir;
                        done = true;
                        break;
                    }
                }
                if (!done) {
                    Directory newDir = new Directory();
                    newDir.setName(filepath[i]);
                    current.getNodeList().add(newDir);
                    current = newDir;
                }
            }
        }
    }

    @Override
    public DeleteRepoEntityResponse<T> deleteRepoEntity(DeleteRepoEntityRequest<T> request) throws NonExistentRepoEntityException {
        R repository = getRepository();
        Optional<T> entityDoesExist = repository.findOneById(request.getId());
        if(!entityDoesExist.isPresent()){
            throw getNonExistentRepoEntityException();
        }

        repository.delete(request.getId());
        return new DeleteRepoEntityResponse<T>(entityDoesExist.get());
    }

    @Override
    public UpdateRepoEntityMetadataResponse<T> updateRepoEntityMetaData(UpdateRepoEntityMetadataRequest<C, T> request) throws NonExistentRepoEntityException {
        R repository = getRepository();
        Optional<T> entityDoesExist = repository.findOneById(request.getId());
        if(!entityDoesExist.isPresent()){
            throw getNonExistentRepoEntityException();
        }
        entityDoesExist.get().setName(request.getName());
        entityDoesExist.get().setUser(request.getUser());
        entityDoesExist.get().setDescription(request.getDescription());
        List<C> oldCategories = entityDoesExist.get().getCategories();
        oldCategories.clear();
        List<C> categories = request.getCategories();
        for (C category: categories) {
            entityDoesExist.get().addCategory(category);
        }
        return new UpdateRepoEntityMetadataResponse<T>(entityDoesExist.get());
    }

    @Override
    public GetRepoEntityByIdResponse<T> getRepoEntityById(GetRepoEntityByIdRequest<T> request) throws NonExistentRepoEntityException {
        R repository = getRepository();
        Optional<T> entityDoesExist = repository.findOneById(request.getId());
        if(!entityDoesExist.isPresent()){
            throw getNonExistentRepoEntityException();
        }
        return new GetRepoEntityByIdResponse<T>(entityDoesExist.get());
    }

    @Override
    public GetRepoEntityByUsernameResponse<T> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<T> request){
        R repository = getRepository();
        List<T> entities = repository.findByUser(request.getUsername());
        return new GetRepoEntityByUsernameResponse<>(entities);
    }

    @Override
    public GetRepoEntityByCategoryResponse<T> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<C, T> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntitysResponse<T> getUnusedRepoEntitys(GetUnusedRepoEntitysRequest<T> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntityByUsernameResponse<T> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<T> request) {
        return null;
    }
}
