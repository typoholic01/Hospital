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
import view.letter.LetterDetailView;
import view.letter.LetterView;

public class LetterDao implements LetterDaoImpl {

	
	public LetterDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Driver Loading Success!!");
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}

	//내부 메소드
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
	
	private void close(ResultSet rs, Statement stmt, Connection conn) {		
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	
	private List<LetterDto> searchBBS(String searchSQL) {
		Connection conn = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<LetterDto> letterDtoList = new ArrayList<>();

		String sql = searchSQL;

		System.out.println("sql:"+sql);

		try {
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);

			while(rs.next())
			{
				int i = 1;				
				LetterDto dto = new LetterDto
						(
								rs.getInt(i++), //seq
								rs.getString(i++), //wdate
								rs.getString(i++), //content
								rs.getString(i++), //WRITER_ID
								rs.getString(i++), //USER_ID
								rs.getInt(i++), //iSREAD, 
								rs.getInt(i++) //del
						);
				letterDtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,stmt, conn);
		}


		return letterDtoList;
	}
	
	private void executeLetterDB(String SQL) {
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
	
	private Object[][] convertObjToList(List<LetterDto> letterDtoList) {
		Object[][] rowData = new Object[letterDtoList.size()][5];
		int n = 1;
		for (int i = 0; i < letterDtoList.size(); i++) 
		{
			LetterDto dto = letterDtoList.get(i);
			
			//읽음 읽지않음 표시		
			String isreadStr = isReadStr(dto.getISREAD());
			
			
			rowData[i][0] = dto.getSeq(); //번호
			rowData[i][1] = dto.getWRITER_ID(); //작성자
			rowData[i][2] = dto.getCONTENT(); //제목
			rowData[i][3] = dto.getWDATE(); //날짜
			rowData[i][4] = isreadStr; //읽음 여부
			n++;
		}
		
		return rowData;
	}
	
	private String isReadStr(int isRead) {
		switch (isRead) {
		case 0:
			return "읽지않음"; 
		default:
			return "읽음"; 
		}
	}

	private Object[][] convertObjToDetail(List<LetterDto> letterDtoList) {
		Object[][] rowData = new Object[letterDtoList.size()][4];
		int n = 1;
		for (int i = 0; i < letterDtoList.size(); i++) 
		{
			LetterDto dto = letterDtoList.get(i);
			rowData[i][0] = dto.getSeq(); //번호
			rowData[i][1] = dto.getWDATE(); //날짜
			rowData[i][2] = dto.getCONTENT(); //내용
			rowData[i][3] = dto.getWRITER_ID(); //작성자
			n++;
		}
		
		return rowData;
	}
	
	//XXX [쪽지]받은 편지 확인
	@Override
	public void letter(MemberDto mem) {
		
		Object[][] receiveData = getRecvLetterObject(mem);
		Object[][] sendData = getSendLetterObject(mem);
		
		
		new LetterView(mem,receiveData,sendData);
	}
	
	public Object[][] getRecvLetterObject(MemberDto mem) {
		//받은 쪽지
		List<LetterDto> recvLetterDtoList = searchBBS("SELECT SEQ,WDATE,CONTENT,WRITER_ID,USER_ID,ISREAD,RECEIVE_DEL,SEND_DEL "
				+ "FROM (SELECT SEQ,WDATE,CONTENT,WRITER_ID,USER_ID,ISREAD,RECEIVE_DEL,SEND_DEL "
				+ "		FROM Hospital_Latter "
				+ "		WHERE USER_ID = "
				+ "'" + mem.getId() + "' "
				+ "		ORDER BY WDATE DESC) "
				+ "WHERE ROWNUM<= 10 "
				+ "AND RECEIVE_DEL=0 ");

		Object[][] receiveData = convertObjToList(recvLetterDtoList);
		
		return receiveData;
	}
	
	public Object[][] getSendLetterObject(MemberDto mem) {
		//보낸 쪽지
		List<LetterDto> sendLetterDtoList = searchBBS("SELECT SEQ,WDATE,CONTENT,WRITER_ID,USER_ID,ISREAD,RECEIVE_DEL,SEND_DEL "
				+ "FROM (SELECT SEQ,WDATE,CONTENT,WRITER_ID,USER_ID,ISREAD,RECEIVE_DEL,SEND_DEL "
				+ "		FROM Hospital_Latter "
				+ "		WHERE WRITER_ID = "
				+ "'" + mem.getId() + "' "
				+ "		ORDER BY WDATE DESC) "
				+ "WHERE ROWNUM<= 10 "
				+ "AND SEND_DEL=0 ");

		Object[][] sendData = convertObjToList(sendLetterDtoList);
		
		return sendData;
	}

	//XXX [쪽지]본문 확인
	@Override
	public void letterDetail(String seq, boolean isRead) {
		//받은 쪽지만 읽음 처리함
		if (isRead) {
			String insertSQL = "UPDATE Hospital_Latter SET ISREAD=1 WHERE SEQ = " + seq;
			
			executeLetterDB(insertSQL);
		}
		
		//쪽지 확인
		List<LetterDto> letterDtoList = searchBBS("SELECT SEQ,WDATE,CONTENT,WRITER_ID,USER_ID,ISREAD,RECEIVE_DEL,SEND_DEL "
				+"FROM (SELECT SEQ,WDATE,CONTENT,WRITER_ID,USER_ID,ISREAD,RECEIVE_DEL,SEND_DEL "
				+"		FROM Hospital_Latter "
				+"		WHERE seq = "
				+ "'" + seq + "' "
				+"		ORDER BY WDATE DESC) "
				/*+"WHERE ROWNUM<= 10 "*/);
		
		Object[][] searchRowData = convertObjToDetail(letterDtoList);
		
		new LetterDetailView(searchRowData);
		
	}

	//XXX [쪽지]보내기
	@Override
	public void letterWrite(MemberDto mem, String targetID, String receiveLetter) {
		String insertSQL = "INSERT INTO Hospital_Latter(SEQ,WDATE,CONTENT,WRITER_ID,USER_ID,ISREAD,RECEIVE_DEL,SEND_DEL) "
				+ "VALUES("
				+ "Hospital_Latter_SEQ.NEXTVAL,"
				+ "SYSDATE,"
				+ "'" + receiveLetter + "',"
				+ "'" + mem.getId() + "',"
				+ "'" + targetID +"',"
				+ "0,"
				+ "0,"
				+ "0"
				+ ")";
		
		executeLetterDB(insertSQL);
		
		JOptionPane.showMessageDialog(null, "전송 성공");
		
	}

	//XXX [쪽지]받은 쪽지 삭제
	@Override
	public void deleteReceiveLetter(String seq) {
		String insertSQL = "UPDATE Hospital_Latter SET RECEIVE_DEL=1 WHERE SEQ = " 
							+ seq;
		
		executeLetterDB(insertSQL);
		
		JOptionPane.showMessageDialog(null, "삭제 성공");
	}
	

	@Override
	public void deleteSendLetter(String seq) {
		String insertSQL = "UPDATE Hospital_Latter SET SEND_DEL=1 WHERE SEQ = " 
				+ seq;

		executeLetterDB(insertSQL);

		JOptionPane.showMessageDialog(null, "삭제 성공");
		
	}
}
