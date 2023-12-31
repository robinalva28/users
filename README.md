# User Management API

API RESTful para la gestión de usuarios, desarrollada en Java 17 con Spring Boot 3, Maven y usando arquitectura hexagonal.

En la raíz del proyecto hay un diagrama de secuencia que muestra el flujo del servicio que expone la aplicación.

## Instrucciones de Uso

### Requisitos Previos

- **Java:** Versión 17 instalada.
- **Maven:** Versión 3.x instalada.

### Configuración del Proyecto

1. **Clonar el Repositorio**
 

2. **Configurar Base de Datos:**
    - No se requiere configuración adicional para el banco de datos en memoria.
    - El script de creación de la base y seeding se ejecuta automáticamente al iniciar la aplicación.
    - En caso de necesitar crear la base de datos manualmente, ejecutar los scripts de bd proporcionados en la ruta `src/main/resources`.

3. **Compilar el Proyecto:**
   ```
   mvn clean install
   ```

4. **Ejecutar la Aplicación:**
   ```
   mvn spring-boot:run
   ```

### Acceso a la API

La API se encuentra disponible en la siguiente ruta local: `http://localhost:8080/api/v1/users`

### Documentación de la API (Swagger)

El swagger se encuentra disponible en la siguiente ruta local: `http://localhost:8080/swagger-ui/index.html`    

### Consola Web de H2 Database

### Acceso a la Consola

La base de datos H2 utilizada en este proyecto proporciona una interfaz web para visualizar y administrar los datos. Para acceder a la consola web de H2:


1. **Ir a la Consola de H2:**
   Ir a un navegador web e ingresa la siguiente URL:
   ```
   http://localhost:8080/h2-console
   ```

2. **Configuración de la Conexión:**
    - **JDBC URL:** Por defecto, la URL de conexión a la base de datos H2 es `jdbc:h2:mem:testdb`.
    - **Usuario:** El usuario por defecto es `sa`.
    - **Contraseña:** password.


### Endpoints Disponibles

#### Crear Usuario

- **Descripción:** Crea un nuevo usuario según los parámetros proporcionados.
- **Ruta:** `POST /api/v1/users`
- **Formato del Cuerpo de la Solicitud (JSON):**
  ```json
  {
    "name": "Nombre Apellido",
    "email": "correo@dominio.com",
    "password": "contraseña123",
    "phones": [
      {
        "number": "1234567",
        "citycode": "1",
        "contrycode": "57"
      }
    ]
  }
  ```
- **Respuesta Exitosa (Código de Estado 201):**
  ```json
  {
    "id": "40352c04-240b-460c-8e67-be5eaf538989",
    "created": "2023-12-14T08:47:57.685845",
    "modified": "2023-12-14T08:47:57.685845",
    "last_login": "2023-12-14T08:47:57.685845",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc0FjdGl2ZSI",
    "name": "juan",
    "email": "juan@rodriguez.ourg",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ],
    "isActive": true
}


- **Errores Comunes:**
    - Código de estado 400: Datos de entrada inválidos.
    - Código de estado 409: Conflicto, el correo ya está registrado en la base de datos.
    - Código de estado 500: Error interno del servidor.

### Pruebas Unitarias

Para ejecutar las pruebas unitarias, ejecutar el siguiente comando:
   ```
   mvn test
   ```
