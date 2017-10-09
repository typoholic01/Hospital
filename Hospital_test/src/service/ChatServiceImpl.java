package service;

import java.net.Socket;

import dto.MemberDto;
import view.client.ClientFrame;

public interface ChatServiceImpl {

	public void connect(MemberDto mem);

	public void start(Socket sock, ClientFrame clientFrame, String id);
	
	public void sendMsg(Socket sock,ClientFrame cf,String id);

}
