package hub.policy.dto;

public class UserSignInDetail {
	Long id;
	String firstName;
	String lastName;
	String signinStatus;
	

	public UserSignInDetail() {
		super();
	}
	
	
	public UserSignInDetail(String firstName, String lastName, String signinStatus) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.signinStatus = signinStatus;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSigninStatus() {
		return signinStatus;
	}
	public void setSigninStatus(String signinStatus) {
		this.signinStatus = signinStatus;
	}


	@Override
	public String toString() {
		return "UserSignInDetail [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", signinStatus="
				+ signinStatus + "]";
	}
	
	
	
	
}
