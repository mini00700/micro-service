FROM java:8
VOLUME /tmp
ADD micro-service-0.0.1.jar app.jar
RUN bash -c 'touch /app.war'
EXPOSE 8090
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
