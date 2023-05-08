package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.TareasDAO;
import com.taskflow.taskflow.entity.Tareas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController  implements Initializable {

    public JFXButton btnBuscar;
    public JFXButton btnHome;
    public JFXButton btnCategorias;
    public JFXButton btnPendiente;
    public JFXButton btnProgreso;
    public JFXButton btnTerminado;
    public JFXButton btnAdd;
    public TableView<Tareas> tabladb;
    //Calling classes
    TareasDAO tareasDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareasDAO = new TareasDAO();
        try {
            tareasDAO.getConnection();
            System.out.println("Conexion conseguida");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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