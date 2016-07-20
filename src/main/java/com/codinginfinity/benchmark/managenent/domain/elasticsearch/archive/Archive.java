package com.codinginfinity.benchmark.managenent.domain.elasticsearch.archive;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

/**
 * Created by andrew on 2016/07/08.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Document(indexName = "archive", type="archive")
public class Archive extends ArchiveNode {

    @Id
    private String id;
}
