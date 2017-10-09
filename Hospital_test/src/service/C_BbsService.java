package service;

import java.util.List;

import dao.C_BbsDao;
import dao.C_BbsDaoImpl;
import dto.C_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;


public class C_BbsService implements C_BbsServiceImpl 
{
	//Dao와 연결
	C_BbsDaoImpl bbs = new C_BbsDao();
	//Dto와 연결
	C_BbsDto dto = new C_BbsDto();
	
	@Override
	public List<C_BbsDto> getBbsList() 
	{
		return bbs.getBbsList();
	}

	@Override
	public void readCount(int seq)
	{
		bbs.readCount(seq);
	}

	@Override
	public C_BbsDto readBbs(int seq) 
	{
		return bbs.readBbs(seq);
	}

	@Override
	public boolean writeBbs(C_BbsDto dto) 
	{
		return bbs.writeBbs(dto);
	}

	@Override
	public boolean deleteBbs(int seq) 
	{
		return bbs.deleteBbs(seq);
	}

	@Override
	public List<C_BbsDto> getIdFindList(String fStr) 
	{
		return bbs.getIdFindList(fStr);
	}

	@Override
	public List<C_BbsDto> getTitleFindList(String fStr) 
	{
		return bbs.getTitleFindList(fStr);
	}

	@Override
	public List<C_BbsDto> getContentFindList(String fStr) 
	{
		return bbs.getContentFindList(fStr);
	}

	@Override
	public boolean repBbs(C_BbsDto dto,MemberDto mem) 
	{
		return bbs.repBbs(dto,mem);
	}

	@Override
	public void recoCount(int seq) 
	{
		bbs.recoCount(seq);
	}

	@Override
	public R_BbsDto getBool(int seq,MemberDto mem) 
	{
		return bbs.getBool(seq, mem);
	}

	@Override
	public void recoSubtract(int seq) 
	{
		bbs.recoSubtract(seq);
	}

	@Override
	public boolean recoInsert(String id, int seq)
	{
		return  bbs.recoInsert(id,seq);
	}

	@Override
	public boolean recoDelete(int seq, String id) 
	{
		return bbs.recoDelete(seq, id);
	}

	@Override
	public int listCount(int num, String fStr)
	{		
		return bbs.listCount(num, fStr);
	}

	@Override
	public void moveBBsList(int choiceNum, List<C_BbsDto> list,MemberDto mem) 
	{
		bbs.moveBBsList(choiceNum, list,mem);
		
	}

}
