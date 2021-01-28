package gp11.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddReparationController {

    Connection connection;

    @FXML
    private TextField type;

    @FXML
    private TextField cout;

    @FXML
    private TextField immatriculation;

    @FXML
    public void add(ActionEvent event) throws SQLException {
        String requete = "insert into reparation (type, cout, immatriculation) values (?,?,?)";
        PreparedStatement ps = null;
        //ResultSet rs= null;
        ps = connection.prepareStatement(requete);
        //ps.setInt(1, Integer.parseInt(indice.getText()));
        ps.setString(1, type.getText());
        ps.setFloat(2, Float.parseFloat(cout.getText()));
        ps.setString(3, immatriculation.getText());

        // Execution de la requête
        ps.executeUpdate();
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Confirmation");
        alert.setTitle("Alert");
        alert.setContentText("Cette réparation a été bien enregistée");
        alert.showAndWait();
        type.clear();
        cout.clear();
        immatriculation.clear();
    }


}
