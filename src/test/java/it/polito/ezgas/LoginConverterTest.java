package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polito.ezgas.converter.LoginConverter;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;

public class LoginConverterTest {

	private Integer userId;
	private String userName = "user1";
	private String token="";
	private String email = "user1@user.com";
	private Integer reputation = 1;
	private Boolean admin = false;
	private String password ="user1";
	private String correctString;
	
	
	@Test
	public void testToDto() {
		User user = new User (userName,password,email,reputation);
		userId = user.getUserId();
		admin = user.getAdmin();
 		
		LoginDto loginDto = LoginConverter.toDto(user);
		correctString = "\n{\n"
				+ "userId = " + this.userId + ",\n"
				+ "userName = " + this.userName + ",\n"
				+ "token = " + this.token + ",\n"
				+ "email = " + this.email + ",\n"
				+ "reputation = "+ this.reputation + ",\n"
				+ "}\n";
		assertEquals(correctString, loginDto.toString());
				
	}
}
