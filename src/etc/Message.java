package etc;

import java.util.Calendar;

import user.User;

public class Message {
	public int mesageNo; // �޽��� ��ȣ
	public User sendMassageUser; // �޽��� ������
	public User receiveMassageUser; // �޽��� ������
	public String messageContent; // �޽��� ����
	public Calendar messageWriteTime; // �޽��� ���� �ð�

	// ������
	public Message(int mesageNo, User sendMassageUser, User receiveMassageUser, String messageContent) {
		this.mesageNo = mesageNo;
		this.sendMassageUser = sendMassageUser;
		this.receiveMassageUser = receiveMassageUser;
		this.messageContent = messageContent;
		this.messageWriteTime = Calendar.getInstance();
	}

} // Message Ŭ����
