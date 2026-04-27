FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN mkdir -p lib && curl -L -o lib/junit-platform-console-standalone-1.10.2.jar \
  https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar
RUN javac -cp lib/junit-platform-console-standalone-1.10.2.jar -d bin src/*.java
CMD ["java", "-jar", "lib/junit-platform-console-standalone-1.10.2.jar", "execute", "-cp", "bin", "--scan-classpath"]
