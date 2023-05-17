package com.ssafy.enjoy.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberDto {
	int groupNo;
	String userId;
	int grade;
}
