package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.MemberDto;
import singleton.Delegate;

public class MypageView extends JFrame implements ActionListener {
	Delegate d = Delegate.getInstance();
	private JPanel contentPane;
	JScrollPane scrollPane = new JScrollPane();
	private JTable tblCheckPreorder;
	MemberDto mem = new MemberDto();
	
	//라벨	
	JLabel lblSetname = new JLabel("SetName");
	JLabel lblSetemail = new JLabel("SetEmail");
	JLabel lblSetphone = new JLabel("SetPhone");

	//버튼
	JButton btnPreorderCancle = new JButton("Preorder Cancle");
	JButton btnBackMenu = new JButton("Back Menu");
	
	//테이블
		String[] columnNames = {
				"SEQ","날짜","진료명","시간","상황"
		};
		
	//테이블 데이터
	DefaultTableModel tableModel;
	
		
	public MypageView() {
		view();
	}
	public MypageView(MemberDto mem,Object[][] searchRowData) {
		this.mem = mem;
		view();
		
		//라벨 세팅
		lblSetname.setText(mem.getName());
		lblSetemail.setText(mem.getEmail());
		lblSetphone.setText(mem.getPhone());
		
		//테이블 세팅
		tableModel = new DefaultTableModel(searchRowData, columnNames);
		
		tblCheckPreorder = new JTable(tableModel)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row , int col)
			{
				return false;
			}
		};
		
		contentPane.add(tblCheckPreorder);
		scrollPane.setViewportView(tblCheckPreorder);
	}
	private void view() {
		//초기화
				setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				setBounds(100, 100, 845, 275);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				//요소 선언
				JLabel lblPrivate = new JLabel("개인정보");
				JLabel lblPreorderSchedule = new JLabel("예약 확인");
				
				//모양잡기
				scrollPane.setBounds(361, 38, 414, 117);
				lblPrivate.setBounds(12, 10, 57, 15);
				lblPreorderSchedule.setBounds(360, 10, 111, 15);
				lblSetname.setBounds(12, 38, 120, 15);
				lblSetemail.setBounds(12, 63, 120, 15);
				lblSetphone.setBounds(12, 88, 120, 15);
				btnPreorderCancle.setBounds(642, 165, 133, 23);
				btnBackMenu.setBounds(12, 203, 97, 23);
				
				//요소 추가
				contentPane.add(lblPrivate);
				contentPane.add(lblPreorderSchedule);
				contentPane.add(lblSetname);
				contentPane.add(lblSetemail);
				contentPane.add(lblSetphone);
				contentPane.add(btnPreorderCancle);
				contentPane.add(btnBackMenu);
				contentPane.add(scrollPane);
				
				//액션 추가
				btnPreorderCancle.addActionListener(this);
				btnBackMenu.addActionListener(this);
				
				this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
				
		if (e.getSource().equals(btnPreorderCancle) && tblCheckPreorder.getSelectedRow() != -1) {
			//예약 삭제
			String seq = String.valueOf(tblCheckPreorder.getValueAt(tblCheckPreorder.getSelectedRow(), 0));
			
			d.scheduleCtrl.preorderCancle(seq);
			
			//데이터 갱신
			Object[][] dataCheckPreorder = d.scheduleCtrl.getDataPreorder(mem);
			DefaultTableModel model = (DefaultTableModel)tblCheckPreorder.getModel();
			model.setDataVector(dataCheckPreorder, columnNames);
			
		} else if (e.getSource().equals(btnBackMenu)) {
//			d.scheduleCtrl.backMenu(mem);
			dispose();
		}
	}
}
