package view.letter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dto.MemberDto;
import singleton.Delegate;

public class LetterView extends JFrame implements MouseListener, ActionListener {
	Delegate d = Delegate.getInstance();
	MemberDto mem = new MemberDto();

	//전체 패널
	private JPanel contentPane;
	//탭 패널
	private JPanel paneReceive = new JPanel();
	private JPanel paneSend = new JPanel();
	private JPanel paneWrite = new JPanel();

	//탭 내부 패널
	private final JScrollPane scrPanWriteLetter = new JScrollPane();
	private final JScrollPane scrPanReceiveLetter = new JScrollPane();
	private final JScrollPane scrPanSendLetter = new JScrollPane();

	//테이블
	private JTable tableReceiveLetter;
	private JTable tableSendLetter;

	String[] columnNames = {
			"seq","ID","Content","Wdate","isRead"
	};
	
	//테이블 데이터
	Object[][] receiveData;
	Object[][] sendData;
	DefaultTableModel tableModelRecv;
	DefaultTableModel tableModelSend;
	
	//버튼
	JButton btnReceiveDelete = new JButton("Delete");
	JButton btnSendDelete = new JButton("Delete");
	JButton btnWrite = new JButton("Write");

	//글
	private JTextField txtfTargetID;
	private JTextArea txtAWriteLetter = new JTextArea();



	public LetterView(MemberDto mem, Object[][] receiveData,Object[][] sendData) {
		this.mem = mem;
		this.receiveData = receiveData;
		this.sendData = sendData;
		tableModelRecv = new DefaultTableModel(this.receiveData, columnNames);
		tableModelSend = new DefaultTableModel(this.sendData, columnNames);
		
		//초기화
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//////요소 선언
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		JLabel lblMyLetterbox = new JLabel("My LetterBox");

		//Write탭
		JLabel lblLetterWrite = new JLabel("Letter Write");
		JLabel lblTargetid = new JLabel("Target_ID");

		txtfTargetID = new JTextField();


		//요소 설정
		lblMyLetterbox.setBounds(12, 10, 75, 15);
		lblLetterWrite.setBounds(12, 10, 78, 15);
		lblTargetid.setBounds(22, 35, 57, 15);
		txtfTargetID.setBounds(90, 32, 116, 21);
		btnWrite.setBounds(511, 235, 97, 23);
		tabbedPane.setBounds(22, 35, 625, 297);
		btnReceiveDelete.setBounds(12, 235, 80, 23);
		btnSendDelete.setBounds(12, 235, 80, 23);
		scrPanReceiveLetter.setBounds(12, 10, 596, 215);
		scrPanSendLetter.setBounds(12, 10, 596, 215);
		txtfTargetID.setColumns(10);
		scrPanWriteLetter.setBounds(32, 60, 576, 167);

		//데이터 추가
		tableReceiveLetter = new JTable(tableModelRecv);
		tableSendLetter = new JTable(tableModelSend);

		//요소 추가
		contentPane.add(lblMyLetterbox);
		contentPane.add(tabbedPane);
		tabbedPane.addTab("Receive", null, paneReceive, null);
		paneReceive.setLayout(null);
		paneReceive.add(btnReceiveDelete);

		scrPanReceiveLetter.setViewportView(tableReceiveLetter);
		paneReceive.add(scrPanReceiveLetter);
		scrPanSendLetter.setViewportView(tableSendLetter);
		paneSend.add(scrPanSendLetter);
		tabbedPane.addTab("Send", null, paneSend, null);
		paneSend.setLayout(null);
		paneSend.add(btnSendDelete);
		tabbedPane.addTab("Write", null, paneWrite, null);
		paneWrite.setLayout(null);

		paneWrite.add(lblLetterWrite);

		paneWrite.add(lblTargetid);
		paneWrite.add(txtfTargetID);
		paneWrite.add(btnWrite);

		paneWrite.add(scrPanWriteLetter);

		scrPanWriteLetter.setViewportView(txtAWriteLetter);


		//액션
		tableReceiveLetter.addMouseListener(this);
		tableSendLetter.addMouseListener(this);
		btnWrite.addActionListener(this);
		btnReceiveDelete.addActionListener(this);
		btnSendDelete.addActionListener(this);

		this.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getSource().equals(tableReceiveLetter) || e.getSource().equals(tableSendLetter)) {
			JTable temp = (JTable) e.getSource();
			String seq = String.valueOf(temp.getValueAt(temp.getSelectedRow(), 0));
			
			boolean isRead = false;
			if (e.getSource().equals(tableReceiveLetter)) {
				isRead = true;
			}

			if (e.getClickCount() == 2) {
				d.letterCtrl.letterDetail(seq,isRead);
				
				recvRefresh();
				sendRefresh();
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

	@Override
	public void actionPerformed(ActionEvent e) {

		//받은 편지 삭제
		if (e.getSource().equals(btnReceiveDelete) && tableReceiveLetter.getSelectedRow() != -1) {
			String seq = String.valueOf(tableReceiveLetter.getValueAt(tableReceiveLetter.getSelectedRow(), 0));

			d.letterCtrl.deleteReceiveLetter(seq);
			
			recvRefresh();
			
		//보낸 편지 삭제
		} else if (e.getSource().equals(btnSendDelete) && tableSendLetter.getSelectedRow() != -1) {
			String seq = String.valueOf(tableSendLetter.getValueAt(tableSendLetter.getSelectedRow(), 0));

			d.letterCtrl.deleteSendLetter(seq);
			
			sendRefresh();
			
		//편지 쓰기
		} else if (e.getSource().equals(btnWrite)) {
			receiveData = d.letterCtrl.letterWrite(mem,txtfTargetID.getText(),txtAWriteLetter.getText());
			txtfTargetID.setText("");
			txtAWriteLetter.setText("");
			
			//쪽지 데이터 갱신
			////받은 쪽지함 테이블 데이터 선언
			DefaultTableModel recvmodel = (DefaultTableModel)tableReceiveLetter.getModel();
			////받은 쪽지함 테이블 데이터 갱신
			recvmodel.setDataVector(receiveData, columnNames);
			
			sendRefresh();
			
		} else {
			JOptionPane.showMessageDialog(null, "삭제할 쪽지를 선택해주세요");
		}
	}
	
	private void recvRefresh() {
		//보낸 쪽지 데이터 받아오기
		receiveData = d.letterCtrl.getRecvLetterObject(mem);
		////보낸 쪽지함 테이블 데이터 주소
		DefaultTableModel model = (DefaultTableModel)tableReceiveLetter.getModel();
		////보낸 쪽지함 테이블 데이터 갱신
		model.setDataVector(receiveData, columnNames);

	}
	private void sendRefresh() {
		//보낸 쪽지 데이터 받아오기
		sendData = d.letterCtrl.getSendLetterObject(mem);
		////보낸 쪽지함 테이블 데이터 주소
		DefaultTableModel model = (DefaultTableModel)tableSendLetter.getModel();
		////보낸 쪽지함 테이블 데이터 갱신
		model.setDataVector(sendData, columnNames);

	}
}

