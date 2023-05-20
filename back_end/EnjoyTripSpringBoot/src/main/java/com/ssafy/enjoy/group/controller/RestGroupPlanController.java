package com.ssafy.enjoy.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoy.attraction.dto.attractionDto;
import com.ssafy.enjoy.group.dto.GroupCommentDto;
import com.ssafy.enjoy.group.dto.GroupDto;
import com.ssafy.enjoy.group.dto.GroupMemberDto;
import com.ssafy.enjoy.group.dto.GroupPlanDto;
import com.ssafy.enjoy.group.dto.GroupPlanPathDto;
import com.ssafy.enjoy.group.service.GroupPlanService;

@RestController
@RequestMapping("/api")
public class RestGroupPlanController {

	@Autowired
	@Qualifier("GroupPlanServiceMapperImpl")
	GroupPlanService service;
	
	@GetMapping("/groupplan/{groupNo}")
	public ResponseEntity<Map<String,Object>> getGroup(@PathVariable("groupNo") int groupNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		GroupDto group = service.getDetailGroup(groupNo);
		List<GroupPlanDto> gplist = service.getGroupPlan(groupNo);
		List<GroupCommentDto> gclist = service.getGroupComment(groupNo);
		List<GroupMemberDto> gmlist = service.getGroupMember(groupNo);
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("group", group);
		rsmap.put("plans", gplist);
		rsmap.put("comments",gclist);
		rsmap.put("members",gmlist);
		rsmap.put("rsmsg", "조회 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/groupplan/{groupNo}/{planNo}")
	public ResponseEntity<Map<String,Object>> viewGroupPlan(@PathVariable("groupNo") int groupNo
		,@PathVariable("planNo") int planNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		GroupPlanDto gdto = service.getDetailPlan(planNo);
		List<attractionDto> alist = service.getPlanPath(planNo);
		List<String> mlist = service.getPlanMemo(planNo);
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("plan", gdto);
		rsmap.put("paths", alist);
		rsmap.put("memos", mlist);
		rsmap.put("rsmsg", "조회 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/groupplan/{groupNo}/{planNo}/{userId}")
	public ResponseEntity<Map<String,Object>> likeGroupPlan(@PathVariable("groupNo") int groupNo
		,@PathVariable("planNo") int planNo,@PathVariable("userId") String userId) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		service.likeGroupPlan(planNo,userId);
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg", "좋아요 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	@DeleteMapping("/groupplan/{groupNo}/{planNo}")
	public ResponseEntity<Map<String,Object>> deleteGroupPlan(@PathVariable("groupNo") int groupNo,
			@PathVariable("planNo") int planNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		service.deletePlan(planNo);
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg", "삭제 성공");
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/groupplan/{groupNo}")
	public ResponseEntity<Map<String,Object>> writeGroupPlan(@RequestBody Map<String,Object> map,@PathVariable("groupNo") int groupNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		GroupPlanDto gdto = new GroupPlanDto();
		gdto.setGroupNo(Integer.parseInt((String)map.get("groupNo")));
		gdto.setSubject((String)map.get("subject"));
		gdto.setUserId((String)map.get("userId"));
		List<Map<String,String>> plist = (List<Map<String,String>>)map.get("paths");
		
		service.writeGroupPlan(gdto,plist);
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg", "작성 성공");
		
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@PutMapping("/groupplan/{groupNo}/{planNo}")
	public ResponseEntity<Map<String,Object>> updateGroupPlan(@RequestBody Map<String,Object> map
			,@PathVariable("groupNo") int groupNo, @PathVariable("planNo") int planNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		GroupPlanDto gdto = new GroupPlanDto();
		gdto.setGroupNo(Integer.parseInt((String)map.get("groupNo")));
		gdto.setSubject((String)map.get("subject"));
		gdto.setUserId((String)map.get("userId"));
		List<Map<String,String>> plist = (List<Map<String,String>>)map.get("paths");		
		service.updateGroupPlan(gdto,plist,planNo);
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg", "수정 성공");
		
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/groupplan/comment/{groupNo}")
	public ResponseEntity<Map<String,Object>> writeGroupComment(@RequestBody Map<String,Object> map,@PathVariable("groupNo") int groupNo) throws Exception{
		ResponseEntity<Map<String,Object>> entity = null;
		GroupCommentDto gcdto = new GroupCommentDto();
		gcdto.setGroupNo(groupNo);
		gcdto.setUserId((String)map.get("userId"));
		gcdto.setComment((String)map.get("comment"));
		
		service.writeGroupComment(gcdto);
		
		Map<String,Object> rsmap = new HashMap<String, Object>();
		rsmap.put("rsmsg", "작성 성공");
		
		entity = new ResponseEntity<Map<String,Object>>(rsmap,HttpStatus.OK);
		return entity;
	}
	
}
