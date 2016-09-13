package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RegisterNodeHeartbeatRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RemoveNodeRequest;
import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by andrew on 2016/09/01.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class RemoveNodeTest {

    @Inject
    private ExperimentManagement experimentManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        try {
            experimentManagement.removeNode(new RemoveNodeRequest("test"));
        } catch (NonExistentException ignored) { }
    }

    @Test
    public void removeNodeNonExistentExceptionTest() throws NonExistentException {
        thrown.expect(NonExistentException.class);
        experimentManagement.removeNode(new RemoveNodeRequest("test"));
    }

    @Test
    public void removeNodeTest() throws NonExistentException {
        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(newHeartbeat()));
        experimentManagement.removeNode(new RemoveNodeRequest("test"));

        thrown.expect(NonExistentException.class);
        experimentManagement.removeNode(new RemoveNodeRequest("test"));
    }

    private Heartbeat newHeartbeat() {
        return new Heartbeat("test",
                "Test Description",
                "Intel Test CPU",
                "8Gb",
                "Ubuntu",
                "Linux",
                "John Doe",
                "john@example.com",
                "0123456789",
                0,
                60,
                true);
    }
}
