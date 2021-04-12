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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.Board;
import board.SaleBoard;
import etc.Book;
import user.Admin;
import user.Member;
import user.User;
import javax.swing.JScrollPane;

public class RegisterBookPanel extends JPanel {
	public JTextField bookNameTF;
	public JTextField authorTF;
	public JTextField genreTF;
	public JTextField priceTF;
	public JButton cancelBtn;
	public JButton bookRegisterBtn;
	public JComboBox bookStateComboBox;
	public JTextArea introduceBookTA;
	
	

	/**
	 * Create the panel.
	 */
	public RegisterBookPanel(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, JPanel cardLayoutPanel,
			SaleBoardListPanel saleBoardListPanel, MySellingListPanel mySellingListPanel) {
		this.setBounds(14, 60, 1246, 613);
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uCC45 \uC774\uB984");
		lblNewLabel.setBounds(231, 129, 62, 18);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC9C0\uC740\uC774");
		lblNewLabel_1.setBounds(231, 186, 62, 18);
		add(lblNewLabel_1);
		
		bookNameTF = new JTextField();
		bookNameTF.setBounds(385, 126, 195, 24);
		add(bookNameTF);
		bookNameTF.setColumns(10);
		
		authorTF = new JTextField();
		authorTF.setBounds(385, 183, 195, 24);
		add(authorTF);
		authorTF.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\uC7A5\uB974");
		lblNewLabel_2.setBounds(231, 248, 62, 18);
		add(lblNewLabel_2);
		
		genreTF = new JTextField();
		genreTF.setBounds(385, 245, 195, 24);
		add(genreTF);
		genreTF.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\uAC00\uACA9");
		lblNewLabel_3.setBounds(231, 314, 62, 18);
		add(lblNewLabel_3);
		
		JLabel label = new JLabel("\uCC45\uC758 \uC0C1\uD0DC");
		label.setBounds(231, 383, 62, 18);
		add(label);
		
		priceTF = new JTextField();
		priceTF.setBounds(385, 311, 195, 24);
		add(priceTF);
		priceTF.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\uCC45 \uC18C\uAC1C");
		lblNewLabel_4.setBounds(231, 458, 62, 18);
		add(lblNewLabel_4);
		
		bookStateComboBox = new JComboBox();
		bookStateComboBox.setModel(new DefaultComboBoxModel(new String[] {"1. \uAC70\uC758 \uC0C8\uCC45", "2. \uCC45\uC774 \uC811\uD600\uC788\uAC70\uB098 \uC904\uC774 \uADF8\uC5B4\uC9C4 \uACBD\uC6B0", "3. \uCC45\uC758 \uB0B4\uC6A9\uC774 \uC870\uAE08 \uCC22\uC5B4\uC9C4 \uACBD\uC6B0"}));
		bookStateComboBox.setBounds(385, 380, 272, 24);
		add(bookStateComboBox);
		
		bookRegisterBtn = new JButton("상품등록");
		bookRegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bookName;
				String author;
				String genre;
				int price;
				int bookState = 0;
				String content;
				bookName = bookNameTF.getText();
				author = authorTF.getText();
				genre = genreTF.getText();
				price = Integer.parseInt(priceTF.getText());
				if(bookStateComboBox.getSelectedItem().equals("1. \uAC70\uC758 \uC0C8\uCC45")){
					bookState = 1;
				}else if(bookStateComboBox.getSelectedItem().equals("2. \uCC45\uC774 \uC811\uD600\uC788\uAC70\uB098 \uC904\uC774 \uADF8\uC5B4\uC9C4 \uACBD\uC6B0")){
					bookState = 2;
				}else if(bookStateComboBox.getSelectedItem().equals("3. \uCC45\uC758 \uB0B4\uC6A9\uC774 \uC870\uAE08 \uCC22\uC5B4\uC9C4 \uACBD\uC6B0")){
					bookState = 3;
				}
				content = introduceBookTA.getText();
				Book tempB = new Book(bookList.size(),bookName,author,genre,price,bookState);
				Admin ad = new Admin();
				Member loginM = ad.findMemberByUserNo(userList, MainFrame.LOGIN_STATE[1]);
				SaleBoard tempSB = new SaleBoard(boardList.size(),content,loginM,tempB);
				loginM.registerBookByGUI(userList, boardList, bookList, tempSB, saleBoardListPanel,mySellingListPanel);
				cardLayoutPanel.setVisible(false);
				
				bookNameTF.setText("");
				authorTF.setText("");
				genreTF.setText("");
				priceTF.setText("");
				introduceBookTA.setText("");
			}
		});
		bookRegisterBtn.setBounds(1001, 454, 105, 27);
		add(bookRegisterBtn);
		
		cancelBtn = new JButton("뒤로가기");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutPanel.setVisible(false);
				bookNameTF.setText("");
				authorTF.setText("");
				genreTF.setText("");
				priceTF.setText("");
				introduceBookTA.setText("");
			}
		});
		cancelBtn.setBounds(1001, 500, 105, 27);
		add(cancelBtn);
		
		JLabel lblNewLabel_5 = new JLabel("상품등록");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 35, 144, 18);
		add(lblNewLabel_5);
		
		introduceBookTA = new JTextArea();
		introduceBookTA.setWrapStyleWord(true);
		introduceBookTA.setLineWrap(true);
		introduceBookTA.setBounds(385, 462, 437, 69);
		
		JScrollPane scrollPane = new JScrollPane(introduceBookTA);
		scrollPane.setBounds(385, 462, 399, 97);
		add(scrollPane);
		
	}
}
