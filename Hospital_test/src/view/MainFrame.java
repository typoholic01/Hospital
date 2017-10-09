package view;

import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import dto.MemberDto;
import oracle.net.aso.i;
import singleton.Delegate;
import view.abbs.A_BbsListView;
import view.cbbs.C_BbsListView;
import view.preorder.PreorderMainView;

public class MainFrame extends JFrame implements ActionListener {
	//패널 선언
	private JPanel contentPane;
	ScrollPane scrollPane = new ScrollPane();
	//기본 프로퍼티
	Delegate d = Delegate.getInstance();
	MemberDto mem = new MemberDto();
	
	//버튼
	JButton btnDoctorIntro = new JButton("의사 소개");		
	JButton btnNotice = new JButton("공지 사항");		
	JButton btnQa = new JButton("상담 게시판");		
	JButton btnLogout = new JButton("로그 아웃");		
	JButton btnLetter = new JButton("☎");
	JButton btnMypage = new JButton("마이 페이지");	
	JButton btnPreorder = new JButton("진료 예약");
	JButton btnSchedule = new JButton("스케쥴 확인");
	
	
	//////////
	//JPANAL 클래스 내장용 프로퍼티
	Container con;
	///////////

	public MainFrame() {
		
	}
	
	//공통 프레임
	private void allFrame() {
		//초기화
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//요소 선언
		JLabel lblName = new JLabel(mem.getName());
		JLabel lblNim = new JLabel("님");		
		
		//위치,모양잡기
		btnDoctorIntro.setBounds(10, 85, 105, 23);
		btnNotice.setBounds(10, 125, 105, 23);
		btnQa.setBounds(10, 204, 105, 23);
		btnLogout.setBounds(730, 15, 90, 23);
		btnLetter.setBounds(660, 15, 60, 23);
		btnMypage.setBounds(545, 15, 105, 23);
		lblNim.setBounds(500, 15, 21, 15);
		lblName.setBounds(430, 15, 60, 15);
		//scrollPane.setBounds(125, 52, 701, 310);

		
		//액션 추가
		btnDoctorIntro.addActionListener(this);
		btnNotice.addActionListener(this);
		btnQa.addActionListener(this);
		btnLogout.addActionListener(this);
		btnLetter.addActionListener(this);
		btnMypage.addActionListener(this);
		
		//요소 추가
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(btnDoctorIntro);
		contentPane.add(btnNotice);
		contentPane.add(btnQa);
		contentPane.add(btnLogout);
		contentPane.add(btnLetter);
		contentPane.add(btnMypage);
		contentPane.add(lblNim);
		contentPane.add(lblName);
		//contentPane.add(scrollPane);
	}
	
	////의사 소개 화면
	public void introduceMainView(MemberDto mem) {
		this.mem = mem;
		//의사,환자 스위칭
		switch (this.mem.getAuth()) {
		case 3:
			//auth==3 환자 프레임 로드
			patientMainFrame();
			break;

		default:
			//의사 프레임 로드
			doctorMainFrame();
			break;
		}
		
		//기본 화면은 의사소개 화면
		doctorIntro();
		
		//시각화
		this.setVisible(true);
	}
	
	//환자 프레임
	public void patientMainFrame() {
		//기본프레임 로드
		allFrame();
		
		//예약 버튼 추가
		btnPreorder.setBounds(10, 165, 105, 23);
		btnPreorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		//액션 추가
		btnPreorder.addActionListener(this);
		
		contentPane.add(btnPreorder);

	}
	
	//의사 프레임
	public void doctorMainFrame() {
		//기본 프레임 로드
		allFrame();

		//스케쥴 버튼 추가
		btnSchedule.setBounds(10, 165, 105, 23);
		btnSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		//액션 추가
		btnSchedule.addActionListener(this);
		
		//요소 추가
		contentPane.add(btnSchedule);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//공통부분 Common
		if (e.getSource().equals(btnDoctorIntro)) {
			//의사 소개
			doctorIntro();
		} else if (e.getSource().equals(btnNotice)) {
			//공지 게시판
			noticeBBS();
		} else if (e.getSource().equals(btnQa)) {
			//상담 게시판
			qaBBS();
		} else if (e.getSource().equals(btnLogout)) {
			//로그아웃
			System.exit(0);
//			d.commonCtrl.logout();
//			dispose();
		} else if (e.getSource().equals(btnLetter)) {
			//쪽지창
			d.letterCtrl.letter(mem);
		}
		
		//의사부분 Doctor
		if (e.getSource().equals(btnSchedule)) {
			if (con != null) {
				super.remove(con);
			}
			con = new Container();
			ScheduleView pre;
			con.add(pre = new ScheduleView(mem));
			this.add(con).setBounds(120, 60, 900, 500);
			
		}
		
		//환자부분 Patient
		if (e.getSource().equals(btnPreorder)) {
			if (con != null) {
				super.remove(con);
			}
			con = new Container();
			PreorderMainView pre;
			con.add(pre = new PreorderMainView(mem));
			this.add(con).setBounds(120, 60, 900, 500);
		} else if (e.getSource().equals(btnMypage)) {
			d.scheduleCtrl.mypage(mem);
		}

	}
	
	//의사 소개
	private void doctorIntro() {
		
		if (con != null) {
			super.remove(con);
		}
		con = new Container();
		DoctorIntroduce docint;
		con.add(docint = new DoctorIntroduce());
		add(con).setBounds(120, 60, 900, 500);
		
	}
	
	//공지 게시판
	private void noticeBBS() {

		d.A_bbsCtrl.enterA_BbsListView(mem);
		
	}
	
	//상담 게시판
	private void qaBBS() {

		d.C_bbsCtrl.enterC_BbsListView(mem);
	}
}
