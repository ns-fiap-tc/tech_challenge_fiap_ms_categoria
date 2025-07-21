FROM eclipse-temurin:17.0.13_11-jre-noble

WORKDIR /service

COPY target/*.jar ./app.jar

ARG PROFILE=prod

ENV SPRING_PROFILES_ACTIVE=$PROFILE
ENV DB_NAME=lanch_cat_db
ENV DB_USER=lanch_cat_user
ENV DB_PASSWORD=lanch_cat@1234
ENV DB_HOST=dbcatserver
ENV DB_PORT=5432

CMD ["java", "-jar", "./app.jar"]
