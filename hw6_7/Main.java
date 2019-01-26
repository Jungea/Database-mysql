package hw6_7;

/*
 * 작성자: 정은애
 * 작성일: 2018.12.19.
 * 내용: 프로그램 구현
 */

import java.sql.*;
import java.util.Scanner;

public class Main {
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/projects?characterEncoding=UTF-8&serverTimezone=UTC"; // 데이터베이스 변경
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("데이터베이스 연결중 ...");
			con = DriverManager.getConnection(url, "puser", "1234"); // 사용자 변경
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
		System.out.println("hw6_7: 정은애 \n");

		Connection con = makeConnection();

		Scanner scan = new Scanner(System.in);
		System.out.println("다음 중 하나를 선택하시오.");
		String data1;
		String data2;
		String data3;
		String data4;
		String mainMenu;
		String subMenu;
		String menu;
		int check;

		String sql;
		Statement stmt = con.createStatement();
		ResultSet rs;

		do {
			System.out.print("\n---------------------------------------------");
			System.out.print("\na.학과관리  b.학생관리  c.교수관리  d.상담관리  e.종료  ---> ");

			mainMenu = scan.next();
			switch (mainMenu) {
			case "a":
				// 학과 정보 전체 출력
				sql = "SELECT * FROM department";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (null == rs.getString(3))
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2));
					else
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3));
				}

				do {
					System.out.print("\n1.학과정보입력  2.학과삭제  3.메인메뉴돌아가기 ---> ");
					subMenu = scan.next();
					switch (subMenu) {
					case "1":
						// 학과 정보 입력
						System.out.println("<학과정보 입력>");
						System.out.print("학과 고유번호: ");
						data1 = scan.next();
						System.out.print("학과 이름: ");
						data2 = scan.next();
						System.out.print("학과 사무실 번호 (입력을 안하려면 -1을 입력하세요.): ");
						data3 = scan.next();
						if (data3.equals("-1"))
							sql = "INSERT INTO department (deptID, deptName) VALUES (" + data1 + ", '" + data2 + "')";
						else
							sql = "INSERT INTO department VALUES (" + data1 + ", '" + data2 + "', " + data3 + ")";

						try {
							if (stmt.executeUpdate(sql) == 1)
								System.out.println("학과 추가 성공");
							else
								System.out.println("학과 추가 실패");

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("학과 추가 실패 : 이미 존재하는 고유번호/학과이름 입니다.");
						}
						System.out.println();

						sql = "SELECT * FROM department";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3));
						}
						break;

					case "2":
						System.out.print("삭제할 학과 고유 번호: ");
						data1 = scan.next();
						sql = "DELETE FROM department WHERE deptID = " + data1;

						try {
							if (stmt.executeUpdate(sql) == 1)
								System.out.println("학과 삭제 성공");
							else
								System.out.println("학과 삭제 실패 : 존재하지 않는 학과 고유 번호입니다.");
							System.out.println();
						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("학과 삭제 실패 : 해당 학과 학생이나 교수가 남아 있습니다.");
						}

						sql = "SELECT * FROM DEPARTMENT";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3));
						}
						break;

					case "3":
						System.out.println("메인메뉴로 돌아갑니다.");
						break;
					default:
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
					}
				} while (!subMenu.equals("3"));
				break;

			case "b":
				sql = "SELECT * FROM student";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (null == rs.getString(3))
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(4));
					else
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3) + " ㅣ  "
								+ rs.getString(4));
				}
				do {
					System.out.print("\n1.학생정보입력  2.학생삭제  3.메인메뉴돌아가기 ---> ");
					subMenu = scan.next();
					switch (subMenu) {
					case "1":
						System.out.println("<학생정보 입력>");
						System.out.print("학번: ");
						data1 = scan.next();
						System.out.print("학생 이름: ");
						data2 = scan.next();
						System.out.print("학생 핸드폰 번호 (입력을 안하려면 -1을 입력하세요.): ");
						data3 = scan.next();
						System.out.print("전공 학과 고유 번호: ");
						data4 = scan.next();
						try {
							if (data3.equals("-1"))
								sql = "INSERT INTO student (stdID, stdName, deptID) VALUES (" + data1 + ", '" + data2
										+ "', " + data4 + ")";
							else
								sql = "INSERT INTO student VALUES (" + data1 + ", '" + data2 + "', " + data3 + ", "
										+ data4 + ")";

							if (stmt.executeUpdate(sql) == 1)
								System.out.println("학생 추가 성공");
							else
								System.out.println("학생 추가 실패");
							System.out.println();

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("학생 추가 실패 : 존재하지 않는 학과 고유번호이거나 이미 존재하는 학번/핸드폰 번호입니다. \n");
						}

						sql = "SELECT * FROM student";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3)
										+ " ㅣ  " + rs.getString(4));
						}
						break;

					case "2":
						System.out.print("삭제할 학생 학번: ");
						data1 = scan.next();
						sql = "DELETE FROM student WHERE stdID = " + data1;
						if (stmt.executeUpdate(sql) == 1)
							System.out.println("학생 삭제 성공");
						else
							System.out.println("학생 삭제 실패 : 존재하지 않는 학번입니다.");
						System.out.println();

						sql = "SELECT * FROM student";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3)
										+ " ㅣ  " + rs.getString(4));
						}
						break;

					case "3":
						System.out.println("메인메뉴로 돌아갑니다.");
						break;

					default:
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
					}
				} while (!subMenu.equals("3"));
				break;

			case "c":
				sql = "SELECT * FROM professor";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (null == rs.getString(3))
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(4));
					else
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3) + " ㅣ  "
								+ rs.getString(4));
				}

				do {
					System.out.print("\n1.교수정보입력  2.교수삭제  3.메인메뉴돌아가기 ---> ");
					subMenu = scan.next();
					switch (subMenu) {
					case "1":
						System.out.println("<교수정보 입력>");
						System.out.print("교수 고유번호: ");
						data1 = scan.next();
						System.out.print("교수 이름: ");
						data2 = scan.next();
						System.out.print("교수 핸드폰 번호 (입력을 안하려면 -1을 입력하세요.): ");
						data3 = scan.next();
						System.out.print("소속 학과 고유 번호: ");
						data4 = scan.next();
						try {
							if (data3.equals("-1"))
								sql = "INSERT INTO professor (proID, proName, deptID) VALUES (" + data1 + ", '" + data2
										+ "', " + data4 + ")";
							else
								sql = "INSERT INTO professor VALUES (" + data1 + ", '" + data2 + "', " + data3 + ", "
										+ data4 + ")";

							if (stmt.executeUpdate(sql) == 1)
								System.out.println("교수 추가 성공");
							else
								System.out.println("교수 추가 실패");
							System.out.println();

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("교수 추가 실패 : 존재하지 않는 학과 고유번호이거나 이미 존재하는 교수 고유번호/핸드폰 번호입니다. \n");
						}

						sql = "SELECT * FROM professor";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3)
										+ " ㅣ  " + rs.getString(4));
						}
						break;

					case "2":
						System.out.print("삭제할 교수 고유번호: ");
						data1 = scan.next();
						sql = "DELETE FROM professor WHERE proID = " + data1;
						if (stmt.executeUpdate(sql) == 1)
							System.out.println("교수 삭제 성공");
						else
							System.out.println("교수 삭제 실패 : 존재하지 않는 교수 고유 번호입니다.");
						System.out.println();

						sql = "SELECT * FROM professor";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3)
										+ " ㅣ  " + rs.getString(4));
						}
						break;

					case "3":
						System.out.println("메인메뉴로 돌아갑니다.");
						break;

					default:
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
					}
				} while (!subMenu.equals("3"));

				break;

			case "d":
				do {
					System.out.print("\n1.학번입력  2.교수입력 ---> ");
					subMenu = scan.next();
					if (subMenu.equals("1")) {
						System.out.print("학번 입력: ");
						data1 = scan.next();

						sql = "SELECT * FROM student WHERE stdID = " + data1;

						rs = stmt.executeQuery(sql);
						check = 0;
						while (rs.next()) {
							check++;
						}
						if (check == 0) {
							System.out.println("입력 오류 : 존재하지 않는 학번입니다.");
							continue;
						}

						sql = "SELECT * FROM reservation WHERE stdID = " + data1;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3)
									+ " ㅣ  " + rs.getString(4) + " ㅣ " + rs.getString(5) + " ㅣ " + rs.getString(6)
									+ " ㅣ  " + rs.getString(7));
						}

						do {
							System.out.print("\n1.상담신청  2.상담취소  3.상담기록  4.상담 상세정보표시  5.메인메뉴로 돌아가기  ---> ");
							menu = scan.next();

							switch (menu) {
							case "1":
								System.out.print("상담 신청 교수 고유번호: ");
								data2 = scan.next();
								System.out.print("상담 요청 날짜(YYYY-MM-DD): ");
								data3 = scan.next();
								System.out.print("상담 주제: ");
								data4 = scan.next();

								sql = "INSERT INTO reservation (stdID, proID, resTime, resTitle) VALUES (" + data1
										+ ", " + data2 + ", '" + data3 + "', '" + data4 + "')";

								try {
									if (stmt.executeUpdate(sql) == 1)
										System.out.println("상담 신청 성공");
									else
										System.out.println("상담 신청 실패");
									System.out.println();

								} catch (SQLIntegrityConstraintViolationException e) {
									System.out.println("상담 신청 실패 : 존재하지 않는 교수 고유번호입니다.");
								}

								sql = "SELECT * FROM reservation WHERE stdID = " + data1;
								rs = stmt.executeQuery(sql);
								while (rs.next()) {
									System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ "
											+ rs.getString(3) + " ㅣ  " + rs.getString(4) + " ㅣ " + rs.getString(5)
											+ " ㅣ " + rs.getString(6) + " ㅣ  " + rs.getString(7));
								}
								break;
							case "2":
								System.out.print("취소할 상담 선택: ");
								data1 = scan.next();
								System.out.println("입력 <" + data1 + ">");
								System.out.println("취소할 상담 선택");
								break;
							case "3":
								System.out.print("상담 선택: ");
								data1 = scan.next();
								System.out.print("상담 기록 입력: ");
								data2 = scan.next();
								System.out.println("입력 <" + data1 + ", " + data2 + ">");
								break;
							case "4":
								System.out.println("승인된 상담에 대한 정보 표시");
								System.out.print("상담 선택: ");
								data1 = scan.next();
								System.out.println("입력 <" + data1 + ">");
								System.out.println("특정 상담에 대한 내용 표시");
								break;
							case "5":
								System.out.println("메인메뉴로 돌아갑니다.");
								break;
							default:
								System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
							}
						} while (!menu.equals("5"));

						if (menu.equals("5"))
							break;

					} else if (subMenu.equals("2")) {
						System.out.print("교수 고유번호 입력: ");
						data1 = scan.next();

						sql = "SELECT * FROM professor WHERE proID = " + data1;

						rs = stmt.executeQuery(sql);
						check = 0;
						while (rs.next()) {
							check++;
						}
						if (check == 0) {
							System.out.println("입력 오류 : 존재하지 않는 교수 고유번호입니다.");
							continue;
						}

						sql = "SELECT * FROM reservation WHERE proID = " + data1;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3)
									+ " ㅣ  " + rs.getString(4) + " ㅣ " + rs.getString(5) + " ㅣ " + rs.getString(6)
									+ " ㅣ  " + rs.getString(7));
						}

						do {
							System.out.print("\n1.승인할상담선택  2.상담상세정보표시  3.메인메뉴로 돌아가기  ---> ");
							menu = scan.next();

							switch (menu) {
							case "1":
								System.out.print("승인할 상담 번호: ");
								data2 = scan.next();
								sql = "UPDATE reservation SET approve = 1 where resID =" + data2 + " and proID = "
										+ data1;
								if (stmt.executeUpdate(sql) == 1)
									System.out.println("상담 신청  승인 성공");
								else
									System.out.println("상담 신청 승인 실패");
								System.out.println();

								sql = "SELECT * FROM reservation WHERE proID = " + data1;
								rs = stmt.executeQuery(sql);
								while (rs.next()) {
									System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ "
											+ rs.getString(3) + " ㅣ  " + rs.getString(4) + " ㅣ " + rs.getString(5)
											+ " ㅣ " + rs.getString(6) + " ㅣ  " + rs.getString(7));
								}

								break;
							case "2":
								System.out.println("담당 상담에 대한 정보 표시");
								System.out.print("상담 선택: ");
								data1 = scan.next();
								System.out.println("입력 <" + data1 + ">");
								System.out.println("특정 상담에 대한 내용 표시");
								break;
							case "3":
								System.out.println("메인메뉴로 돌아갑니다.");
								break;
							default:
								System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
							}
						} while (!menu.equals("3"));

						if (menu.equals("3"))
							break;
					} else
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
				} while (!(subMenu.equals("1") || subMenu.equals("2")));

				break;

			case "e":
				System.out.println("종료합니다.");
				break;

			default:
				System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
			}
		} while (!mainMenu.equals("e"));

		stmt.close();
		con.close();

	}

}
