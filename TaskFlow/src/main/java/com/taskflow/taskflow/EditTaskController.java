package com.taskflow.taskflow;

import com.taskflow.taskflow.pojo.Tareas;
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
    private Tareas tarea;

    public void setTarea(Tareas tarea){
        this.tarea = tarea;
        //Setting the fields
        txtNombre.setText(tarea.getTitulo());
        txtDescripcion.setText(tarea.getDescripcion());
        cmbEstado.setValue(tarea.getEstado());
    }
    public void btnEliminar(ActionEvent actionEvent) {
        //Deletes the task, and comeback to home view
        dialogStage= TaskFlowApplication.controladorHome.dialogo;
        okClicked = true;
        dialogStage.close();

        //ACTIONS... DELETE ... UDPATE THE VIEW
    }

    public void btnAceptar(ActionEvent actionEvent) {
        //Update the task and comeback to homeview
        dialogStage= TaskFlowApplication.controladorHome.dialogo;
        okClicked = true;
        dialogStage.close();

        //ACTIONS.... UPDATE ... UPDATE THE VIEW


    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public void btnCancelar(ActionEvent actionEvent) {
        //Close the window
        dialogStage= TaskFlowApplication.controladorHome.dialogo;
        System.out.println("bnt cancelar prueba");
        dialogStage.close();
    }
}
