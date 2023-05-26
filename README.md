# TaskFlow
Proyecto fin de ciclo de desarrollo aplicaciones multiplataforma

## Caracteristicas
- Creación de tareas: Permite a los usuarios crear nuevas tareas con detalles como título, descripción y determinar el estado en el que se encuentra.
- Lista de tareas: Muestra una lista de todas las tareas del usuario.
- Estado de las tareas: Permite a los usuarios modificar el estado de las tareas para marcarlas como completadas, en progreso o sin empezar, y filtrarlas segun estén.
- Filtros y búsquedas avanzadas: Permite a los usuarios buscar y filtrar por nombre las tareas en tiempo real.

## Capturas de Pantalla
![Ventana de inicio](Documentacion%2FCapturas%20de%20pantalla%2Flogin.png)
* Se presenta se muestra la interfaz de inicio de sesión de la aplicación. Los usuarios pueden ingresar sus credenciales, como usuario y contraseña, para acceder a su cuenta.
---
![Ventana de registro](Documentacion%2FCapturas%20de%20pantalla%2Fregistro.png)
* Aquí se presenta la interfaz de registro, donde los nuevos usuarios pueden crear una cuenta en la aplicación.
---
![Ventana de inicio](Documentacion%2FCapturas%20de%20pantalla%2Fhome.png)
* Ventana de inicio tras iniciar sesion (usuario por defecto). Se muestra la vista de lista de tareas, donde los usuarios pueden ver todas las tareas en un formato organizado.
---
![Actualizacion de tareas](Documentacion%2FCapturas%20de%20pantalla%2Fupdate.png)
* Aqui se muestra la ventana de actualizacion de una tarea en la que se puede modificar la categoria o el estado de esta.
---
![Creacion de tareas](Documentacion%2FCapturas%20de%20pantalla%2Fadd.png)
* Aquí se presenta la interfaz de creación de tareas, donde los usuarios pueden agregar nuevas tareas con detalles como título, descripción, categoria y estado.
---

## Requisitos previos a la instalacion
1. Tener instalado un gestor de base de datos (XAMMP o WAMP)
2. Tener un IDE instalado actualizado (se empleará _openjdk20_) recomendable __IntelliJ IDEA__ ya que busca el JDK automaticamente.

> ### Importante
> Para que la aplicacion funcione correctamente es necesario tener una base de datos creada llamada "__gestion_tareas__", no hara falta crear ninguna tabla, ya que la aplicacion las crearas automaticamente.  
> Por defecto se creara un __admin__ con credenciales:
> * user: __admin__
> * password: __0000__

## Instalación
1. Clona este repositorio: `git clone https://github.com/bamdab7/TaskFlow.git`
2. Asegurate de tener un gestor de base de datos operando y crea una base de datos llamada ___gestor_tareas___
3. Abre tu proyecto con un IDE (recomendable emplear __IntelliJ IDEA__)
4. Encuentra la clase llamada __TaskFlowAplication.java__ y ejecutala

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
Aqui pondre el uso

## Contribución
Si quieres contribuir a este proyecto, sigue los siguientes pasos:
 1. Haz un fork del repositorio.
 2. Crea una nueva rama: `git checkout -b mi-rama`
 3. Realiza tus modificaciones y confírmalas: `git commit -am 'Agregué nuevas características' `
 4. Haz push a la rama: `git push origin mi-rama`
 5. Crea un pull request en GitHub.

## Autores
Nerea Pena Carbajales [@bamdab7](https://github.com/bamdab7/)

## Licencia
MIT License
