package service;

import dto.MemberDto;

public interface ScheduleServiceImpl {

	public void preorder(MemberDto mem);

	public void mypage(MemberDto mem);

	public void preorderCancle(String seq);

	public void backMenu(MemberDto mem);

	public void schedule(MemberDto mem);

	public Object[][] getDataPreorder(MemberDto mem);
}
