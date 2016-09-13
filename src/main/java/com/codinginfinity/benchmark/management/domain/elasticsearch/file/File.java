package com.codinginfinity.benchmark.management.domain.elasticsearch.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

/**
 * Defines a file object used to represent files in user uploaded archives.
 * Object contains id, filename and contents.
 *
 * @author Andrew Broekman
 * @version 1.0.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(indexName = "file", type="file")
public class File extends INode {

    @Id
    private String id;

    /**
     * Byte array to store the byte content of the file.
     */
    private byte[] contents;
}
