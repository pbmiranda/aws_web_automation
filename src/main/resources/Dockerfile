FROM maven:3.8.5-openjdk-11-slim
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install -DskipTests=true
CMD ["mvn", "test"]