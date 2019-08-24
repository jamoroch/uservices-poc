FROM openjdk:8-jre

ENV JAVA_OPTS="-Xmx1024m -XX:MaxMetaspaceSize=128m"

WORKDIR "/opt/hermes"

COPY target/hermes-1.jar /opt/hermes/hermes.jar

CMD exec java $JAVA_OPTS -jar /opt/hermes/hermes.jar

