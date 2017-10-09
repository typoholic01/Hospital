package dao;

import java.util.List;

import dto.MemberDto;

public interface MemberDaoImpl {
	
	public MemberDto login(String id, String pw);
	public boolean addMember(MemberDto dto) ;
	
	public boolean getId(String id);
	
	public String idSearch(String email, String name);
	public String pwSearch(String id, String email);
	public List<MemberDto> memberlist(String doct_name);
	public void OpenLoginView(MemberDto mem);
	public boolean deleteMember(String id, String pw);
	

}
