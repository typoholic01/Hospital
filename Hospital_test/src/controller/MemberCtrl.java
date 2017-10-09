package controller;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import dto.MemberDto;
import dto.MemberDto;
import service.ChatService;
import service.ChatServiceImpl;
import service.memberService;
import service.memberServiceImpl;
import singleton.Delegate;
import view.client.ClientFrame;
import view.login.AccountView;
import view.login.LoginView;

public class MemberCtrl {
	
    memberServiceImpl memberSer = new memberService();
	ChatServiceImpl chatService = new ChatService();
	
	public void login() {
		new LoginView();
	}
	
	public boolean loginAf(String id, String pw){
		MemberDto mem = memberSer.login(id,pw);
		boolean b = false;
		if (mem==null) {
			
		} else {
			Delegate dg = Delegate.getInstance();
			dg.getAuth=mem.getAuth();
			dg.getId=mem.getId();
			if (!(mem == null)) {
				chatService.connect(mem);
			}
			b = true;
		}
		return b;
	}
	
	public void account(){
		new AccountView();
	}
	
	public void addMember(String id, String pw, String name, String email,String phone) {

		
		boolean b = memberSer.addMember(new MemberDto(id, pw, name, email,phone, 3));
		
		if(b){
			JOptionPane.showMessageDialog(null, "회원가입성공!");
			new LoginView();			
		}else{
			JOptionPane.showMessageDialog(null, "회원가입실패");
			new AccountView();
		}		
	}
	
	public boolean IdCheck(String id) {
		return memberSer.getId(id);
	}
	
	public String idSearch(String email, String name){
		return memberSer.idSearch(email,name);
	}
	
	public String pwSearch(String id, String email){
		return memberSer.pwSearch(id,email);
	}
	public List<MemberDto> memberlist(String doct_name) {
		List<MemberDto> memberlist = new ArrayList<>();
		memberlist = memberSer.memberlist(doct_name);
		return memberlist;
	}

	public void start(Socket sock, ClientFrame clientFrame, String id) {
		chatService.start(sock,clientFrame,id);
	}

	public void sendMsg(Socket sock,ClientFrame cf, String id) {
		chatService.sendMsg(sock,cf,id);
		
	}

	public boolean deleteMember(String id, String pw) {
	    return memberSer.deleteMember(id,pw);
		
	}
	
	
	
	

}
