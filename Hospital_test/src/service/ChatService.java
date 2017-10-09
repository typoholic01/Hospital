package service;

import java.net.Socket;

import dao.ChatDao;
import dao.ChatDaoImpl;
import dto.MemberDto;
import view.client.ClientFrame;

public class ChatService implements ChatServiceImpl {
	ChatDaoImpl chatDao = new ChatDao();

	@Override
	public void connect(MemberDto mem) {
		chatDao.connect(mem);
	}

	@Override
	public void start(Socket sock, ClientFrame cf, String id) {
		chatDao.start(sock, cf, id);
	}

	@Override
	public void sendMsg(Socket sock, ClientFrame cf, String id) {
		chatDao.sendMsg(sock, cf, id);
		
	}

}
