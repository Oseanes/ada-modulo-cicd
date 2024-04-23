FROM openjdk:8-jdk-alpine

# Variáveis de ambiente para o Maven
ENV MAVEN_VERSION 3.9.6
ENV MAVEN_HOME /usr/lib/mvn
ENV PATH $MAVEN_HOME/bin:$PATH

# Baixando e instalando o Maven
RUN wget http://www-us.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
    tar -zxvf apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
    rm apache-maven-${MAVEN_VERSION}-bin.tar.gz && \
    mv apache-maven-${MAVEN_VERSION} /usr/lib/mvn

# Copiando os arquivos do projeto para o diretório usr/src/app
COPY . /usr/src/app

# Expondo a porta da APP
EXPOSE 8000

# Executando o comando para subir a aplicação
CMD ["java", "-jar", "/usr/src/app/app.jar"]
