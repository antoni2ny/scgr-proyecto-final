# SCGR - Sistema de Control y Generación de Reportes

Proyecto final del Diplomado Desarrollo de Sistemas con Tecnología Java.

Autor: Calderón Díaz Antoni Jair

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- MariaDB
- Hibernate / JPA
- Maven
- Bootstrap
- OpenPDF

## Descripción

SCGR es una aplicación web para la administración, generación y consulta de reportes administrativos.
El sistema implementa autenticación mediante Spring Security y JWT.

Al iniciar sesión:
- Se genera un Access Token
- Se genera un Refresh Token
- Ambos se almacenan en cookies HTTPOnly

El sistema utiliza filtros personalizados para validar los JWT en cada petición y controlar el acceso según roles.

## Instrucciones de despliegue

1. Crear la base de datos `scgr` en MariaDB.
2. Ejecutar los scripts SQL incluidos en el proyecto.
3. Abrir el proyecto en IntelliJ IDEA.
4. Configurar el archivo `application.properties` con los datos de conexión a MariaDB.
5. Ejecutar la aplicación Spring Boot.
6. Acceder desde el navegador a:
http://localhost:8080

## Base de datos

Los scripts SQL se encuentran dentro de la carpeta:

database/

Archivos incluidos:
- schema.sql
- data.sql

## Usuarios de prueba

- ADMIN: 1001
- USER: 200
- CONSULTA: 3001

## Contraseñas en orden

- ADMIN: 1234
- USER: 1234
- CONSULTA: 1234