package view.abbs;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dto.A_BbsDto;
import dto.MemberDto;
import singleton.Delegate;

public class A_BbsListView extends JFrame 
implements ActionListener, MouseListener, ItemListener 
{
	
	private static final long serialVersionUID = 1L;
	//멤버변수
	private JTable jTable;
	private JScrollPane jScrPane;	
	private JButton writeBtn;
	Choice choice;
	
	
	String columnNames[] = 
		{
			"번호", "제목", "작성자","작성일","조회수"	,"추천"
		};
	
	Object rowData[][];	
	DefaultTableModel model;
	List<A_BbsDto> list;
	Delegate dg = Delegate.getInstance();	
		
	private JTextField selectField;
	private JButton selectBtn;
	
	//페이징
	JButton pageBtn[];
	JButton pageMoveBtn[];
	int currentPage;
	
	
	MemberDto mem;
	//생성자
	public A_BbsListView(List<A_BbsDto> list,MemberDto mem) 
	{
		setTitle("공지사항");
		setLayout(null);
		this.mem = mem;
		
		//아이디정보가져오기 
		//String id = dg.memCtrl.getLoginId();
		
		//리스트정보불러오기
		this.list = list;
		int n = 1;
		
		//페이지 버튼생성
		pageBtn = new JButton[dg._returnPage+5];
		pageMoveBtn = new JButton[2];
		
		//현재페이지
		currentPage = dg._getPageNum;
		
		//버튼생성(번호로이동버튼)
		for (int i= dg._returnPage; i< dg._returnPage+5; i++) 
		{
			pageBtn[i] = new JButton((i+1)+"");			
			pageBtn[i].setBounds(340+(50*(i-dg._returnPage)), 355, 50, 35);	
			pageBtn[i].addActionListener(this);
			pageBtn[i].setFont(new Font("돋움", Font.PLAIN, 13));
			if(currentPage==i) //현재 페이지에따라서 버튼 색깔에 변화를줌
			{
				pageBtn[i].setBackground(Color.GRAY);
			}
			else
			{
				pageBtn[i].setBackground(Color.LIGHT_GRAY);
			}
			
			add(pageBtn[i]);
		}
		//버튼생성(화살표로이동버튼)
		pageMoveBtn[0] = new JButton("◁");
		pageMoveBtn[0].setBounds(295, 355, 45, 35);
		pageMoveBtn[0].addActionListener(this);
		pageMoveBtn[0].setFont(new Font("돋움", Font.PLAIN, 10));
		pageMoveBtn[0].setBackground(Color.LIGHT_GRAY);
		add(pageMoveBtn[0]);
		pageMoveBtn[1] = new JButton("▷");
		pageMoveBtn[1].setBounds(590, 355, 45, 35);
		pageMoveBtn[1].addActionListener(this);
		pageMoveBtn[1].setFont(new Font("돋움", Font.PLAIN, 10));
		pageMoveBtn[1].setBackground(Color.LIGHT_GRAY);
		add(pageMoveBtn[1]);
		
		
		rowData = new Object[list.size()][6];
		for (int i = 0; i < list.size(); i++) 
		{
			A_BbsDto dto = list.get(i);
			rowData[i][0] = (currentPage*17)+n; //번호
			rowData[i][1] = dto.getTitle(); //제목
			rowData[i][2] = dto.getId(); //작성자
			rowData[i][3] = dto.getWdate(); //작성일
			rowData[i][4] = dto.getRcount(); //조회수
			rowData[i][5] = dto.getReco(); //추천수
			n++;
		}
		
		model = new DefaultTableModel(columnNames, 0);
		
		model.setDataVector(rowData, columnNames);	
		
		jTable = new JTable(model);	
		jTable.addMouseListener(this);
		
		jTable.getColumnModel().getColumn(0).setMaxWidth(50);
		jTable.getColumnModel().getColumn(1).setMaxWidth(300);
		jTable.getColumnModel().getColumn(2).setMaxWidth(100);
		jTable.getColumnModel().getColumn(3).setMaxWidth(150);
		jTable.getColumnModel().getColumn(4).setMaxWidth(80);
		jTable.getColumnModel().getColumn(5).setMaxWidth(80);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		jTable.getColumn("번호").setCellRenderer(celAlignCenter);
		jTable.getColumn("작성자").setCellRenderer(celAlignCenter);
		jTable.getColumn("작성일").setCellRenderer(celAlignCenter);
		
		jScrPane = new JScrollPane(jTable);
		
		jScrPane.setBounds(10, 50, 750, 300);
		add(jScrPane);		
		
		//auth넘버가져오기
		if(mem.getAuth()==0)
		{
			writeBtn = new JButton("글쓰기");
			writeBtn.addActionListener(this);
			writeBtn.setBounds(10, 420, 100, 20);		
			add(writeBtn);
		}
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 800, 500);
		setVisible(true);	
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//초이스
		choice = new Choice();
		choice.setBounds(130, 420, 60, 40);
		choice.add("제목");
		choice.add("아이디");
		choice.add("내용");
		choice.addItemListener(this);
		add(choice);
		
		//검색
		selectField = new JTextField();
		selectField.setBounds(220,420, 150, 20);
		add(selectField);
		
		selectBtn = new JButton("검색");
		selectBtn.setBounds(390, 420, 100, 20);	
		selectBtn.addActionListener(this);
		add(selectBtn);
	}
	

	

	@Override
	public void mouseClicked(MouseEvent e) 
	{		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	//마우스 눌렀을때 실행되는 부분
	public void mousePressed(MouseEvent e) 
	{
		int rowNum = jTable.getSelectedRow();
		JOptionPane.showMessageDialog(null, 
				"클릭한 게시글로 이동합니다");
		
		//rcount
		dg.A_bbsCtrl.readCount(list.get(rowNum).getSeq());
		
		//읽기로 이동
		dg.A_bbsCtrl.detailBBS(list.get(rowNum).getSeq(), mem);
		this.dispose();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	//버튼눌렀을때 실행되는부분
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();
		
		if(obj ==writeBtn)
		{
			dg.A_bbsCtrl.writeBbsView(mem);
			this.dispose();
		}
		else if(obj ==selectBtn)
		{
			//JOptionPane.showMessageDialog(null, choiceNum);
			if(selectField.getText().length()<1)
			{
				dg._getTextF="";
				dg._choiceNum=0;
				dg.A_bbsCtrl.enterA_BbsListView(mem);
				this.dispose();
				return;
			}
			else if(dg._choiceNum ==0) //제목으로검색
			{
				dg._getTextF= selectField.getText();
				dg._getPageNum=0;
				list = dg.A_bbsCtrl.getTitleFindList(selectField.getText());
				dg.A_bbsCtrl.searchA_BbsListView(list, mem);
				this.dispose();
			}
			else if(dg._choiceNum ==1) //아이디로검색
			{
				dg._getTextF = selectField.getText();
				dg._getPageNum=0;
				list = dg.A_bbsCtrl.getIdFindList(selectField.getText());
				dg.A_bbsCtrl.searchA_BbsListView(list, mem);
				this.dispose();
			}
			else if(dg._choiceNum ==2) //내용으로검색
			{
				dg._getTextF = selectField.getText();
				dg._getPageNum=0;
				list = dg.A_bbsCtrl.getContentFindList(selectField.getText());
				dg.A_bbsCtrl.searchA_BbsListView(list, mem);
				this.dispose();
				
			}
			if(list.size()<1)
			{
				JOptionPane.showMessageDialog(null, "검색된데이터가없습니다.");
				this.dispose();
			}
			
		}else{}
		
		//페이지 이동버튼
		int listCount = 0;
		int lastPage =0;
		int checkList=0; //검색인지,아닌지 확인
		
		if(dg._choiceNum ==0 && dg._getTextF.length()>=1)//제목검색
		{
			listCount = dg.A_bbsCtrl.listCount(1, dg._getTextF);
			checkList =1;
		}
		else if(dg._choiceNum ==1 && dg._getTextF.length()>=1) //아이디로 텍스트를 입력하여 검색했을때
		{
			listCount = dg.A_bbsCtrl.listCount(2, dg._getTextF);
			checkList=2;
		}
		else if(dg._choiceNum ==2 && dg._getTextF.length()>=1) //제목으로 텍스트를 입력하여 검색했을때
		{
			listCount = dg.A_bbsCtrl.listCount(3, dg._getTextF);
			checkList=3;
		}
		else if(dg._getTextF.length()<1) //검색을 하지않았을때
		{
			listCount = dg.A_bbsCtrl.listCount(0,dg._getTextF);
			
			checkList =0;
		}	
		
		lastPage = listCount/17;
		if(listCount %17 !=0) lastPage++;
		
		for (int i = dg._returnPage; i < dg._returnPage+5; i++) 
		{
			if(obj == pageBtn[i]) //0:1p, 1:2p, 2:3p
			{
				if(i < lastPage) //조건안에서만 버튼이 명령이 듣게 만듬
				{
					if(currentPage ==i) 
					{
						//JOptionPane.showMessageDialog(null, "현재상주하고있는페이지입니다.");
						dg._getPageNum =i;
						return;
					}
					else
					{
						//JOptionPane.showMessageDialog(null, "이동가능합니다.");
						dg._getPageNum =i; //dg.returnPage 값은 < , > 이동버튼이 없는한 0 이다.																
						dg.A_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
						this.dispose();
						return;
					}
					
				}
				else
				{
					//JOptionPane.showMessageDialog(null, lastPage);
					JOptionPane.showMessageDialog(null, "없는 페이지입니다.");
				}
			}
		}
		
		if(obj == pageMoveBtn[0]) //<버튼
		{
			if(dg._returnPage ==0) //리스트 형태가 1 2 3 4 5 일때
			{
				if(currentPage == 0)
				{
					JOptionPane.showMessageDialog(null, "처음페이지입니다.");
					dg._getPageNum=0;
					return;
				}
				else //현재 페이지가 1페이지가 아니라면
				{
					dg._getPageNum--;
					dg.A_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
					this.dispose();
					return;
				}
			}
			else //리스트 형태가 1 2 3 4 5 가 아닐때
			{
				dg._getPageNum--;
				dg._returnPage--;
				dg.A_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
				this.dispose();
				return;
			}
		}
		else if(obj ==pageMoveBtn[1])
		{
			if(dg._getPageNum+1 <lastPage) 
			{
				dg._returnPage++;
				dg._getPageNum++;
				dg.A_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "없는 페이지입니다.");
			}
		}
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		//초이스 정보얻어오기
		Choice cho = (Choice)e.getSource();
		String selStr = cho.getSelectedItem();
		
		if(selStr.equals("제목"))
		{
			dg._choiceNum = 0;
		}
		else if(selStr.equals("아이디"))
		{
			dg._choiceNum = 1;
		}
		else if(selStr.equals("내용"))
		{
			dg._choiceNum = 2;
		}

	}
}
