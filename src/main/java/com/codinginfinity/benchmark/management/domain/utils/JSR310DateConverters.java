package com.codinginfinity.benchmark.management.domain.utils;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Utility class defining converters to allow for converting between different
 * data formats. Used to allow serialization to persistence store.
 */

public final class JSR310DateConverters {

    private JSR310DateConverters() {
    }

    public static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        public static final ZonedDateTimeToDateConverter INSTANCE = new ZonedDateTimeToDateConverter();
        private ZonedDateTimeToDateConverter() {}

        @Override
        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }

    public static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        public static final DateToZonedDateTimeConverter INSTANCE = new DateToZonedDateTimeConverter();
        private DateToZonedDateTimeConverter() {}

        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneId.systemDefault());
        }
    }
}
