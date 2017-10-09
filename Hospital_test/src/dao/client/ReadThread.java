package dao.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import dto.MemberDto;
import singleton.Delegate;
import view.client.ClientFrame;

public class ReadThread extends Thread{

	Socket socket;
	ClientFrame cf;
	MemberDto mem;

	public ReadThread(Socket socket, ClientFrame cf,MemberDto mem) {
		this.cf = cf;
		this.socket = socket;
		this.mem = mem;
	}

	@Override
	public void run() {	
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String str = null;
			while(true){
				str = br.readLine();
				if(str == null){
					System.out.println("접속 끊김");
					break;
				} else
					//유저가 의사일 경우 모든 메세지를 출력한다
					if (mem.getId().equals("doct1") || mem.getId().equals("doct2") || mem.getId().equals("doct3") || mem.getId().equals("doct4")) {
						cf.txtA.append(str + "\n");		
					////환자일 경우 의사 아이디가 포함된 메세지만 출력한다
					//귓속말 조건을 주어 자기자신의 이야기만 리드한다
					} else if ((str.contains("doct1")||str.contains("doct2")||str.contains("doct3")||str.contains("doct4")) && (str.contains("/w "+mem.getId())||str.contains("/ㅈ "+mem.getId()))) {
						str = str.replace("/w "+mem.getId(), "");
						str = str.replace("/ㅈ "+mem.getId(), "");
						
						cf.txtA.append(str + "\n");		
					}
			}

		} catch (IOException e) {
			System.out.println("ReadThread catch");
		}
	}

}


