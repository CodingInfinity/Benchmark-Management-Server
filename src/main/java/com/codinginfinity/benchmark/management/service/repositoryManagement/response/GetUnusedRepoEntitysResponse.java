package com.codinginfinity.benchmark.management.service.repositoryManagement.response;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@AllArgsConstructor
public class GetUnusedRepoEntitysResponse <T extends RepoEntity> extends Response {
}
