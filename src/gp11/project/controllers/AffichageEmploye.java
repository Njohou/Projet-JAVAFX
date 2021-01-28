package gp11.project.controllers;

import gp11.project.connexion.DbconnectionEmpl;
import gp11.project.model.EmployeInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AffichageEmploye implements Initializable {

    @FXML private TableView<EmployeInfo> tableEmpl;
    @FXML private TableColumn<EmployeInfo, Integer> IdEmpl;
    @FXML private TableColumn<EmployeInfo, String> NomEmpl;
    @FXML private TableColumn<EmployeInfo, String> PrenomEmpl;
    @FXML private TableColumn<EmployeInfo, String> Sexe;
    @FXML private TableColumn<EmployeInfo, String> ServiceTrav;
    @FXML private TableColumn<EmployeInfo, Integer> TelEmpl;
    @FXML private TableColumn<EmployeInfo, String> MailEmpl;
    @FXML private Button retour;

    /** création d'une liste d'employé de type employé**/
    public ObservableList<EmployeInfo> ListEmpl = FXCollections.observableArrayList();

    /** Fonction d'affichage des employés **/

    public void Affichage(){
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection connection;
        try{
            connection = DbconnectionEmpl.connect();
            String sql = "SELECT * FROM employe";
            stat = connection.prepareStatement(sql);
            rs = stat.executeQuery();
            ListEmpl.clear();
            while (rs.next()){
                ListEmpl.add(new EmployeInfo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        IdEmpl.setCellValueFactory(new PropertyValueFactory<EmployeInfo, Integer>("IdEmpl"));
        NomEmpl.setCellValueFactory(new PropertyValueFactory<EmployeInfo, String>("NomEmpl"));
        PrenomEmpl.setCellValueFactory(new PropertyValueFactory<EmployeInfo, String>("PrenomEmpl"));
        Sexe.setCellValueFactory(new PropertyValueFactory<EmployeInfo, String>("Sexe"));
        MailEmpl.setCellValueFactory(new PropertyValueFactory<EmployeInfo, String>("MailEmpl"));
        TelEmpl.setCellValueFactory(new PropertyValueFactory<EmployeInfo, Integer>("TelEmpl"));
        ServiceTrav.setCellValueFactory(new PropertyValueFactory<EmployeInfo, String>("ServiceTrav"));
        tableEmpl.setItems(ListEmpl);
    }


    /** Affichage de l'interface de suppression et de mise à jour **/
    @FXML
        public void DeleteUpdate(ActionEvent event) throws IOException{
        try{
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.initStyle(StageStyle.UNDECORATED);
            Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/DeleteUpdate.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Modifier ou suppression");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /** fonction permettant d'afficher l'interface d'insertion d' un employé **/
    @FXML
        public void Insertion(ActionEvent event){
        try {
            Stage stage = new Stage();
            ((Node)event.getSource()).getScene().getWindow().hide(); // permet de masquer l' interface d'affichageEmploye
            stage.initStyle(StageStyle.UNDECORATED); // permet d'enlever la barre de titre
            Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/view/employe.fxml")); // envoi sur l'interface de création d'employé
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Insérer un employé");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** fonction qui permet de fermer la fenêtre d'AffichageEmploye à l'aide de l'id de la touche "retour" **/
    @FXML
        public void Retour() throws IOException {
            Stage stage = (Stage) retour.getScene().getWindow();
            stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Affichage(); // permet d'afficher la liste des employés lors de l'affichage de l'interface
    }
}
