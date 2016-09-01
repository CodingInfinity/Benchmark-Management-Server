package com.codinginfinity.benchmark.management.web.rest.experiementManagment;

import com.codinginfinity.benchmark.management.security.AuthoritiesConstants;
import com.codinginfinity.benchmark.management.service.exception.NonExistentException;
import com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetNodeStatusByIdRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.GetNodesSummaryRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.request.RemoveNodeRequest;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.GetNodeStatusByIdResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.response.GetNodesSummaryResponse;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.Node;
import com.codinginfinity.benchmark.management.service.experimentManagement.utils.NodeSummary;
import com.codinginfinity.benchmark.management.service.reporting.exception.ProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Defines RESTful API endpoints for the monitoring of profiler nodes registered with the management system.
 *
 * @see com.codinginfinity.benchmark.management.service.experimentManagement.ExperimentManagement
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api")
public class NodeResource {

    @Inject
    private ExperimentManagement experimentManagement;

    /**
     * GET: /api/nodes
     *
     * Returns a summary of all nodes currently registered with the management
     * system's monitoring system,
     * @return ResponseEntity with status 200 (OK),with a list of {@link NodeSummary}.
     */
    @Secured(AuthoritiesConstants.ADMIN)
    @RequestMapping(value = "/nodes",
            method = RequestMethod.GET)
    public ResponseEntity<List<NodeSummary>> getNodesSummary() {
        GetNodesSummaryResponse response = experimentManagement.getNodesSummary(new GetNodesSummaryRequest());
        return ResponseEntity.ok(response.getNodeSummaryList());
    }

    /**
     * GET: /api/node/{id}
     * @param id The id of the requested node for which we want get full node
     *           information.
     * @return ResponseEntity with status 200 (OK),with a {@link Node} entity
     *          in the body.
     */
    @Secured(AuthoritiesConstants.ADMIN)
    @RequestMapping(value = "/node/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Node> getNodeById(@PathVariable String id) throws NonExistentException {
        GetNodeStatusByIdResponse response = experimentManagement.getNodeStatusById(new GetNodeStatusByIdRequest(id));
        return ResponseEntity.ok(response.getNode());
    }

    /**
     * DELETE: /api/node/{id}
     * @param id The id of the node we want to remove from the monitoring system.
     * @return ResponseEntity with status 200 (OK),
     * @throws NonExistentException Thrown if the node with the requested iD is
     *          not registered with the monitoring system.
     */
    @Secured(AuthoritiesConstants.ADMIN)
    @RequestMapping(value = "/node/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<?> removeNodeFromPool(@PathVariable String id) throws NonExistentException {
        experimentManagement.removeNode(new RemoveNodeRequest(id));
        return ResponseEntity.ok(null);
    }
}
