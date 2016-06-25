package com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm;

import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.request.*;
import com.codinginfinity.benchmark.managenent.service.repositoryManagement.algorithm.response.*;

/**
 * Created by andrew on 2016/06/25.
 */
public interface AlgorithmManagement {

    AddAlgorithmResponse addAlgorithm(AddAlgorithmRequest request);
    DeleteAlgorithmResponse deleteAlgorithm(DeleteAlgorithmRequest request);
    UpdateAlgorithmMetadataResponse updateAlgorithmMetaData(UpdataAlgorithmMetadataRequest request);
    GetAlgorithmByIdResponse getAlgorithmById(GetAlgorithmByIdRequest request);
    GetAlgorithmByUsernameResponse getAlgorithmByUsername(GetAlgorithmByUsernameRequest request);
    GetAlgorithmByCategoryResponse getAlgorithmByCategory(GetAlgorithmByCategoryRequest request);
    GetUnusedAlgorithmsResponse getUnusedAlgorithms(GetUnusedAlgorithmsRequest request);
    GetUnusedAlgorithmByUsernameResponse getUnusedAlgorithmByUsername(GetUnusedAlgorithmByUsernameRequest request);
}
