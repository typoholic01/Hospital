package service;

import dao.CommonDao;
import dao.CommonDaoImpl;
import dao.LetterDao;
import dao.LetterDaoImpl;
import dto.MemberDto;

public class CommonService implements CommonServiceImpl {
	CommonDaoImpl commonDao = new CommonDao();
	
	@Override
	public void docIntro(MemberDto mem) {
		commonDao.docIntro(mem);
	}

	@Override
	public void notice(MemberDto mem) {
		commonDao.notice(mem);
		
	}

	@Override
	public void qa(MemberDto mem) {
		commonDao.qa(mem);
		
	}

	@Override
	public void logout() {
		commonDao.logout();
		
		
	}

}
