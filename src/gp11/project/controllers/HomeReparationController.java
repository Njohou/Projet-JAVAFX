package gp11.project.controllers;

import Gestionreparation.Model.Reparation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HomeReparationController implements Initializable {
    Connection connection;

    @FXML
    private TableView<Reparation> table;

    @FXML
    private TableColumn<Reparation, Integer> idReparation;

    @FXML
    private TableColumn<Reparation, String> type;

    @FXML
    private TableColumn<Reparation, Float> cout;

    @FXML
    private TableColumn<Reparation, String> immatriculation;

    public ObservableList<Reparation> data = FXCollections.observableArrayList();

    @FXML
    void add(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/View/AddReparation.fxml"));
        primaryStage.setTitle("Gestion de réparation");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    void delete(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gp11/project/View/DeleteReparation.fxml"));
        primaryStage.setTitle("Gestion de réparation");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    public void display(ActionEvent event) {
        data.clear();
        String requete = "select * from reparation";
        PreparedStatement ps;
        ResultSet resultat;
        try {
            ps = connection.prepareStatement(requete);
            resultat = ps.executeQuery();//permet s'exécuter une requête de lecture de données
            while (resultat.next()) {
                data.add(new Reparation(resultat.getInt(1), resultat.getString(2), resultat.getFloat(3), resultat.getString(4)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        idReparation.setCellValueFactory(new PropertyValueFactory<Reparation,Integer>("idReparation"));
        type.setCellValueFactory(new PropertyValueFactory<Reparation,String>("type"));
        cout.setCellValueFactory(new PropertyValueFactory<Reparation,Float>("cout"));
        immatriculation.setCellValueFactory(new PropertyValueFactory<Reparation,String>("immatriculation"));
        table.setItems(data);
    }

    @FXML
    void modify(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projetuml",
                    "root",
                    "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
