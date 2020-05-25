package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.dto.UserDto;

public class UserDtoTest {
	
	private UserDto userDto;
	
	@Before
	public void init() {
		userDto = new UserDto();
	}

	@Test
	public void testConstructor() {
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3, true);
		
		assertEquals((Integer)12, userDto.getUserId());
		assertEquals("username12", userDto.getUserName());
		assertEquals("pass12", userDto.getPassword());
		assertEquals("test12@example.com", userDto.getEmail());
		assertEquals((Integer)3, userDto.getReputation());
		assertEquals(true, userDto.getAdmin());
	}

	@Test
	public void testConstructorRegularUser() {
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3);
		
		assertEquals((Integer)12, userDto.getUserId());
		assertEquals("username12", userDto.getUserName());
		assertEquals("pass12", userDto.getPassword());
		assertEquals("test12@example.com", userDto.getEmail());
		assertEquals((Integer)3, userDto.getReputation());
	}
	
	
	
	@Test
	public void testEditUserReputationNegativeOutOfBounds() {
		userDto.setReputation(3);
		int reputation = userDto.editUserReputation(-50);
		
		assertEquals(-5, reputation);
	}
	
	@Test
	public void testEditUserReputationNegativeWithinBounds() {
		userDto.setReputation(3);
		int reputation = userDto.editUserReputation(-7);
		
		assertEquals(-4, reputation);
	}
	
	@Test
	public void testEditUserReputationPositiveOutOfBounds() {
		userDto.setReputation(3);
		int reputation = userDto.editUserReputation(609);
		
		assertEquals(5, reputation);
	}
	
	@Test
	public void testEditUserReputationPositiveWithinBounds() {
		userDto.setReputation(3);
		int reputation = userDto.editUserReputation(1);
		
		assertEquals(4, reputation);
	}

	
	@Test
	public void testUserId() {
		userDto.setUserId((Integer)12);
		assertEquals((Integer)12, userDto.getUserId());
	}
	
	@Test
	public void testUserName() {
		userDto.setUserName("username12");
		assertEquals("username12", userDto.getUserName());
	}
	
	@Test
	public void testPassword() {
		userDto.setPassword("pass12");
		assertEquals("pass12", userDto.getPassword()); 
	}
	
	@Test
	public void testEmail() {
		userDto.setEmail("test12@example.com");
		assertEquals("test12@example.com", userDto.getEmail()); 
	}
	
	@Test
	public void testReputation() {
		userDto.setReputation((Integer)3);
		assertEquals((Integer)3, userDto.getReputation()); 
	}
	
	@Test
	public void testAdmin() {
		userDto.setAdmin(true);
		assertEquals(true, userDto.getAdmin());
	}
	
}
