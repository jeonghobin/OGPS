package com.ssafy.sample.controller;

import java.io.*;
import java.security.NoSuchAlgorithmException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.ssafy.board.model.*;
import com.ssafy.model.service.*;
import com.ssafy.sample.util.SHA256;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;

	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
//		String action = request.getParameter("action");

		String path = "";
		if ("/login".equals(action)) {
			path = login(request, response);
			redirect(request, response, path);
		} else if ("/logout".equals(action)) {
			path = logout(request, response);
			redirect(request, response, path);
		} else if ("/idcheck".equals(action)) {
			String id = request.getParameter("id");
			boolean idCheck;
			try {
				idCheck = memberService.idCheck(id);
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				if (idCheck)
					out.print("ok");
				else
					out.print("no");
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/reg".equals(action)) {
			path = reg(request, response);
			redirect(request, response, path);
		} else if ("/delete".equals(action)) {
			path = delete(request, response);
			redirect(request, response, path);
		} else if ("/update".equals(action)) {
			path = update(request, response);
			redirect(request, response, path);
		} else {
			redirect(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	private String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("login-user-id");
		String userPwd = request.getParameter("login-password");
		
		try {
			MemberDto memberDto = memberService.login(userId, SHA(userPwd));
			if (memberDto != null) {
//				session 설정
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", memberDto);

				MemberDto dto = memberService.getInfo(userId);
				session.setAttribute("owninfo", dto);

//				cookie 설정
				String idsave = request.getParameter("saveid");
				if ("ok".equals(idsave)) { // 아이디 저장을 체크 했다면.
					Cookie cookie = new Cookie("ssafy_id", userId);
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40); // 40년간 저장.
					response.addCookie(cookie);
				} else { // 아이디 저장을 해제 했다면.
					Cookie cookies[] = request.getCookies();
					if (cookies != null) {
						for (Cookie cookie : cookies) {
							if ("ssafy_id".equals(cookie.getName())) {
								cookie.setMaxAge(0);
								response.addCookie(cookie);
								break;
							}
						}
					}
				}
				return "/index.jsp";
			} else {
				return "/error/alert.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "로그인 오류");
			return "/error/error.jsp";
		}

	}

	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		session.removeAttribute("userinfo");
		session.invalidate();
		return "";
	}

	private String reg(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("user-id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String domain = request.getParameter("domain");

		try {
			memberService.memberReg(new MemberDto(name, id, SHA(pw), email, domain));
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 오류1");
			request.setAttribute("msg", "회원가입 오류");
			return "/error/error.jsp";
		}
	}

	private String update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = request.getParameter("user-id");
		String pw = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String domain = request.getParameter("domain");
		try {
			memberService.memberUpdate(new MemberDto(name, id, SHA(pw), email, domain));

			HttpSession session = request.getSession();
			session.setAttribute("owninfo", new MemberDto(name, id, SHA(pw), email, domain));
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원정보수정 오류");
			return "/error/error.jsp";
		}
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		String id = memberDto.getId();

		try {
			memberService.memberDelete(id);
			session.invalidate();
			return "/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "회원탈퇴 오류");
			return "/error/error.jsp";
		}
	}
	
	private String SHA(String pw) {
		// SHA256으로 암호화
		SHA256 sha256 = new SHA256();
		try {
			pw = sha256.encrypt(pw);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		return pw;
	}
	

}