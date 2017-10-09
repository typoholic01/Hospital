package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.A_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;
import singleton.Delegate;






public class A_BbsDao implements A_BbsDaoImpl 
{
	public A_BbsDao() {}
	
	//DB연결
	public Connection getConnection()throws SQLException 
	{
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
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
	
	//게시글갯수 구하기
	//페이징 :글의 총갯수 구하기
	public int listCount(int num, String fStr)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int _listCount=0;
		String sql="";
		if(num ==0) //검색안했을때
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_A";
			
		}
		else if(num ==1) //제목으로검색했을때
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_A WHERE TITLE LIKE '%"+fStr+"%'";
			
		}
		else if(num ==2) //아이디로검색
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_A WHERE ID = '"+fStr+"'";
			
		}
		else if(num ==3) //내용으로 검색
		{
			sql ="SELECT COUNT(*) AS COUNT FROM BBS_A WHERE CONTENT LIKE '%"+fStr+"%'";
			
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
		
	//어떤 게시글로 이동할지(0:검색X, 1:제목, 2:아이디, 3:내용)
	public void moveBBsList(int choiceNum,List<A_BbsDto> list,MemberDto mem)
	{
		Delegate dg = Delegate.getInstance();
		if(choiceNum ==0) //검색을 하지않았을때
		{
			dg.A_bbsCtrl.enterA_BbsListView(mem);
		}
		else if(choiceNum ==1) //제목으로 텍스트를 입력하여 검색했을때
		{
			list = dg.A_bbsCtrl.getTitleFindList(dg._getTextF);
			dg.A_bbsCtrl.searchA_BbsListView(list, mem);
		}
		else if(choiceNum ==2) //아이디로 텍스트를 입력하여 검색했을때
		{
			list = dg.A_bbsCtrl.getIdFindList(dg._getTextF);
			dg.A_bbsCtrl.searchA_BbsListView(list, mem);	
		}
		else if(choiceNum ==3) //제목으로 텍스트를 입력하여 검색했을때
		{
			list = dg.A_bbsCtrl.getContentFindList(dg._getTextF);
			dg.A_bbsCtrl.searchA_BbsListView(list, mem);
		}
	}
	
	//리스트불러오기
	public List<A_BbsDto> getBbsList() 
	{
		List<A_BbsDto> list = new ArrayList<A_BbsDto>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		Delegate dg = Delegate.getInstance();
		int pageNum = dg._getPageNum;
		String sql =  "SELECT SEQ,TITLE, CONTENT, RCOUNT, "
				+ "	RECO, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_A "
				+ " ORDER BY WDATE DESC ) AA ) "
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
				A_BbsDto dto = new A_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
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
		
	//조회수 증가
	public void readCount(int seq)
	{
		String sql = " UPDATE BBS_A SET  "
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

	//디테일정보
	public A_BbsDto readBbs(int seq)
	{
		A_BbsDto dto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, TITLE, WDATE, CONTENT, RCOUNT,RECO, DEL "
				+ " FROM BBS_A "
				+ " WHERE SEQ=? ";
			
		
		try {			
			conn = getConnection();
			psmt = conn.prepareStatement(sql);	
			
			psmt.setInt(1, seq);			
			rs = psmt.executeQuery();
			
			while(rs.next())
			{				
				//위랑 순서맞춰줘야됨
				String id = rs.getString(1);
				String title = rs.getString(2);
				String wdate = rs.getString(3);
				String content = rs.getString(4);
				int rcount = rs.getInt(5);
				int reco = rs.getInt(6);
				int del = rs.getInt(7);
				
				
				dto = new A_BbsDto(seq, title, content, rcount, reco, wdate, id, del);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally{
			close(rs, psmt, conn);			
		}
		return dto;
		
	}
	
	//글쓰기
	public boolean writeBbs(A_BbsDto dto)
	{
		int count = 0;		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		
		String sql = "INSERT INTO BBS_A(SEQ,TITLE,CONTENT,RCOUNT,RECO,WDATE,ID,DEL) "
				+ " VALUES(SEQ_BBS_A.NEXTVAL, ?, ?, 0,0, SYSDATE, ?, 1) ";
		
	
		try {
			conn = getConnection();
			System.out.println("2/6 writeBBS Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writeBBS Success");
			
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
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
	
	//글삭제하기
	public boolean deleteBbs(int seq)
	{
		int count = 0;
		String sql = " DELETE FROM BBS_A "
				+ " WHERE SEQ = ?";
		
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
		
	//아이디로검색하기
	public List<A_BbsDto> getIdFindList(String fStr)
	{
		List<A_BbsDto> list = new ArrayList<A_BbsDto>();
		
		//페이징처리
		Delegate dg = Delegate.getInstance();
		int pageNum = dg._getPageNum;
		String sql =  "SELECT SEQ,TITLE, CONTENT, RCOUNT, "
				+ "	RECO, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_A "
				+ " ORDER BY WDATE DESC ) AA  WHERE ID ='"+fStr+"')"
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
				
				A_BbsDto dto = new A_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
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
	public List<A_BbsDto> getTitleFindList(String fStr)
	{
		List<A_BbsDto> list = new ArrayList<A_BbsDto>();
		
		//페이징처리
		Delegate dg = Delegate.getInstance();
		int pageNum = dg._getPageNum;
		String sql =  "SELECT SEQ,TITLE, CONTENT, RCOUNT, "
				+ "	RECO, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_A "
				+ " ORDER BY WDATE DESC ) AA  WHERE TITLE LIKE '%"+fStr+"%')"
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
				
				A_BbsDto dto = new A_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
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
	public List<A_BbsDto> getContentFindList(String fStr)
	{
		List<A_BbsDto> list = new ArrayList<A_BbsDto>();
		
		Delegate dg = Delegate.getInstance();
		int pageNum = dg.getPageNum;
		String sql =  "SELECT SEQ, TITLE, CONTENT, RCOUNT, "
				+ "	RECO, WDATE, ID, DEL "
				+ " FROM (SELECT ROWNUM NUMROW, AA.* FROM ( SELECT * FROM BBS_A "
				+ " ORDER BY WDATE DESC ) AA  WHERE CONTENT LIKE '%"+fStr+"%')"
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
				
				A_BbsDto dto = new A_BbsDto
				(
					rs.getInt(i++), //seq
					rs.getString(i++), //title
					rs.getString(i++), //content
					rs.getInt(i++), //rcount
					rs.getInt(i++), //reco
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

	//추천(+)카운트
	public void recoCount(int seq)
	{
		String sql = " UPDATE BBS_A SET  "
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
		String sql = " UPDATE BBS_A SET  "
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

	//추천 (bool값 가져오기)
	public R_BbsDto getBool(int seq,MemberDto mem)
	{
		R_BbsDto dto = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//로그인아이디 가져오기
		Delegate dg = Delegate.getInstance();
		String getId = mem.getId();
		
		String sql = " SELECT BOOL,ID "
				+ " FROM BBS_R "
				+ " WHERE SEQ=? AND ID= ? AND BBSCHECK =0 ";
		
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
				
				dto = new R_BbsDto(0, id, seq, bool);
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
		+ " VALUES(0,?,?,0) ";
		
	
		try {
			conn = getConnection();
			System.out.println("2/6 writeBBS Success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("3/6 writeBBS Success");
			
			psmt.setInt(1, seq); //seq	
			psmt.setString(2, id); //id			
			
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
				+ "WHERE SEQ = ? AND ID = ? AND BBSCHECK =0";		
		
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









