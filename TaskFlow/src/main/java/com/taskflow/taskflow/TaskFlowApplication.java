package com.taskflow.taskflow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TaskFlowApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TaskFlowApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        //Adding icon to application
        stage.getIcons().add(new Image(Objects.requireNonNull(TaskFlowApplication.class.getResourceAsStream("/logo.png"))));
        stage.setTitle("Task Flow!");
        stage.setScene(scene);
        //No resize
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}