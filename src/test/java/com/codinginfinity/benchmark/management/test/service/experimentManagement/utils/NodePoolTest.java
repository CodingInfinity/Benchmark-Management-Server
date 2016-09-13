package com.codinginfinity.benchmark.management.test.service.experimentManagement.utils;

import com.codinginfinity.benchmark.management.service.experimentManagement.utils.Node;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.NodePool;
import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

/**
 * Created by andrew on 2016/09/01.
 */
public class NodePoolTest {

    private NodePool nodePool = new NodePool();

    private static long now = Instant.now().getEpochSecond();

    private static long renewal = Instant.now().getEpochSecond() + 1800;

    @Test
    public void registerNodeTest() {
        Assert.assertEquals(false, nodePool.nodeRegistered("test"));
        nodePool.registerNode(initialHeartbeat());

        Assert.assertEquals(true, nodePool.nodeRegistered("test"));
        Node node = nodePool.getNode("test");
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
        Assert.assertEquals(60, node.getHeartbeat());
        Assert.assertEquals(false, node.isBusy());

        nodePool.registerNode(renewalHeartbeat());
        node = nodePool.getNode("test");
        Assert.assertEquals("test", node.getId());
        Assert.assertEquals("Test Description", node.getDescription());
        Assert.assertEquals("Intel Test CPU", node.getCpu());
        Assert.assertEquals("8Gb", node.getMemory());
        Assert.assertEquals("Ubuntu", node.getOs());
        Assert.assertEquals("Linux", node.getKernel());
        Assert.assertEquals("John Doe", node.getName());
        Assert.assertEquals("john@example.com", node.getEmail());
        Assert.assertEquals("0123456789", node.getPhone());
        Assert.assertEquals(renewal, node.getCurrent());
        Assert.assertEquals(1800, node.getHeartbeat());
        Assert.assertEquals(true, node.isBusy());
    }

    @Test
    public void deregisterNodeAndNodeRegisteredTest() {
        Assert.assertEquals(false, nodePool.nodeRegistered("test"));
        nodePool.deregisterNode("test");

        nodePool.registerNode(initialHeartbeat());
        Assert.assertEquals(true, nodePool.nodeRegistered("test"));

        nodePool.deregisterNode("test");
        Assert.assertEquals(false, nodePool.nodeRegistered("test"));
    }

    @Test
    public void getNodeTest() {
        Assert.assertEquals(null, nodePool.getNode("test"));

        nodePool.registerNode(initialHeartbeat());
        Assert.assertEquals(true, nodePool.nodeRegistered("test"));
        Node node = nodePool.getNode("test");
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
        Assert.assertEquals(60, node.getHeartbeat());
        Assert.assertEquals(false, node.isBusy());
    }

    @Test
    public void getNodesTest() {
        Assert.assertEquals(true, nodePool.getNodes().isEmpty());

        nodePool.registerNode(initialHeartbeat());
        Assert.assertEquals(true, nodePool.nodeRegistered("test"));
        Assert.assertEquals(1, nodePool.getNodes().size());

        Node node = nodePool.getNode("test");
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
        Assert.assertEquals(60, node.getHeartbeat());
        Assert.assertEquals(false, node.isBusy());
    }


    private Heartbeat initialHeartbeat() {
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
                60,
                false);
    }

    private Heartbeat renewalHeartbeat() {
        return new Heartbeat("test",
                "Test Description",
                "Intel Test CPU",
                "8Gb",
                "Ubuntu",
                "Linux",
                "John Doe",
                "john@example.com",
                "0123456789",
                renewal,
                1800,
                true);
    }
}
