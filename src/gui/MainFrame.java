package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import board.Board;
import board.SaleBoard;
import etc.Book;
import etc.Deal;
import etc.Message;
import javazoom.jl.player.Player;
import system.Data;
import system.Music;
import user.Admin;
import user.Member;
import user.User;

public class MainFrame extends JFrame{
	
    public static int[] LOGIN_STATE = new int[2];
    /* LOGIN_STATE
     * 인덱스 0 --> 0: 로그인 실패, 1: 관리자 로그인, 2: 멤버 로그인
     * 인덱스 1 --> 로그인 객체 번호 
     * */
	
	// 메소드 필요 객체 참조변수선언
	User user;
	Member member;
	Admin admin;
	
	ImageIcon backImage = new ImageIcon(MainFrame.class.getResource("..\\image\\bookMain.jpg")); // 배경이미지를 위한 객체
	
	JFrame frame;
	
	public JPanel mainPanel;
	private CLP cLP;
	private JPanel logoutMenuBarPanel;
	private JPanel adminMenuBarPanel;
	private JPanel memberMenuBarPanel;
	
	public JButton loginBtn;
	public JButton findIdPasswordBtn;
	public JButton menuJoinBtn;
	
	private JButton adminShowMemberListBtn;
	private JButton adminLogout;
	private JButton adminShowDealListBtn;
	private JButton adminMessageBtn;
	
	private JLabel myInfoLabel;
	private JButton memberShowBookListBtn;
	private JButton memberRegisterBookBtn;
	private JButton memberShowSellingListBtn;
	private JButton memberShowBuyListBtn;
	private JButton memberShowSellListBtn;
	private JButton memberShowWishListBtn;
	private JButton memberEventBtn;
	private JButton memberMessageBtn;
	private JButton memberMyInfoBtn;
	private JButton memberLogoutBtn;
	
	boolean firstClickMemberListCheck=true; // 첫번째 회원목록 클릭인지 확인하는 변수
	boolean firstClickSaleBoardListCheck=true; // 첫번째 상품목록 클릭인지 확인하는 변수
	boolean firstClickDealList=true; // 첫번째 거래목록 클릭인지 확인하는 변수
	
	String welcomLoginMember; // 로그인멤버 환영인삿말
	
	int autoRegisterBookTime; // 자동 상품 등록 시간
	int autoBuyBookTime; // 자동 상품 구매 시간
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/* LOGIN_STATE
		 * 인덱스 0 --> 0: 로그인 실패, 1: 관리자 로그인, 2: 멤버 로그인
		 * 인덱스 1 --> 로그인 객체 번호 
		 * */
//		Scanner sc = new Scanner(System.in);
//		boolean isReAction=false;
//		System.out.println("모두의 책장을 시작합니다.");
//		do{
//			System.out.print("어떤 모드로 시작하시겠습니까?  (0:시연모드, 1:관리자로그인 테스트 모드, 2:멤버로그인(member2)테스트모드)  :  ");
//			int select = sc.nextInt();
//			switch(select){
//			case 0: // 로그아웃 상태
//				LOGIN_STATE[0]=0;
//				LOGIN_STATE[1]=-1;
//				isReAction=false;
//				break;
//			case 1: // 관리자 로그인
//				LOGIN_STATE[0]=1;
//				LOGIN_STATE[1]=0;
//				isReAction=false;
//				break;
//			case 2: // member1 로그인
//				LOGIN_STATE[0]=2;
//				LOGIN_STATE[1]=1;
//				isReAction=false;
//				break;
//			default:
//				isReAction=true;
//				break;
//			}
//		}while(isReAction);
		LOGIN_STATE[0]=0;
		LOGIN_STATE[1]=-1;
		
		// 데이터 세팅
		Data data = new Data();
        
    	new MainFrame(data,data.userList,data.boardList,data.bookList,data.dealList);
    	
    	
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame window = new MainFrame(data.userList,data.boardList,data.bookList,data.dealList);
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
	}

	/**
	 * Create the application.
	 */
	public MainFrame(Data data, ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, ArrayList<Deal> dealList) {
		
		initialize(data, userList,boardList,bookList,dealList);
		
		
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Data data, ArrayList<User> userList, ArrayList<Board> boardList, ArrayList<Book> bookList, ArrayList<Deal> dealList) {
		
		// 메소드 필요 객체 생성
		user = new User();
		member = new Member();
		admin = new Admin();
		
		
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ frame 시작 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★//
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setResizable(false); // 창의 크기조절 막음
		frame.setLocationRelativeTo(null); // 가운데 창이 나타나도록
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("모두의 책장");
		
		


		
		// ---------------------------------------------- mainPanel 시작 ----------------------------------------------//		
		mainPanel = new JPanel(){
	          public void paintComponent(Graphics g) {
		          g.drawImage(backImage.getImage(), 0, 0, null);
		          setOpaque(false); //그림을 표시하게 설정,투명하게 조절
		          super.paintComponent(g);
	          }
		};
		mainPanel.setBounds(0, 0, 1274, 685);
        mainPanel.setLayout(null);
        frame.getContentPane().setLayout(null); // ContentPane의 레이아웃 설정
		frame.getContentPane().add(mainPanel);
		// =============================================== mainPanel 끝 ===========================================//
        
		// =============================================== cardLayoutPanel 시작 ===========================================//
		cLP = new CLP(data,userList,boardList,bookList,dealList);
		mainPanel.add(cLP); // mainPanel에 cardLayoutPanel을 추가
		// =============================================== cardLayoutPanel 끝 ===========================================//
		
		// ---------------------------------------------- logoutMenuBarPanel 시작 ----------------------------------------------//
        logoutMenuBarPanel = new JPanel();
        mainPanel.add(logoutMenuBarPanel);
		logoutMenuBarPanel.setBackground(new Color(255, 0, 0, 0));
        logoutMenuBarPanel.setBounds(0, 0, 1180, 48);
		if(LOGIN_STATE[0]==0){
			logoutMenuBarPanel.setVisible(true);
        }else{
        	logoutMenuBarPanel.setVisible(false);
        }
        logoutMenuBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JLabel idLabel = new JLabel("id");
        logoutMenuBarPanel.add(idLabel);
        JTextField idTF = new JTextField(10);
        logoutMenuBarPanel.add(idTF);
        JLabel passwordLabel = new JLabel("password");
        logoutMenuBarPanel.add(passwordLabel);
        JPasswordField passwordF = new JPasswordField(10);
        logoutMenuBarPanel.add(passwordF);
                
        loginBtn = new JButton("로그인");
        logoutMenuBarPanel.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		User loginUser = user.login(userList, idTF.getText(), passwordF.getText());
        		if(loginUser==null){
        			JOptionPane.showMessageDialog(null,"로그인 실패");
        		}else if(loginUser instanceof Admin){
        			// 관리자 로그인시 효과음 쓰레드
        			new Thread(){
        				@Override
        				public void run(){
        					try {
        						System.out.println("<<MainFrame>> 관리자 로그인 효과음 쓰레드 실행");
        						File file = new File(Music.class.getResource("..\\music\\login.mp3").toURI());
        						FileInputStream fis = new FileInputStream(file);
        						BufferedInputStream bis = new BufferedInputStream(fis);
        						Player player = new Player(bis);
        						player.play();
							} catch (Exception e2) { }
        				}
        			}.start();
        			JOptionPane.showMessageDialog(null,"관리자로 로그인\n"+loginUser.id+"님 로그인");
        			LOGIN_STATE[0] = 1;
        			LOGIN_STATE[1] = loginUser.userNo;
        			idTF.setText("");
        			passwordF.setText("");
        			cLP.setVisible(false);
        			logoutMenuBarPanel.setVisible(false);
        			adminMenuBarPanel.setVisible(true);
        		}else if(loginUser instanceof Member){
        			// 멤버 로그인시 효과음 쓰레드
        			new Thread(){
        				@Override
        				public void run(){
        					try {
        						System.out.println("<<MainFrame>> 멤버 로그인 효과음 쓰레드 실행");
        						File file = new File(Music.class.getResource("..\\music\\login.mp3").toURI());
        						FileInputStream fis = new FileInputStream(file);
        						BufferedInputStream bis = new BufferedInputStream(fis);
        						Player player = new Player(bis);
        						player.play();
							} catch (Exception e2) { }
        				}
        			}.start();
        			JOptionPane.showMessageDialog(null,loginUser.id+"님 로그인");
        			LOGIN_STATE[0] = 2;
        			LOGIN_STATE[1] = loginUser.userNo;
        			idTF.setText("");
        			passwordF.setText("");
        			cLP.setVisible(false);
        			logoutMenuBarPanel.setVisible(false);
        			memberMenuBarPanel.setVisible(true);
        			welcomLoginMember = loginUser.id+"님 반갑습니다          ";
        			myInfoLabel.setText(welcomLoginMember);
        			new Thread(){
        				public void run(){
        					String content = loginUser.id+"님의 방문에 감사드립니다.\n - 관리자  -";
        					Message tempMS = new Message(data.messageList.size(), userList.get(0), loginUser, content);
        					data.messageList.add(tempMS);
        					loginUser.showSendMessageList(data, cLP.messagePanel.sendMessageTable);
        				}
        			}.start();
        		}
        	}
        }); // 로그인 버튼 클릭 이벤트
        
        menuJoinBtn = new JButton("회원가입");
        logoutMenuBarPanel.add(menuJoinBtn);
        menuJoinBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "join");
        		cLP.joinPanel.threadCheck = true;
        	}
        }); // 회원가입 클릭 이벤트
        
        findIdPasswordBtn = new JButton("아이디/비밀번호 찾기");
        logoutMenuBarPanel.add(findIdPasswordBtn);
        findIdPasswordBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			cLP.setVisible(true);
			cLP.cL.show(cLP, "findIdPass");
			
			}
		}); // 아이디/비밀번호 찾기 클릭 이벤트
		// =============================================== logoutMenuBarPanel 끝 ===========================================/*/
        
		// ---------------------------------------------- adminLoginMenuBarPanel 시작 ----------------------------------------------//
		adminMenuBarPanel = new JPanel();
        mainPanel.add(adminMenuBarPanel);
        adminMenuBarPanel.setBackground(new Color(255, 0, 0, 0));
        adminMenuBarPanel.setBounds(0, 0, 1180, 48);
        if(LOGIN_STATE[0]==1){
        	adminMenuBarPanel.setVisible(true);
        }else{
        	adminMenuBarPanel.setVisible(false);
        }
        adminMenuBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        adminShowMemberListBtn = new JButton("회원목록");
        adminShowMemberListBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		admin.showMemberListByGUI(userList, cLP.adminMemberListPanel.table);
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "adminMemberList");
				if(firstClickMemberListCheck){
					new Thread(){
						@Override
						public void run(){
							System.out.println("<<MainFrame>> 자동 회원가입 쓰레드 실행");
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Member member = new Member();
							member.join(userList, userList.size(), "하늘나라", "123123", "김하늘", "01000000000", "19931216", "여자", "부산 해운대",cLP.adminMemberListPanel);
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							member.join(userList, userList.size(), "달나라", "123123", "김달", "01000000001", "19930205", "남자", "인천 구월동",cLP.adminMemberListPanel);
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							member.join(userList, userList.size(), "햇님나라", "123123", "김햇님", "01000000002", "20030715", "남자", "의왕 오전동",cLP.adminMemberListPanel);
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							member.join(userList, userList.size(), "지옥나라", "123123", "김지옥", "01000000003", "19440821", "남자", "경북 상주",cLP.adminMemberListPanel);
							System.out.println("<<MainFrame>> 자동 회원가입 쓰레드 종료");
						}
					}.start();
					firstClickMemberListCheck=false;
				}
        	}
        }); // 회원목록 보기 버튼 클릭 이벤트
        adminMenuBarPanel.add(adminShowMemberListBtn);
        
        adminShowDealListBtn = new JButton("거래목록");
        adminShowDealListBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		admin.showDealListByGUI(dealList, cLP.adminDealListPanel.table);
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "adminDealList");
        	}
        });
        adminMenuBarPanel.add(adminShowDealListBtn);
        
        adminMessageBtn = new JButton("메시지");
        adminMessageBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		user.showReceiveMessageList(data, cLP.messagePanel.receiveMessageTable);
        		user.showSendMessageList(data, cLP.messagePanel.sendMessageTable);
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "message");
        	}
        });
        adminMenuBarPanel.add(adminMessageBtn);
        
        adminLogout = new JButton("로그아웃");
        adminLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//로그아웃시 메시지 패널 초기화
				cLP.messagePanel.inputMessageNoTF.setText("");
				cLP.messagePanel.messageContentTA.setText("");
				cLP.messagePanel.inputIdTF.setText("");
				cLP.messagePanel.messageWriteTA.setText("");
				cLP.messagePanel.messageSendBtn.setEnabled(false);
//				cLP.messagePanel.tempMessageLengthLabel.setText("0");
        		// 관리자 로그아웃시 효과음 쓰레드
    			new Thread(){
    				@Override
    				public void run(){
    					System.out.println("<<MainFrame>> 관리자 로그아웃 효과음 쓰레드 실행");
    					try {
    						File file = new File(Music.class.getResource("..\\music\\logout.mp3").toURI());
    						FileInputStream fis = new FileInputStream(file);
    						BufferedInputStream bis = new BufferedInputStream(fis);
    						Player player = new Player(bis);
    						player.play();
						} catch (Exception e2) { }
    				}
    			}.start();
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		LOGIN_STATE[0]=0;
        		LOGIN_STATE[1]=-1;
        		cLP.setVisible(false);
        		adminMenuBarPanel.setVisible(false);
        		logoutMenuBarPanel.setVisible(true);
        		
        	}
        });
        adminMenuBarPanel.add(adminLogout);
        
		// =============================================== adminLoginMenuBarPanel 끝 ===========================================/*/
        
		// ---------------------------------------------- memberLoginMenuBarPanel 시작 ----------------------------------------------//
		memberMenuBarPanel = new JPanel();
		mainPanel.add(memberMenuBarPanel);
		if(LOGIN_STATE[0]==2){
			memberMenuBarPanel.setVisible(true);
        }else{
        	memberMenuBarPanel.setVisible(false);
        }
		memberMenuBarPanel.setBackground(new Color(255, 0, 0, 0));
		memberMenuBarPanel.setBounds(0, 0, 1180, 48);
		memberMenuBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		myInfoLabel = new JLabel(welcomLoginMember);
		memberMenuBarPanel.add(myInfoLabel);
		
		 // 상품목록 버튼
		memberShowBookListBtn = new JButton("상품목록");
		memberShowBookListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					member.showSaleBoardListByGUI(userList, boardList, bookList, cLP.saleBoardListPanel.table);
				} catch (Exception e1) {
					System.out.println("[ERROR] <<MainFrame  상품목록 버튼 클릭 이벤트>>  showSaleBoardListByGUI 메소드 : "+e1.getMessage());
					e1.printStackTrace();
				} // 상품목록 테이블 동기화
				cLP.setVisible(true);
				cLP.cL.show(cLP, "saleBoardList");
//        		if(firstClickSaleBoardListCheck){
//        			// 자동 상품 등록 쓰레드
//        			new Thread(){
//            			@Override
//            			public void run(){
//            				System.out.println("<<MainFrame>>  자동 상품등록 쓰레드 실행");
//            				try {
//            					Thread.sleep(3000);
//            				} catch (InterruptedException e) { e.printStackTrace(); }
//            				Admin ad = new Admin();
//            				Member tempM = ad.findMemberByUserNo(userList, 2);
//            				Book tempB = new Book(bookList.size(),"곰돌이푸, 행복한 일은 매일 있어","곰돌이 푸(원작)","에세이",10000,2);
//            				SaleBoard tempSB = new SaleBoard(boardList.size(),"우리 같이 행복해요",tempM,tempB);
//            				bookList.add(tempSB.saleBook);
//            				boardList.add(tempSB);
//            				tempM.showSaleBoardListByGUI(userList, boardList, bookList, cLP.saleBoardListPanel.table);
//            				// 상품등록시 효과음 쓰레드
//            				new Thread() {
//            					@Override
//            					public void run() {
//            						System.out.println("<<Memeber>> 상품등록 효과음 쓰레드 실행");
//            						try {
//            							File file = new File(Music.class.getResource("..\\music\\registerBook.mp3").toURI());
//            							FileInputStream fis = new FileInputStream(file);
//            							BufferedInputStream bis = new BufferedInputStream(fis);
//            							Player player = new Player(bis);
//            							player.play();
//            						} catch (Exception e2) { }
//            					}
//            				}.start();
//            				System.out.println("<<MainFrame>>  자동 상품등록 쓰레드 종료");
//            			}
//            		}.start();
//            		firstClickSaleBoardListCheck=false;
//        		}
			}
		});
		memberMenuBarPanel.add(memberShowBookListBtn);
		
		memberRegisterBookBtn = new JButton("상품등록");
		memberRegisterBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLP.setVisible(true);
				cLP.cL.show(cLP, "registerBook");
			}
		});
		memberMenuBarPanel.add(memberRegisterBookBtn);
		
		// 판매중인 상품 버튼
		memberShowSellingListBtn = new JButton("판매중인 상품");
		memberShowSellingListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showMySellingListGUI(userList, boardList, cLP.mySellingListPanel.table);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "mySellingList");
			}
		});
		memberMenuBarPanel.add(memberShowSellingListBtn);
		
		memberShowBuyListBtn = new JButton("구매목록");
		memberShowBuyListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showBuyListGUI(userList, cLP.myBuyListPanel.table);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "myBuyList");
			}
		});
		memberMenuBarPanel.add(memberShowBuyListBtn);
		
		memberShowSellListBtn = new JButton("판매목록");
		memberShowSellListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showSellListGUI(userList, cLP.mySellListPanel.table);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "mySellList");
			}
		});
		memberMenuBarPanel.add(memberShowSellListBtn);
		
		memberShowWishListBtn = new JButton("위시리스트");
		memberShowWishListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showWishListGUI(member.findLoginMember(userList), cLP.wishListPanel.table); // 위시리스트 테이블 동기화
				cLP.setVisible(true);
				cLP.cL.show(cLP, "wishList");
			}
		}); // 위시리스트 버튼 클릭 이벤트
		memberMenuBarPanel.add(memberShowWishListBtn);
		
		memberMessageBtn = new JButton("메시지");
		memberMessageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.showReceiveMessageList(data, cLP.messagePanel.receiveMessageTable);
        		user.showSendMessageList(data, cLP.messagePanel.sendMessageTable);
				cLP.setVisible(true);
        		cLP.cL.show(cLP, "message");
			}
		});
		memberMenuBarPanel.add(memberMessageBtn);
		
		memberEventBtn = new JButton("이벤트");
		memberEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLP.setVisible(true);
				cLP.cL.show(cLP, "event");
			}
		});
		memberMenuBarPanel.add(memberEventBtn);
		
		memberMyInfoBtn = new JButton("나의 정보");
		memberMyInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member loginM = member.findLoginMember(userList);
				member.myInfoGUI(loginM,cLP.myInfoPanel.memberInfoTA);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "myInfo");
			}
		});
		memberMenuBarPanel.add(memberMyInfoBtn);
		
		memberLogoutBtn = new JButton("로그아웃");
		memberLogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//로그아웃시 메시지 패널 초기화
				cLP.messagePanel.inputMessageNoTF.setText("");
				cLP.messagePanel.messageContentTA.setText("");
				cLP.messagePanel.inputIdTF.setText("");
				cLP.messagePanel.messageWriteTA.setText("");
				cLP.messagePanel.messageSendBtn.setEnabled(false);
//				cLP.messagePanel.tempMessageLengthLabel.setText("0");
				
				//로그아웃시 이벤트 패널 초기화
				cLP.eventPanel.iP.a.setText("?");
				cLP.eventPanel.iP.b.setText("?");
				cLP.eventPanel.iP.c.setText("?");
				cLP.eventPanel.iP.d.setText("?");
				cLP.eventPanel.iP.e.setText("?");
				cLP.eventPanel.iP.f.setText("?");
				cLP.eventPanel.iP.aTF.setText("");
				cLP.eventPanel.iP.bTF.setText("");
				cLP.eventPanel.iP.cTF.setText("");
				cLP.eventPanel.iP.dTF.setText("");
				cLP.eventPanel.iP.eTF.setText("");
				cLP.eventPanel.iP.fTF.setText("");
				cLP.eventPanel.iP.testModeCheck.setSelected(false);
				cLP.eventPanel.iP.bettingCoinTF.setText("");
				cLP.eventPanel.cGP.a.setText("?");
				cLP.eventPanel.cGP.b.setText("?");
				cLP.eventPanel.cGP.c.setText("?");
				cLP.eventPanel.cGP.d.setText("?");
				cLP.eventPanel.cGP.e.setText("?");
				cLP.eventPanel.cGP.f.setText("?");
				cLP.eventPanel.cGP.inputTF.setText("");
				cLP.eventPanel.cGP.bettingCoinTF.setText("");
				
				//로그아웃시 나의정보패널 초기화
				cLP.myInfoPanel.chargeCoinTF.setText("");
				cLP.myInfoPanel.refundCoinTF.setText("");

				//로그아웃시 상품등록패널 초기화
				cLP.memberRegisterBookPanel.bookNameTF.setText("");
				cLP.memberRegisterBookPanel.authorTF.setText("");
				cLP.memberRegisterBookPanel.genreTF.setText("");
				cLP.memberRegisterBookPanel.priceTF.setText("");
				cLP.memberRegisterBookPanel.introduceBookTA.setText("");
				
				//로그아웃시 상품목록 상세보기 초기화
				cLP.saleBoardListPanel.saleBoardDetailPanel.saleBoardInfoTA.setText("");
				cLP.saleBoardListPanel.saleBoardDetailPanel.inputBoardNoTF.setText("");
				cLP.saleBoardListPanel.saleBoardDetailPanel.saleBoardInfoTA.setText("");
				cLP.saleBoardListPanel.saleBoardDetailPanel.buyBtn.setEnabled(false);
				cLP.saleBoardListPanel.saleBoardDetailPanel.addWishBtn.setEnabled(false);
				
				//로그아웃시 위시리스트 상세보기 초기화
				cLP.wishListPanel.wishListDetailPanel.inputBoardNoTF.setText("");
				cLP.wishListPanel.wishListDetailPanel.saleBoardInfoTA.setText("");
				cLP.wishListPanel.wishListDetailPanel.buyBtn.setEnabled(false);
				
				// 멤버 로그아웃시 효과음 쓰레드
    			new Thread(){
    				@Override
    				public void run(){
    					System.out.println("<<MainFrame>> 멤버 로그아웃 효과음 쓰레드 실행");
    					try {
    						File file = new File(Music.class.getResource("..\\music\\logout.mp3").toURI());
    						FileInputStream fis = new FileInputStream(file);
    						BufferedInputStream bis = new BufferedInputStream(fis);
    						Player player = new Player(bis);
    						player.play();
						} catch (Exception e2) { }
    				}
    			}.start();
    			try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
        		LOGIN_STATE[0] = 0;
        		LOGIN_STATE[1] = -1;
        		cLP.setVisible(false);
        		memberMenuBarPanel.setVisible(false);
        		logoutMenuBarPanel.setVisible(true);
			}
		});
		memberMenuBarPanel.add(memberLogoutBtn);

		// =============================================== memberLoginMenuBarPanel 끝 ===========================================//
        
		frame.setVisible(true);
		// ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ frame 끝 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★//
		
		// 음악 재생 쓰레드
//        Music introMusic = new Music("mainMusic.mp3",true); // Music thread 객체 생성
//        introMusic.start(); // 쓰레드 시작
//        System.out.println("<<MainFrame>> 배경음악 재생 쓰레드 시작");
        
        
        // 자동 상품등록 쓰레드 실행
        new Thread(){
			public void run(){
				setName("자동 상품등록 쓰레드");
				System.out.println("<<MainFrame>> 자동 상품등록 쓰레드 실행");
				autoRegisterBookTime=8000;
//				autoRegisterBookTime=100;
				while(true){
					try { Thread.sleep(autoRegisterBookTime); } catch (InterruptedException e) { e.printStackTrace(); }
					try {
						registerBook();
					} catch (Exception e) {
						System.out.println("[ERROR] 자동상품등록쓰레드 에러다");
						System.out.println(e.getMessage());
					}
				}
			}
			
			public void registerBook(){
				Random r = new Random();
				SaleBoard tempSB;
				Book saleBook = bookList.get(r.nextInt(bookList.size()));
				String boardContent = "이 책의 이름은 "+saleBook.bookName+"이고 지은이는 "+saleBook.author+"입니다. "
						+"그리고 장르는 "+saleBook.genre+"입니다. 좋은 책입니다. 많은 관심 부탁드려요 ~ :)";
				User boardWriteUser;
				while(true){
					boardWriteUser = userList.get(r.nextInt(userList.size()));
					if(boardWriteUser.userNo!=0 && boardWriteUser.userNo!=LOGIN_STATE[1]){
						break;
					}
				}
				tempSB = new SaleBoard(boardList.size(), boardContent, boardWriteUser, saleBook);
				member.registerBookByGUI(userList, boardList, bookList, tempSB, cLP.saleBoardListPanel, cLP.mySellingListPanel);
				System.out.println("<<자동상품등록 쓰레드>>  게시글번호: "+tempSB.boardNo+" / "+tempSB.boardWriteUser.id+"(이)가 "+tempSB.saleBook.bookName+"을 자동 상품등록함. 시간 : "+timeToString(tempSB.boardWriteTime));
			}
			// Calendar를 시분초로 반환 메소드
			public String timeToString(Calendar time) {
				String timeToString = (time.get(Calendar.HOUR_OF_DAY)) + "시 "
						+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
				return timeToString;
			}
		}.start();
		
        // 자동 상품구매 쓰레드 실행
		new Thread(){
			public void run(){
				setName("자동 상품구매 쓰레드");
				System.out.println("<<MainFrame>> 자동 상품구매 쓰레드 실행");
				autoBuyBookTime=12000;
//				autoBuyBookTime=210;
				while(true){
					try { Thread.sleep(autoBuyBookTime); } catch (InterruptedException e) { e.printStackTrace(); }
					try {
						buyBook();
					} catch (Exception e) {
						System.out.println("[ERROR] 자동상품구매쓰레드 에러다");
						System.out.println(e.getMessage());
					}
				}
			}
			public void buyBook(){
				Random r = new Random();
				SaleBoard tempSB=null;
				Book tempB=null;
				Member sellM=null;
				while(true){
					Board board = boardList.get(r.nextInt(boardList.size())); 
					if( board instanceof SaleBoard){
						tempSB = (SaleBoard)boardList.get(r.nextInt(boardList.size()));
						if(!tempSB.isSale){
							sellM=(Member)tempSB.boardWriteUser;
							tempB = tempSB.saleBook;
							if(sellM.isPossibleDeal){
								break;
							}
						}
					}
				}
				
				Member buyM=null;
				while(true){
					User user=userList.get(r.nextInt(userList.size()));
					if( user instanceof Member){
						buyM = (Member)user;
						if(buyM.userNo!=0 && buyM.userNo!=sellM.userNo && buyM.isPossibleDeal){
							break;
						}
					}
				}
				member.autoBuyBookGUI(userList, boardList, bookList, dealList, sellM, buyM,tempSB, tempB, cLP.saleBoardListPanel, cLP.wishListPanel, cLP.adminDealListPanel);
				System.out.println("<<자동상품구매 쓰레드>>  게시글번호: "+tempSB.boardNo+" / "+buyM.id+"(이)가 "+sellM.id+"(이)가 판매하는 "+tempB.bookName+"을 자동 상품구매함. "
						+ "시간 : "+timeToString(Calendar.getInstance()));
			}
			
			// Calendar를 시분초로 반환 메소드
			public String timeToString(Calendar time) {
				String timeToString = (time.get(Calendar.HOUR_OF_DAY)) + "시 "
						+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
				return timeToString;
			}
			
		}.start();
		
		new Thread(){
			public void run(){
				System.out.println("<<MainFrame>>  1분에 한번씩 자동상품구매 주기와 자동상품등록 주기를 7초 ~ 14초중 랜덤으로 바꾸어주는 쓰레드");
				Random r = new Random();
				int[] autoTimeArr = {7000,8000,9000,10000,11000,12000,13000,14000};
//				int[] autoTimeArr = {700,800,900,1000,1100,1200,1300,1400};
				while(true){
					try { Thread.sleep(60000); } catch (InterruptedException e) { e.printStackTrace(); }
//					try { Thread.sleep(60000); } catch (InterruptedException e) { e.printStackTrace(); }
					
					autoRegisterBookTime = autoTimeArr[r.nextInt(8)];
					autoBuyBookTime = autoTimeArr[r.nextInt(8)];
					System.out.println("<<자동상품등록/자동상품구매 주기 수정 쓰레드>> 자동 상품 등록 주기가 "+autoRegisterBookTime+"ms로 변하였습니다.");
					System.out.println("<<자동상품등록/자동상품구매 주기 수정 쓰레드>> 자동 상품 구매 주기가 "+autoBuyBookTime+"ms로 변하였습니다.");
					if(autoBuyBookTime>autoRegisterBookTime){
						System.out.println("<<자동상품등록/자동상품구매 주기 수정 쓰레드>> 상품등록이 더 빨라집니다.");
					}else if(autoBuyBookTime<autoRegisterBookTime){
						System.out.println("<<자동상품등록/자동상품구매 주기 수정 쓰레드>> 상품구매가 더 빨라집니다.");						
					}else if(autoBuyBookTime==autoRegisterBookTime){
						System.out.println("<<자동상품등록/자동상품구매 주기 수정 쓰레드>> 상품구매와 상품 등록 시간이 같습니다.");
					}
				}
			}
		}.start();
        
	} // 메인 프레임 초기화 메소드
} // 메인 프레임 클래스
