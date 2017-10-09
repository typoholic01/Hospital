package singleton;

import controller.A_BbsController;
import controller.C_BbsController;
import controller.CommonCtrl;
import controller.LetterCtrl;
import controller.ScheduleCtrl;
import controller.MemberCtrl;
import controller.PreorderCtrl;

public class Delegate {
	private static Delegate single = null;
	//BBS_A 변수
	public int _getPageNum;
	public int _returnPage;
	public String _getTextF="";
	public int _choiceNum=0;
	
	//BBS_C 변수
	public int getPageNum;
	public int returnPage;
	public String getTextF="";
	public int choiceNum=0; //choice값 저장(초기값 : 제목형식)
	
	//아이디저장값과 관리자저장값( 게시판을 위한 값)
	public String getId="";
	public int getAuth=3;
	
	//컨트롤러 변수
	public A_BbsController A_bbsCtrl;
	public C_BbsController C_bbsCtrl;
	public CommonCtrl commonCtrl;
	public ScheduleCtrl scheduleCtrl;
	public MemberCtrl memCtrl;
	public LetterCtrl letterCtrl;
	public PreorderCtrl preorderCtrl;
	
	private Delegate() {
		commonCtrl = new CommonCtrl();
		scheduleCtrl = new ScheduleCtrl();
		memCtrl = new MemberCtrl();
		letterCtrl = new LetterCtrl();
		preorderCtrl = new PreorderCtrl();
		
		A_bbsCtrl = new A_BbsController();
		C_bbsCtrl = new C_BbsController();
		
	}
	
	public static Delegate getInstance() {
		if (single == null) {
			single = new Delegate();
		}
		
		return single;
	}

}
