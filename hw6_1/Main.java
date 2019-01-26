package hw6_1;

/*
 * �ۼ���: ������
 * �ۼ���: 2018.12.05.
 * ����: [hw6_1] JDBC
 */

import java.sql.*;

public class Main {

	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/employees?characterEncoding=UTF-8&serverTimezone=UTC"; // �����ͺ��̽� ����
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("�����ͺ��̽� ������ ...");
			con = DriverManager.getConnection(url, "emp_user", "test123"); // ����� ����
			System.out.println("�����ͺ��̽� ���� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹��� ã�� ���߽��ϴ�...");
		} catch (SQLException e) {
			System.out.println("�����ͺ��̽� ���� ����");
		}
		return con;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = makeConnection();
		System.out.println();

		String sql = "SELECT * FROM EMPLOYEES"; // ���� ���
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}
		System.out.println();

		Statement stmt = con.createStatement(); // ���� ����
		sql = "DELETE FROM EMPLOYEES WHERE LAST_NAME = 'GIETZ'";
		if (stmt.executeUpdate(sql) == 1)
			System.out.println("���ڵ� ���� ����");
		else
			System.out.println("���ڵ� ���� ����");
		System.out.println();

		stmt.close();

		sql = "SELECT * FROM EMPLOYEES"; // ���� �����
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
		}

		pstmt.close();

		con.close();

	}

}
