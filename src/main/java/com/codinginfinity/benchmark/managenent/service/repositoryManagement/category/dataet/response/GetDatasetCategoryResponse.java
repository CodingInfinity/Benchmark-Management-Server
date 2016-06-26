package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.response;

import com.codinginfinity.benchmark.managenent.domain.DatasetCategory;
import com.codinginfinity.benchmark.managenent.service.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by andrew on 2016/06/25.
 */
@Getter
@NoArgsConstructor
public class GetDatasetCategoryResponse extends Response {

    private static final long serialVersionUID = 2822614308213800378L;

    private DatasetCategory category;
}
