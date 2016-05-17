package beans;

public class T_competence_l {
    private String   CL_fk_MP;
    private int      CL_fk_CP;
    private int      CL_id;
    private String   CL_noteur;
    private int      T_NOTE_P_NP_id;

    public String getCL_fk_MP() {
        return CL_fk_MP;
    }
    public void setCL_fk_MP(String CL_fk_MP) {
        this.CL_fk_MP = CL_fk_MP;
    }

    public int getCL_fk_CP() {
        return CL_fk_CP;
    }
    public void setCL_fk_CP(int CL_fk_CP) {
        this.CL_fk_CP = CL_fk_CP;
    }
    
    public int getCL_id() {
        return CL_id;
    }
    public void setCL_id(int CL_id) {
        this.CL_id = CL_id;
    }
    
    public String getCL_noteur() {
        return CL_noteur;
    }
    public void setCL_noteur(String CL_noteur) {
        this.CL_noteur = CL_noteur;
    }
    
    public int getT_NOTE_P_NP_id() {
        return T_NOTE_P_NP_id;
    }
    public void setT_NOTE_P_NP_id(int T_NOTE_P_NP_id) {
        this.T_NOTE_P_NP_id = T_NOTE_P_NP_id;
    }
}
