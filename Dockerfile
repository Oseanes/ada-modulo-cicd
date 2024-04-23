FROM eclipse-temurin:21-jdk

# Copiando os arquivos do projeto para o diretório usr/src/app
COPY . /usr/src/app

# Atualizando, instalando dependências e construindo o projeto com o Maven
RUN apt-get update && \
    apt-get -y install --no-install-recommends maven=3.9.6 && \
    mvn install

# Expondo a porta da APP
EXPOSE 8000

# Executando o arquivo jar
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
