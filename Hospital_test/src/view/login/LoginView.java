package view.login;

import java.awt.Color;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import dto.MemberDto;
import singleton.Delegate;
import javax.swing.JPanel;

public class LoginView extends JFrame implements ActionListener, KeyListener {
	private JTextField idTextF;
	private JPasswordField pwTextF;
	
	private JButton logBtn;
	private JButton accountBtn;
	
	private JButton IdSearchBtn;
	private JButton PwSearchBtn;
	private JButton btnNewButton;
	
	private JLabel jimage;

	//아이디 찾기
	private JLabel label_ids ;
	private JLabel label_ids_Name;
	private JTextField textField_ids_Email;
	private JTextField textField_ids_Name;
	
	//비밀번호 찾기
	private JLabel label_pws ;
	private JLabel label_pws_Name;
	private JTextField textField_pws_ID;
	private JTextField textField_pws_EMAIL;
    
	//찾은 아이디, 비밀번호 텍스트 필드 
	private JTextField se_id ;
	private JTextField se_pw ;
	
	//찾기버튼, 확인 버튼
	private JButton button_ids; //아이디찾기
	private JButton button_pws;//비밀번호찾기
	private JButton button_close_id;//아이디확인
    private JButton button_close_pw;//비밀번호확인
    private JButton button_bye;
    
    
	public LoginView() {
		super("loginView");
		getContentPane().setLayout(null);
		
		//LookAndFeel Windows 스타일 적용
     	try {
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setBounds(31, 363, 67, 15);
		getContentPane().add(idLabel);
		
		idTextF = new JTextField(10);
		idTextF.setBounds(74, 335, 300, 50);
		getContentPane().add(idTextF);
		
		JLabel passLabel = new JLabel("PW:");
		passLabel.setBounds(31, 414, 67, 15);
		getContentPane().add(passLabel);
		
		pwTextF = new JPasswordField();
		pwTextF.setBounds(74, 395, 300, 50);
		getContentPane().add(pwTextF);
				
		logBtn = new JButton("log-in");
		logBtn.setBounds(386, 336, 129, 73);
		logBtn.addActionListener(this);
		getContentPane().add(logBtn);
		
		accountBtn = new JButton("회원가입");
		accountBtn.setBounds(400, 474, 150, 30);
		accountBtn.addActionListener(this);
		getContentPane().add(accountBtn);
		
		IdSearchBtn = new JButton("아이디 찾기");
		IdSearchBtn.setBounds(50, 474, 150, 30);
		IdSearchBtn.addActionListener(this);
		getContentPane().add(IdSearchBtn);
		
		PwSearchBtn = new JButton("비밀번호 찾기");
		PwSearchBtn.setBounds(225, 474, 150, 30);
		PwSearchBtn.addActionListener(this);
		getContentPane().add(PwSearchBtn);
		
		button_bye= new JButton("탈퇴");
		button_bye.setBounds(386, 414, 129, 30);
		button_bye.addActionListener(this);
		getContentPane().add(button_bye);
		
		
		
		ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/LoginLogo.jpg")));
		jimage = new JLabel(null, image, JLabel.CENTER);
		jimage.setBounds(-108, 28, 1156, 272);
		getContentPane().add(jimage);
		
		



		 

//********* 아이디찾기
		label_ids = new JLabel("이메일");
		label_ids.setBounds(527, 330, 200, 30);
		getContentPane().add(label_ids);
		label_ids.setVisible(false);
		
		label_ids_Name = new JLabel("이름");
		label_ids_Name.setBounds(527, 395, 57, 15);
		getContentPane().add(label_ids_Name);
		label_ids_Name.setVisible(false);
		
		

		textField_ids_Email = new JTextField(10);
		textField_ids_Email.setBounds(527, 363, 218, 30);
		getContentPane().add(textField_ids_Email);
		textField_ids_Email.setVisible(false);
		
		textField_ids_Name = new JTextField(10);
		textField_ids_Name.setBounds(527, 414, 218, 29);
		getContentPane().add(textField_ids_Name);
		textField_ids_Name.setVisible(false);
		
		

		button_ids = new JButton("찾기");
		button_ids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if (btn == button_ids) {
					Delegate d = Delegate.getInstance();
					String id = d.memCtrl.idSearch(textField_ids_Email.getText(),textField_ids_Name.getText());
					if (id == null) {
						JOptionPane.showMessageDialog(null, "이메일이 없습니다");
					} else {
						se_id = new JTextField(" 찾으신 아이디는   [   " + id + "    ]  입니다. ");
						se_id.setEditable(false);
						se_id.setBounds(527, 464, 300, 40);
						getContentPane().add(se_id);
						textField_ids_Email.setText("");
						textField_ids_Name.setText("");
					}
				}
			}
		});
		button_ids.setBounds(757, 400, 57, 42);
		getContentPane().add(button_ids);
		button_ids.setVisible(false);

		button_close_id = new JButton("닫기");
		button_close_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(se_id == null)) se_id.setVisible(false);
				if (!(label_ids_Name == null)) label_ids_Name.setVisible(false);
				if (!(label_ids == null)) label_ids.setVisible(false);
				textField_ids_Email.setText("");
				textField_ids_Name.setText("");
				if (!(textField_ids_Email == null)) textField_ids_Email.setVisible(false);
				if (!(textField_ids_Name == null)) textField_ids_Name.setVisible(false);
				
				if (!(button_ids == null)) button_ids.setVisible(false);
				if (!(button_close_id == null)) button_close_id.setVisible(false);
			}
		});
		button_close_id.setBounds(826, 400, 57, 43);
		getContentPane().add(button_close_id);
		button_close_id.setVisible(false);

// ********* 비밀번호찾기
		label_pws = new JLabel("아이디");
		label_pws.setBounds(527, 330, 200, 30);
		getContentPane().add(label_pws);
		label_pws.setVisible(false);
		
		label_pws_Name = new JLabel("이메일");
		label_pws_Name.setBounds(527, 395, 57, 15);
		getContentPane().add(label_pws_Name);
		label_pws_Name.setVisible(false);

		textField_pws_ID = new JTextField(10);
		textField_pws_ID.setBounds(527, 362, 215, 30);
		getContentPane().add(textField_pws_ID);
		textField_pws_ID.setVisible(false);
		
		textField_pws_EMAIL = new JTextField(10);
		textField_pws_EMAIL.setBounds(527, 414, 218, 29);
		getContentPane().add(textField_pws_EMAIL);
		textField_pws_EMAIL.setVisible(false);
		

		button_pws = new JButton("찾기");
		button_pws.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if (btn == button_pws) {
					Delegate d = Delegate.getInstance();
					String pw = d.memCtrl.pwSearch(textField_pws_ID.getText(),textField_pws_EMAIL.getText());
					if (pw == null) {
						JOptionPane.showMessageDialog(null, "아이디가  없습니다");
					} else {
						se_pw = new JTextField("찾으신 비밀번호 는 [ " + pw + " ] 입니다. ");
						se_pw.setEditable(false);
						se_pw.setBounds(527, 464, 300, 40);
						getContentPane().add(se_pw);
						se_pw.setVisible(true);
						textField_pws_ID.setText("");
						textField_pws_EMAIL.setText("");
					}
				}
			}
		});
		button_pws.setBounds(757, 400, 57, 42);
		getContentPane().add(button_pws);
		button_pws.setVisible(false);

		
		button_close_pw = new JButton("닫기");
		button_close_pw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(label_pws == null)) label_pws.setVisible(false);
				if (!(label_pws_Name == null)) label_pws_Name.setVisible(false);
				textField_pws_ID.setText("");
				textField_pws_EMAIL.setText("");
				if (!(textField_pws_ID == null)) textField_pws_ID.setVisible(false);
				if (!(textField_pws_EMAIL == null)) textField_pws_EMAIL.setVisible(false);	
				if (!(se_pw == null)) se_pw.setVisible(false);
				
				if (!(button_pws == null)) button_pws.setVisible(false);
				if (!(button_close_pw == null)) button_close_pw.setVisible(false);
			}
		});
		button_close_pw.setBounds(826, 400, 57, 43);
		getContentPane().add(button_close_pw);
		button_close_pw.setVisible(false);

//-------------------------------------------------
		
		
		//액션 추가
		pwTextF.addKeyListener(this);

		

		
		
		setBounds(100, 100, 929, 590);
		getContentPane().setBackground(Color.white);
		

		

		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

}

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    JButton btn = (JButton)e.getSource();
		Delegate d = Delegate.getInstance();		
		if(btn == logBtn){		
			boolean b = d.memCtrl.loginAf(idTextF.getText(), pwTextF.getText());
			if (b) {
				this.dispose();
			}
		}
		else if(btn == accountBtn){			
			d.memCtrl.account();
			this.dispose();
		}else if(btn == IdSearchBtn){	
			
			label_ids.setVisible(true);
			label_ids_Name.setVisible(true);
			textField_ids_Email.setVisible(true);
			textField_ids_Name.setVisible(true);
			button_ids.setVisible(true);
			button_close_id.setVisible(true);
			
			if (!(label_pws == null)) label_pws.setVisible(false);
			if (!(label_pws_Name == null)) label_pws_Name.setVisible(false);
			if (!(textField_pws_ID == null)) textField_pws_ID.setVisible(false);
			if (!(textField_pws_EMAIL == null)) textField_pws_EMAIL.setVisible(false);
			if (!(button_pws == null)) button_pws.setVisible(false);
			if (!(button_close_pw == null)) button_close_pw.setVisible(false);
			if (!(se_pw == null)) se_pw.setVisible(false);
		}else if(btn == PwSearchBtn){
			
			label_pws.setVisible(true);
			label_pws_Name.setVisible(true);
			textField_pws_ID.setVisible(true);
			textField_pws_EMAIL.setVisible(true);
			button_pws.setVisible(true);
			button_close_pw.setVisible(true);
			
			
			if (!(label_ids == null)) label_ids.setVisible(false);
			if (!(label_ids_Name == null)) label_ids_Name.setVisible(false);
			if (!(textField_ids_Email == null)) textField_ids_Email.setVisible(false);
			if (!(textField_ids_Name == null)) textField_ids_Name.setVisible(false);
			if (!(button_ids == null)) button_ids.setVisible(false);
			if (!(button_close_id == null)) button_close_id.setVisible(false);
			if (!(se_id == null)) se_id.setVisible(false);
		}else if(btn == button_bye){
			boolean b = d.memCtrl.deleteMember(idTextF.getText(), pwTextF.getText());
			if(b){
				JOptionPane.showMessageDialog(null, "탈퇴성공!");
			}else
				JOptionPane.showMessageDialog(null, "탈퇴실패..");
		}
	}




	@Override
	public void keyTyped(KeyEvent e) {
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
		Delegate d = Delegate.getInstance();
		int c = e.getKeyChar();
		if (c == KeyEvent.VK_ENTER) {
			d.memCtrl.loginAf(idTextF.getText(), pwTextF.getText());
			this.dispose();
		}
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}