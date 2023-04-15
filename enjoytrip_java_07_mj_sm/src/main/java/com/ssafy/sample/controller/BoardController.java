package com.ssafy.sample.controller;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.ssafy.board.model.*;
import com.ssafy.model.service.*;
import com.ssafy.sample.util.*;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private int pgno;
	private String key;
	private String word;
	private String queryString; 

	private BoardService boardService;
	private MemberService memberService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		boardService = BoardServiceImpl.getBoardService();
		memberService = MemberServiceImpl.getMemberService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		pgno = ParameterCheck.notNumberToOne(request.getParameter("pgno"));
		key = ParameterCheck.nullToBlank(request.getParameter("key"));
		word = ParameterCheck.nullToBlank(request.getParameter("word"));
		queryString = "?action=information&pgno=" + pgno + "&key=" + key + "&word=" + URLEncoder.encode(word, "utf-8");

		String path = "";
		if ("information".equals(action)) {
			path = information(request, response);
			forward(request, response, path);
		} else if ("write".equals(action)) {
			path = write(request, response);
			redirect(request, response, path);
		} else if ("mvview".equals(action)) {
			path = mvView(request, response);
			forward(request, response, path);
		} else if ("view".equals(action)) {
			path = view(request, response);
			forward(request, response, path);
		} else if ("delete".equals(action)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else if ("mvmodify".equals(action)) {
			path = mvModify(request, response);
			forward(request, response, path);
		} else if ("modify".equals(action)) {
			path = modify(request, response);
			redirect(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private String modify(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("userInfo");
		try {
			if (!memberService.idCheck(mdto.getId())) {
				BoardDto dto = new BoardDto();
				dto.setArticleNo(Integer.parseInt(request.getParameter("postno")));
				dto.setSubject(request.getParameter("title"));
				dto.setContent(request.getParameter("context"));
				boardService.modifyPost(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/board?action=information&pgno=1&key=&word=";
	}

	private String mvModify(HttpServletRequest request, HttpServletResponse response) {
		int postNo = Integer.parseInt(request.getParameter("postno"));
		try {
			BoardDto dto = boardService.getPost(postNo);
			request.setAttribute("post", dto);

			return "/board/modify.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "게시글 조회 오류");
			return "/error/error.jsp";
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int postno = Integer.parseInt(request.getParameter("postno"));
		try {
			boardService.deletePost(postno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/board?action=information&pgno=1&key=&word=";
	}

	private String view(HttpServletRequest request, HttpServletResponse response) {
		BoardDto boardDto = (BoardDto) request.getAttribute("post");
		return "/board/view.jsp";
	}

	private String mvView(HttpServletRequest request, HttpServletResponse response) {
		int postNo = Integer.parseInt(request.getParameter("postno"));
		try {
			boardService.updateHit(postNo);
			BoardDto dto = boardService.getPost(postNo);
			request.setAttribute("post", boardService.getPost(postNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/board?action=view";
	}

	private String write(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("userInfo");
		try {
			if (!memberService.idCheck(mdto.getId())) {
				BoardDto dto = new BoardDto();
				dto.setUserId(mdto.getId());
				dto.setSubject(request.getParameter("title"));
				dto.setContent(request.getParameter("context"));
				boardService.writePost(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/board?action=information&pgno=1&key=&word=";
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	private String information(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pgno", pgno + "");
			map.put("key", key);
			map.put("word", word);

//			List<BoardDto> list = boardService.listPost(map);
			List<BoardDto> list = QuickSorter.quickSort(boardService.listPost(map));
			request.setAttribute("posts", list);

			PageNavigation pageNavigation = boardService.makePageNavigation(map);
			request.setAttribute("navigation", pageNavigation);

			return "/board/information.jsp" + queryString;
		} catch (Exception e) {
			e.printStackTrace();
			return "/error/error.jsp";
		}
	}
}
