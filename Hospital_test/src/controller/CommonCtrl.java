package controller;

import dto.MemberDto;
import service.CommonService;
import service.CommonServiceImpl;
import view.DoctorIntroduce;



public class CommonCtrl {
	CommonServiceImpl commonService = new CommonService();

	public void docIntro() {
		new DoctorIntroduce();
	}

	public void notice(MemberDto mem) {
		commonService.notice(mem);
		
	}

	public void qa(MemberDto mem) {
		commonService.qa(mem);
		
	}

	public void logout() {
		commonService.logout();
		
	}
}
