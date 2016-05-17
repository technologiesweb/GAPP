package beans;

public class T_competence_p {
    private int      CP_id;
    private String   CP_name;
    private int    	 CP_parentId;
    private String   CP_description;
    private int      CP_coeff;

    public int getCP_id() {
        return CP_id;
    }
    public void setCP_id(int CP_id) {
        this.CP_id = CP_id;
    }

    public String getCP_name() {
        return CP_name;
    }
    public void setCP_name(String CP_name) {
        this.CP_name = CP_name;
    }
    
    public int getCP_parentId() {
        return CP_parentId;
    }
    public void setCP_parentId(int CP_parentId) {
        this.CP_parentId = CP_parentId;
    }
    
    public String getCP_description() {
        return CP_description;
    }
    public void setCP_description(String CP_description) {
        this.CP_description = CP_description;
    }
    
    public int getCP_coeff() {
        return CP_coeff;
    }
    public void setCP_coeff(int CP_coeff) {
        this.CP_coeff = CP_coeff;
    }
}
