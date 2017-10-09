package view.login;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.w3c.dom.events.MouseEvent;

import singleton.Delegate;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class AccountView extends JFrame implements ActionListener, ItemListener {

	private JButton accountBtn;
	private JButton idBtn;
	
	private JTextField idTextF;
	private JTextField passTextF ;
	private JTextField nameTextF;
	private JTextField emailTextF;
	private JTextField emailTextF_1;
	private JTextField phoneTextF;
	
	private  JLabel passlabel;
	
	Choice choice;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	
	boolean id = false;
	String id1="";

	
	public AccountView() {
		super("회원가입");
		getContentPane().setLayout(null);		
		
		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(31, 67, 67, 15);
		getContentPane().add(idLabel);
		
		idTextF = new JTextField("아이디 영문,숫자입력");
		idTextF.setBounds(98, 60, 150, 30);
		getContentPane().add(idTextF);
		idTextF.setForeground(Color.gray);
		idTextF.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				idTextF.setText("");
			}
			public void mousePressed(java.awt.event.MouseEvent e) {}
			public void mouseReleased(java.awt.event.MouseEvent e) {}
			public void mouseEntered(java.awt.event.MouseEvent e) {}
			public void mouseExited(java.awt.event.MouseEvent e) {}
		});



		idBtn = new JButton("중복확인");
		idBtn.addActionListener(this);
		idBtn.setBounds(260, 59, 81, 30);
		getContentPane().add(idBtn);
		
		JLabel passLabel = new JLabel("비밀번호");
		passLabel.setBounds(31, 124, 67, 15);
		getContentPane().add(passLabel);
		
		passTextF = new JTextField("비밀번호 8자리 이상 입력");
		passTextF.setForeground(Color.gray);
		passTextF.setBounds(98, 117, 150, 30);
		getContentPane().add(passTextF);
		passTextF.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				passTextF.setText("");
			}
			public void mousePressed(java.awt.event.MouseEvent e) {}
			public void mouseReleased(java.awt.event.MouseEvent e) {}
			public void mouseEntered(java.awt.event.MouseEvent e) {}
			public void mouseExited(java.awt.event.MouseEvent e) {}
		});


		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(31, 184, 67, 15);
		getContentPane().add(nameLabel);
		
		nameTextF = new JTextField();
		nameTextF.setBounds(98, 177, 150, 30);
		getContentPane().add(nameTextF);
		
		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setBounds(31, 244, 67, 15);
		getContentPane().add(emailLabel);
		
		emailTextF = new JTextField();
		emailTextF.setBounds(98, 237, 67, 30);
		getContentPane().add(emailTextF);
		
		emailTextF_1 = new JTextField();
		emailTextF_1.setBounds(185, 237, 67, 30);
		getContentPane().add(emailTextF_1);
		
	    choice = new Choice();
	    choice.setSize(81, 30);
	    choice.setLocation(260, 237);
	    choice.add("직접입력");
	    choice.add("naver.com");
	    choice.add("daum.net");
	    choice.add("gmail.com");
	    choice.addItemListener(this);
	    getContentPane().add(choice);
	    
		lblNewLabel = new JLabel("@");
		lblNewLabel.setBounds(166, 237, 21, 28);
		lblNewLabel.setForeground(Color.GRAY);
		getContentPane().add(lblNewLabel);
		
		
		JLabel phoneLabel = new JLabel("전화번호");
		phoneLabel.setBounds(31, 301, 67, 15);
		getContentPane().add(phoneLabel);
		
		accountBtn = new JButton("회원가입");
		accountBtn.addActionListener(this);
		accountBtn.setBounds(12, 353, 329, 30);
		getContentPane().add(accountBtn);		
		
		phoneTextF = new JTextField();
		phoneTextF.setDocument(new JTextFieldLimit(3));
		phoneTextF.setBounds(98, 294, 44, 30);
		getContentPane().add(phoneTextF);
		phoneTextF.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		textField = new JTextField();
		textField.setDocument(new JTextFieldLimit(4));
		textField.setBounds(166, 294, 44, 30);
		getContentPane().add(textField);
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		textField_1 = new JTextField();
		textField_1.setDocument(new JTextFieldLimit(4));
		textField_1.setBounds(227, 294, 44, 30);
		getContentPane().add(textField_1);
		textField_1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setBounds(144, 301, 21, 15);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("-");
		lblNewLabel_2.setBounds(208, 301, 21, 15);
		getContentPane().add(lblNewLabel_2);
		
		
		JButton button = new JButton("뒤로가기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delegate d = Delegate.getInstance();
				d.memCtrl.login();
			
			}
		});
		button.setBounds(276, 10, 81, 21);
		getContentPane().add(button);
		

		getContentPane().setBackground(Color.white);
		setBounds(500, 200, 377, 443);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String btnStr = btn.getLabel();
		boolean b = true;
	
		Delegate d = Delegate.getInstance();
		if (btnStr.equals("중복확인")) {
			b = d.memCtrl.IdCheck(idTextF.getText());
			if (b) {
				JOptionPane.showMessageDialog(null, "사용불가 ID입니다");
				idTextF.setText("");
			}else if (!idTextF.getText().matches("[a-z0-9]{1,}")) {
				JOptionPane.showMessageDialog(null, "아이디는 영문과 숫자만 입력해주세요");
			} 	else {
				JOptionPane.showMessageDialog(null, "사용 가능한 ID입니다");
		        id = true;
		        id1 = idTextF.getText();
			}
		} else if (btnStr.equals("회원가입")) {
		    String email = emailTextF.getText() + "@"+emailTextF_1.getText();
			System.out.println(email);
			String phone = phoneTextF.getText()+textField.getText()+textField_1.getText();
			//조건부
			if (!idTextF.getText().matches("[a-z0-9]{1,}")) {
				JOptionPane.showMessageDialog(null, "아이디는 영문과 숫자만 입력해주세요");
				return;
			}
			if (passTextF.getText().length() < 8) {
				JOptionPane.showMessageDialog(null, "비밀번호를 8자 이상 입력해주세요");
				return;
			}if (!(phoneTextF.getText().matches("[0-9]{1,}")&&textField.getText().matches("[0-9]{1,}")
					&&textField_1.getText().matches("[0-9]{1,}") && phoneTextF.getText().length()==3 &&
					textField.getText().length()==4  && textField_1.getText().length()==4)
					){
			
				JOptionPane.showMessageDialog(null, "핸드폰번호 다시 입력해주세요");
				return;
			} if(!(idTextF.getText().equals(id1)) || id == false  ){
				JOptionPane.showMessageDialog(null, "ID 중복확인 해주세요");
				return;
			}
			d.memCtrl.addMember(idTextF.getText(), passTextF.getText(), nameTextF.getText(), email,
					phone);
		
		     this.dispose();
		
	}
}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Choice cho = (Choice)e.getSource();
		String selectecd = cho.getSelectedItem();
				if (selectecd.equals("naver.com")) {
					emailTextF_1.setText("naver.com");
				} else if (selectecd.equals("daum.net")) {
					emailTextF_1.setText("daum.net");
				} else if (selectecd.equals("gmail.com")) {
					emailTextF_1.setText("gmail.com");
				} else if (emailTextF_1.equals("직접입력")) {
					emailTextF_1.setText("직접입력");
				}
	}
	
	public class JTextFieldLimit extends PlainDocument
	{
	    private static final long serialVersionUID = 1L;

	    private int     limit;

	    // optional uppercase conversion
	    private boolean toUppercase = false;

	    JTextFieldLimit(int limit)
	    {
	        super();
	        this.limit = limit;
	    }

	    JTextFieldLimit(int limit, boolean upper)
	    {
	        super();
	        this.limit = limit;
	        toUppercase = upper;
	    }

	    public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws BadLocationException
	    {
	        if (str == null) return;

	        if ((getLength() + str.length()) <= limit)
	        {
	            if (toUppercase) str = str.toUpperCase();
	           
	            super.insertString(offset, str, attr);
	        }
	    }
	}
	




}
