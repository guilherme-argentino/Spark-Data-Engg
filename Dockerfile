# Maven
FROM maven:3.8.1-openjdk-11-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn clean -e -B package

# Java
FROM openjdk:11-jre-slim-buster
WORKDIR /app
COPY --from=builder /app/target/Spark-Data-Engg-1.0-SNAPSHOT-jar-with-dependencies.jar .
COPY run.sh ./run.sh

# Use shell script to support passing application name and its arguments to the ENTRYPOINT
ENTRYPOINT ["./run.sh"]
# and CMD to support default argument
CMD ["com.learning.sparkdataengg.setup.SetupPrerequisites"]