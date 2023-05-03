package com.ssafy.enjoy.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	String user_id;
	String user_name;
	String user_password;
	String email_id;
	String email_domain;
	String join_date;
}
