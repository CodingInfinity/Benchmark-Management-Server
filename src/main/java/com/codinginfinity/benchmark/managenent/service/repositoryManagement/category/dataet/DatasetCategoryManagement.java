package com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.category.dataet.response.*;

/**
 * Created by andrew on 2016/06/25.
 */
public interface DatasetCategoryManagement {

    AddDatasetCategoryResponse addDatasetCategory(AddDatasetCategoryRequest request);
    DeleteDatasetCategoryResponse deleteDatasetCategory(DeleteDatasetCategoryRequest request);
    UpdateDatasetCategoryResponse updateDatasetCategory(UpdateDatasetCategoryRequest request);
    GetDatasetCategoryResponse getDatasetCategory(GetDatasetCategoryRequest request);
}
