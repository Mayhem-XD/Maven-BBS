# Java

<h1>자바 웹 강좌</h1>

<p>
빅데이터 머신러닝을 활용한 분석 및 UI 전문가 과정

연세it미래교육관에서 2023년 부터 2023년8월2일까지 진행됩니다.
</p>

<h3>개발 도구</h3>

>  ![img_61 (2)](https://github.com/Mayhem-XD/PyCo/assets/116787370/9945e567-f717-4da7-9db7-f4147960d7d9) ![img_61](https://github.com/Mayhem-XD/PyCo/assets/116787370/315f7972-a2df-4f8d-aeba-b889b50d0c5d)
> 
<hr>
<h2>수정중</h2>

> ext jar 3개
> bcrypt
> tomcat9
> 
<hr>
<br><br><br><br>

~~~ java

public User getUser(String uid) {
		User u = null;
		Connection conn = getConnection();		 // 데이터베이스 연결을 설정
		String sql = "select * from users where uid=? and isDeleted=0";			//  SQL 쿼리를 준비
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);		// 쿼리의 첫 번째 매개변수에 사용자 ID를 설정
			ResultSet rs = pstmt.executeQuery();	// 쿼리를 실행하고 결과를 가져옴		
			while (rs.next()) {                   // ResultSet 객체는 DB쿼리의 결과 테이블 형태의 데이터	
				u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							 LocalDate.parse(rs.getString(5)), rs.getInt(6));
			}
			rs.close(); pstmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

// PreparedStatement는 쿼리에 매개변수를 사용가능
// Statement는 쿼리에 매개변수를 사용불가
// PreparedStatement는 쿼리가 실행되기 전에 컴파일
// Statement는 쿼리가 실행될 때마다 컴파일




	public List<User> getUserList() {
		List<User> list = new ArrayList<User>();
		Connection conn = getConnection();
		String sql = "select * from users where isDeleted=0 order by regDate desc, uid";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);		
			while (rs.next()) {
				User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							 LocalDate.parse(rs.getString(5)), rs.getInt(6));
				list.add(u);
			}
			rs.close(); stmt.close(); conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

~~~


<h5>.././././</h5>
<hr>




