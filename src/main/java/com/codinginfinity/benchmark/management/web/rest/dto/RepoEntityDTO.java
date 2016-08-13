package com.codinginfinity.benchmark.management.web.rest.dto;

import com.codinginfinity.benchmark.management.domain.RepoEntity;
import com.codinginfinity.benchmark.management.domain.User;
import com.codinginfinity.benchmark.management.domain.elasticsearch.archive.Archive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by andrew on 2016/07/20.
 */
@Getter
@NoArgsConstructor
@ToString
public class RepoEntityDTO {

    private Long id;

    private String name;

    private User user;

    private String description;

    private Archive archive;

    public <T extends RepoEntity<?>> RepoEntityDTO(T repoEntity, Archive archive) {
        this.id = repoEntity.getId();
        this.name = repoEntity.getName();
        this.user = repoEntity.getUser();
        this.description = repoEntity.getDescription();
        this.archive = archive;
    }
}
