package user;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import board.Board;
import board.SaleBoard;
import etc.Book;
import etc.Deal;
import gui.AdminDealListPanel;
import gui.AdminMemberListPanel;
import gui.MainFrame;
import gui.MySellingListPanel;
import gui.SaleBoardListPanel;
import gui.WishListPanel;
import javazoom.jl.player.Player;
import system.Data;
import system.Music;

public class Member extends User {

	public String name; // 이름
	public String phone; // 전화번호
	public String birthday; // 생년월일
	public String gender; // 성별
	public String address; // 주소
	public int grade; // 온도
	public int coin; // 코인
	public boolean isPossibleDeal; // 거래가능 여부
	public ArrayList<Deal> buyList; // 구매리스트
	public ArrayList<Deal> sellList; // 판매리스트
	public ArrayList<SaleBoard> wishList; // 위시리스트
	public boolean isParticipationAdversity; // 광고이벤트 참여여부
	public boolean isParticipationLotto; // 로또이벤트 참여여부
	public boolean isParticipationCardGame; // 카드게임이벤트 참여여부
	public int gradeCount; // 평가받은 횟수
	
	public void printConsolMember(ArrayList<User> userList){
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i) instanceof Member){
				Member m = (Member)userList.get(i);
				System.out.println("멤버번호 : "+ m.userNo+" / 아이디 : "+m.id+" / 온도 : "+m.grade);
			}
		}
		System.out.println();
	}

	// Member 기본 생성자
	public Member() {
	} // Member 기본 생성자

	// Member 생성자
	public Member(int userNo, String id, String password, String name, String phone, String birthday, String gender,
			String address) {
		super(userNo, id, password);
		this.name = name;
		this.phone = phone;
		this.birthday = birthday;
		this.gender = gender;
		this.address = address;
		this.grade = 0;
		this.coin = 0;
		this.isPossibleDeal = true;
		this.buyList = new ArrayList<>();
		this.sellList = new ArrayList<Deal>();
		this.wishList = new ArrayList<>();
		this.gradeCount=0;
	} // Member 생성자

	// 아이디, 비밀번호 찾기 메소드
	public Member findIdPassword(ArrayList<User> userList, String nameTF, String phoneTF) {
		Member findMember = null;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Member) {
				Member tempMember = (Member) userList.get(i);
				if (nameTF.equals(tempMember.name) && phoneTF.equals(tempMember.phone)) {
					findMember = tempMember;
				}
			}
		}
		return findMember;
	} // 아이디 비밀번호 찾기 메소드

	// 회원가입 메소드 (+ 회원가입 효과음 쓰레드 )
	public synchronized void join(ArrayList<User> userList, int userNo, String id, String password, String name,
			String phone, String birthday, String gender, String address, AdminMemberListPanel adminMemberListPanel) {
		Member joinMember = new Member(userNo, id, password, name, phone, birthday, gender, address);
		userList.add(joinMember);
		Admin ad = new Admin();
		// 회원가입시 회원리스트 테이블 동기화
		ad.showMemberListByGUI(userList, adminMemberListPanel.table);

		// 회원가입시 효과음 쓰레드
		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("<<Member>> 회원가입 효과음 쓰레드 실행");
					File file = new File(Music.class.getResource("..\\music\\join.mp3").toURI());
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					Player player = new Player(bis);
					player.play();
				} catch (Exception e2) {
				}
			}
		}.start();

	} // 회원가입 메소드

	// 아이디 중복 체크 메소드
	public boolean idDuplicationCheck(ArrayList<User> userList, String tempId) {
		boolean duplicationCheck = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).id.equals(tempId)) {
				duplicationCheck = true;
			}
		}
		return duplicationCheck;
	} // 아이디 중복 체크 메소드

	// 핸드폰 번호 중복 체크 메소드
	public synchronized boolean phoneDuplicationCheck(ArrayList<User> userList, String tempPhone) {
		boolean phoneDuplicationCheck = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Member) {
				Member tempMember = (Member) userList.get(i);
				if (tempMember.phone.equals(tempPhone)) {
					phoneDuplicationCheck = true;
				}
			}
		}
		return phoneDuplicationCheck;
	} // 핸드폰 번호 중복 체그 메소드

	// 로그인 멤버 찾기 메소드
	public Member findLoginMember(ArrayList<User> userList) {
		Admin ad = new Admin();
		Member loginM = ad.findMemberByUserNo(userList, MainFrame.LOGIN_STATE[1]);
		return loginM;
	} // 로그인 멤버 찾는 메소드

	// 상품목록 보기 메소드
	public synchronized void showSaleBoardListByGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			JTable table) throws Exception{
		try {
			Vector<String> headVector = new Vector<String>();
			headVector.add("게시글번호");
			headVector.add("책 제목");
			headVector.add("가격");
			headVector.add("판매자");
			headVector.add("작성시간");
			Vector<Vector> tableDataVector = new Vector<Vector>();
			for (int i = 0; i < boardList.size(); i++) {
				if (boardList.get(i) instanceof SaleBoard) {
					SaleBoard tempSB = (SaleBoard) boardList.get(i);
					if (!tempSB.isSale && tempSB.boardWriteUser.userNo != MainFrame.LOGIN_STATE[1]) {
						String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
						Vector<String> rowVector = new Vector<String>();
						rowVector.add(String.valueOf(tempSB.boardNo));
						rowVector.add(tempSB.saleBook.bookName);
						rowVector.add(String.valueOf(tempSB.saleBook.price) + "코인");
						Member sellM = null;
						if(tempSB.boardWriteUser instanceof Member){
							sellM = (Member)tempSB.boardWriteUser;
						}
						if(sellM.isPossibleDeal){
							rowVector.add(tempSB.boardWriteUser.id);
						}else{
							rowVector.add(tempSB.boardWriteUser.id+"  (거래정지)");
						}
						rowVector.add(saleBoardWriteTimeToString);
						tableDataVector.add(rowVector);
					}
				}
			}
			table.setModel(new DefaultTableModel(tableDataVector, headVector));
		} catch (Exception e) {
			System.out.println("[ERROR] showSaleBoardListByGUI 메소드 : "+e.getMessage());
		}

	}

	// 판매게시글 상세보기
	public boolean showSaleBoardDetailGUI(ArrayList<Board> boardList, String inputSaleBoardNo,
			JTextArea saleBoardInfoTA) {
		boolean isSaleBoard = false;
		Admin ad = new Admin();
		if (ad.findBoardByBoardNo(boardList, Integer.parseInt(inputSaleBoardNo)) instanceof SaleBoard) {
			SaleBoard tempSB = (SaleBoard) ad.findBoardByBoardNo(boardList, Integer.parseInt(inputSaleBoardNo));
			if (tempSB.boardWriteUser.userNo != MainFrame.LOGIN_STATE[1] && !tempSB.isSale) {
				String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
				String bookState = null;
				if (tempSB.saleBook.bookState == 1) {
					bookState = "거의 새책";
				} else if (tempSB.saleBook.bookState == 2) {
					bookState = "책이 접혀있거나 줄이 그어져있습니다.";
				} else if (tempSB.saleBook.bookState == 3) {
					bookState = "책이 조금 찢어진 부분이 있습니다.";
				}
				String content = tempSB.boardContent;
				String str = "  >> 게시글 번호 :  " + tempSB.boardNo + "\n  >> 판매자 :  " + tempSB.boardWriteUser.id
						+ "\n  >> 작성시간 :  " + saleBoardWriteTimeToString + "\n  >> 책 제목 :  " + tempSB.saleBook.bookName
						+ "\n  >> 지은이 :  " + tempSB.saleBook.author + "\n  >> 장르 :  " + tempSB.saleBook.genre
						+ "\n  >> 책 소개 :  " + content + "\n  >> 책의 상태 :  " + bookState + "\n  >> 가격 :  "
						+ tempSB.saleBook.price + "코인";
				isSaleBoard = true;
				saleBoardInfoTA.setText(str);
			}
		}
		return isSaleBoard;
	}

	// 위시리스트에서 상품 상세보기
	public boolean showSaleBoardDetailInWishListGUI(ArrayList<User> userList, ArrayList<Board> boardList,
			String inputSaleBoardNo, JTextArea saleBoardInfoTA) {
		boolean isSaleBoard = false;
		Admin ad = new Admin();
		Member loginM = findLoginMember(userList);
		if (ad.findBoardByBoardNo(boardList, Integer.parseInt(inputSaleBoardNo)) instanceof SaleBoard) {
			SaleBoard tempSB = (SaleBoard) ad.findBoardByBoardNo(boardList, Integer.parseInt(inputSaleBoardNo));
			for (int i = 0; i < loginM.wishList.size(); i++) {
				if (tempSB.boardNo == loginM.wishList.get(i).boardNo && !tempSB.isSale) {
					String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
					String bookState = null;
					if (tempSB.saleBook.bookState == 1) {
						bookState = "거의 새책";
					} else if (tempSB.saleBook.bookState == 2) {
						bookState = "책이 접혀있거나 줄이 그어져있습니다.";
					} else if (tempSB.saleBook.bookState == 3) {
						bookState = "책이 조금 찢어진 부분이 있습니다.";
					}
					String str = " 게시글 번호 :  " + tempSB.boardNo + "\n 판매자 :  " + tempSB.boardWriteUser.id
							+ "\n 작성시간 :  " + saleBoardWriteTimeToString + "\n 책 제목 :  " + tempSB.saleBook.bookName
							+ "\n 지은이 :  " + tempSB.saleBook.author + "\n 장르 :  " + tempSB.saleBook.genre
							+ "\n 책 소개 :  " + tempSB.boardContent + "\n 책의 상태 :  " + bookState + "\n 가격 :  "
							+ tempSB.saleBook.price + "코인";
					isSaleBoard = true;
					saleBoardInfoTA.setText(str);
				}
			}
		}
		return isSaleBoard;
	}

	// 상품등록 메소드
	public synchronized void registerBookByGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			SaleBoard tempSB, SaleBoardListPanel saleBoardListPanel, MySellingListPanel mySellingListPanel) {
		bookList.add(tempSB.saleBook);
		boardList.add(tempSB);
//		JOptionPane.showMessageDialog(null, "상품을 등록하였습니다.");
		try {
			showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] <<Member  상품등록 메소드>>showSaleBoardListByGUI 메소드 : "+e.getMessage());
			e.printStackTrace();
		}
		showMySellingListGUI(userList, boardList, mySellingListPanel.table);
		notify();
		// 상품등록시 효과음 쓰레드
		new Thread() {
			@Override
			public void run() {
				System.out.println("<<Memeber>> 상품등록 효과음 쓰레드 실행");
				try {
					File file = new File(Music.class.getResource("..\\music\\registerBook.mp3").toURI());
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					Player player = new Player(bis);
					player.play();
				} catch (Exception e2) {
				}
			}
		}.start();
	}

	// 자동상품구매 메소드
	public synchronized void autoBuyBookGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			ArrayList<Deal> dealList, Member sellM, Member buyM, SaleBoard tempSB, Book tempB,
			SaleBoardListPanel saleBoardListPanel, WishListPanel wishListPanel, AdminDealListPanel adminDealListPanel) {
			boolean check=false;
			if(boardList.size()==0){
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			sellM.coin += tempB.price;
			Deal tempDeal = new Deal(dealList.size(), tempSB, buyM, sellM);
			tempSB.isSale = true;
			sellM.sellList.add(tempDeal);
			buyM.buyList.add(tempDeal);
			dealList.add(tempDeal);
			// 상품구매 효과음 쓰레드
			playMusic("buyBook.mp3");
			// 상품목록 테이블 동기화
			try {
				showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[ERROR] <<Member 자동 상품구매 메소드>> showSaleBoardListByGUI 메소드 : "+e.getMessage());
				e.printStackTrace();
			}
			// 상품구매에 따른 위시리스트 동기화
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i) instanceof Member) {
					Member tempM = (Member) userList.get(i);
					for (int j = 0; j < tempM.wishList.size(); j++) {
						if (tempM.wishList.get(j).isSale) {
							tempM.wishList.remove(j);
						}
					}
				}
			}
			// 위시리스트 테이블 동기화
			showWishListGUI(userList, wishListPanel.table);
			Admin ad = new Admin();
			// 관리자 거래목록 테이블 동기화
			ad.showDealListByGUI(dealList, adminDealListPanel.table);
		
	}

	// 효과음 재생
	private void playMusic(String musicName) {
		// TODO Auto-generated method stub
		new Thread() {
			@Override
			public void run() {
				System.out.println("<<Member>> 상품구매 효과음 쓰레드 실행");
				try {
					File file = new File(Music.class.getResource("..\\music\\"+musicName).toURI());
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					Player player = new Player(bis);
					player.play();
				} catch (Exception e2) {
				}
			}
		}.start();
	}

	// 상품구매 메소드( + 상품구매 효과음 쓰레드 )
	public void buyBookGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			ArrayList<Deal> dealList, Member sellM, Member buyM, SaleBoard tempSB, Book tempB,
			SaleBoardListPanel saleBoardListPanel, WishListPanel wishListPanel, AdminDealListPanel adminDealListPanel) {
		if (sellM.isPossibleDeal) {
			if (buyM.isPossibleDeal) {
				if (buyM.coin >= tempB.price) {
					// 상품구매 효과음 쓰레드
					new Thread() {
						@Override
						public void run() {
							System.out.println("<<Member>> 상품구매 효과음 쓰레드 실행");
							try {
								File file = new File(Music.class.getResource("..\\music\\buyBook.mp3").toURI());
								FileInputStream fis = new FileInputStream(file);
								BufferedInputStream bis = new BufferedInputStream(fis);
								Player player = new Player(bis);
								player.play();
							} catch (Exception e2) {
							}
						}
					}.start();
					buyM.coin -= tempB.price;
					sellM.coin += tempB.price;
					Deal tempDeal = new Deal(dealList.size(), tempSB, buyM, sellM);
					tempSB.isSale = true;
					sellM.sellList.add(tempDeal);
					buyM.buyList.add(tempDeal);
					dealList.add(tempDeal);

					// 상품목록테이블 동기화
					try {
						showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("[ERROR] <<Member 상품구매 메소드>> showSaleBoardListByGUI 메소드 : "+e.getMessage());
						e.printStackTrace();
					}
					// 상품 구매에 따른 위시리스트 동기화
					for (int i = 0; i < userList.size(); i++) {
						if (userList.get(i) instanceof Member) {
							Member tempM = (Member) userList.get(i);
							for (int j = 0; j < tempM.wishList.size(); j++) {
								if (tempM.wishList.get(j).isSale) {
									tempM.wishList.remove(j);
								}
							}
						}
					}
					// 위시리스트테이블 동기화
					showWishListGUI(userList, wishListPanel.table);
					Admin ad = new Admin();
					// 관리자 거래목록 테이블 동기화
					ad.showDealListByGUI(dealList, adminDealListPanel.table);
					JOptionPane.showMessageDialog(null, "상품구매를 완료하였습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "코인이 부족합니다. 코인을 충전하세요.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "관리자에게 거래정지되었습니다. 관리자에게 문의하세요.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "판매자가 관리자에게 거래정지되었습니다. 다른 상품을 구매해주세요.");
		}
	}

	// 위시리스트에서 상품구매 메소드
	public void buyBookInWishListGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			ArrayList<Deal> dealList, Member sellM, SaleBoard tempSB, Book tempB, WishListPanel wishListPanel,
			AdminDealListPanel adminDealListPanel) {
		Member buyM = findLoginMember(userList);
		if (sellM.isPossibleDeal) {
			if (buyM.isPossibleDeal) {
				if (buyM.coin >= tempB.price) {
					// 상품구매 효과음 쓰레드
					new Thread() {
						@Override
						public void run() {
							System.out.println("<<Member>> 상품구매 효과음 쓰레드 실행");
							try {
								File file = new File(Music.class.getResource("..\\music\\buyBook.mp3").toURI());
								FileInputStream fis = new FileInputStream(file);
								BufferedInputStream bis = new BufferedInputStream(fis);
								Player player = new Player(bis);
								player.play();
							} catch (Exception e2) {
							}
						}
					}.start();
					buyM.coin -= tempB.price;
					sellM.coin += tempB.price;
					Deal tempDeal = new Deal(dealList.size(), tempSB, buyM, sellM);
					tempSB.isSale = true;
					sellM.sellList.add(tempDeal);
					buyM.buyList.add(tempDeal);
					dealList.add(tempDeal);
//					 상품목록 테이블 동기화
//					 showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
					 // 상품 구매에 따른 위시리스트 테이블 동기화
					for (int i = 0; i < userList.size(); i++) {
						if (userList.get(i) instanceof Member) {
							Member tempM = (Member) userList.get(i);
							for (int j = 0; j < tempM.wishList.size(); j++) {
								if (tempM.wishList.get(j).isSale) {
									tempM.wishList.remove(j);
								}
							}
						}
					}
					Admin ad = new Admin();
					// 관리자 거래목록 테이블 동기화
					ad.showDealListByGUI(dealList, adminDealListPanel.table);
					JOptionPane.showMessageDialog(null, "상품구매를 완료하였습니다.");
					// 위시리스트테이블 동기화
					// showWishListGUI(userList, wishListPanel.table);
				} else {
					JOptionPane.showMessageDialog(null, "코인이 부족합니다. 코인을 충전하세요.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "관리자에게 거래정지되었습니다. 관리자에게 문의하세요.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "판매자가 관리자에게 거래정지되었습니다. 다른 상품을 구매해주세요.");
		}
	}

	// 위시리스트 추가 메소드
	public void addWishList(ArrayList<User> userList, ArrayList<Board> boardList, int inputSaleBoardNo,
			WishListPanel wishListPanel) {
		Admin ad = new Admin();
		SaleBoard tempSB = (SaleBoard) ad.findBoardByBoardNo(boardList, inputSaleBoardNo);
		Member loginM = findLoginMember(userList);
		boolean saleBoardDuplicationCheck = false;
		for (int i = 0; i < loginM.wishList.size(); i++) {
			if (loginM.wishList.get(i).boardNo == inputSaleBoardNo) {
				saleBoardDuplicationCheck = true;
			}
		} // 위시리스트 중복체크
		if (saleBoardDuplicationCheck) {
			JOptionPane.showMessageDialog(null, tempSB.saleBook.bookName + "이 이미 위시리스트에 있습니다.");
		} else {
			loginM.wishList.add(tempSB);
			JOptionPane.showMessageDialog(null, tempSB.saleBook.bookName + "이 위시리스트에 추가되었습니다.");
			 // 위시리스트 테이블 동기화
			showWishListGUI(userList, wishListPanel.table);
		}
	} // 상품 위시리스트에 추가하기 메소드

	// 나의 정보보기 메소드
	public void myInfoGUI(Member loginM, JTextArea memberInfoTA) {
		String possibleDeal;
		if (loginM.isPossibleDeal) {
			possibleDeal = "거래 가능";
		} else {
			possibleDeal = "거래 불가";
		}
		String str = "  >> 멤버번호 :   " + loginM.userNo + "\n  >> 아이디 :   " + loginM.id + "\n  >> 이름 :   " + loginM.name
				+ "\n  >> 전화번호 :   " + loginM.phone + "\n  >> 생년월일 :   " + loginM.birthday + "\n  >> 성별 :   "
				+ loginM.gender + "\n  >> 주소 :   " + loginM.address + "\n  >> 코인 :   " + loginM.coin + "\n  >> 온도 :   " + loginM.grade
				+ "\n  >> 거래가능여부 :   " + possibleDeal;
		memberInfoTA.setText(str);
	}

	// 코인 충전 메소드
	public void chargeCoin(ArrayList<User> userList, Member loginM, String chargeCoin, JTextArea memberInfoTA) {
		loginM.coin += Integer.parseInt(chargeCoin);
		JOptionPane.showMessageDialog(null, chargeCoin + "코인을 충전하였습니다.");
		myInfoGUI(loginM, memberInfoTA);
	} // 코인 충전 메소드

	// 코인 환불 메소드
	public void refundCoin(ArrayList<User> userList, Member loginM, String refundCoin, JTextArea memberInfoTA) {
		int howMuchCoin = Integer.parseInt(refundCoin);
		if (howMuchCoin <= loginM.coin) {
			loginM.coin -= howMuchCoin;
			JOptionPane.showMessageDialog(null, refundCoin + "코인을 환불하였습니다.");
			myInfoGUI(loginM, memberInfoTA);
		} else {
			JOptionPane.showMessageDialog(null, refundCoin + "코인 이하만 환불 가능합니다.");
		}
	} // 코인 환불 메소드

	// 나의 위시리스트 보기 (유저리스트 인자)
	public void showWishListGUI(ArrayList<User> userList, JTable table) {
		Member tempM = null;
		SaleBoard tempSB = null;
		ArrayList<SaleBoard> tempWishList = new ArrayList<>();
		Vector<Vector> tableDataVector = new Vector<Vector>();
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Member) {
				tempM = (Member) userList.get(i);
				tempWishList = tempM.wishList;
				for (int j = 0; j < tempWishList.size(); j++) {
					tempSB = tempWishList.get(j);
					String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
					Vector<String> rowVector = new Vector<String>();
					rowVector.add(String.valueOf(tempSB.boardNo));
					rowVector.add(tempSB.saleBook.bookName);
					rowVector.add(String.valueOf(tempSB.saleBook.price) + "코인");
					rowVector.add(tempSB.boardWriteUser.id);
					rowVector.add(saleBoardWriteTimeToString);
					tableDataVector.add(rowVector);
				}
			}
		}

		Vector<String> headVector = new Vector<String>();
		headVector.add("게시글번호");
		headVector.add("책 제목");
		headVector.add("가격");
		headVector.add("판매자");
		headVector.add("작성시간");

		table.setModel(new DefaultTableModel(tableDataVector, headVector));
	}
	
	// 나의 위시리스트 보기 (유저리스트 인자)
		public void showWishListGUI(Member loginM, JTable table) {
			if(loginM!=null){
				SaleBoard tempSB = null;
				ArrayList<SaleBoard> tempWishList = new ArrayList<>();
				Vector<Vector> tableDataVector = new Vector<Vector>();
				tempWishList = loginM.wishList;
				for (int j = 0; j < tempWishList.size(); j++) {
					tempSB = tempWishList.get(j);
					String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
					Vector<String> rowVector = new Vector<String>();
					rowVector.add(String.valueOf(tempSB.boardNo));
					rowVector.add(tempSB.saleBook.bookName);
					rowVector.add(String.valueOf(tempSB.saleBook.price) + "코인");
					rowVector.add(tempSB.boardWriteUser.id);
					rowVector.add(saleBoardWriteTimeToString);
					tableDataVector.add(rowVector);
				}

				Vector<String> headVector = new Vector<String>();
				headVector.add("게시글번호");
				headVector.add("책 제목");
				headVector.add("가격");
				headVector.add("판매자");
				headVector.add("작성시간");

				table.setModel(new DefaultTableModel(tableDataVector, headVector));
			}
			
		}

	// 나의 구매리스트 보기
	public void showBuyListGUI(ArrayList<User> userList, JTable table) {
		Member loginM = findLoginMember(userList);
		ArrayList<Deal> myBuyList = new ArrayList<>();
		if (loginM != null) {
			myBuyList = loginM.buyList;
			Vector<String> headVector = new Vector<String>();
			headVector.add("거래번호");
			headVector.add("책 제목");
			headVector.add("가격");
			headVector.add("판매자");
			headVector.add("거래시간");
			Vector<Vector> tableDataVector = new Vector<Vector>();
			for (int i = 0; i < myBuyList.size(); i++) {
				Deal tempD = myBuyList.get(i);
				String saleBoardWriteTimeToString = timeToString(tempD.dealTime);
				Vector<String> rowVector = new Vector<String>();
				if(tempD.buyUserSatification!=0){
					rowVector.add(String.valueOf(tempD.dealNo)+" (평가완료)");
				}else{
					rowVector.add(String.valueOf(tempD.dealNo));
				}
				rowVector.add(tempD.saleBoard.saleBook.bookName);
				rowVector.add(String.valueOf(tempD.saleBoard.saleBook.price) + "코인");
				rowVector.add(tempD.sellMember.id);
				rowVector.add(saleBoardWriteTimeToString);
				tableDataVector.add(rowVector);
			}
			table.setModel(new DefaultTableModel(tableDataVector, headVector));
		} else {
			return;
		}
	}

	// 판매리스트 보기
	public void showSellListGUI(ArrayList<User> userList, JTable table) {
		Member loginM = findLoginMember(userList);
		ArrayList<Deal> mySellList = new ArrayList<>();
		if (loginM != null) {

			mySellList = loginM.sellList;
			Vector<String> headVector = new Vector<String>();
			headVector.add("거래번호");
			headVector.add("책 제목");
			headVector.add("가격");
			headVector.add("구매자");
			headVector.add("거래시간");
			Vector<Vector> tableDataVector = new Vector<Vector>();
			for (int i = 0; i < mySellList.size(); i++) {
				Deal tempD = mySellList.get(i);
				String saleBoardWriteTimeToString = timeToString(tempD.dealTime);
				Vector<String> rowVector = new Vector<String>();
				if(tempD.sellUserSatification!=0){
					rowVector.add(String.valueOf(tempD.dealNo)+" (평가완료)");
				}else{
					rowVector.add(String.valueOf(tempD.dealNo));
				}
				rowVector.add(tempD.saleBoard.saleBook.bookName);
				rowVector.add(String.valueOf(tempD.saleBoard.saleBook.price) + "코인");
				rowVector.add(tempD.buyMember.id);
				rowVector.add(saleBoardWriteTimeToString);
				tableDataVector.add(rowVector);
			}
			table.setModel(new DefaultTableModel(tableDataVector, headVector));
		}
	}

	// Calendar를 년월일시분초로 반환 메소드
	public String timeToString(Calendar time) {
//		String timeToString = (time.get(Calendar.YEAR)) + "년 " + (time.get(Calendar.MONTH) + 1) + "월 "
//				+ (time.get(Calendar.DAY_OF_MONTH)) + "일 " + (time.get(Calendar.HOUR_OF_DAY)) + "시 "
//				+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
		String timeToString = (time.get(Calendar.MONTH) + 1) + "월 "
				+ (time.get(Calendar.DAY_OF_MONTH)) + "일 " + (time.get(Calendar.HOUR_OF_DAY)) + "시 "
				+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
		return timeToString;
	}

	// 판매중인 상품보기 메소드
	public synchronized void showMySellingListGUI(ArrayList<User> userList, ArrayList<Board> boardList, JTable table) {
		Member loginM = findLoginMember(userList);
		if (loginM != null) {
			SaleBoard tempSB = null;
			ArrayList<SaleBoard> tempSellingList = new ArrayList<>();
			Vector<Vector> tableDataVector = new Vector<Vector>();
			for (int i = 0; i < boardList.size(); i++) {
				if (boardList.get(i) instanceof SaleBoard) {
					tempSB = (SaleBoard) boardList.get(i);
					if (tempSB.boardWriteUser.userNo == loginM.userNo && !tempSB.isSale) {
						String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
						Vector<String> rowVector = new Vector<String>();
						rowVector.add(String.valueOf(tempSB.boardNo));
						rowVector.add(tempSB.saleBook.bookName);
						rowVector.add(String.valueOf(tempSB.saleBook.price) + "코인");
						rowVector.add(saleBoardWriteTimeToString);
						tableDataVector.add(rowVector);
					}
				}
			}

			Vector<String> headVector = new Vector<String>();
			headVector.add("게시글번호");
			headVector.add("책 제목");
			headVector.add("가격");
			headVector.add("작성시간");

			table.setModel(new DefaultTableModel(tableDataVector, headVector));

		}
	}
	
	// 구매자 평가하기
	public boolean evaluateBuyMember(Data data, String dealNo, String point) {
		Deal tempD = null;
		for (int i = 0; i < data.dealList.size(); i++) {
			if(Integer.parseInt(dealNo)==data.dealList.get(i).dealNo){
				if(data.dealList.get(i).sellMember.userNo==findLoginMember(data.userList).userNo){
					tempD = data.dealList.get(i);					
				}
			}
		}
		if(tempD==null){
			JOptionPane.showMessageDialog(null, "거래번호를 잘못 입력하였습니다.");
			return false;
		}
		if(tempD.sellUserSatification==0){
			tempD.sellUserSatification = Integer.parseInt(point);
			Member buyM = tempD.buyMember;
			int score = buyM.grade*buyM.gradeCount+Integer.parseInt(point);
			buyM.gradeCount++;
			buyM.grade = score/buyM.gradeCount;
			tempD.sellUserSatification = Integer.parseInt(point);
			JOptionPane.showMessageDialog(null, "이 거래를 "+Integer.parseInt(point)+"점으로 평가하였습니다.");
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "이미 평가하였습니다.");
			return false;
		}
	}
	
	// 판매자 평가하기
	public boolean evaluateSellMember(Data data, String dealNo, String point) {
		Deal tempD = null;
		for (int i = 0; i < data.dealList.size(); i++) {
			if(Integer.parseInt(dealNo)==data.dealList.get(i).dealNo){
				if(data.dealList.get(i).buyMember.userNo==findLoginMember(data.userList).userNo){
					tempD = data.dealList.get(i);
				}
			}
		}
		if(tempD==null){
			JOptionPane.showMessageDialog(null, "거래번호를 잘못 입력하였습니다.");
			return false;
		}
		if(tempD.buyUserSatification==0){
			tempD.buyUserSatification = Integer.parseInt(point);
			Member sellM = tempD.sellMember;
			int score = sellM.grade*sellM.gradeCount+Integer.parseInt(point);
			sellM.gradeCount++;
			sellM.grade = score/sellM.gradeCount;
			tempD.buyUserSatification = Integer.parseInt(point);
			JOptionPane.showMessageDialog(null, "이 거래를 "+Integer.parseInt(point)+"점으로 평가하였습니다.");
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "이미 평가하였습니다.");
			return false;
		}
	}

	// 콘솔용 나의 정보보기 메소드
	// public void myInfo(ArrayList<User> userList, int loginMemberNo) {
	// Admin admin = new Admin();
	// Member loginM = admin.findMemberByUserNo(userList, loginMemberNo);
	// pageNamePrint("나의 정보보기");
	// System.out.print("<Member> ");
	// System.out.println(">> 멤버번호: " + loginM.userNo);
	// System.out.print("<Member> ");
	// System.out.println(">> 아이디: " + loginM.id);
	// System.out.print("<Member> ");
	// System.out.println(">> 이름: " + loginM.name);
	// System.out.print("<Member> ");
	// System.out.println(">> 전화번호: " + loginM.phone);
	// System.out.print("<Member> ");
	// System.out.println(">> 생년월일: " + loginM.birthday);
	// System.out.print("<Member> ");
	// System.out.println(">> 성별: " + loginM.gender);
	// System.out.print("<Member> ");
	// System.out.println(">> 주소: " + loginM.address);
	// System.out.print("<Member> ");
	// System.out.println(">> 코인: " + loginM.coin);
	// if (loginM.couponList.size() == 0) {
	// System.out.print("<Member> ");
	// System.out.println(">> 쿠폰: 쿠폰이 없습니다.");
	// } else {
	// System.out.print("<Member> ");
	// System.out.print(">> 쿠폰: ");
	// for (int i = 0; i < couponList.size(); i++) {
	// Coupon tempC = new Coupon();
	// if (i == couponList.size() - 1) {
	// System.out.println(tempC.couponName);
	// } else {
	// System.out.print(tempC.couponName + " / ");
	// }
	// }
	// }
	// if (loginM.grade == 0) {
	// System.out.print("<Member> ");
	// System.out.println(">> 평점: 평가받지 않음");
	// } else {
	// System.out.print("<Member> ");
	// System.out.println(">> 평점: " + loginM.grade);
	// }
	//
	// System.out.println();
	//// Scanner sc = new Scanner(System.in);
	//// System.out.print("<Member> ");
	//// System.out.print("코인충전을 하시겠습니까? (0: 코인충천, 1: 코인환불, 다른키: 뒤로가기) : ");
	//// int select = sc.nextInt();
	//// if (select == 0) {
	//// chargeCoin(userList, loginM);
	//// } else if (select == 1) {
	//// refundCoin(userList, loginM);
	//// } else {
	//// System.out.print("<Member> ");
	//// System.out.println("뒤로가기를 누르셨습니다.");
	//// System.out.println();
	//// return;
	//// }
	// } // 나의 정보 보기 메소드

	// 콘솔용 상품 목록보기 메소드
	// public void showSaleBoardList(ArrayList<User> userList, ArrayList<Board>
	// boardList, ArrayList<Book> bookList,
	// ArrayList<Deal> dealList) {
	// Admin admin = new Admin();
	// Scanner sc = new Scanner(System.in);
	// SaleBoard tempSB = null;
	// Book tempB = null;
	// Member tempM = null;
	// pageNamePrint("상품 게시글 목록");
	// for (int i = 0; i < boardList.size(); i++) {
	// if (boardList.get(i) instanceof SaleBoard) {
	// tempSB = (SaleBoard) boardList.get(i);
	// tempB = tempSB.saleBook;
	// tempM = (Member) tempSB.boardWriteUser;
	// String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
	// String saleBoardInfo = ">> 게시글 번호: " + tempSB.boardNo + " / 책 제목: " +
	// tempB.bookName + " / 가격: "
	// + tempB.price + " / 판매자: " + tempM.id + " / 작성시간: " +
	// saleBoardWriteTimeToString;
	// if (!tempM.isPossibleDeal) {
	// saleBoardInfo = saleBoardInfo + " / 구매불가 (" + tempM.id + " 거래정지)";
	// }
	// if (!tempSB.isSale && tempM.userNo != MainFrame.LOGIN_STATE[1]) {
	// try {
	// Thread.sleep(300);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.print("<Member> ");
	// System.out.println(saleBoardInfo);
	// }
	// }
	// } // end while
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.print("확인하실 상품게시글의 번호를 입력해주세요(다른키: 뒤로가기) : ");
	// int boardNo = sc.nextInt();
	// this.showSaleBoardDetail(userList, boardList, bookList, dealList,
	// boardNo);
	// } // 상품목록 보기 메소드

	// 콘솔용 상품 상세보기 메소드
	// public void showSaleBoardDetail(ArrayList<User> userList,
	// ArrayList<Board> boardList, ArrayList<Book> bookList,
	// ArrayList<Deal> dealList, int boardNo) {
	// Admin admin = new Admin();
	// Scanner sc = new Scanner(System.in);
	// SaleBoard tempSB = null;
	// Book tempB = null;
	// Member tempM = null;
	// while (true) {
	// if (0 <= boardNo && boardNo < boardList.size() && boardList.get(boardNo)
	// instanceof SaleBoard) {
	// tempSB = (SaleBoard) boardList.get(boardNo);
	// tempB = tempSB.saleBook;
	// tempM = (Member) tempSB.boardWriteUser;
	// Calendar saleBoardWriteTime = tempSB.boardWriteTime;
	// String saleBoardWriteTimeToString =
	// (saleBoardWriteTime.get(Calendar.YEAR)) + "년 "
	// + (saleBoardWriteTime.get(Calendar.MONTH) + 1) + "월 "
	// + (saleBoardWriteTime.get(Calendar.DAY_OF_MONTH)) + "일 "
	// + (saleBoardWriteTime.get(Calendar.HOUR_OF_DAY)) + "시 "
	// + (saleBoardWriteTime.get(Calendar.MINUTE)) + "분 " +
	// (saleBoardWriteTime.get(Calendar.SECOND))
	// + "초";
	// if (!tempSB.isSale && tempM.userNo != MainFrame.LOGIN_STATE[1]) {
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.println("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
	// System.out.print("<Member> ");
	// System.out.println("상품 게시글 상세보기 페이지입니다.");
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.println(">> 게시글 번호: " + tempSB.boardNo);
	// System.out.print("<Member> ");
	// System.out.println(">> 작성자: " + tempM.id);
	// System.out.print("<Member> ");
	// System.out.println(">> 작성시간: " + saleBoardWriteTimeToString);
	// System.out.print("<Member> ");
	// System.out.println(">> 책 제목: " + tempB.bookName);
	// System.out.print("<Member> ");
	// System.out.println(">> 지은이: " + tempB.author);
	// System.out.print("<Member> ");
	// System.out.println(">> 가격: " + tempB.price);
	// System.out.print("<Member> ");
	// System.out.println(">> 책 소개: " + tempSB.boardContent);
	// if (tempB.bookState == 1) {
	// System.out.print("<Member> ");
	// System.out.println(">> 책 의 상태: 거의 새책");
	// } else if (tempB.bookState == 2) {
	// System.out.print("<Member> ");
	// System.out.println(">> 책 의 상태: 책이 접혀있거나 줄이 그어져있습니다");
	// } else if (tempB.bookState == 3) {
	// System.out.print("<Member> ");
	// System.out.println(">> 책 의 상태: 책이 조금 찢어져 있습니다.");
	// }
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.print("무엇을 하시겠습니까? (0: 상품구매, 1: 위시리스트에 추가, 다른키: 뒤로가기) : ");
	// int select = sc.nextInt();
	// switch (select) {
	// case 0:
	// this.buyBook(userList, boardList, bookList, dealList, tempM, tempSB,
	// tempB);
	// break;
	// case 1:
	// // this.addWishList(userList, tempSB);
	// break;
	// default:
	// this.showSaleBoardList(userList, boardList, bookList, dealList);
	// break;
	// }
	// break;
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("뒤로가기를 누르셨습니다.");
	// System.out.println();
	// break;
	// }
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("뒤로가기를 누르셨습니다.");
	// System.out.println();
	// break;
	// }
	// } // end while
	// } // 상품 상세보기 메소드

	// 콘솔용 상품구매 메소드
	// public void buyBook(ArrayList<User> userList, ArrayList<Board> boardList,
	// ArrayList<Book> bookList,
	// ArrayList<Deal> dealList, Member sellM, SaleBoard tempSB, Book tempB) {
	// Scanner sc = new Scanner(System.in);
	//
	// Deal tempDeal;
	// System.out.println();
	// Member loginM = findLoginMember(userList);
	// if (loginM.isPossibleDeal && sellM.isPossibleDeal) {
	// System.out.print("<Member> ");
	// System.out.println("상품구매를 진행합니다.");
	// while (true) {
	// System.out.print("<Member> ");
	// System.out.print("비밀번호를 입력해주세요 (구매를 원하지 않으시면 '-1'을 눌러주세요) : ");
	// String tempPass = sc.nextLine();
	// if (tempPass.equals("-1")) {
	// System.out.print("<Member> ");
	// System.out.println("상품구매를 취소하였습니다.");
	// System.out.println();
	// break;
	// }
	// if (tempPass.equals(loginM.password)) {
	// if (loginM.coin >= tempB.price) {
	// System.out.print("<Member> ");
	// System.out.println("결제 진행중입니다.");
	// // ~~~~~~~~~~~~~~~~~~~~~~~ 로딩바 쓰레드 시작
	// System.out.print("<Member> ");
	// for (int i = 0; i < 30; i++) {
	// try {
	// Thread.sleep(100);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// System.out.print("■");
	// }
	// System.out.println();
	// // ~~~~~~~~~~~~~~~~~~~~~~~ 쓰레드 종료
	// loginM.coin -= tempB.price;
	// tempDeal = new Deal(dealList.size(), tempSB, loginM, sellM);
	// dealList.add(tempDeal); // 거래리스트에 거래객체 추가
	// tempSB.isSale = true;// 책 판매완료 세팅
	// sellM.sellList.add(tempDeal); // 판매자 판매리스트에 거래객체 추가
	// loginM.buyList.add(tempDeal); // 구매자 구매리스트에 구매객체 추가
	// System.out.print("<Member> ");
	// System.out.println("상품구매를 완료하였습니다.");
	// new Thread() {
	// @Override
	// public void run() {
	// Member tempM = null;
	// for (int i = 0; i < userList.size(); i++) {
	// if (userList.get(i) instanceof Member) {
	// tempM = (Member) userList.get(i);
	// for (int j = 0; j < tempM.wishList.size(); j++) {
	// if (tempM.wishList.get(j).isSale) {
	// tempM.wishList.remove(j);
	// }
	// }
	// }
	// }
	// }
	// }.start(); // wishList 동기화해주는 쓰레드
	// break;
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("코인이 부족합니다. 코인을 더 충전해주세요.");
	// break;
	// }
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("비밀번호가 틀렸습니다.");
	// }
	// }
	// } else if (loginM.isPossibleDeal && !sellM.isPossibleDeal) {
	// System.out.print("<Member> ");
	// System.out.println("판매자가 관리자에 의해 거래정지를 당하였습니다. 다른 상품을 구매해주세요.");
	// this.showSaleBoardList(userList, boardList, bookList, dealList);
	// } else if (!loginM.isPossibleDeal && sellM.isPossibleDeal) {
	// System.out.print("<Member> ");
	// System.out.println("관리자에 의해 거래정지를 당하였습니다. 관리자에게 문의해주세요");
	// } else if (!loginM.isPossibleDeal && !sellM.isPossibleDeal) {
	// System.out.print("<Member> ");
	// System.out.println("구매자님과 판매자가 관리자에 의해 거래정지를 당하였습니다. 다른 상품을 구매해주세요.");
	// }
	// } // 상품 구매 메소드

	// 콘솔용 상품등록 메소드
	// public void registerBook(ArrayList<User> userList, ArrayList<Board>
	// boardList, ArrayList<Book> bookList) {
	// Scanner sc = new Scanner(System.in);
	// pageNamePrint("상품등록");
	// Member loginM = findLoginMember(userList);
	// System.out.print("<Member> ");
	// System.out.println("책의 정보를 입력해주세요.");
	// System.out.print("<Member> ");
	// System.out.println(">> 책 이름: ");
	// String tempBookName = sc.nextLine();
	// System.out.print("<Member> ");
	// System.out.println(">> 지은이: ");
	// String tempAuthor = sc.nextLine();
	// System.out.print("<Member> ");
	// System.out.println(">> 장르: ");
	// String tempGenre = sc.nextLine();
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.print(">> 가격: ");
	// int tempPrice = sc.nextInt();
	// System.out.println();
	// int tempBookState;
	// while (true) {
	// System.out.print("<Member> ");
	// System.out.print(">> 책의 상태 (1: 거의 새책, 2: 책이 접혀있거나 줄이 그어진 경우, 3: 책 내용이 찢어진
	// 경우) : ");
	// tempBookState = sc.nextInt();
	// if (tempBookState == 1 || tempBookState == 2 || tempBookState == 3) {
	// break;
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("[error] 1~3의 수를 입력해주세요.");
	// }
	// }
	// System.out.println();
	// Book registerBook = new Book(bookList.size(), tempBookName, tempAuthor,
	// tempGenre, tempPrice, tempBookState);
	// System.out.print("<Member> ");
	// System.out.print(">> 책을 소개해주세요: ");
	// sc = new Scanner(System.in);
	// String tempContent = sc.nextLine();
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.print("책을 등록하시겠습니까? (0: 예, 다른키: 아니요) : ");
	// int select = sc.nextInt();
	// switch (select) {
	// case 0:
	// SaleBoard tempSB = new SaleBoard(boardList.size(), tempContent, loginM,
	// registerBook);
	// bookList.add(registerBook);
	// boardList.add(tempSB);
	// System.out.print("<Member> ");
	// System.out.print("책 등록을 완료하였습니다.");
	// System.out.println();
	// break;
	// default:
	// System.out.print("<Member> ");
	// System.out.print("책 등록을 취소하였습니다.");
	// System.out.println();
	// break;
	// }
	//
	// } // 상품을 등록하는 메소드

	// 콘솔용 나의 판매중인상품목록 보기 메소드
	// public void showMySellingList(ArrayList<User> userList, ArrayList<Board>
	// boardList) {
	// pageNamePrint("나의 판매중인 상품리스트");
	// Member loginM = findLoginMember(userList);
	// SaleBoard tempSB = null;
	// Book tempB = null;
	// Member tempM = null;
	// for (int i = 0; i < boardList.size(); i++) {
	// if (boardList.get(i) instanceof SaleBoard) {
	// tempSB = (SaleBoard) boardList.get(i);
	// if (tempSB.boardWriteUser.userNo == loginM.userNo && !tempSB.isSale) {
	// tempB = tempSB.saleBook;
	// tempM = (Member) tempSB.boardWriteUser;
	// Calendar saleBoardWriteTime = tempSB.boardWriteTime;
	// String saleBoardWriteTimeToString =
	// (saleBoardWriteTime.get(Calendar.YEAR)) + "년 "
	// + (saleBoardWriteTime.get(Calendar.MONTH) + 1) + "월 "
	// + (saleBoardWriteTime.get(Calendar.DAY_OF_MONTH)) + "일 "
	// + (saleBoardWriteTime.get(Calendar.HOUR_OF_DAY)) + "시 "
	// + (saleBoardWriteTime.get(Calendar.MINUTE)) + "분 "
	// + (saleBoardWriteTime.get(Calendar.SECOND)) + "초";
	// String saleBoardInfo = ">> 게시글 번호: " + tempSB.boardNo + " / 책 제목: " +
	// tempB.bookName + " / 가격: "
	// + tempB.price + " / 판매자: " + tempM.id + " / 작성시간: " +
	// saleBoardWriteTimeToString;
	// if (!tempM.isPossibleDeal) {
	// saleBoardInfo = saleBoardInfo + " / 구매불가 (" + tempM.id + " 거래정지)";
	// }
	// try {
	// Thread.sleep(300);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.print("<Member> ");
	// System.out.println(saleBoardInfo);
	// }
	// }
	// }
	// } // 판매중인 상품 리스트

	// 콘솔용 나의 판매목록 보기 메소드
	// public void showMySellList(ArrayList<User> userList) {
	// pageNamePrint("나의 판매리스트");
	// Member loginM = findLoginMember(userList);
	// ArrayList<Deal> sellList = loginM.sellList;
	// for (int i = 0; i < sellList.size(); i++) {
	// Deal tempD = (Deal) sellList.get(i);
	// Calendar dealTime = tempD.dealTime;
	// String dealTimeToString = (dealTime.get(Calendar.YEAR)) + "년 " +
	// (dealTime.get(Calendar.MONTH) + 1) + "월 "
	// + (dealTime.get(Calendar.DAY_OF_MONTH)) + "일 " +
	// (dealTime.get(Calendar.HOUR_OF_DAY)) + "시 "
	// + (dealTime.get(Calendar.MINUTE)) + "분 " +
	// (dealTime.get(Calendar.SECOND)) + "초";
	// String str = "거래 번호: " + tempD.dealNo + " / 거래일시: " + dealTimeToString +
	// " / 구매자: " + tempD.buyMember.id
	// + " / 책 이름: " + tempD.saleBoard.saleBook.bookName + " / 가격: " +
	// tempD.saleBoard.saleBook.price;
	// System.out.print("<Member> ");
	// System.out.println(str);
	// }
	// } // 내 판매리스트 보기

	// 콘솔용 나의 구매목록 보기 메소드
	// public void showMyBuyList(ArrayList<User> userList) {
	// pageNamePrint("나의 구매리스트");
	// Member loginM = findLoginMember(userList);
	// ArrayList<Deal> buyList = loginM.buyList;
	// for (int i = 0; i < buyList.size(); i++) {
	// Deal tempD = (Deal) buyList.get(i);
	// Calendar dealTime = tempD.dealTime;
	// String dealTimeToString = (dealTime.get(Calendar.YEAR)) + "년 " +
	// (dealTime.get(Calendar.MONTH) + 1) + "월 "
	// + (dealTime.get(Calendar.DAY_OF_MONTH)) + "일 " +
	// (dealTime.get(Calendar.HOUR_OF_DAY)) + "시 "
	// + (dealTime.get(Calendar.MINUTE)) + "분 " +
	// (dealTime.get(Calendar.SECOND)) + "초";
	// String str = "거래 번호: " + tempD.dealNo + " / 거래일시: " + dealTimeToString +
	// " / 구매자: " + tempD.buyMember.id
	// + " / 책 이름: " + tempD.saleBoard.saleBook.bookName + " / 가격: " +
	// tempD.saleBoard.saleBook.price;
	// System.out.print("<Member> ");
	// System.out.println(str);
	// }
	// } // 내 구매리스트 보기

	// 콘솔용 나의 위시리스트 보기 메소드
	// public void showMyWishList(ArrayList<User> userList) {
	// pageNamePrint("나의 위시리스트");
	// Member loginM = findLoginMember(userList);
	// ArrayList<SaleBoard> wishList = loginM.wishList;
	// for (int i = 0; i < wishList.size(); i++) {
	// SaleBoard tempSB = wishList.get(i);
	// String str = "게시글 번호: " + tempSB.boardNo + " / 책 제목 : " +
	// tempSB.saleBook.bookName + " / 가격: "
	// + tempSB.saleBook.price + " / 판매자: " + tempSB.boardWriteUser.id;
	// System.out.print("<Member> ");
	// System.out.println(str);
	// }
	// } // 내 위시리스트 보기

	// 콘솔용 페이지 이름 출력 메소드
	// public void pageNamePrint(String pageName) {
	// System.out.println();
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.println("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
	// System.out.print("<Member> ");
	// System.out.println(pageName + " 페이지입니다.");
	// System.out.println();
	// } // 페이지 이름 출력

	// 나의 위시리스트 보기 (로그인멤버 인자)
	/*
	 * private void showWishListGUI(Member loginM, JTable table) {
	 * ArrayList<SaleBoard> tempWishList = new ArrayList<>(); tempWishList =
	 * loginM.wishList; Vector<Vector> tableDataVector = new Vector<Vector>();
	 * for (int i = 0; i < tempWishList.size(); i++) { SaleBoard tempSB =
	 * tempWishList.get(i); String saleBoardWriteTimeToString =
	 * timeToString(tempSB.boardWriteTime); Vector<String> rowVector = new
	 * Vector<String>(); rowVector.add(String.valueOf(tempSB.boardNo));
	 * rowVector.add(tempSB.saleBook.bookName);
	 * rowVector.add(String.valueOf(tempSB.saleBook.price) + "코인");
	 * rowVector.add(tempSB.boardWriteUser.id);
	 * rowVector.add(saleBoardWriteTimeToString);
	 * tableDataVector.add(rowVector); } Vector<String> headVector = new
	 * Vector<String>(); headVector.add("게시글번호"); headVector.add("책 제목");
	 * headVector.add("가격"); headVector.add("판매자"); headVector.add("작성시간");
	 * 
	 * table.setModel(new DefaultTableModel(tableDataVector, headVector)); }
	 */

} // Member class
