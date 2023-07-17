package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import entity.User;

public class UserDao {

	public Connection getConnection() {			// getConnection(): 데이터베이스 연결을 설정하는 메소드
		Connection conn = null;
		try {
			InitialContext initContext = new InitialContext();
			DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/project");	// lookup():""에 바인딩된 DataSource 객체를 검색 
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
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
