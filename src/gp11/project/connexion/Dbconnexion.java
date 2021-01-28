package gp11.project.connexion;

import gp11.project.model.Garageinfo;


import java.sql.*;

public class Dbconnexion {
    static Connection connection;

    public static Connection connect() throws SQLException {
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/projetumlpoo",
                    "root",
                    "");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
    /** fonction qui permet d'enregistrer un garage en BD**/
    public static int Enregistrer(Garageinfo garage){
        int st = 0;
        try{
            String sql = "INSERT INTO garage(idGarage,NomGarage,Adressegarage,Mailgarage,Telgarage) VALUES (?,?,?,?,?)";
            Connection connection = Dbconnexion.connect();
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setString(1,garage.getIdGarage());
            stat.setString(2,garage.getNomGarage());
            stat.setString(3,garage.getAdressegarage());
            stat.setString(4,garage.getMailgarage());
            stat.setInt(5,garage.getTelgarage());

            st = stat.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** fonction qui permet la mise à jour des données d'un garage **/
    public static int Update(Garageinfo garageinfo){
        int st = 0;
        try {
            Connection connection = Dbconnexion.connect();
            PreparedStatement stat = connection.prepareStatement("UPDATE garage SET nomGarage=?,adressegarage=?,mailgarage=?,telgarage=? WHERE idGarage=?");

            stat.setString(1,garageinfo.getNomGarage());
            stat.setString(2,garageinfo.getAdressegarage());
            stat.setString(3,garageinfo.getMailgarage());

            stat.setInt(4,garageinfo.getTelgarage());
            stat.setString(5,garageinfo.getIdGarage());

            st = stat.executeUpdate();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** Fonction qui permet de retrouver un garage à l'aide de son id **/
    public static Garageinfo ChercherGar(String idGarage){
        Garageinfo garageinfo = new Garageinfo();
        try {
            Connection connection = Dbconnexion.connect();
            PreparedStatement stat = connection.prepareStatement("SELECT * FROM garage WHERE idGarage=?");
            stat.setString(1,idGarage);

            ResultSet res = stat.executeQuery();
            if(res.next()){
                garageinfo.setIdGarage(res.getString(1));
                garageinfo.setNomGarage(res.getString(2));
                garageinfo.setAdressegarage(res.getString(3));
                garageinfo.setMailgarage(res.getString(4));

                garageinfo.setTelgarage(res.getInt(5));
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return garageinfo;
    }



    /** fonction qui permet de supprimer les données d'un garage en BD**/
    public static int Delete(String idGarage){
        int st = 0;
        try {
            Connection connection = Dbconnexion.connect();
            PreparedStatement stat = connection.prepareStatement("DELETE FROM garage WHERE idGarage=?");
            stat.setString(1, idGarage);

            st = stat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }



    /***********************************************************************************************************************************/



}
