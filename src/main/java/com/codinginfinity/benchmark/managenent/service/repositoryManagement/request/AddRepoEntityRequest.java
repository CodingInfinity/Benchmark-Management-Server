package com.codinginfinity.benchmark.managenent.service.repositoryManagement.request;

import com.codinginfinity.benchmark.managenent.domain.Category;
import com.codinginfinity.benchmark.managenent.domain.RepoEntity;
import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.*;

import java.util.List;

/**
 * Created by reinhardt on 2016/06/27.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddRepoEntityRequest<C extends Category, T extends RepoEntity<C>> extends Request{

    private static final long serialVersionUID = 7974802860501890267L;

    String name;

    List<Long> categories;

    String description;
}
