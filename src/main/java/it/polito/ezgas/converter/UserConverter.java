package it.polito.ezgas.converter;

import java.util.LinkedList;
import java.util.List;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {
	public static User toEntity(UserDto userDto) {
		User user;
		user = new User(userDto.getUserName(),userDto.getPassword(),userDto.getEmail(),userDto.getReputation());
		return user;
	}
	
	public static List<UserDto> toDto(List<User> userList) {
		List<UserDto> userDtoList = new LinkedList<UserDto>(); 
		for (User user : userList) {
			userDtoList.add(toDto(user));
		}
		return userDtoList;
	}
	
	public static UserDto toDto(User user) {
		UserDto userDto; 
		userDto = new UserDto(user.getUserId(),user.getUserName(),user.getPassword(),user.getEmail(),user.getReputation(),user.getAdmin());
		return userDto;
	}

	public static LoginDto toLoginDto(User user) {
		LoginDto loginDto = new LoginDto(user.getUserId(),user.getUserName(),"",user.getEmail(),user.getReputation());
		return loginDto;
	}
	
	
}
