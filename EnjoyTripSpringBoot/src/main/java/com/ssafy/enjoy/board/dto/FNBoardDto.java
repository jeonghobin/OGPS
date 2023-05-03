package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FNBoardDto {
	int article_no;
	String user_id;
	String subject;
	String content;
	int hit;
	String register_time;
}
