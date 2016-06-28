package com.codinginfinity.benchmark.managenent.service.repositoryManagement.request;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.domain.User;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@AllArgsConstructor
public class UpdataRepoEntityMetadataRequest <C extends Category, T extends RepoEntity<C>> extends Request {
    String name;

    User user;

    List<C> categories;

    String description;
}
