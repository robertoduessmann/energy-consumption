FROM maven:3.6.2-jdk-11-slim

COPY . /build
RUN cd /build && mvn clean install && mkdir /energy-consumption && cp /build/target/energy-consumption*.jar /energy-consumption/app.jar

ENTRYPOINT [ "sh", "-c", "java -jar /energy-consumption/app.jar" ]