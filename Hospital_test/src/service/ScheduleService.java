package service;

import dao.ScheduleDao;
import dao.ScheduleDaoImpl;
import dto.MemberDto;

public class ScheduleService implements ScheduleServiceImpl {
	ScheduleDaoImpl scheduleDao = new ScheduleDao();


	@Override
	public void preorder(MemberDto mem) {
		scheduleDao.preorder(mem);
		
	}

	@Override
	public void mypage(MemberDto mem) {
		Object[][] scheduleData = scheduleDao.getDataPreorder(mem);
		
		scheduleDao.mypage(mem,scheduleData);
		
		
	}

	@Override
	public void preorderCancle(String seq) {
		scheduleDao.preorderCancle(seq);
		
	}

	@Override
	public void backMenu(MemberDto mem) {
		scheduleDao.backMenu(mem);
		
	}


	@Override
	public void schedule(MemberDto mem) {
		scheduleDao.schedule(mem);
		
	}

	@Override
	public Object[][] getDataPreorder(MemberDto mem) {
		return scheduleDao.getDataPreorder(mem);
	}
}
