FROM openjdk:latest
COPY . /app
WORKDIR /app
RUN ./build.sh
RUN ./gradlew clean bootJar
WORKDIR /app/build/libs
RUN mv -v *.jar content_server.jar
RUN mkdir /jar
RUN cp -v ./content_server.jar /jar/
WORKDIR /jar
RUN rm -rfv /app
RUN rm -rfv /root/.sdkman
RUN yum remove -y which unzip zip
ENTRYPOINT [ "java" ]
CMD [ "-jar", "/jar/content_server.jar"]
