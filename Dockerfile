FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
LABEL maintainer="johndeathsmell@gmail.com"
ADD . /src
WORKDIR /src
RUN ./mvnw package -DskipTests
EXPOSE 8010
EXPOSE 5432
ENTRYPOINT ["java","-jar","target//manager-0.0.1-SNAPSHOT.jar"]