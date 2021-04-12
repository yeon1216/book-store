package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import board.Board;
import etc.Book;
import user.Admin;
import user.Member;
import user.User;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AdminMemberListPanel extends JPanel {
	public JTable table;
	
	/**
	 * Create the panel.
	 */
	public AdminMemberListPanel(ArrayList<User> userList, JPanel cardLayoutPanel) {
		this.setBounds(14, 60, 1246, 613);
		setLayout(null);
		Admin ad = new Admin();
		
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  테이블 시작  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(103, 61, 468, 496);
		add(scrollPane);
		table = new JTable();
		table.setEnabled(false);
		ad.showMemberListByGUI(userList, table);
		scrollPane.setViewportView(table);
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  테이블 종료  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		
		AdminMemberDetailPanel adminMemberDetailPanel = new AdminMemberDetailPanel(userList, cardLayoutPanel);
		adminMemberDetailPanel.setBounds(668, 61, 506, 496);
		add(adminMemberDetailPanel);
		
		JLabel lblNewLabel_5 = new JLabel("회원목록");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 20, 250, 30);
		add(lblNewLabel_5);
		
		
	} // 패널 생성자
} // 클래스
