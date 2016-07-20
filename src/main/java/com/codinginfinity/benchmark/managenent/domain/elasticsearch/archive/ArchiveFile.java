package com.codinginfinity.benchmark.managenent.domain.elasticsearch.archive;

import lombok.*;

import javax.persistence.Id;

/**
 * Created by andrew on 2016/07/20.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ArchiveFile extends ArchiveNode {

    @Id
    private String id;
}
