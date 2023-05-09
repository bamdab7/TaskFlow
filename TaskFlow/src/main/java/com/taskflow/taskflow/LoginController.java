package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField txtUser;
    public TextField txtPassword;
    public JFXButton btnLogin;
    public Hyperlink linkRegister;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnLogin(ActionEvent actionEvent) throws IOException, SQLException {
        //Scene Home
           // Parent fxmlLoaderHome = FXMLLoader.load(TaskFlowApplication.class.getResource("home-view.fxml"));
        Scene sceneHome = TaskFlowApplication.sceneHome;
        Stage stageHome = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stageHome.setScene(sceneHome);
        stageHome.show();

    }

    public void linkRegister(ActionEvent actionEvent) {
        //Scene Registwer
    }
}
