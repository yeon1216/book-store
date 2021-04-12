package gui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import board.Board;
import etc.Book;
import etc.Deal;
import user.Admin;
import user.Member;
import user.User;

public class AdminDealListPanel extends JPanel {
	public JTable table;
	JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public AdminDealListPanel(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, ArrayList<Deal> dealList, JPanel cardLayoutPanel) {
		this.setBounds(14, 60, 1246, 613);
		this.setLayout(null);
		
		// �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�  ���̺� ����  �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ� //
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 61, 1173, 496);
		add(scrollPane);
		table = new JTable();
		table.setEnabled(false);
		Admin ad = new Admin();
		ad.showDealListByGUI(dealList, table);
		scrollPane.setViewportView(table);
		// �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�  ���̺� ����  �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ� //
		
		JLabel lblNewLabel_5 = new JLabel("�ŷ����");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 20, 250, 30);
		add(lblNewLabel_5);
	} // ������ �ŷ�����Ʈ �г� ������

} // ������ �ŷ�����Ʈ �г� Ŭ����
