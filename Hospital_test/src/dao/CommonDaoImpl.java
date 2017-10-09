package dao;

import dto.MemberDto;

public interface CommonDaoImpl {

	public void docIntro(MemberDto mem);

	public void notice(MemberDto mem);

	public void qa(MemberDto mem);

	public void logout();

}
