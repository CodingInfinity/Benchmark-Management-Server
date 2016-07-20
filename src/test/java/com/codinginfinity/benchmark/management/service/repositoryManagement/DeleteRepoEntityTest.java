package com.codinginfinity.benchmark.management.service.repositoryManagement;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.repository.RepoEntityRepository;
import com.codinginfinity.benchmark.managenent.service.exception.NonExistentException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.RepositoryEntityManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.DeleteRepoEntityRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.request.GetRepoEntityByIdRequest;
import com.codinginfinity.benchmark.managenent.web.rest.dto.RepoEntityDTO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Created by reinhardt on 2016/06/28.
 */
public abstract class DeleteRepoEntityTest<C extends Category, T extends RepoEntity<C>,
        R extends RepoEntityRepository<T>,
        M extends RepositoryEntityManagement<C,T>> extends AbstractRepositoryManagementTest<C,T>{

    @Inject
    private M repositoryEntityManagement;

    @Inject
    private R repoEntityRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void deleteNonExistentRepoEntityTest() throws NonExistentRepoEntityException {
        Mockito.when(repoEntityRepository.findOneById(getExpectedId())).thenReturn(Optional.empty());
        thrown.expect(NonExistentRepoEntityException.class);
        //thrown.expectMessage(getNonExistentExceptionMessage());
        repositoryEntityManagement.deleteRepoEntity(new DeleteRepoEntityRequest<T>(getExpectedId()));
    }

    @Test
    public void deleteRepoEntityTest() throws NonExistentRepoEntityException {
        Map<Long, T> database = new HashMap<>();
        database.put(getExpectedId(), getRepoEntity());


        doAnswer(args -> database.remove((Long)args.getArguments()[0])).when(repoEntityRepository).delete(getExpectedId());
        when(repoEntityRepository.findOneById(anyLong())).thenAnswer(args -> Optional.ofNullable(database.get((Long) args.getArguments()[0])));

        T entity = repositoryEntityManagement.deleteRepoEntity(new DeleteRepoEntityRequest<T>(getExpectedId())).getEntity();
        assertEquals(entity.getId(), getExpectedId());
        assertEquals(entity.getCategories().size(), 2);
        assertEquals(entity.getDescription(), getExpectedDescription());
        assertEquals(entity.getName(), getExpectedName());
        assertEquals(entity.getUser().getUsername(), getExpectedUser().getUsername());

        //now try to get the deleted entity
        thrown.expect(NonExistentException.class);
        thrown.expectMessage(getNonExistentExceptionMessage());
        RepoEntityDTO deletedEntity = repositoryEntityManagement.getRepoEntityById(new GetRepoEntityByIdRequest<T>(getExpectedId())).getRepoEntity();
        assertNull(deletedEntity);
    }
}
