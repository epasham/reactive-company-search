FROM maven:onbuild AS buildenv

FROM java:8-jre

EXPOSE 8080

COPY --from=buildenv /usr/src/app/target/*company-search*.jar app.jar

ENV JAVA_OPTS=""
ENV PROFILE=""
ENTRYPOINT ["java", "-Dspring.config.location=file:/config/application.properties", "-jar","app.jar"]
