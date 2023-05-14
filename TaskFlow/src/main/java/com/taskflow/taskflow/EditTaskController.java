package com.taskflow.taskflow;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditTaskController {
    public TextArea txtDescripcion;
    public TextField txtNombre;
    public ComboBox cmbEstado;
    public Button btnEliminar;
    public Button btnCancelar;
    public Button btnAceptar;

    public void btnEliminar(ActionEvent actionEvent) {
        //Deletes the task, and comeback to home view
    }

    public void btnCancelar(ActionEvent actionEvent) {
        //Close the window
    }

    public void btnAceptar(ActionEvent actionEvent) {
        //Update the task and comeback to homeview
    }
}
