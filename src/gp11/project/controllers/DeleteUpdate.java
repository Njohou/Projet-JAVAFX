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
import java.util.ResourceBundle;

public class DeleteUpdate implements Initializable {

    @FXML private TextField IdEmpl;
    @FXML private TextField NomEmpl;
    @FXML private TextField PrenomEmpl;
    @FXML private TextField PasswordEmpl;
    @FXML private  TextField MailEmpl;
    @FXML private TextField TelEmpl;

    ObservableList<String> sexe = FXCollections.observableArrayList("M","F","A");
    ObservableList<String> serviceTrav = FXCollections.observableArrayList("Service Logistique", "Comptabilité", "Service Juridique", "Service Financier", "Ressource Humaine");
    @FXML private ComboBox<String> Sexe;
    @FXML private ComboBox<String> ServiceTrav;
    @FXML private Button fermer;

    /** fonction qui permet de chercher un employé avec son id**/

    @FXML public void ChercherEmpl(ActionEvent event){
        String ids = IdEmpl.getText();
        int id = Integer.parseInt(ids);
        EmployeInfo emplinfo = DbconnectionEmpl.ChercherEmpl(id);
        NomEmpl.setText(emplinfo.getNomEmpl());
        PrenomEmpl.setText(emplinfo.getPrenomEmpl());
        Sexe.setValue(emplinfo.getSexe());
        MailEmpl.setText(emplinfo.getMailEmpl());
        PasswordEmpl.setText(emplinfo.getPasswordEmpl());
        TelEmpl.setText(String.valueOf(emplinfo.getTelEmpl()));
        ServiceTrav.setValue(emplinfo.getServiceTrav());
    }

    /** Fonction qui permet de supprimer un employé grâce à son id **/

    @FXML public void SupprimerEmpl(){
        int id = Integer.parseInt(IdEmpl.getText());
        EmployeInfo emplInfo = new EmployeInfo();
        emplInfo.setIdEmpl(id);
        int Etat = DbconnectionEmpl.Delete(id);

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


    /** Fonction qui permet de mettre à jour un employé grâce à son id **/

    @FXML public void UpdateEmpl(){
        int id = Integer.parseInt(IdEmpl.getText()); //transforme les chaînes de caractères en integer
        String nom = NomEmpl.getText();
        String prenom = PrenomEmpl.getText();
        String sexe = Sexe.getValue();
        String password = PasswordEmpl.getText();
        String service = ServiceTrav.getValue();
        String mail = MailEmpl.getText();
        int telephone = Integer.parseInt(TelEmpl.getText());


        EmployeInfo emplInfo = new EmployeInfo();

        emplInfo.setIdEmpl(id);
        emplInfo.setNomEmpl(nom);
        emplInfo.setPrenomEmpl(prenom);
        emplInfo.setSexe(sexe);
        emplInfo.setMailEmpl(mail);
        emplInfo.setPasswordEmpl(password);
        emplInfo.setTelEmpl(telephone);
        emplInfo.setServiceTrav(service);

        int status = DbconnectionEmpl.Update(emplInfo);

        /** Boîte de dialogue de type Alert qui renseigne de l'état de l'opération **/
        if (status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour d'employé !!");
            alert.setHeaderText("Information");
            alert.setContentText("Mise à jour effectuée ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mise à jour d'employé !!");
            alert.setHeaderText("Information");
            alert.setContentText("Echec de la mise à jour");
            alert.showAndWait();
        }
    }

    /** Fonction qui permet de fermer la page DeleteUpdate et ouvrir la page d'aafichage d'employé **/
    @FXML
        public void Annuler (ActionEvent event) throws IOException{
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();

        ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface DeleteUpdate
        Stage stage1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageEmploye.fxml")); // envoi sur l'interface AffichageEmploye
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Liste des employés");
        stage1.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Sexe.setItems(sexe);

            ServiceTrav.setItems(serviceTrav);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
