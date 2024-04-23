FROM openjdk:8-jdk-alpine
#Copiando os arquivos do projeto para o diretorio usr/src/app
COPY . /usr/src/app
COPY mvnw /usr/src/app/mvnw
COPY mvnw.cmd/usr/src/app/mvnw.cmd

# Expondo a porta da APP
EXPOSE 8000
# Executando o comando para subir a aplicacao
CMD ["java", "-jar", "/usr/src/app/app.jar"]
