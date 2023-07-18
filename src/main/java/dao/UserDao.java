package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import entity.User;

public class UserDao {

	public Connection getConnection() {			// getConnection(): 데이터베이스 연결을 설정하는 메소드
		Connection conn = null;
		try {
			InitialContext initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/bbs");	// lookup():""에 바인딩된 DataSource 객체를 검색 
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public User getUser(String uid) {
		User user = null;
		Connection conn = getConnection();
		String sql = "select * from users where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						LocalDate.parse(rs.getString(5)),rs.getInt(6), rs.getString(7),rs.getString(8));
			}
			rs.close();pstmt.close();conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
	public void register(User user) {
		Connection conn = getConnection();
		String sql = "insert into users values (?, ?, ?, ?, default, default,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getPwd());
			pstmt.setString(3, user.getUname());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getProfile());
			pstmt.setString(6, user.getAddr());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<User> getUserList(int page){
		List<User> list = new ArrayList<User>();
		int offset = (page-1)*10;
		
		Connection conn = getConnection();
		String sql ="select * from users where isDeleted=0 order by regDate desc, uid limit 10 offset ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offset);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						LocalDate.parse(rs.getString(5)),rs.getInt(6), rs.getString(7),rs.getString(8));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getUserCount() {
		int count = 0;
		Connection conn = getConnection();
		String sql = "select count(uid) as c from users where isDeleted=0";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();pstmt.close();conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public void deleteUser(String uid) {
		Connection conn = getConnection();
		String sql = "UPDATE users SET isDeleted=1 WHERE uid=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, uid);
			
			pStmt.executeUpdate();
			pStmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void updateUser(User user) {
		Connection conn = getConnection();
		String sql = "update users set uname=?, email=?, profile=?, addr=? where uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUname());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getProfile());
			pstmt.setString(4, user.getAddr());
			pstmt.setString(5, user.getUid());
			
			pstmt.executeUpdate();
			pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
