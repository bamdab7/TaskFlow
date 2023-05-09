package com.taskflow.taskflow.dao;

import java.sql.*;

public class UsuariosDAO {
    Connection conn;
    //Connect to the database
     public Connection getConnection() throws SQLException {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_tareas", "root", "");
            //System.out.print("Conexion establecida");
            return conn;
        }catch (SQLException e){
            //Catching errors
            System.out.printf("Error " + e.getMessage());
            return  null;
        }
    }

    //Methods

    //Find user and password
    public boolean comprobarUsuario(String username, String password) throws SQLException {
         try{
            Connection  conn = getConnection();
            String query = "SELECT * FROM USUARIOS WHERE username = ? AND password = ?";
            PreparedStatement st = conn.prepareStatement(query);

            //Saving the values
             st.setString(1,username);
             st.setString(2,password);

             ResultSet rs = st.executeQuery();
             boolean autenticated = rs.next();

             return autenticated;
         }catch (Exception e){
             System.out.printf("Error: " +e.getMessage());
             return false;
         }
    }
}
