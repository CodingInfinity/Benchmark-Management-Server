package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.repository.ExperimentRepository;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetAllExperimentsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetAllUserExperimentsRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.GetAllUserExperimentsResponse;
import com.codinginfinity.benchmark.management.service.repositoryManagement.exception.NonExistentRepoEntityException;
import com.codinginfinity.benchmark.management.thrift.messages.LanguageType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
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

/**
 * Created by andrew on 2016/08/31.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class GetAllUserExperimentsTest {

    @Inject
    private ExperimentRepository experimentRepository;

    @Inject
    private ExperimentManagement experimentManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getAllUserExperimentsNonExistentRepoEntityException() throws NonExistentRepoEntityException {
        Mockito.when(experimentRepository.findAllByUser(Mockito.anyObject())).thenReturn(new ArrayList<>());
        thrown.expect(NonExistentRepoEntityException.class);
        experimentManagement.getAllUserExperiments(new GetAllUserExperimentsRequest(user()));
    }

    @Test
    public void getAllUserExperiments() throws NonExistentRepoEntityException {
        Mockito.when(experimentRepository.findAllByUser(Mockito.anyObject())).thenReturn(getExperiments());

        GetAllUserExperimentsResponse response = experimentManagement.getAllUserExperiments(new GetAllUserExperimentsRequest(user()));
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

    private User user() {
        User user = new User();
        user.setId(1L);
        user.setUsername("johndoe");
        return user;
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
