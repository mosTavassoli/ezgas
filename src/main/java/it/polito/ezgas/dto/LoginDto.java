package it.polito.ezgas.dto;

public class LoginDto {
	Integer userId;
    String userName;
    String token;
    String email;
    Integer reputation;
    Boolean admin;
    
    public LoginDto (Integer userId, String userName, String token, String email, Integer reputation) {
    	this.userId = userId;
    	this.userName = userName;
    	this.token = token;
    	this.email = email;
    	this.reputation = reputation;
    }
    
    public LoginDto() {}
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getReputation() {
		return reputation;
	}
	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
    public Boolean getAdmin() {
    	return admin;
    }
    public void setAdmin(Boolean admin) {
    	this.admin = admin;
    }
    
    
    @Override 
    public String toString() {
    	return "\n{\n"
				+ "userId = " + this.userId + ",\n"
				+ "userName = " + this.userName + ",\n"
				+ "token = " + this.token + ",\n"
				+ "email = " + this.email + ",\n"
				+ "reputation = " + this.reputation + ",\n"
				+ "}\n";
    }
}
