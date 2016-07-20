package com.codinginfinity.benchmark.managenent.service.repositoryManagement.response;

import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.service.Response;
import com.codinginfinity.benchmark.managenent.web.rest.dto.RepoEntityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@AllArgsConstructor
public class GetRepoEntityByIdResponse <T extends RepoEntity> extends Response {
    RepoEntityDTO repoEntity;
}
