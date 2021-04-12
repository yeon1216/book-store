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
		
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  테이블 시작  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 63, 729, 511);
		add(scrollPane);
		table = new JTable();
		table.setEnabled(false);
		try {
			member.showSaleBoardListByGUI(userList, boardList,bookList, table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] <<SaleBoardListPanel>> showSaleBoardListByGUI 메소드 : "+e.getMessage());
			e.printStackTrace();
		}
		scrollPane.setViewportView(table);
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  테이블 종료  ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ //
		
		saleBoardDetailPanel = new SaleBoardDetailPanel(userList, boardList,bookList, dealList, cardLayoutPanel,this,wishListPanel,adminDealListPanel);
		saleBoardDetailPanel.setBounds(763, 61, 469, 540);
		add(saleBoardDetailPanel);
		
		JLabel lblNewLabel_5 = new JLabel("상품목록");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 20, 250, 30);
		add(lblNewLabel_5);
	} // 판매 게시글 리스트 패널 생성자
	
	
} // 판매 게시글 리스트 패널 클래스

