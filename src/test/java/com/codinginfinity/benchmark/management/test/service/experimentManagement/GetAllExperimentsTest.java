package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetAllExperimentsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetExperimentByIdRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.GetAllExperimentsResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by andrew on 2016/08/30.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class GetAllExperimentsTest {

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
        experimentManagement.getAllExperiments(new GetAllExperimentsRequest());
    }

    @Test
    public void confirmGetAllExperimentsTest() throws NonExistentRepoEntityException {
        Mockito.when(experimentRepository.findAll()).thenReturn(getExperiments());
        GetAllExperimentsResponse response = experimentManagement.getAllExperiments(new GetAllExperimentsRequest());
        Assert.assertEquals(3, response.getExperiments().size());

        Assert.assertEquals(new Long(1L), response.getExperiments().get(0).getId());
        Assert.assertEquals(LanguageType.JAVA, response.getExperiments().get(0).getLanguageType());
        Assert.assertEquals(new Integer(60), response.getExperiments().get(0).getProbeInterval());
        Assert.assertEquals(new Integer(180), response.getExperiments().get(0).getTimeout());

        Assert.assertEquals(new Long(2L), response.getExperiments().get(1).getId());
        Assert.assertEquals(LanguageType.JAVA, response.getExperiments().get(1).getLanguageType());
        Assert.assertEquals(new Integer(120), response.getExperiments().get(1).getProbeInterval());
        Assert.assertEquals(new Integer(360), response.getExperiments().get(1).getTimeout());

        Assert.assertEquals(new Long(3L), response.getExperiments().get(2).getId());
        Assert.assertEquals(LanguageType.JAVA, response.getExperiments().get(2).getLanguageType());
        Assert.assertEquals(new Integer(180), response.getExperiments().get(2).getProbeInterval());
        Assert.assertEquals(new Integer(540), response.getExperiments().get(2).getTimeout());
    }

    private List<Experiment> getExperiments() {
        List<Experiment> experiments = new ArrayList<>();
        experiments.add(getExperimentOne());
        experiments.add(getExperimentTwo());
        experiments.add(getExperimentThree());
        return experiments;
    }

    private Experiment getExperimentOne() {
        Experiment experiment = new Experiment();
        experiment.setId(1L);
        experiment.setLanguageType(LanguageType.JAVA);
        experiment.setProbeInterval(60);
        experiment.setTimeout(180);
        return experiment;
    }

    private Experiment getExperimentTwo() {
        Experiment experiment = new Experiment();
        experiment.setId(2L);
        experiment.setLanguageType(LanguageType.JAVA);
        experiment.setProbeInterval(120);
        experiment.setTimeout(360);
        return experiment;
    }

    private Experiment getExperimentThree() {
        Experiment experiment = new Experiment();
        experiment.setId(3L);
        experiment.setLanguageType(LanguageType.JAVA);
        experiment.setProbeInterval(180);
        experiment.setTimeout(540);
        return experiment;
    }
}
