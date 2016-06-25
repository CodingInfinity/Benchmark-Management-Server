package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.algorithm.response.*;

/**
 * Created by andrew on 2016/06/25.
 */
public interface AlgorithmCategoryManagement {

    AddAlgorithmCategoryResponse addAlgorithmCategory(AddAlgorithmCategoryRequest request);
    DeleteAlgorithmCategoryResponse deleteAlgorithmCategory(DeleteAlgorithmCategoryRequest request);
    UpdateAlgorithmCategoryResponse updateAlgorithmCategory(UpdateAlgorithmCategoryRequest request);
    GetAlgorithmCategoryResponse getAlgorithmCategory(GetAlgorithmCategoryRequest request);
}
