package beans;

import java.util.Date;

public class T_comment_p {
    private int      CMP_id;
    private String   CMP_description;
    private Date     CMP_date;
    private String   T_MEMBER_P_MP_Login;
    private int      T_GROUP_P_GP_id;
    private int      T_COMPETENCE_P_CP_id;

    public int getCMP_id() {
        return CMP_id;
    }
    public void setCMP_id(int CMP_id) {
        this.CMP_id = CMP_id;
    }

    public String getCMP_description() {
        return CMP_description;
    }
    public void setCMP_description(String CMP_description) {
        this.CMP_description = CMP_description;
    }
    
    public Date getCMP_date() {
        return CMP_date;
    }
    public void setCMP_date(Date CMP_date) {
        this.CMP_date = CMP_date;
    }
    
    public String getT_MEMBER_P_MP_Login() {
        return T_MEMBER_P_MP_Login;
    }
    public void setT_MEMBER_P_MP_Login(String T_MEMBER_P_MP_Login) {
        this.T_MEMBER_P_MP_Login = T_MEMBER_P_MP_Login;
    }
    
    public int getT_GROUP_P_GP_id() {
        return T_GROUP_P_GP_id;
    }
    public void setT_GROUP_P_GP_id(int T_GROUP_P_GP_id) {
        this.T_GROUP_P_GP_id = T_GROUP_P_GP_id;
    }
    
    public int getT_COMPETENCE_P_CP_id() {
        return T_COMPETENCE_P_CP_id;
    }
    public void setT_COMPETENCE_P_CP_id(int T_COMPETENCE_P_CP_id) {
        this.T_COMPETENCE_P_CP_id = T_COMPETENCE_P_CP_id;
    }
}
