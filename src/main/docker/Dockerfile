FROM java:8-jdk-alpine

MAINTAINER Andrew Broekman

ENV BENCHMARK_SLEEP 0

# add directly the war
ADD *.war /app.war

RUN sh -c 'touch /app.war'
VOLUME /tmp
EXPOSE 8081
CMD echo "The application will start in ${BENCHMARK_SLEEP}s..." && \
    sleep ${BENCHMARK_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app.war