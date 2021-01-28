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
import java.util.ResourceBundle;

public class DeleteUpdateGarage implements Initializable {

    @FXML private TextField idGarage;

    @FXML
    private TextField nomGarage;

    @FXML
    private TextField adressegarage;

    @FXML
    private TextField mailgarage;

    @FXML
    private Button fermer;

    @FXML
    private TextField telgarage;

    /** fonction qui permet de chercher un garage avec son id**/

    @FXML public void ChercherGar(ActionEvent event){
        String ids = idGarage.getText();
        Garageinfo garageinfo = Dbconnexion.ChercherGar(ids);
        nomGarage.setText(garageinfo.getNomGarage());
        adressegarage.setText(garageinfo.getAdressegarage());
        mailgarage.setText(garageinfo.getMailgarage());
        telgarage.setText(String.valueOf(garageinfo.getTelgarage()));
    }

    /** Fonction qui permet de supprimer un garage grâce à son id **/

    @FXML public void SupprimerGar(){
        String id = idGarage.getText();
         Garageinfo garageinfo = new Garageinfo();
        garageinfo.setIdGarage(id);
        int Etat = Dbconnexion.Delete(id);

        /** Boîte de dialogue de type Alert qui renseigne de l'état de l'opération **/

        if (Etat>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression d'employé !!");
            alert.setHeaderText("Information");
            alert.setContentText("Suppression effectuée");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Suppression effectuée !!");
            alert.setHeaderText("Information");
            alert.setContentText("Echec de la suppression");
            alert.showAndWait();
        }

    }


    /** Fonction qui permet de mettre à jour un garage grâce à son id **/

    @FXML public void UpdateGar(){
        String idgarage = idGarage.getText();
        String nom = nomGarage.getText();
        String adresse = adressegarage.getText();
        String mail = mailgarage.getText();
        int contact = Integer.parseInt(telgarage.getText());//transforme les chaînes de caractères en integer

        Garageinfo garageinfo = new Garageinfo();

        garageinfo.setIdGarage(idgarage);
        garageinfo.setNomGarage(nom);
        garageinfo.setAdressegarage(adresse);
        garageinfo.setMailgarage(mail);

        garageinfo.setTelgarage(contact);

        int status = Dbconnexion.Update(garageinfo);

        /** Boîte de dialogue de type Alert qui renseigne de l'état de l'opération **/
        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour d'un Garage!!");
            alert.setHeaderText("Information");
            alert.setContentText("Mise à jour effectuée ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mise à jour d'un Garage !!");
            alert.setHeaderText("Information");
            alert.setContentText("Echec de la mise à jour");
            alert.showAndWait();
        }
    }

    /** Fonction qui permet de fermer la page DeleteUpdate et ouvrir la page d'affichage d'un garage **/
    @FXML
    public void Annuler (ActionEvent event) throws IOException {
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();

        ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface DeleteUpdate
        Stage stage1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/Acceuil.fxml")); // envoi sur l'interface AffichageEmploye
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Liste des Garages");
        stage1.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
