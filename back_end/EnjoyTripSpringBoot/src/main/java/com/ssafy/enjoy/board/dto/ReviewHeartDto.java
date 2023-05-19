package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewHeartDto {
	int articleNo;
	String userId;
	int state;
}
