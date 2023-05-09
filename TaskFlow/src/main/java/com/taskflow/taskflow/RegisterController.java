package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.UsuariosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {


    public TextField txtUser;
    public TextField txtPassword;
    public TextField txtName;
    public TextField txtEmail;
    public Hyperlink linkRegister;
    public JFXButton btnSignup;

    UsuariosDAO usuariosDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Calling UsuariosDAO in order to add users
        usuariosDAO = new UsuariosDAO();
        try {
            usuariosDAO.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Conexion conseguida con bd usuarios\n");
    }

    public void btnSignup(ActionEvent actionEvent) {
    }

    public void linkRegister(ActionEvent actionEvent) {
        //Scene Login
         Scene sceneLogin = TaskFlowApplication.sceneLogin;
         Stage stageLogin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stageLogin.setScene(sceneLogin);
        stageLogin.show();
    }
}
