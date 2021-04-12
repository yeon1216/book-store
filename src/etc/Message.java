package etc;

import java.util.Calendar;

import user.User;

public class Message {
	public int mesageNo; // 메시지 번호
	public User sendMassageUser; // 메시지 전송자
	public User receiveMassageUser; // 메시지 수신자
	public String messageContent; // 메시지 내용
	public Calendar messageWriteTime; // 메시지 전송 시간

	// 생성자
	public Message(int mesageNo, User sendMassageUser, User receiveMassageUser, String messageContent) {
		this.mesageNo = mesageNo;
		this.sendMassageUser = sendMassageUser;
		this.receiveMassageUser = receiveMassageUser;
		this.messageContent = messageContent;
		this.messageWriteTime = Calendar.getInstance();
	}

} // Message 클래스
