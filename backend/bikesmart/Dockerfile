FROM maven:3.9.7-amazoncorretto-21 AS MAVEN_BUILD
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package

FROM amazoncorretto:21
EXPOSE 8080
COPY --from=MAVEN_BUILD /target/bikesmart-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]