package Gestionreparation.Model;

public class Reparation {
    private Integer idReparation;
    private String type;
    private Float cout;
    private String immatriculation;

    public Reparation(Integer idReparation, String type, Float cout, String immatriculation) {
        this.idReparation = idReparation;
        this.type = type;
        this.cout = cout;
        this.immatriculation = immatriculation;
    }

    public Integer getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(Integer idReparation) {
        this.idReparation = idReparation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getCout() {
        return cout;
    }

    public void setCout(Float cout) {
        this.cout = cout;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }
}
