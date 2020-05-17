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
	private String userName = "Username";
	private String password = "pass";
	private String email = "test@email.com";
	private Integer reputation = 5;
	private Boolean admin = false;
	
	@Test
	public void testUserIdMinInt() {
		user = new User();
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
	
	
	@Test
	public void testUserEntityConstructor() {
		user = new User(this.userName,this.password,this.email,this.reputation);
		assertNull(user.getUserId());
		assertTrue(user.getUserName() == this.userName);
		assertTrue(user.getPassword() == this.password);
		assertTrue(user.getEmail() == this.email);
		assertTrue(user.getReputation() == this.reputation);
	}
	
	
	@Test
	public void testUserName() {
		user = new User(this.userName,this.password,this.email,this.reputation);
		assertEquals(this.userName,this.user.getUserName());
	}

}
