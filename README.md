# ⚽ Sistema Gestor de Fútbol

El **Sistema Gestor de Fútbol** es una aplicación diseñada para facilitar la gestión integral de clubes de fútbol, jugadores, entrenadores, competiciones y asociaciones. Este sistema permite registrar, consultar y administrar toda la información relacionada con las entidades deportivas de forma eficiente y centralizada.

Este proyecto ha sido desarrollado utilizando **MongoDB** como base de datos principal, aprovechando sus capacidades de almacenamiento NoSQL para manejar documentos flexibles, relaciones dinámicas entre entidades y escalabilidad.

### 🎯 Objetivos del sistema:

- Gestionar clubes de fútbol con sus respectivos entrenadores, jugadores y competiciones.
- Administrar asociaciones deportivas y su relación con los clubes.
- Registrar y consultar información sobre competiciones, incluyendo premios y fechas clave.
- Proveer un sistema de backend moderno, eficiente y adaptable basado en tecnologías actuales.

### 🛠️ Tecnologías utilizadas:

- **Backend**: Java + Spring Boot
- **Base de datos**: MongoDB
- **ORM/ODM**: Spring Data MongoDB
- **Control de versiones**: Git + GitHub
- **Diseño responsivo**: Bootstrap 
- **Vistas**: Thymeleaf 

---

## 📁 Estructura del Proyecto
A continuación se muestra la organización principal del proyecto:
```
sistema-gestor-futbol/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── futbol/
│   │   │           ├── controller/         # Controladores REST
│   │   │           │   ├── ClubController.java
│   │   │           │   ├── JugadorController.java
│   │   │           │   └── ...
│   │   │           ├── model/              # Entidades (documentos MongoDB)
│   │   │           │   ├── Club.java
│   │   │           │   ├── Jugador.java
│   │   │           │   ├── Entrenador.java
│   │   │           │   ├── Competicion.java
│   │   │           │   └── Asociacion.java
│   │   │           ├── repository/         # Interfaces de acceso a datos
│   │   │           │   ├── ClubRepository.java
│   │   │           │   └── ...
│   │   │           ├── config/             # Configuración MongoDB, CORS, etc.
│   │   │           │   └── MongoConfig.java
│   │   │           └── SistemaFutbolApplication.java  # Clase principal
│   │   └── resources/
│   │       ├── application.properties      # Configuración Spring + MongoDB
│   │       └── static/                     # Archivos estáticos (si aplica)
│   │           └── ...
│   └── test/                               # Tests unitarios y de integración
│       └── ...
├── pom.xml / build.gradle                  # Dependencias del proyecto
└── README.md                               # Documentación del proyecto
```
---
## ⚙️ Configuración de la Base de Datos
Para usar MongoDB, asegúrate de tener el servidor ejecutándose localmente o en la nube, y configura application.properties así:

```properties
spring.data.mongodb.uri=${MONGO_URI}
spring.data.mongodb.database=DATABASE
spring.application.name=NAMEAPPLICATION
server.port=8013
```
---
## 🚀 ¿Cómo ejecutar el proyecto?
Pasos claros para ejecutar el proyecto localmente:

1. Clonar el repositorio.

2. Abrirlo en mongoDB.

3. Configura la base de datos con los datos correctos.

4. Ejecutar los micorservicios.

5. Accede a la aplicacion: http://localhost:8013
   

