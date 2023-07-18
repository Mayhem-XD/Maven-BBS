package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

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
	
}