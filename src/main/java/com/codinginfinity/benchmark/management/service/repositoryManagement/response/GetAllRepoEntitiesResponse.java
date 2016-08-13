package com.codinginfinity.benchmark.management.service.repositoryManagement.response;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
/**
 * Created by brenton on 8/13/16.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRepoEntitiesResponse<T extends RepoEntity>extends Response
{
    private static final long serialVersionUID = -1099271693727229319L;
    private List<T> entities;
}
