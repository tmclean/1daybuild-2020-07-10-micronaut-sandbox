FROM openjdk:14-alpine
COPY build/libs/pet-tracker-*-all.jar pet-tracker.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "pet-tracker.jar"]