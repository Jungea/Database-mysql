package hw6_7;

/*
 * �ۼ���: ������
 * �ۼ���: 2018.12.19.
 * ����: ���α׷� ����
 */

import java.sql.*;
import java.util.Scanner;

public class Main {
	public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/projects?characterEncoding=UTF-8&serverTimezone=UTC"; // �����ͺ��̽� ����
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("�����ͺ��̽� ������ ...");
			con = DriverManager.getConnection(url, "puser", "1234"); // ����� ����
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
		System.out.println("hw6_7: ������ \n");

		Connection con = makeConnection();

		Scanner scan = new Scanner(System.in);
		System.out.println("���� �� �ϳ��� �����Ͻÿ�.");
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
			System.out.print("\na.�а�����  b.�л�����  c.��������  d.������  e.����  ---> ");

			mainMenu = scan.next();
			switch (mainMenu) {
			case "a":
				// �а� ���� ��ü ���
				sql = "SELECT * FROM department";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (null == rs.getString(3))
						System.out.println(rs.getString(1) + " �� " + rs.getString(2));
					else
						System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3));
				}

				do {
					System.out.print("\n1.�а������Է�  2.�а�����  3.���θ޴����ư��� ---> ");
					subMenu = scan.next();
					switch (subMenu) {
					case "1":
						// �а� ���� �Է�
						System.out.println("<�а����� �Է�>");
						System.out.print("�а� ������ȣ: ");
						data1 = scan.next();
						System.out.print("�а� �̸�: ");
						data2 = scan.next();
						System.out.print("�а� �繫�� ��ȣ (�Է��� ���Ϸ��� -1�� �Է��ϼ���.): ");
						data3 = scan.next();
						if (data3.equals("-1"))
							sql = "INSERT INTO department (deptID, deptName) VALUES (" + data1 + ", '" + data2 + "')";
						else
							sql = "INSERT INTO department VALUES (" + data1 + ", '" + data2 + "', " + data3 + ")";

						try {
							if (stmt.executeUpdate(sql) == 1)
								System.out.println("�а� �߰� ����");
							else
								System.out.println("�а� �߰� ����");

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("�а� �߰� ���� : �̹� �����ϴ� ������ȣ/�а��̸� �Դϴ�.");
						}
						System.out.println();

						sql = "SELECT * FROM department";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " �� " + rs.getString(2));
							else
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3));
						}
						break;

					case "2":
						System.out.print("������ �а� ���� ��ȣ: ");
						data1 = scan.next();
						sql = "DELETE FROM department WHERE deptID = " + data1;

						try {
							if (stmt.executeUpdate(sql) == 1)
								System.out.println("�а� ���� ����");
							else
								System.out.println("�а� ���� ���� : �������� �ʴ� �а� ���� ��ȣ�Դϴ�.");
							System.out.println();
						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("�а� ���� ���� : �ش� �а� �л��̳� ������ ���� �ֽ��ϴ�.");
						}

						sql = "SELECT * FROM DEPARTMENT";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " �� " + rs.getString(2));
							else
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3));
						}
						break;

					case "3":
						System.out.println("���θ޴��� ���ư��ϴ�.");
						break;
					default:
						System.out.println("�޴� ��ȣ ����: �޴��� �ٽ� �����ϼ���.");
					}
				} while (!subMenu.equals("3"));
				break;

			case "b":
				sql = "SELECT * FROM student";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (null == rs.getString(3))
						System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(4));
					else
						System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3) + " ��  "
								+ rs.getString(4));
				}
				do {
					System.out.print("\n1.�л������Է�  2.�л�����  3.���θ޴����ư��� ---> ");
					subMenu = scan.next();
					switch (subMenu) {
					case "1":
						System.out.println("<�л����� �Է�>");
						System.out.print("�й�: ");
						data1 = scan.next();
						System.out.print("�л� �̸�: ");
						data2 = scan.next();
						System.out.print("�л� �ڵ��� ��ȣ (�Է��� ���Ϸ��� -1�� �Է��ϼ���.): ");
						data3 = scan.next();
						System.out.print("���� �а� ���� ��ȣ: ");
						data4 = scan.next();
						try {
							if (data3.equals("-1"))
								sql = "INSERT INTO student (stdID, stdName, deptID) VALUES (" + data1 + ", '" + data2
										+ "', " + data4 + ")";
							else
								sql = "INSERT INTO student VALUES (" + data1 + ", '" + data2 + "', " + data3 + ", "
										+ data4 + ")";

							if (stmt.executeUpdate(sql) == 1)
								System.out.println("�л� �߰� ����");
							else
								System.out.println("�л� �߰� ����");
							System.out.println();

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("�л� �߰� ���� : �������� �ʴ� �а� ������ȣ�̰ų� �̹� �����ϴ� �й�/�ڵ��� ��ȣ�Դϴ�. \n");
						}

						sql = "SELECT * FROM student";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3)
										+ " ��  " + rs.getString(4));
						}
						break;

					case "2":
						System.out.print("������ �л� �й�: ");
						data1 = scan.next();
						sql = "DELETE FROM student WHERE stdID = " + data1;
						if (stmt.executeUpdate(sql) == 1)
							System.out.println("�л� ���� ����");
						else
							System.out.println("�л� ���� ���� : �������� �ʴ� �й��Դϴ�.");
						System.out.println();

						sql = "SELECT * FROM student";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3)
										+ " ��  " + rs.getString(4));
						}
						break;

					case "3":
						System.out.println("���θ޴��� ���ư��ϴ�.");
						break;

					default:
						System.out.println("�޴� ��ȣ ����: �޴��� �ٽ� �����ϼ���.");
					}
				} while (!subMenu.equals("3"));
				break;

			case "c":
				sql = "SELECT * FROM professor";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					if (null == rs.getString(3))
						System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(4));
					else
						System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3) + " ��  "
								+ rs.getString(4));
				}

				do {
					System.out.print("\n1.���������Է�  2.��������  3.���θ޴����ư��� ---> ");
					subMenu = scan.next();
					switch (subMenu) {
					case "1":
						System.out.println("<�������� �Է�>");
						System.out.print("���� ������ȣ: ");
						data1 = scan.next();
						System.out.print("���� �̸�: ");
						data2 = scan.next();
						System.out.print("���� �ڵ��� ��ȣ (�Է��� ���Ϸ��� -1�� �Է��ϼ���.): ");
						data3 = scan.next();
						System.out.print("�Ҽ� �а� ���� ��ȣ: ");
						data4 = scan.next();
						try {
							if (data3.equals("-1"))
								sql = "INSERT INTO professor (proID, proName, deptID) VALUES (" + data1 + ", '" + data2
										+ "', " + data4 + ")";
							else
								sql = "INSERT INTO professor VALUES (" + data1 + ", '" + data2 + "', " + data3 + ", "
										+ data4 + ")";

							if (stmt.executeUpdate(sql) == 1)
								System.out.println("���� �߰� ����");
							else
								System.out.println("���� �߰� ����");
							System.out.println();

						} catch (SQLIntegrityConstraintViolationException e) {
							System.out.println("���� �߰� ���� : �������� �ʴ� �а� ������ȣ�̰ų� �̹� �����ϴ� ���� ������ȣ/�ڵ��� ��ȣ�Դϴ�. \n");
						}

						sql = "SELECT * FROM professor";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3)
										+ " ��  " + rs.getString(4));
						}
						break;

					case "2":
						System.out.print("������ ���� ������ȣ: ");
						data1 = scan.next();
						sql = "DELETE FROM professor WHERE proID = " + data1;
						if (stmt.executeUpdate(sql) == 1)
							System.out.println("���� ���� ����");
						else
							System.out.println("���� ���� ���� : �������� �ʴ� ���� ���� ��ȣ�Դϴ�.");
						System.out.println();

						sql = "SELECT * FROM professor";
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							if (null == rs.getString(3))
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(4));
							else
								System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3)
										+ " ��  " + rs.getString(4));
						}
						break;

					case "3":
						System.out.println("���θ޴��� ���ư��ϴ�.");
						break;

					default:
						System.out.println("�޴� ��ȣ ����: �޴��� �ٽ� �����ϼ���.");
					}
				} while (!subMenu.equals("3"));

				break;

			case "d":
				do {
					System.out.print("\n1.�й��Է�  2.�����Է� ---> ");
					subMenu = scan.next();
					if (subMenu.equals("1")) {
						System.out.print("�й� �Է�: ");
						data1 = scan.next();

						sql = "SELECT * FROM student WHERE stdID = " + data1;

						rs = stmt.executeQuery(sql);
						check = 0;
						while (rs.next()) {
							check++;
						}
						if (check == 0) {
							System.out.println("�Է� ���� : �������� �ʴ� �й��Դϴ�.");
							continue;
						}

						sql = "SELECT * FROM reservation WHERE stdID = " + data1;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3)
									+ " ��  " + rs.getString(4) + " �� " + rs.getString(5) + " �� " + rs.getString(6)
									+ " ��  " + rs.getString(7));
						}

						do {
							System.out.print("\n1.����û  2.������  3.�����  4.��� ������ǥ��  5.���θ޴��� ���ư���  ---> ");
							menu = scan.next();

							switch (menu) {
							case "1":
								System.out.print("��� ��û ���� ������ȣ: ");
								data2 = scan.next();
								System.out.print("��� ��û ��¥(YYYY-MM-DD): ");
								data3 = scan.next();
								System.out.print("��� ����: ");
								data4 = scan.next();

								sql = "INSERT INTO reservation (stdID, proID, resTime, resTitle) VALUES (" + data1
										+ ", " + data2 + ", '" + data3 + "', '" + data4 + "')";

								try {
									if (stmt.executeUpdate(sql) == 1)
										System.out.println("��� ��û ����");
									else
										System.out.println("��� ��û ����");
									System.out.println();

								} catch (SQLIntegrityConstraintViolationException e) {
									System.out.println("��� ��û ���� : �������� �ʴ� ���� ������ȣ�Դϴ�.");
								}

								sql = "SELECT * FROM reservation WHERE stdID = " + data1;
								rs = stmt.executeQuery(sql);
								while (rs.next()) {
									System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� "
											+ rs.getString(3) + " ��  " + rs.getString(4) + " �� " + rs.getString(5)
											+ " �� " + rs.getString(6) + " ��  " + rs.getString(7));
								}
								break;
							case "2":
								System.out.print("����� ��� ����: ");
								data1 = scan.next();
								System.out.println("�Է� <" + data1 + ">");
								System.out.println("����� ��� ����");
								break;
							case "3":
								System.out.print("��� ����: ");
								data1 = scan.next();
								System.out.print("��� ��� �Է�: ");
								data2 = scan.next();
								System.out.println("�Է� <" + data1 + ", " + data2 + ">");
								break;
							case "4":
								System.out.println("���ε� ��㿡 ���� ���� ǥ��");
								System.out.print("��� ����: ");
								data1 = scan.next();
								System.out.println("�Է� <" + data1 + ">");
								System.out.println("Ư�� ��㿡 ���� ���� ǥ��");
								break;
							case "5":
								System.out.println("���θ޴��� ���ư��ϴ�.");
								break;
							default:
								System.out.println("�޴� ��ȣ ����: �޴��� �ٽ� �����ϼ���.");
							}
						} while (!menu.equals("5"));

						if (menu.equals("5"))
							break;

					} else if (subMenu.equals("2")) {
						System.out.print("���� ������ȣ �Է�: ");
						data1 = scan.next();

						sql = "SELECT * FROM professor WHERE proID = " + data1;

						rs = stmt.executeQuery(sql);
						check = 0;
						while (rs.next()) {
							check++;
						}
						if (check == 0) {
							System.out.println("�Է� ���� : �������� �ʴ� ���� ������ȣ�Դϴ�.");
							continue;
						}

						sql = "SELECT * FROM reservation WHERE proID = " + data1;
						rs = stmt.executeQuery(sql);
						while (rs.next()) {
							System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� " + rs.getString(3)
									+ " ��  " + rs.getString(4) + " �� " + rs.getString(5) + " �� " + rs.getString(6)
									+ " ��  " + rs.getString(7));
						}

						do {
							System.out.print("\n1.�����һ�㼱��  2.��������ǥ��  3.���θ޴��� ���ư���  ---> ");
							menu = scan.next();

							switch (menu) {
							case "1":
								System.out.print("������ ��� ��ȣ: ");
								data2 = scan.next();
								sql = "UPDATE reservation SET approve = 1 where resID =" + data2 + " and proID = "
										+ data1;
								if (stmt.executeUpdate(sql) == 1)
									System.out.println("��� ��û  ���� ����");
								else
									System.out.println("��� ��û ���� ����");
								System.out.println();

								sql = "SELECT * FROM reservation WHERE proID = " + data1;
								rs = stmt.executeQuery(sql);
								while (rs.next()) {
									System.out.println(rs.getString(1) + " �� " + rs.getString(2) + " �� "
											+ rs.getString(3) + " ��  " + rs.getString(4) + " �� " + rs.getString(5)
											+ " �� " + rs.getString(6) + " ��  " + rs.getString(7));
								}

								break;
							case "2":
								System.out.println("��� ��㿡 ���� ���� ǥ��");
								System.out.print("��� ����: ");
								data1 = scan.next();
								System.out.println("�Է� <" + data1 + ">");
								System.out.println("Ư�� ��㿡 ���� ���� ǥ��");
								break;
							case "3":
								System.out.println("���θ޴��� ���ư��ϴ�.");
								break;
							default:
								System.out.println("�޴� ��ȣ ����: �޴��� �ٽ� �����ϼ���.");
							}
						} while (!menu.equals("3"));

						if (menu.equals("3"))
							break;
					} else
						System.out.println("�޴� ��ȣ ����: �޴��� �ٽ� �����ϼ���.");
				} while (!(subMenu.equals("1") || subMenu.equals("2")));

				break;

			case "e":
				System.out.println("�����մϴ�.");
				break;

			default:
				System.out.println("�޴� ��ȣ ����: �޴��� �ٽ� �����ϼ���.");
			}
		} while (!mainMenu.equals("e"));

		stmt.close();
		con.close();

	}

}
