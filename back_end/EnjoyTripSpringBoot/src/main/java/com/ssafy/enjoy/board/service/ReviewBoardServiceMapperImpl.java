package com.ssafy.enjoy.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.board.dto.ReviewComDto;
import com.ssafy.enjoy.board.dto.ReviewDto;
import com.ssafy.enjoy.board.dto.FileInfoDto;
import com.ssafy.enjoy.board.dto.ReviewHeartDto;
import com.ssafy.enjoy.board.repository.ReviewBoardRepository;
import com.ssafy.enjoy.group.repository.GroupPlanRepository;
import com.ssafy.enjoy.util.PageNavigation;
import com.ssafy.enjoy.util.SizeConstant;

@Service("ReviewBoardServiceMapperImpl")
public class ReviewBoardServiceMapperImpl implements ReviewBoardService{

	@Autowired
	SqlSession session;
	
	/** board **/
	@Override
	public int write(ReviewDto rDto) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ReviewBoardRepository.class).write(rDto);
		return getAuto();
	}

	@Override
	public List<ReviewDto> list(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if("userid".equals(key))
			key = "b.user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return session.getMapper(ReviewBoardRepository.class).list(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = session.getMapper(ReviewBoardRepository.class).getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public ReviewDto getnotice(int articleNo) throws Exception {
		updateHit(articleNo);
		return session.getMapper(ReviewBoardRepository.class).getnotice(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ReviewBoardRepository.class).updateHit(articleNo);
	}

	@Override
	public void modify(ReviewDto rdto) throws Exception {
		session.getMapper(ReviewBoardRepository.class).modify(rdto);
	}

	@Override
	public void delete(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ReviewBoardRepository.class).delete(articleNo);
	}


	/** comment **/
	@Override
	public List<ReviewComDto> comList(int articleNo) throws Exception {
		return session.getMapper(ReviewBoardRepository.class).comList(articleNo);
	}

	@Override
	public ReviewComDto comOne(int commentNo) throws Exception {
		return session.getMapper(ReviewBoardRepository.class).comOne(commentNo);
	}

	@Override
	public void comDelete(int commentNo) throws Exception {
		session.getMapper(ReviewBoardRepository.class).comDelete(commentNo);
	}

	@Override
	public void comModify(ReviewComDto fcdto) throws Exception {
		session.getMapper(ReviewBoardRepository.class).comModify(fcdto);
	}
	
	@Override
	public void comWrite(ReviewComDto fcdto) throws Exception {
		session.getMapper(ReviewBoardRepository.class).comWrite(fcdto);
	}

	
	/** heart **/
	@Override
	public int heartState(ReviewHeartDto rhDto) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(ReviewBoardRepository.class).heartState(rhDto);
	}

	@Override
	public int allHeart(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(ReviewBoardRepository.class).allHeart(articleNo);
	}

	@Override
	public void addHeart(ReviewHeartDto rhDto) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ReviewBoardRepository.class).addHeart(rhDto);
	}

	@Override
	public void updateHeart(ReviewHeartDto rhDto) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ReviewBoardRepository.class).updateHeart(rhDto);
	}
	
	@Override
	public int exitHeart(ReviewHeartDto rhDto) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(ReviewBoardRepository.class).exitHeart(rhDto);
	}
	

	/** file **/
	@Override
	public void savefile(FileInfoDto rfDto) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ReviewBoardRepository.class).savefile(rfDto);
	}

	@Override
	public void deletefile(int idx) throws Exception {
		// TODO Auto-generated method stub
		session.getMapper(ReviewBoardRepository.class).deletefile(idx);
	}

	@Override
	public int getAuto() throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(ReviewBoardRepository.class).getAuto();
	}

	@Override
	public FileInfoDto[] getFile(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(ReviewBoardRepository.class).getFile(articleNo);
	}
	
	@Override
	public FileInfoDto oneFile(int idx) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(ReviewBoardRepository.class).oneFile(idx);
	}

	
}
