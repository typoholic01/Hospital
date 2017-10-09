package service;

import java.util.ArrayList;
import java.util.List;
import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.MemberDto;
import view.login.LoginView;

public class memberService implements memberServiceImpl {
	
	MemberDaoImpl memdao = new MemberDao();

	@Override
	public MemberDto login(String id, String pw) {
		MemberDto mem = memdao.login(id,pw);
		
		if (mem == null) {
		} else {
			memdao.OpenLoginView(mem);
		}
		
		
		return mem;
	}

	@Override
	public boolean addMember(MemberDto dto) {
		return memdao.addMember(dto);
	}

	@Override
	public boolean getId(String id) {
		return memdao.getId(id);
	}

	@Override
	public String idSearch(String email,String name) {
		return memdao.idSearch(email,name);
	}

	@Override
	public String pwSearch(String id,String email) {
		return memdao.pwSearch(id,email);
	}

	@Override
	public List<MemberDto> memberlist(String doct_name) {
		List<MemberDto> memberlist = new ArrayList<>();
		memberlist = memdao.memberlist(doct_name);
		return memberlist;
	}

	@Override
	public boolean deleteMember(String id, String pw) {
		return memdao.deleteMember(id,pw);
		
	}
	
	
	
	
	
	
	
	
	

}
