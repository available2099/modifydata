FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine

ENV OPTS_PROFILES_ACTIVE="prod" \
 OPTS_JVM_OPTS="-Dserver.port=8866" \
 OPTS_ENV_MARK="none" \
 OPTS_NODE_MARK="none" \
 OPTS_OTHER=""

RUN mkdir -p /data/
COPY ./JDhelperConsumer-1.0.0.jar /data/app.jar
HEALTHCHECK --interval=5s --timeout=2s --retries=12 \
  CMD curl --silent --fail localhost:8866/healthz || exit 1
VOLUME ["/data/logs"]
WORKDIR  /data
EXPOSE 8080 8081

CMD OPTS_NODE_MARK=`ifconfig eth0 | grep 'inet addr' | awk '{print $2}' | awk -F: '{print $2}'` && \
    java -Dspring.profiles.active=${OPTS_PROFILES_ACTIVE} \
         -Dhelp.profile.env=${OPTS_ENV_MARK} \
         -Dhelp.profile.node=${OPTS_NODE_MARK} \
         ${OPTS_JVM_OPTS} \
         ${OPTS_OTHER} \
         -Djava.security.egd=file:/dev/./urandom \
         -jar /data/app.jar
