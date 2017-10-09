package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.MemberDto;
import singleton.Delegate;
import view.MainFrame;
import view.client.ClientFrame;
import view.login.LoginView;

public class MemberDao implements MemberDaoImpl {
	
	public MemberDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Driver Loading Success!!");
			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.8:1521:xe", "hr", "hr");
			System.out.println("DB Connection Success!!");		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(ResultSet rs, Statement stmt, Connection conn) {		
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

	@Override
	public MemberDto login(String loginID, String pw) {

		
        MemberDto mem = null;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID, NAME, EMAIL, PHONE, AUTH "
				+ " FROM MEMBER "
				+ " WHERE ID=? AND PW=? ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, loginID);
			psmt.setString(2, pw);			
			
			rs = psmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				int auth = rs.getInt(5);
				
				mem = new MemberDto(id, null, name, email, phone, auth);			
			}	
			if (mem == null) {
				
				System.out.println("login Fail");
				JOptionPane.showMessageDialog(null, "ID나 Password가 틀렸습니다");
				
				return null;
			}
			
		} catch (SQLException e) {
			
		} finally{
			close(rs, psmt, conn);			
		}
//		Delegate d = Delegate.getInstance();
		JOptionPane.showMessageDialog(null, mem.getId()+ " 님 환영합니다.");
		
		return mem;
	}
	
	public void OpenLoginView(MemberDto mem) {
		MainFrame f = new MainFrame();

		f.introduceMainView(mem);
	}
	
	public boolean addMember(MemberDto dto) {
		int count = 0;
		
		Connection conn = null;
		PreparedStatement psmt = null;
				
		String sql = " INSERT INTO MEMBER "
				+ "(ID, PW, NAME, EMAIL, PHONE,AUTH) "
				+ "VALUES(?, ?, ?, ?, ?,3) ";
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			psmt.setString(5, dto.getPhone());
			
			count = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("addMember Fail");					
		} finally{
			close(null, psmt, conn);			
		}
		
		return count>0?true:false;
	}

		@Override
		public boolean getId(String id) {
			String sql = " SELECT ID FROM MEMBER "
					+ " WHERE ID = '" + id + "'";
			boolean findId = false;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
					
			try {			
				conn = getConnection();
				psmt = conn.prepareStatement(sql);			
				rs = psmt.executeQuery(sql);
				
				while(rs.next()){			
					findId = true;			
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				close(rs, psmt, conn);			
			}
			
			return findId;
		}

		//TODO
		@Override
		public String idSearch(String EMAIL,String NAME) {
			String sql = " SELECT ID FROM MEMBER "
					+ " WHERE  EMAIL= '" + EMAIL + "' AND NAME= '" + NAME +"'";
	        String id = null;	
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
					
			try {			
				conn = getConnection();
				psmt = conn.prepareStatement(sql);			
				rs = psmt.executeQuery(sql);
				
				while(rs.next()){			
					id= rs.getString(1);			
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				close(rs, psmt, conn);			
			}
			
			return id;
		}

		@Override
		public String pwSearch(String ID,String EMAIL) {
			String sql = " SELECT PW FROM MEMBER "
					+ " WHERE ID = '" + ID + "' AND EMAIL= '"+EMAIL+"'";
	        String PW = null;	
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
					
			try {			
				conn = getConnection();
				psmt = conn.prepareStatement(sql);			
				rs = psmt.executeQuery(sql);
				
				while(rs.next()){			
					PW= rs.getString(1);			
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				close(rs, psmt, conn);			
			}
			return PW;
		}

		public List<MemberDto> memberlist(String doct_name) {
			List<MemberDto> memberlist = new ArrayList<>();
			String sql = " SELECT id, pw, name, email, phone, auth "
					+ " FROM member "
					+ " WHERE name = '" + doct_name + "' ";
			
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
					
			try {			
				conn = getConnection();
				psmt = conn.prepareStatement(sql);			
				rs = psmt.executeQuery(sql);
				
				while(rs.next()){			
					String id = rs.getString("id");
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int auth = rs.getInt("auth");
					MemberDto mem = new MemberDto(id, pw, name, email, phone, auth);
					memberlist.add(mem);			
				}
				
			} catch (SQLException e) {			
				e.printStackTrace();
			} finally{
				close(rs, psmt, conn);			
			}
			
			return memberlist;
		}
		
		@Override
		public boolean deleteMember(String id,String pw) {
			
		int count = 0;
			
			Connection conn = null;
			PreparedStatement psmt = null;
					
			String sql = " delete "
					+ "from member "
					+ "where id = ? and pw = ? " ;
			
			try {
				conn = getConnection();
				psmt = conn.prepareStatement(sql);
				
				psmt.setString(1, id);
				psmt.setString(2, pw);
				
				count = psmt.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println("Delete Member Fail");					
			} finally{
				close(null, psmt, conn);			
			}
			return count>0?true:false;
		}
}
