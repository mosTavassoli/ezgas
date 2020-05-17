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
	public void testEdiUserReputationNegativeOutOfBounds() {
		userDto.setReputation(3);
		int reputation = userDto.editUserReputation(-50);
		
		assertEquals(-5, reputation);
	}
	


}
