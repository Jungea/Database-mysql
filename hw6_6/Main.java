package hw6_6;

/*
 * 작성자: 정은애
 * 작성일: 2018.12.12.
 * 내용: 학과 관리 메뉴 완성
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
		System.out.println("hw6_6: 정은애 \n");

		Connection con = makeConnection();

		Scanner scan = new Scanner(System.in);
		System.out.println("다음 중 하나를 선택하시오.");
		String data1;
		String data2;
		String data3;
		String mainMenu;
		int subMenu;
		int menu;

		String sql;
		Statement stmt = con.createStatement();
		ResultSet rs;

		do {
			System.out.print("\n---------------------------------------------");
			System.out.print("\na)학과관리  b)학생관리  c)교수관리  d)상담관리  e)종료  ---> ");

			mainMenu = scan.next();
			switch (mainMenu) {
			case "a":
				sql = "SELECT * FROM DEPARTMENT";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (null == rs.getString(3))
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2));
					else
						System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3));
				}

				do {
					System.out.print("\n1)학과정보입력  2)학과삭제  3)메인메뉴돌아가기 ---> ");
					subMenu = scan.nextInt();
					switch (subMenu) {
					case 1:
						System.out.println("<학과정보 입력>");
						System.out.print("학과 고유번호: ");
						data1 = scan.next();
						System.out.print("학과 이름: ");
						data2 = scan.next();
						System.out.print("학과 사무실 번호 (입력을 안하려면 -1을 입력하세요.): ");
						data3 = scan.next();
						if (data3.equals("-1"))
							sql = "INSERT INTO department VALUES ('" + data1 + "', '" + data2 + "', NULL)";
						else
							sql = "INSERT INTO department VALUES ('" + data1 + "', '" + data2 + "', '" + data3 + "')";

						try {
							if (stmt.executeUpdate(sql) == 1)
								System.out.println("학과 추가 성공");
							else
								System.out.println("학과 추가 실패");
						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("이미 존재하는 고유번호입니다.");
						}
						System.out.println();

						sql = "SELECT * FROM DEPARTMENT";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3));
						}
						break;

					case 2:
						System.out.print("삭제할 학과 고유 번호: ");
						data1 = scan.next();
						sql = "DELETE FROM department WHERE deptID = " + data1 + "";
						if (stmt.executeUpdate(sql) == 1)
							System.out.println("학과 삭제 성공");
						else
							System.out.println("학과 삭제 실패 : 존재하지 않는 학과 고유 번호입니다.");
						System.out.println();

						sql = "SELECT * FROM DEPARTMENT";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2));
							else
								System.out.println(rs.getString(1) + " ㅣ " + rs.getString(2) + " ㅣ " + rs.getString(3));
						}
						break;
					case 3:
						System.out.println("메인메뉴로 돌아갑니다.");
						break;
					default:
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
					}
				} while (!(subMenu == 3));

				break;

			case "b":
				System.out.println("전체 리스트 출력");
				do {
					System.out.print("\n1)학생정보입력  2)학생삭제  3)메인메뉴돌아가기 ---> ");
					subMenu = scan.nextInt();
					switch (subMenu) {
					case 1:
						System.out.print("학과 선택: ");
						data1 = scan.next();
						System.out.print("학생정보 입력: ");
						data2 = scan.next();
						System.out.println("입력 <" + data1 + ", " + data2 + ">");
						System.out.println("전체 리스트 출력");
						break;
					case 2:
						System.out.print("삭제할 학생: ");
						data1 = scan.next();
						System.out.println("입력 <" + data1 + ">");
						System.out.println("전체 리스트 출력");
						break;
					case 3:
						System.out.println("메인메뉴로 돌아갑니다.");
						break;
					default:
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
					}
				} while (!(subMenu == 3));

				break;

			case "c":
				System.out.println("전체 리스트 출력");
				do {
					System.out.print("\n1)교수정보입력  2)교수삭제  3)메인메뉴돌아가기 ---> ");
					subMenu = scan.nextInt();
					switch (subMenu) {
					case 1:
						System.out.print("학과 선택: ");
						data1 = scan.next();
						System.out.print("교수정보 입력: ");
						data2 = scan.next();
						System.out.println("입력 <" + data1 + ", " + data2 + ">");
						System.out.println("전체 리스트 출력");
						break;
					case 2:
						System.out.print("삭제할 교수: ");
						data1 = scan.next();
						System.out.println("입력 <" + data1 + ">");
						System.out.println("전체 리스트 출력");
						break;
					case 3:
						System.out.println("메인메뉴로 돌아갑니다.");
						break;
					default:
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
					}
				} while (!(subMenu == 3));

				break;

			case "d":
				do {
					System.out.print("\n1)학번입력  2)교수입력 ---> ");
					subMenu = scan.nextInt();
					if (subMenu == 1) {
						System.out.print("학번 입력: ");
						data1 = scan.next();
						System.out.println("입력 <" + data1 + ">");
						do {
							System.out.print("\n1)상담신청  2)상담취소  3)상담기록  4)상담 상세정보표시  5)메인메뉴로 돌아가기  ---> ");
							menu = scan.nextInt();

							switch (menu) {
							case 1:
								System.out.print("교수 선택: ");
								data1 = scan.next();
								System.out.print("상담 정보 입력: ");
								data2 = scan.next();
								System.out.println("입력 <" + data1 + ", " + data2 + ">");
								System.out.println("신청한 상담 정보 표시");
								break;
							case 2:
								System.out.print("취소할 상담 선택: ");
								data1 = scan.next();
								System.out.println("입력 <" + data1 + ">");
								System.out.println("취소할 상담 선택");
								break;
							case 3:
								System.out.print("상담 선택: ");
								data1 = scan.next();
								System.out.print("상담 기록 입력: ");
								data2 = scan.next();
								System.out.println("입력 <" + data1 + ", " + data2 + ">");
								break;
							case 4:
								System.out.println("승인된 상담에 대한 정보 표시");
								System.out.print("상담 선택: ");
								data1 = scan.next();
								System.out.println("입력 <" + data1 + ">");
								System.out.println("특정 상담에 대한 내용 표시");
								break;
							case 5:
								System.out.println("메인메뉴로 돌아갑니다.");
								break;
							default:
								System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
							}
						} while (!(menu == 5));

						if (menu == 5)
							break;

					} else if (subMenu == 2) {
						System.out.print("교수 고유번호 입력: ");
						data1 = scan.next();
						System.out.println("입력 <" + data1 + ">");
						System.out.println("신청된 상담 리스트 출력");

						do {
							System.out.print("\n1)승인할상담선택  2)상담상세정보표시  3)메인메뉴로 돌아가기  ---> ");
							menu = scan.nextInt();

							switch (menu) {
							case 1:
								System.out.print("승인할 상담 선택: ");
								data1 = scan.next();
								System.out.println("입력 <" + data1 + ">");
								System.out.println("승인할 상담 선택");
								break;
							case 2:
								System.out.println("담당 상담에 대한 정보 표시");
								System.out.print("상담 선택: ");
								data1 = scan.next();
								System.out.println("입력 <" + data1 + ">");
								System.out.println("특정 상담에 대한 내용 표시");
								break;
							case 3:
								System.out.println("메인메뉴로 돌아갑니다.");
								break;
							default:
								System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
							}
						} while (!(menu == 3));

						if (menu == 3)
							break;
					} else
						System.out.println("메뉴 번호 오류: 메뉴를 다시 선택하세요.");
				} while (!(subMenu == 1 || subMenu == 2));

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
