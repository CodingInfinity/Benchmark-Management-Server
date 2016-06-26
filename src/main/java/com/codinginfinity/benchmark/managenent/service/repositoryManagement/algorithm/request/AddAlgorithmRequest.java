package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.request;

import com.codinginfinity.benchmark.managenent.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@AllArgsConstructor
public class AddAlgorithmRequest extends Request {

    private static final long serialVersionUID = 7769164294013216788L;

    String name;

    User user;

    List<AlgorithmCategory> categories;
}
