# TaskFlow
Proyecto fin de ciclo de desarrollo aplicaciones multiplataforma

## Caracteristicas
- Creación de tareas: Permite a los usuarios crear nuevas tareas con detalles como título, descripción y determinar el estado en el que se encuentra.
- Lista de tareas: Muestra una lista de todas las tareas del usuario.
- Estado de las tareas: Permite a los usuarios modificar el estado de las tareas para marcarlas como completadas, en progreso o sin empezar, y filtrarlas segun estén.
- Filtros y búsquedas avanzadas: Permite a los usuarios buscar y filtrar por nombre las tareas en tiempo real.

## Instalación

1. Clona este repositorio: `git clone https://github.com/bamdab7/TaskFlow.git`
2. Asegurate de tener un gestor de base de datos operando y crea una base de datos llamada ___gestor_tareas___
3. Abre tu proyecto con un IDE (recomendable emplear __IntelliJ IDEA__)
4. Encuentra la clase llamada __TaskFlowApllication.java__ y ejecutala

## Estructura del proyecto
    proyecto/
    ├── src/
    │ ├── main/
    │ │ ├── java/
    │ │ │ ├── com/
    │ │ │ │ ├── taskflow/
    │ │ │ │ │ ├── taskflow/
    │ │ │ │ │ │ ├── dao/
    │ │ │ │ │ │ │ ├── CategoriasDAO.java
    │ │ │ │ │ │ │ ├── TareasDAO.java
    │ │ │ │ │ │ │ ├── UsuariosDAO.java
    │ │ │ │ │ │ ├── pojo/
    │ │ │ │ │ │ │ ├── Categorias.java
    │ │ │ │ │ │ │ ├── Tareas.java
    │ │ │ │ │ │ │ ├── Usuarios.java
    │ │ │ │ │ │ ├── CategoriasController.java
    │ │ │ │ │ │ ├── EditTaskController.java
    │ │ │ │ │ │ ├── FormTaskController.java
    │ │ │ │ │ │ ├── HomeController.java
    │ │ │ │ │ │ ├── LoginController.java
    │ │ │ │ │ │ ├── RegisterController.java
    │ │ │ │ │ │ ├── TaskFlowApplication.java
    │ │ │ ├── module-info.java
    │ │ └── resources/
    │ │  ├── css/
    │ │  ├── icons/
    │ │  ├── imagenLogin.png
    │ │  ├── imagenRegister.png
    │ │  ├── logo.png
    README.md


## Uso



### Importante
Para que la aplicacion funcione correctamente es necesario tener una base de datos creada llamada "gestion_tareas", no hara falta crear ninguna tabla, ya que la aplicacion las crearas automaticamente.  
Por defecto se creara un __admin__ con credenciales:
* user: __admin__
* password: __0000__

