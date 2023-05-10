package com.taskflow.taskflow.dao;
import com.taskflow.taskflow.pojo.Tareas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TareasDAO {
    Connection conn;
    //Get connection to the database
    public Connection getConnection() throws SQLException {
        try{
            conn =DriverManager.getConnection("jdbc:mysql://localhost/gestion_tareas", "root", "");
            //System.out.print("Conexion establecida");
            return conn;
        }catch (SQLException e){
            //Catching errors
            System.out.printf("Error" + e.getMessage());
            return  null;
        }
    }
    //Creating the table
     public void crearDBTareas() throws SQLException {
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
    }

    //Getting the task from the database
    public ObservableList<Tareas> obtenerListadoTareas() throws SQLException {
         ObservableList<Tareas> tareasList = FXCollections.observableArrayList();
       // List<Tareas> tareas = new ArrayList<>();
        Connection conn = getConnection();
        String query = "SELECT * FROM tareas";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs =st.executeQuery(query);
            Tareas tarea;
            //Getting task
            while ((rs.next())){
                tarea = new Tareas(rs.getInt("id_tareas"),rs.getString("titulo"),rs.getString("descripcion"), rs.getString("estado"),rs.getInt("usuario_id"),rs.getInt("categoria_id"));
                tareasList.add(tarea);
                //System.out.println(tarea);
            }
        }catch (Exception e){
            System.out.printf("Error" + e.getMessage());
        }
        return tareasList;
    }
}
