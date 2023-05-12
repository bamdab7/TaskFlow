package com.taskflow.taskflow;

import com.taskflow.taskflow.dao.CategoriasDAO;
import com.taskflow.taskflow.pojo.Categorias;
import com.taskflow.taskflow.pojo.Tareas;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FormTaskController {

    public ComboBox cmbCategoria;
    public TextArea txtDescripcion;
    public TextField txtNombre;
    public ComboBox cmbEstado;
    public Button btnCancelar;
    public Button btnAceptar;
    private Stage dialogStage;
    private boolean okClicked = false;

    private int id;

    public void btnCancelar(ActionEvent actionEvent) {
         dialogStage.close();
    }

    public void btnAceptar(ActionEvent actionEvent) {
         // Action when u click on Acept
        okClicked = true;
        dialogStage.close();

        //ACTIONS
        Tareas tarea = new Tareas();

        // Validate all completed
//        if (txtNombre.getText().isEmpty()||txtDescripcion.getText().isEmpty()||cmbEstado.getSelectionModel().isEmpty()||cmbCategoria.getSelectionModel().isEmpty()){
//            mostrarAlerta("Error", "Debe completar la info sobre la tarea");
//        }else {
            //Insert into database
            tarea.setId_tareas(id);
            tarea.setTitulo(txtNombre.getText());

            String categoria =  cmbCategoria.getSelectionModel().getSelectedItem().toString();
            int id_categoria=CategoriasDAO.getIdCategoria(categoria);
            System.out.println(id_categoria);
            id=0;
            eliminarCampos();
        }
   // }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private void mostrarAlerta(String titulo,String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    public void eliminarCampos(){
        id=0;
        txtNombre.clear();
        txtDescripcion.clear();
        cmbCategoria.setValue(null);
        cmbEstado.setValue(null);
    }
}
