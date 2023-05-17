package com.ssafy.enjoy.group.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCommentDto {
	int groupNo;
	int commentNo;
	String userId;
	String comment;
	String memoTime;
}
