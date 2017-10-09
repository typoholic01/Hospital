package controller;

import java.util.List;

import dto.A_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;
import service.A_BbsService;
import service.A_BbsServiceImpl;
import view.abbs.A_BbsDetailView;
import view.abbs.A_BbsListView;
import view.abbs.A_BbsWriteView;


public class A_BbsController 
{
	//A_BbsServiceImpl 연결
	A_BbsServiceImpl bbsSer = new A_BbsService();
	
	//리스트불러오기
	public List<A_BbsDto> getList()
	{
		return bbsSer.getBbsList();
	}
	//A_BbsListView불러오기
	public void enterA_BbsListView(MemberDto mem)
	{
		new A_BbsListView(getList(),mem);
		
	}
	
	//Rcount
	public void readCount(int seq)
	{
		bbsSer.readCount(seq);
	}
	
	//detail 읽기
	public A_BbsDto readBbs(int seq)
	{
		return bbsSer.readBbs(seq);
	}
	
	//A_BbsDetailView 불러오기
	public void detailBBS(int seq,MemberDto mem)
	{
		new A_BbsDetailView(seq, mem);
	}
	
	//글쓰기
	public boolean writeBbs(A_BbsDto dto)
	{
		return bbsSer.writeBbs(dto);
	}
	
	//A_BbsWriteView 불러오기
	public void writeBbsView(MemberDto mem)
	{
		new A_BbsWriteView(mem);
	}
	
	//삭제하기
	public boolean deleteBbs(int seq)
	{
		return bbsSer.deleteBbs(seq);
	}
	
	//아이디로검색하기
	public List<A_BbsDto> getIdFindList(String fStr)
	{
		return bbsSer.getIdFindList(fStr);
	}
	
	//제목으로검색하기
	public List<A_BbsDto> getTitleFindList(String fStr)
	{
		return bbsSer.getTitleFindList(fStr);
	}
	
	//내용으로검색하기
	public List<A_BbsDto> getContentFindList(String fStr)
	{
		return bbsSer.getContentFindList(fStr);
	}
	
	//검색된 A_BbsListView불러오기
	public void searchA_BbsListView(List<A_BbsDto> list,MemberDto mem)
	{
		new A_BbsListView(list,mem);
		
	}
	
	//리스트갯수구하기
	public int listCount(int num, String fStr) 
	{		
		return bbsSer.listCount(num, fStr);
	}

	//어떤리스트로이동할지
	public void moveBBsList(int choiceNum, List<A_BbsDto> list,MemberDto mem) 
	{
		bbsSer.moveBBsList(choiceNum, list,mem);
	}

	//추천수 +
	public void recoCount(int seq) 
	{
		bbsSer.recoCount(seq);
	}

	//추천수 -
	public void recoSubtract(int seq) 
	{
		bbsSer.recoSubtract(seq);	
	}

	//bool값가져오기
	public R_BbsDto getBool(int seq,MemberDto mem) 
	{
		return bbsSer.getBool(seq,mem);
	}

	//db에 넣기
	public boolean recoInsert(String id, int seq)
	{
		return bbsSer.recoInsert(id,seq);
	}

	//db에서 제거하기
	public boolean recoDelete(int seq, String id) 
	{
		return bbsSer.recoDelete(seq, id);
	}
}
