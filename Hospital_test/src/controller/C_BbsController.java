package controller;

import java.util.List;

import dto.C_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;
import service.C_BbsService;
import service.C_BbsServiceImpl;
import view.cbbs.C_BbsComentView;
import view.cbbs.C_BbsDetailView;
import view.cbbs.C_BbsListView;
import view.cbbs.C_BbsWriteView;

public class C_BbsController 
{
	//C_BbsServiceImpl 연결
	C_BbsServiceImpl bbsSer = new C_BbsService();
	
	//리스트불러오기
	public List<C_BbsDto> getList()
	{
		return bbsSer.getBbsList();
	}
	//C_BbsListView불러오기
	public void enterC_BbsListView(MemberDto mem)
	{
		new C_BbsListView(getList(), mem);
		
	}
	
	//Rcount
	public void readCount(int seq)
	{
		bbsSer.readCount(seq);
	}
	
	//detail 읽기
	public C_BbsDto readBbs(int seq)
	{
		return bbsSer.readBbs(seq);
	}
	
	//c_BbsDetailView 불러오기
	public void detailBBS(int seq,MemberDto mem)
	{
		new C_BbsDetailView(seq, mem);
		
	}
	
	//글쓰기
	public boolean writeBbs(C_BbsDto dto)
	{
		return bbsSer.writeBbs(dto);
	}
	
	//A_BbsWriteView 불러오기
	public void writeBbsView(MemberDto mem)
	{
		new C_BbsWriteView(mem);
	}
	
	//삭제하기
	public boolean deleteBbs(int seq)
	{
		return bbsSer.deleteBbs(seq);
	}
	
	//아이디로검색하기
	public List<C_BbsDto> getIdFindList(String fStr)
	{
		return bbsSer.getIdFindList(fStr);
	}
	
	//제목으로검색하기
	public List<C_BbsDto> getTitleFindList(String fStr)
	{
		return bbsSer.getTitleFindList(fStr);
	}
	
	//내용으로검색하기
	public List<C_BbsDto> getContentFindList(String fStr)
	{
		return bbsSer.getContentFindList(fStr);
	}
	
	//검색된 A_BbsListView불러오기
	public void searchC_BbsListView(List<C_BbsDto> list,MemberDto mem)
	{
		new C_BbsListView(list, mem);
		
	}
	
	//댓글달기 
	public boolean repBbs(C_BbsDto dto,MemberDto mem)
	{
		return bbsSer.repBbs(dto,mem);
	}
	//C_BbsComentView 불러오기
	public void callComentView(int seq, String title, int orderNum, int step ,int depth,MemberDto mem)
	{
		new C_BbsComentView(seq, title, orderNum, step, depth, mem);
	}
	
	//추천카운트
	public void recoCount(int seq) 
	{
		bbsSer.recoCount(seq);
	}
	
	//bool정보 가져오기
	public R_BbsDto getBool(int seq,MemberDto mem)
	{
		return bbsSer.getBool(seq,mem);
	}
	
	//추천취소카운트
	public void recoSubtract(int seq) 
	{
		bbsSer.recoSubtract(seq);
	}
	
	//추천취소버튼활성화
	public boolean recoInsert(String id, int seq)
	{
		return bbsSer.recoInsert(id,seq);
	}
	
	//추천버튼활성화
	public boolean recoDelete(int seq, String id)
	{
		return bbsSer.recoDelete(seq, id);
	}
	
	//listCount (총 글의 갯수 구하기)
	public int listCount(int num, String fStr)
	{
		return bbsSer.listCount(num, fStr);
	}
	
	//어떤리스트로 이동할지 일반method
	public void moveBBsList(int choiceNum,List<C_BbsDto> list,MemberDto mem)
	{
		bbsSer.moveBBsList(choiceNum, list,mem);
	}
	

}
