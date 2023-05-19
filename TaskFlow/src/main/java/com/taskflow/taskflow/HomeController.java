package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.CategoriasDAO;
import com.taskflow.taskflow.dao.TareasDAO;
import com.taskflow.taskflow.dao.UsuariosDAO;
import com.taskflow.taskflow.pojo.Tareas;
import com.taskflow.taskflow.pojo.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
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
    public Text nombreUsuario;
    public Stage dialogo;
    TareasDAO tareasDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Creating the tooltips
        Tooltip tooltipHome = new Tooltip("Estas en home!!");
        tooltipHome.setShowDuration(Duration.millis(500));
        Tooltip.install(btnHome, tooltipHome);

        Tooltip tooltipCat = new Tooltip("Listado de categorias");
        tooltipCat.setShowDuration(Duration.millis(500));
        Tooltip.install(btnCategorias, tooltipCat);

        Tooltip tooltipEstado1 = new Tooltip("Buscar por estado: PENDIENTE");
        tooltipEstado1.setShowDuration(Duration.millis(500));
        Tooltip.install(btnPendiente, tooltipEstado1);
        Tooltip tooltipEstado2 = new Tooltip("Buscar por estado: TERMINADO");
        tooltipEstado2.setShowDuration(Duration.millis(500));
        Tooltip.install(btnTerminado, tooltipEstado2);
        Tooltip tooltipEstado3 = new Tooltip("Buscar por estado: PROGRESO");
        tooltipEstado3.setShowDuration(Duration.millis(500));
        Tooltip.install(btnProgreso, tooltipEstado3);

        Tooltip tooltipAdd = new Tooltip("Añade nueva tarea!");
        tooltipAdd.setShowDuration(Duration.millis(2000));
        Tooltip.install(btnAdd, tooltipAdd);

        tareasDAO = new TareasDAO();
        try {
            tareasDAO.getConnection();
        } catch (SQLException e) {
            System.out.printf("No se ha podido establecer la conexion. Error: " + e.getMessage());
        }
        System.out.println("Conexion conseguida");

        //If we click twice in the table, opens a dialog that show us the details of the task selectet
        tabladb.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                Tareas tareaSeleccionada = (Tareas) tabladb.getSelectionModel().getSelectedItem();
                //Opens the dialogo in order to edit the selected task
                abrirDialogoEditar(tareaSeleccionada);
            }
        });
    }

    private void abrirDialogoEditar(Tareas tareaSeleccionada) {
        try {
            //Opens a window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit-task-view.fxml"));
            Parent root = loader.load();

            //Getting the controler
            EditTaskController editController = loader.getController();
            editController.setTarea(tareaSeleccionada);

            //Opens the dialog and sets the thing
            dialogo = new Stage();
            dialogo.initModality(Modality.APPLICATION_MODAL);
            dialogo.setTitle("Editar Tarea");
            dialogo.setScene(new Scene(root));

            // Shows the dialog and waits to close
            dialogo.showAndWait();
        }catch (Exception e){
            System.out.printf("Error al abrir el dialogo. \n Error: " + e.getMessage());
        }
    }

    //Show the task in the table, put every time you want to refresh the table ( searching or refresing the app)
    public void mostrarTareas() throws SQLException {
        tabladb.setItems(tareasDAO.obtenerListadoTareas(LoginController.user.getId_usuario()));
    }

    public void btnBuscar(ActionEvent actionEvent) {
        //Search by categoria or name maybe
    }


    public void btnCategorias(ActionEvent actionEvent) {
        //Show list of categorias(?
    }

    public void btnPendiente(ActionEvent actionEvent) {
        //Search by estado = pendiente
    }

    public void btnProgreso(ActionEvent actionEvent) {
        //Search by estado = progreso
    }

    public void btnTerminado(ActionEvent actionEvent) {
        //Search by estado = terminado
    }

    public void btnAdd(ActionEvent actionEvent) {
        //Opens a window, that contais form
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("form-task-view.fxml"));
            Parent root = loader.load();
            //Calling the controller of the dialog
            FormTaskController dialogController = loader.getController();

            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(btnAdd.getScene().getWindow());
            dialogStage.setScene(new Scene(root));
            dialogStage.setTitle("Añadir formulario");

            //Setting the categories in the combobox
            if(CategoriasDAO.getAllCategories().isEmpty()){
                dialogController.cmbCategoria.setDisable(true);
                dialogController.cmbCategoria.setPromptText("No hay categorias");
            }else{
               // System.out.println(CategoriasDAO.getAllCategories());
                dialogController.cmbCategoria.setItems(CategoriasDAO.getAllCategories());
            }

            dialogController.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
              System.out.println("Error: " + e.getMessage());
        }
    }
}


