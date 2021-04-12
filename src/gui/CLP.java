package gui;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import board.Board;
import etc.Book;
import etc.Deal;
import system.Data;
import user.User;

public class CLP extends JPanel {

	
	CardLayout cL;
	
	FindIdPassPanel findIdPassPanel;
	SaleBoardListPanel saleBoardListPanel;
	AdminMemberListPanel adminMemberListPanel;
	AdminDealListPanel adminDealListPanel;
	RegisterBookPanel memberRegisterBookPanel;
	JoinPanel joinPanel;
	WishListPanel wishListPanel;
	MyBuyListPanel myBuyListPanel;
	MySellListPanel mySellListPanel;
	MySellingListPanel mySellingListPanel;
	MyInfoPanel myInfoPanel;
	EventPanel eventPanel;
	MessagePanel messagePanel;
	
	/**
	 * Create the panel.
	 */
	public CLP(Data data, ArrayList<User> userList, ArrayList<Board> boardList,
			ArrayList<Book> bookList, ArrayList<Deal> dealList) {
		
		this.setBounds(14, 60, 1246, 613);
		cL = new CardLayout(0,0);
		this.setLayout(cL);
		this.setVisible(false);
		
		messagePanel = new MessagePanel(data,this);
		this.add(messagePanel,"message");
		
		myInfoPanel = new MyInfoPanel(userList, this);
		this.add(myInfoPanel,"myInfo");
		
		eventPanel = new EventPanel(data,this,myInfoPanel);
		this.add(eventPanel,"event");
		
		findIdPassPanel = new FindIdPassPanel(userList, this); // ���̵� ��й�ȣ ã�� �г� 
		this.add(findIdPassPanel, "findIdPass");
		
		adminMemberListPanel = new AdminMemberListPanel(userList, this); // ������ �������Ʈ �г�
		this.add(adminMemberListPanel,"adminMemberList");
		
		myBuyListPanel = new MyBuyListPanel(data,userList, boardList, bookList, dealList, this); // ���Ÿ�� �г�
		this.add(myBuyListPanel,"myBuyList");
		
		mySellListPanel = new MySellListPanel(data, userList, boardList, bookList, dealList, this); // �ǸŸ�� �г�
		this.add(mySellListPanel,"mySellList");
		
		mySellingListPanel = new MySellingListPanel(userList, boardList, bookList, dealList, this); // �Ǹ����� ��ǰ ��� �г�
		this.add(mySellingListPanel,"mySellingList");
		
		adminDealListPanel = new AdminDealListPanel(userList, boardList, bookList, dealList, this); // ������ �ŷ�����Ʈ �г� 
		this.add(adminDealListPanel, "adminDealList");
		
		joinPanel = new JoinPanel(userList, this, adminMemberListPanel); // ȸ������ �г�
		this.add(joinPanel, "join");
		
		wishListPanel = new WishListPanel(userList, boardList, bookList, dealList, this, wishListPanel,adminDealListPanel); // ���ø���Ʈ �г�
		this.add(wishListPanel,"wishList");
		
		saleBoardListPanel = new SaleBoardListPanel(userList, boardList, bookList, dealList, this, wishListPanel,adminDealListPanel); // ��ǰ��� �г�
		this.add(saleBoardListPanel,"saleBoardList");
		
		memberRegisterBookPanel = new RegisterBookPanel(userList, boardList, bookList, this,saleBoardListPanel,mySellingListPanel); // ��ǰ��� �г�
		this.add(memberRegisterBookPanel, "registerBook");
		
		
	} // ī�巹�̾ƿ��г� ������

} // ī�巹�̾ƿ��г� Ŭ����
