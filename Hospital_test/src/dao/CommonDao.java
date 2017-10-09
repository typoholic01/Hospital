package dao;

import dto.MemberDto;
import service.A_BbsService;
import service.A_BbsServiceImpl;
import view.MainFrame;
import view.abbs.A_BbsListView;
import view.letter.LetterDetailView;
import view.letter.LetterView;
import view.login.LoginView;

public class CommonDao implements CommonDaoImpl {
	//FIXME 임시
	A_BbsServiceImpl bbsSer = new A_BbsService();
//	@Override
	public void docIntro(MemberDto mem) {
		MainFrame f = new MainFrame();
		f.introduceMainView(mem);
	}

	@Override
	public void notice(MemberDto mem) {
//		MainFrame f = new MainFrame();
//		f.noticeBBSView(mem);
		//FIXME 임시
		new A_BbsListView(bbsSer.getBbsList(), mem);
	}

	@Override
	public void qa(MemberDto mem) {
	}

	@Override
	public void logout() {
		new LoginView();
	}
}
