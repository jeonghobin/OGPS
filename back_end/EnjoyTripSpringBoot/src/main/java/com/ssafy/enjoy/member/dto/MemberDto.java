package com.ssafy.enjoy.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	String userId;
	String userName;
	String userPassword;
	String userEmail;
	String joinDate;
	int grade;
	String token;
}
