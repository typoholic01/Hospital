package service;

import dto.MemberDto;

public interface CommonServiceImpl {

	public void docIntro(MemberDto mem);

	public void notice(MemberDto mem);

	public void qa(MemberDto mem);

	public void logout();

}
