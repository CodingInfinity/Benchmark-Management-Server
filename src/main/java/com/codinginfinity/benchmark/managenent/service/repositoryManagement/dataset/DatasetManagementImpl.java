package com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.dataset.response.*;
import org.springframework.stereotype.Service;

/**
 * Created by andrew on 2016/06/24.
 */
@Service
public class DatasetManagementImpl implements DatasetManagement {

    @Override
    public AddDatasetResponse addDataset(AddDatasetRequest request) {
        return null;
    }

    @Override
    public DeleteDatasetResponse deleteDataset(DeleteDatasetRequest request) {
        return null;
    }

    @Override
    public UpdateDatasetMetaDataResponse updateDatasetMetaData(UpdataDatasetMetaDataRequest request) {
        return null;
    }

    @Override
    public GetDatasetByIdResponse getDatasetById(GetDatasetByIdRequest request) {
        return null;
    }

    @Override
    public GetDatasetByUsernameResponse getDatasetByUsername(GetDatasetByUsernameRequest request) {
        return null;
    }

    @Override
    public GetDatasetByCategoryResponse getDatasetByCategory(GetDatasetByCategoryRequest request) {
        return null;
    }

    @Override
    public GetUnusedDatasetResponse getUnusedDataset(GetUnusedDatasetRequest request) {
        return null;
    }
}
