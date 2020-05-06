package it.polito.ezgas.converter;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {
	public static User toEntity(UserDto userDto) {
		User user;
		user = new User(userDto.getUserName(),userDto.getEmail(),userDto.getPassword(),0);
		return user;
	}
}
