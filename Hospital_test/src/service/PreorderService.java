package service;

import java.util.ArrayList;
import java.util.List;

import dao.PreorderDao;
import dto.MemberDto;
import dto.Month_dto;
import dto.PreorderDto;

public class PreorderService {
	PreorderDao preorderDao = new PreorderDao();
	public List<Month_dto> monthset(List<Month_dto> monthlist) {
		monthlist = preorderDao.monthset(monthlist);
		return monthlist;
	}
	public List<PreorderDto> doct_name(String doct_name) {
		List<PreorderDto> doctlist= new ArrayList<>();
		doctlist = preorderDao.doct_name(doct_name);
		return doctlist;
	}
	public void doct_insert(String doct_name, int scejul_month, int scejul_day, int scejul_time, String diction, MemberDto mem) {
		preorderDao.preorderInsert(doct_name, scejul_month, scejul_day, scejul_time, diction, mem);
	}
	public String idselect(String doct_name){
		String id = "";
		id = preorderDao.idselect(doct_name);
		return id;
	}

}
