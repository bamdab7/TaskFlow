package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.CategoriasDAO;
import com.taskflow.taskflow.pojo.Categorias;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategoriasController implements Initializable {
    public Text nombreUsuario;
    public JFXButton btnHome;
    public JFXButton btnAdd;
    public TableView<Categorias> tablacategorias;
    public JFXButton btnDelete;
    public TextField txtNombre;
    private int id;

    CategoriasDAO categoriasDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Creating the tooltips
        Tooltip tooltipHome = new Tooltip("Volver al menu principal");
        tooltipHome.setShowDuration(Duration.millis(500));
        Tooltip.install(btnHome, tooltipHome);

        categoriasDAO = new CategoriasDAO();

        //In order to get the information
        tablacategorias.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Categorias categoria = tablacategorias.getSelectionModel().getSelectedItem();
                txtNombre.setText(categoria.getNombre());
                id = categoria.getId_categorias();
            }
        });
    }

    public void bntHome(ActionEvent actionEvent) {
        //Sends to another scene
        Scene sceneHome = TaskFlowApplication.sceneHome;
        Stage stageHome = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stageHome.setScene(sceneHome);
        stageHome.show();

        try {
            TaskFlowApplication.controladorHome.mostrarTareas();
        } catch (SQLException e) {
            System.out.printf("Error al mostrar tareas. \n Error: " + e.getMessage());
        }
        TaskFlowApplication.controladorHome.busquedaDinamica();
        ;
    }


    public void btnAdd(ActionEvent actionEvent) {
        //Collects the values them in a form to add or Edit?¿
        Categorias categoria = new Categorias();

        categoria.setId_categorias(id);
        categoria.setNombre(txtNombre.getText());
        if (!txtNombre.getText().isEmpty()) {
            if (id == 0) {
                //So then insert
                categoriasDAO.insertarCategoria(categoria);
                id = 0;
                //Update values
                mostrarCategorias();
                eliminarCampos();
            } else {
                //Update?
                categoriasDAO.actualizarCategoria(categoria);
                id = 0;
                //Update values
                mostrarCategorias();
                eliminarCampos();
            }
        }
    }

    public void mostrarCategorias() {
        tablacategorias.setItems(categoriasDAO.obtenerListadoCategorias());
    }

    public void eliminarCampos() {
        txtNombre.clear();
    }

    public void btnDelete(ActionEvent actionEvent) {
        Categorias categorias = tablacategorias.getSelectionModel().getSelectedItem();
        if (categorias == null) {
            //U must have select something
            mostrarAlerta("Error", "Debe tener seleccionado una categoria");
        } else {
            categoriasDAO.eliminarCategoria(categorias);
            mostrarCategorias();
        }
    }

    //Shows an alert when something is wrong
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void onButtonMousePressed(MouseEvent mouseEvent) {
        btnDelete.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);
        btnAdd.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), true);

    }
    public void onButtonMouseReleased(MouseEvent mouseEvent) {
        btnDelete.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);
        btnAdd.pseudoClassStateChanged(PseudoClass.getPseudoClass("pressed"), false);

    }
}
