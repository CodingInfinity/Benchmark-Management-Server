package com.codinginfinity.benchmark.management.service.repositoryManagement.request;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;

/**
 * Created by brenton on 8/13/16.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetAllEntitiesRequest<T extends RepoEntity> extends Request {

    private static final long serialVersionUID = 4544026646814612593L;
}
