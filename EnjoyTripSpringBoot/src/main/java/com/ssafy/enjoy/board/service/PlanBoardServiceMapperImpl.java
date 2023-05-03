package com.ssafy.enjoy.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.board.dto.PlanBoardDto;
import com.ssafy.enjoy.board.repository.PlanBoardRepository;
import com.ssafy.enjoy.util.PageNavigation;
import com.ssafy.enjoy.util.SizeConstant;


@Service("PlanBoardServiceMapperImpl")
public class PlanBoardServiceMapperImpl implements PlanBoardService {
	
	@Autowired
	SqlSession session;
	
	/** 
	 * 게시판
	 **/
	@Override
	public void write(PlanBoardDto pdto, List<String> contentId) throws Exception {
		session.getMapper(PlanBoardRepository.class).write(pdto);
		String a = String.valueOf(session.getMapper(PlanBoardRepository.class).getAuto());
		for(String str : contentId) {
			session.getMapper(PlanBoardRepository.class).writePath(str,a);
		}
	}
	

	@Override
	public List<PlanBoardDto> list(Map<String, String> map) throws Exception {
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

		return session.getMapper(PlanBoardRepository.class).list(param);
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
		int totalCount = session.getMapper(PlanBoardRepository.class).getTotalArticleCount(param);
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
	public void delete(int plan_id) throws Exception {
		session.getMapper(PlanBoardRepository.class).delete(plan_id);
	}
	
	
	@Override
	public void updateHit(int plan_id) throws Exception {
		session.getMapper(PlanBoardRepository.class).updateHit(plan_id);
	}
	
	
	/** 
	 * view 
	 **/
	@Override
	public List<attractionDto> getInfo(String title) throws Exception {
		// TODO Auto-generated method stub
		return session.getMapper(PlanBoardRepository.class).getInfo(title);
	}






}
