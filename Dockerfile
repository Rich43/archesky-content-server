FROM openjdk:latest
COPY . /app
WORKDIR /app
RUN apk update
RUN apk add gradle
RUN gradle wrapper
RUN ./gradlew clean bootJar
WORKDIR /app/build/libs
RUN mv -v *.jar content_server.jar
RUN mkdir /jar
RUN cp -v ./content_server.jar /jar/
WORKDIR /jar
RUN rm -rfv /app
ENTRYPOINT [ "java" ]
CMD [ "-jar", "/jar/content_server.jar"]
