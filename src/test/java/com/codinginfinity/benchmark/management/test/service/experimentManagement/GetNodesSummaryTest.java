package com.codinginfinity.benchmark.management.test.service.experimentManagement;

import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetNodesSummaryRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RegisterNodeHeartbeatRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RemoveNodeRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.GetNodesSummaryResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.NodeState;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.NodeSummary;
import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
public class GetNodesSummaryTest {

    @Inject
    private ExperimentManagement experimentManagement;

    @Before
    public void setUp() throws Exception {
        try {
            experimentManagement.removeNode(new RemoveNodeRequest("test"));
        } catch (NonExistentException ignored) { }
    }

    @Test
    public void nodeIsOnlineTest() {
        Heartbeat heartbeat = newHeartbeat(Instant.now().getEpochSecond(), 1800, false);
        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(heartbeat));
        GetNodesSummaryResponse response = experimentManagement.getNodesSummary(new GetNodesSummaryRequest());
        List<NodeSummary> summaries = response.getNodeSummaryList();

        Assert.assertEquals(1, summaries.size());
        Assert.assertEquals("test", summaries.get(0).getId());
        Assert.assertEquals(NodeState.ONLINE, summaries.get(0).getState());
    }

    @Test
    public void nodeIsBusyTest() {
        Heartbeat heartbeat = newHeartbeat(Instant.now().getEpochSecond(), 1800, true);
        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(heartbeat));
        GetNodesSummaryResponse response = experimentManagement.getNodesSummary(new GetNodesSummaryRequest());
        List<NodeSummary> summaries = response.getNodeSummaryList();

        Assert.assertEquals(1, summaries.size());
        Assert.assertEquals("test", summaries.get(0).getId());
        Assert.assertEquals(NodeState.BUSY, summaries.get(0).getState());
    }

    @Test
    public void nodeIsDeadTest() {
        Heartbeat heartbeat = newHeartbeat(Instant.now().getEpochSecond(), 0, false);
        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(heartbeat));
        GetNodesSummaryResponse response = experimentManagement.getNodesSummary(new GetNodesSummaryRequest());
        List<NodeSummary> summaries = response.getNodeSummaryList();

        Assert.assertEquals(1, summaries.size());
        Assert.assertEquals("test", summaries.get(0).getId());
        Assert.assertEquals(NodeState.DEAD, summaries.get(0).getState());
    }

    @Test
    public void nodeIsOfflineByCurrentTest() {
        Heartbeat heartbeat = newHeartbeat(-1, 0, false);
        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(heartbeat));
        GetNodesSummaryResponse response = experimentManagement.getNodesSummary(new GetNodesSummaryRequest());
        List<NodeSummary> summaries = response.getNodeSummaryList();

        Assert.assertEquals(1, summaries.size());
        Assert.assertEquals("test", summaries.get(0).getId());
        Assert.assertEquals(NodeState.OFFLINE, summaries.get(0).getState());
    }

    @Test
    public void nodeIsOfflineByHeartbeatTest() {
        Heartbeat heartbeat = newHeartbeat(Instant.now().getEpochSecond(), -1, false);
        experimentManagement.registerNodeHeartbeat(new RegisterNodeHeartbeatRequest(heartbeat));
        GetNodesSummaryResponse response = experimentManagement.getNodesSummary(new GetNodesSummaryRequest());
        List<NodeSummary> summaries = response.getNodeSummaryList();

        Assert.assertEquals(1, summaries.size());
        Assert.assertEquals("test", summaries.get(0).getId());
        Assert.assertEquals(NodeState.OFFLINE, summaries.get(0).getState());
    }

    private Heartbeat newHeartbeat(long current, int heartbeat, boolean busy) {
        return new Heartbeat("test",
                "Test Description",
                "Intel Test CPU",
                "8Gb",
                "Ubuntu",
                "Linux",
                "Andrew Broekman",
                "andrewbroekman@gmail.com",
                "0123456789",
                current,
                heartbeat,
                busy);
    }
}
