package view.letter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class LetterDetailView extends JFrame implements ActionListener 
{
	private JPanel contentPane;

	JButton btnWindowClose = new JButton("닫기");
	public LetterDetailView(Object[][] searchRowData) {
		
		//초기화
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//요소 선언
		JLabel lblName = new JLabel(searchRowData[0][3].toString());
		JLabel lblNim = new JLabel("님이 보낸 쪽지입니다");
		JScrollPane scrollPane = new JScrollPane();
		JTextPane txtpnContent = new JTextPane();
		txtpnContent.setText(searchRowData[0][2].toString());

		//요소 추가
		contentPane.add(lblName);
		contentPane.add(lblNim);
		scrollPane.setViewportView(txtpnContent);
		txtpnContent.setEditable(false);
		contentPane.add(scrollPane);
		contentPane.add(btnWindowClose);

		//모양 잡기
		lblName.setBounds(12, 10, 57, 15);
		lblNim.setBounds(80, 10, 150, 15);
		scrollPane.setBounds(12, 35, 410, 360);
		btnWindowClose.setBounds(190, 401, 76, 23);
		
		
		//액션 추가
		btnWindowClose.addActionListener(this);
		
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnWindowClose)) {
			dispose();
		}
		
	}
}
