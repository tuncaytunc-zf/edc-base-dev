FROM openjdk:11-jre-slim-buster
ARG JAR

ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8090

WORKDIR /app
COPY $JAR app.jar

EXPOSE 8080

ENTRYPOINT java \
    -Djava.security.edg=file:/dev/.urandom \
    -jar app.jar
