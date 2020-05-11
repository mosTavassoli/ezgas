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
		User user = userRepository.findByUserId(userId);
		userDto = UserConverter.toDto(user);
		return userDto;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		User user = UserConverter.toEntity(userDto);
		userRepository.save(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> list = UserConverter.toDto(userRepository.findAll());
		return list;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		if(userRepository.existsByUserId(userId)) {
			userRepository.delete(userId);
		}
		throw new InvalidUserException("No user found!");
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		LoginDto loginDto;
		User user = userRepository.findByEmail(credentials.getUser());
		if(user.getPassword().equals(credentials.getPw())) {
			 loginDto= LoginConverter.toDto(user);
			 return loginDto;
		}
		throw new InvalidLoginDataException("Invalid email or password!");
	}
	
	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		UserDto userDto = getUserById(userId);
		userDto.editUserReputation(+1);
		userDto = saveUser(userDto);
		return userDto.getReputation();
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		UserDto userDto = getUserById(userId);
		userDto.editUserReputation(-1);
		userDto = saveUser(userDto);
		return userDto.getReputation();
	}
	
}
