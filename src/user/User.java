package user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import etc.Message;
import gui.MainFrame;
import system.Data;


public class User {
    public int userNo; // 멤버번호
    public String id; // 아이디
    public String password; // 비밀번호
    
    @Override
    public String toString() {
    	return "유저번호 : "+userNo+" / 아이디 : "+id+" / 비밀번호 : "+password;
    }
	
    // User 기본 생성자
    public User() {
    }

    // User 생성자
    public User(int userNo, String id, String password) {
        this.userNo = userNo;
        this.id = id;
        this.password = password;
    }
    
    // 로그인 메소드
    public User login(ArrayList<User> userList, String idTF, String passwordF){
    	User loginUser = null;
    	for (int i = 0; i < userList.size(); i++) {
			if(idTF.equals(userList.get(i).id) && passwordF.equals(userList.get(i).password)){
				loginUser = userList.get(i);
			}
		}
    	return loginUser;
    }

    // 받은 메시지리스트 보기 메소드
	public void showReceiveMessageList(Data data, JTable table) {
		Vector<String> headVector = new Vector<String>();
		headVector.add("메시지 번호");
		headVector.add("전송시간");
		headVector.add("전송자");
		headVector.add("메시지 내용");
		Vector<Vector> tableDataVector = new Vector<Vector>();
		for (int i = 0; i < data.messageList.size(); i++) {
			Message tempMS = data.messageList.get(i);
			User loginU = findUserByUserNo(data.userList, MainFrame.LOGIN_STATE[1]);
			if(loginU!=null){
				if(tempMS.receiveMassageUser.userNo==loginU.userNo){
					Vector<String> rowVector = new Vector<String>();
					String messageSendTime = timeToString(tempMS.messageWriteTime);
					rowVector.add(String.valueOf(tempMS.mesageNo));
					rowVector.add(messageSendTime);			
					rowVector.add(tempMS.sendMassageUser.id);
					rowVector.add(tempMS.messageContent);
					tableDataVector.add(rowVector);			
				}
			}
		}
		table.setModel(new DefaultTableModel(tableDataVector, headVector));
	}
	
	// 보낸 메시지리스트 보기 메소드
	public void showSendMessageList(Data data, JTable table) {
		Vector<String> headVector = new Vector<String>();
		headVector.add("메시지 번호");
		headVector.add("전송시간");
		headVector.add("수신자");
		headVector.add("메시지 내용");
		Vector<Vector> tableDataVector = new Vector<Vector>();
		for (int i = 0; i < data.messageList.size(); i++) {
			Message tempMS = data.messageList.get(i);
			User loginU = findUserByUserNo(data.userList, MainFrame.LOGIN_STATE[1]);
			if(loginU != null){
				if(tempMS.sendMassageUser.userNo==loginU.userNo){
					Vector<String> rowVector = new Vector<String>();
					String messageSendTime = timeToString(tempMS.messageWriteTime);
					rowVector.add(String.valueOf(tempMS.mesageNo));
					rowVector.add(messageSendTime);			
					rowVector.add(tempMS.receiveMassageUser.id);
					rowVector.add(tempMS.messageContent);
					tableDataVector.add(rowVector);			
				}				
			}
			
		}
		table.setModel(new DefaultTableModel(tableDataVector, headVector));
	}
	
	// 메시지 읽기 메소드
	public void readMessage(Data data, int messageNo, JTextArea messageContentTA){
		User loginU = findUserByUserNo(data.userList, MainFrame.LOGIN_STATE[1]);
		Message tempMS = findMessageByMessageNo(data, messageNo);
		int checkNum = 0; // 0:잘못 입력한 번호, 1:
		boolean check=false;
		if(tempMS!=null){
			for (int i = 0; i < data.messageList.size(); i++) {
				if(loginU.userNo==tempMS.sendMassageUser.userNo){
					checkNum = 1;
				}else if(loginU.userNo==tempMS.receiveMassageUser.userNo){
					checkNum = 2;
				}
			}
		}
		String sendTime;
		String str;
		switch(checkNum){
			case 0:
				JOptionPane.showMessageDialog(null, "알맞은 메시지 번호를 입력해주세요.");				
				break;
			case 1:
				sendTime = timeToString(tempMS.messageWriteTime);
				str = " >> 메시지 번호 : "+tempMS.mesageNo
						+"\n >> 전송시간 :  "+sendTime
						+"\n >> 수신자 :  "+tempMS.receiveMassageUser.id
						+"\n >> 메시지 내용 \n"+tempMS.messageContent;
				messageContentTA.setText(str);
				break;
			case 2:
				sendTime = timeToString(tempMS.messageWriteTime);
				str = " >> 메시지 번호 : "+tempMS.mesageNo
						+"\n >> 전송시간 :  "+sendTime
						+"\n >> 전송자 :  "+tempMS.sendMassageUser.id
						+"\n >> 메시지 내용 \n"+tempMS.messageContent;
				messageContentTA.setText(str);
				break;
		}
	}
	
	// 메시지 보내기 메소드
	public void sendMessage(Data data, String receiveId, String content, JTable table){
		User loginU = findUserByUserNo(data.userList, MainFrame.LOGIN_STATE[1]);
		User receiveU = findUserById(data, receiveId);
		Message tempMS = new Message(data.messageList.size(), loginU, receiveU, content);
		data.messageList.add(tempMS);
		showSendMessageList(data, table);
	}
	
	// 아이디로 유저찾기 메소드
	public User findUserById(Data data,String id){
		User findUser = null;
		for (int i = 0; i < data.userList.size(); i++) {
			if(id.equals(data.userList.get(i).id)){
				findUser = data.userList.get(i);
			}
		}
		return findUser;
	}
	
	// 유저번호로 유저 찾기
	public User findUserByUserNo(ArrayList<User> userList, int userNo) {
		User findUser=null;
		for (int i = 0; i < userList.size(); i++) {
			if(userNo==userList.get(i).userNo){
				findUser=userList.get(i);
			}
		}
		return findUser;
	}
	
	// 메시지번호로 메시지 찾기
	public Message findMessageByMessageNo(Data data,int messageNo){
		Message findMessage=null;
		for (int i = 0; i < data.messageList.size(); i++) {
			if(messageNo==data.messageList.get(i).mesageNo){
				findMessage = data.messageList.get(i);
			}
		}
		return findMessage;
	}
	
	// Calendar를 년월일시분초로 반환 메소드
	public String timeToString(Calendar time) {
//		String timeToString = (time.get(Calendar.YEAR)) + "년 " + (time.get(Calendar.MONTH) + 1) + "월 "
//				+ (time.get(Calendar.DAY_OF_MONTH)) + "일 " + (time.get(Calendar.HOUR_OF_DAY)) + "시 "
//				+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
		String timeToString = (time.get(Calendar.MONTH) + 1) + "월 "
				+ (time.get(Calendar.DAY_OF_MONTH)) + "일 " + (time.get(Calendar.HOUR_OF_DAY)) + "시 "
				+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
		return timeToString;
	}
	
} // User class

