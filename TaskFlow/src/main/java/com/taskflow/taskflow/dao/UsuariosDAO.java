package com.taskflow.taskflow.dao;

import com.taskflow.taskflow.pojo.Usuarios;

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
            System.out.printf("Ha ocurrido un error de conexion. Error: " + e.getMessage());
            return  null;
        }
    }
    //Create DB
     public void crearDBUsuarios() throws SQLException {
       conn = getConnection();
       Statement st = conn.createStatement();

       String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
               "  id_usuarios int NOT NULL AUTO_INCREMENT," +
               "  username varchar(50) NOT NULL," +
               "  nombre varchar(50) NOT NULL," +
               "  email varchar(50) NOT NULL," +
               "  password varchar(50) NOT NULL," +
               "  PRIMARY KEY (id_usuarios)" +
               ")ENGINE=InnoDB";
       st.executeUpdate(sql);
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
             System.out.printf("Error al comprobar usuario : " +e.getMessage());
             return false;
         }
    }

    //Insert users into dabatase
    public void insertUsuario(Usuarios usuario) throws SQLException {
        Connection conn = getConnection();
        String query = "INSERT INTO usuarios(id_usuarios,username,nombre,email,password) VALUES ('" +  usuario.getId_usuario() + "','" +
        usuario.getUsername()+ "','"+ usuario.getNombre() + "','"+ usuario.getEmail() + "','" + usuario.getPassword() + "')";
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception e){
            System.out.printf("Error al insertar usuario. Error: " + e.getMessage());
        }
    }
}
//"INSERT INTO empleados (idEmpleado, nombre, apellidos, fecha_nacimiento, categoria) VALUES ('\" +\n" +
//                "                empleado.getIdEmpleado()+\"','\"+ empleado.getNombre()+\"','\" +
//                empleado.getApellidos()+\"','\"+empleado.getFecha_nacimiento()+\"','\"+ empleado.getCategoria() + \"')\";\n" +
//                "        ";