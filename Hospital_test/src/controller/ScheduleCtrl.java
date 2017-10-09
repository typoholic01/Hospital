package controller;

import dto.MemberDto;
import service.ScheduleService;
import service.ScheduleServiceImpl;

public class ScheduleCtrl {
	ScheduleServiceImpl scheduleService = new ScheduleService();

	public void preorder(MemberDto mem) {
		scheduleService.preorder(mem);
		
	}
	

	public void mypage(MemberDto mem) {
		scheduleService.mypage(mem);
		
	}


	public void preorderCancle(String seq) {
		scheduleService.preorderCancle(seq);
	}


	public void backMenu(MemberDto mem) {
		scheduleService.backMenu(mem);
	}
	

	public void schedule(MemberDto mem) {
		scheduleService.schedule(mem);
		
	}


	public Object[][] getDataPreorder(MemberDto mem) {
		return scheduleService.getDataPreorder(mem);
	}
}
