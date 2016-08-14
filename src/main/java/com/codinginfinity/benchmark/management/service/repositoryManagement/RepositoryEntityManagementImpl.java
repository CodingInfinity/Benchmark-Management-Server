package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.domain.elasticsearch.archive.Archive;
import com.codinginfinity.benchmark.management.domain.elasticsearch.archive.ArchiveFile;
import com.codinginfinity.benchmark.management.domain.elasticsearch.archive.ArchiveNode;
import com.codinginfinity.benchmark.management.domain.elasticsearch.file.Directory;
import com.codinginfinity.benchmark.management.domain.elasticsearch.file.File;
import com.codinginfinity.benchmark.management.domain.elasticsearch.file.INode;
import com.codinginfinity.benchmark.management.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.management.repository.elasticsearch.ArchiveRepository;
import com.codinginfinity.benchmark.management.repository.elasticsearch.FileRepository;
import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.management.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.management.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.management.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.service.repositoryManagement.request.*;
import com.codinginfinity.benchmark.management.service.repositoryManagement.response.*;
import com.codinginfinity.benchmark.management.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.management.service.userManagement.request.GetUserWithAuthoritiesByLoginRequest;
import com.codinginfinity.benchmark.management.service.userManagement.request.GetUserWithAuthoritiesRequest;
import com.codinginfinity.benchmark.management.web.rest.dto.RepoEntityDTO;
import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;
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
    @Secured(AuthoritiesConstants.USER)
    public AddRepoEntityResponse<T> addRepoEntity(AddRepoEntityRequest<C, T> request) throws NoFileUploadedException, NonExistentException, FileFormatNotSupportedException, CorruptedFileException {
        R repository =  getRepository();
        T repoEntity = newRepoEntity();

        try (ByteArrayOutputStream byteArrayOutputStream =
                     new ByteArrayOutputStream();
             BufferedOutputStream bufferedOutputStream =
                     new BufferedOutputStream(byteArrayOutputStream);
             GzipCompressorOutputStream gzipCompressorOutputStream =
                     new GzipCompressorOutputStream(bufferedOutputStream);
             TarArchiveOutputStream tarArchiveOutputStream =
                     new TarArchiveOutputStream(gzipCompressorOutputStream);) {

            repackageUserArchive(request.getFile(), tarArchiveOutputStream);
            tarArchiveOutputStream.close();

            String tarBallName = FilenameUtils.getBaseName(request.getFile().getOriginalFilename()) + ".tar.gz";
            Directory root = new Directory();
            root.setName(tarBallName);
            ArchiveEntry entry;

            /* First try to decompress the file */
            try (ByteArrayInputStream byteArrayInputStream =
                         new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                 BufferedInputStream bufferedInputStream =
                         new BufferedInputStream(byteArrayInputStream);
                 CompressorInputStream compressorInputStream =
                         new CompressorStreamFactory().createCompressorInputStream(bufferedInputStream)) {

                // Now try to extract the archive inside of the compressed file
                try (BufferedInputStream decompressedBufferedInputStream =
                            new BufferedInputStream(compressorInputStream);
                        ArchiveInputStream archiveInputStream =
                            new ArchiveStreamFactory().createArchiveInputStream(decompressedBufferedInputStream)) {

                    while ((entry = archiveInputStream.getNextEntry()) != null) {
                        byte[] buffer = new byte[(int)entry.getSize()];
                        archiveInputStream.read(buffer, 0, buffer.length);
                        addFileToFileSystem(root, entry.getName(), buffer);
                    }
                } catch (ArchiveException | IllegalArgumentException e) {
                    throw new FileFormatNotSupportedException("The uploaded file format is not supported");
                }
            } catch (CompressorException e) {
                throw new FileFormatNotSupportedException("The uploaded file format is not supported");
            }

            repoEntity.setName(request.getName());
            repoEntity.setDescription(request.getDescription());
            User currentUser = userManagement.getUserWithAuthorities(new GetUserWithAuthoritiesRequest()).getUser();
            repoEntity.setUser(currentUser);
            for (Long categoryId : request.getCategories()) {
                C category = getCategoryManagement().getCategoryById(new GetCategoryByIdRequest<C>(categoryId)).getCategory();
                repoEntity.addCategory(category);
            }
            repoEntity.setFilename(tarBallName);
            repository.save(repoEntity);

            StringBuilder prefix = new StringBuilder();
            prefix.append(repoEntity.getClass().getSimpleName().subSequence(0,3));
            prefix.append("-");
            prefix.append(repoEntity.getId());
            prefix.append("_");

            saveFilesInFilesystem(root, prefix.toString());

            Archive archive = convertDirectoryToArchive(root);
            archive.setId(prefix + archive.getName());
            archiveRepository.save(archive);

            File tarBall = new File();
            tarBall.setName(tarBallName);
            tarBall.setId(prefix + tarBallName);
            tarBall.setContents(byteArrayOutputStream.toByteArray());
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

    private void saveFilesInFilesystem(Directory dir, String path) {
        path += dir.getName() + "_";
        for (INode node : dir.getNodeList()) {
            if (node instanceof Directory) {
                saveFilesInFilesystem((Directory)node, path);
            } else {
                ((File)node).setId(path + node.getName());
                fileRepository.save((File)node);
            }
        }
    }

    private Archive convertDirectoryToArchive(Directory dir) {
        Archive archive = new Archive();
        archive.setName(dir.getName());
        convertDirectoryToArchive(dir, archive);
        return archive;
    }

    private void convertDirectoryToArchive(Directory dir, ArchiveNode archive) {
        for (INode node : dir.getNodeList()) {
            if (node instanceof Directory) {
                ArchiveNode archiveDirectory = new ArchiveNode(((Directory)node).getName(), new ArrayList<>());
                archive.getNodeList().add(archiveDirectory);
                convertDirectoryToArchive((Directory)node, archiveDirectory);
            } else {
                ArchiveFile file = new ArchiveFile(((File)node).getId());
                file.setName(((File)node).getName());
                archive.getNodeList().add(file);
            }
        }
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

    private void repackageUserArchive(MultipartFile request, ArchiveOutputStream archiveOutputStream) throws CorruptedFileException, NoFileUploadedException, FileFormatNotSupportedException {
        try (BufferedInputStream bufferedInputStream =
                     new BufferedInputStream(request.getInputStream())) {

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
                        if (!entry.isDirectory()) {
                            archiveOutputStream.putArchiveEntry(entry);
                            byte[] buffer = new byte[(int) entry.getSize()];
                            archiveInputStream.read(buffer, 0, buffer.length);
                            archiveOutputStream.write(buffer);
                            archiveOutputStream.closeArchiveEntry();
                        }
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
                            archiveOutputStream.putArchiveEntry(entry);
                            byte[] buffer = new byte[(int) entry.getSize()];
                            archiveInputStream.read(buffer, 0, buffer.length);
                            archiveOutputStream.write(buffer);
                            archiveOutputStream.closeArchiveEntry();
                        }
                    }
                } catch (ArchiveException e1) {
                    throw new FileFormatNotSupportedException("The uploaded file format is not supported");
                }
            }
        } catch (IOException e) {
            throw new CorruptedFileException("The uploaded file is corrupted");
        } catch (NullPointerException e) {
            throw new NoFileUploadedException("No file uploaded");
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
        Optional<Archive> archive =  archiveRepository.findOneById(entityDoesExist.get().getClass().getSimpleName().substring(0,3) + "-" + entityDoesExist.get().getId() + "_" + entityDoesExist.get().getFilename());
        if(!entityDoesExist.isPresent()){
            throw getNonExistentRepoEntityException();
        }
        RepoEntityDTO dto = new RepoEntityDTO(entityDoesExist.get(), archive.get());
        return new GetRepoEntityByIdResponse<T>(dto,entityDoesExist.get());
    }

    @Override
    @Secured(AuthoritiesConstants.USER)
    public GetRepoEntityByUsernameResponse<T> getRepoEntityByUsername(GetRepoEntityByUsernameRequest<T> request) throws NonExistentException {
        R repository = getRepository();
        User user = userManagement.getUserWithAuthoritiesByLogin(new GetUserWithAuthoritiesByLoginRequest(request.getUsername())).getUser();
        List<T> entities = repository.findByUser(user);
        return new GetRepoEntityByUsernameResponse<>(entities);
    }

    @Override
    public GetRepoEntityByCategoryResponse<T> getRepoEntityByCategory(GetRepoEntityByCategoryRequest<C, T> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntitiesResponse<T> getUnusedRepoEntities(GetUnusedRepoEntitiesRequest<T> request) {
        return null;
    }

    @Override
    public GetUnusedRepoEntityByUsernameResponse<T> getUnusedRepoEntityByUsername(GetUnusedRepoEntityByUsernameRequest<T> request) {
        return null;
    }

    @Override
    @Secured(AuthoritiesConstants.USER)
    public GetAllRepoEntitiesResponse<T> getAllRepoEntities(GetAllRepoEntitiesRequest<T> request)throws NonExistentRepoEntityException{
        R repository = getRepository();
        List<T> entities = repository.findAll();
        if (entities.isEmpty())
        {
            throw getNonExistentRepoEntityException();
        }
        return new GetAllRepoEntitiesResponse<>(entities);
    }
}
