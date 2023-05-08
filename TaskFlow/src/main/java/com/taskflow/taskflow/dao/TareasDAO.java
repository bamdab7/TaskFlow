package com.taskflow.taskflow.dao;
import java.sql.*;
public class TareasDAO {

    //Get connection to the database
    public Connection getConnection() throws SQLException {
        Connection conn;
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
}
