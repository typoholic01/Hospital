package dao;

import dto.MemberDto;

public interface ScheduleDaoImpl {

	
	public void mypage(MemberDto mem,Object[][] scheduleData);
	
	public void preorder(MemberDto mem);

	public void preorderCancle(String seq);

	public void backMenu(MemberDto mem);

	public void schedule(MemberDto mem);

	public Object[][] getDataPreorder(MemberDto mem);
}
