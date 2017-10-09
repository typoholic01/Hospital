package view;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import dto.MemberDto;
import dto.PreorderDto;
import singleton.Delegate;


public class DoctorIntroduce extends JTabbedPane implements ItemListener{
	
    JPanel panel1;
    JPanel panel2;
    
    Choice choice;
    String choice_name ="";
    ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/doct_1.jpg")));
    ImageIcon img2 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/doct_2.jpg")));
    ImageIcon img3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/doct_3.jpg")));
    ImageIcon img4 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/doct_4.jpg")));
    JLabel label1;
    JLabel label5;
    JLabel label6;
    JLabel label7;
    ////
    JTable jTable;
	JScrollPane jScrollPane;
	String columnNames[] = {
			"진료내역", "날짜"
	};
	Vector<String> Column = new Vector<String>();
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	Vector<String> userRow;
	Object rowData[][];
	///
    List<PreorderDto> historylist = new ArrayList<>();
    Delegate d = Delegate.getInstance();
    MatteBorder border = new MatteBorder(2, 2, 2, 2, Color.black);
	public DoctorIntroduce() {
		setBounds(0, 0, 520, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(choice_name.equals("")){choice_name = "문성환";}
		historylist = d.preorderCtrl.doct_name(choice_name);
		////////////////////////////테이블
		jTable = new JTable(model)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row , int col)
			{
				return false;
			}
		};
		jTable.getColumnModel().getColumn(0).setMaxWidth(100);
		jScrollPane = new JScrollPane(jTable);
		add(jScrollPane);
		/////////////////////////
		rowData = new Object[historylist.size()][2];
		for (int i = 0; i < historylist.size(); i++) {
			rowData[i][0] = historylist.get(i).getGetPreorder_date();
			rowData[i][1] = historylist.get(i).getDiction();
		}
		model.setDataVector(rowData, columnNames);
		System.out.println(historylist.size());
		/////////////////////////////
		choice = new Choice();
		choice.add("문성환");
		choice.add("기승간");
		choice.add("최국호");
		choice.add("배정혜");
		choice.addItemListener(this);
		//add(choice).setBounds(400, 25, 100, 30);
		/////////////////////////////
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel2 = new JPanel();
        panel2.setLayout(null);
        ////////////////////////////////
        
        /////////////////////////////////////
        List<MemberDto> member = new ArrayList<>();
		 member = d.memCtrl.memberlist(choice_name);
		 
        //////////////////////////////////
        label1 = new JLabel(img);
        label1.setBorder(border);
        JLabel label2 = new JLabel("이름");
        label2.setFont(new Font("고딕", Font.BOLD, 18));
        label2.setBorder(border);
        JLabel label3 = new JLabel("Email");
        label3.setFont(new Font("고딕", Font.BOLD, 18));
        label3.setBorder(border);
        JLabel label4 = new JLabel("Phone");
        label4.setFont(new Font("고딕", Font.BOLD, 18));
        label4.setBorder(border);
        label5 = new JLabel(member.get(0).getName().toString());
        label5.setFont(new Font("고딕", Font.BOLD, 18));
        label5.setBorder(border);
        label6 = new JLabel(member.get(0).getEmail().toString());
        label6.setFont(new Font("고딕", Font.BOLD, 18));
        label6.setBorder(border);
        label7 = new JLabel(member.get(0).getPhone());
        label7.setFont(new Font("고딕", Font.BOLD, 18));
        label7.setBorder(border);
        //////////////////////////////////////////
        panel1.add(label1).setBounds(0, 0, 180, 230);
        panel1.add(label2).setBounds(210, 30, 70, 40);
        panel1.add(label3).setBounds(210, 70, 70, 40);
        panel1.add(label4).setBounds(210, 110, 70, 40);
        panel1.add(label5).setBounds(280, 30, 200, 40);
        panel1.add(label6).setBounds(280, 70, 200, 40);
        panel1.add(label7).setBounds(280, 110, 200, 40);
        panel1.add(choice).setBounds(400, 5, 100, 30);
        panel2.add(jScrollPane).setBounds(0, 0, 400, 230);
        //panel2.add(choice).setBounds(400, 15, 100, 30);
        ///////////////////////////////////////////
        add("기본정보", panel1);
        add("진료,수술 내역", panel2);
        /////////////////////////////////////////
        
        //setVisible(true);
        
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Choice cho = (Choice)e.getSource();
		String selected = cho.getSelectedItem();
		if (selected.equals("문성환")) {
			choice_name = "문성환";
			historylist = d.preorderCtrl.doct_name(choice_name);
			rowData = new Object[historylist.size()][2];
			for (int i = 0; i < historylist.size(); i++) {
				rowData[i][0] = historylist.get(i).getGetPreorder_date();
				rowData[i][1] = historylist.get(i).getDiction();
			}
			 model.setDataVector(rowData, columnNames);
			 label1.setIcon(img);
			 List<MemberDto> member = new ArrayList<>();
			 member = d.memCtrl.memberlist(choice_name);
			 label5.setText(member.get(0).getName().toString());
			 label6.setText(member.get(0).getEmail().toString());
			 label7.setText(member.get(0).getPhone());
			
		}else if (selected.equals("기승간")) {
			choice_name = "기승간";
			historylist = d.preorderCtrl.doct_name(choice_name);
			rowData = new Object[historylist.size()][2];
			for (int i = 0; i < historylist.size(); i++) {
				rowData[i][0] = historylist.get(i).getGetPreorder_date();
				rowData[i][1] = historylist.get(i).getDiction();
			}
	        model.setDataVector(rowData, columnNames);
	        label1.setIcon(img2);
	        List<MemberDto> member = new ArrayList<>();
			 member = d.memCtrl.memberlist(choice_name);
			 label5.setText(member.get(0).getName().toString());
			 label6.setText(member.get(0).getEmail().toString());
			 label7.setText(member.get(0).getPhone());
	           
			
		}else if (selected.equals("최국호")) {
			choice_name = "최국호";
			historylist = d.preorderCtrl.doct_name(choice_name);
			rowData = new Object[historylist.size()][2];
			for (int i = 0; i < historylist.size(); i++) {
				rowData[i][0] = historylist.get(i).getGetPreorder_date();
				rowData[i][1] = historylist.get(i).getDiction();
			}
			model.setDataVector(rowData, columnNames);
			label1.setIcon(img3);
			List<MemberDto> member = new ArrayList<>();
			 member = d.memCtrl.memberlist(choice_name);
			 label5.setText(member.get(0).getName().toString());
			 label6.setText(member.get(0).getEmail().toString());
			 label7.setText(member.get(0).getPhone());
	        	
			
		}else if (selected.equals("배정혜")) {
			choice_name = "배정혜";
			historylist = d.preorderCtrl.doct_name(choice_name);
			rowData = new Object[historylist.size()][2];
			for (int i = 0; i < historylist.size(); i++) {
				rowData[i][0] = historylist.get(i).getGetPreorder_date();
				rowData[i][1] = historylist.get(i).getDiction();
			}
	        model.setDataVector(rowData, columnNames);
	        label1.setIcon(img4);
	        List<MemberDto> member = new ArrayList<>();
			 member = d.memCtrl.memberlist(choice_name);
			 label5.setText(member.get(0).getName().toString());
			 label6.setText(member.get(0).getEmail().toString());
			 label7.setText(member.get(0).getPhone());
		}	
	}
}
