FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-Deureka.instance.hostname=${EUREKA_INSTANCE_HOSTNAME}","-Ddb.root.password=${DB_ROOT_PASSWORD}","/app.jar"]