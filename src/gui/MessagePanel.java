package gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import system.Data;
import user.User;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessagePanel extends JPanel {
	public JTable receiveMessageTable;
	public JTable sendMessageTable;
	public JTextField inputMessageNoTF;
	public JTextField inputIdTF;
	public JTextArea messageWriteTA;
	public JTextArea messageContentTA;
	public JButton messageSendBtn;
	public JButton userIdCheckBtn;
	public JButton messageNoCheckBtn;
	public JLabel tempMessageLengthLabel;
	public JButton cancelBtn;
	
	String inputId;
	boolean messageSendCheck;
	
	/**
	 * Create the panel.
	 */
	public MessagePanel(Data data, JPanel cardLayoutPanel) {
		User user = new User();
		
		this.setBounds(14, 60, 1246, 613);
		setLayout(null);
		
		messageSendCheck=false;
		
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  받은 메시지 테이블 시작  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		JLabel lblNewLabel_5 = new JLabel("받은 메시지");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 20, 250, 30);
		add(lblNewLabel_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 63, 588, 233);
		add(scrollPane);
		receiveMessageTable = new JTable();
		receiveMessageTable.setEnabled(false);
		user.showReceiveMessageList(data,receiveMessageTable);
		scrollPane.setViewportView(receiveMessageTable);
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  받은 메시지 테이블 종료  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  보낸 메시지 테이블 시작  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		JLabel label = new JLabel("보낸 메시지");
		label.setFont(new Font("굴림", Font.BOLD, 20));
		label.setBounds(35, 314, 250, 30);
		add(label);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(14, 356, 588, 233);
		add(scrollPane2);
		sendMessageTable = new JTable();
		sendMessageTable.setEnabled(false);
		user.showSendMessageList(data,sendMessageTable);
		scrollPane2.setViewportView(sendMessageTable);
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  보낸 메시지 테이블 종료  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //

		
		
		JLabel label_1 = new JLabel("\uBA54\uC2DC\uC9C0 \uC77D\uAE30");
		label_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_1.setBounds(687, 32, 250, 30);
		add(label_1);
		
		JLabel label_2 = new JLabel("\uBA54\uC2DC\uC9C0 \uC804\uC1A1");
		label_2.setFont(new Font("굴림", Font.BOLD, 20));
		label_2.setBounds(687, 289, 127, 30);
		add(label_2);
		
		JLabel label_3 = new JLabel("\uBA54\uC2DC\uC9C0\uBC88\uD638 \uC785\uB825");
		label_3.setBounds(687, 74, 112, 18);
		add(label_3);
		
		inputMessageNoTF = new JTextField();
		inputMessageNoTF.setBounds(837, 71, 116, 24);
		add(inputMessageNoTF);
		inputMessageNoTF.setColumns(10);
		
		messageNoCheckBtn = new JButton("\uD655 \uC778");
		messageNoCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int messageNo = Integer.parseInt(inputMessageNoTF.getText().trim());
				user.readMessage(data, messageNo, messageContentTA);
			}
		});
		messageNoCheckBtn.setBounds(1000, 70, 105, 27);
		add(messageNoCheckBtn);
		
		messageContentTA = new JTextArea();
		messageContentTA.setEditable(false);
		messageContentTA.setWrapStyleWord(true);
		messageContentTA.setLineWrap(true);
		messageContentTA.setBounds(687, 109, 445, 151);
		add(messageContentTA);
		
		JScrollPane scrollPane3 = new JScrollPane(messageContentTA);
		scrollPane3.setBounds(687, 109, 445, 151);
		add(scrollPane3);
		
		JLabel label_4 = new JLabel("\uC218\uC2E0 \uC544\uC774\uB514");
		label_4.setBounds(687, 344, 80, 18);
		add(label_4);
		
		inputIdTF = new JTextField();
		inputIdTF.setColumns(10);
		inputIdTF.setBounds(777, 341, 116, 24);
		add(inputIdTF);
		
		userIdCheckBtn = new JButton("\uD655 \uC778");
		userIdCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputId = inputIdTF.getText().trim();
				User sendUser = user.findUserById(data, inputId);
				User loginUser = user.findUserByUserNo(data.userList, MainFrame.LOGIN_STATE[1]);
				if(sendUser!=null && !sendUser.id.equals(loginUser.id)){
					messageSendBtn.setEnabled(true);
					messageWriteTA.setEditable(true);
					new Thread(){
						public void run(){
							System.out.println("<<MessagePanel>> 입력한 아이디가 바뀌면 전송버튼을 비활성화하는 쓰레드 시작");
							String tempId = inputId;
							while(true){
								if(!tempId.equals(inputIdTF.getText().trim())){
									messageSendBtn.setEnabled(false);
									messageWriteTA.setEditable(false);
									break;
								}
							}
							System.out.println("<<MessagePanel>> 입력한 아이디가 바뀌면 전송버튼을 비활성화하는 쓰레드 종료");
						}
					}.start();
//					new Thread(){
//						public void run(){
//							System.out.println("<<MessagePanel>> 메시지 글자수를 실시간으로 카운팅하는 쓰레드 시작");
//							while(true){
//								if(messageSendCheck){
//									messageSendCheck=false;
//									break;
//								}
//								int tempLength = messageWriteTA.getText().length();
//								tempMessageLengthLabel.setText(String.valueOf(tempLength));
//							}
//							System.out.println("<<MessagePanel>> 메시지 글자수를 실시간으로 카운팅하는 쓰레드 종료");
//						}
//					}.start();
				}else{
					JOptionPane.showMessageDialog(null, "알맞은 아이디를 입력해주세요.");
				}
				
			}
		});
		userIdCheckBtn.setBounds(921, 340, 80, 27);
		add(userIdCheckBtn);
		
		messageWriteTA = new JTextArea();
		messageWriteTA.setEditable(false);
		messageWriteTA.setWrapStyleWord(true);
		messageWriteTA.setLineWrap(true);
		messageWriteTA.setBounds(687, 391, 445, 140);
		add(messageWriteTA);
		
		JScrollPane scrollPane4 = new JScrollPane(messageWriteTA);
		scrollPane4.setBounds(687, 391, 445, 140);
		add(scrollPane4);
		
		messageSendBtn = new JButton("전 송");
		messageSendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String receiveId = inputIdTF.getText().trim();
				String content = messageWriteTA.getText();
				user.sendMessage(data, receiveId, content, sendMessageTable);
				inputIdTF.setText("");
				messageWriteTA.setText("");
				messageSendCheck=true;
//				tempMessageLengthLabel.setText("0");
			}
		});
		messageSendBtn.setEnabled(false);
		messageSendBtn.setBounds(1052, 340, 80, 27);
		add(messageSendBtn);
		
		cancelBtn = new JButton("뒤로가기");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputMessageNoTF.setText("");
				messageContentTA.setText("");
				messageWriteTA.setText("");
				inputIdTF.setText("");
				messageSendBtn.setEnabled(false);
//				tempMessageLengthLabel.setText("0");
				cardLayoutPanel.setVisible(false);
			}
		});
		cancelBtn.setBounds(1027, 562, 105, 27);
		add(cancelBtn);
		
//		JLabel lblNewLabel = new JLabel("\uD604\uC7AC \uAE00\uC790\uC218 : ");
//		lblNewLabel.setBounds(687, 566, 96, 18);
//		add(lblNewLabel);
//		
//		tempMessageLengthLabel = new JLabel("0");
//		tempMessageLengthLabel.setBounds(797, 566, 35, 18);
//		add(tempMessageLengthLabel);
		
		JLabel label_5 = new JLabel("(\uAD00\uB9AC\uC790 \uC544\uC774\uB514 : admin)");
		label_5.setBounds(817, 297, 184, 18);
		add(label_5);
		
	} // 메시지 패널 생성자
} // 메시지 패널 클래스
