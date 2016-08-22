package com.codinginfinity.benchmark.management.domain.utils;

import com.codinginfinity.benchmark.management.domain.utils.JSR310DateConverters.DateToZonedDateTimeConverter;
import com.codinginfinity.benchmark.management.domain.utils.JSR310DateConverters.ZonedDateTimeToDateConverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Utility class defining converters to allow for converting between different
 * data formats. Used to allow serialization to persistence store. Converters
 * are auto applied by the JVM.
 */

public final class JSR310PersistenceConverters {

    private JSR310PersistenceConverters() {
    }

    @Converter(autoApply = true)
    public static class ZonedDateTimeConverter implements AttributeConverter<ZonedDateTime, Date> {

        @Override
        public Date convertToDatabaseColumn(ZonedDateTime zonedDateTime) {
            return ZonedDateTimeToDateConverter.INSTANCE.convert(zonedDateTime);
        }

        @Override
        public ZonedDateTime convertToEntityAttribute(Date date) {
            return DateToZonedDateTimeConverter.INSTANCE.convert(date);
        }
    }
}
