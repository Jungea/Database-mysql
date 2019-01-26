package hw6_1;

/*
 * 작성자: 정은애
 * 작성일: 2018.12.05.
 * 내용: [hw6_1] JDBC
 */

import java.sql.*;

public class Main {

	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/employees?characterEncoding=UTF-8&serverTimezone=UTC"; // 데이터베이스 변경
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("데이터베이스 연결중 ...");
			con = DriverManager.getConnection(url, "emp_user", "test123"); // 사용자 변경
			System.out.println("데이터베이스 연결 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못했습니다...");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
		}
		return con;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = makeConnection();
		System.out.println();

		String sql = "SELECT * FROM EMPLOYEES"; // 직원 출력
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}
		System.out.println();

		Statement stmt = con.createStatement(); // 직원 삭제
		sql = "DELETE FROM EMPLOYEES WHERE LAST_NAME = 'GIETZ'";
		if (stmt.executeUpdate(sql) == 1)
			System.out.println("레코드 삭제 성공");
		else
			System.out.println("레코드 삭제 실패");
		System.out.println();

		stmt.close();

		sql = "SELECT * FROM EMPLOYEES"; // 직원 재출력
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}

		pstmt.close();

		con.close();

	}

}
