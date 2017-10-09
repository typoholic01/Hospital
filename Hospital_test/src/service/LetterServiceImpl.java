package service;

import dto.MemberDto;

public interface LetterServiceImpl {

	public void letter(MemberDto mem);

	public void letterDetail(String seq, boolean isRead);

	public Object[][] letterWrite(MemberDto mem, String targetID, String receiveLetter);

	public void deleteReceiveLetter(String seq);

	public void deleteSendLetter(String seq);
	
	public Object[][] getRecvLetterObject(MemberDto mem);

	public Object[][] getSendLetterObject(MemberDto mem);

}
