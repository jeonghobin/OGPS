package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewComDto {
	int commentNo;
	int articleNo;
	String userId;
	String comment;
	String memoTime;
}
