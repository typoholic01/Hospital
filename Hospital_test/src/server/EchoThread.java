package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import dto.MemberDto;
import singleton.Delegate;

public class EchoThread extends Thread {
	
	Socket socket;
	Vector<Socket> vec;
	
	public EchoThread(Socket socket, Vector<Socket> vec) {
		this.socket = socket;
		this.vec = vec;

		System.out.println("연결IP:" + socket.getInetAddress()
						+ " Port:" + socket.getPort());
	}

	@Override
	public void run() {		
		BufferedReader br = null;
		Delegate d = Delegate.getInstance();
		try {
			br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			
			String str = null;
			
			while((str = br.readLine()) != null){
				// recv
//				Delegate dg= Delegate.getInstance();
//			    String id = dg.getId;
//				System.out.println("id   :   " + id );
				
				System.out.println("str:" + str);
				
				// send
				for (Socket sock : vec) {
					if(sock != this.socket){
						PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);
						pw.println(str);
						pw.flush();
					}
				}
				

			}
			
		} catch (IOException e) {
			System.out.println("연결이 끊김 IP:" + socket.getInetAddress() + " Port:" + socket.getPort());
			vec.remove(this.socket);
			
			try {
				socket.close();
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
    public static void main(String[] args) {
		
		ServerSocket serverSock = null;
		Socket socket = null;
		
		// list == Vector, ArrayList, LinkedList
		Vector<Socket> vec = new Vector<>(); 
		
		try {
			serverSock = new ServerSocket(9000);
			
			while(true){
			
				System.out.println("접속 대기중...");
				socket = serverSock.accept();
				vec.add(socket);
				new EchoThread(socket, vec).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}