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
		
		findIdPassPanel = new FindIdPassPanel(userList, this); // 아이디 비밀번호 찾기 패널 
		this.add(findIdPassPanel, "findIdPass");
		
		adminMemberListPanel = new AdminMemberListPanel(userList, this); // 관리자 멤버리스트 패널
		this.add(adminMemberListPanel,"adminMemberList");
		
		myBuyListPanel = new MyBuyListPanel(data,userList, boardList, bookList, dealList, this); // 구매목록 패널
		this.add(myBuyListPanel,"myBuyList");
		
		mySellListPanel = new MySellListPanel(data, userList, boardList, bookList, dealList, this); // 판매목록 패널
		this.add(mySellListPanel,"mySellList");
		
		mySellingListPanel = new MySellingListPanel(userList, boardList, bookList, dealList, this); // 판매중인 상품 목록 패널
		this.add(mySellingListPanel,"mySellingList");
		
		adminDealListPanel = new AdminDealListPanel(userList, boardList, bookList, dealList, this); // 관리자 거래리스트 패널 
		this.add(adminDealListPanel, "adminDealList");
		
		joinPanel = new JoinPanel(userList, this, adminMemberListPanel); // 회원가입 패널
		this.add(joinPanel, "join");
		
		wishListPanel = new WishListPanel(userList, boardList, bookList, dealList, this, wishListPanel,adminDealListPanel); // 위시리스트 패널
		this.add(wishListPanel,"wishList");
		
		saleBoardListPanel = new SaleBoardListPanel(userList, boardList, bookList, dealList, this, wishListPanel,adminDealListPanel); // 상품목록 패널
		this.add(saleBoardListPanel,"saleBoardList");
		
		memberRegisterBookPanel = new RegisterBookPanel(userList, boardList, bookList, this,saleBoardListPanel,mySellingListPanel); // 상품등록 패널
		this.add(memberRegisterBookPanel, "registerBook");
		
		
	} // 카드레이아웃패널 생성자

} // 카드레이아웃패널 클래스
