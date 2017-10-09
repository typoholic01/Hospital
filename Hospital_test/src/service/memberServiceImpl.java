package service;

import java.util.List;


import dto.MemberDto;

public interface memberServiceImpl {
	
	public MemberDto login(String id, String pw);
	public boolean addMember(MemberDto dto) ;
	public boolean getId(String id);
	
	public String idSearch(String email, String name);
	public String pwSearch(String pw, String email);
	public List<MemberDto> memberlist(String doct_name);
	public boolean deleteMember(String id, String pw);
		
	
	
	

}
