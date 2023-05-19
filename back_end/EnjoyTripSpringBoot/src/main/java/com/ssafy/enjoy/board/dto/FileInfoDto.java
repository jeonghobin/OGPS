package com.ssafy.enjoy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoDto {
	int idx;
	int articleNo;
	String saveFolder;
	String originalFile;
	String saveFile;
}
