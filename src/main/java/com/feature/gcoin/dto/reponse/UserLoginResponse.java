package com.feature.gcoin.dto.reponse;

import com.feature.gcoin.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

public class UserLoginResponse  {

	private static final long serialVersionUID = 1L;
	private String userName;
	private UserDTO userDTO;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserDTO getUserDTO()
	{
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO)
	{
		this.userDTO = userDTO;
	}

}
