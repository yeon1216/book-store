package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import user.Admin;
import user.Member;
import user.User;

public class JoinPanel extends JPanel {
	
	public JButton joinBtn;
	public JTextField joinIdTF;
//	public JTextField joinPasswordTF;
	public JPasswordField joinPasswordTF;
//	public JTextField joinPasswordCheckTF;
	public JPasswordField joinPasswordCheckTF;
	public JTextField joinNameTF;
	public JTextField joinPhoneTF;
	public JTextField joinBirthdayTF;
	public JTextField joinAddressTF;
	public JRadioButton joinManRadioBtn;
	public JRadioButton joinGirlRadioBtn;
	public JButton joinDuplicationCheckBtn;
	public JButton cancelBtn;
	
	
	public String tempId; // 회원가입에서 아이디 중복확인 버튼 클릭시 입력되었던 아이디값
	
	public boolean threadCheck=true;
	
	public JoinPanel() {
		super();
	}

	/**
	 * Create the panel.
	 */
	public JoinPanel(ArrayList<User> userList, JPanel cardLayoutPanel, AdminMemberListPanel adminMemberListPanel) {
		
		Member member = new Member();
		
		this.setBounds(14, 60, 1246, 613);
		this.setLayout(null);

		joinBtn = new JButton("회원가입");
		joinBtn.setEnabled(false);
		joinBtn.addActionListener(new ActionListener() {
			public synchronized void actionPerformed(ActionEvent e) {
				threadCheck=false;
				String id = joinIdTF.getText().trim();
				String pass = joinPasswordTF.getText().trim();
				String passCheck = joinPasswordCheckTF.getText().trim();
				String name = joinNameTF.getText().trim();
				String phone = joinPhoneTF.getText().trim();
				String birthday = joinBirthdayTF.getText().trim();
				String gender = null;
				String address = joinAddressTF.getText().trim();

				if (id.length() == 0 || pass.length() == 0 || passCheck.length() == 0 || name.length() == 0 || phone.length() == 0 
						|| birthday.length() == 0 || address.length() == 0) {
					JOptionPane.showMessageDialog(null, "모두 다 입력해주세요");
					return;
				}

				if (pass.length() < 6) {
					JOptionPane.showMessageDialog(null, "비밀번호는 6글자 이상이 필요합니다.");
					return;
				}
				if (!pass.equals(passCheck)) {
					JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 다릅니다.");
					return;
				}
				if (phone.length() != 11 && (!phone.substring(0, 3).equals("010")|| !phone.substring(0, 3).equals("011")
						|| !phone.substring(0, 3).equals("017")|| !phone.substring(0, 3).equals("016")
						|| !phone.substring(0, 3).equals("018")|| !phone.substring(0, 3).equals("019"))) {
					JOptionPane.showMessageDialog(null, "전화번호를 바르게 입력해주세요");
					return;
				}
				if (member.phoneDuplicationCheck(userList, phone)) {
					JOptionPane.showMessageDialog(null, "중복된 전화번호가 있습니다. 자신의 번호를 입력해주세요.");
					return;
				}
				if (birthday.length() != 8) {
					JOptionPane.showMessageDialog(null, "생년월일을 바르게 입력해주세요");
					return;
				}
				
				if (joinManRadioBtn.isSelected()) {
					gender = "남자";
				} else if (joinGirlRadioBtn.isSelected()) {
					gender = "여자";
				}

				member.join(userList,userList.size(),id,pass,name,phone,birthday, gender, address, adminMemberListPanel); 
						
				cardLayoutPanel.setVisible(false);
				JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다.");
				joinIdTF.setText("");
				joinPasswordTF.setText("");
				joinPasswordCheckTF.setText("");
				joinNameTF.setText("");
				joinPhoneTF.setText("");
				joinBirthdayTF.setText("");
				joinAddressTF.setText("");
				joinIdTF.setEnabled(true);
				joinPasswordTF.setEnabled(false);
				joinPasswordCheckTF.setEnabled(false);
				joinNameTF.setEnabled(false);
				joinPhoneTF.setEnabled(false);
				joinBirthdayTF.setEnabled(false);
				joinManRadioBtn.setEnabled(false);
				joinGirlRadioBtn.setEnabled(false);
				joinAddressTF.setEnabled(false);
				joinDuplicationCheckBtn.setEnabled(true);
			}
		});
		joinBtn.setBounds(581, 532, 105, 27);
		this.add(joinBtn);
		
		

		JLabel lblNewLabel = new JLabel("\uC544\uC774\uB514 (10\uAE00\uC790 \uC774\uD558)");
		lblNewLabel.setBounds(219, 54, 139, 18);
		this.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		lblNewLabel_1.setBounds(219, 157, 91, 18);
		this.add(lblNewLabel_1);

		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638 (6\uAE00\uC790 \uC774\uC0C1, 10\uAE00\uC790 \uC774\uD558)");
		label.setBounds(219, 114, 234, 18);
		this.add(label);

		JLabel label_1 = new JLabel("\uC774\uB984");
		label_1.setBounds(219, 215, 62, 18);
		this.add(label_1);

		JLabel lblex_1 = new JLabel("\uC804\uD654\uBC88\uD638 (ex> 01012351456)");
		lblex_1.setBounds(219, 270, 353, 18);
		this.add(lblex_1);

		JLabel lblex = new JLabel("\uC0DD\uB144\uC6D4\uC77C (ex> 19931216)");
		lblex.setBounds(219, 324, 160, 18);
		this.add(lblex);

		JLabel label_4 = new JLabel("\uC131\uBCC4");
		label_4.setBounds(219, 378, 62, 18);
		this.add(label_4);

		JLabel label_5 = new JLabel("\uC8FC\uC18C");
		label_5.setBounds(219, 426, 62, 18);
		this.add(label_5);

		joinIdTF = new JTextField();
		joinIdTF.setBounds(607, 54, 116, 24);
		this.add(joinIdTF);
		joinIdTF.setColumns(10);
		joinIdTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= 10) {
					e.consume();
				}
			}
		}); // joinIdTF 입력 글자수 제한

//		joinPasswordTF = new JTextField();
		joinPasswordTF = new JPasswordField();
		joinPasswordTF.setBounds(607, 114, 116, 24);
		this.add(joinPasswordTF);
		joinPasswordTF.setColumns(10);
		joinPasswordTF.setEnabled(false);
		joinPasswordTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= 10) {
					e.consume();
				}
			}
		}); // joinPasswordTF 입력 글자수 제한

//		joinPasswordCheckTF = new JTextField();
		joinPasswordCheckTF = new JPasswordField();
		joinPasswordCheckTF.setBounds(607, 157, 116, 24);
		this.add(joinPasswordCheckTF);
		joinPasswordCheckTF.setColumns(10);
		joinPasswordCheckTF.setEnabled(false);
		joinPasswordCheckTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= 10) {
					e.consume();
				}
			}
		}); // joinPasswordCheckTF 입력 글자수 제한

		joinNameTF = new JTextField();
		joinNameTF.setBounds(607, 215, 116, 24);
		this.add(joinNameTF);
		joinNameTF.setColumns(10);
		joinNameTF.setEnabled(false);

		joinPhoneTF = new JTextField();
		joinPhoneTF.setBounds(607, 270, 116, 24);
		this.add(joinPhoneTF);
		joinPhoneTF.setColumns(10);
		joinPhoneTF.setEnabled(false);
		joinPhoneTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= 11) {
					e.consume();
				}
			}
		}); // joinPhoneTF 입력 글자수 제한

		joinBirthdayTF = new JTextField();
		joinBirthdayTF.setBounds(607, 324, 116, 24);
		this.add(joinBirthdayTF);
		joinBirthdayTF.setColumns(10);
		joinBirthdayTF.setEnabled(false);
		joinBirthdayTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				JTextField src = (JTextField) e.getSource();
				if (src.getText().length() >= 8) {
					e.consume();
				}
			}
		}); // joinBirthdayTF 입력 글자수 제한

		joinAddressTF = new JTextField();
		joinAddressTF.setBounds(607, 426, 368, 24);
		this.add(joinAddressTF);
		joinAddressTF.setColumns(10);
		joinAddressTF.setEnabled(false);

		joinManRadioBtn = new JRadioButton("남자");
		joinManRadioBtn.setEnabled(false);
		joinManRadioBtn.setSelected(true);
		joinManRadioBtn.setBounds(607, 377, 91, 27);
		joinManRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(joinManRadioBtn.isSelected()){
					joinGirlRadioBtn.setSelected(false);
				}else{
					joinGirlRadioBtn.setSelected(true);
				}
			}
		});
		this.add(joinManRadioBtn);

		joinGirlRadioBtn = new JRadioButton("여자");
		joinGirlRadioBtn.setEnabled(false);
		joinGirlRadioBtn.setBounds(701, 377, 139, 27);
		joinGirlRadioBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(joinGirlRadioBtn.isSelected()){
					joinManRadioBtn.setSelected(false);
				}else{
					joinManRadioBtn.setSelected(true);
				}
			}
		});
		this.add(joinGirlRadioBtn);

		joinDuplicationCheckBtn = new JButton("아이디 중복확인");
		joinDuplicationCheckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempId = joinIdTF.getText().trim();
				if (tempId.length() == 0) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
					return;
				}
				if (member.idDuplicationCheck(userList, tempId)) {
					// 아이디 중복
					JOptionPane.showMessageDialog(null, tempId + " 아이디가 이미 사용중입니다.");
				} else {
					// 아이디 사용 가능
					JOptionPane.showMessageDialog(null, tempId + " 아이디는 사용 가능합니다.");
					joinDuplicationCheckBtn.setEnabled(false);
//					joinIdTF.setEnabled(false);
					joinPasswordTF.setEnabled(true);
					joinPasswordCheckTF.setEnabled(true);
					joinNameTF.setEnabled(true);
					joinPhoneTF.setEnabled(true);
					joinBirthdayTF.setEnabled(true);
					joinManRadioBtn.setEnabled(true);
					joinGirlRadioBtn.setEnabled(true);
					joinAddressTF.setEnabled(true);
					new Thread(){
						@Override
						public void run(){
							System.out.println("<<JoinPanel>> 회원가입 조건검사 쓰레드 실행");
							while(threadCheck){
								int check=0;
								String tempIdGetIdTF=tempId;
								if(!tempIdGetIdTF.equals(joinIdTF.getText().trim())){
									joinDuplicationCheckBtn.setEnabled(true);
								}
								if(!joinDuplicationCheckBtn.isEnabled()){
									check++;
								}
								String tempPass = joinPasswordTF.getText();
								if(tempPass.length()<6 || tempPass.trim().length()==0){
									joinPasswordTF.setForeground(Color.RED);
								}else{
									joinPasswordTF.setForeground(Color.BLACK);
									check++;
								}							
								String tempPassCheck = joinPasswordCheckTF.getText();
								if(tempPassCheck.equals(tempPass) && tempPassCheck.trim().length()!=0){
									joinPasswordCheckTF.setForeground(Color.BLACK);
									check++;
								}else{
									joinPasswordCheckTF.setForeground(Color.RED);
								}
								String tempName = joinNameTF.getText();
								if(tempName.trim().length()!=0){
									check++;
								}
								String tempPhone = joinPhoneTF.getText();
								if(tempPhone.length()==11 && !member.phoneDuplicationCheck(userList, tempPhone) && tempPhone.trim().length()!=0
										&& (tempPhone.substring(0, 3).equals("010")|| tempPhone.substring(0, 3).equals("011")
												|| tempPhone.substring(0, 3).equals("017")|| tempPhone.substring(0, 3).equals("016")
												|| tempPhone.substring(0, 3).equals("018")|| tempPhone.substring(0, 3).equals("019"))){
									joinPhoneTF.setForeground(Color.BLACK);
									check++;
								}else{
									joinPhoneTF.setForeground(Color.RED);
								}
								String tempBirthday = joinBirthdayTF.getText();
								if(tempBirthday.length()==8 && tempBirthday.trim().length()!=0){
									joinBirthdayTF.setForeground(Color.BLACK);
									check++;
								}else{
									joinBirthdayTF.setForeground(Color.RED);
								}
								String tempAddress = joinAddressTF.getText();
								if(tempAddress.trim().length()!=0){
									check++;
								}
								if(check==7){
									joinBtn.setEnabled(true);
								}else{
									joinBtn.setEnabled(false);
								}
							} // while
							if(!threadCheck){
								System.out.println("<<JoinPanel>> 회원가입 조건검사 쓰레드 종료");
							}
						} // run 메소드
					}.start(); // 회원가입 조건 검사 쓰레드
				}
			}
		});
		joinDuplicationCheckBtn.setBounds(815, 50, 160, 27);
		this.add(joinDuplicationCheckBtn);
		
		cancelBtn = new JButton("뒤로가기");
		cancelBtn.setBounds(700, 532, 105, 27);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayoutPanel.setVisible(false);
				joinIdTF.setEnabled(true);
				joinPasswordTF.setEnabled(false);
				joinPasswordCheckTF.setEnabled(false);
				joinNameTF.setEnabled(false);
				joinPhoneTF.setEnabled(false);
				joinBirthdayTF.setEnabled(false);
				joinManRadioBtn.setEnabled(false);
				joinGirlRadioBtn.setEnabled(false);
				joinAddressTF.setEnabled(false);
				joinDuplicationCheckBtn.setEnabled(true);
				joinIdTF.setText("");
				joinPasswordTF.setText("");
				joinPasswordCheckTF.setText("");
				joinNameTF.setText("");
				joinPhoneTF.setText("");
				joinBirthdayTF.setText("");
				joinAddressTF.setText("");
			}
		});
		this.add(cancelBtn);
		
		JLabel lblNewLabel_5 = new JLabel("회원가입");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 35, 144, 18);
		add(lblNewLabel_5);
	}

}

