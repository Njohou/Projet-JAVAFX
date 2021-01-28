package gp11.project.controllers;


import gp11.project.model.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {
    /** appel des pages créer un employé et véhicule dans la page principale **/

    @FXML
        public void TransEmploye() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageEmploye.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des employés");
        stage.show();

    }


    /** fonction qui permet d'afficher l'interface de véhicule **/
    @FXML
    public void TransChauffeur(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageChauffeur.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouveau chauffeur");
        stage.show();
    }

    @FXML
        public void TransGarage() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/Acceuil.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouveau chauffeur");
        stage.show();
    }

    @FXML
        public void TransReparation() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/HomeReparation.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Créer nouveau chauffeur");
        stage.show();
    }

    /** la fonction pour quitter l'appli **/
    @FXML
        public void quitter(javafx.event.ActionEvent actionEvent) {
            System.exit(0);
        }

     /** fonction qui affiche l'interface Apropos**/
    @FXML
        public void Apropos(javafx.event.ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("A propos de ");
        alert.setHeaderText(null);
        alert.setContentText("Cette application est faite pour la gestion d'un parc automobile");
        alert.showAndWait();
    }

    @FXML
        public void Deconnexion(ActionEvent event){
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            Main main = new Main();
            Stage PrimaryStage = new Stage();
            main.start(PrimaryStage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
