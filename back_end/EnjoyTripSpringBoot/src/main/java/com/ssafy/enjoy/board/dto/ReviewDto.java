package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
	int articleNo;
	String userId;
	String subject;
	String content;
	int hit;
	String registerTime;
	int heart;
}
