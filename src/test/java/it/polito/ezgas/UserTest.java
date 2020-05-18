package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.entity.User;

public class UserTest {
	User user;
	 
	@Before
	public void init() {
		this.user = new User();
	}
	
	private Integer userId = 15;
	private String userName = "Username15";
	private String password = "pass15";
	private String email = "test15@email.com";
	private Integer reputation = 3;
	private Boolean admin = false;
	
	@Test
	public void testUserEntityConstructor() {
		user = new User(this.userName,this.password,this.email,this.reputation);
		assertNull(user.getUserId());
		assertEquals("Username15", user.getUserName());
		assertEquals("pass15", user.getPassword());
		assertEquals("test15@email.com", user.getEmail());
		assertTrue(user.getReputation() == this.reputation);
	}
	
	
	@Test
	public void testUserIdMinInt() {
		user.setUserId(Integer.MIN_VALUE);
		assertTrue(user.getUserId() == Integer.MIN_VALUE);
	}
	
	@Test
	public void testUserIdMinIntPlusOne() {
		user.setUserId(Integer.MIN_VALUE + 1);
		assertTrue(user.getUserId() == Integer.MIN_VALUE + 1 );
	}
	
	@Test
	public void testUserIdMinusOne() {
		user.setUserId(-1);
		assertTrue(user.getUserId() == -1);
	}
	
	@Test
	public void testUserIdZero() {
		user.setUserId(0);
		assertTrue(user.getUserId() == 0);
	}
	
	@Test
	public void testUserIdMaxInt() {
		user.setUserId(Integer.MAX_VALUE);
		assertTrue(user.getUserId() == Integer.MAX_VALUE);
	}
	
	@Test
	public void testUserIdMaxIntMinusOne() {
		user.setUserId(Integer.MAX_VALUE -1);
		assertTrue(user.getUserId() == Integer.MAX_VALUE -1);
	}
	

}
