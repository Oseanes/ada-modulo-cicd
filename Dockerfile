FROM openjdk:8-jdk-alpine

# Instalando o Maven
RUN apk add --no-cache maven

# Copiando os arquivos do projeto para o diretório usr/src/app
COPY . /usr/src/app

# Expondo a porta da APP
EXPOSE 8000

# Executando o comando para subir a aplicação
CMD ["java", "-jar", "/usr/src/app/app.jar"]
