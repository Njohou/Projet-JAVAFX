package gp11.project.controllers;

import gp11.project.connexion.Dbconnexion;
import gp11.project.model.Garageinfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Garage implements Initializable {


    @FXML
    private TextArea idGarage;

    @FXML
    private TextArea nomGarage;

    @FXML
    private TextArea adressegarage;

    @FXML
    private TextArea mailgarage;

    @FXML
    private TextField telgarage;

    @FXML
    private Button fermer;




    /**
     * Création de la fonction pour ajouter un nuveau garage
     **/

    @FXML
    public void CreateGarage(ActionEvent event) throws SQLException, IOException {
        String id = idGarage.getText();
        String nom = nomGarage.getText();
        String adresse = adressegarage.getText();
        String mail = mailgarage.getText();
        int contact = Integer.parseInt(telgarage.getText());
        /** Création d'une instance de la classe et l'envoi des données **/

        Garageinfo garageinfo = new Garageinfo();
        garageinfo.setIdGarage(id);
        garageinfo.setNomGarage(nom);
        garageinfo.setAdressegarage(adresse);
        garageinfo.setMailgarage(mail);
        garageinfo.setTelgarage(contact);


        int status = Dbconnexion.Enregistrer(garageinfo);


        /** En cas d'insertion ou de non insertion nous avons des messages ci-dessous **/

        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout du garage !!");
            alert.setHeaderText("Information");
            alert.setContentText("le garage a été bien ajouté ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout d'un garage !!");
            alert.setHeaderText("Information");
            alert.setContentText("le garage n' a pas été ajouté ");
            alert.showAndWait();
        }

        /** permet d'afficher la page AffichageGarage actualisée **/
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/Acceuil.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();


    }
    /** fonction qui ferme la fenêtre de l'ajout d'un garage */
    @FXML public void Annuler(ActionEvent event) throws IOException {
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();

        Stage stage1 = new Stage();
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/Acceuil.fxml"));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Liste des Garages");
        stage1.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
