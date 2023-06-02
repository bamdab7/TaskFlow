# TaskFlow
Gestor de tareas de facil uso.

## Tabla de Contenido

- [Características de la aplicacion](##Características)
- [Capturas de pantalla](##Capturas de Pantalla)
- [Requisitos](##Requisitos previos a la instalacion)
- [Instalación](##Instalación y uso)
- [Estructura de la aplicación](##Estructura del proyecto)
- [Dependencias](##Dependencias)
- [Como contribuir?](##Contribución)
- [Autor/es](##Autores)
- [Licencia](#licencia)


## Características
- __Creación de tareas__: Permite a los usuarios crear nuevas tareas con detalles como título, descripción y determinar el estado en el que se encuentra.
- __Lista de tareas__: Muestra una lista de todas las tareas del usuario.
- __Estado de las tareas__: Permite a los usuarios modificar el estado de las tareas para marcarlas como completadas, en progreso o sin empezar, y filtrarlas segun estén.
- __Filtros y búsquedas avanzadas__: Permite a los usuarios buscar y filtrar por nombre las tareas en tiempo real.

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
![categorias.png](Documentacion%2FCapturas%20de%20pantalla%2Fcategorias.png)
* Ventana de las categorias que existen en la aplicacion, con posibilidad de modificarlas (editando y borrando) y añadir más.
---

## Requisitos previos a la instalacion
1. Tener instalado un gestor de base de datos ([XAMPP](https://www.apachefriends.org/es/download.html) o [WampServer](https://www.wampserver.com/en/))
2. Tener un IDE instalado actualizado (se empleará _openjdk20_) recomendable __[IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/#section=windows)__ ya que busca el JDK automaticamente.

> ### Importante
> Para que la aplicacion funcione correctamente es necesario tener una base de datos creada llamada "__gestion_tareas__", no hara falta crear ninguna tabla, ya que la aplicacion las crearas automaticamente.  
> Por defecto se creara un __admin__ con credenciales:
> * user: __admin__
> * password: __0000__

## Instalación y uso
1. Clona este repositorio: `git clone https://github.com/bamdab7/TaskFlow.git`
2. Asegurate de tener un gestor de base de datos operando y crea una base de datos llamada ___gestor_tareas___
3. Abre tu proyecto con un IDE (recomendable emplear __[IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/#section=windows)__)
4. Encuentra la clase llamada __TaskFlowAplication.java__ y ejecutala

La aplicacion cuenta con una intefaz intuitiva en la que el usuario podra desenvolverse facilmente por ella.  
Lo primero que tendra que hacer el usuario una vez descargue la aplicación será, o bien crearse un usuario o registrarse con el que se genera por defecto.  
Una vez dentro de la aplicación, el usuario verá distintos botones que le permitirán (cuando tenga más tareas) filtrar tanto por el nombre (mediante un filtro de texto) o por el estado en el que deja las tareas (mediante botones laterales).  
También encontrara facil la manera en la que puede añadir o editar las tareas, simplemente clicando en ellas y modificandolas a su gusto, una vez cerrado la ventana de moficación, estas mismas se actualizarán.  
Se proporciona una ventana en la que se permite la gestión de las categorias que existen, permitiendo al usuario poder añadir o modificar las que ya existen.  

---
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
    │ │  ├── degrad1.png
    │ │  ├── degrad2.png
    │ │  ├── degrad3.png
    │ │  ├── degrad4.png
    │ │  ├── logo.png
    README.md

## Dependencias
A continuación, se enumeran las dependencias empleadas en este proyecto:  

### JFoenix
    <dependency>
            <groupId>com.jfoenix</groupId>
            <artifactId>jfoenix</artifactId>
            <version>9.0.10</version>
    </dependency>
> JFoenix is an open source Java library, that implements Google Material Design using Java components
### MySQL
    <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.0.32</version>
    </dependency>
> MySQL is an Oracle-backed open source relational database management system (RDBMS) based on Structured Query Language (SQL).
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
