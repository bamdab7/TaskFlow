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
    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoaderHome = new FXMLLoader(TaskFlowApplication.class.getResource("home-view.fxml"));
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(TaskFlowApplication.class.getResource("login-view.fxml"));

        //Creating scenes
            // Scene sceneHome = new Scene(fxmlLoaderHome.load(), 600, 400);
        Scene sceneLogin = new Scene(fxmlLoaderLogin.load(), 600, 400);

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