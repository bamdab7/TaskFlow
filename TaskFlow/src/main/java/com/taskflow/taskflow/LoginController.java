package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public TextField txtUser;
    public TextField txtPassword;
    public JFXButton btnLogin;
    public Hyperlink linkRegister;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnLogin(ActionEvent actionEvent) {
        //Scene Home
    }

    public void linkRegister(ActionEvent actionEvent) {
        //Scene Registwer
    }
}
