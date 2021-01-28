package gp11.project.controllers;

import gp11.project.connexion.DbconnectionEmpl;
import gp11.project.model.EmployeInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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


public class Employe implements Initializable {

    @FXML private TextField NomEmpl;
    @FXML private TextField PrenomEmpl;
    @FXML private PasswordField PasswordEmpl;
    @FXML private TextField MailEmpl;
    @FXML private TextField TelEmpl;


    @FXML private Button Fermer;

    /** Création des listes pour le sexes et les services de travail **/
    ObservableList<String> choiceSexe = FXCollections.observableArrayList("M","F","A");
    ObservableList<String> choiceServ = FXCollections.observableArrayList("Service Logistique", "Comptabilité", "Service Juridique", "Service Financier", "Ressource Humaine");
    @FXML private ComboBox<String> ChoiceSexe;
    @FXML private ComboBox<String> ChoiceServ;

    /** Création de la fonction pour ajouter un nouvel employé **/

    @FXML public void CreateEmploye(ActionEvent event) throws SQLException, IOException {
        String nomEmpl = NomEmpl.getText();
        String prenomEmpl = PrenomEmpl.getText();
        String passwordEmpl = PasswordEmpl.getText();
        String mailEmpl = MailEmpl.getText();
        int telEmpl = Integer.parseInt(TelEmpl.getText());


        /** Création d'une instance de la classe et l'envoi des données **/

        EmployeInfo emplinfo = new EmployeInfo();
        emplinfo.setNomEmpl(nomEmpl);
        emplinfo.setPrenomEmpl(prenomEmpl);
        emplinfo.setSexe(ChoiceSexe.getValue());
        emplinfo.setMailEmpl(mailEmpl);
        emplinfo.setPasswordEmpl(passwordEmpl);
        emplinfo.setTelEmpl(telEmpl);
        emplinfo.setServiceTrav(ChoiceServ.getValue());

        int status = DbconnectionEmpl.Enregistrer(emplinfo);


        /** En cas d'insertion ou de non insertion nous avons des messages ci-dessous **/

        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout d'employé !!");
            alert.setHeaderText("Information");
            alert.setContentText("l'Employé a été bien ajouté ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajout d'employé !!");
            alert.setHeaderText("Information");
            alert.setContentText("l'Employé n' a pas été ajouté ");
            alert.showAndWait();
        }

        /** permet d'afficher la page AffichageEmploye actualisée **/
        ((Node)event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageEmploye.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Accueil");
        stage.show();

    }

    /** fonction qui ferme la fenêtre de l' ajout d'employé **/
   @FXML public void Annuler(ActionEvent event) throws IOException {
        Stage stage = (Stage) Fermer.getScene().getWindow();
        stage.close();

        Stage stage1 = new Stage();
       ((Node)event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageEmploye.fxml"));
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Liste des employés");
        stage1.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            //pour les listes de service
            ChoiceServ.setItems(choiceServ);
            ChoiceServ.setValue("Ressource Humaine");

            //pour les listes de sexe
            ChoiceSexe.setItems(choiceSexe);
            ChoiceSexe.setValue("M");
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}
