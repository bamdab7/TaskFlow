package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.UsuariosDAO;
import com.taskflow.taskflow.pojo.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Calling UsuariosDAO in order to add users
        usuariosDAO = new UsuariosDAO();
        usuariosDAO.getConnection();
    }

    public void btnSignup(ActionEvent actionEvent) {
        //Collect all data from textfield and insert into database and then redirect to Login
        Usuarios user = new Usuarios();
        //In order to validate the email, get text from form
        String email = txtEmail.getText();
        if (txtUser.getText().isEmpty()||txtPassword.getText().isEmpty()||txtEmail.getText().isEmpty()||txtName.getText().isEmpty()) {
            mostrarAlerta("Error", "Debe completar todos los campos");
        } else if (!email.contains("@")) {
            mostrarAlerta("Error","Debe introducir un correo valido");
        } else {
            //Insert
            user.setId_usuario(id);
            user.setNombre(txtName.getText());
            user.setUsername(txtUser.getText());
            user.setPassword(txtPassword.getText());
            user.setEmail(txtEmail.getText());

            usuariosDAO.insertUsuario(user);
            id = 0;

            eliminarCampos();

            //Scene Login
             Scene sceneLogin = TaskFlowApplication.sceneLogin;
             Stage stageLogin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stageLogin.setScene(sceneLogin);
            stageLogin.show();
        }
    }

    private void mostrarAlerta(String titulo,String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

public void linkRegister(ActionEvent actionEvent) {
        //Scene Login
         Scene sceneLogin = TaskFlowApplication.sceneLogin;
         Stage stageLogin = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stageLogin.setScene(sceneLogin);
        stageLogin.show();

        eliminarCampos();
    }

    public void eliminarCampos(){
        txtName.clear();
        txtEmail.clear();
        txtPassword.clear();
        txtUser.clear();
    }
}
