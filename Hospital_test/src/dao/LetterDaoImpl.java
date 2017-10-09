package dao;

import dto.MemberDto;

public interface LetterDaoImpl {

	public void letter(MemberDto mem);

	public void letterDetail(String seq, boolean isRead);

	public void letterWrite(MemberDto mem, String targetID, String receiveLetter);

	public void deleteReceiveLetter(String seq);

	public void deleteSendLetter(String seq);

	public Object[][] getRecvLetterObject(MemberDto mem);

	public Object[][] getSendLetterObject(MemberDto mem);

}
