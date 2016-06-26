package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.request;

import com.codinginfinity.benchmark.managenent.service.Request;
import lombok.*;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetDatasetCategoryRequest extends Request {

    private static final long serialVersionUID = 3379139585395320947L;

    private Long id;
}
