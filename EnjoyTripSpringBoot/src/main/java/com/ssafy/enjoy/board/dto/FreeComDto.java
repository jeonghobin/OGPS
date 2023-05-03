package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeComDto {
	int comment_no;
	int article_no;
	String user_id;
	String comment;
	String memo_time;
}
