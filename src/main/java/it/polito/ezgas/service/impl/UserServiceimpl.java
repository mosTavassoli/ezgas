package it.polito.ezgas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.LoginConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		UserDto userDto;
		if(userId<0) {
			throw new InvalidUserException("Invalid user!");
		}
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			return null;
		}
		userDto = UserConverter.toDto(user);
		return userDto;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = UserConverter.toEntity(userDto);
		
		// Check if the user exists or not, If not we can save the user
		if (userRepository.findByEmail(userDto.getEmail()) == null) { 
			user=userRepository.save(user);
		} else {
			return null;
		}
		
		if(user == null) {
			return null;
		}
		userDto = UserConverter.toDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> userList = userRepository.findAll();
		if(userList == null) {
			return null;
		}
		//list can be empty
		List<UserDto> userDtoList = UserConverter.toDto(userList);
		return userDtoList;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		if(userId<0) {
			throw new InvalidUserException("Invalid user!");
		}
		if(userRepository.exists(userId)) {
			userRepository.delete(userId);
			if(!userRepository.exists(userId)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		LoginDto loginDto;
		User user = userRepository.findByEmail(credentials.getUser());
		if(user != null && user.getPassword().equals(credentials.getPw())) {
			 loginDto= LoginConverter.toDto(user);
			 return loginDto;
		}
		throw new InvalidLoginDataException("Invalid login data!");
	}
	
	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		if(userId<0) {
			throw new InvalidUserException("Invalid user!");
		}
		UserDto userDto = getUserById(userId);
		userDto.editUserReputation(+1);
		userDto = saveUser(userDto);
		return userDto.getReputation();
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		if(userId<0) {
			throw new InvalidUserException("Invalid user!");
		}
		UserDto userDto = getUserById(userId);
		userDto.editUserReputation(-1);
		userDto = saveUser(userDto);
		return userDto.getReputation();
	}
	
}
