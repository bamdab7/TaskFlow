package com.taskflow.taskflow;

import com.taskflow.taskflow.dao.CategoriasDAO;
import com.taskflow.taskflow.dao.TareasDAO;
import com.taskflow.taskflow.dao.UsuariosDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class TaskFlowApplication extends Application {

    //Getting the controller
    public static LoginController controladorLogin;
    public static HomeController controladorHome;
    public static RegisterController controladorRegister;
    public static CategoriasController controladorCategorias;

    public static UsuariosDAO users = new UsuariosDAO();
    public static CategoriasDAO categorias = new CategoriasDAO();
    public static TareasDAO tareas = new TareasDAO();

    //Setting scenes
    public static Scene sceneHome;
    public static Scene sceneRegister;
    public static Scene sceneLogin;
    public static Scene sceneCategorias;
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        users.crearDBUsuarios();
        categorias.crearDBCategorias();
        tareas.crearDBTareas();

        //Creating scenes
        FXMLLoader fxmlLoaderHome = new FXMLLoader(TaskFlowApplication.class.getResource("home-view.fxml"));
            Parent rootHome = fxmlLoaderHome.load();
            controladorHome = fxmlLoaderHome.getController();
            sceneHome = new Scene(rootHome,646,394);

        FXMLLoader fxmlLoaderRegister = new FXMLLoader(TaskFlowApplication.class.getResource("register-view.fxml"));
            Parent rootRegister = fxmlLoaderRegister.load();
            controladorRegister = fxmlLoaderRegister.getController();
            sceneRegister = new Scene(rootRegister, 656,400);

        FXMLLoader fxmlLoaderCategorias = new FXMLLoader(TaskFlowApplication.class.getResource("categories-view.fxml"));
            Parent rootCategorias = fxmlLoaderCategorias.load();
            controladorCategorias = fxmlLoaderCategorias.getController();
            sceneCategorias = new Scene(rootCategorias, 646,394);


        FXMLLoader fxmlLoaderLogin = new FXMLLoader(TaskFlowApplication.class.getResource("login-view.fxml"));
           // Parent rootLogin = fxmlLoaderLogin.load(); //Do not use this if this is the first scene lauched
            controladorRegister = fxmlLoaderLogin.getController();
            sceneLogin = new Scene(fxmlLoaderLogin.load(),646,394);

        //Adding icon to application
        stage.getIcons().add(new Image(Objects.requireNonNull(TaskFlowApplication.class.getResourceAsStream("/logo.png"))));

        //Setting the tittle
        stage.setTitle("Task Flow!");

        //Launching scene
            // stage.setScene(sceneHome);
        stage.setScene(sceneLogin);

        //No resize
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}