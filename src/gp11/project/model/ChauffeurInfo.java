package gp11.project.model;

public class ChauffeurInfo {
    private int IdChauff;
    private String NumPermis;

    public ChauffeurInfo(int idChauff, String numPermis) {
        this.IdChauff = idChauff;
        this.NumPermis = numPermis;
    }

    public ChauffeurInfo(){}

    public int getIdChauff() {
        return IdChauff;
    }

    public String getNumPermis() {
        return NumPermis;
    }

    public void setIdChauff(int idChauff) {
        IdChauff = idChauff;
    }

    public void setNumPermis(String numPermis) {
        NumPermis = numPermis;
    }
}
