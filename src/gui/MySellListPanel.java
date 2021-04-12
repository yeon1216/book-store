package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import board.Board;
import etc.Book;
import etc.Deal;
import system.Data;
import user.Member;
import user.User;

public class MySellListPanel extends JPanel {

	public JTable table;
	/**
	 * Create the panel.
	 */
	public MySellListPanel(Data data, ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, ArrayList<Deal> dealList, JPanel cardLayoutPanel) {
		this.setBounds(14, 60, 1246, 613);
		setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("판매목록");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 20, 250, 30);
		add(lblNewLabel_5);
		
		Member member = new Member();
		
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  테이블 시작  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 63, 729, 511);
		add(scrollPane);
		table = new JTable();
		table.setEnabled(false);
		member.showSellListGUI(userList, table);
		scrollPane.setViewportView(table);
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  테이블 종료  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		
		JLabel lblNewLabel = new JLabel("\uAC70\uB798 \uD3C9\uAC00\uD558\uAE30");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(817, 299, 141, 18);
		add(lblNewLabel);
		
		JLabel label = new JLabel("\uAC70\uB798\uBC88\uD638");
		label.setBounds(817, 357, 94, 18);
		add(label);
		
		JTextField inputDealNoTF = new JTextField();
		inputDealNoTF.setBounds(906, 354, 116, 24);
		add(inputDealNoTF);
		inputDealNoTF.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"20", "40", "60", "80", "100"}));
		comboBox.setBounds(1043, 354, 74, 24);
		add(comboBox);
		
		JButton cancelBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutPanel.setVisible(false);
				inputDealNoTF.setText("");
			}
		});
		cancelBtn.setBounds(1012, 547, 105, 27);
		add(cancelBtn);
		
		// 구매자 평가 버튼
		JButton evaluateBtn = new JButton("\uD655\uC778");
		evaluateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isSuccess = member.evaluateBuyMember(data,inputDealNoTF.getText().trim(),String.valueOf(comboBox.getSelectedItem()));
				if(isSuccess){
					inputDealNoTF.setText("");
					member.showSellListGUI(userList, table);
				}
			}
		});
		evaluateBtn.setBounds(1043, 425, 74, 27);
		add(evaluateBtn);
		
	} // 판매리스트 패널 생성자 

} // 판매리스트 패널 클래스
