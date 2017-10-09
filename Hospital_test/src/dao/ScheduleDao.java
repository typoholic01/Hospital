package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.LetterDto;
import dto.MemberDto;
import dto.ScheduleDto;
import view.MainFrame;
import view.MypageView;
import view.ScheduleView;

public class ScheduleDao implements ScheduleDaoImpl {
	ScheduleDto scheduleDto = new ScheduleDto();
	
	public ScheduleDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			System.out.println("Driver Loading Success");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.8:1521:xe", "hr", "hr");
			
			System.out.println("DB Connection Success!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	private void close(ResultSet rs,Statement stmt,Connection conn) {
		try {
			if (rs!=null)
				rs.close();

			if(stmt != null)
				stmt.close();

			if (conn!=null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void executeDB(String SQL) {
		Connection conn = getConnection();
		Statement stmt = null;
		
		System.out.println("sql:"+SQL);
		
		try {
			stmt=conn.createStatement();
			stmt.executeUpdate(SQL);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null,stmt, conn);
		}
	}
	
	private List<ScheduleDto> selectScheduleDB(String searchSQL) {
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<ScheduleDto> scheduleDtoList = new ArrayList<>();

		String sql = searchSQL;

		System.out.println("sql:"+sql);

		try {
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);

			while(rs.next())
			{
				int i = 1;				
				ScheduleDto dto = new ScheduleDto
						(
								rs.getInt(i++), //seq
								rs.getString(i++), //user_id
								rs.getString(i++), //diction
								rs.getString(i++), //doctor_id
								rs.getString(i++), //preorder_date
								rs.getInt(i++), //isend
								rs.getInt(i++) //del
						);
				scheduleDtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stmt, conn);
		}


		return scheduleDtoList;
	}
	
	private Object[][] convertObjToList(List<ScheduleDto> scheduleDtoList) {
		Object[][] rowData = new Object[scheduleDtoList.size()][5];
		
		int n = 1;
		for (int i = 0; i < scheduleDtoList.size(); i++) 
		{
			ScheduleDto dto = scheduleDtoList.get(i);
			
			//예약 종료 여부
			String isEndStr = isEndStr(dto.getIsend());
			
			String[] preorder_time = dto.getPreorder_date().split(" ");
			
			rowData[i][0] = dto.getSeq(); //번호
			rowData[i][1] = preorder_time[0]; //날짜
			rowData[i][2] = dto.getDiction(); //진료명
			rowData[i][3] = preorder_time[1]; //시간
			rowData[i][4] = isEndStr; //예약여부
			n++;
		}
		
		return rowData;
	}
	
	private String isEndStr(int isEnd) {
		switch (isEnd) {
		case 0:
			return "예약"; 
		default:
			return "완료"; 
		}
	}

	
	
	@Override
	public void preorder(MemberDto mem) {
		new ScheduleView(mem);
	}

	@Override
	public void mypage(MemberDto mem,Object[][] scheduleData) {
		
		new MypageView(mem,scheduleData);
	}
	
	public Object[][] getDataPreorder(MemberDto mem) {
		List<ScheduleDto> scheduleDtoList = selectScheduleDB("SELECT seq,user_id,diction,doctor_id,preorder_date,isend,del "
				+ "FROM (SELECT seq,user_id,diction,doctor_id,preorder_date,isend,del "
				+ "		FROM Hospital_Schedule "
				+ "		WHERE USER_ID = "
				+ "'" + mem.getId() + "' "
				+ "		ORDER BY preorder_date DESC) "
				+ "WHERE ROWNUM<= 10 "
				+ "AND DEL=0 ");

		Object[][] scheduleData = convertObjToList(scheduleDtoList);
		
		return scheduleData;
	}

	@Override
	public void preorderCancle(String seq) {
		String insertSQL = "UPDATE Hospital_Schedule SET DEL=1 WHERE SEQ = " 
				+ seq;

		executeDB(insertSQL);

		JOptionPane.showMessageDialog(null, "삭제 성공");
		
		
	}

	@Override
	public void backMenu(MemberDto mem) {
		MainFrame f = new MainFrame();
		f.introduceMainView(mem);
	}


	@Override
	public void schedule(MemberDto mem) {
	}


}
