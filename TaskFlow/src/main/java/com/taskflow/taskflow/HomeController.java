package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.CategoriasDAO;
import com.taskflow.taskflow.dao.TareasDAO;
import com.taskflow.taskflow.dao.UsuariosDAO;
import com.taskflow.taskflow.pojo.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    //Calling classes
    TareasDAO tareasDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareasDAO = new TareasDAO();
        try {
            tareasDAO.getConnection();
        } catch (SQLException e) {
            System.out.printf("No se ha podido establecer la conexion. Error: " + e.getMessage());
        }
        System.out.println("Conexion conseguida");
        try {
            mostrarTareas();
        } catch (SQLException e) {
            System.out.printf("No se pudieron mostrar las tareas. Error: " +e.getMessage());
        }
    }
    //Show the task in the table, put every time you want to refresh the table ( searching or refresing the app)
    public void mostrarTareas() throws SQLException {
        tabladb.setItems(tareasDAO.obtenerListadoTareas());
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


