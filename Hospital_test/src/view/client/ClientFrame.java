package view.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Delegate;
import view.login.LoginView;

public class ClientFrame extends JFrame implements ActionListener{

	public JTextField txtF = new JTextField(14);
	public JTextArea txtA = new JTextArea();
	
	JButton btnTransfer = new JButton("전송");
	JButton btnExit = new JButton("닫기");
	
	JPanel pl = new JPanel();
	
	public boolean isFirst = true;
	
	public Socket socket;
	
	//로그인 유저 정보
	String id = "";
	
	public ClientFrame(Socket socket,String id) {
		super("상담문의");
		this.id = id;

		this.socket = socket;
//	    new LoginView();
		Delegate d = Delegate.getInstance();
		d.memCtrl.start(socket, this, id);

		txtA.setEditable(false);
		JScrollPane scrPane = new JScrollPane( txtA );
		scrPane.setPreferredSize(new Dimension(200, 120));
		
		getContentPane().add("Center", scrPane);
		
		pl.add(txtF);
		pl.add(btnTransfer);
		pl.add(btnExit);
		getContentPane().add("South", pl);
		
		btnTransfer.addActionListener(this);
		btnExit.addActionListener(this);
		txtF.addActionListener(this);
		
		setBounds(1100, 100, 324, 298);
		setVisible(true);		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		singleton.Delegate d = singleton.Delegate.getInstance();
		if(e.getSource() == btnTransfer || e.getSource() == txtF){			
			if(txtF.getText().equals("")) return;
			
			txtA.append("[" + id + "]" + txtF.getText() + "\n" );
			
			// 전송 부분
			d.memCtrl.sendMsg(socket,this,id);
			txtF.setText("");			
		}else{
			dispose();
		}		
	}	
}

