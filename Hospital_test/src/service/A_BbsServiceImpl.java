package service;

import java.util.List;

import dto.A_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;

public interface A_BbsServiceImpl 
{
	public List<A_BbsDto> getBbsList();

	public void readCount(int seq);
	
	public A_BbsDto readBbs(int seq);
	
	public boolean writeBbs(A_BbsDto dto);
	
	public boolean deleteBbs(int seq);
	
	public List<A_BbsDto> getIdFindList(String fStr);
	
	public List<A_BbsDto> getTitleFindList(String fStr);
	
	public List<A_BbsDto> getContentFindList(String fStr);
	
    public int listCount(int num, String fStr);
	
	public void moveBBsList(int choiceNum,List<A_BbsDto> list,MemberDto mem);
	
	public void recoCount(int seq);
	
	public void recoSubtract(int seq);
	
	public R_BbsDto getBool(int seq,MemberDto mem);
	
	public boolean recoInsert(String id, int seq);
	
	public boolean recoDelete(int seq, String id);
}
