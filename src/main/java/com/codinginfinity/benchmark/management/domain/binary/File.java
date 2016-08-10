package com.codinginfinity.benchmark.management.domain.binary;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class File extends FileProperties {

    private byte[] contents;
}
