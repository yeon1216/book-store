package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import user.Member;
import user.User;

public class MyInfoPanel extends JPanel {
	public JPanel memberInfoPanel;
	public JTextArea memberInfoTA;
	public JTextField chargeCoinTF;
	public JTextField refundCoinTF;

	/**
	 * Create the panel.
	 */
	public MyInfoPanel(ArrayList<User> userList, JPanel cardLayoutPanel) {
		
		Member member = new Member();
		
		this.setBounds(14, 60, 1246, 613);
		this.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("나의 정보");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 20, 250, 30);
		add(lblNewLabel_5);
		
		memberInfoPanel = new JPanel();
		memberInfoPanel.setBounds(178, 88, 816, 474);
		add(memberInfoPanel);
		memberInfoPanel.setLayout(null);
		
		memberInfoTA = new JTextArea();
		memberInfoTA.setBounds(68, 32, 678, 348);
		memberInfoPanel.add(memberInfoTA);
		
		JButton chargeCoinBtn = new JButton("\uCF54\uC778\uCDA9\uC804");
		chargeCoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Member loginM = member.findLoginMember(userList);
				member.chargeCoin(userList, loginM,chargeCoinTF.getText().trim(),memberInfoTA);
				chargeCoinTF.setText("");
			}
		});
		chargeCoinBtn.setBounds(522, 392, 105, 27);
		memberInfoPanel.add(chargeCoinBtn);
		
		JButton refundCoinBtn = new JButton("\uCF54\uC778\uD658\uBD88");
		refundCoinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member loginM = member.findLoginMember(userList);
				member.refundCoin(userList, loginM, refundCoinTF.getText().trim(),memberInfoTA);
				refundCoinTF.setText("");
			}
		});
		refundCoinBtn.setBounds(522, 431, 105, 27);
		memberInfoPanel.add(refundCoinBtn);
		
		JButton cancelBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chargeCoinTF.setText("");
				refundCoinTF.setText("");
				cardLayoutPanel.setVisible(false);
			}
		});
		cancelBtn.setBounds(641, 431, 105, 27);
		memberInfoPanel.add(cancelBtn);
		
		chargeCoinTF = new JTextField();
		chargeCoinTF.setBounds(381, 392, 116, 24);
		memberInfoPanel.add(chargeCoinTF);
		chargeCoinTF.setColumns(10);
		
		refundCoinTF = new JTextField();
		refundCoinTF.setBounds(381, 432, 116, 24);
		memberInfoPanel.add(refundCoinTF);
		refundCoinTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\uCDA9\uC804 \uD560 \uCF54\uC778 \uC785\uB825");
		lblNewLabel.setBounds(227, 392, 127, 18);
		memberInfoPanel.add(lblNewLabel);
		
		JLabel label = new JLabel("\uD658\uBD88 \uD560 \uCF54\uC778 \uC785\uB825");
		label.setBounds(227, 435, 127, 18);
		memberInfoPanel.add(label);
	} // 패널 생성자
} // 패널 클래스
