package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import dao.client.ReadThread;
import dto.MemberDto;
import singleton.Delegate;
import view.client.ClientFrame;

public class ChatDao implements ChatDaoImpl {

	public void connect(MemberDto mem){
		Socket socket = null;
		ClientFrame cf;

		try {
			socket = new Socket("192.168.10.8", 9000);

			int localport = socket.getLocalPort();
			System.out.println("client LocalPort : "+ localport);

			Delegate d = Delegate.getInstance();
			System.out.println("id : " + mem.getId());

			System.out.println("Connect Success!");
			cf = new ClientFrame(socket,mem.getId());

			new ReadThread(socket, cf,mem).start();

		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	public void start(Socket sock, ClientFrame cf, String id){

		sendMsg(sock,cf,id);
		// 첫번째 Packet
		cf.isFirst = false;		
		//	cf.setTitle(id + "님  환영합니다.. "); 

		cf.txtA.setText("환영합니다.\n 자세한 상담은 게시판을 이용해 주세요.\n");


	}


	public void sendMsg(Socket sock, ClientFrame cf, String id) {		
		String str = "";
		PrintWriter pw = null;
		try {

			pw = new PrintWriter(sock.getOutputStream(), true);
			if(cf.isFirst){	// 첫번째 Packet전송
				str = id + "님이 접속하셨습니다";

			}else {
				str = " [ " + id + " 님  ] " + cf.txtF.getText();
			}

			pw.println(str);
			pw.flush();

		} catch (IOException e) {
			System.out.println("IOException");
		}

	}
}
