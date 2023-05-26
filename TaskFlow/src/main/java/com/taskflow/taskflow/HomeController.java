package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.CategoriasDAO;
import com.taskflow.taskflow.dao.TareasDAO;
import com.taskflow.taskflow.pojo.Tareas;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TableRow;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
    public TextField txtBuscar;
    TareasDAO tareasDAO;
    ObservableList<Tareas> listadoTareas;

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


             // Configurar Tooltip en las filas del TableView
        tabladb.setRowFactory(tv -> {
            TableRow<Tareas> row = new TableRow<>();

            Tooltip tooltip = new Tooltip();
            StringProperty descripcionProperty = new SimpleStringProperty();

            tooltip.textProperty().bind(descripcionProperty);

            row.setOnMouseEntered(event -> {
                if (!row.isEmpty()) {
                    descripcionProperty.set(row.getItem().getDescripcion());
                    tooltip.show(row, event.getScreenX(), event.getScreenY() + 10);
                }
            });

            row.setOnMouseExited(event -> {
                tooltip.hide();
            });
            return row;
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
        listadoTareas = tareasDAO.obtenerListadoTareas(LoginController.user.getId_usuario());
        tabladb.setItems(listadoTareas);
    }

    public void mostrarTareasCondicion(ObservableList<Tareas> lista) throws SQLException {
        tabladb.setItems(lista);
    }

//    public void btnBuscar(ActionEvent actionEvent) {
//        //Search by categoria or name maybe
//       String nombre =  txtBuscar.getText();
//       if(tareasDAO.findTaskByName(nombre,LoginController.user.getId_usuario()).isEmpty()){
//            tabladb.setItems(null);
//        }else {
//            try {
//                mostrarTareasCondicion(tareasDAO.findTaskByName(nombre,LoginController.user.getId_usuario()));
//            } catch (SQLException e) {
//                System.out.printf("Error al mostrar las tareas por nombre: \n Error: "+ e.getMessage());
//            }
//        }
//    }
    public void busquedaDinamica(){
        FilteredList<Tareas> listadoTareasBusqueda = new FilteredList<>(listadoTareas, e -> true);
        txtBuscar.setOnKeyReleased( e ->{
            txtBuscar.textProperty().addListener((observableValue, oldValue, newValue)->{
                listadoTareasBusqueda.setPredicate((Predicate<? super Tareas>) tareas->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }else if(tareas.getTitulo().toLowerCase().contains(newValue.toLowerCase())){
                        return  true;
                    }
                    return false;
                });
        });
            SortedList<Tareas> sortedList = new SortedList<>(listadoTareasBusqueda);
            sortedList.comparatorProperty().bind(tabladb.comparatorProperty());
            tabladb.setItems(sortedList);
        });
    }



    public void btnCategorias(ActionEvent actionEvent) {
        //Opens a new scene that contains the categories
        Scene sceneCategorias = TaskFlowApplication.sceneCategorias;
        Stage stageCategorias = (Stage) ( (Node) actionEvent.getSource()).getScene().getWindow();

        stageCategorias.setScene(sceneCategorias);
        stageCategorias.show();

        //Call the controller and the metod that shows the categories
        TaskFlowApplication.controladorCategorias.mostrarCategorias();
    }

    public void btnPendiente(ActionEvent actionEvent) {
        //Search by estado = pendiente
        if(tareasDAO.findTaskByStatus("Pendiente",LoginController.user.getId_usuario()).isEmpty()){
            tabladb.setItems(null);
        }else {
            try {
                mostrarTareasCondicion(tareasDAO.findTaskByStatus("Pendiente",LoginController.user.getId_usuario()));
            } catch (SQLException e) {
                System.out.printf("Error al mostrar las tareas por estado: \n Error: "+ e.getMessage());
            }
        }
    }

    public void btnProgreso(ActionEvent actionEvent) {
        //Search by estado = progreso
        if(tareasDAO.findTaskByStatus("Progreso",LoginController.user.getId_usuario()).isEmpty()){
            tabladb.setItems(null);
        }else {
            try {
                mostrarTareasCondicion(tareasDAO.findTaskByStatus("Progreso",LoginController.user.getId_usuario()));
            } catch (SQLException e) {
                System.out.printf("Error al mostrar las tareas por estado: \n Error: "+ e.getMessage());
            }
        }
    }

    public void btnTerminado(ActionEvent actionEvent) {
        //Search by estado = terminado
        if(tareasDAO.findTaskByStatus("Terminado",LoginController.user.getId_usuario()).isEmpty()){
            tabladb.setItems(null);
        }else {
            try {
                mostrarTareasCondicion(tareasDAO.findTaskByStatus("Terminado",LoginController.user.getId_usuario()));
            } catch (SQLException e) {
                System.out.printf("Error al mostrar las tareas por estado: \n Error: "+ e.getMessage());
            }
        }
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
          busquedaDinamica();
    }

    public void btnHome(ActionEvent actionEvent) {
        try {
            mostrarTareas();
        } catch (SQLException e) {
            System.out.printf("Error al mostrar tareas. \n Error: " + e.getMessage());
        }
    }

    public void btnClear(ActionEvent actionEvent) {
        txtBuscar.clear();
    }
}


