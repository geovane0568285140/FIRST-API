
# Gradlew

First create folder resources and file application.properties 
src/main/resources/application.properties

 after this create gradlew:
<br>
```bash
 ./gradlew build
```
# File properties
## **Configuration for running API in file properties:**

**Created DB in docker:** 
```bash 
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=you-Password" --name name-you-db  --network rede-connection-with-api -v sql_server:/var/opt/mssql  -d --restart=always  --hostname sql  --platform linux/amd64  -d mcr.microsoft.com/mssql/server:2022-latest
```
<br>

**Connection with DB in file properties with api run docker or compiler local:**

Run in compiler local - test debug
```
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=db.frota;trustServerCertificate=true
```
Run in docker:
```
spring.datasource.url=jdbc:sqlserver://name-you-db:1433;databaseName=db.frota;trustServerCertificate=true
```
Run in docker or teste with in compiler local with docker db
```
spring.datasource.username=sa
spring.datasource.password=you-password
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver
```
<br>

**CONFIGURATION DO JPA / HIBERNATE**
```
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
```
<br>

**CONFIGURATION DO SECURITY / SECRET**

Run in compiler local - test debug:
```
api.security.token.secret=${you-secret}
```
Run in docker api:
```
api.security.token.secret=${JWT_SECRET}
```