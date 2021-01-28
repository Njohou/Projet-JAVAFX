package gp11.project.controllers;

import gp11.project.connexion.DbconnectionEmpl;
import gp11.project.model.ChauffeurInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AffichageChauffeur implements Initializable {

    @FXML private TableView<ChauffeurInfo> tableChauff;
    @FXML private TableColumn<ChauffeurInfo, Integer> IdChauff;
    @FXML private TableColumn<ChauffeurInfo, String> NumPermis;
    @FXML private Button retour;

    public ObservableList<ChauffeurInfo> ListChauff = FXCollections.observableArrayList();

    public void AffichageChauff(){
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;
        try{
            connection = DbconnectionEmpl.connect();
            String sql = "SELECT * FROM chauffeur";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ListChauff.clear();
            while (rs.next()){
                ListChauff.add(new ChauffeurInfo(rs.getInt(1),rs.getString(2)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        IdChauff.setCellValueFactory(new PropertyValueFactory<ChauffeurInfo, Integer>("IdChauff"));
        NumPermis.setCellValueFactory(new PropertyValueFactory<ChauffeurInfo, String>("NumPermis"));

        tableChauff.setItems(ListChauff);
    }

@FXML
    public void InsertionChauff(ActionEvent event){
    try {
        Stage stage = new Stage();
        ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface d'affichageEmploye
        stage.initStyle(StageStyle.UNDECORATED); // permet d'enlever la barre de titre
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/chauffeur.fxml")); // envoi sur l'interface de création d'employé
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Insérer un chauffeur");
        stage.show();
    }catch (Exception e){
        e.printStackTrace();
    }
}

@FXML
    public void DeleteUpdateChauf(ActionEvent event){
    try{
        Stage stage = new Stage();
        ((Node)event.getSource()).getScene().getWindow().hide();
        stage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/DeleteUpdateChauff.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Modifier ou suppression");
        stage.show();
    }catch (Exception e){
        e.printStackTrace();
    }
}

@FXML
    public void Retour(){
    Stage stage = (Stage) retour.getScene().getWindow();
    stage.close();
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AffichageChauff();
    }
}
