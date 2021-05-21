package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 회원정보
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private String userId;
	private String userPwd;
	private String userEmail;
	private String userName;
	private String userAddr;
	private String userTel;
	private String userRegisterDate;
}
