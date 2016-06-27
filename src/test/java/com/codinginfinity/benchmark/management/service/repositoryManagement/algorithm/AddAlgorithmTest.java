package com.codinginfinity.benchmark.management.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.management.AbstractTest;
import com.codinginfinity.benchmark.managenent.domain.Algorithm;
import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.repository.AlgorithmRepository;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.AlgorithmManagement;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.request.AddAlgorithmRequest;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.request.GetAlgorithmByIdRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrew on 2016/06/25.
 */
public class AddAlgorithmTest extends AbstractTest {

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
    public void addAlgoritmTest(){
        Mockito.when(algorithmRepository.save((Algorithm)any())).thenAnswer(invocation -> {
            Algorithm alg = (Algorithm)invocation.getArguments()[0];
            alg.setName("BubbleSort");
            alg.setCategories(createCategories());
            alg.setUser(createUser());
            alg.setDescription("Sorting using a standard Bubblesort Algorithm");
            alg.setId(new Long(12345));
            return alg;
        });

        Algorithm algorithm = algorithmManagement.addAlgorithm(new AddAlgorithmRequest("BubbleSort", createUser(),
                createCategories(), "Sorting using a standard Bubblesort Algorithm")).getAlgorithm();
        assertEquals(algorithm.getId(), new Long(12345));
        assertEquals(algorithm.getCategories().size(), 2);
        assertEquals(algorithm.getDescription(), "Sorting using a standard Bubblesort Algorithm");
        assertEquals(algorithm.getName(), "BubbleSort");
        assertEquals(algorithm.getUser().getUsername(), createUser().getUsername());

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
