package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import user.Admin;
import user.User;

public class AdminMemberDetailPanel extends JPanel {
	private JLabel label;
	private JTextField inputUserNoTF;
	private JTextArea memberInfoTA;
	private JButton dealPossibleBtn;
	private JButton cancelBtn;
	private JButton okBtn;
	
	private String checkingMemberNo;

	/**
	 * Create the panel.
	 */
	public AdminMemberDetailPanel(ArrayList<User> userList, JPanel cardLayoutPanel) {
		
		Admin ad = new Admin();
		
		this.setBounds(668, 61, 506, 496);
		this.setLayout(null);
		
		label = new JLabel("\uD655\uC778\uD558\uC2E4 \uBA64\uBC84 \uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694");
		label.setBounds(27, 35, 259, 18);
		this.add(label);
		
		inputUserNoTF = new JTextField();
		inputUserNoTF.setBounds(274, 32, 105, 24);
		this.add(inputUserNoTF);
		inputUserNoTF.setColumns(10);
		
		memberInfoTA = new JTextArea();
		memberInfoTA.setWrapStyleWord(true);
		memberInfoTA.setLineWrap(true);
		memberInfoTA.setEditable(false);
		memberInfoTA.setBounds(27, 95, 452, 326);
//		this.add(memberInfoTA);
		
		JScrollPane scrollPane = new JScrollPane(memberInfoTA);
		scrollPane.setBounds(27, 95, 452, 326);
		add(scrollPane);
		
		dealPossibleBtn = new JButton("\uAC70\uB798\uC815\uC9C0 / \uAC70\uB798\uC815\uC9C0 \uD574\uC81C");
		dealPossibleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ad.dealPossibleCheck(userList, checkingMemberNo, memberInfoTA);
			}
		});
		dealPossibleBtn.setBounds(140, 457, 209, 27);
		this.add(dealPossibleBtn);
		
		cancelBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutPanel.setVisible(false);
				inputUserNoTF.setText("");
				memberInfoTA.setText("");
			}
		});
		cancelBtn.setBounds(374, 457, 105, 27);
		this.add(cancelBtn);
		
		okBtn = new JButton("\uD655\uC778");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkingMemberNo = inputUserNoTF.getText().trim();
				ad.showMemberDetailGUI(userList, checkingMemberNo, memberInfoTA);
			}
		});
		okBtn.setBounds(404, 31, 75, 27);
		this.add(okBtn);
	} // 멤버 상세보기 패널 생성자

} // 멤버 상세보기 패널 클래스
