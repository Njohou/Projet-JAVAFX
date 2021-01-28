package gp11.project.controllers;


import gp11.project.connexion.DbconnectionEmpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login implements Initializable {

    @FXML private TextField NomLogin;
    @FXML private TextField PrenomLogin;
    @FXML private PasswordField PasswordLogin;


    @FXML public void Login(ActionEvent event) throws SQLException {
        Connection connection = DbconnectionEmpl.connect();
        PreparedStatement stat = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM employe WHERE nomEmpl = ? AND prenomEmpl = ? AND motdepasse = ?";

        try{
            stat = connection.prepareStatement(sql);
            stat.setString(1,NomLogin.getText().toString());
            stat.setString(2,PrenomLogin.getText().toString());
            stat.setString(3,PasswordLogin.getText().toString());

            rs = stat.executeQuery();

            if (rs.next()){

                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/Accueil.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Accueil");
                stage.show();

            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Erreur de connexion");
                alert.setTitle("ERREUR");
                alert.setContentText("Votre nom ou prénom ou mot de passe est incorrect.");
                alert.showAndWait();

                NomLogin.clear();
                PrenomLogin.clear();
                PasswordLogin.clear();

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
