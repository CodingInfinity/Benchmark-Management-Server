package com.codinginfinity.benchmark.management.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Class which allows for the mapping of Spring properties in the resources
 * file onto a Java object.
 *
 * @author Andrew Broekman
 * @since 1.0.0
 */

@Getter
@ConfigurationProperties(prefix = "benchmark", ignoreUnknownFields = false)
public class BenchmarkProperties {

    private final CorsConfiguration cors = new CorsConfiguration();

    private final Frontend frontend = new Frontend();

    private final Mail mail = new Mail();

    private final Security security = new Security();

    private final Swagger swagger = new Swagger();


    @Getter
    @Setter
    public static class Frontend {

        private String baseUrl = "localhost:8080";
    }

    @Getter
    @Setter
    public static class Mail {

        private String from = "benchmark-service@cs.up.ac.za";
    }

    @Getter
    public static class Security {

        private final Authentication authentication = new Authentication();

        @Getter
        public static class Authentication {

            private final Oauth oauth = new Oauth();

            @Getter
            @Setter
            public static class Oauth {

                private String clientid;

                private String secret;

                private int tokenValidityInSeconds = 1800;
            }
        }
    }

    @Getter
    @Setter
    public static class Swagger {
        private String title = "Benchmark Management Server API";

        private String description = "Benchmark Management Server API documentation";

        private String version = "0.0.1";

        private String termsOfServiceUrl;

        private String contactName = "Vreda Pieterse";

        private String contactUrl = "http://www.cs.up.ac.za/~vpieterse/";

        private String contactEmail = "vpieterse@cs.up.ac.za";

        private String license = "AGPLv3";

        private String licenseUrl = "https://www.gnu.org/licenses/agpl-3.0.en.html";

        private Boolean enabled;
    }
}
