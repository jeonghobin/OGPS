package com.ssafy.enjoy.board.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoy.board.dto.ReviewComDto;
import com.ssafy.enjoy.board.dto.ReviewDto;
import com.ssafy.enjoy.board.dto.FileInfoDto;
import com.ssafy.enjoy.board.dto.ReviewHeartDto;
import com.ssafy.enjoy.util.PageNavigation;


public interface ReviewBoardService {

	/** 게시판 **/
	int write(ReviewDto rDto) throws Exception;
	List<ReviewDto> list(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	ReviewDto getnotice(int articleNo) throws Exception;
	void updateHit(int articleNo) throws Exception;
	void modify(ReviewDto rDto) throws Exception;
	void delete(int articleNo) throws Exception;

	/** 댓글 **/
	List<ReviewComDto> comList(int articleNo) throws Exception;
	ReviewComDto comOne(int commentNo) throws Exception;
	void comDelete(int commentNo) throws Exception;
	void comModify(ReviewComDto rcDto) throws Exception;
	void comWrite(ReviewComDto rcDto) throws Exception;
	
	/** 좋아요 **/
	//유저 좋아요 상태
	//유저 좋아요 존재 확인(유저 좋아요 개수)
	//좋아요 총 개수
	//유저 좋아요 추가
	//유저 좋아요 상태 업데이트
	int heartState(ReviewHeartDto rhDto) throws Exception;
	int exitHeart(ReviewHeartDto rhDto) throws Exception;
	int allHeart(int articleNo) throws Exception;
	void addHeart(ReviewHeartDto rhDto) throws Exception;
	void updateHeart(ReviewHeartDto rhDto) throws Exception;
	
	/** 파일업로드 **/
	int getAuto() throws Exception;
	void savefile(FileInfoDto rfDto) throws Exception;
	FileInfoDto[] getFile(int ArticleNo) throws Exception;
	FileInfoDto oneFile(int idx) throws Exception;
	void deletefile(int idx) throws Exception;
	void deleteAll(int articleNo) throws Exception;
	
}
