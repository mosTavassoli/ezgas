package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

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
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3, true);
		assertEquals((Integer)12, userDto.getUserId());
	}
	
	@Test
	public void testUserName() {
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3, true);
		assertEquals("username12", userDto.getUserName());
	}
	
	@Test
	public void testPassword() {
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3, true);
		assertEquals("pass12", userDto.getPassword()); 
	}
	
	@Test
	public void testEmail() {
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3, true);
		assertEquals("test12@example.com", userDto.getEmail()); 
	}
	
	@Test
	public void testReputation() {
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3, true);
		assertEquals((Integer)3, userDto.getReputation()); 
	}
	
	@Test
	public void testAdmin() {
		userDto = new UserDto(12, "username12", "pass12", "test12@example.com", 3, true);
		assertEquals(true, userDto.getAdmin());
	}
	
}
