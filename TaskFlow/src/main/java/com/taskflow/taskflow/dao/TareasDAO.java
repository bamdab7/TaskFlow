package com.taskflow.taskflow.dao;

import com.taskflow.taskflow.pojo.Tareas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class TareasDAO {
    static Connection conn;

    //Get connection to the database
    public static Connection getConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_tareas", "root", "");
            //System.out.print("Conexion establecida");
            return conn;
        } catch (SQLException e) {
            //Catching errors
            System.out.printf("Error" + e.getMessage());
            return null;
        }
    }

    //Creating the table
    public void crearDBTareas() {
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS tareas (" +
                    "  id_tareas int NOT NULL AUTO_INCREMENT," +
                    "  titulo varchar(50) NOT NULL," +
                    "  descripcion text NOT NULL," +
                    "  estado enum('Pendiente','Terminado','Progreso') NOT NULL," +
                    "  usuario_id int NOT NULL," +
                    "  categoria_id int NOT NULL," +
                    "  PRIMARY KEY (id_tareas)," +
                    "  FOREIGN KEY (categoria_id) REFERENCES categorias (id_categorias) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "  FOREIGN KEY (usuario_id) REFERENCES usuarios (id_usuarios) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ")ENGINE=InnoDB;";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.printf("Error al crear la tabla TAREAS.\n Error: " + e.getMessage());
        }
    }

    public void insertTareaporDefecto() {
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String sql = "INSERT INTO tareas (titulo, descripcion, estado, usuario_id, categoria_id) " +
                    " SELECT 'tarea nueva', 'tarea nueva de prueba', 'pendiente', 1, 1" +
                    " WHERE NOT EXISTS (" +
                    "    SELECT 1" +
                    "    FROM tareas" +
                    "    WHERE titulo = 'tarea nueva'" +
                    ");";
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.printf("Error al crear la tarea por defecto.\n Error: " + e.getMessage());
            ;
        }
    }

    //Getting the task from the database
    public ObservableList<Tareas> obtenerListadoTareas(Integer user) {
        ObservableList<Tareas> tareasList = FXCollections.observableArrayList();
        try {
            // List<Tareas> tareas = new ArrayList<>();
            Connection conn = getConnection();
            //  String query = "SELECT * FROM tareas WHERE usuario_id = " + user;

            String query = "SELECT tareas.*, categorias.nombre AS nombre_categoria FROM tareas JOIN categorias ON tareas.categoria_id = categorias.id_categorias WHERE usuario_id = " + user;

            Statement st;
            ResultSet rs;
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Tareas tarea;
            //Getting task
            while ((rs.next())) {
                tarea = new Tareas(rs.getInt("id_tareas"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("estado"), rs.getInt("usuario_id"), rs.getInt("categoria_id"), rs.getString("nombre_categoria"));
                tareasList.add(tarea);
            }
        } catch (Exception e) {
            System.out.printf("Error al obtener el listado de tareas. \n Error: " + e.getMessage());
        }
        return tareasList;
    }

    //Inserts into database
    public void insertarTareas(Tareas tareas) {
        try {
            conn = getConnection();
            String query = "INSERT INTO tareas (id_tareas, titulo, descripcion, estado, usuario_id, categoria_id) VALUES ('" +
                    tareas.getId_tareas() + "','" + tareas.getTitulo() + "','" + tareas.getDescripcion() + "','" + tareas.getEstado() +
                    "','" + tareas.getId_usuarios() + "','" + tareas.getId_categoria() + "')";
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Error al insertar las tareas.\n Error:" + e.getMessage());
        }
    }

    //Searching for task due to the status
    public ObservableList<Tareas> findTaskByStatus(String estado, int id_usuario) {
        ObservableList<Tareas> tareasList = FXCollections.observableArrayList();
        try {
            conn = getConnection();
            String sql = "SELECT tareas.*, categorias.nombre AS nombre_categoria FROM tareas JOIN categorias ON tareas.categoria_id = categorias.id_categorias WHERE estado LIKE '" + estado + "' AND usuario_id LIKE '" + id_usuario + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Tareas tarea;
            //Getting task
            while ((rs.next())) {
                tarea = new Tareas(rs.getInt("id_tareas"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("estado"), rs.getInt("usuario_id"), rs.getInt("categoria_id"), rs.getString("nombre_categoria"));
                tareasList.add(tarea);
            }
            System.out.println(tareasList);
            return tareasList;
        } catch (SQLException e) {
            System.out.printf("Error al encontrar por estado. \n Error: " + e.getMessage());
        }
        return tareasList;
    }

    //Searching for task
    public ObservableList<Tareas> findTaskByName(String nombre, int id_usuario) {
        ObservableList<Tareas> tareasList = FXCollections.observableArrayList();
        try {
            conn = getConnection();
            String sql = "SELECT * FROM tareas WHERE titulo LIKE '%" + nombre + "%'AND usuario_id LIKE '" + id_usuario + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Tareas tarea;

            while ((rs.next())) {
                tarea = new Tareas(rs.getInt("id_tareas"), rs.getString("titulo"), rs.getString("descripcion"), rs.getString("estado"), rs.getInt("usuario_id"), rs.getInt("categoria_id"));
                tareasList.add(tarea);
            }
            return tareasList;
        } catch (SQLException e) {
            System.out.printf("Error al buscar la tarea por el nombre. \n Error: " + e.getMessage());
        }
        return tareasList;
    }

    //Edit the task, only the status
    public void editarTareas(Tareas tareas) {
        try {
            conn = getConnection();
            String query = "UPDATE tareas " +
                    "SET estado = '" + tareas.getEstado()
                    + "' , titulo = '" + tareas.getTitulo()
                    + "' , descripcion = '" + tareas.getDescripcion()
                    + "' , categoria_id = '" + tareas.getId_categoria()
                    + "' WHERE id_tareas = " + tareas.getId_tareas();
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.printf("Error al editar las tareas. \n Error: " + e.getMessage());
        }
    }

    //Delete the task
    public void eliminarTarea(Tareas tarea) {
        try {
            conn = getConnection();
            String query = "DELETE from tareas WHERE id_tareas = " + tarea.getId_tareas();
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.printf("Error eliminando la tarea. \n Error:" + e.getMessage());
        }
    }
}
