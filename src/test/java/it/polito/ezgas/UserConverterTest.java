package it.polito.ezgas;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Arrays;

import org.junit.Test;

import it.polito.ezgas.converter.LoginConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverterTest {

	private Integer userId=1;
	private String userName = "user1";
	private String password ="pass1";
	private String email = "user1@user.com";
	private Integer reputation = 1;
	private Boolean admin = false;
	

	@Test
    public void testUserConverter(){
    	new UserConverter();
    }
	
	@Test
	public void testToDto() {
		User user = new User (userName,password,email,reputation);
		user.setUserId(userId);
		user.setAdmin(admin);
 		
		UserDto userDto = UserConverter.toDto(user);
		
		assertEquals(this.userId,userDto.getUserId());
		assertEquals(this.userName,userDto.getUserName());
		assertEquals(this.password,userDto.getPassword());
		assertEquals(this.email,userDto.getEmail());
		assertEquals(this.reputation,userDto.getReputation());	
	}
	
	
	@Test
	public void testToEntity() {
		UserDto userDto = new UserDto (1,userName,password,email,reputation);
		admin = userDto.getAdmin();
		
		User user = UserConverter.toEntity(userDto);
		
		assertEquals(this.userId,user.getUserId());
		assertEquals(this.userName,user.getUserName());
		assertEquals(this.password,user.getPassword());
		assertEquals(this.email,user.getEmail());
		assertEquals(this.reputation,user.getReputation());
		
	}
	
	@Test
	public void testToDtoList() {
		User user1 = new User (userName,password,email,reputation);
		user1.setUserId(userId);
		user1.setAdmin(admin);
		
		User user2 = new User ("user2","pass2","user2@user.com",2);
		user2.setUserId(userId);
		user2.setAdmin(admin);
		
		List<User> listEntity = Arrays.asList(user1,user2);
		List<UserDto> listDto = UserConverter.toDto(listEntity);
		
		assertEquals(listEntity.size(),listDto.size());
		
	}
	

}
