package com.codinginfinity.benchmark.management.service.experimentManagement.respones;

/**
 * Created by fabio on 2016/08/11.
 */

import lombok.*;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetExperimentWeeklyReportResponse {
    DayOfWeek startDate;
    List<Integer> totalExperiments;
    List<Integer> jobsCompleted;
    List<Integer> totalWallTime;
    List<Integer> totalCPU;
    List<Integer> totalMemory;
}
