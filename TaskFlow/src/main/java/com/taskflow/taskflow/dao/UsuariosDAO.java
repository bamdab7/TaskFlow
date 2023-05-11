package com.taskflow.taskflow.dao;

import com.taskflow.taskflow.pojo.Usuarios;

import java.sql.*;

public class UsuariosDAO {
    Connection conn;
    //Connect to the database
     public Connection getConnection() {
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_tareas", "root", "");
            //System.out.print("Conexion establecida");
        }catch (SQLException e){
            //Catching errors
            System.out.printf("Ha ocurrido un error de conexion. \n Error: " + e.getMessage());
        }
        return conn;
    }
    //Create DB
     public void crearDBUsuarios(){
         try {
             conn = getConnection();
             Statement st = null;
             st = conn.createStatement();
                  String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                   "  id_usuarios int NOT NULL AUTO_INCREMENT," +
                   "  username varchar(50) NOT NULL," +
                   "  nombre varchar(50) NOT NULL," +
                   "  email varchar(50) NOT NULL," +
                   "  password varchar(50) NOT NULL," +
                   "  PRIMARY KEY (id_usuarios)" +
                   ")ENGINE=InnoDB";
                  st.executeUpdate(sql);
         } catch (SQLException e) {
             System.out.printf("Error al crear la base de datos USUARIOS.\n Error: " +e.getMessage());;
         }
    }

    //Methods

    //Find user and password
    public boolean comprobarUsuario(String username, String password) {
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
             System.out.printf("Error al comprobar usuario.\n Error:  " +e.getMessage());
             return false;
         }
    }

    //Insert users into dabatase
    public void insertUsuario(Usuarios usuario){
        try{
            Connection conn = getConnection();
            String query = "INSERT INTO usuarios(id_usuarios,username,nombre,email,password) VALUES ('" +  usuario.getId_usuario() + "','" +
            usuario.getUsername()+ "','"+ usuario.getNombre() + "','"+ usuario.getEmail() + "','" + usuario.getPassword() + "')";
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception e){
            System.out.printf("Error al insertar usuario.\n Error: " + e.getMessage());
        }
    }
}
