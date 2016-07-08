package com.codinginfinity.benchmark.managenent.service.repositoryManagement.response;

import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@AllArgsConstructor
public class GetRepoEntityByUsernameResponse <T extends RepoEntity> extends Response {
    List<T> entities;
}
