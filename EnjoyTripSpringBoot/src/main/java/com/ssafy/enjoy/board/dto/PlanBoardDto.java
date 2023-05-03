package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanBoardDto {
	int plan_id;
	String user_id;
	String subject;
	String content;
	int hit;
	String register_time;
	String start_time;
	String end_time;
}
