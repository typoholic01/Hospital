package service;

import dao.LetterDao;
import dao.LetterDaoImpl;
import dto.MemberDto;

public class LetterService implements LetterServiceImpl {
	LetterDaoImpl letterDao = new LetterDao();


	@Override
	public void letter(MemberDto mem) {
		letterDao.letter(mem);
		
	}

	@Override
	public void letterDetail(String seq, boolean isRead) {
		letterDao.letterDetail(seq,isRead);
		
	}

	@Override
	public Object[][] letterWrite(MemberDto mem, String targetID, String receiveLetter) {
		letterDao.letterWrite(mem,targetID,receiveLetter);
		Object[][] receiveData = letterDao.getRecvLetterObject(mem);
		
		return receiveData;
	}

	@Override
	public void deleteReceiveLetter(String seq) {
		letterDao.deleteReceiveLetter(seq);
	}

	@Override
	public void deleteSendLetter(String seq) {
		letterDao.deleteSendLetter(seq);
		
	}

	@Override
	public Object[][] getRecvLetterObject(MemberDto mem) {
		return letterDao.getRecvLetterObject(mem);
		
	}

	@Override
	public Object[][] getSendLetterObject(MemberDto mem) {
		return letterDao.getSendLetterObject(mem);
	}

}
