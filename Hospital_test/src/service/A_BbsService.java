package service;

import java.util.List;

import dao.A_BbsDao;
import dao.A_BbsDaoImpl;
import dto.A_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;

public class A_BbsService implements A_BbsServiceImpl 
{
	A_BbsDaoImpl bbs = new A_BbsDao();
	A_BbsDto dto = new A_BbsDto();

	@Override
	public List<A_BbsDto> getBbsList() 
	{
		return bbs.getBbsList();
	}

	@Override
	public void readCount(int seq)
	{
		bbs.readCount(seq);
	}

	@Override
	public A_BbsDto readBbs(int seq) 
	{
		return bbs.readBbs(seq);
	}

	@Override
	public boolean writeBbs(A_BbsDto dto) 
	{
		return bbs.writeBbs(dto);
	}

	@Override
	public boolean deleteBbs(int seq) 
	{
		return bbs.deleteBbs(seq);
	}

	@Override
	public List<A_BbsDto> getIdFindList(String fStr) 
	{
		return bbs.getIdFindList(fStr);
	}

	@Override
	public List<A_BbsDto> getTitleFindList(String fStr) 
	{
		return bbs.getTitleFindList(fStr);
	}

	@Override
	public List<A_BbsDto> getContentFindList(String fStr) 
	{
		return bbs.getContentFindList(fStr);
	}

	@Override
	public int listCount(int num, String fStr) {		
		return bbs.listCount(num, fStr);
	}

	@Override
	public void moveBBsList(int choiceNum, List<A_BbsDto> list,MemberDto mem) 
	{
		bbs.moveBBsList(choiceNum, list,mem);
	}

	@Override
	public void recoCount(int seq) 
	{
		bbs.recoCount(seq);
	}

	@Override
	public void recoSubtract(int seq) 
	{
		bbs.recoSubtract(seq);	
	}

	@Override
	public R_BbsDto getBool(int seq,MemberDto mem) 
	{
		return bbs.getBool(seq,mem);
	}

	@Override
	public boolean recoInsert(String id, int seq)
	{
		return bbs.recoInsert(id,seq);
	}

	@Override
	public boolean recoDelete(int seq, String id) 
	{
		return bbs.recoDelete(seq, id);
	}
	
	
	

}
