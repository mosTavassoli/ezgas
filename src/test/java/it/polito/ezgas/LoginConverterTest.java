package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polito.ezgas.converter.LoginConverter;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;

public class LoginConverterTest {
	
	
	private Integer userId=1;
	private String userName = "user1";
	private String token="";
	private String email = "user1@user.com";
	private Integer reputation = 1;
	private Boolean admin = false;
	private String password ="user1";
	
	@Test
	public void testLoginConverter() {
		new LoginConverter();
	}
	
	
	@Test
	public void testToDto() {
		
		User user = new User (userName,password,email,reputation);
		user.setUserId(userId);
		user.setAdmin(admin);
		
		LoginDto loginDto = LoginConverter.toDto(user);
		
		assertEquals(this.userId,loginDto.getUserId());
		assertEquals(this.userName,loginDto.getUserName());
		assertEquals(this.token,loginDto.getToken());
		assertEquals(this.email,loginDto.getEmail());
		assertEquals(this.reputation,loginDto.getReputation());
					
	}
}
