package com.codinginfinity.benchmark.management.domain.binary;

import lombok.*;

import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by andrew on 2016/07/08.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FileProperties extends INode {

    @Id
    private String id = UUID.randomUUID().toString();

    public FileProperties(File file) {
        super(file.getName());
        this.setId(file.getId());
    }
}
