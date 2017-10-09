package view.cbbs;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.C_BbsDto;
import dto.MemberDto;
import singleton.Delegate;

public class C_BbsComentView extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	//멤버변수
	JTextField writerText;
	JTextField titleText;
	JTextArea contentArea;
	JButton btn;
	JButton backBtn;
	Delegate dg = Delegate.getInstance();
	Checkbox check;
	int secreat = 1; //초기값은 공개댓글로 설정
	
	//생성자
	public C_BbsComentView(int seq, String title,int orderNum, int step, int depth,MemberDto mem) 
	{
		setTitle("답글달기");
		setLayout(null);
		
		JLabel writerLabel = new JLabel("작성자 : ");
		writerLabel.setBounds(10, 10, 120, 15);
		add(writerLabel);
		
		//아이디정보가져오기
		writerText = new JTextField(dg.getId);
		writerText.setBounds(120, 10, 200, 20);
		writerText.setEditable(false);		
		add(writerText);
		
		//비밀답글체크박스
		JLabel checkLabel = new JLabel("비밀답글 체크 ");
		checkLabel.setBounds(340, 10, 80, 20);
		add(checkLabel);
		
		check = new Checkbox();
		check.setBounds(430, 10, 30, 20);
		check.setState(false);
		add(check);
		check.addItemListener(new ItemListener() 
		{
			public void itemStateChanged(ItemEvent e) 
			{
				
				if(check.getState()) //check가 되어있을때 (true일때)
				{				
					secreat = 0;
					
				}
				else //check가 안되어있을때 (false)
				{
					secreat = 1;
				}
			}
		});
		
		JLabel titleLabel = new JLabel("제목:");
		titleLabel.setBounds(10, 40, 120, 15);
		add(titleLabel);
		
		titleText = new JTextField(title);
		titleText.setBounds(120, 40, 350, 20);
		titleText.setEditable(false);
		add(titleText);
		
		JLabel contentLabel = new JLabel("내용:");
		contentLabel.setBounds(10, 80, 60, 15);
		add(contentLabel);
		
		contentArea = new JTextArea();	
		contentArea.setLineWrap(true);	
		
		JScrollPane scrPane = new JScrollPane(contentArea);
		scrPane.setPreferredSize(new Dimension(200, 120));
		scrPane.setBounds(10, 100, 460, 300);
		add(scrPane);
		
		//글쓰기버튼+명령
		btn = new JButton("답글쓰기");
		btn.setBounds(150, 420, 100, 20);		
		add(btn);
		btn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
				System.out.println("sdf = " + sdf.format(d));
				
				//값 선언
				String id = dg.getId;
				
				String title = titleText.getText();
				
				String content = contentArea.getText();
				String wdate = sdf.format(d);
				
				//dto가져오기								
				C_BbsDto dto = new C_BbsDto(seq, orderNum, step, depth, title, content, 0, 0, secreat, wdate, id, 0);
				boolean b = dg.C_bbsCtrl.repBbs(dto, mem);
				if(b)
				{
					JOptionPane.showMessageDialog(null, "성공적으로 추가되었습니다");
					new C_BbsListView(dg.C_bbsCtrl.getList(), mem);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "추가되지 못했습니다");
				}
				
			}
		});
		
		
		setBounds(100, 100, 500, 500);
		setVisible(true);	
		
		backBtn = new JButton("뒤로가기");
		backBtn.setBounds(10, 420, 100, 20);		
		add(backBtn);
		backBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dg.C_bbsCtrl.enterC_BbsListView(mem);
				dispose();
			}
		});
	}

}




