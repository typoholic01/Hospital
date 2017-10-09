package dao;

import java.net.Socket;

import dto.MemberDto;
import view.client.ClientFrame;

public interface ChatDaoImpl {

	public void connect(MemberDto mem);
	public void start(Socket sock, ClientFrame cf, String id);	
	public void sendMsg(Socket sock,ClientFrame cf,String id);

}
