package com.ssafy.enjoy.board.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.ssafy.enjoy.board.dto.ReviewComDto;
import com.ssafy.enjoy.board.dto.ReviewDto;
import com.ssafy.enjoy.board.dto.FileInfoDto;
import com.ssafy.enjoy.board.dto.ReviewHeartDto;


public interface ReviewBoardRepository {

	/** 게시판 **/
	int write(ReviewDto rDto) throws Exception;
	List<ReviewDto> list(Map<String, Object> map) throws Exception;
	int getTotalArticleCount(Map<String, Object> param) throws Exception;
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
	
}




