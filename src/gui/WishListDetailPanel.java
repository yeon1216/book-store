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

import board.Board;
import board.SaleBoard;
import etc.Book;
import etc.Deal;
import user.Admin;
import user.Member;
import user.User;

public class WishListDetailPanel extends JPanel {
	private JTextField textField;
	
	String inputSaleBoardNo;
	public JButton buyBtn;
	WishListDetailPanel wishListDetailPanel;
	public JTextArea saleBoardInfoTA;
	public JTextField inputBoardNoTF;
	/**
	 * Create the panel.
	 */
	public WishListDetailPanel(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, ArrayList<Deal> dealList, 
			JPanel cardLayoutPanel, WishListPanel wishListPanel, AdminDealListPanel adminDealListPanel) {
		
		wishListDetailPanel = this;
		
		Member member = new Member();
		
		wishListDetailPanel.setBounds(763, 61, 469, 540);
		setLayout(null);
		
		JLabel label = new JLabel("\uAC8C\uC2DC\uAE00 \uBC88\uD638");
		label.setBounds(0, 47, 124, 18);
		this.add(label);
		
		inputBoardNoTF = new JTextField();
		inputBoardNoTF.setBounds(155, 44, 105, 24);
		this.add(inputBoardNoTF);
		inputBoardNoTF.setColumns(10);
		
		saleBoardInfoTA = new JTextArea();
		saleBoardInfoTA.setWrapStyleWord(true);
		saleBoardInfoTA.setLineWrap(true);
		saleBoardInfoTA.setEditable(false);
		saleBoardInfoTA.setBounds(0, 94, 416, 379);
//		this.add(saleBoardInfoTA);
		
		JScrollPane scrollPane = new JScrollPane(saleBoardInfoTA);
		scrollPane.setBounds(0, 94, 416, 379);
		add(scrollPane);
		
		JButton okBtn = new JButton("\uD655\uC778");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inputSaleBoardNo = inputBoardNoTF.getText().trim();
				boolean isSaleBoard = member.showSaleBoardDetailInWishListGUI(userList, boardList, inputSaleBoardNo,saleBoardInfoTA);
				if(isSaleBoard){
					buyBtn.setEnabled(true);
				}else{
					buyBtn.setEnabled(false);
					saleBoardInfoTA.setText("");
				}
			}
		});
		okBtn.setBounds(327, 43, 75, 27);
		this.add(okBtn);
		
		
		
		buyBtn = new JButton("\uC0C1\uD488\uAD6C\uB9E4");
		buyBtn.setEnabled(false);
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyBtn.setEnabled(false);
				new InputPasswordForBuyInWishListDialog(userList,boardList,bookList,dealList,inputSaleBoardNo,wishListPanel,wishListDetailPanel,adminDealListPanel);
			}
		});
		buyBtn.setBounds(10, 485, 89, 27);
		this.add(buyBtn);
		
		JButton cancelBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutPanel.setVisible(false);
				inputBoardNoTF.setText("");
				saleBoardInfoTA.setText("");
				buyBtn.setEnabled(false);
			}
		});
		cancelBtn.setBounds(297, 485, 105, 27);
		this.add(cancelBtn);
	} // 패널 생성자 


} // 패널 클래스
