package com.codinginfinity.benchmark.management.service.repositoryManagement.request;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@AllArgsConstructor
public class UpdateRepoEntityMetadataRequest<C extends Category, T extends RepoEntity<C>> extends Request {

    private static final long serialVersionUID = 6051758393474349120L;

    Long id;

    String name;

    User user;

    List<C> categories;

    String description;
}
