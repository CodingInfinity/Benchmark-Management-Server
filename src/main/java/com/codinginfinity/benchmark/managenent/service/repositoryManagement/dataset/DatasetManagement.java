package com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.response.*;

/**
 * Created by andrew on 2016/06/24.
 */
public interface DatasetManagement {
    
    AddDatasetResponse addDataset(AddDatasetRequest request);
    DeleteDatasetResponse deleteDataset(DeleteDatasetRequest request);
    UpdateDatasetMetaDataResponse updateDatasetMetaData(UpdataDatasetMetaDataRequest request);
    GetDatasetByIdResponse getDatasetById(GetDatasetByIdRequest request);
    GetDatasetByUsernameResponse getDatasetByUsername(GetDatasetByUsernameRequest request);
    GetDatasetByCategoryResponse getDatasetByCategory(GetDatasetByCategoryRequest request);
    GetUnusedDatasetResponse getUnusedDataset(GetUnusedDatasetRequest request);
}
