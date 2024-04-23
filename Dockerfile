FROM maven:3.8.7-eclipse-temurin-19-alpine

LABEL maintainer="Oseanes Dias de Farias <oseanes.farias@gmail.com>"

# Copiando os arquivos do projeto para o diretório usr/src/app
COPY . /usr/src/app

# Set environment variables if needed
ENV DATASOURCE_URL=jdbc:postgresql://localhost:5432/db
ENV DATASOURCE_USERNAME=postgres
ENV DATASOURCE_PASSWORD=&insert312

# Expondo a porta da APP
EXPOSE 8000

# Run the jar file
CMD ["java", "-jar", "target/myapp-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]