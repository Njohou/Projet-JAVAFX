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

public class DeleteUpdateChauff implements Initializable {

    @FXML private TextField IdChauff;
    @FXML private TextField NumChauff;
    @FXML private Button fermer;


    @FXML
        public void ChercherChauff() throws IOException{
            int id = Integer.parseInt(IdChauff.getText());
        ChauffeurInfo chauffInfo = DbconnectionEmpl.Chercherchauff(id);
        NumChauff.setText(chauffInfo.getNumPermis());
    }

    @FXML
        public void SupprimerChauff(){
        int id = Integer.parseInt(IdChauff.getText());
        ChauffeurInfo chauffinfo = new ChauffeurInfo();
        chauffinfo.setIdChauff(id);
        int Etat = DbconnectionEmpl.Deletechauff(id);

        /** Boîte de dialogue de type Alert qui renseigne de l'état de l'opération **/

        if (Etat>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression du conducteur !!");
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

    @FXML
        public void UpdateChauff(){
        int id = Integer.parseInt(IdChauff.getText());
        String num = NumChauff.getText();

        ChauffeurInfo chauffinfo = new ChauffeurInfo();
        chauffinfo.setIdChauff(id);
        chauffinfo.setNumPermis(num);

        int Etat = DbconnectionEmpl.Updatechauff(chauffinfo);

        /** Boîte de dialogue de type Alert qui renseigne de l'état de l'opération **/
        if (Etat>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mise à jour du chauffeur !!");
            alert.setHeaderText("Information");
            alert.setContentText("Mise à jour effectuée ");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Mise à jour du chauffeur !!");
            alert.setHeaderText("Information");
            alert.setContentText("Echec de la mise à jour");
            alert.showAndWait();
        }

    }

    @FXML
        public void Annuler(ActionEvent event) throws IOException{
        Stage stage = (Stage) fermer.getScene().getWindow();
        stage.close();

        ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface DeleteUpdate
        Stage stage1 = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/AffichageChauffeur.fxml")); // envoi sur l'interface AffichageEmploye
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage1.setScene(scene);
        stage1.setTitle("Liste des chauffeurs");
        stage1.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
