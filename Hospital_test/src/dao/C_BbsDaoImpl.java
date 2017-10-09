package dao;

import java.util.List;

import dto.C_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;

public interface C_BbsDaoImpl 
{
	public List<C_BbsDto> getBbsList();
	
	public void readCount(int seq);
	
	public C_BbsDto readBbs(int seq);
	
	public boolean writeBbs(C_BbsDto dto);
	
	public boolean deleteBbs(int seq);
	
	public List<C_BbsDto> getIdFindList(String fStr);
	
	public List<C_BbsDto> getTitleFindList(String fStr);
	
	public List<C_BbsDto> getContentFindList(String fStr);
	
	public boolean repBbs(C_BbsDto dto,MemberDto mem);
	
	public void recoCount(int seq);
	
	public void recoSubtract(int seq);
	
	public R_BbsDto getBool(int seq,MemberDto mem);
	
	public boolean recoInsert(String id, int seq);
	
	public boolean recoDelete(int seq, String id);
	
	public int listCount(int num, String fStr);
	
	public void moveBBsList(int choiceNum,List<C_BbsDto> list,MemberDto mem);
}
