package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.CategoriasDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoriasController implements Initializable {
    public Text nombreUsuario;
    public JFXButton btnHome;
    public JFXButton btnCategorias;
    public JFXButton btnAdd;
    public TableView tablacategorias;

    CategoriasDAO categoriasDAO;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Creating the tooltips
        Tooltip tooltipHome = new Tooltip("Volver al menu principal");
        tooltipHome.setShowDuration(Duration.millis(500));
        Tooltip.install(btnHome, tooltipHome);

        Tooltip tooltipCat = new Tooltip("Estas viendo el listado de categorias");
        tooltipCat.setShowDuration(Duration.millis(500));
        Tooltip.install(btnCategorias, tooltipCat);

        categoriasDAO = new CategoriasDAO();
    }

    public void bntHome(ActionEvent actionEvent) {
    }

    public void btnCategorias(ActionEvent actionEvent) {
    }

    public void btnAdd(ActionEvent actionEvent) {
    }

    public void mostrarCategorias(){
        tablacategorias.setItems(categoriasDAO.obtenerListadoCategorias());
    }
}
