package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polito.ezgas.converter.LoginConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.service.impl.UserServiceimpl;
import javassist.tools.reflect.Reflection;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceimplTest {
	
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceimpl();
        }
    }
	private final int NUMBER_OF_USER = 5;
    private int validUserId;
    private List<User> userList;
    
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	
	User user;
	UserDto userDto;
	LoginDto loginDto;

	@Before
	public void init() {
		Random random = new Random();
		userList = new ArrayList<User>();
		for (int i=0;i< NUMBER_OF_USER; i++) {
			userList.add(userRepository.save(new User(
					"userName"+i, 
					"password"+i, 
					"email"+i, 
					random.nextInt())));
		}
		
		
		user = new User("user1", "passowrd1", "user1@example.com", 3);
		userRepository.save(user);
		
		
		if(user!=null) {
			validUserId = user.getUserId();
		}
	}
	
	
	@Test(expected = InvalidUserException.class)
	public void testGetUserByIdNegative() throws InvalidUserException {
		userService.getUserById(-12);
	}
	
	@Test
	public void testGetUserByIdDoesNotExist() throws InvalidUserException{
		assertNull(userService.getUserById(9999));
	}
	
	@Test
	public void testGetUserByIdPositiveAndExists() throws InvalidUserException {
		userDto = userService.getUserById(this.validUserId);
		assertEquals(validUserId,(int) userDto.getUserId());
	}
	
	
	
	@Test
	public void testGetAllUsersNotEmpty() {
		List<UserDto> userDtoList;
		userDtoList = userService.getAllUsers();
		assertEquals(NUMBER_OF_USER+2 , userDtoList.size());
	}

	
	@Test
	public void testSaveUserValid() {
		UserDto userDtoSent = new UserDto(123, "userName", "pass", "email",4);
		UserDto userDtoReceievd = userService.saveUser(userDtoSent);
		assertTrue(new ReflectionEquals(userDtoSent,"userId","email").matches(userDtoReceievd));
	}
	
	
	@Test(expected = InvalidUserException.class)
	public void testDeleteUserUnsuccessful() throws InvalidUserException{
		userService.deleteUser(-12);
	}
	
	@Test
	public void testDeleteUserSuccessful() throws InvalidUserException{
		assertTrue(userService.deleteUser(1));
	}
	
	
//	@Test void testDeleteUserDeleteFails() throws InvalidUserException{
//		UserRepository userRepositoryMock = mock(UserRepository.class);
//		UserService userService;
//		
//		when(userRepositoryMock.exists(userList.get(0).getUserId())).thenReturn(true);
//		userService = new UserServiceimpl(userRepositoryMock);
//		assertNull(userService.deleteUser(userList.get(0).getUserId()));
//	}
	
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
