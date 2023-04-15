package com.ssafy.model.dao;

import java.sql.*;

import com.ssafy.board.model.*;
import com.ssafy.sample.util.*;

public class MemberDaoImpl implements MemberDao {

	private static MemberDao memberDao = new MemberDaoImpl();
	private DBUtil dbUtil;

	private MemberDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static MemberDao getMemberDao() {
		return memberDao;
	}

	@Override
	public boolean idCheck(String id) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from members ");
			sql.append(" where user_id=? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} finally {
			dbUtil.close(conn);
			dbUtil.close(pstmt);
			dbUtil.close(rs);
		}

		return false;
	}

	@Override
	public MemberDto login(String id, String pw) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from members ");
			sql.append(" where user_id=? and user_password=? ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return new MemberDto(id, pw);
			}

		} finally {
			dbUtil.close(conn);
			dbUtil.close(pstmt);
			dbUtil.close(rs);
		}

		return null;
	}

	@Override
	public void memberReg(MemberDto m) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into members (user_id, user_name, user_password, email_id, email_domain)"
					+ " values(?,?,?,?,?) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getPw());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getDomain());

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(conn);
			dbUtil.close(pstmt);
		}
	}

	@Override
	public void memberDelete(String id) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from members " + " where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(conn);
			dbUtil.close(pstmt);
		}

	}

	@Override
	public void memberUpdate(MemberDto m) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update  members set user_name=?, user_password=?, email_id=?, email_domain=? "
					+ " where user_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getPw());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getDomain());
			pstmt.setString(5, m.getId());

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(conn);
			dbUtil.close(pstmt);
		}
	}

	@Override
	public MemberDto getInfo(String id) throws Exception {
		Connection conn = null;
		ResultSet rs = null;
		MemberDto memberDto = null;
		try {
			conn = dbUtil.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select user_name, user_password, email_id, email_domain from members ");
			sql.append(" where user_id=?");
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberDto = new MemberDto(rs.getString(1), id, rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} finally {
			dbUtil.close(rs);
			dbUtil.close(conn);
		}
		return memberDto;
	}

}
