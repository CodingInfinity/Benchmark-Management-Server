package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetNodeStatusByIdRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetNodesSummaryRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RegisterNodeHeartbeatRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RemoveNodeRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.Node;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.NodeState;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.NodeSummary;
import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;
import org.junit.Assert;
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
import java.util.List;

/**
 * Created by andrew on 2016/09/01.
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ExperimentManagentConfiguration.class)
public class RegisterNodeHeartbeatTest {

    @Inject
    private ExperimentManagement experimentManagement;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private static long now = Instant.EPOCH.getEpochSecond();

    @Test
    public void registerNodeHeartbeat() throws NonExistentException {
        thrown.expect(NonExistentException.class);
        experimentManagement.getNodeStatusById(new GetNodeStatusByIdRequest("test"));

        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(newHeartbeat()));

        List<NodeSummary> summaryList = experimentManagement.getNodesSummary(new GetNodesSummaryRequest()).getNodeSummaryList();
        Assert.assertEquals(1, summaryList.size());

        NodeSummary nodeSummary = summaryList.get(0);
        Assert.assertEquals("test", nodeSummary.getId());
        Assert.assertEquals(NodeState.ONLINE, nodeSummary.getState());

        Node node = experimentManagement.getNodeStatusById(new GetNodeStatusByIdRequest("test")).getNode();
        Assert.assertEquals("test", node.getId());
        Assert.assertEquals("Test Description", node.getDescription());
        Assert.assertEquals("Intel Test CPU", node.getCpu());
        Assert.assertEquals("8Gb", node.getMemory());
        Assert.assertEquals("Ubuntu", node.getOs());
        Assert.assertEquals("Linux", node.getKernel());
        Assert.assertEquals("John Doe", node.getName());
        Assert.assertEquals("john@example.com", node.getEmail());
        Assert.assertEquals("0123456789", node.getPhone());
        Assert.assertEquals(now, node.getCurrent());
        Assert.assertEquals(64800, node.getHeartbeat());
        Assert.assertEquals(false, node.isBusy());

        experimentManagement.removeNode(new RemoveNodeRequest("test"));

        thrown.expect(NonExistentException.class);
        experimentManagement.getNodeStatusById(new GetNodeStatusByIdRequest("test"));
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
                now,
                64800,
                false);
    }
}
