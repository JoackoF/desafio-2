# Sistema Escolar – Persistencia con Spring Boot, JPA y MySQL

## Descripción
Este proyecto implementa la estructura de persistencia de un sistema escolar básico utilizando Spring Boot, Spring Data JPA y una base de datos MySQL.

Se gestionan las entidades:
- Profesor
- Materia
- Alumno
- Relación Alumno–Materia (inscripciones)

La base de datos se inicializa mediante el archivo [`schema.sql`](src/main/resources/schema.sql).

## Tecnologías utilizadas
- Java 17
- Spring Boot 3.3.x
- Spring Data JPA
- MySQL
- Maven
- Lombok
- OpenAPI (Swagger UI)

## Configuración de la base de datos
La aplicación utiliza MySQL. Los parámetros de conexión se encuentran en [`application.properties`](src/main/resources/application.properties):

- **JDBC URL:**  
  `jdbc:mysql://localhost:3306/desafio_2?createDatabaseIfNotExist=true&serverTimezone=UTC`
- **Usuario:** `root`  
- **Contraseña:** *(vacía por defecto)*

Asegúrate de tener MySQL en ejecución y el usuario configurado.

## Documentación de la API
Accede a la documentación interactiva Swagger UI en:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Ejecución del proyecto

1. Clona el repositorio.
2. Configura tu base de datos MySQL.
3. Ejecuta el proyecto con Maven:
   ```sh
   ./mvnw spring-boot:run