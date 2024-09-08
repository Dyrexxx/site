FROM openjdk:17-oracle
ARG JAR_FILE
COPY ${JAR_FILE} site.jar
ENTRYPOINT ["java","-jar","/site.jar"]