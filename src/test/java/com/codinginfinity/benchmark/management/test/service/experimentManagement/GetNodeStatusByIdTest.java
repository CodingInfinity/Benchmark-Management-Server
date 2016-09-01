package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetNodeStatusByIdRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RegisterNodeHeartbeatRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RemoveNodeRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.GetNodeStatusByIdResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.Node;
import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;
import org.junit.Assert;
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
import java.time.Instant;

/**
 * Created by andrew on 2016/09/01.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class GetNodeStatusByIdTest {

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
    public void getNodeStatusByIdNonExistentExceptionTest() throws NonExistentException {
        thrown.expect(NonExistentException.class);
        experimentManagement.removeNode(new RemoveNodeRequest("test"));
    }

    @Test
    public void getNodeStatusByIdTest() throws NonExistentException {
        long current = Instant.now().getEpochSecond();
        Heartbeat heartbeat = newHeartbeat(current, 60, false);
        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(heartbeat));
        GetNodeStatusByIdResponse response = experimentManagement.getNodeStatusById(new GetNodeStatusByIdRequest("test"));
        Node node = response.getNode();

        Assert.assertEquals("test", node.getId());
        Assert.assertEquals("Test Description", node.getDescription());
        Assert.assertEquals("Intel Test CPU", node.getCpu());
        Assert.assertEquals("8Gb", node.getMemory());
        Assert.assertEquals("Ubuntu", node.getOs());
        Assert.assertEquals("Linux", node.getKernel());
        Assert.assertEquals("John Doe", node.getName());
        Assert.assertEquals("john@example.com", node.getEmail());
        Assert.assertEquals("0123456789", node.getPhone());
        Assert.assertEquals(current, node.getCurrent());
        Assert.assertEquals(60, node.getHeartbeat());
        Assert.assertEquals(false, node.isBusy());
    }

    private Heartbeat newHeartbeat(long current, int heartbeat, boolean busy) {
        return new Heartbeat("test",
                "Test Description",
                "Intel Test CPU",
                "8Gb",
                "Ubuntu",
                "Linux",
                "John Doe",
                "john@example.com",
                "0123456789",
                current,
                heartbeat,
                busy);
    }
}
