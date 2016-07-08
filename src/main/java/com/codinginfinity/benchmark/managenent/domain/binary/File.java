package com.codinginfinity.benchmark.managenent.domain.binary;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.UUID;

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
