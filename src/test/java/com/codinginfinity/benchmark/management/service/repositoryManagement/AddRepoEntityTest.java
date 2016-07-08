package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.exception.CorruptedFileException;
import com.codinginfinity.benchmark.managenent.service.exception.FileFormatNotSupportedException;
import com.codinginfinity.benchmark.managenent.service.exception.NoFileUploadedException;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.CategoryManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.exception.NonExistentCategoryException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.request.GetCategoryByIdRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.response.GetCategoryByIdResponse;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.AddRepoEntityRequest;
import com.codinginfinity.benchmark.managenent.service.userManagement.UserManagement;
import com.codinginfinity.benchmark.managenent.service.userManagement.response.GetUserWithAuthoritiesResponse;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;

/**
 * Created by reinhardt on 2016/06/27.
 */
public abstract class AddRepoEntityTest<C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>,
        S extends CategoryManagement<C>> extends AbstractRepositoryManagementTest<C,T> {

    @Inject
    private M repositoryEntityManagement;

    @Inject
    private R repoEntityRepository;

    @Inject
    private UserManagement userManagement;

    @Inject
    private S categoryManagement;

    private List<Long> expectedCategoriesIds;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        List<C> expectedCategories = getExpectedCategories();
        expectedCategoriesIds = new ArrayList<>(expectedCategories.size());
        expectedCategories.forEach(category -> expectedCategoriesIds.add(category.getId()));

        Mockito.reset(repoEntityRepository);
        Mockito.when(repoEntityRepository.save((T)any())).thenAnswer(invocation -> {
            T entity = (T)invocation.getArguments()[0];
            entity.setId(getExpectedId());
            return entity;
        });

        Mockito.reset(userManagement);
        Mockito.when(userManagement.getUserWithAuthorities(anyObject()))
                .thenReturn(new GetUserWithAuthoritiesResponse(getExpectedUser()));

        Mockito.reset(categoryManagement);
        Mockito.when(categoryManagement.getCategoryById(anyObject())).thenAnswer(invocationOnMock -> {
            List<C> categories = getExpectedCategories();
            GetCategoryByIdRequest request = (GetCategoryByIdRequest)invocationOnMock.getArguments()[0];
            for (C category : categories) {
                if (category.getId().equals(request.getId()))
                    return new GetCategoryByIdResponse<>(category);
            }
            throw new NonExistentCategoryException();
        });
    }

    @Test
    public void addRepoEntityWithNullFileTest() throws NoFileUploadedException, FileFormatNotSupportedException, CorruptedFileException, NonExistentException {
        thrown.expect(NoFileUploadedException.class);
        thrown.expectMessage("No file uploaded");
        repositoryEntityManagement.addRepoEntity(new AddRepoEntityRequest<C,T>(getExpectedName(),
                expectedCategoriesIds, getExpectedDescription(), null)).getRepoEntity();
    }

    @Test
    public void addRepoEntityWithUnsupportedFileTest() throws NoFileUploadedException, FileFormatNotSupportedException, CorruptedFileException, NonExistentException, IOException {

        testUnsupportedFile("Test.txt");
    }

    @Test
    public void addRepoEntityWithCorruptedArchiveInsideCompressedFileTest() throws NoFileUploadedException, FileFormatNotSupportedException, CorruptedFileException, NonExistentException, IOException {

        testUnsupportedFile("Test.txt.gz");
    }

    private void testUnsupportedFile(String filename) throws NoFileUploadedException, FileFormatNotSupportedException, CorruptedFileException, NonExistentException, IOException {
        InputStream is = getClass().getResourceAsStream("/" + filename);
        MockMultipartFile file = new MockMultipartFile(filename, filename, "*/*", is);

        thrown.expect(FileFormatNotSupportedException.class);
        thrown.expectMessage("The uploaded file format is not supported");
        repositoryEntityManagement.addRepoEntity(new AddRepoEntityRequest<C,T>(getExpectedName(),
                expectedCategoriesIds, getExpectedDescription(), file)).getRepoEntity();
    }

    @Test
    public void addRepoEntityWithOnlyArchiveUploadTest() throws NoFileUploadedException, FileFormatNotSupportedException, CorruptedFileException, NonExistentException, IOException {
        addRepoEntityTest("Test.tar");
    }

    @Test
    public void addRepoEntityWithArchiveInsideCompressedTest() throws NoFileUploadedException, FileFormatNotSupportedException, CorruptedFileException, NonExistentException, IOException {
        addRepoEntityTest("Test.tar.gz");
    }

    public void addRepoEntityTest(String filename) throws NoFileUploadedException, NonExistentException, CorruptedFileException, FileFormatNotSupportedException, IOException {

        InputStream is = getClass().getResourceAsStream("/" + filename);
        MockMultipartFile file = new MockMultipartFile(filename, filename, "*/*", is);


        T entity = (T)repositoryEntityManagement.addRepoEntity(new AddRepoEntityRequest<C,T>(getExpectedName(),
                expectedCategoriesIds, getExpectedDescription(), file)).getRepoEntity();
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getCategories().size(), 2);
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());
    }

    @Test
    public void addRepoEntityWithMultipartIOExceptionTest() throws IOException, NoFileUploadedException, FileFormatNotSupportedException, CorruptedFileException, NonExistentException {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Mockito.when(file.getInputStream()).thenThrow(new IOException("File store failure"));

        thrown.expect(CorruptedFileException.class);
        thrown.expectMessage("The uploaded file is corrupted");
        repositoryEntityManagement.addRepoEntity(new AddRepoEntityRequest<C,T>(getExpectedName(),
                expectedCategoriesIds, getExpectedDescription(), file));
    }
}
