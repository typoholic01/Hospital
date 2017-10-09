package view.abbs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.A_BbsDto;
import dto.MemberDto;
import singleton.Delegate;

public class A_BbsWriteView extends JFrame 
{


	private static final long serialVersionUID = 1L;

	//변수
	JTextField writerText;
	JTextField titleText;
	JTextArea contentArea;
	
	JButton btn;
	JButton backBtn;
	Delegate dg = Delegate.getInstance();
	
	//생성자
	public A_BbsWriteView(MemberDto mem) 
	{
		super("글쓰기");
		setLayout(null);
		
		JLabel writerLabel = new JLabel("작성자 : ");
		writerLabel.setBounds(10, 10, 120, 15);
		add(writerLabel);
		
		//아이디정보가져오기
		String id = mem.getId();
		
		writerText = new JTextField(id);
		writerText.setBounds(120, 10, 200, 20);
		writerText.setEditable(false);		
		add(writerText);
		
		JLabel titleLabel = new JLabel("제목:");
		titleLabel.setBounds(10, 40, 120, 15);
		add(titleLabel);
		
		titleText = new JTextField();
		titleText.setBounds(120, 40, 350, 20);
		add(titleText);
		
		JLabel contentLabel = new JLabel("내용 : ");
		contentLabel.setBounds(10, 70, 120, 15);
		add(contentLabel);
		
		contentArea = new JTextArea();	
		contentArea.setLineWrap(true);	
		
		JScrollPane scrPane = new JScrollPane(contentArea);
		scrPane.setPreferredSize(new Dimension(200, 120));
		scrPane.setBounds(10, 100, 460, 300);
		add(scrPane);
		
		//글쓰기버튼+명령
		btn = new JButton("글쓰기");
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
				String id = mem.getId();
				String title = titleText.getText();
				String content = contentArea.getText();
				String wdate = sdf.format(d);
				
				//dto가져오기
				A_BbsDto dto = new A_BbsDto(0, title, content, 0,0, wdate, id, 0);
				
				
				boolean b = dg.A_bbsCtrl.writeBbs(dto);
				if(b)
				{
					JOptionPane.showMessageDialog(null, "성공적으로 추가되었습니다");
					new A_BbsListView(dg.A_bbsCtrl.getList(), mem);
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
				dg.A_bbsCtrl.enterA_BbsListView(mem);
				dispose();
			}
		});
		
		
		
	}


}




