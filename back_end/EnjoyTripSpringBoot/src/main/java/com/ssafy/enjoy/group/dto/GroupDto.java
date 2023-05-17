package com.ssafy.enjoy.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
	int groupNo;
	String userId;
	String subject;
	String registerTime;
	String startTime;
	String endTime;
	int memberCnt;
}
