package com.taskflow.taskflow;

import com.jfoenix.controls.JFXButton;
import com.taskflow.taskflow.dao.UsuariosDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    public Text verification;

    UsuariosDAO usuariosDAO;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Calling UsuariosDAO in order to get the users loged
        usuariosDAO = new UsuariosDAO();
        try {
            usuariosDAO.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Conexion conseguida con bd usuarios\n");
        verification.setText(null);
    }

    public void btnLogin(ActionEvent actionEvent) throws IOException, SQLException {
        //Check credentials
        String username = txtUser.getText();
        String password = txtPassword.getText();
        if(usuariosDAO.comprobarUsuario(username,password)){
            System.out.println("Usuario encontrado\n");
            //Scene Home
            Scene sceneHome = TaskFlowApplication.sceneHome;
            Stage stageHome = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stageHome.setScene(sceneHome);
            stageHome.show();
            System.out.println(username + password);
        }else{
            eliminarCampos();
            verification.setText("Usuario no encontrado");
            verification.setStyle("-fx-font-size: 16px;");
            verification.setFill(Color.RED);
        }
    }

    public void linkRegister(ActionEvent actionEvent) {
        //Scene Register
        Scene sceneRegister = TaskFlowApplication.sceneRegister;
        Stage stageRegister = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stageRegister.setScene(sceneRegister);
        stageRegister.show();
    }

    //Set all fields null
    public void eliminarCampos(){
        txtUser.clear();
        txtPassword.clear();
    }
}
