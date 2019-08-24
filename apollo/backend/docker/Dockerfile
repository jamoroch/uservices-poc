FROM openjdk:8-jre

ENV JAVA_OPTS="-Xmx1024m -XX:MaxMetaspaceSize=128m"

WORKDIR "/opt/apollo"

COPY target/apollo-1.jar /opt/apollo/apollo.jar

CMD exec java $JAVA_OPTS -jar /opt/hermes/apollo.jar

