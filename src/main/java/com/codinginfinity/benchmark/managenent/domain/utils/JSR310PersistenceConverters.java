package com.codinginfinity.benchmark.managenent.domain.utils;

import com.codinginfinity.benchmark.managenent.domain.utils.JSR310DateConverters.*;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by andrew on 2016/07/06.
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