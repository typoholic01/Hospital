package view;

import java.awt.Choice;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dto.MemberDto;
import dto.Month_dto;
import dto.PreorderDto;
import dto.Tempdto;
import dto.xydto;
import singleton.Delegate;

public class ScheduleView extends JPanel implements ActionListener, ItemListener, MouseListener{
	Calendar cal = Calendar.getInstance();
	int month = cal.get(Calendar.MONTH) + 1;
	Delegate d = Delegate.getInstance();
	ImageIcon imgCalendar = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/dal.gif")));
	ImageIcon imgNotSel = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/11.jpg")));
	ImageIcon imgSel = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/22.jpg")));
	ImageIcon imgcheck = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/check.gif")));
	List<Month_dto> monthlist = new ArrayList<>();
	List<PreorderDto> preorderList = new ArrayList<>(); 
	List<dto.xydto> xylist = new ArrayList<>();
	
	
	
	MemberDto mem = new MemberDto();
	
	JScrollPane jScrollPane;
	JTextArea ta1;
	JButton btn1;
	JButton btn2;
	JLabel label1;
	JLabel label3;
	
	Choice choice2;
	int choice_name2;
	//////
	int xx;
	int yy;
	int tt = 0;
	public String choice_name;
	JLabel label5;
	//////
	public ScheduleView(MemberDto mem) {
		
		setLayout(null);
		setBounds(0, 0, 900, 500);
		this.mem = mem;
		monthlist = d.preorderCtrl.monthset(monthlist);
		choice_name = mem.getName();
		////////////초이스
		choice2 = new Choice();
		choice2.add("오전 10:00 - 12:00");
		choice2.add("오후 14:00 - 17:00");
		choice2.add("저녘 19:00 - 21:00");
		choice2.addItemListener(this);
		add(choice2).setBounds(600, 100, 180, 15);
		///////////////////라벨
		label1 = new JLabel(Integer.toString(month));
		label1.setFont(new Font(null, Font.ITALIC, 30));
		add(label1).setBounds(70, 30, 60, 30);
		label3 = new JLabel("의사명 : " + mem.getName());
		label3.setFont(new Font("고딕", Font.BOLD, 18));
		add(label3).setBounds(600, 30, 220, 30);
		JLabel label4 = new JLabel("↓진료예약한 시간↓");
		label4.setFont(new Font("고딕", Font.BOLD, 18));
		add(label4).setBounds(600, 68, 220, 30);
		label5 = new JLabel();
		label5.setFont(new Font("고딕", Font.BOLD, 18));
		add(label5).setBounds(600, 135, 220, 30);
		//////////////////버튼
		btn1 = new JButton("<<");
		btn1.addActionListener(this);
		btn1.setBounds(10, 20, 50, 50);
		add(btn1);
		btn2 = new JButton(">>");
		btn2.addActionListener(this);
		btn2.setBounds(100, 20, 50, 50);
		add(btn2);
		///////////텍필
		ta1 = new JTextArea();
		ta1.setText("");
		jScrollPane = new JScrollPane(ta1);
		jScrollPane.setBounds(600, 170, 200, 230);
		add(jScrollPane);
		/////////////
		addMouseListener(this);
		//setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		String btnStr = btn.getText();
		if (btnStr.equals("<<")) {
			if (month == 1) {
				JOptionPane.showMessageDialog(null, "2017년 첫달입니다");
			}else month -= 1;
			label1.setText(Integer.toString(month));
			tt = 0;
			super.remove(choice2);
			choice2 = new Choice();
			ta1.setText("");
			label5.setText("");
			repaint();
		}else if (btnStr.equals(">>")) {
			if (month == 12) {
				JOptionPane.showMessageDialog(null, "2017년 마지막 달입니다");
			}else month += 1;
			label1.setText(Integer.toString(month));
			tt = 0;
			super.remove(choice2);
			choice2 = new Choice();
			ta1.setText("");
			label5.setText("");
			repaint();
		}
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Choice cho = (Choice)e.getSource();
		String selected = cho.getSelectedItem();
		 if (selected.equals("오전 10:00 - 12:00")) {
			choice_name2 = 0;
			
			for (int i = 0; i < preorderList.size(); i++) {
				if (preorderList.get(i).getScejul_day() == tt) {
					if (preorderList.get(i).getScejul_time() == 0) {
						ta1.setText(preorderList.get(i).getGetPreorder_date());
						label5.setText(preorderList.get(i).getUser_id() + "환자가말한 증상");
					}
				}
			}
		}else if (selected.equals("오후 14:00 - 17:00")) {
			choice_name2 = 1;
			for (int i = 0; i < preorderList.size(); i++) {
				if (preorderList.get(i).getScejul_day() == tt) {
					if (preorderList.get(i).getScejul_time() == 1) {
						ta1.setText(preorderList.get(i).getGetPreorder_date());
						label5.setText(preorderList.get(i).getUser_id() + "환자가말한 증상");
					}
				}
			}
		}else if (selected.equals("저녘 19:00 - 21:00")) {
			choice_name2 = 2;
			for (int i = 0; i < preorderList.size(); i++) {
				if (preorderList.get(i).getScejul_day() == tt) {
					if (preorderList.get(i).getScejul_time() == 2) {
						ta1.setText(preorderList.get(i).getGetPreorder_date());
						label5.setText(preorderList.get(i).getUser_id() + "환자가말한 증상");
					}
				}
			}
		}
	}
	
	public void paint(Graphics g){
		System.out.println("paint()");
		super.paint(g);
		g.drawImage(imgCalendar.getImage(), 0, 100, null);
		////////////////////
		preorderList = d.preorderCtrl.doct_name(choice_name);
		System.out.println(preorderList.toString());
		List<Integer> scejulday = new ArrayList<>();
		List<Tempdto> templist = new ArrayList<>();
		int ttemp = 0;
		for (int i = 0; i < preorderList.size(); i++) {
			if (preorderList.get(i).getScejul_month() == month) {
				Tempdto aa = new Tempdto(preorderList.get(i).getScejul_day(), preorderList.get(i).getScejul_time());
				templist.add(aa);
			}
		}
		for (int i = 0; i < templist.size(); i++) {
			ttemp = 0;
				for (int j = 0; j < templist.size(); j++) {
					if (templist.get(i).getDay() == templist.get(j).getDay()) {
						ttemp++;
					}
				}
				if (ttemp >= 1) {
					scejulday.add(templist.get(i).getDay());//
				}
		}
		////////////////////
		int temp = 1;
		int x = monthlist.get(month -1).getX();
		int y = 200;
		int p = 0;
		for (int i = 0; i < monthlist.get(month -1).getLast(); i++) {
			if (scejulday.size() == 0) {
				scejulday.add(45);
			}
			if (x == 450) {
				for (int j = 0; j < scejulday.size(); j++) {
					if (scejulday.get(j) == temp) {
						p = 1;
					}
				}
				if (p == 1) {
					g.drawImage(imgSel.getImage(), x, y, null);
					g.drawString(Integer.toString(temp), x+10, y+20);
				}else {
					g.drawImage(imgNotSel.getImage(), x, y, null);
					g.drawString(Integer.toString(temp), x+10, y+20);
				}
				p = 0;
				x = 30;
				y += 45; 
			}else{
				for (int j = 0; j < scejulday.size(); j++) {
					if (scejulday.get(j) == temp) {
						p = 1;
					}
				}
				if (p == 1) {
					g.drawImage(imgSel.getImage(), x, y, null);
					g.drawString(Integer.toString(temp), x+10, y+20);
				}else {
					g.drawImage(imgNotSel.getImage(), x, y, null);
					g.drawString(Integer.toString(temp), x+10, y+20);
				}
				p = 0;
				x += 70;
			}
			temp++;
		}
		if (tt != 0) {
			g.drawImage(imgcheck.getImage(), xylist.get(0).getX()+10, xylist.get(0).getY()-40, null);
		}
	}

	public void update(Graphics g){
		System.out.println("update()");
		super.update(g); //상위 update 메소드에서 paint 호출함.
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xx = e.getX();
		yy = e.getY();
		List<Month_dto> monlist = new ArrayList<>();
		Month_dto montemp = new Month_dto(month, monthlist.get(month-1).getX(), monthlist.get(month -1).getLast());
		monlist.add(montemp);
		int tempx = monlist.get(0).getX();
		int tempy = 200;
		tt = 0;
		/////////////////
		label3.setText("의사명 : " + choice_name);
		preorderList = d.preorderCtrl.doct_name(choice_name);
		int a = 0;//0
		int b = 0;//1
		int c = 0;//2
		super.remove(choice2);
		choice2 = new Choice();
		/////////////////////////////////////////////
		for (int i = 0; i < monlist.get(0).getLast(); i++) {
			if (tempx <= xx && xx <= tempx+65 && tempy <= yy && yy <= tempy+35){
				tt = i+1;
				xydto xy = new xydto(tempx, tempy);
				int w = 0;
				while(w<1){
					if (xylist.size() == 0) {
						xylist.add(xy);
						w++;
					}else if (xylist.size() != 0) {
						for (int j = 0; j < xylist.size(); j++) {
							xylist.remove(j);
						}
					}
				}
			}
			if (tempx == 450) {
			tempx = 30;
			tempy += 45; 
			}else{tempx += 70;}
			
		}
		
		if(tt != 0){
		///////////////////////////////////////////////------------
		for (int i = 0; i < preorderList.size(); i++) {
			if (preorderList.get(i).getScejul_month() == month) {
			if (preorderList.get(i).getScejul_day() == tt) {
				if (preorderList.get(i).getScejul_time() == 0) {
					a = 1;
				}else if (preorderList.get(i).getScejul_time() == 1) {
					b = 1;
				}else if (preorderList.get(i).getScejul_time() == 2) {
					c = 1;
				}
			}
			}
		}
		
		if (a == 1) {choice2.add("오전 10:00 - 12:00");}
		if (b == 1) {choice2.add("오후 14:00 - 17:00");}
		if (c == 1) {choice2.add("저녘 19:00 - 21:00");}
		super.remove(label5);
		label5 = new JLabel();
		label5.setFont(new Font("고딕", Font.BOLD, 18));
		add(label5).setBounds(600, 135, 220, 30);
		ta1.setText("");
		repaint();
		
		}else if(tt == 0){repaint();}
		choice2.addItemListener(this);
		add(choice2).setBounds(600, 100, 180, 15);
		
		if (choice2 != null) {
			if (a == 1) {a = 5;}
			if (b == 1) {b = 4;}
			if (c == 1) {c = 3;}
			
			if (a < b) {
				int k = a;
				a = b;
				b = k;
			}
			if (a < c){
				int k = a;
				a = c;
				c = k;
			}
			if (b < c){
				int k = b;
				b = c;
				c = k;
			}
		}
		if (a == 5) {choice_name2 = 0;}
		else if (a == 4) {choice_name2 = 1;}
		else if (a == 3) {choice_name2 = 2;}
		if (tt != 0) {
			for (int i = 0; i < preorderList.size(); i++) {
			if (preorderList.get(i).getScejul_day() == tt) {
				if (preorderList.get(i).getScejul_time() == choice_name2) {
					ta1.setText(preorderList.get(i).getGetPreorder_date());
					label5.setText(preorderList.get(i).getUser_id() + "환자가말한 증상");
				}
			}
		}
		}
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	

}
