package com.codinginfinity.benchmark.management.domain.elasticsearch.archive;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/07/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ArchiveNode {

    private String name;

    private List<ArchiveNode> nodeList = new ArrayList<>();
}
