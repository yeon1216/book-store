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
    public int userNo; // �����ȣ
    public String id; // ���̵�
    public String password; // ��й�ȣ
    
    @Override
    public String toString() {
    	return "������ȣ : "+userNo+" / ���̵� : "+id+" / ��й�ȣ : "+password;
    }
	
    // User �⺻ ������
    public User() {
    }

    // User ������
    public User(int userNo, String id, String password) {
        this.userNo = userNo;
        this.id = id;
        this.password = password;
    }
    
    // �α��� �޼ҵ�
    public User login(ArrayList<User> userList, String idTF, String passwordF){
    	User loginUser = null;
    	for (int i = 0; i < userList.size(); i++) {
			if(idTF.equals(userList.get(i).id) && passwordF.equals(userList.get(i).password)){
				loginUser = userList.get(i);
			}
		}
    	return loginUser;
    }

    // ���� �޽�������Ʈ ���� �޼ҵ�
	public void showReceiveMessageList(Data data, JTable table) {
		Vector<String> headVector = new Vector<String>();
		headVector.add("�޽��� ��ȣ");
		headVector.add("���۽ð�");
		headVector.add("������");
		headVector.add("�޽��� ����");
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
	
	// ���� �޽�������Ʈ ���� �޼ҵ�
	public void showSendMessageList(Data data, JTable table) {
		Vector<String> headVector = new Vector<String>();
		headVector.add("�޽��� ��ȣ");
		headVector.add("���۽ð�");
		headVector.add("������");
		headVector.add("�޽��� ����");
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
	
	// �޽��� �б� �޼ҵ�
	public void readMessage(Data data, int messageNo, JTextArea messageContentTA){
		User loginU = findUserByUserNo(data.userList, MainFrame.LOGIN_STATE[1]);
		Message tempMS = findMessageByMessageNo(data, messageNo);
		int checkNum = 0; // 0:�߸� �Է��� ��ȣ, 1:
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
				JOptionPane.showMessageDialog(null, "�˸��� �޽��� ��ȣ�� �Է����ּ���.");				
				break;
			case 1:
				sendTime = timeToString(tempMS.messageWriteTime);
				str = " >> �޽��� ��ȣ : "+tempMS.mesageNo
						+"\n >> ���۽ð� :  "+sendTime
						+"\n >> ������ :  "+tempMS.receiveMassageUser.id
						+"\n >> �޽��� ���� \n"+tempMS.messageContent;
				messageContentTA.setText(str);
				break;
			case 2:
				sendTime = timeToString(tempMS.messageWriteTime);
				str = " >> �޽��� ��ȣ : "+tempMS.mesageNo
						+"\n >> ���۽ð� :  "+sendTime
						+"\n >> ������ :  "+tempMS.sendMassageUser.id
						+"\n >> �޽��� ���� \n"+tempMS.messageContent;
				messageContentTA.setText(str);
				break;
		}
	}
	
	// �޽��� ������ �޼ҵ�
	public void sendMessage(Data data, String receiveId, String content, JTable table){
		User loginU = findUserByUserNo(data.userList, MainFrame.LOGIN_STATE[1]);
		User receiveU = findUserById(data, receiveId);
		Message tempMS = new Message(data.messageList.size(), loginU, receiveU, content);
		data.messageList.add(tempMS);
		showSendMessageList(data, table);
	}
	
	// ���̵�� ����ã�� �޼ҵ�
	public User findUserById(Data data,String id){
		User findUser = null;
		for (int i = 0; i < data.userList.size(); i++) {
			if(id.equals(data.userList.get(i).id)){
				findUser = data.userList.get(i);
			}
		}
		return findUser;
	}
	
	// ������ȣ�� ���� ã��
	public User findUserByUserNo(ArrayList<User> userList, int userNo) {
		User findUser=null;
		for (int i = 0; i < userList.size(); i++) {
			if(userNo==userList.get(i).userNo){
				findUser=userList.get(i);
			}
		}
		return findUser;
	}
	
	// �޽�����ȣ�� �޽��� ã��
	public Message findMessageByMessageNo(Data data,int messageNo){
		Message findMessage=null;
		for (int i = 0; i < data.messageList.size(); i++) {
			if(messageNo==data.messageList.get(i).mesageNo){
				findMessage = data.messageList.get(i);
			}
		}
		return findMessage;
	}
	
	// Calendar�� ����Ͻú��ʷ� ��ȯ �޼ҵ�
	public String timeToString(Calendar time) {
//		String timeToString = (time.get(Calendar.YEAR)) + "�� " + (time.get(Calendar.MONTH) + 1) + "�� "
//				+ (time.get(Calendar.DAY_OF_MONTH)) + "�� " + (time.get(Calendar.HOUR_OF_DAY)) + "�� "
//				+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
		String timeToString = (time.get(Calendar.MONTH) + 1) + "�� "
				+ (time.get(Calendar.DAY_OF_MONTH)) + "�� " + (time.get(Calendar.HOUR_OF_DAY)) + "�� "
				+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
		return timeToString;
	}
	
} // User class

