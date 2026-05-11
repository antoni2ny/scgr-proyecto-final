# SCGR - Sistema de Control y Generación de Reportes

Proyecto final del Diplomado Desarrollo de Sistemas con Tecnología Java.

Autor: Calderón Díaz Antoni Jair

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- JWT
- MariaDB
- Hibernate / JPA
- Maven
- Bootstrap
- OpenPDF
- Lombok

## Descripción

SCGR es una aplicación web para la administración, generación y consulta de reportes administrativos.

El sistema implementa autenticación y autorización mediante Spring Security y JWT.

Al iniciar sesión:
- Se genera un Access Token
- Se genera un Refresh Token
- Ambos se almacenan mediante cookies HTTPOnly

El sistema utiliza filtros personalizados para validar los JWT en cada petición y controlar el acceso según roles de usuario.

## Características principales

- Inicio de sesión seguro mediante Spring Security
- Autenticación basada en JWT
- Uso de Refresh Token
- Manejo de roles:
    - ADMIN
    - USER
    - CONSULTA
- CRUD de usuarios
- CRUD de reportes
- Control de acceso por roles
- Generación de reportes PDF
- Bitácora de movimientos
- Validaciones en formularios
- Interfaz web responsive con Bootstrap

## Flujo general de uso

1. El usuario inicia sesión en el sistema.
2. Spring Security valida las credenciales.
3. Se generan Access Token y Refresh Token.
4. Los tokens se almacenan en cookies HTTPOnly.
5. El usuario accede a las funcionalidades según su rol.
6. El filtro personalizado JWT valida la autenticación en cada petición.
7. El usuario puede cerrar sesión eliminando los tokens almacenados.

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
- `schema.sql`
- `data.sql`

## Usuarios de prueba

| Rol | Usuario | Contraseña |
|------|---------|-------------|
| ADMIN | 1001    | 1234 |
| USER | 2001    | 1234 |
| CONSULTA | 3001    | 1234 |

## Notas

- El proyecto utiliza autenticación basada en JWT.
- Los tokens se almacenan mediante cookies HTTPOnly.
- El sistema implementa filtros personalizados con Spring Security.
- El proyecto fue desarrollado con fines académicos para el Diplomado Desarrollo de Sistemas con Tecnología Java Emisión 18.