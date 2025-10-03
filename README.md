# JavaSpring – Gestión de Clientes

Proyecto de ejemplo con Spring Boot para gestionar clientes. Permite listar, crear, actualizar y eliminar clientes usando REST API, con validaciones, manejo de excepciones y mapeo DTO/Entidad. Además, incluye una interfaz web con **Thymeleaf**.

## Tecnologías

- Java 17
- Spring Boot 3.5.6
- Spring Data JPA / Hibernate
- Oracle Database
- Maven
- Lombok
- Validation (`jakarta.validation`)
- REST API
- Thymeleaf
- Control de excepciones global (`@RestControllerAdvice`)

## Configuración de la base de datos

Antes de ejecutar la aplicación, asegúrate de tener desplegada y accesible una **base de datos Oracle** (por ejemplo, con Docker).

La aplicación está configurada para leer las credenciales de conexión desde **variables de entorno**.  
Durante el desarrollo puedes definirlas directamente en tu IDE (por ejemplo, en IntelliJ: `Run > Edit Configurations > Environment > Variables`).  
En entornos de prueba o producción, deben establecerse como variables de entorno del sistema.  
