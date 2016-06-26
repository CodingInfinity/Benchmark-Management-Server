package com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.exception.DuplicateAlgorithmException;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.request.AddAlgorithmRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrew on 2016/06/25.
 */
public class AddAlgorithm extends AbstractTest {

    private AlgorithmRepository algorithmRepository;

    @InjectMocks
    @Inject
    private AlgorithmManagement algorithmManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        algorithmRepository = Mockito.mock(AlgorithmRepository.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addDuplicateUserTest(){
        Algorithm algorithm = new Algorithm();
        algorithm.setName("BubbleSort");
        algorithm.setUser(createUser());
        algorithm.setCategories(createCategories());
        Mockito.when(algorithmRepository.findOneByName("BubbleSort")).thenReturn(Optional.of(algorithm));
        thrown.expect(DuplicateAlgorithmException.class);
        algorithmManagement.addAlgorithm(new AddAlgorithmRequest("Bubblesort", createUser(), createCategories()));
    }

    private User createUser() {
        User result = new User();
        result.setUsername("johndoe");
        result.setPassword("p@$$w0rd");
        result.setFirstName("John");
        result.setLastName("Doe");
        result.setEmail("johndoe@example.com");
        result.setActivated(true);
        result.setActivationKey(null);
        result.setResetDate(null);
        result.setResetKey(null);
        return result;
    }

    private List<AlgorithmCategory> createCategories() {
        List<AlgorithmCategory> categories = new ArrayList<AlgorithmCategory>();
        AlgorithmCategory sorting = new AlgorithmCategory(new Long(1), "Sorting");
        AlgorithmCategory ai = new AlgorithmCategory(new Long(2), "Artificial Intelligence");
        categories.add(sorting);
        categories.add(ai);
        return categories;
    }
}
