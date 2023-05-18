package com.ssafy.enjoy.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupPlanPathDto {
	int planNo;
	int planSeq;
	int contentId;
	String memo;
}
