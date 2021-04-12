package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import board.Board;
import board.SaleBoard;
import etc.Book;
import etc.Deal;
import user.Admin;
import user.Member;
import user.User;

public class InputPasswordForBuyInWishListDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
//	public JTextField tempPassTF;
	public JPasswordField tempPassTF;
	
	public String inputPassword;
	public JButton okButton;
	public JButton cancelButton;
	
	/**
	 * Create the dialog.
	 */
	public InputPasswordForBuyInWishListDialog(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, ArrayList<Deal> dealList, 
			String inputSaleBoardNo, WishListPanel wishListPanel, WishListDetailPanel wishListDetailPanel,AdminDealListPanel adminDealListPanel) {
		setTitle("비밀번호 입력");
		setBounds(100, 100, 450, 201);
		setVisible(true);
		setLocationRelativeTo(null); // 가운데 창이 나타나도록
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("비밀번호");
			lblNewLabel.setBounds(75, 62, 110, 18);
			contentPanel.add(lblNewLabel);
		}
		{
//			tempPassTF = new JTextField();
			tempPassTF = new JPasswordField();
			tempPassTF.setBounds(207, 59, 116, 24);
			contentPanel.add(tempPassTF);
			tempPassTF.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Admin ad = new Admin();
						Member loginM = ad.findMemberByUserNo(userList, MainFrame.LOGIN_STATE[1]);
						if(tempPassTF.getText().trim().equals(loginM.password)){
							SaleBoard tempSB = (SaleBoard)ad.findBoardByBoardNo(boardList, Integer.parseInt(inputSaleBoardNo));
							Book tempB = tempSB.saleBook;
							Member sellM = (Member)tempSB.boardWriteUser;
							loginM.buyBookInWishListGUI(userList, boardList, bookList, dealList, sellM, tempSB, tempB, wishListPanel,adminDealListPanel);
							wishListDetailPanel.saleBoardInfoTA.setText("");
							wishListDetailPanel.inputBoardNoTF.setText("");
							dispose();
						}else{
							JOptionPane.showMessageDialog(null,"비밀번호가 다릅니다.");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						wishListDetailPanel.buyBtn.setEnabled(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	} // 생성자

	

} // 클래스
