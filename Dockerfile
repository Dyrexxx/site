FROM openjdk:17
ARG JAR_FILE
COPY ${JAR_FILE} site.jar
ENTRYPOINT ["java","-jar","/site.jar"]