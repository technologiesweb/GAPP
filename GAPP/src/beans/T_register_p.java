package beans;

import java.util.Date;

public class T_register_p {
	  	private int      RP_id;
	    private Date   	 RP_date;
	    private String   T_MEMBER_P_MP_Login;
	    private int    	 T_COURSES_P_CP_Id;

	    public int getRP_id() {
	        return RP_id;
	    }
	    public void setRP_id(int RP_id) {
	        this.RP_id = RP_id;
	    }

	    public Date getRP_date() {
	        return RP_date;
	    }
	    public void setRP_date(Date RP_date) {
	        this.RP_date = RP_date;
	    }
	    
	    public String getT_MEMBER_P_MP_Login() {
	        return T_MEMBER_P_MP_Login;
	    }
	    public void setT_MEMBER_P_MP_Login(String T_MEMBER_P_MP_Login) {
	        this.T_MEMBER_P_MP_Login = T_MEMBER_P_MP_Login;
	    }
	    
	    public int getT_COURSES_P_CP_Id() {
	        return T_COURSES_P_CP_Id;
	    }
	    public void setT_COURSES_P_CP_Id(int T_COURSES_P_CP_Id) {
	        this.T_COURSES_P_CP_Id = T_COURSES_P_CP_Id;
	    }
}
