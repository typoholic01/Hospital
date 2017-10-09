package view.cbbs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import dto.C_BbsDto;
import dto.MemberDto;
import dto.R_BbsDto;
import singleton.Delegate;

public class C_BbsDetailView extends JFrame 
{
	
	private static final long serialVersionUID = 1L;
	//멤버변수
	JTextField idTextfield;
	JTextField wdateTextfield;
	JTextField readCountTextfield;
	JTextField titleTextfield;	
	JTextArea contentArea;	
	
	MemberDto mem;
	
	public C_BbsDetailView(int seq,MemberDto mem) 
	{
		setTitle("게시물");
		setLayout(null);
		this.mem = mem;
		
		Delegate dg = Delegate.getInstance();
		C_BbsDto dto = dg.C_bbsCtrl.readBbs(seq);
		R_BbsDto dto2 = dg.C_bbsCtrl.getBool(seq, mem);
		
		JLabel writerLabel = new JLabel("작성자 : ");
		writerLabel.setBounds(10, 10, 60, 15);
		add(writerLabel);
		
		
		idTextfield = new JTextField(dto.getId());
		idTextfield.setBounds(120, 10, 200, 20);
		idTextfield.setEditable(false);		
		add(idTextfield);
		
		JLabel writedLabel = new JLabel("작성일:");
		writedLabel.setBounds(10, 40, 60, 15);
		add(writedLabel);
		
		wdateTextfield = new JTextField(dto.getWdate());
		wdateTextfield.setBounds(120, 40, 200, 20);
		wdateTextfield.setEditable(false);		
		add(wdateTextfield);
		
		JLabel readLabel = new JLabel("조회수:");
		readLabel.setBounds(10, 70, 60, 15);
		add(readLabel);
		
		readCountTextfield = new JTextField(dto.getRcount() + "");
		readCountTextfield.setBounds(120, 70, 200, 20);
		readCountTextfield.setEditable(false);		
		add(readCountTextfield);
		
		JLabel titleLabel = new JLabel("제목:");
		titleLabel.setBounds(10, 100, 60, 15);
		add(titleLabel);
		
		titleTextfield = new JTextField(dto.getTitle());
		titleTextfield.setBounds(120, 100, 300, 20);
		titleTextfield.setEditable(false);		
		add(titleTextfield);
		
		JLabel contentLabel = new JLabel("내용:");
		contentLabel.setBounds(10, 130, 60, 15);
		add(contentLabel);
		
		contentArea = new JTextArea(dto.getContent());
		contentArea.setEditable(false);
		contentArea.setLineWrap(true);	
		
		JScrollPane scrPane = new JScrollPane(contentArea);
		scrPane.setPreferredSize(new Dimension(200, 120));
		scrPane.setBounds(10, 160, 460, 300);
		add(scrPane);
		
		//뒤로가기 버튼+명령
		JButton bbsBtn = new JButton("뒤로가기");
		bbsBtn.setBounds(10, 480, 100, 20);		
		add(bbsBtn);
		bbsBtn.addActionListener(new ActionListener() 
		{			
			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				dg.getTextF=""; //기존텍스트빈칸으로 초기화
				dg.choiceNum=0; //기존값으로 초기화
				dg.C_bbsCtrl.enterC_BbsListView(mem);
				dispose();
				return;
			}
		});
		
		//게시글삭제하기 버튼+명령 (접속아이디와 일치했을때 또는 관리자일때)
		JButton delBtn = null;
		if(dto.getId().equals(dg.getId) || (dg.getAuth==0))
		{
			delBtn = new JButton("삭제");
			delBtn.setBounds(130, 480, 100, 20);
			add(delBtn);	
			
			delBtn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{					
					boolean b = dg.C_bbsCtrl.deleteBbs(seq);
					if(b)
					{
						JOptionPane.showMessageDialog(null, "성공적으로 삭제 되었습니다");
						dispose();
						dg.C_bbsCtrl.enterC_BbsListView(mem);
					}
				}
			});
		}
		//댓글달기 버튼+명령(누구나 달수있게 만듬)
	
		JButton replBtn =  new JButton("댓글달기");
		replBtn.setBounds(250, 480, 100, 20);
		add(replBtn);
		replBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				dg.C_bbsCtrl.callComentView(seq, dto.getTitle(), dto.getOrderNum(), dto.getStep(), dto.getDepth(), mem);
				
			}
		});
		
		//접속자와 작성자가 같을때 추천하기버튼 비활성화하기
		//추천은 한번만 누르게 만듬 (추천을 누르게되면 추천취소 버튼으로 바꾸기)
		//bool값 가져오기
		//dto2.getBool()
		dto2 = dg.C_bbsCtrl.getBool(seq, mem);
			
		if(!dto.getId().equals(dg.getId))
		{
			if(dto2 != null) //추천취소버튼활성화
			{
				JButton xrecoBtn = new JButton("추천취소");
				xrecoBtn.setBounds(370, 480, 100, 20);
				add(xrecoBtn);
				xrecoBtn.addActionListener(new ActionListener() 
				{	
					public void actionPerformed(ActionEvent e) 
					{
						//추천수 감소
						dg.C_bbsCtrl.recoSubtract(seq);
						//R_BbsDto소속 DB BBS_R에서 해당 seq, id 에 해당하는 정보를 지운다.
						boolean b =dg.C_bbsCtrl.recoDelete(seq, mem.getId());
						if(b)
						{
							JOptionPane.showMessageDialog(null, "추천취소 성공");
							dg.C_bbsCtrl.detailBBS(seq, mem);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "추천취소 실패");
						}
					}
				}
				);
			}
			else //추천버튼활성화
			{
				JButton recoBtn = new JButton("추천");
				recoBtn.setBounds(370, 480, 100, 20);
				add(recoBtn);
				recoBtn.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						//추천수 증가
						dg.C_bbsCtrl.recoCount(seq);
						//BBS_R에 INSERT를 통해 해당 SEQ,ID 정보를 이용해 BOOL =0 으로 저장하도록한다.
						boolean b = dg.C_bbsCtrl.recoInsert(dg.getId,seq);
						if(b)
						{
							JOptionPane.showMessageDialog(null, "추천 성공");
							dg.C_bbsCtrl.detailBBS(seq, mem);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "추천 실패");
						}
					}
				});						
			}
		}
		
		
		
		
		
		
		//윈도우설정
		setBounds(100, 100, 500, 600);
		setVisible(true);
	}

}
