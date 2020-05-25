package it.polito.ezgas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	
    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService(UserRepository userRepository) {
            return new UserServiceimpl(userRepository);
        }
    }
    
	private final int NUMBER_OF_USER = 5;
    private int validUserId;
    private List<User> userList;
	
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
		assertNotNull(userService.getUserById(this.validUserId));
	}
	
	@Test
	public void testSaveUserValid() {
		UserDto userDto = new UserDto(123, "userName", "pass", "email",4);
		assertNotNull(userService.saveUser(userDto));
	}
	
	@Test
	public void testSaveUserForUpdate() {
		UserDto userDto = new UserDto(1, "user1", "passowrd1", "user1@example.com", 5);
		assertNotNull(userService.saveUser(userDto));
	}
	
	@Test
	public void testSaveUserForInvalidUpdate() {
		UserDto userDto = new UserDto(null, "user1", "passowrd1", "user1@example.com", 5);
		assertNull(userService.saveUser(userDto));
	}
	
	@Test
	public void testSaveUserFails() {
		UserRepository userRepositoryMock = mock(UserRepository.class);
		UserService userService;
		UserDto userDto = new UserDto(null, "user1", "passowrd1", "user1@example.com", 5);
		
		when(userRepositoryMock.save(UserConverter.toEntity(userDto))).thenReturn(null);
		userService = new UserServiceimpl(userRepositoryMock);
		assertNull(userService.saveUser(userDto));
	}
	
	@Test
	public void testGetAllUsersNotEmpty() {
		List<UserDto> userDtoList;
		userDtoList = userService.getAllUsers();
		assertEquals(NUMBER_OF_USER+2 , userDtoList.size());
	}
	
	@Test
	public void testGetAllUsersEmpty() {
		UserRepository userRepositoryMock = mock(UserRepository.class);
		UserService userService;
		
		when(userRepositoryMock.findAll()).thenReturn(null);
		userService = new UserServiceimpl(userRepositoryMock);
		assertNull(userService.getAllUsers());
	}
	
	
	@Test(expected = InvalidUserException.class)
	public void testDeleteUserThrowInvalidUserException() throws InvalidUserException{
		userService.deleteUser(-12);
	}
	
	@Test
	public void testDeleteUserSuccessful() throws InvalidUserException{
		assertTrue(userService.deleteUser(1));
	}
	
	@Test
	public void testDeleteUserNotExists() throws InvalidUserException{
		assertFalse(userService.deleteUser(9999));
	}
	
	@Test
	public void testDeleteUserFails() throws InvalidUserException{
		UserRepository userRepositoryMock = mock(UserRepository.class);
		UserService userService;
		
		when(userRepositoryMock.exists(1)).thenReturn(true);
		userService = new UserServiceimpl(userRepositoryMock);
		assertFalse(userService.deleteUser(1));
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
