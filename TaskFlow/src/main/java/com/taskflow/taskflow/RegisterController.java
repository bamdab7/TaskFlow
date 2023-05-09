package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {


    public TextField txtUser;
    public TextField txtPassword;
    public TextField txtName;
    public TextField txtEmail;
    public Hyperlink linkRegister;
    public JFXButton btnSignup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btnSignup(ActionEvent actionEvent) {
    }

    public void linkRegister(ActionEvent actionEvent) {
    }
}
