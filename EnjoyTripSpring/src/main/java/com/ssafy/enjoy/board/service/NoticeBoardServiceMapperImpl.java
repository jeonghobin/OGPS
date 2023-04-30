package com.ssafy.enjoy.board.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.board.dto.FNBoardDto;
import com.ssafy.enjoy.board.repository.NoticeBoardRepository;
import com.ssafy.enjoy.util.PageNavigation;
import com.ssafy.enjoy.util.SizeConstant;

@Service("NoticeBoardServiceMapperImpl")
public class NoticeBoardServiceMapperImpl implements NoticeBoardService {
	
	@Autowired
	SqlSession session;
	
	
	@Override
	public void write(FNBoardDto bdto) throws SQLException {
		// TODO Auto-generated method stub
		session.getMapper(NoticeBoardRepository.class).write(bdto);
	}

	@Override
	public List<FNBoardDto> list(Map<String, String> map) throws SQLException {
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

		return session.getMapper(NoticeBoardRepository.class).list(param);
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
		int totalCount = session.getMapper(NoticeBoardRepository.class).getTotalArticleCount(param);
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
	public FNBoardDto getnotice(int articleNo) throws SQLException {
		updateHit(articleNo);
		return session.getMapper(NoticeBoardRepository.class).getnotice(articleNo);
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		// TODO Auto-generated method stub
		session.getMapper(NoticeBoardRepository.class).updateHit(articleNo);
	}

	@Override
	public void modify(FNBoardDto boardDto) throws SQLException {
		session.getMapper(NoticeBoardRepository.class).modify(boardDto);

	}

	@Override
	public void delete(int articleNo) throws SQLException {
		// TODO Auto-generated method stub
		session.getMapper(NoticeBoardRepository.class).delete(articleNo);
	}

}
