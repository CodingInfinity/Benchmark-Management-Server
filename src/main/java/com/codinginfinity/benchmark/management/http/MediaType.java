package com.codinginfinity.benchmark.management.http;

/**
 * Created by andrew on 2016/08/25.
 */
public class MediaType extends org.springframework.http.MediaType {

    public MediaType(String type) {
        super(type);
    }

    public static final org.springframework.http.MediaType TEXT_CSV;

    public static final String TEXT_CSV_VALUE = "text/csv";

    static {
        TEXT_CSV = valueOf(TEXT_CSV_VALUE);
    }
}
