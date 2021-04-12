package gui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import board.Board;
import etc.Book;
import etc.Deal;
import user.Member;
import user.User;

public class SaleBoardListPanel extends JPanel {
	
	public SaleBoardDetailPanel saleBoardDetailPanel;
	
	public JTable table;
	/**
	 * Create the panel.
	 */
	public SaleBoardListPanel(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, ArrayList<Deal> dealList, 
			JPanel cardLayoutPanel, WishListPanel wishListPanel,AdminDealListPanel adminDealListPanel) {
		
		this.setBounds(14, 60, 1246, 613);
		setLayout(null);
		Member member = new Member();
		
		// �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�  ���̺� ����  �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ� //
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 63, 729, 511);
		add(scrollPane);
		table = new JTable();
		table.setEnabled(false);
		try {
			member.showSaleBoardListByGUI(userList, boardList,bookList, table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] <<SaleBoardListPanel>> showSaleBoardListByGUI �޼ҵ� : "+e.getMessage());
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		// �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�  ���̺� ����  �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ� //
		
		saleBoardDetailPanel = new SaleBoardDetailPanel(userList, boardList,bookList, dealList, cardLayoutPanel,this,wishListPanel,adminDealListPanel);
		saleBoardDetailPanel.setBounds(763, 61, 469, 540);
		add(saleBoardDetailPanel);
		
		JLabel lblNewLabel_5 = new JLabel("��ǰ���");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 20, 250, 30);
		add(lblNewLabel_5);
	} // �Ǹ� �Խñ� ����Ʈ �г� ������
	
	
} // �Ǹ� �Խñ� ����Ʈ �г� Ŭ����

