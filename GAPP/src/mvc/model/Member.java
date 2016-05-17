package mvc.model;

public class Member {
	    private String    MP_Login;
	    private String    MP_Password;
	    
	    public Member(String MP_Login, String MP_Password) {
		    this.MP_Login = MP_Login;
		    this.MP_Password = MP_Password;
		  }

	    public void setMP_Login(String MP_Login) {
	        this.MP_Login = MP_Login;
	    }
	    public String getMP_Login() {
	        return MP_Login;
	    }

	    public void setMP_Password(String MP_Password) {
	        this.MP_Password = MP_Password;
	    }
	    public String getMP_Password() {
	        return MP_Password;
	    }
}
