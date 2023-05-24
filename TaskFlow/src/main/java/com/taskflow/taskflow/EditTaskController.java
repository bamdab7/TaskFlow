package com.taskflow.taskflow;

import com.taskflow.taskflow.dao.TareasDAO;
import com.taskflow.taskflow.pojo.Tareas;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {
    public TextArea txtDescripcion;
    public TextField txtNombre;
    public ComboBox cmbEstado;
    public Button btnEliminar;
    public Button btnCancelar;
    public Button btnAceptar;
    private Stage dialogStage;
    private boolean okClicked = false;
    private Tareas tarea1;
    private TareasDAO tareasDAO;

    public void setTarea(Tareas tarea){
        tarea1 = tarea;
        //Setting the fields
        tarea.getId_tareas();
        txtNombre.setText(tarea.getTitulo());
        txtDescripcion.setText(tarea.getDescripcion());
        cmbEstado.setValue(tarea.getEstado());
        System.out.println(tarea1);
    }
    public void btnEliminar(ActionEvent actionEvent) {
        //Deletes the task, and comeback to home view
        dialogStage= TaskFlowApplication.controladorHome.dialogo;
        okClicked = true;
        dialogStage.close();

        //ACTIONS... DELETE ... UDPATE THE VIEW
        tareasDAO.eliminarTarea(tarea1);
        try {
            TaskFlowApplication.controladorHome.mostrarTareas();
        } catch (SQLException e) {
            System.out.printf("Error al mostrar las tareas.\n Error: " +e.getMessage());
        }
        TaskFlowApplication.controladorHome.busquedaDinamica();
    }

    public void btnAceptar(ActionEvent actionEvent) {
        //Update the task and comeback to homeview
        dialogStage= TaskFlowApplication.controladorHome.dialogo;
        okClicked = true;
        dialogStage.close();

        //ACTIONS.... UPDATE ... UPDATE THE VIEW
        tarea1.setId_tareas(tarea1.getId_tareas());
        tarea1.setTitulo(txtNombre.getText());
        tarea1.setDescripcion(txtDescripcion.getText());
        tarea1.setEstado(cmbEstado.getSelectionModel().getSelectedItem().toString());
        //Update
        tareasDAO.editarTareas(tarea1);
        System.out.println(tarea1);
        //Update the table
        try {
            TaskFlowApplication.controladorHome.mostrarTareas();
        } catch (SQLException e) {
            System.out.printf("Error al mostrar las tareas. \n Error: " + e.getMessage());
        }
        TaskFlowApplication.controladorHome.busquedaDinamica();
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public void btnCancelar(ActionEvent actionEvent) {
        //Close the window
        dialogStage= TaskFlowApplication.controladorHome.dialogo;
        dialogStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareasDAO = new TareasDAO();
    }
}
