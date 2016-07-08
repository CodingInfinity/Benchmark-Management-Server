package com.codinginfinity.benchmark.managenent.domain.binary;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
