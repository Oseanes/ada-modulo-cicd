FROM eclipse-temurin:21-jdk

# Atualizando e instalando dependências necessárias
RUN apt-get update \
  && apt-get install -y ca-certificates curl git --no-install-recommends \
  && rm -rf /var/lib/apt/lists/*

# Variáveis de ambiente para o Maven
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_VERSION 3.9.6
ENV USER_HOME_DIR "/root"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

# Copiando o Maven do contêiner maven:3.9.6-eclipse-temurin-11
COPY --from=maven:3.9.6-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.6-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml

# Criando um link simbólico para mvn
RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

# Copiando os arquivos do projeto para o diretório usr/src/app
COPY . /usr/src/app

# Set environment variables if needed
ENV DATASOURCE_URL=jdbc:postgresql://localhost:5432/db
ENV DATASOURCE_USERNAME=postgres
ENV DATASOURCE_PASSWORD=&insert312

# Navegando para o diretório do projeto
WORKDIR /usr/src/app

# Construindo o projeto com o Maven
RUN mvn install

# Expondo a porta da APP
EXPOSE 8000

# Executando o arquivo jar
CMD ["java", "-jar", "target/myapp-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]
