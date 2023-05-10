package com.taskflow.taskflow.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoriasDAO {
     Connection conn;
    //Connect to the database
     public Connection getConnection() throws SQLException {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_tareas", "root", "");
            //System.out.print("Conexion establecida");
            return conn;
        }catch (SQLException e){
            //Catching errors
            System.out.printf("Ha ocurrido un error de conexion. Error: " + e.getMessage());
            return  null;
        }
    }

    //Creatin table
    public void crearDBCategorias() throws SQLException {
        conn = getConnection();
        Statement st = conn.createStatement();

       String sql = "CREATE TABLE IF NOT EXISTS categorias (" +
               "  id_categorias int NOT NULL AUTO_INCREMENT," +
               "  nombre varchar(50) NOT NULL," +
               "  PRIMARY KEY (id_categorias)" +
               ")ENGINE=InnoDB";
       st.executeUpdate(sql);
    }
}
