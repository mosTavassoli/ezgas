package it.polito.ezgas.service.impl;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
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
		userDto = UserConverter.toDto(userRepository.findByUserId(userId));
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
		userRepository.delete(userId);
		return true;
	}

	public String generateNewToken() {
		SecureRandom secureRandom = new SecureRandom();
		Base64.Encoder base64Encoder = Base64.getUrlEncoder();
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		LoginDto loginDto;
		User user = userRepository.findByEmail(credentials.getUser());
		if(user.getPassword().equals(credentials.getPw())) {
			 loginDto= UserConverter.toLoginDto(user);
			 loginDto.setToken(generateNewToken());
			 return loginDto;
		}
		throw new InvalidLoginDataException("Invalid email or password!");
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		UserDto userDto = getUserById(userId);
		Integer reputation = userDto.getReputation();
		if (reputation <5) {
			userDto.setReputation(reputation + 1);
			userDto = saveUser(userDto);
		}
		return userDto.getReputation();
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		UserDto userDto = getUserById(userId);
		Integer reputation = userDto.getReputation();
		if (reputation >-5) {
			userDto.setReputation(reputation - 1);
			userDto = saveUser(userDto);
		}
		return userDto.getReputation();
	}
	
}
