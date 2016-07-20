package com.codinginfinity.benchmark.managenent.domain.elasticsearch.file;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

/**
 * Created by andrew on 2016/07/06.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Document(indexName = "file", type="file")
public class File extends INode {

    @Id
    private String id;

    private byte[] contents;
}
