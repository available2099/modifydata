FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine

ENV OPTS_PROFILES_ACTIVE="prod" \
 OPTS_JVM_OPTS="-Dserver.port=8080 -Dmanagement.server.port=8081" \
 OPTS_ENV_MARK="none" \
 OPTS_NODE_MARK="none" \
 OPTS_OTHER=""

RUN mkdir -p /data/
COPY ./target/JDhelperProvider-1.0.0.jar /data/app.jar

VOLUME ["/data/logs"]
WORKDIR  /data
EXPOSE 8080 8081

CMD OPTS_NODE_MARK=`ifconfig eth0 | grep 'inet addr' | awk '{print $2}' | awk -F: '{print $2}'` && \
    java -Dspring.profiles.active=${OPTS_PROFILES_ACTIVE} \
         -Decarx.profile.env=${OPTS_ENV_MARK} \
         -Decarx.profile.node=${OPTS_NODE_MARK} \
         ${OPTS_JVM_OPTS} \
         ${OPTS_OTHER} \
         -Djava.security.egd=file:/dev/./urandom \
         -jar /data/app.jar
