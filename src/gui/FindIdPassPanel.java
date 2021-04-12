package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import user.Member;
import user.User;

public class FindIdPassPanel extends JPanel {
	public JTextField findIdPassPhoneTF;
	public JTextField findIdPassNameTF;
	public JLabel phoneLabel;
	public JLabel nameLabel;
	public JButton findBtn;
	
	public FindIdPassPanel() {
	}

	/**
	 * Create the panel.
	 */
	public FindIdPassPanel(ArrayList<User> userList, JPanel cardLayoutPanel) {
		
		Member member = new Member();
		
		this.setBounds(14, 60, 1246, 613);
		setLayout(null);
		
		findIdPassPhoneTF = new JTextField();
		findIdPassPhoneTF.setBounds(490, 204, 116, 24);
		this.add(findIdPassPhoneTF);
		findIdPassPhoneTF.setColumns(10);
		
		findIdPassNameTF = new JTextField();
		findIdPassNameTF.setBounds(490, 164, 116, 24);
		this.add(findIdPassNameTF);
		findIdPassNameTF.setColumns(10);
		
		phoneLabel = new JLabel("전화번호");
		phoneLabel.setBounds(381, 204, 62, 18);
		this.add(phoneLabel);
		
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(381, 170, 62, 18);
		this.add(nameLabel);
		
		
		
		findBtn = new JButton("\uCC3E\uAE30");
		findBtn.setBounds(653, 161, 105, 73);
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member findMember = member.findIdPassword(userList, findIdPassNameTF.getText(), findIdPassPhoneTF.getText());
				if(findMember==null){
					JOptionPane.showMessageDialog(null,"이름과 번호가 등록되어있지 않습니다.");
				}else{
					JOptionPane.showMessageDialog(null,findIdPassNameTF.getText()+"님의 \n아이디 : "+findMember.id+"\n비밀번호 : "+findMember.password);
					findIdPassNameTF.setText("");
					findIdPassPhoneTF.setText("");
				}
			}
		});
		this.add(findBtn);
		
		JButton cancelBtn = new JButton("뒤로가기");
		cancelBtn.setBounds(700, 532, 105, 27);
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayoutPanel.setVisible(false);
				findIdPassNameTF.setText("");
				findIdPassPhoneTF.setText("");
			}
		});
		this.add(cancelBtn);
		
		JLabel lblNewLabel_5 = new JLabel("아이디/비밀번호 찾기");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 35, 250, 30);
		add(lblNewLabel_5);
	}

}
