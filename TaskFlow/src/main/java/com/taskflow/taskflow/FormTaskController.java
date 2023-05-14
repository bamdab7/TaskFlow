package com.taskflow.taskflow;

import com.taskflow.taskflow.dao.CategoriasDAO;
import com.taskflow.taskflow.dao.TareasDAO;
import com.taskflow.taskflow.dao.UsuariosDAO;
import com.taskflow.taskflow.pojo.Tareas;
import com.taskflow.taskflow.pojo.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FormTaskController implements Initializable {

    public ComboBox cmbCategoria;
    public TextArea txtDescripcion;
    public TextField txtNombre;
    public ComboBox cmbEstado;
    public Button btnCancelar;
    public Button btnAceptar;
    private Stage dialogStage;
    private TareasDAO tareasDAO;
    private boolean okClicked = false;
    public static Usuarios user;
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
        if (txtNombre.getText().isEmpty()||txtDescripcion.getText().isEmpty()||cmbEstado.getSelectionModel().isEmpty()||cmbCategoria.getSelectionModel().isEmpty()){
            mostrarAlerta("Error", "Debe completar la info sobre la tarea");
        }else {
            //GETTING THE INFO
            tarea.setId_tareas(0);
            tarea.setTitulo(txtNombre.getText());
            tarea.setDescripcion(txtDescripcion.getText());

            String categoria =  cmbCategoria.getSelectionModel().getSelectedItem().toString();
            int id_categoria=CategoriasDAO.getIdCategoria(categoria);
            tarea.setId_categoria(id_categoria);

            tarea.setEstado(cmbEstado.getSelectionModel().getSelectedItem().toString());

            tarea.setId_usuarios(LoginController.user.getId_usuario());

            //INSERT
            tareasDAO.insertarTareas(tarea);
            id= 0;
            System.out.println(tarea);
            eliminarCampos();
            //Updating the window
            try {
                 TaskFlowApplication.controladorHome.mostrarTareas();
            } catch (SQLException e) {
                System.out.printf("Error al mostrar las tareas.\n Error: " + e.getMessage());
            }
        }
    }

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
        txtNombre.clear();
        txtDescripcion.clear();
        cmbCategoria.setValue(null);
        cmbEstado.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareasDAO = new TareasDAO();
        homeController = new HomeController();
    }
}
