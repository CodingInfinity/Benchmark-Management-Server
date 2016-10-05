package com.codinginfinity.benchmark.management.service.experimentManagement.utils;

import com.codinginfinity.benchmark.management.domain.AlgorithmCategory;
import com.codinginfinity.benchmark.management.domain.Dataset;
import com.codinginfinity.benchmark.management.domain.Experiment;
import com.codinginfinity.benchmark.management.domain.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabio on 2016/10/05.
 */
public class ExperimentUtils {

    public static List<Dataset> getAllDatasetsFromExperiment(Experiment experiment){
        List<Dataset> uniqueDatasets = new ArrayList<>();
        for(Job job: experiment.getJobs()){
            if(!uniqueDatasets.contains(job.getDataset())){
                uniqueDatasets.add(job.getDataset());
            }
        }
        return uniqueDatasets;
    }

    public static Boolean intersectionAlgorithmCategory(List<AlgorithmCategory> list1, List<AlgorithmCategory> list2) {
        List<AlgorithmCategory> list = new ArrayList<>();

        for (AlgorithmCategory t1 : list1) {
            for (AlgorithmCategory t2 : list2) {
                if(t1.getId().equals(t2.getId())){
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean intersectionDataset(List<Dataset> list1, List<Dataset> list2) {
        List<Dataset> list = new ArrayList<>();

        for (Dataset t1 : list1) {
            for (Dataset t2 : list2) {
                if(t1.getId().equals(t2.getId())){
                    return true;
                }
            }
        }
        return false;
    }
}
