package gp11.project.controllers;

import gp11.project.connexion.Dbconnexion;
import gp11.project.model.Garageinfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AffichageGarage implements Initializable {
    @FXML
    private TableView<Garageinfo> table;

    @FXML
    private TableColumn<Garageinfo, String> idGarage;

    @FXML
    private TableColumn<Garageinfo, String> nomGarage;

    @FXML
    private TableColumn<Garageinfo, String> adressegarage;

    @FXML
    private TableColumn<Garageinfo, Integer> telgarage;

    @FXML
    private TableColumn<Garageinfo, String> mailgarage;
    /** création d'une liste de garage de type garage**/
    public ObservableList<Garageinfo> Listgar = FXCollections.observableArrayList();


    /** Fonction d'affichage des garages **/

    public void Affichage(){
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;
        try{
            connection = Dbconnexion.connect();
            String sql = "SELECT * FROM garage";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            Listgar.clear();
            while (rs.next()){
                Listgar.add(new Garageinfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        idGarage.setCellValueFactory(new PropertyValueFactory<Garageinfo, String>("IdGarage"));
        nomGarage.setCellValueFactory(new PropertyValueFactory<Garageinfo, String>("nomGarage"));
        adressegarage.setCellValueFactory(new PropertyValueFactory<Garageinfo, String>("adressegarage"));
        mailgarage.setCellValueFactory(new PropertyValueFactory<Garageinfo, String>("mailgarage"));
        telgarage.setCellValueFactory(new PropertyValueFactory<Garageinfo, Integer>("telgarage"));
        table.setItems(Listgar);
    }







    /** fonction permettant d'afficher l'interface d'insertion d' un garage **/
    @FXML
    public void Insertion(ActionEvent event){
        try {
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface d'affichageGarage
            stage.initStyle(StageStyle.UNDECORATED); // permet d'enlever la barre de titre
            Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/pageCreer.fxml")); // envoi sur l'interface de création d'un garage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Insérer un garage");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /** Affichage de l'interface de suppression et de mise à jour **/
    @FXML
    public void DeleteUpdate(ActionEvent event) throws IOException{
        try{
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.initStyle(StageStyle.UNDECORATED);
            Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/DeleteUpdateGarage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modifier ou suppression");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    /** fonction permettant d'afficher l'interface d'insertion d' un garage **/
    @FXML
    public void Insert(ActionEvent event){
        try {
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface d'affichageEmploye
            stage.initStyle(StageStyle.UNDECORATED); // permet d'enlever la barre de titre
            Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/PageCreer.fxml")); // envoi sur l'interface de création d'employé
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Insérer un garage");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** fonction permettant d'afficher l'interface de suppression/modifier **/
    @FXML
    public void supprimerModifier(ActionEvent event){
        try {
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface d'affichageGarage
            stage.initStyle(StageStyle.UNDECORATED); // permet d'enlever la barre de titre
            Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/DeleteUpdateGarage.fxml")); // envoi sur l'interface de suppression/modification d'un garage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modifier/Supprimer un garage");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Affichage(); // permet d'afficher la liste des employés lors de l'affichage de l'interface
    }
}
