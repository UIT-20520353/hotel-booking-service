FROM maven:3.9.6-openjdk-17
WORKDIR /hotel-booking
COPY . .
RUN mvn clean package -DskipTests
CMD mvn spring-boot:run
