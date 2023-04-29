package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeCommentDto {
	int comment_no;
	int article_no;
	String user_id;
	String comment;
	StringBuffer memo_time;
}
