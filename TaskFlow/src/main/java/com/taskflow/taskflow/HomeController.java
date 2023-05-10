package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.TareasDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public JFXButton btnBuscar;
    public JFXButton btnHome;
    public JFXButton btnCategorias;
    public JFXButton btnPendiente;
    public JFXButton btnProgreso;
    public JFXButton btnTerminado;
    public JFXButton btnAdd;
    public TableView tabladb;
    //Calling classes
    TareasDAO tareasDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareasDAO = new TareasDAO();
        try {
            tareasDAO.getConnection();
        } catch (SQLException e) {
            System.out.printf("No se ha podido establecer la conexion. Error: " + e.getMessage());
        }
        System.out.println("Conexion conseguida");
        try {
            mostrarTareas();
        } catch (SQLException e) {
            System.out.printf("No se pudieron mostrar las tareas. Error: " +e.getMessage());
        }
    }
    //Show the task in the table, put every time you want to refresh the table ( searching or refresing the app)
    public void mostrarTareas() throws SQLException {
        tabladb.setItems(tareasDAO.obtenerListadoTareas());
    }

    public void btnBuscar(ActionEvent actionEvent) {

    }

    public void bntHome(ActionEvent actionEvent) {
        System.out.println("Prueba de botones");
    }

    public void btnCategorias(ActionEvent actionEvent) {
    }

    public void btnPendiente(ActionEvent actionEvent) {
    }

    public void btnProgreso(ActionEvent actionEvent) {
    }

    public void btnTerminado(ActionEvent actionEvent) {
    }

    public void btnAdd(ActionEvent actionEvent) {
    }


}