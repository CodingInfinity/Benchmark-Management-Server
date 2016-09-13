package com.codinginfinity.benchmark.management.service.repositoryManagement.request;

import com.codinginfinity.benchmark.management.domain.Category;
import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.service.Request;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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

    MultipartFile file;
}
