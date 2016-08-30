package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetExperimentByIdRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.respones.GetExperimentByIdResponse;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.thrift.messages.LanguageType;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by andrew on 2016/08/30.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class GetExperimentByIdTest {

    @Inject
    private ExperimentRepository experimentRepository;

    @Inject
    private ExperimentManagement experimentManagement;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void nonExistantExperimentTest() throws NonExistentRepoEntityException {
        Mockito.when(experimentRepository.findOneById(Mockito.anyLong())).thenReturn(Optional.empty());
        thrown.expect(NonExistentRepoEntityException.class);
        experimentManagement.getExperimentById(new GetExperimentByIdRequest(1L));
    }

    @Test
    public void confirmExperimentTest() throws NonExistentRepoEntityException {
        Mockito.when(experimentRepository.findOneById(Mockito.anyLong())).thenReturn(Optional.of(mockExperiment()));
        GetExperimentByIdResponse response = experimentManagement.getExperimentById(new GetExperimentByIdRequest(1L));
        Assert.assertEquals(new Long(123L), response.getExperiment().getId());
        Assert.assertEquals(LanguageType.JAVA, response.getExperiment().getLanguageType());
        Assert.assertEquals(new Integer(60), response.getExperiment().getProbeInterval());
        Assert.assertEquals(new Integer(180), response.getExperiment().getTimeout());
        Assert.assertEquals(new Long(123L), response.getExperiment().getId());
    }

    private Experiment mockExperiment() {
        Experiment experiment = new Experiment();
        experiment.setId(123L);
         experiment.setLanguageType(LanguageType.JAVA);
        experiment.setProbeInterval(60);
        experiment.setTimeout(180);
        return experiment;
    }
}