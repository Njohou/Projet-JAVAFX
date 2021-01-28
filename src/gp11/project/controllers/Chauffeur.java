package gp11.project.controllers;

import gp11.project.connexion.DbconnectionEmpl;
import gp11.project.model.ChauffeurInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Chauffeur implements Initializable {

    @FXML private TextField IdChauff;
    @FXML private TextField NumPer;
    @FXML private Button Fermer;

    @FXML
        public void CreateDriver(ActionEvent event) throws IOException {
        int id = Integer.parseInt(IdChauff.getText());
        String num = NumPer.getText();

        ChauffeurInfo chauffinfo = new ChauffeurInfo();

        chauffinfo.setIdChauff(id);
        chauffinfo.setNumPermis(num);
        int etat = DbconnectionEmpl.EnregistrerChauff(chauffinfo);

        if (etat>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout de chauffeur !!");
            alert.setHeaderText("Information");
            alert.setContentText("le chauffeur a été bien ajouté ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout de chauffeur !!");
            alert.setHeaderText("Information");
            alert.setContentText("le chauffeur n' a pas été ajouté ");
            alert.showAndWait();
        }

        /** permet d'afficher la page AffichageChauffeur actualisée **/
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageChauffeur.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Liste des chauffeurs");
        stage.show();

    }

    @FXML
        public void Annuler(ActionEvent event) throws IOException{
        Stage stage = (Stage) Fermer.getScene().getWindow();
        stage.close();

        Stage stage1 = new Stage();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageChauffeur.fxml"));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Liste des chauffeurs");
        stage1.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
