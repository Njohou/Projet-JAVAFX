package gp11.project.model;

public class Garageinfo {
    private String idGarage;
    private String nomGarage;
    private  String adressegarage;
    private  String mailgarage;
    private int telgarage;

    public Garageinfo() {
    }

    public Garageinfo(String idGarage, String nomGarage, String adressegarage, String mailgarage, int telgarage) {
        this.idGarage = idGarage;
        this.nomGarage = nomGarage;
        this.adressegarage = adressegarage;
        this.mailgarage = mailgarage;
        this.telgarage = telgarage;
    }

    public String getIdGarage() {
        return idGarage;
    }

    public void setIdGarage(String idGarage) {
        this.idGarage = idGarage;
    }

    public String getNomGarage() {
        return nomGarage;
    }

    public void setNomGarage(String nomGarage) {
        this.nomGarage = nomGarage;
    }

    public String getAdressegarage() {
        return adressegarage;
    }

    public void setAdressegarage(String adressegarage) {
        this.adressegarage = adressegarage;
    }

    public String getMailgarage() {
        return mailgarage;
    }

    public void setMailgarage(String mailgarage) {
        this.mailgarage = mailgarage;
    }

    public int getTelgarage() {
        return telgarage;
    }

    public void setTelgarage(int telgarage) {
        this.telgarage = telgarage;
    }
}
