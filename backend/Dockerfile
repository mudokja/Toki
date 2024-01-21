FROM azul/zulu-openjdk:17
LABEL authors="mudokja@gmail.com"

ARG DEFAULT_PORT=8081
ARG JAR_FILE=build/libs/*.jar
ENV DEFAULT_BACKEND_BASEURL=http://localhost

COPY ${JAR_FILE} backend-app.jar

ENV PORT ${DEFAULT_PORT}

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "/backend-app.jar"]