package controller;

import java.util.ArrayList;
import java.util.List;

import dto.MemberDto;
import dto.Month_dto;
import dto.PreorderDto;
import service.PreorderService;
import view.ScheduleView;

public class PreorderCtrl {
	PreorderService preorderServ = new PreorderService();
	
	public void vvv(MemberDto mem) {
		new ScheduleView(mem);
	}
	public List<PreorderDto> doct_name(String doct_name) {
		List<PreorderDto> doctlist= new ArrayList<>();
		doctlist = preorderServ.doct_name(doct_name);
		return doctlist;
	}
	
	public List<Month_dto> monthset(List<Month_dto> monthlist){
		monthlist = preorderServ.monthset(monthlist);
		return monthlist;
	}
	public void doct_insert(String doct_name, int scejul_month, int scejul_day, int scejul_time, String diction,MemberDto mem) {
		preorderServ.doct_insert(doct_name, scejul_month, scejul_day, scejul_time, diction, mem);
	}	
	public String idselect(String doct_name) {
		String id = "";
		id = preorderServ.idselect(doct_name);
		return id;
	}

}
