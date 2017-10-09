package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import dto.C_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;
import singleton.Delegate;

public class C_BbsDao implements C_BbsDaoImpl 
{
	public C_BbsDao() {}
	
	//DB연결
	public Connection getConnection()throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.8:1521:xe", "hr", "hr"); //조장님 db
		//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr"); //my db
		System.out.println("DB Connection Success!!");
		return conn;		
	}
	
	//DB닫기
	public void close(ResultSet rs, Statement stmt, Connection conn) 
	{		
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
	
	//페이징 : 글의 총 갯수구하기
	public int listCount(int num, String fStr)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int _listCount=0;
		String sql="";
		if(num ==0) //검색안했을때
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_C";
			
		}
		else if(num ==1) //제목으로검색했을때
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_C WHERE TITLE LIKE '%"+fStr+"%'";
			
		}
		else if(num ==2) //아이디로검색
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_C WHERE ID = '"+fStr+"'";
			
		}
		else if(num ==3) //내용으로 검색
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_C WHERE CONTENT LIKE '%"+fStr+"%'";
			
		}
		
		try 
		{
			conn = getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			if(rs.next())
			{				
				String count = rs.getString("COUNT");
				_listCount =Integer.parseInt(count);				
			}
	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally
		{
			close(rs, psmt, conn);
			
		}
		return _listCount;
		
	}
	
	//리스트 이동 method
	public void moveBBsList(int choiceNum,List<C_BbsDto> list,MemberDto mem)
	{
		Delegate dg = Delegate.getInstance();
		if(choiceNum ==0) //검색을 하지않았을때
		{
			dg.C_bbsCtrl.enterC_BbsListView(null);
		}
		else if(choiceNum ==1) //제목으로 텍스트를 입력하여 검색했을때
		{
			list = dg.C_bbsCtrl.getTitleFindList(dg.getTextF);
			dg.C_bbsCtrl.searchC_BbsListView(list, mem);
		}
		else if(choiceNum ==2) //아이디로 텍스트를 입력하여 검색했을때
		{
			list = dg.C_bbsCtrl.getIdFindList(dg.getTextF);
			dg.C_bbsCtrl.searchC_BbsListView(list, mem);	
		}
		else if(choiceNum ==3) //제목으로 텍스트를 입력하여 검색했을때
		{
			list = dg.C_bbsCtrl.getContentFindList(dg.getTextF);
			dg.C_bbsCtrl.searchC_BbsListView(list, mem);
		}
	}
	
	//리스트정보가져오기
	public List<C_BbsDto> getBbsList()
	{
		List<C_BbsDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//페이징처리

		Delegate dg = Delegate.getInstance();
		int pageNum = dg.getPageNum;		
		
		String sql =  "SELECT SEQ,ORDERNUM,STEP,DEPTH, TITLE, CONTENT, RCOUNT, "
				+ "	RECO, SECREAT, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_C "
				+ " ORDER BY ORDERNUM DESC, STEP ASC, DEPTH ASC ) AA ) "
				+ " WHERE NUMROW >= 1+(17*("+pageNum+")) AND NUMROW <= 17*"+(pageNum+1);
				
		try {
			conn = getConnection();
			System.out.println("2/6 getBbsList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getBbsList Success");
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getBbsList Success");
			
			while(rs.next())
			{
				int i = 1;				
				C_BbsDto dto = new C_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getInt(i++), //ordernum
					rs.getInt(i++), //step
					rs.getInt(i++), //depth
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
					rs.getInt(i++), //secreat
					rs.getString(i++), //wdate
					rs.getString(i++), //id
					rs.getInt(i++) //del
				);	
				list.add(dto);
			}		
			System.out.println("5/6 getBbsList Success");
			
		} catch (SQLException e) {
			System.out.println("getBbsList fail");
		} finally{
			close(rs, psmt, conn);
			System.out.println("6/6 getBbsList Success");
		}
		
		return list;
		
	}

	//마우스 클릭으로 이동 : 조회수 증가
	public void readCount(int seq) 
	{
		String sql = " UPDATE BBS_C SET  "
				+" RCOUNT=RCOUNT+1 "
				+ " WHERE SEQ= ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, psmt, conn);		
		}
	}

	//마우스클릭으로 이동 : detailView정보불러오기
	public C_BbsDto readBbs(int seq) 
	{
		C_BbsDto dto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, TITLE, WDATE, CONTENT,SECREAT, RCOUNT,DEL, "
				+ "RECO,ORDERNUM,STEP,DEPTH"
				+ " FROM BBS_C "
				+ " WHERE SEQ=? ";
		try {			
			conn = getConnection();
			psmt = conn.prepareStatement(sql);	
			
			psmt.setInt(1, seq);			
			rs = psmt.executeQuery();
			
			while(rs.next())
			{				
				String id = rs.getString(1);
				String title = rs.getString(2);
				String wdate = rs.getString(3);
				String content = rs.getString(4);
				int secreat = rs.getInt(5);
				int rcount = rs.getInt(6);
				int del = rs.getInt(7);
				int reco = rs.getInt(8);
				int orderNum = rs.getInt(9);
				int step = rs.getInt(10);
				int depth = rs.getInt(11);
				
				dto = new C_BbsDto(seq, orderNum, step, depth, title, content, rcount, reco, secreat, wdate, id, del);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			close(rs, psmt, conn);			
		}
		return dto;
	}

	//글쓰기
	public boolean writeBbs(C_BbsDto dto) 
	{
		int count = 0;		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//공개글 SECREAT = 1, 비공개 = 0
		//ORDERNUM에는 SEQ_BBS_C값을 넣어주도록한다(SEQ와 같은수로 움직이도록설정) 
		//STEP과 DEPTH는 0으로 맞춰준다
		String sql = "INSERT INTO BBS_C(SEQ,ORDERNUM,STEP,DEPTH,TITLE,CONTENT,RCOUNT,RECO,SECREAT,WDATE,ID,DEL) "
		+ " VALUES(SEQ_BBS_C.NEXTVAL, SEQ_BBS_C.NEXTVAL, 0,0, ?, ?, 0, 0, ?, SYSDATE, ?, 1) ";
		
	
		try {
			conn = getConnection();
			System.out.println("2/6 writeBBS Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writeBBS Success");
			
			psmt.setString(1, dto.getTitle()); //TITLE						
			psmt.setString(2, dto.getContent()); //CONTENT
			psmt.setInt(3, dto.getSecreat()); 	 //SECREAT
			psmt.setString(4, dto.getId()); //ID
			
			
			count = psmt.executeUpdate();
			System.out.println("4/6 writeBBS Success");
			
		} catch (SQLException e) 
		{			
			System.out.println("writeBBS fail");
		} finally
		{
			close(rs, psmt, conn);			
		}
		
		return count>0?true:false;
		
	}

	//삭제하기
	public boolean deleteBbs(int seq) 
	{
		int count = 0;
		String sql = " UPDATE BBS_C "
				+ " SET TITLE='삭제된 글입니다',DEL =0 WHERE SEQ = ?";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		try {
			conn=getConnection();					
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally
		{
			close(null, psmt, conn);			
		}
				
		return count>0?true:false;
	}

	//아이디로 검색하기
	public List<C_BbsDto> getIdFindList(String fStr) {
		List<C_BbsDto> list = new ArrayList<C_BbsDto>();
		
		//페이징처리
		Delegate dg = Delegate.getInstance();
		int pageNum = dg.getPageNum;
		String sql =  "SELECT SEQ,ORDERNUM,STEP,DEPTH, TITLE, CONTENT, RCOUNT, "
				+ "	RECO, SECREAT, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_C "
				+ " ORDER BY ORDERNUM DESC, STEP ASC, DEPTH ASC ) AA  WHERE ID ='"+fStr+"')"
				+ " WHERE NUMROW >= 1+(17*("+pageNum+")) AND NUMROW <= 17*"+(pageNum+1);
				
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			System.out.println("2/6 getTitleFindList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getTitleFindList Success");		
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getTitleFindList Success");
			System.out.println(sql);
			while(rs.next())
			{
				int i = 1;
				
				C_BbsDto dto = new C_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getInt(i++), //orderNum
					rs.getInt(i++), //step
					rs.getInt(i++), //depth
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
					rs.getInt(i++), //secreat
					rs.getString(i++), //wdate
					rs.getString(i++), //id
					rs.getInt(i++) //del										
				);	
				list.add(dto);
			}		
			System.out.println("5/6 getTitleFindList Success");
			
		} catch (SQLException e) {
			System.out.println("getTitleFindList fail");
			e.printStackTrace();
		} finally{
			close(rs, psmt, conn);
			System.out.println("6/6 getTitleFindList Success");
		}
		
		return list;
	}

	//제목으로 검색하기
	public List<C_BbsDto> getTitleFindList(String fStr) 
	{
		List<C_BbsDto> list = new ArrayList<>();
		
		//페이징처리
		Delegate dg = Delegate.getInstance();
		int pageNum = dg.getPageNum;
		String sql =  "SELECT SEQ,ORDERNUM,STEP,DEPTH, TITLE, CONTENT, RCOUNT, "
				+ "	RECO, SECREAT, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_C "
				+ " ORDER BY ORDERNUM DESC, STEP ASC, DEPTH ASC ) AA  WHERE TITLE LIKE '%"+fStr+"%')"
				+ " WHERE NUMROW >= 1+(17*("+pageNum+")) AND NUMROW <= 17*"+(pageNum+1);
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			System.out.println("2/6 getTitleFindList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getTitleFindList Success");
			
			
			
			rs = psmt.executeQuery();
			System.out.println("4/6 getTitleFindList Success");
			
			while(rs.next())
			{
				int i = 1;
				
				C_BbsDto dto = new C_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getInt(i++), //ordernum
					rs.getInt(i++), //step
					rs.getInt(i++), //depth
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
					rs.getInt(i++), //secreat
					rs.getString(i++), //wdate
					rs.getString(i++), //id
					rs.getInt(i++) //del										
				);	
				list.add(dto);
			}		
			System.out.println("5/6 getTitleFindList Success");
			
		} catch (SQLException e) {
			System.out.println("getTitleFindList fail");
			e.printStackTrace();
		} finally{
			close(rs, psmt, conn);
			System.out.println("6/6 getTitleFindList Success");
		}
		
		return list;
	}

	//내용으로 검색하기
	public List<C_BbsDto> getContentFindList(String fStr) 
	{
		List<C_BbsDto> list = new ArrayList<C_BbsDto>();
		
		
		Delegate dg = Delegate.getInstance();
		int pageNum = dg.getPageNum;
		String sql =  "SELECT SEQ,ORDERNUM,STEP,DEPTH, TITLE, CONTENT, RCOUNT, "
				+ "	RECO, SECREAT, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_C "
				+ " ORDER BY ORDERNUM DESC, STEP ASC, DEPTH ASC ) AA  WHERE CONTENT LIKE '%"+fStr+"%')"
				+ " WHERE NUMROW >= 1+(17*("+pageNum+")) AND NUMROW <= 17*"+(pageNum+1);
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try {
			conn = getConnection();
			System.out.println("2/6 getTitleFindList Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 getTitleFindList Success");
						
			rs = psmt.executeQuery();
			System.out.println("4/6 getTitleFindList Success");
			
			while(rs.next())
			{
				int i = 1;
				
				C_BbsDto dto = new C_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getInt(i++), //ordernum
					rs.getInt(i++), //step
					rs.getInt(i++), //depth
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
					rs.getInt(i++), //secreat
					rs.getString(i++), //wdate
					rs.getString(i++), //id
					rs.getInt(i++) //del										
				);	
				list.add(dto);
			}		
			System.out.println("sql" +sql); 
			System.out.println("5/6 getTitleFindList Success");
			
		} catch (SQLException e) {
			System.out.println("getTitleFindList fail");
			e.printStackTrace();
		} finally{
			close(rs, psmt, conn);
			System.out.println("6/6 getTitleFindList Success");
		}
		
		return list;
		
	}
	
	//답글쓰기
	public boolean repBbs(C_BbsDto dto,MemberDto mem) 
	{
		int count = 0;		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//아이디 정보가져오기
		Delegate dg = Delegate.getInstance();
		String getId = dg.getId;
		//공개글 SECREAT = 1, 비공개 = 0
		//ORDERNUM에 seq값을 넣도록함
		//부모글 step = 0 , 그 이후 댓글들은 ++를 해주도록한다
		//depth : 부모글= 0 ,댓글 = 1 , 댓댓글 = 2, .... 
				
		String sql = "INSERT INTO BBS_C(SEQ,ORDERNUM,STEP,DEPTH,TITLE,CONTENT,RCOUNT,RECO,SECREAT,WDATE,ID,DEL) "
				+"VALUES(SEQ_BBS_C.NEXTVAL,?,?,?,?,?,0,0,?,SYSDATE,?,1) ";
		
	
		try {
			conn = getConnection();
			System.out.println("2/6 repBbs Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 repBbs Success");
			
			//?채우기 : 1=ORDERNUM, 2=STEP, 3=DEPTH, 4=TITLE, 5=CONTENT, 6=SECREAT (7=ID)[MEMBER와연결후 수정하기]
			psmt.setInt(1, dto.getOrderNum()); //들어오는 orderNum값 넣기
			//만약 들어온 step값이 db데이터에서 존재하게된다면 이미존재한 데이터들의 step값을 모두 업데이트하도록한다(+1씩)
			psmt.setInt(2, dto.getStep()+1); //STEP +1
			psmt.setInt(3, dto.getDepth()+1); //DEPTH +1
			//들어온 depth+1에 값과 └Re: 문자를 포함하여 title변경하기
			String nullStr ="";
			//depth 가 0일때
			if(dto.getDepth() ==0) 
			{
				nullStr =" └Re:";
			}
			//depth 가 1이상일때
			else if(dto.getDepth() >=1)
			{				
				for (int i = 0; i < dto.getDepth()+1; i++) 
				{
					nullStr ="  "+nullStr;
				}
			}	
			//첨가된 문자 nullStr을 더해서 넣기
			psmt.setString(4, nullStr+ dto.getTitle());
			psmt.setString(5, dto.getContent());        
			psmt.setInt(6, dto.getSecreat()); //SECREAT(작성할때 비밀글체크를 했다면0으로들어가게됨)
			psmt.setString(7, getId);        //ID
			
			System.out.println("-4/6 repBbs Success");
			
			//업데이트 method호출
			boolean b = updateStep(dto);
			
			count = psmt.executeUpdate();
			System.out.println("4/6 repBbs Success");
			
		} catch (SQLException e) 
		{			
			e.printStackTrace();
			System.out.println("repBbs fail");
		} finally
		{
			close(rs, psmt, conn);			
		}
		
		return count>0?true:false;
		
	}
	
	//업데이트 스텝(기존에 존재하는스텝 +1씩 해주기)
	private boolean updateStep(C_BbsDto dto)
	{
		int count = 0;
		String sql = " UPDATE BBS_C "
				+ " SET STEP = STEP+1 WHERE STEP >"+dto.getStep()+"AND ORDERNUM ="+dto.getOrderNum();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		try {
			conn=getConnection();					
			psmt=conn.prepareStatement(sql);
					
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally
		{
			close(null, psmt, conn);			
		}
		
		return count>0?true:false;
				
		
	}

	//추천(+)카운트
	public void recoCount(int seq) 
	{
		String sql = " UPDATE BBS_C SET  "
				+" RECO=RECO+1 "
				+ " WHERE SEQ= ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, psmt, conn);		
		}
	}
	
	//추천(-)카운트
	public void recoSubtract(int seq) 
	{
		String sql = " UPDATE BBS_C SET  "
				+" RECO=RECO-1 "
				+ " WHERE SEQ= ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			psmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, psmt, conn);		
		}
	}
	
	//추천 (bool값) 가져오기
	public R_BbsDto getBool(int seq,MemberDto mem) 
	{
		R_BbsDto dto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		//로그인아이디 가져오기
		Delegate dg = Delegate.getInstance();
		String getId = dg.getId;
		
		String sql = " SELECT BOOL ,ID"
				+ " FROM BBS_R "
				+ " WHERE SEQ=? AND BBSCHECK =1 AND ID =?";
		try {			
			conn = getConnection();
			psmt = conn.prepareStatement(sql);	
			
			psmt.setInt(1, seq);	
			psmt.setString(2, getId);
			rs = psmt.executeQuery();
			
			
			while(rs.next())
			{				
				int bool = rs.getInt(1);
				String id = rs.getString(2);

				dto = new R_BbsDto(1, id, seq, bool);
			}
			
		} catch (SQLException e) 
		{			
			e.printStackTrace();
		} finally{
			close(rs, psmt, conn);			
		}
		return dto;
	}
	
	//추천취소버튼활성화(INSERT)
	public boolean recoInsert(String id, int seq)
	{
		int count = 0;		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = "INSERT INTO BBS_R(BBSCHECK,SEQ,ID,BOOL) "
		+ " VALUES(1,?,?,0) ";
		
	
		try {
			conn = getConnection();
			System.out.println("2/6 writeBBS Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writeBBS Success");
			
			psmt.setInt(1, seq); //seq							
			psmt.setString(2, id); //ID
			
			
			count = psmt.executeUpdate();
			System.out.println("4/6 writeBBS Success");
			
		} catch (SQLException e) 
		{			
			System.out.println("writeBBS fail");
		} finally
		{
			close(rs, psmt, conn);			
		}
		
		return count>0?true:false;
	}
	
	//추천버튼활성화(DELETE)
	public boolean recoDelete(int seq, String id) 
	{
		int count = 0;
		String sql = " DELETE FROM BBS_R "
				+ "WHERE SEQ = ? AND ID = ? AND BBSCHECK =1";		
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		try {
			conn=getConnection();					
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			psmt.setString(2, id);
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally
		{
			close(null, psmt, conn);			
		}
				
		return count>0?true:false;
	}
}
