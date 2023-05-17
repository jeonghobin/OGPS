package com.ssafy.enjoy.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupPlanDto {
	int planNo;
	int groupNo;
	String subject;
	String userId;
	int like;
}
