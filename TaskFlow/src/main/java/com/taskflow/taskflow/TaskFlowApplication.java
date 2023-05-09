package com.taskflow.taskflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TaskFlowApplication extends Application {

    //Getting the controller
    public static LoginController controladorLogin;
    public static HomeController controladorHome;
    public static RegisterController controladorRegister;

    //Setting scenes
    public static Scene sceneHome;
    public static Scene sceneRegister;
    public static Scene sceneLogin;
    @Override
    public void start(Stage stage) throws IOException {
        //Creating scenes
        FXMLLoader fxmlLoaderHome = new FXMLLoader(TaskFlowApplication.class.getResource("home-view.fxml"));
            Parent rootHome = fxmlLoaderHome.load();
            controladorHome = fxmlLoaderHome.getController();
            sceneHome = new Scene(rootHome,646,394);

        FXMLLoader fxmlLoaderRegister = new FXMLLoader(TaskFlowApplication.class.getResource("register-view.fxml"));
            Parent rootRegister = fxmlLoaderRegister.load();
            controladorRegister = fxmlLoaderRegister.getController();
            sceneRegister = new Scene(rootRegister, 656,400);

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