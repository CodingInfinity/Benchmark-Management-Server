package com.codinginfinity.benchmark.management.config;

import com.codinginfinity.benchmark.management.domain.utils.JSR310DateTimeSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.ZonedDateTime;

/**
 * Created by andrew on 2016/08/25.
 */
@Configuration
public class JacksonConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {

        CsvMapper mapper = new CsvMapper();
        return new Jackson2ObjectMapperBuilder()
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .findModulesViaServiceLoader(true)
                .modulesToInstall(javaTimeModule());
    }

    @Bean
    public JavaTimeModule javaTimeModule() {
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(ZonedDateTime.class, JSR310DateTimeSerializer.INSTANCE);
        return module;
    }
}
