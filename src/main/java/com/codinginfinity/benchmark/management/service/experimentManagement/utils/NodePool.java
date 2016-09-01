package com.codinginfinity.benchmark.management.service.experimentManagement.utils;

import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrew on 2016/09/01.
 */
public class NodePool {

    private HashMap<String, Node> pool = new HashMap<>();

    public void registerNode(Heartbeat heartbeat) {
        if (pool.containsKey(heartbeat.getId())) {
            pool.get(heartbeat.getId()).updateNode(heartbeat);
        } else {
            pool.put(heartbeat.getId(), new Node(heartbeat));
        }
    }

    public void deregisterNode(String id) {
        if (pool.containsKey(id)) {
            pool.remove(id);
        }
    }

    public boolean nodeRegistered(String id) {
        return pool.containsKey(id);
    }

    public Node getNode(String id) {
        return pool.get(id);
    }

    public List<Node> getNodes() {
        return new ArrayList<>(this.pool.values());
    }
}