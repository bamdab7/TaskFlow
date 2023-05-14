package com.taskflow.taskflow;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTaskController {
    public TextArea txtDescripcion;
    public TextField txtNombre;
    public ComboBox cmbEstado;
    public Button btnEliminar;
    public Button btnCancelar;
    public Button btnAceptar;

    private Stage dialogStage;
    private boolean okClicked = false;

    public void btnEliminar(ActionEvent actionEvent) {
        //Deletes the task, and comeback to home view
        okClicked = true;
        dialogStage.close();

        //ACTIONS... DELETE ... UDPATE THE VIEW


    }

    public void btnAceptar(ActionEvent actionEvent) {
        //Update the task and comeback to homeview
        okClicked = true;
        dialogStage.close();

        //ACTIONS.... UPDATE ... UPDATE THE VIEW

    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public void btnCancelar(ActionEvent actionEvent) {
        //Close the window
        dialogStage.close();
    }
}
