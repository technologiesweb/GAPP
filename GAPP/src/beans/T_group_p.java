package beans;

import java.util.Date;

public class T_group_p {
	 	private int      GP_id;
	    private String   GP_name;
	    private Date     GP_StartingDate;
	    private Date     GP_EndingDate;

	    public int getGP_id() {
	        return GP_id;
	    }
	    public void setGP_id(int GP_id) {
	        this.GP_id = GP_id;
	    }

	    public String getGP_name() {
	        return GP_name;
	    }
	    public void setGP_name(String GP_name) {
	        this.GP_name = GP_name;
	    }
	    
	    public Date getGP_StartingDate() {
	        return GP_StartingDate;
	    }
	    public void setGP_StartingDate(Date GP_StartingDate) {
	        this.GP_StartingDate = GP_StartingDate;
	    }
	    
	    public Date getGP_EndingDate() {
	        return GP_EndingDate;
	    }
	    public void setGP_EndingDate(Date GP_EndingDate) {
	        this.GP_EndingDate = GP_EndingDate;
	    }
}
