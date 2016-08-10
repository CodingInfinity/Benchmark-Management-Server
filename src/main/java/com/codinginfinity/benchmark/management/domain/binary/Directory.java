package com.codinginfinity.benchmark.management.domain.binary;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/07/06.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Directory extends INode {

    private List<INode> nodeList = new ArrayList<>();
}
