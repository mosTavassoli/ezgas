package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;

import static org.mockito.Mockito.*;

import it.polito.ezgas.converter.LoginConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.service.impl.UserServiceimpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceimplTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	User user;
	UserDto userDto;
	LoginDto loginDto;

	@Before
	public void init() {		
		user = new User("user1", "passowrd1", "user1@example.com", 3);
		user.setUserId(1);
		userDto = new UserDto(1, "user1", "passowrd1", "user1@example.com", 3, false);
		loginDto = new LoginDto(1, "user1", "token1", "user1@example.com", 3);
		
		userRepository.save(user);
	}
	
	@Test(expected = InvalidLoginDataException.class)
	public void testLoginThrowInvalidLoginDataExceptionForWrongPw() throws InvalidLoginDataException {
		IdPw credentials = new IdPw(user.getEmail(), "");
		userService.login(credentials);
	}
	
	@Test(expected = InvalidLoginDataException.class)
	public void testLoginThrowInvalidLoginDataExceptionForWrongEmail() throws InvalidLoginDataException {
		IdPw credentials = new IdPw("", user.getPassword());
		userService.login(credentials);
	}
	
	@Test
	public void testLoginSuccessuful() throws InvalidLoginDataException {
		IdPw credentials = new IdPw(user.getEmail(), user.getPassword());
		assertNotNull(userService.login(credentials));
	}
	
	@Test(expected = InvalidUserException.class)
	public void testIncreaseUserReputationThrowsInvalidUserException() throws InvalidUserException {
		userService.increaseUserReputation(-1);
	}
	
	@Test
	public void testIncreaseUserReputation() throws InvalidUserException {
		assertNotNull(userService.increaseUserReputation(1));
	}
	
	@Test(expected = InvalidUserException.class)
	public void testDecreaseUserReputationThrowsInvalidUserException() throws InvalidUserException {
		userService.decreaseUserReputation(-1);
	}
	
	@Test
	public void testDecreaseUserReputation() throws InvalidUserException {
		assertNotNull(userService.decreaseUserReputation(1));
	}
	
}
