package gp11.project.model;



public class EmployeInfo {
    private int IdEmpl;
    private String NomEmpl;
    private String PrenomEmpl;
    private String PasswordEmpl;
    private String Sexe;
    private String ServiceTrav;
    private String mailEmpl;
    private int telEmpl;


    public EmployeInfo(int idEmpl, String nomEmpl, String prenomEmpl, String sexe, String mailEmpl, String PasswordEmpl, int telEmpl, String serviceTrav ) {
        this.IdEmpl = idEmpl;
        this.NomEmpl = nomEmpl;
        this.PrenomEmpl = prenomEmpl;
        this.Sexe = sexe;
        this.mailEmpl = mailEmpl;
        this.PasswordEmpl= PasswordEmpl;
        this.telEmpl = telEmpl;
        this.ServiceTrav = serviceTrav;

    }

    public EmployeInfo(){}


    public int getIdEmpl() {
        return IdEmpl;
    }

    public void setIdEmpl(int idEmpl) {
        IdEmpl = idEmpl;
    }

    public String getNomEmpl() {
        return NomEmpl;
    }

    public void setNomEmpl(String nomEmpl) {
        NomEmpl = nomEmpl;
    }

    public String getPrenomEmpl() {
        return PrenomEmpl;
    }

    public void setPrenomEmpl(String prenomEmpl) {
        PrenomEmpl = prenomEmpl;
    }

    public String getPasswordEmpl() {
        return PasswordEmpl;
    }

    public void setPasswordEmpl(String passwordEmpl) {
        PasswordEmpl = passwordEmpl;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public String getServiceTrav() {
        return ServiceTrav;
    }

    public void setServiceTrav(String serviceTrav) {
        ServiceTrav = serviceTrav;
    }

    public String getMailEmpl() {
        return mailEmpl;
    }

    public int getTelEmpl() {
        return telEmpl;
    }

    public void setMailEmpl(String mailEmpl) {
        this.mailEmpl = mailEmpl;
    }

    public void setTelEmpl(int telEmpl) {
        this.telEmpl = telEmpl;
    }
}
