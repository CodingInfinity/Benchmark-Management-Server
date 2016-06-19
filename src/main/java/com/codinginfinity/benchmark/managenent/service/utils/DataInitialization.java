package com.codinginfinity.benchmark.managenent.service.utils;

import com.codinginfinity.benchmark.managenent.domain.Authority;
import com.codinginfinity.benchmark.managenent.repository.AuthorityRepository;
import com.codinginfinity.benchmark.managenent.security.AuthoritiesConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrew on 2016/06/16.
 */
@Component
@Slf4j
public class DataInitialization {

    @Inject
    private AuthorityRepository authorityRepository;

    @PostConstruct
    public void init() {
        log.debug("Populating datasource with authorities");
        List<Authority> roles = new ArrayList<>(3);
        roles.add(new Authority(AuthoritiesConstants.ANONYMOUS));
        roles.add(new Authority(AuthoritiesConstants.ADMIN));
        roles.add(new Authority(AuthoritiesConstants.USER));

        roles.forEach(role -> {
            if (authorityRepository.findOne(role.getName()) == null)
                authorityRepository.save(role);
        });
    }
}
