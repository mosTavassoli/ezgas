package it.polito.ezgas.service.impl;

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
		userDto = UserConverter.toDto(userRepository.findOne(userId));
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

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		// TODO Auto-generated method stub
		List<UserDto> arr = getAllUsers();
		for(int i=0; i<arr.size(); i++) {
			if(arr.get(i).getEmail() == credentials.getUser() && arr.get(i).getPassword()==credentials.getPw()) {
				LoginDto ld = new LoginDto(
						arr.get(i).getUserId(),
						arr.get(i).getUserName(),
						"a token",//TODO:token where do i get the token from ?
						arr.get(i).getEmail(),
						arr.get(i).getReputation()
						);
				ld.setAdmin(arr.get(i).getAdmin());
				return ld;
			}
		}
		return null; //TODO change null maybe ?
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		UserDto user = getUserById(userId);
		Integer rep = user.getReputation();
		if (rep <5) {
			user.setReputation(rep + 1);
			user = saveUser(user);
		}
		return user.getReputation();
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		UserDto user = getUserById(userId);
		Integer rep = user.getReputation();
		if (rep >-5) {
			user.setReputation(rep - 1);
			user = saveUser(user);
		}
		return user.getReputation();
	}
	
}
