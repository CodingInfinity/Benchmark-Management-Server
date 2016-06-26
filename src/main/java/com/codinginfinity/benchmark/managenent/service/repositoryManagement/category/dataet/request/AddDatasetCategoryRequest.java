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
public class AddDatasetCategoryRequest extends Request {

    private static final long serialVersionUID = -6731406553053859819L;

    private String name;
}
