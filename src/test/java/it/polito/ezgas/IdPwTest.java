package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.dto.IdPw;

public class IdPwTest {

	IdPw idpw;
	
	@Before
	public void init() {
		idpw = new IdPw();
	}
	
	@Test
	public void testConstructor () {
		idpw = new IdPw("user12", "pass12");
		assertEquals("user12", idpw.getUser());
		assertEquals("pass12", idpw.getPw());
	}
	
	@Test
	public void testNullObject() {
		assertEquals(null, idpw.getPw());
	}
	
	@Test
	public void testUserId() {
		idpw.setUser("user12");
		assertEquals("user12", idpw.getUser());
	}
	
	@Test
	public void testUserPw() {
		idpw.setPw("pass12");
		assertEquals("pass12", idpw.getPw());
	}

}
