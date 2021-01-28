package gp11.project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteReparationController implements Initializable {
    Connection connection;

    @FXML
    private TextField idReparation;

    @FXML
    void deleter() throws SQLException {
        deletereparation(Integer.parseInt(idReparation.getText()));
    }

    void deletereparation(int idReparation) throws SQLException {
        String requete = "delete from reparation where idReparation=?";
        PreparedStatement ps = connection.prepareStatement(requete);
        ps.setInt(1, idReparation);
        ps.executeUpdate();
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
