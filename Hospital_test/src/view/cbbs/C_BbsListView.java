package view.cbbs;

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

import dto.C_BbsDto;
import dto.MemberDto;
import singleton.Delegate;

public class C_BbsListView extends JFrame 
implements ActionListener, MouseListener, ItemListener 
{

	private static final long serialVersionUID = 1L;
	
	//멤버변수
	private JTable jTable;
	private JScrollPane jScrPane;	
	private JButton writeBtn;
	Choice choice;
	//int choiceNum=0; //선택된 초이스값[0:제목, 1:아이디, 2:내용]
	
	String columnNames[] = 
		{
			"번호", "비밀글", "제목", "작성자","작성일","조회수","추천"	
		};
	Object rowData[][];	
	DefaultTableModel model;
	List<C_BbsDto> list;
	
	Delegate dg = Delegate.getInstance();
	private JTextField selectField;
	private JButton selectBtn;
	
	//페이징
	JButton pageBtn[];
	JButton pageMoveBtn[];
	int currentPage;
	
	MemberDto mem;
	//생성자
	public C_BbsListView(List<C_BbsDto> list,MemberDto mem) 
	{
		
		setTitle("상담게시판");
		setLayout(null);	
		this.mem = mem;
		
		//System.out.println(dg.getPageNum);
		
		//리스트정보불러오기
		this.list = list;
		int n = 1;
		
		//페이지 버튼생성
		pageBtn = new JButton[dg.returnPage+5];
		pageMoveBtn = new JButton[2];
		
		//현재페이지
		currentPage = dg.getPageNum;
		//System.out.println("현재페이지 :"+(currentPage+1));
		for (int i = dg.returnPage; i < dg.returnPage+5; i++)//다음페이지 이동버튼을눌렀을때 버튼숫자가 바뀌도록 설정한다.
		{			
			pageBtn[i] = new JButton((i+1)+"");			
			pageBtn[i].setBounds(340+(50*(i-dg.returnPage)), 355, 50, 35);	
			pageBtn[i].addActionListener(this);
			pageBtn[i].setFont(new Font("돋움", Font.PLAIN, 13));
			if(currentPage==i) //현재 페이지에따라서 버튼 색깔에 변화를줌
			{
				pageBtn[i].setBackground(Color.BLUE);
			}
			else
			{
				pageBtn[i].setBackground(Color.LIGHT_GRAY);
			}
			
			add(pageBtn[i]);				
		}
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
		
		
		
		
		
		
		rowData = new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) 
		{
			String secreat="";
			C_BbsDto dto = list.get(i);
			
			if(dto.getSecreat()==0) secreat ="비밀글";
			rowData[i][0] = (currentPage*17)+n; //번호
			rowData[i][1] = secreat; //비밀글
			rowData[i][2] = dto.getTitle(); //제목
			rowData[i][3] = dto.getId(); //작성자
			rowData[i][4] = dto.getWdate(); //작성일
			rowData[i][5] = dto.getRcount(); //조회수
			rowData[i][6] = dto.getReco(); //추천수
			n++;
		}
		model = new DefaultTableModel(columnNames, 0);
		
		model.setDataVector(rowData, columnNames);	
		
		jTable = new JTable(model);	
		jTable.addMouseListener(this);
		
		jTable.getColumnModel().getColumn(0).setMaxWidth(50);
		jTable.getColumnModel().getColumn(1).setMaxWidth(50);
		jTable.getColumnModel().getColumn(2).setMaxWidth(300);
		jTable.getColumnModel().getColumn(3).setMaxWidth(100);
		jTable.getColumnModel().getColumn(4).setMaxWidth(150);
		jTable.getColumnModel().getColumn(5).setMaxWidth(50);
		jTable.getColumnModel().getColumn(6).setMaxWidth(50);
		
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		jTable.getColumn("번호").setCellRenderer(celAlignCenter);
		jTable.getColumn("작성자").setCellRenderer(celAlignCenter);
		jTable.getColumn("작성일").setCellRenderer(celAlignCenter);
		
		jScrPane = new JScrollPane(jTable);
		
		jScrPane.setBounds(10, 50, 750, 300);
		add(jScrPane);		
		
		writeBtn = new JButton("글쓰기");
		writeBtn.addActionListener(this);
		writeBtn.setBounds(10, 420, 100, 20);		
		add(writeBtn);
		
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
		selectField.setBounds(220, 420, 150, 20);
		add(selectField);
		//System.out.println("검색문자 : "+dg.getTextF);
		
		selectBtn = new JButton("검색");
		selectBtn.setBounds(390, 420, 100, 20);	
		selectBtn.addActionListener(this);
		add(selectBtn);
	}
	

	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		//초이스 정보얻어오기
		Choice cho = (Choice)e.getSource();
		String selStr = cho.getSelectedItem();
		
		if(selStr.equals("제목"))
		{
			dg.choiceNum=0;
		}
		else if(selStr.equals("아이디"))
		{
			dg.choiceNum =1;
		}
		else if(selStr.equals("내용"))
		{
			dg.choiceNum =2;
		}
		
		

	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) 
	{
		
		int rowNum = jTable.getSelectedRow();

		//삭제된글인지 확인[0:삭제됨, 1:존재함]
		if(list.get(rowNum).getDel() ==0)
		{
			JOptionPane.showMessageDialog(null, "삭제된 글입니다.");
			return;
		}
		
		//비밀글인지 확인
		if(list.get(rowNum).getSecreat() ==0) //만약 비밀글이라면
		{
			//글쓴이와 아이디가 일치하는지 또는 관리자인지 확인(AUTH :  0--관리자, 3--유저)
			//접속자가 비밀글(자신의 글을 클릭할때만 입장가능)
			//if(mem.getId().equals(list.get(rowNum).getId()) ) 	
			if(dg.getId.equals(list.get(rowNum).getId()) ) 	
			{
				JOptionPane.showMessageDialog(null, 
				"클릭한 게시글로 이동합니다");
				//rcount
				dg.C_bbsCtrl.readCount(list.get(rowNum).getSeq());
		
				//읽기로 이동										
				dg.C_bbsCtrl.detailBBS(list.get(rowNum).getSeq(), mem);
				this.dispose();
				return;
			}
			else if(dg.getAuth ==0) //관리자도 접근이 가능하게만듬
			{
				JOptionPane.showMessageDialog(null, 
				"클릭한 게시글로 이동합니다");
				//rcount
				dg.C_bbsCtrl.readCount(list.get(rowNum).getSeq());
		
				//읽기로 이동										
				dg.C_bbsCtrl.detailBBS(list.get(rowNum).getSeq(), mem);
				this.dispose();
				return;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "글쓴이와 관리자만 읽을 수 있습니다.");
				return;	
			}
					
		}
		else
		{
			JOptionPane.showMessageDialog(null, 
					"클릭한 게시글로 이동합니다");
			//rcount
			dg.C_bbsCtrl.readCount(list.get(rowNum).getSeq());
	
			//읽기로 이동										
			dg.C_bbsCtrl.detailBBS(list.get(rowNum).getSeq(), mem);
			this.dispose();
			return;
		}
		
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();
		
		if(obj ==writeBtn)
		{
			dg.C_bbsCtrl.writeBbsView(mem);
			this.dispose();
		}
		else if(obj ==selectBtn)
		{
			//JOptionPane.showMessageDialog(null, choiceNum);
			//System.out.println("selectField.getText() = "+selectField.getText());
			if(selectField.getText().length()<1) //아무내용으로도 검색을 안했따면
				{
					//JOptionPane.showMessageDialog(null, "검색을이용하지않습니다.");
					dg.getTextF=""; //기존텍스트빈칸으로 초기화
					dg.choiceNum=0; //기존값으로 초기화
					dg.C_bbsCtrl.enterC_BbsListView(mem);
					this.dispose();
					return;
				}
			else if(dg.choiceNum ==0) //제목으로검색
			{		
				dg.getTextF =selectField.getText();	
				dg.getPageNum=0;
				list = dg.C_bbsCtrl.getTitleFindList(selectField.getText());			
				dg.C_bbsCtrl.searchC_BbsListView(list, mem);	
				this.dispose();
			}
			else if(dg.choiceNum ==1) //아이디로검색
			{
				dg.getTextF =selectField.getText();	
				dg.getPageNum=0;
				list = dg.C_bbsCtrl.getIdFindList(selectField.getText());
				dg.C_bbsCtrl.searchC_BbsListView(list, mem);				
				this.dispose();
			}
			else if(dg.choiceNum==2) //내용으로검색
			{	
				dg.getTextF =selectField.getText();	
				dg.getPageNum=0;
				list = dg.C_bbsCtrl.getContentFindList(selectField.getText());
				dg.C_bbsCtrl.searchC_BbsListView(list, mem);
				this.dispose();
				
			}
			if(list.size()<1)
			{
				JOptionPane.showMessageDialog(null, "검색된데이터가없습니다.");
				this.dispose();
			}			
		}
		else
		{
		}
		//페이지 이동버튼
		int listCount =0;
		int lastPage = 0;
		int checkList=0; //검색인지,아닌지 확인
		System.out.println("dg.getTextF ="+dg.getTextF);
		System.out.println("dg.choiceNum= "+dg.choiceNum);
		if(dg.choiceNum ==0 && dg.getTextF.length()>=1) //제목으로 텍스트를 입력하여 검색했을때
		{
			listCount = dg.C_bbsCtrl.listCount(1, dg.getTextF);
			checkList=1;
			
		}
		else if(dg.choiceNum ==1 && dg.getTextF.length()>=1) //아이디로 텍스트를 입력하여 검색했을때
		{
			listCount = dg.C_bbsCtrl.listCount(2, dg.getTextF);
			checkList=2;
		}
		else if(dg.choiceNum ==2 && dg.getTextF.length()>=1) //제목으로 텍스트를 입력하여 검색했을때
		{
			listCount = dg.C_bbsCtrl.listCount(3, dg.getTextF);
			checkList=3;
		}
		else if(dg.getTextF.length()<1) //검색을 하지않았을때
		{
			listCount = dg.C_bbsCtrl.listCount(0,dg.getTextF);
			
			checkList =0;
		}
		System.out.println("listCount = "+listCount);
		System.out.println("dg.getPageNum ="+dg.getPageNum);
		System.out.println("currentPage ="+currentPage);
		lastPage =listCount/17;
		if(listCount %17 !=0) lastPage++; //2
		System.out.println("lastPage = "+lastPage);
		for (int i = dg.returnPage; i < dg.returnPage+5; i++) 
		{
			if(obj == pageBtn[i]) //0:1p, 1:2p, 2:3p
			{
				if(i < lastPage) //조건안에서만 버튼이 명령이 듣게 만듬
				{
					if(currentPage ==i) 
					{
						JOptionPane.showMessageDialog(null, "현재상주하고있는페이지입니다.");
						dg.getPageNum =i;
						return;
					}
					else
					{
						//JOptionPane.showMessageDialog(null, "이동가능합니다.");
						dg.getPageNum =i; //dg.returnPage 값은 < , > 이동버튼이 없는한 0 이다.																
						dg.C_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
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
			if(dg.returnPage ==0) //리스트 형태가 1 2 3 4 5 일때
			{
				if(currentPage == 0)
				{
					JOptionPane.showMessageDialog(null, "처음페이지입니다.");
					dg.getPageNum=0;
					return;
				}
				else //현재 페이지가 1페이지가 아니라면
				{
					dg.getPageNum--;
					dg.C_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
					this.dispose();
					return;
				}
			}
			else //리스트 형태가 1 2 3 4 5 가 아닐때
			{
				dg.getPageNum--;
				dg.returnPage--;
				dg.C_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
				this.dispose();
				return;
			}
		}
		else if(obj ==pageMoveBtn[1])
		{
			if(dg.getPageNum+1 <lastPage) //0
			{
				dg.returnPage++;
				dg.getPageNum++;
				dg.C_bbsCtrl.moveBBsList(checkList, list, mem); //어떤리스트를 처리할지 정함
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "없는 페이지입니다.");
			}
		}
		
	}

}
