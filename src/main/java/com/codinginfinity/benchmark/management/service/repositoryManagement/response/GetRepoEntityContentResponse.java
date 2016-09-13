package com.codinginfinity.benchmark.management.service.repositoryManagement.response;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by fabio on 2016/09/02.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetRepoEntityContentResponse extends Response{
    String content;
}
