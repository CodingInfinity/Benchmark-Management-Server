package com.codinginfinity.benchmark.management.service.experimentManagement.utils;

import com.codinginfinity.benchmark.management.thrift.messages.Heartbeat;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by andrew on 2016/09/01.
 */
@Data
@AllArgsConstructor
public class Node {
    private final String id;
    private final String description;
    private final String cpu;
    private final String memory;
    private final String os;
    private final String kernel;
    private final String name;
    private final String email;
    private final String phone;
    private long current;
    private int heartbeat;
    private boolean busy;

    public Node(Heartbeat heartbeat) {
        this.id = heartbeat.getId();
        this.description = heartbeat.getDescription();
        this.cpu = heartbeat.getCpu();
        this.memory = heartbeat.getMemory();
        this.os = heartbeat.getOs();
        this.kernel = heartbeat.getKernel();
        this.name = heartbeat.getName();
        this.email = heartbeat.getEmail();
        this.phone = heartbeat.getPhone();
        this.current = heartbeat.getCurrent();
        this.heartbeat = heartbeat.getHeartbeat();
        this.busy = heartbeat.isBusy();
    }

    public void updateNode(Heartbeat heartbeat) {
        this.current =  heartbeat.getCurrent();
        this.heartbeat = heartbeat.getHeartbeat();
        this.busy = heartbeat.busy;
    }
}
