package beans;

import java.util.Date;

public class T_courses_p {
    private Date     CP_StartingDate;
    private Date     CP_EndingDate;
    private int    	 CP_Id;
    private int   	 T_COURSES_T_CT_id;

    public Date getCP_StartingDate() {
        return CP_StartingDate;
    }
    public void setCP_StartingDate(Date CP_StartingDate) {
        this.CP_StartingDate = CP_StartingDate;
    }

    public Date getCP_EndingDate() {
        return CP_EndingDate;
    }
    public void setCP_EndingDate(Date CP_EndingDate) {
        this.CP_EndingDate = CP_EndingDate;
    }
    
    public int getCP_Id() {
        return CP_Id;
    }
    public void setCP_Id(int CP_Id) {
        this.CP_Id = CP_Id;
    }
    
    public int getT_COURSES_T_CT_id() {
        return T_COURSES_T_CT_id;
    }
    public void setT_COURSES_T_CT_id(int T_COURSES_T_CT_id) {
        this.T_COURSES_T_CT_id = T_COURSES_T_CT_id;
    }
}
