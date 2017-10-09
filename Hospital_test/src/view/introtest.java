package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class introtest extends JFrame {

	private JPanel contentPane;
	private JPanel paneReceive = new JPanel();
	private JPanel paneSend = new JPanel();
	private JPanel paneWrite = new JPanel();
	private JPanel paneWrite2 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					introtest frame = new introtest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public introtest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("의사 A", null, paneReceive, null);
		paneReceive.setLayout(null);
		tabbedPane.addTab("의사 B", null, paneSend, null);
		paneSend.setLayout(null);
		tabbedPane.addTab("의사 C", null, paneWrite, null);
		paneWrite.setLayout(null);
		tabbedPane.addTab("의사 D", null, paneWrite2, null);
		paneWrite2.setLayout(null);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

}
