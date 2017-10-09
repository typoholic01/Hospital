package dao;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.LetterDto;
import dto.MemberDto;
import dto.Month_dto;
import dto.PreorderDto;
import singleton.Delegate;

public class PreorderDao implements PreorderDaoImpl {
	
	//DB 초기화
	public static void initConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//이 클래스가 있느냐 따져보는 명령문
			System.out.println("Driver Loading Success!!");
		} catch (ClassNotFoundException e) {e.printStackTrace();}
	}
	
	//DB 연결
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.8:1521:xe", "hr", "hr");
			System.out.println("DB Connection Success!!");
		} catch (SQLException e) {e.printStackTrace();}
		return conn;
	}
	
	public static void close(Statement stmt, Connection conn) {
		try {if(stmt != null)
			stmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection conn) {

		try {if(stmt != null)
			if(rs != null)
				rs.close();
				stmt.close();
				if(conn != null)
					conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Month_dto> monthset(List<Month_dto> monthlist) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = new PreorderDao().getConnection();
		String sql = " SELECT month, x, last "
				+ " FROM calendar ";
		System.out.println("sql:" + sql);
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				
				int month = rs.getInt("month");
				int x = rs.getInt("x");
				int last = rs.getInt("last");
				Month_dto aa = new Month_dto(month, x, last);
				monthlist.add(aa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs, stmt, conn);
		}
		return monthlist;
		
		
	}
	
	public List<PreorderDto> doct_name(String doct_name) {
		List<PreorderDto> doctlist= new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = new PreorderDao().getConnection();
		String sql = " SELECT m.name, h.preorder_date, h.diction, h.user_id "
				+ " FROM member m, hospital_schedule h "
				+ " WHERE m.id = h.doctor_id and m.name = '" + doct_name + "' "
				+ " AND h.del = 0 "
				+ " AND m.auth = 0";
		System.out.println("sql:" + sql);
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String preorder_date = rs.getString("preorder_date");
				String user_id = rs.getString("user_id");
				String diction = rs.getString("diction");
				String node[] = rs.getString("preorder_date").split(" ");
				String node1[] = node[0].split("-");
				String node2[] = node[1].split(":");
				int scejul_month = Integer.parseInt(node1[1]);
				int scejul_day = Integer.parseInt(node1[2]);
				int scejul_time = 0;
				if (10 <= Integer.parseInt(node2[0]) && Integer.parseInt(node2[0]) <= 12) {
					scejul_time = 0;
				}else if (14 <= Integer.parseInt(node2[0]) && Integer.parseInt(node2[0]) <= 17) {
					scejul_time = 1;
				}else if (19 <= Integer.parseInt(node2[0]) && Integer.parseInt(node2[0]) <= 21) {
					scejul_time = 2;
				}
				PreorderDto aa = new PreorderDto(doct_name, scejul_month, scejul_day, scejul_time, preorder_date, diction, user_id);
				doctlist.add(aa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs, stmt, conn);
		}
		return doctlist;
		
	}
	
	public void preorderInsert(String doct_name, int scejul_month, int scejul_day, int scejul_time, String diction, MemberDto mem) {
		int count = 0;
		Connection conn = null;
		Statement stmt = null;
		conn = new PreorderDao().getConnection();
		Delegate d = Delegate.getInstance();
		String id = d.preorderCtrl.idselect(doct_name);
		String day ="";
		int time = 0;
		if (scejul_time == 0) {
			time = 11;
		}else if (scejul_time == 1) {
			time = 15;
		}else if (scejul_time == 2) {
			time = 20;
		}
		
			day = "2017-"+scejul_month+"-"+scejul_day+" "+time;
		
		//
		String sql = "INSERT INTO HOSPITAL_SCHEDULE(seq, doctor_id, preorder_date, diction, user_id, del, isend) "
				+ "VALUES(HOSPITAL_SCHEDULE_seq.nextval, '" 
				+ id 
				+ "', to_date('" + day + "', 'YYYY-MM-DD HH24'), "
				+ " '" + diction + "', " 
				+ " '"+ mem.getId() +"', "
				+ "0, 0 ) ";                                              
		System.out.println("sql:" + sql);
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {e.printStackTrace();}
		finally{
				try {if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
				} catch (SQLException e) {e.printStackTrace();}
		}
		
	}
	public String idselect(String doct_name) {
		String id = "";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = new PreorderDao().getConnection();
		String sql = " SELECT id "
				+ " FROM member "
				+ " WHERE name = '" + doct_name + "' "
				+ " and auth = 0";
		System.out.println("sql:" + sql);
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				id = rs.getString("id");
				
			
			/*stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			id = rs.getString("id");*/
		}} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs, stmt, conn);
		}
		System.out.println(id);
		return id;
	}
}
