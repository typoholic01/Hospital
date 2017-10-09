package controller;

import dto.MemberDto;
import service.CommonService;
import service.CommonServiceImpl;
import service.LetterService;
import service.LetterServiceImpl;

public class LetterCtrl {
	LetterServiceImpl letterService = new LetterService();

	public void letter(MemberDto mem) {
		letterService.letter(mem);
		
	}

	public void letterDetail(String seq, boolean isRead) {
		letterService.letterDetail(seq,isRead);
		
	}

	public Object[][] letterWrite(MemberDto mem, String targetID, String receiveLetter) {
		return letterService.letterWrite(mem,targetID,receiveLetter);
		
	}

	public void deleteReceiveLetter(String seq) {
		letterService.deleteReceiveLetter(seq);
		
	}

	public void deleteSendLetter(String seq) {
		letterService.deleteSendLetter(seq);
		
	}
	public Object[][] getRecvLetterObject(MemberDto mem) {
		return letterService.getRecvLetterObject(mem);
		
	}

	public Object[][] getSendLetterObject(MemberDto mem) {
		return letterService.getSendLetterObject(mem);
	}
}
