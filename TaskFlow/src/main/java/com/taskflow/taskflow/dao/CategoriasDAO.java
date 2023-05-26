package com.taskflow.taskflow.dao;

import com.taskflow.taskflow.pojo.Categorias;
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
    public void insertarCategoriaDefecto(){
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String sql ="INSERT INTO categorias (nombre)" +
                    " SELECT 'Sin categoria' " +
                    " WHERE NOT EXISTS (" +
                    "    SELECT 1" +
                    "    FROM categorias" +
                    "    WHERE nombre = 'Sin Categoria'" +
                    ");";
            st.executeUpdate(sql);
        } catch (SQLException e) {
             System.out.printf("Error al crear la categoria por defecto.\n Error: " +e.getMessage());;
        }
    }


    //Only get the name and put it in a simple List
    public static ObservableList<String> getAllCategories(){
        try {
            conn = getConnection();
            String sql = "SELECT* FROM categorias";
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
    //Another method to get all categories and put them in the table
    public ObservableList<Categorias> obtenerListadoCategorias() {
        ObservableList<Categorias> categoriasList = FXCollections.observableArrayList();
        try {
            conn = getConnection();
            String query = "SELECT * FROM categorias WHERE nombre NOT LIKE 'Sin Categoria'";
            Statement st;
            ResultSet rs;
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Categorias categoria;
            while ((rs.next())){
                categoria = new Categorias(rs.getInt("id_categorias"), rs.getString("nombre"));
                categoriasList.add(categoria);
            }
        } catch (SQLException e) {
            System.out.printf("Error al obtener el listado de categorias. \n Error: " + e.getMessage());
        }
        return categoriasList;
    }

    public static int getIdCategoria(String nombre){
        try {
            int id = 0;
            conn = getConnection();
            String sql = "SELECT id_categorias FROM categorias WHERE nombre LIKE '" + nombre + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            //Update in case someone adds another category
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
               id = rs.getInt("id_categorias");
            }
            return id;
        } catch (SQLException e) {
            System.out.printf("Error al obtener el id de categoria.\n Error: " + e.getMessage());
            return 0;
        }
    }

    //Creating methods to create and delete a category
    public void eliminarCategoria(Categorias categoria){
        try {
            conn = getConnection();
            String query = "DELETE FROM categorias WHERE id_categorias = " + categoria.getId_categorias();
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.printf("Error al eliminar una categoria. \n Error: " + e.getMessage());
        }
    }
    public void insertarCategoria(Categorias categoria){
        try {
            conn = getConnection();
            String query = "INSERT INTO categorias (id_categorias,nombre) VALUES ('" +
                    categoria.getId_categorias() + "','" + categoria.getNombre() + "')";
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.printf("Error al insertar una categoria. \n Error: "+ e.getMessage());
        }
    }
    public void actualizarCategoria(Categorias categoria){
        try {
            conn = getConnection();
            String sql = "UPDATE categorias " +
                    "SET nombre = '" + categoria.getNombre()
                    + "' WHERE id_categorias = " + categoria.getId_categorias();
            Statement st;
            st = conn.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.printf("Error al actualizar la categoria. \n Error: "+ e.getMessage());
        }
    }
}
