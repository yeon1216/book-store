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

	public String name; // �̸�
	public String phone; // ��ȭ��ȣ
	public String birthday; // �������
	public String gender; // ����
	public String address; // �ּ�
	public int grade; // �µ�
	public int coin; // ����
	public boolean isPossibleDeal; // �ŷ����� ����
	public ArrayList<Deal> buyList; // ���Ÿ���Ʈ
	public ArrayList<Deal> sellList; // �ǸŸ���Ʈ
	public ArrayList<SaleBoard> wishList; // ���ø���Ʈ
	public boolean isParticipationAdversity; // �����̺�Ʈ ��������
	public boolean isParticipationLotto; // �ζ��̺�Ʈ ��������
	public boolean isParticipationCardGame; // ī������̺�Ʈ ��������
	public int gradeCount; // �򰡹��� Ƚ��
	
	public void printConsolMember(ArrayList<User> userList){
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i) instanceof Member){
				Member m = (Member)userList.get(i);
				System.out.println("�����ȣ : "+ m.userNo+" / ���̵� : "+m.id+" / �µ� : "+m.grade);
			}
		}
		System.out.println();
	}

	// Member �⺻ ������
	public Member() {
	} // Member �⺻ ������

	// Member ������
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
	} // Member ������

	// ���̵�, ��й�ȣ ã�� �޼ҵ�
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
	} // ���̵� ��й�ȣ ã�� �޼ҵ�

	// ȸ������ �޼ҵ� (+ ȸ������ ȿ���� ������ )
	public synchronized void join(ArrayList<User> userList, int userNo, String id, String password, String name,
			String phone, String birthday, String gender, String address, AdminMemberListPanel adminMemberListPanel) {
		Member joinMember = new Member(userNo, id, password, name, phone, birthday, gender, address);
		userList.add(joinMember);
		Admin ad = new Admin();
		// ȸ�����Խ� ȸ������Ʈ ���̺� ����ȭ
		ad.showMemberListByGUI(userList, adminMemberListPanel.table);

		// ȸ�����Խ� ȿ���� ������
		new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("<<Member>> ȸ������ ȿ���� ������ ����");
					File file = new File(Music.class.getResource("..\\music\\join.mp3").toURI());
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					Player player = new Player(bis);
					player.play();
				} catch (Exception e2) {
				}
			}
		}.start();

	} // ȸ������ �޼ҵ�

	// ���̵� �ߺ� üũ �޼ҵ�
	public boolean idDuplicationCheck(ArrayList<User> userList, String tempId) {
		boolean duplicationCheck = false;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).id.equals(tempId)) {
				duplicationCheck = true;
			}
		}
		return duplicationCheck;
	} // ���̵� �ߺ� üũ �޼ҵ�

	// �ڵ��� ��ȣ �ߺ� üũ �޼ҵ�
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
	} // �ڵ��� ��ȣ �ߺ� ü�� �޼ҵ�

	// �α��� ��� ã�� �޼ҵ�
	public Member findLoginMember(ArrayList<User> userList) {
		Admin ad = new Admin();
		Member loginM = ad.findMemberByUserNo(userList, MainFrame.LOGIN_STATE[1]);
		return loginM;
	} // �α��� ��� ã�� �޼ҵ�

	// ��ǰ��� ���� �޼ҵ�
	public synchronized void showSaleBoardListByGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			JTable table) throws Exception{
		try {
			Vector<String> headVector = new Vector<String>();
			headVector.add("�Խñ۹�ȣ");
			headVector.add("å ����");
			headVector.add("����");
			headVector.add("�Ǹ���");
			headVector.add("�ۼ��ð�");
			Vector<Vector> tableDataVector = new Vector<Vector>();
			for (int i = 0; i < boardList.size(); i++) {
				if (boardList.get(i) instanceof SaleBoard) {
					SaleBoard tempSB = (SaleBoard) boardList.get(i);
					if (!tempSB.isSale && tempSB.boardWriteUser.userNo != MainFrame.LOGIN_STATE[1]) {
						String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
						Vector<String> rowVector = new Vector<String>();
						rowVector.add(String.valueOf(tempSB.boardNo));
						rowVector.add(tempSB.saleBook.bookName);
						rowVector.add(String.valueOf(tempSB.saleBook.price) + "����");
						Member sellM = null;
						if(tempSB.boardWriteUser instanceof Member){
							sellM = (Member)tempSB.boardWriteUser;
						}
						if(sellM.isPossibleDeal){
							rowVector.add(tempSB.boardWriteUser.id);
						}else{
							rowVector.add(tempSB.boardWriteUser.id+"  (�ŷ�����)");
						}
						rowVector.add(saleBoardWriteTimeToString);
						tableDataVector.add(rowVector);
					}
				}
			}
			table.setModel(new DefaultTableModel(tableDataVector, headVector));
		} catch (Exception e) {
			System.out.println("[ERROR] showSaleBoardListByGUI �޼ҵ� : "+e.getMessage());
		}

	}

	// �ǸŰԽñ� �󼼺���
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
					bookState = "���� ��å";
				} else if (tempSB.saleBook.bookState == 2) {
					bookState = "å�� �����ְų� ���� �׾����ֽ��ϴ�.";
				} else if (tempSB.saleBook.bookState == 3) {
					bookState = "å�� ���� ������ �κ��� �ֽ��ϴ�.";
				}
				String content = tempSB.boardContent;
				String str = "  >> �Խñ� ��ȣ :  " + tempSB.boardNo + "\n  >> �Ǹ��� :  " + tempSB.boardWriteUser.id
						+ "\n  >> �ۼ��ð� :  " + saleBoardWriteTimeToString + "\n  >> å ���� :  " + tempSB.saleBook.bookName
						+ "\n  >> ������ :  " + tempSB.saleBook.author + "\n  >> �帣 :  " + tempSB.saleBook.genre
						+ "\n  >> å �Ұ� :  " + content + "\n  >> å�� ���� :  " + bookState + "\n  >> ���� :  "
						+ tempSB.saleBook.price + "����";
				isSaleBoard = true;
				saleBoardInfoTA.setText(str);
			}
		}
		return isSaleBoard;
	}

	// ���ø���Ʈ���� ��ǰ �󼼺���
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
						bookState = "���� ��å";
					} else if (tempSB.saleBook.bookState == 2) {
						bookState = "å�� �����ְų� ���� �׾����ֽ��ϴ�.";
					} else if (tempSB.saleBook.bookState == 3) {
						bookState = "å�� ���� ������ �κ��� �ֽ��ϴ�.";
					}
					String str = " �Խñ� ��ȣ :  " + tempSB.boardNo + "\n �Ǹ��� :  " + tempSB.boardWriteUser.id
							+ "\n �ۼ��ð� :  " + saleBoardWriteTimeToString + "\n å ���� :  " + tempSB.saleBook.bookName
							+ "\n ������ :  " + tempSB.saleBook.author + "\n �帣 :  " + tempSB.saleBook.genre
							+ "\n å �Ұ� :  " + tempSB.boardContent + "\n å�� ���� :  " + bookState + "\n ���� :  "
							+ tempSB.saleBook.price + "����";
					isSaleBoard = true;
					saleBoardInfoTA.setText(str);
				}
			}
		}
		return isSaleBoard;
	}

	// ��ǰ��� �޼ҵ�
	public synchronized void registerBookByGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			SaleBoard tempSB, SaleBoardListPanel saleBoardListPanel, MySellingListPanel mySellingListPanel) {
		bookList.add(tempSB.saleBook);
		boardList.add(tempSB);
//		JOptionPane.showMessageDialog(null, "��ǰ�� ����Ͽ����ϴ�.");
		try {
			showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR] <<Member  ��ǰ��� �޼ҵ�>>showSaleBoardListByGUI �޼ҵ� : "+e.getMessage());
			e.printStackTrace();
		}
		showMySellingListGUI(userList, boardList, mySellingListPanel.table);
		notify();
		// ��ǰ��Ͻ� ȿ���� ������
		new Thread() {
			@Override
			public void run() {
				System.out.println("<<Memeber>> ��ǰ��� ȿ���� ������ ����");
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

	// �ڵ���ǰ���� �޼ҵ�
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
			// ��ǰ���� ȿ���� ������
			playMusic("buyBook.mp3");
			// ��ǰ��� ���̺� ����ȭ
			try {
				showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("[ERROR] <<Member �ڵ� ��ǰ���� �޼ҵ�>> showSaleBoardListByGUI �޼ҵ� : "+e.getMessage());
				e.printStackTrace();
			}
			// ��ǰ���ſ� ���� ���ø���Ʈ ����ȭ
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
			// ���ø���Ʈ ���̺� ����ȭ
			showWishListGUI(userList, wishListPanel.table);
			Admin ad = new Admin();
			// ������ �ŷ���� ���̺� ����ȭ
			ad.showDealListByGUI(dealList, adminDealListPanel.table);
		
	}

	// ȿ���� ���
	private void playMusic(String musicName) {
		// TODO Auto-generated method stub
		new Thread() {
			@Override
			public void run() {
				System.out.println("<<Member>> ��ǰ���� ȿ���� ������ ����");
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

	// ��ǰ���� �޼ҵ�( + ��ǰ���� ȿ���� ������ )
	public void buyBookGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			ArrayList<Deal> dealList, Member sellM, Member buyM, SaleBoard tempSB, Book tempB,
			SaleBoardListPanel saleBoardListPanel, WishListPanel wishListPanel, AdminDealListPanel adminDealListPanel) {
		if (sellM.isPossibleDeal) {
			if (buyM.isPossibleDeal) {
				if (buyM.coin >= tempB.price) {
					// ��ǰ���� ȿ���� ������
					new Thread() {
						@Override
						public void run() {
							System.out.println("<<Member>> ��ǰ���� ȿ���� ������ ����");
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

					// ��ǰ������̺� ����ȭ
					try {
						showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("[ERROR] <<Member ��ǰ���� �޼ҵ�>> showSaleBoardListByGUI �޼ҵ� : "+e.getMessage());
						e.printStackTrace();
					}
					// ��ǰ ���ſ� ���� ���ø���Ʈ ����ȭ
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
					// ���ø���Ʈ���̺� ����ȭ
					showWishListGUI(userList, wishListPanel.table);
					Admin ad = new Admin();
					// ������ �ŷ���� ���̺� ����ȭ
					ad.showDealListByGUI(dealList, adminDealListPanel.table);
					JOptionPane.showMessageDialog(null, "��ǰ���Ÿ� �Ϸ��Ͽ����ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "������ �����մϴ�. ������ �����ϼ���.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "�����ڿ��� �ŷ������Ǿ����ϴ�. �����ڿ��� �����ϼ���.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "�Ǹ��ڰ� �����ڿ��� �ŷ������Ǿ����ϴ�. �ٸ� ��ǰ�� �������ּ���.");
		}
	}

	// ���ø���Ʈ���� ��ǰ���� �޼ҵ�
	public void buyBookInWishListGUI(ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList,
			ArrayList<Deal> dealList, Member sellM, SaleBoard tempSB, Book tempB, WishListPanel wishListPanel,
			AdminDealListPanel adminDealListPanel) {
		Member buyM = findLoginMember(userList);
		if (sellM.isPossibleDeal) {
			if (buyM.isPossibleDeal) {
				if (buyM.coin >= tempB.price) {
					// ��ǰ���� ȿ���� ������
					new Thread() {
						@Override
						public void run() {
							System.out.println("<<Member>> ��ǰ���� ȿ���� ������ ����");
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
//					 ��ǰ��� ���̺� ����ȭ
//					 showSaleBoardListByGUI(userList, boardList, bookList, saleBoardListPanel.table);
					 // ��ǰ ���ſ� ���� ���ø���Ʈ ���̺� ����ȭ
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
					// ������ �ŷ���� ���̺� ����ȭ
					ad.showDealListByGUI(dealList, adminDealListPanel.table);
					JOptionPane.showMessageDialog(null, "��ǰ���Ÿ� �Ϸ��Ͽ����ϴ�.");
					// ���ø���Ʈ���̺� ����ȭ
					// showWishListGUI(userList, wishListPanel.table);
				} else {
					JOptionPane.showMessageDialog(null, "������ �����մϴ�. ������ �����ϼ���.");
				}
			} else {
				JOptionPane.showMessageDialog(null, "�����ڿ��� �ŷ������Ǿ����ϴ�. �����ڿ��� �����ϼ���.");
			}
		} else {
			JOptionPane.showMessageDialog(null, "�Ǹ��ڰ� �����ڿ��� �ŷ������Ǿ����ϴ�. �ٸ� ��ǰ�� �������ּ���.");
		}
	}

	// ���ø���Ʈ �߰� �޼ҵ�
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
		} // ���ø���Ʈ �ߺ�üũ
		if (saleBoardDuplicationCheck) {
			JOptionPane.showMessageDialog(null, tempSB.saleBook.bookName + "�� �̹� ���ø���Ʈ�� �ֽ��ϴ�.");
		} else {
			loginM.wishList.add(tempSB);
			JOptionPane.showMessageDialog(null, tempSB.saleBook.bookName + "�� ���ø���Ʈ�� �߰��Ǿ����ϴ�.");
			 // ���ø���Ʈ ���̺� ����ȭ
			showWishListGUI(userList, wishListPanel.table);
		}
	} // ��ǰ ���ø���Ʈ�� �߰��ϱ� �޼ҵ�

	// ���� �������� �޼ҵ�
	public void myInfoGUI(Member loginM, JTextArea memberInfoTA) {
		String possibleDeal;
		if (loginM.isPossibleDeal) {
			possibleDeal = "�ŷ� ����";
		} else {
			possibleDeal = "�ŷ� �Ұ�";
		}
		String str = "  >> �����ȣ :   " + loginM.userNo + "\n  >> ���̵� :   " + loginM.id + "\n  >> �̸� :   " + loginM.name
				+ "\n  >> ��ȭ��ȣ :   " + loginM.phone + "\n  >> ������� :   " + loginM.birthday + "\n  >> ���� :   "
				+ loginM.gender + "\n  >> �ּ� :   " + loginM.address + "\n  >> ���� :   " + loginM.coin + "\n  >> �µ� :   " + loginM.grade
				+ "\n  >> �ŷ����ɿ��� :   " + possibleDeal;
		memberInfoTA.setText(str);
	}

	// ���� ���� �޼ҵ�
	public void chargeCoin(ArrayList<User> userList, Member loginM, String chargeCoin, JTextArea memberInfoTA) {
		loginM.coin += Integer.parseInt(chargeCoin);
		JOptionPane.showMessageDialog(null, chargeCoin + "������ �����Ͽ����ϴ�.");
		myInfoGUI(loginM, memberInfoTA);
	} // ���� ���� �޼ҵ�

	// ���� ȯ�� �޼ҵ�
	public void refundCoin(ArrayList<User> userList, Member loginM, String refundCoin, JTextArea memberInfoTA) {
		int howMuchCoin = Integer.parseInt(refundCoin);
		if (howMuchCoin <= loginM.coin) {
			loginM.coin -= howMuchCoin;
			JOptionPane.showMessageDialog(null, refundCoin + "������ ȯ���Ͽ����ϴ�.");
			myInfoGUI(loginM, memberInfoTA);
		} else {
			JOptionPane.showMessageDialog(null, refundCoin + "���� ���ϸ� ȯ�� �����մϴ�.");
		}
	} // ���� ȯ�� �޼ҵ�

	// ���� ���ø���Ʈ ���� (��������Ʈ ����)
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
					rowVector.add(String.valueOf(tempSB.saleBook.price) + "����");
					rowVector.add(tempSB.boardWriteUser.id);
					rowVector.add(saleBoardWriteTimeToString);
					tableDataVector.add(rowVector);
				}
			}
		}

		Vector<String> headVector = new Vector<String>();
		headVector.add("�Խñ۹�ȣ");
		headVector.add("å ����");
		headVector.add("����");
		headVector.add("�Ǹ���");
		headVector.add("�ۼ��ð�");

		table.setModel(new DefaultTableModel(tableDataVector, headVector));
	}
	
	// ���� ���ø���Ʈ ���� (��������Ʈ ����)
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
					rowVector.add(String.valueOf(tempSB.saleBook.price) + "����");
					rowVector.add(tempSB.boardWriteUser.id);
					rowVector.add(saleBoardWriteTimeToString);
					tableDataVector.add(rowVector);
				}

				Vector<String> headVector = new Vector<String>();
				headVector.add("�Խñ۹�ȣ");
				headVector.add("å ����");
				headVector.add("����");
				headVector.add("�Ǹ���");
				headVector.add("�ۼ��ð�");

				table.setModel(new DefaultTableModel(tableDataVector, headVector));
			}
			
		}

	// ���� ���Ÿ���Ʈ ����
	public void showBuyListGUI(ArrayList<User> userList, JTable table) {
		Member loginM = findLoginMember(userList);
		ArrayList<Deal> myBuyList = new ArrayList<>();
		if (loginM != null) {
			myBuyList = loginM.buyList;
			Vector<String> headVector = new Vector<String>();
			headVector.add("�ŷ���ȣ");
			headVector.add("å ����");
			headVector.add("����");
			headVector.add("�Ǹ���");
			headVector.add("�ŷ��ð�");
			Vector<Vector> tableDataVector = new Vector<Vector>();
			for (int i = 0; i < myBuyList.size(); i++) {
				Deal tempD = myBuyList.get(i);
				String saleBoardWriteTimeToString = timeToString(tempD.dealTime);
				Vector<String> rowVector = new Vector<String>();
				if(tempD.buyUserSatification!=0){
					rowVector.add(String.valueOf(tempD.dealNo)+" (�򰡿Ϸ�)");
				}else{
					rowVector.add(String.valueOf(tempD.dealNo));
				}
				rowVector.add(tempD.saleBoard.saleBook.bookName);
				rowVector.add(String.valueOf(tempD.saleBoard.saleBook.price) + "����");
				rowVector.add(tempD.sellMember.id);
				rowVector.add(saleBoardWriteTimeToString);
				tableDataVector.add(rowVector);
			}
			table.setModel(new DefaultTableModel(tableDataVector, headVector));
		} else {
			return;
		}
	}

	// �ǸŸ���Ʈ ����
	public void showSellListGUI(ArrayList<User> userList, JTable table) {
		Member loginM = findLoginMember(userList);
		ArrayList<Deal> mySellList = new ArrayList<>();
		if (loginM != null) {

			mySellList = loginM.sellList;
			Vector<String> headVector = new Vector<String>();
			headVector.add("�ŷ���ȣ");
			headVector.add("å ����");
			headVector.add("����");
			headVector.add("������");
			headVector.add("�ŷ��ð�");
			Vector<Vector> tableDataVector = new Vector<Vector>();
			for (int i = 0; i < mySellList.size(); i++) {
				Deal tempD = mySellList.get(i);
				String saleBoardWriteTimeToString = timeToString(tempD.dealTime);
				Vector<String> rowVector = new Vector<String>();
				if(tempD.sellUserSatification!=0){
					rowVector.add(String.valueOf(tempD.dealNo)+" (�򰡿Ϸ�)");
				}else{
					rowVector.add(String.valueOf(tempD.dealNo));
				}
				rowVector.add(tempD.saleBoard.saleBook.bookName);
				rowVector.add(String.valueOf(tempD.saleBoard.saleBook.price) + "����");
				rowVector.add(tempD.buyMember.id);
				rowVector.add(saleBoardWriteTimeToString);
				tableDataVector.add(rowVector);
			}
			table.setModel(new DefaultTableModel(tableDataVector, headVector));
		}
	}

	// Calendar�� ����Ͻú��ʷ� ��ȯ �޼ҵ�
	public String timeToString(Calendar time) {
//		String timeToString = (time.get(Calendar.YEAR)) + "�� " + (time.get(Calendar.MONTH) + 1) + "�� "
//				+ (time.get(Calendar.DAY_OF_MONTH)) + "�� " + (time.get(Calendar.HOUR_OF_DAY)) + "�� "
//				+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
		String timeToString = (time.get(Calendar.MONTH) + 1) + "�� "
				+ (time.get(Calendar.DAY_OF_MONTH)) + "�� " + (time.get(Calendar.HOUR_OF_DAY)) + "�� "
				+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
		return timeToString;
	}

	// �Ǹ����� ��ǰ���� �޼ҵ�
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
						rowVector.add(String.valueOf(tempSB.saleBook.price) + "����");
						rowVector.add(saleBoardWriteTimeToString);
						tableDataVector.add(rowVector);
					}
				}
			}

			Vector<String> headVector = new Vector<String>();
			headVector.add("�Խñ۹�ȣ");
			headVector.add("å ����");
			headVector.add("����");
			headVector.add("�ۼ��ð�");

			table.setModel(new DefaultTableModel(tableDataVector, headVector));

		}
	}
	
	// ������ ���ϱ�
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
			JOptionPane.showMessageDialog(null, "�ŷ���ȣ�� �߸� �Է��Ͽ����ϴ�.");
			return false;
		}
		if(tempD.sellUserSatification==0){
			tempD.sellUserSatification = Integer.parseInt(point);
			Member buyM = tempD.buyMember;
			int score = buyM.grade*buyM.gradeCount+Integer.parseInt(point);
			buyM.gradeCount++;
			buyM.grade = score/buyM.gradeCount;
			tempD.sellUserSatification = Integer.parseInt(point);
			JOptionPane.showMessageDialog(null, "�� �ŷ��� "+Integer.parseInt(point)+"������ ���Ͽ����ϴ�.");
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "�̹� ���Ͽ����ϴ�.");
			return false;
		}
	}
	
	// �Ǹ��� ���ϱ�
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
			JOptionPane.showMessageDialog(null, "�ŷ���ȣ�� �߸� �Է��Ͽ����ϴ�.");
			return false;
		}
		if(tempD.buyUserSatification==0){
			tempD.buyUserSatification = Integer.parseInt(point);
			Member sellM = tempD.sellMember;
			int score = sellM.grade*sellM.gradeCount+Integer.parseInt(point);
			sellM.gradeCount++;
			sellM.grade = score/sellM.gradeCount;
			tempD.buyUserSatification = Integer.parseInt(point);
			JOptionPane.showMessageDialog(null, "�� �ŷ��� "+Integer.parseInt(point)+"������ ���Ͽ����ϴ�.");
			return true;
		}else{
			JOptionPane.showMessageDialog(null, "�̹� ���Ͽ����ϴ�.");
			return false;
		}
	}

	// �ֿܼ� ���� �������� �޼ҵ�
	// public void myInfo(ArrayList<User> userList, int loginMemberNo) {
	// Admin admin = new Admin();
	// Member loginM = admin.findMemberByUserNo(userList, loginMemberNo);
	// pageNamePrint("���� ��������");
	// System.out.print("<Member> ");
	// System.out.println(">> �����ȣ: " + loginM.userNo);
	// System.out.print("<Member> ");
	// System.out.println(">> ���̵�: " + loginM.id);
	// System.out.print("<Member> ");
	// System.out.println(">> �̸�: " + loginM.name);
	// System.out.print("<Member> ");
	// System.out.println(">> ��ȭ��ȣ: " + loginM.phone);
	// System.out.print("<Member> ");
	// System.out.println(">> �������: " + loginM.birthday);
	// System.out.print("<Member> ");
	// System.out.println(">> ����: " + loginM.gender);
	// System.out.print("<Member> ");
	// System.out.println(">> �ּ�: " + loginM.address);
	// System.out.print("<Member> ");
	// System.out.println(">> ����: " + loginM.coin);
	// if (loginM.couponList.size() == 0) {
	// System.out.print("<Member> ");
	// System.out.println(">> ����: ������ �����ϴ�.");
	// } else {
	// System.out.print("<Member> ");
	// System.out.print(">> ����: ");
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
	// System.out.println(">> ����: �򰡹��� ����");
	// } else {
	// System.out.print("<Member> ");
	// System.out.println(">> ����: " + loginM.grade);
	// }
	//
	// System.out.println();
	//// Scanner sc = new Scanner(System.in);
	//// System.out.print("<Member> ");
	//// System.out.print("���������� �Ͻðڽ��ϱ�? (0: ������õ, 1: ����ȯ��, �ٸ�Ű: �ڷΰ���) : ");
	//// int select = sc.nextInt();
	//// if (select == 0) {
	//// chargeCoin(userList, loginM);
	//// } else if (select == 1) {
	//// refundCoin(userList, loginM);
	//// } else {
	//// System.out.print("<Member> ");
	//// System.out.println("�ڷΰ��⸦ �����̽��ϴ�.");
	//// System.out.println();
	//// return;
	//// }
	// } // ���� ���� ���� �޼ҵ�

	// �ֿܼ� ��ǰ ��Ϻ��� �޼ҵ�
	// public void showSaleBoardList(ArrayList<User> userList, ArrayList<Board>
	// boardList, ArrayList<Book> bookList,
	// ArrayList<Deal> dealList) {
	// Admin admin = new Admin();
	// Scanner sc = new Scanner(System.in);
	// SaleBoard tempSB = null;
	// Book tempB = null;
	// Member tempM = null;
	// pageNamePrint("��ǰ �Խñ� ���");
	// for (int i = 0; i < boardList.size(); i++) {
	// if (boardList.get(i) instanceof SaleBoard) {
	// tempSB = (SaleBoard) boardList.get(i);
	// tempB = tempSB.saleBook;
	// tempM = (Member) tempSB.boardWriteUser;
	// String saleBoardWriteTimeToString = timeToString(tempSB.boardWriteTime);
	// String saleBoardInfo = ">> �Խñ� ��ȣ: " + tempSB.boardNo + " / å ����: " +
	// tempB.bookName + " / ����: "
	// + tempB.price + " / �Ǹ���: " + tempM.id + " / �ۼ��ð�: " +
	// saleBoardWriteTimeToString;
	// if (!tempM.isPossibleDeal) {
	// saleBoardInfo = saleBoardInfo + " / ���źҰ� (" + tempM.id + " �ŷ�����)";
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
	// System.out.print("Ȯ���Ͻ� ��ǰ�Խñ��� ��ȣ�� �Է����ּ���(�ٸ�Ű: �ڷΰ���) : ");
	// int boardNo = sc.nextInt();
	// this.showSaleBoardDetail(userList, boardList, bookList, dealList,
	// boardNo);
	// } // ��ǰ��� ���� �޼ҵ�

	// �ֿܼ� ��ǰ �󼼺��� �޼ҵ�
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
	// (saleBoardWriteTime.get(Calendar.YEAR)) + "�� "
	// + (saleBoardWriteTime.get(Calendar.MONTH) + 1) + "�� "
	// + (saleBoardWriteTime.get(Calendar.DAY_OF_MONTH)) + "�� "
	// + (saleBoardWriteTime.get(Calendar.HOUR_OF_DAY)) + "�� "
	// + (saleBoardWriteTime.get(Calendar.MINUTE)) + "�� " +
	// (saleBoardWriteTime.get(Calendar.SECOND))
	// + "��";
	// if (!tempSB.isSale && tempM.userNo != MainFrame.LOGIN_STATE[1]) {
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.println("�ߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡ�");
	// System.out.print("<Member> ");
	// System.out.println("��ǰ �Խñ� �󼼺��� �������Դϴ�.");
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.println(">> �Խñ� ��ȣ: " + tempSB.boardNo);
	// System.out.print("<Member> ");
	// System.out.println(">> �ۼ���: " + tempM.id);
	// System.out.print("<Member> ");
	// System.out.println(">> �ۼ��ð�: " + saleBoardWriteTimeToString);
	// System.out.print("<Member> ");
	// System.out.println(">> å ����: " + tempB.bookName);
	// System.out.print("<Member> ");
	// System.out.println(">> ������: " + tempB.author);
	// System.out.print("<Member> ");
	// System.out.println(">> ����: " + tempB.price);
	// System.out.print("<Member> ");
	// System.out.println(">> å �Ұ�: " + tempSB.boardContent);
	// if (tempB.bookState == 1) {
	// System.out.print("<Member> ");
	// System.out.println(">> å �� ����: ���� ��å");
	// } else if (tempB.bookState == 2) {
	// System.out.print("<Member> ");
	// System.out.println(">> å �� ����: å�� �����ְų� ���� �׾����ֽ��ϴ�");
	// } else if (tempB.bookState == 3) {
	// System.out.print("<Member> ");
	// System.out.println(">> å �� ����: å�� ���� ������ �ֽ��ϴ�.");
	// }
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.print("������ �Ͻðڽ��ϱ�? (0: ��ǰ����, 1: ���ø���Ʈ�� �߰�, �ٸ�Ű: �ڷΰ���) : ");
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
	// System.out.println("�ڷΰ��⸦ �����̽��ϴ�.");
	// System.out.println();
	// break;
	// }
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("�ڷΰ��⸦ �����̽��ϴ�.");
	// System.out.println();
	// break;
	// }
	// } // end while
	// } // ��ǰ �󼼺��� �޼ҵ�

	// �ֿܼ� ��ǰ���� �޼ҵ�
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
	// System.out.println("��ǰ���Ÿ� �����մϴ�.");
	// while (true) {
	// System.out.print("<Member> ");
	// System.out.print("��й�ȣ�� �Է����ּ��� (���Ÿ� ������ �����ø� '-1'�� �����ּ���) : ");
	// String tempPass = sc.nextLine();
	// if (tempPass.equals("-1")) {
	// System.out.print("<Member> ");
	// System.out.println("��ǰ���Ÿ� ����Ͽ����ϴ�.");
	// System.out.println();
	// break;
	// }
	// if (tempPass.equals(loginM.password)) {
	// if (loginM.coin >= tempB.price) {
	// System.out.print("<Member> ");
	// System.out.println("���� �������Դϴ�.");
	// // ~~~~~~~~~~~~~~~~~~~~~~~ �ε��� ������ ����
	// System.out.print("<Member> ");
	// for (int i = 0; i < 30; i++) {
	// try {
	// Thread.sleep(100);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// System.out.print("��");
	// }
	// System.out.println();
	// // ~~~~~~~~~~~~~~~~~~~~~~~ ������ ����
	// loginM.coin -= tempB.price;
	// tempDeal = new Deal(dealList.size(), tempSB, loginM, sellM);
	// dealList.add(tempDeal); // �ŷ�����Ʈ�� �ŷ���ü �߰�
	// tempSB.isSale = true;// å �ǸſϷ� ����
	// sellM.sellList.add(tempDeal); // �Ǹ��� �ǸŸ���Ʈ�� �ŷ���ü �߰�
	// loginM.buyList.add(tempDeal); // ������ ���Ÿ���Ʈ�� ���Ű�ü �߰�
	// System.out.print("<Member> ");
	// System.out.println("��ǰ���Ÿ� �Ϸ��Ͽ����ϴ�.");
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
	// }.start(); // wishList ����ȭ���ִ� ������
	// break;
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("������ �����մϴ�. ������ �� �������ּ���.");
	// break;
	// }
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
	// }
	// }
	// } else if (loginM.isPossibleDeal && !sellM.isPossibleDeal) {
	// System.out.print("<Member> ");
	// System.out.println("�Ǹ��ڰ� �����ڿ� ���� �ŷ������� ���Ͽ����ϴ�. �ٸ� ��ǰ�� �������ּ���.");
	// this.showSaleBoardList(userList, boardList, bookList, dealList);
	// } else if (!loginM.isPossibleDeal && sellM.isPossibleDeal) {
	// System.out.print("<Member> ");
	// System.out.println("�����ڿ� ���� �ŷ������� ���Ͽ����ϴ�. �����ڿ��� �������ּ���");
	// } else if (!loginM.isPossibleDeal && !sellM.isPossibleDeal) {
	// System.out.print("<Member> ");
	// System.out.println("�����ڴ԰� �Ǹ��ڰ� �����ڿ� ���� �ŷ������� ���Ͽ����ϴ�. �ٸ� ��ǰ�� �������ּ���.");
	// }
	// } // ��ǰ ���� �޼ҵ�

	// �ֿܼ� ��ǰ��� �޼ҵ�
	// public void registerBook(ArrayList<User> userList, ArrayList<Board>
	// boardList, ArrayList<Book> bookList) {
	// Scanner sc = new Scanner(System.in);
	// pageNamePrint("��ǰ���");
	// Member loginM = findLoginMember(userList);
	// System.out.print("<Member> ");
	// System.out.println("å�� ������ �Է����ּ���.");
	// System.out.print("<Member> ");
	// System.out.println(">> å �̸�: ");
	// String tempBookName = sc.nextLine();
	// System.out.print("<Member> ");
	// System.out.println(">> ������: ");
	// String tempAuthor = sc.nextLine();
	// System.out.print("<Member> ");
	// System.out.println(">> �帣: ");
	// String tempGenre = sc.nextLine();
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.print(">> ����: ");
	// int tempPrice = sc.nextInt();
	// System.out.println();
	// int tempBookState;
	// while (true) {
	// System.out.print("<Member> ");
	// System.out.print(">> å�� ���� (1: ���� ��å, 2: å�� �����ְų� ���� �׾��� ���, 3: å ������ ������
	// ���) : ");
	// tempBookState = sc.nextInt();
	// if (tempBookState == 1 || tempBookState == 2 || tempBookState == 3) {
	// break;
	// } else {
	// System.out.print("<Member> ");
	// System.out.println("[error] 1~3�� ���� �Է����ּ���.");
	// }
	// }
	// System.out.println();
	// Book registerBook = new Book(bookList.size(), tempBookName, tempAuthor,
	// tempGenre, tempPrice, tempBookState);
	// System.out.print("<Member> ");
	// System.out.print(">> å�� �Ұ����ּ���: ");
	// sc = new Scanner(System.in);
	// String tempContent = sc.nextLine();
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.print("å�� ����Ͻðڽ��ϱ�? (0: ��, �ٸ�Ű: �ƴϿ�) : ");
	// int select = sc.nextInt();
	// switch (select) {
	// case 0:
	// SaleBoard tempSB = new SaleBoard(boardList.size(), tempContent, loginM,
	// registerBook);
	// bookList.add(registerBook);
	// boardList.add(tempSB);
	// System.out.print("<Member> ");
	// System.out.print("å ����� �Ϸ��Ͽ����ϴ�.");
	// System.out.println();
	// break;
	// default:
	// System.out.print("<Member> ");
	// System.out.print("å ����� ����Ͽ����ϴ�.");
	// System.out.println();
	// break;
	// }
	//
	// } // ��ǰ�� ����ϴ� �޼ҵ�

	// �ֿܼ� ���� �Ǹ����λ�ǰ��� ���� �޼ҵ�
	// public void showMySellingList(ArrayList<User> userList, ArrayList<Board>
	// boardList) {
	// pageNamePrint("���� �Ǹ����� ��ǰ����Ʈ");
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
	// (saleBoardWriteTime.get(Calendar.YEAR)) + "�� "
	// + (saleBoardWriteTime.get(Calendar.MONTH) + 1) + "�� "
	// + (saleBoardWriteTime.get(Calendar.DAY_OF_MONTH)) + "�� "
	// + (saleBoardWriteTime.get(Calendar.HOUR_OF_DAY)) + "�� "
	// + (saleBoardWriteTime.get(Calendar.MINUTE)) + "�� "
	// + (saleBoardWriteTime.get(Calendar.SECOND)) + "��";
	// String saleBoardInfo = ">> �Խñ� ��ȣ: " + tempSB.boardNo + " / å ����: " +
	// tempB.bookName + " / ����: "
	// + tempB.price + " / �Ǹ���: " + tempM.id + " / �ۼ��ð�: " +
	// saleBoardWriteTimeToString;
	// if (!tempM.isPossibleDeal) {
	// saleBoardInfo = saleBoardInfo + " / ���źҰ� (" + tempM.id + " �ŷ�����)";
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
	// } // �Ǹ����� ��ǰ ����Ʈ

	// �ֿܼ� ���� �ǸŸ�� ���� �޼ҵ�
	// public void showMySellList(ArrayList<User> userList) {
	// pageNamePrint("���� �ǸŸ���Ʈ");
	// Member loginM = findLoginMember(userList);
	// ArrayList<Deal> sellList = loginM.sellList;
	// for (int i = 0; i < sellList.size(); i++) {
	// Deal tempD = (Deal) sellList.get(i);
	// Calendar dealTime = tempD.dealTime;
	// String dealTimeToString = (dealTime.get(Calendar.YEAR)) + "�� " +
	// (dealTime.get(Calendar.MONTH) + 1) + "�� "
	// + (dealTime.get(Calendar.DAY_OF_MONTH)) + "�� " +
	// (dealTime.get(Calendar.HOUR_OF_DAY)) + "�� "
	// + (dealTime.get(Calendar.MINUTE)) + "�� " +
	// (dealTime.get(Calendar.SECOND)) + "��";
	// String str = "�ŷ� ��ȣ: " + tempD.dealNo + " / �ŷ��Ͻ�: " + dealTimeToString +
	// " / ������: " + tempD.buyMember.id
	// + " / å �̸�: " + tempD.saleBoard.saleBook.bookName + " / ����: " +
	// tempD.saleBoard.saleBook.price;
	// System.out.print("<Member> ");
	// System.out.println(str);
	// }
	// } // �� �ǸŸ���Ʈ ����

	// �ֿܼ� ���� ���Ÿ�� ���� �޼ҵ�
	// public void showMyBuyList(ArrayList<User> userList) {
	// pageNamePrint("���� ���Ÿ���Ʈ");
	// Member loginM = findLoginMember(userList);
	// ArrayList<Deal> buyList = loginM.buyList;
	// for (int i = 0; i < buyList.size(); i++) {
	// Deal tempD = (Deal) buyList.get(i);
	// Calendar dealTime = tempD.dealTime;
	// String dealTimeToString = (dealTime.get(Calendar.YEAR)) + "�� " +
	// (dealTime.get(Calendar.MONTH) + 1) + "�� "
	// + (dealTime.get(Calendar.DAY_OF_MONTH)) + "�� " +
	// (dealTime.get(Calendar.HOUR_OF_DAY)) + "�� "
	// + (dealTime.get(Calendar.MINUTE)) + "�� " +
	// (dealTime.get(Calendar.SECOND)) + "��";
	// String str = "�ŷ� ��ȣ: " + tempD.dealNo + " / �ŷ��Ͻ�: " + dealTimeToString +
	// " / ������: " + tempD.buyMember.id
	// + " / å �̸�: " + tempD.saleBoard.saleBook.bookName + " / ����: " +
	// tempD.saleBoard.saleBook.price;
	// System.out.print("<Member> ");
	// System.out.println(str);
	// }
	// } // �� ���Ÿ���Ʈ ����

	// �ֿܼ� ���� ���ø���Ʈ ���� �޼ҵ�
	// public void showMyWishList(ArrayList<User> userList) {
	// pageNamePrint("���� ���ø���Ʈ");
	// Member loginM = findLoginMember(userList);
	// ArrayList<SaleBoard> wishList = loginM.wishList;
	// for (int i = 0; i < wishList.size(); i++) {
	// SaleBoard tempSB = wishList.get(i);
	// String str = "�Խñ� ��ȣ: " + tempSB.boardNo + " / å ���� : " +
	// tempSB.saleBook.bookName + " / ����: "
	// + tempSB.saleBook.price + " / �Ǹ���: " + tempSB.boardWriteUser.id;
	// System.out.print("<Member> ");
	// System.out.println(str);
	// }
	// } // �� ���ø���Ʈ ����

	// �ֿܼ� ������ �̸� ��� �޼ҵ�
	// public void pageNamePrint(String pageName) {
	// System.out.println();
	// System.out.println();
	// System.out.print("<Member> ");
	// System.out.println("�ߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡ�");
	// System.out.print("<Member> ");
	// System.out.println(pageName + " �������Դϴ�.");
	// System.out.println();
	// } // ������ �̸� ���

	// ���� ���ø���Ʈ ���� (�α��θ�� ����)
	/*
	 * private void showWishListGUI(Member loginM, JTable table) {
	 * ArrayList<SaleBoard> tempWishList = new ArrayList<>(); tempWishList =
	 * loginM.wishList; Vector<Vector> tableDataVector = new Vector<Vector>();
	 * for (int i = 0; i < tempWishList.size(); i++) { SaleBoard tempSB =
	 * tempWishList.get(i); String saleBoardWriteTimeToString =
	 * timeToString(tempSB.boardWriteTime); Vector<String> rowVector = new
	 * Vector<String>(); rowVector.add(String.valueOf(tempSB.boardNo));
	 * rowVector.add(tempSB.saleBook.bookName);
	 * rowVector.add(String.valueOf(tempSB.saleBook.price) + "����");
	 * rowVector.add(tempSB.boardWriteUser.id);
	 * rowVector.add(saleBoardWriteTimeToString);
	 * tableDataVector.add(rowVector); } Vector<String> headVector = new
	 * Vector<String>(); headVector.add("�Խñ۹�ȣ"); headVector.add("å ����");
	 * headVector.add("����"); headVector.add("�Ǹ���"); headVector.add("�ۼ��ð�");
	 * 
	 * table.setModel(new DefaultTableModel(tableDataVector, headVector)); }
	 */

} // Member class
