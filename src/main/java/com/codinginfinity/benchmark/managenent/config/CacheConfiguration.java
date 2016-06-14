package com.codinginfinity.benchmark.managenent.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Created by andrew on 2016/06/13.
 */
@Configuration
@EnableCaching
@AutoConfigureAfter
public class CacheConfiguration {

    private final Logger log = LoggerFactory.getLogger(CacheConfiguration.class);

    private CacheManager cacheManager;

    @PreDestroy
    public void destroy() {
        log.info("Closing Cache Manager");
    }

    @Bean
    public CacheManager cacheManager() {
        log.debug("No cache configured");
        cacheManager = new NoOpCacheManager();
        return cacheManager;
    }


}
