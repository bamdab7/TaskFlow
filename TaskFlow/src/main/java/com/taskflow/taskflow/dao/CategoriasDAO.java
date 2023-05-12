package com.taskflow.taskflow.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CategoriasDAO {
    static Connection conn;

    static ObservableList<String> items = FXCollections.observableArrayList();

    //Connect to the database
    public static Connection getConnection() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_tareas", "root", "");
            //System.out.print("Conexion establecida");
            return conn;
        } catch (SQLException e) {
            //Catching errors
            System.out.printf("Ha ocurrido un error de conexion.\n Error: " + e.getMessage());
            return null;
        }
    }

    //Creatin table
    public void crearDBCategorias() {
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS categorias (" +
                    "  id_categorias int NOT NULL AUTO_INCREMENT," +
                    "  nombre varchar(50) NOT NULL," +
                    "  PRIMARY KEY (id_categorias)" +
                    ")ENGINE=InnoDB";
            st.executeUpdate(sql);
        } catch (SQLException e) {
             System.out.printf("Error al crear la base de datos CATEGORIAS.\n Error: " +e.getMessage());;
        }
    }

    public static ObservableList<String> getAllCategories(){
        try {
            conn = getConnection();
            String sql = "SELECT DISTINCT nombre FROM categorias";
            PreparedStatement ps = conn.prepareStatement(sql);
            //Update in case someone adds another category
            items.removeAll(items);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                items.add(rs.getString("nombre"));
            }
            return items;
        } catch (SQLException e) {
            System.out.printf("Error al obtener las categorias.\n Error: " + e.getMessage());
            return null;
        }
    }
}
