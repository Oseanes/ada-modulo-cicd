FROM eclipse-temurin:21-jdk

# Copiando os arquivos do projeto para o diretório usr/src/app
COPY . /usr/src/app

RUN apt-get install maven

# Construindo o projeto com o Maven
RUN mvn install

# Expondo a porta da APP
EXPOSE 8000

# Executando o arquivo jar
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]