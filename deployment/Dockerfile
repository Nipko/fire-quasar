FROM openjdk
LABEL version="1.0.0" maintainer="nir_levin@hotmail.com" description="Operation Quasar Fire"
ENV TZ America/Bogota

EXPOSE 8080

ENV APP_PORT=8080 \
	ENVIRONMENT=PDN \
	LEVEL_LOG=ERROR \
	PATH_LOCATION=/topsecret \
	PATH_SATELLITE=/topsecret_split \
	NUM_SATELLITES=3 \
	POSITION_KENOBI=-500.0,-200.0 \
	POSITION_SKYWALKER=100.0,-100.0 \
	POSITION_SATO=500.0,100.0 \
	MONGODB_HOST=localhost\
	MONGODB_PORT=27017\
	MONGODB_DATABASE=mongo


WORKDIR /appuser


COPY /target/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom"

USER root

RUN chgrp -R 0 /appuser && chmod -R g+w /appuser && chmod 550  /appuser/app.jar

USER 1001

ENV JAVA_OPTS="-XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom "
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]