package gp11.project.connexion;

import gp11.project.model.ChauffeurInfo;
import gp11.project.model.EmployeInfo;

import java.sql.*;

/** création de la connexion à la BD **/
public class DbconnectionEmpl {
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

    /******************************************************************** CRUD EMPLOYE *****************************************************************************************/

    /** fonction qui permet d'enregistrer un employé en BD**/
    public static int Enregistrer(EmployeInfo emplinfo){
        int st = 0;
        try{
            String sql = "INSERT INTO employe(nomEmpl,prenomEmpl,sexe,mailEmpl,motdepasse,telEmpl,ServiceTravail) VALUES (?,?,?,?,?,?,?)";
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setString(1,emplinfo.getNomEmpl());
            stat.setString(2,emplinfo.getPrenomEmpl());
            stat.setString(3,emplinfo.getSexe());
            stat.setString(4,emplinfo.getMailEmpl());
            stat.setString(5,emplinfo.getPasswordEmpl());
            stat.setInt(6,emplinfo.getTelEmpl());
            stat.setString(7,emplinfo.getServiceTrav());

            st = stat.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** fonction qui permet la mise à jour des données d'un employé **/
    public static int Update(EmployeInfo emplinfo){
        int st = 0;
        try {
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement("UPDATE employe SET nomEmpl=?,prenomEmpl=?,sexe=?,mailEmpl=?,motdepasse=?,telEmpl=?,serviceTravail=? WHERE idEmpl=?");
            stat.setString(1,emplinfo.getNomEmpl());
            stat.setString(2,emplinfo.getPrenomEmpl());
            stat.setString(3,emplinfo.getSexe());
            stat.setString(4,emplinfo.getMailEmpl());
            stat.setString(5,emplinfo.getPasswordEmpl());
            stat.setInt(6,emplinfo.getTelEmpl());
            stat.setString(7,emplinfo.getServiceTrav());
            stat.setInt(8,emplinfo.getIdEmpl());

            st = stat.executeUpdate();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** fonction qui permet de supprimer les données d'un employé en BD**/
    public static int Delete(int IdEmpl){
        int st=0;
        try {
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement("DELETE FROM employe WHERE idEmpl=?");
            stat.setInt(1,IdEmpl);

            st = stat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** Fonction qui permet de retrouver un employé à l'aide de son id **/
    public static EmployeInfo ChercherEmpl(int IdEmpl){
        EmployeInfo emplinfo = new EmployeInfo();
        try {
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement("SELECT * FROM employe WHERE idEmpl=?");
            stat.setInt(1,IdEmpl);

            ResultSet res = stat.executeQuery();
            if(res.next()){
                emplinfo.setIdEmpl(res.getInt(1));
                emplinfo.setNomEmpl(res.getString(2));
                emplinfo.setPrenomEmpl(res.getString(3));
                emplinfo.setSexe(res.getString(4));
                emplinfo.setMailEmpl(res.getString(5));
                emplinfo.setPasswordEmpl(res.getString(6));
                emplinfo.setTelEmpl(res.getInt(7));
                emplinfo.setServiceTrav(res.getString(8));
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return emplinfo;
    }


    /***************************************************************************************************************************************************************/

    /************************************************************************** CRUD CHAUFFEUR *************************************************************************************/

    /** Fonction permettant de créer un chauffeur **/
    public static int EnregistrerChauff(ChauffeurInfo chauffInfo){
        int st = 0;
        try{
            String sql = "INSERT INTO chauffeur(idChauffeur,numpermis) VALUES (?,?)";
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement(sql);

            stat.setInt(1,chauffInfo.getIdChauff());
            stat.setString(2,chauffInfo.getNumPermis());

            st = stat.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** fonction permettant la mise à jour d'un chauffeur **/
    public static int Updatechauff(ChauffeurInfo chauffInfo){
        int st = 0;
        try {
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement("UPDATE chauffeur SET numpermis=? WHERE idChauffeur=?");
            stat.setString(1,chauffInfo.getNumPermis());
            stat.setInt(2,chauffInfo.getIdChauff());

            st = stat.executeUpdate();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** fonction permettant la suppression d'un chauffeur **/
    public static int Deletechauff(int IdCahuff){
        int st=0;
        try {
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement("DELETE FROM chauffeur WHERE idchauffeur=?");
            stat.setInt(1,IdCahuff);

            st = stat.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return st;
    }

    /** Fonction permaettant de rechercher un chauffeur **/
    public static ChauffeurInfo Chercherchauff(int IdChauff){
        ChauffeurInfo chauffInfo = new ChauffeurInfo();
        try {
            Connection connection = DbconnectionEmpl.connect();
            PreparedStatement stat = connection.prepareStatement("SELECT * FROM chauffeur WHERE idchauffeur=?");
            stat.setInt(1,IdChauff);

            ResultSet res = stat.executeQuery();
            if(res.next()){
                chauffInfo.setIdChauff(res.getInt(1));
                chauffInfo.setNumPermis(res.getString(2));
            }
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return chauffInfo;
    }

}