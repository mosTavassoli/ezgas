package it.polito.ezgas.converter;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;

public class LoginConverter {
	public static LoginDto toDto(User user) {
		LoginDto loginDto = new LoginDto(user.getUserId(),user.getUserName(),"",user.getEmail(),user.getReputation());
		return loginDto;
	}
}
