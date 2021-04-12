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
     * �ε��� 0 --> 0: �α��� ����, 1: ������ �α���, 2: ��� �α���
     * �ε��� 1 --> �α��� ��ü ��ȣ 
     * */
	
	// �޼ҵ� �ʿ� ��ü ������������
	User user;
	Member member;
	Admin admin;
	
	ImageIcon backImage = new ImageIcon(MainFrame.class.getResource("..\\image\\bookMain.jpg")); // ����̹����� ���� ��ü
	
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
	
	boolean firstClickMemberListCheck=true; // ù��° ȸ����� Ŭ������ Ȯ���ϴ� ����
	boolean firstClickSaleBoardListCheck=true; // ù��° ��ǰ��� Ŭ������ Ȯ���ϴ� ����
	boolean firstClickDealList=true; // ù��° �ŷ���� Ŭ������ Ȯ���ϴ� ����
	
	String welcomLoginMember; // �α��θ�� ȯ���λ�
	
	int autoRegisterBookTime; // �ڵ� ��ǰ ��� �ð�
	int autoBuyBookTime; // �ڵ� ��ǰ ���� �ð�
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/* LOGIN_STATE
		 * �ε��� 0 --> 0: �α��� ����, 1: ������ �α���, 2: ��� �α���
		 * �ε��� 1 --> �α��� ��ü ��ȣ 
		 * */
//		Scanner sc = new Scanner(System.in);
//		boolean isReAction=false;
//		System.out.println("����� å���� �����մϴ�.");
//		do{
//			System.out.print("� ���� �����Ͻðڽ��ϱ�?  (0:�ÿ����, 1:�����ڷα��� �׽�Ʈ ���, 2:����α���(member2)�׽�Ʈ���)  :  ");
//			int select = sc.nextInt();
//			switch(select){
//			case 0: // �α׾ƿ� ����
//				LOGIN_STATE[0]=0;
//				LOGIN_STATE[1]=-1;
//				isReAction=false;
//				break;
//			case 1: // ������ �α���
//				LOGIN_STATE[0]=1;
//				LOGIN_STATE[1]=0;
//				isReAction=false;
//				break;
//			case 2: // member1 �α���
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
		
		// ������ ����
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
		
		// �޼ҵ� �ʿ� ��ü ����
		user = new User();
		member = new Member();
		admin = new Admin();
		
		
		// �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ� frame ���� �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�//
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setResizable(false); // â�� ũ������ ����
		frame.setLocationRelativeTo(null); // ��� â�� ��Ÿ������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("����� å��");
		
		


		
		// ---------------------------------------------- mainPanel ���� ----------------------------------------------//		
		mainPanel = new JPanel(){
	          public void paintComponent(Graphics g) {
		          g.drawImage(backImage.getImage(), 0, 0, null);
		          setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
		          super.paintComponent(g);
	          }
		};
		mainPanel.setBounds(0, 0, 1274, 685);
        mainPanel.setLayout(null);
        frame.getContentPane().setLayout(null); // ContentPane�� ���̾ƿ� ����
		frame.getContentPane().add(mainPanel);
		// =============================================== mainPanel �� ===========================================//
        
		// =============================================== cardLayoutPanel ���� ===========================================//
		cLP = new CLP(data,userList,boardList,bookList,dealList);
		mainPanel.add(cLP); // mainPanel�� cardLayoutPanel�� �߰�
		// =============================================== cardLayoutPanel �� ===========================================//
		
		// ---------------------------------------------- logoutMenuBarPanel ���� ----------------------------------------------//
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
                
        loginBtn = new JButton("�α���");
        logoutMenuBarPanel.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		User loginUser = user.login(userList, idTF.getText(), passwordF.getText());
        		if(loginUser==null){
        			JOptionPane.showMessageDialog(null,"�α��� ����");
        		}else if(loginUser instanceof Admin){
        			// ������ �α��ν� ȿ���� ������
        			new Thread(){
        				@Override
        				public void run(){
        					try {
        						System.out.println("<<MainFrame>> ������ �α��� ȿ���� ������ ����");
        						File file = new File(Music.class.getResource("..\\music\\login.mp3").toURI());
        						FileInputStream fis = new FileInputStream(file);
        						BufferedInputStream bis = new BufferedInputStream(fis);
        						Player player = new Player(bis);
        						player.play();
							} catch (Exception e2) { }
        				}
        			}.start();
        			JOptionPane.showMessageDialog(null,"�����ڷ� �α���\n"+loginUser.id+"�� �α���");
        			LOGIN_STATE[0] = 1;
        			LOGIN_STATE[1] = loginUser.userNo;
        			idTF.setText("");
        			passwordF.setText("");
        			cLP.setVisible(false);
        			logoutMenuBarPanel.setVisible(false);
        			adminMenuBarPanel.setVisible(true);
        		}else if(loginUser instanceof Member){
        			// ��� �α��ν� ȿ���� ������
        			new Thread(){
        				@Override
        				public void run(){
        					try {
        						System.out.println("<<MainFrame>> ��� �α��� ȿ���� ������ ����");
        						File file = new File(Music.class.getResource("..\\music\\login.mp3").toURI());
        						FileInputStream fis = new FileInputStream(file);
        						BufferedInputStream bis = new BufferedInputStream(fis);
        						Player player = new Player(bis);
        						player.play();
							} catch (Exception e2) { }
        				}
        			}.start();
        			JOptionPane.showMessageDialog(null,loginUser.id+"�� �α���");
        			LOGIN_STATE[0] = 2;
        			LOGIN_STATE[1] = loginUser.userNo;
        			idTF.setText("");
        			passwordF.setText("");
        			cLP.setVisible(false);
        			logoutMenuBarPanel.setVisible(false);
        			memberMenuBarPanel.setVisible(true);
        			welcomLoginMember = loginUser.id+"�� �ݰ����ϴ�          ";
        			myInfoLabel.setText(welcomLoginMember);
        			new Thread(){
        				public void run(){
        					String content = loginUser.id+"���� �湮�� ����帳�ϴ�.\n - ������  -";
        					Message tempMS = new Message(data.messageList.size(), userList.get(0), loginUser, content);
        					data.messageList.add(tempMS);
        					loginUser.showSendMessageList(data, cLP.messagePanel.sendMessageTable);
        				}
        			}.start();
        		}
        	}
        }); // �α��� ��ư Ŭ�� �̺�Ʈ
        
        menuJoinBtn = new JButton("ȸ������");
        logoutMenuBarPanel.add(menuJoinBtn);
        menuJoinBtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "join");
        		cLP.joinPanel.threadCheck = true;
        	}
        }); // ȸ������ Ŭ�� �̺�Ʈ
        
        findIdPasswordBtn = new JButton("���̵�/��й�ȣ ã��");
        logoutMenuBarPanel.add(findIdPasswordBtn);
        findIdPasswordBtn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			cLP.setVisible(true);
			cLP.cL.show(cLP, "findIdPass");
			
			}
		}); // ���̵�/��й�ȣ ã�� Ŭ�� �̺�Ʈ
		// =============================================== logoutMenuBarPanel �� ===========================================/*/
        
		// ---------------------------------------------- adminLoginMenuBarPanel ���� ----------------------------------------------//
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
        
        adminShowMemberListBtn = new JButton("ȸ�����");
        adminShowMemberListBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		admin.showMemberListByGUI(userList, cLP.adminMemberListPanel.table);
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "adminMemberList");
				if(firstClickMemberListCheck){
					new Thread(){
						@Override
						public void run(){
							System.out.println("<<MainFrame>> �ڵ� ȸ������ ������ ����");
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Member member = new Member();
							member.join(userList, userList.size(), "�ϴó���", "123123", "���ϴ�", "01000000000", "19931216", "����", "�λ� �ؿ��",cLP.adminMemberListPanel);
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							member.join(userList, userList.size(), "�޳���", "123123", "���", "01000000001", "19930205", "����", "��õ ������",cLP.adminMemberListPanel);
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							member.join(userList, userList.size(), "�޴Գ���", "123123", "���޴�", "01000000002", "20030715", "����", "�ǿ� ������",cLP.adminMemberListPanel);
							try {
								sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							member.join(userList, userList.size(), "��������", "123123", "������", "01000000003", "19440821", "����", "��� ����",cLP.adminMemberListPanel);
							System.out.println("<<MainFrame>> �ڵ� ȸ������ ������ ����");
						}
					}.start();
					firstClickMemberListCheck=false;
				}
        	}
        }); // ȸ����� ���� ��ư Ŭ�� �̺�Ʈ
        adminMenuBarPanel.add(adminShowMemberListBtn);
        
        adminShowDealListBtn = new JButton("�ŷ����");
        adminShowDealListBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		admin.showDealListByGUI(dealList, cLP.adminDealListPanel.table);
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "adminDealList");
        	}
        });
        adminMenuBarPanel.add(adminShowDealListBtn);
        
        adminMessageBtn = new JButton("�޽���");
        adminMessageBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		user.showReceiveMessageList(data, cLP.messagePanel.receiveMessageTable);
        		user.showSendMessageList(data, cLP.messagePanel.sendMessageTable);
        		cLP.setVisible(true);
        		cLP.cL.show(cLP, "message");
        	}
        });
        adminMenuBarPanel.add(adminMessageBtn);
        
        adminLogout = new JButton("�α׾ƿ�");
        adminLogout.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//�α׾ƿ��� �޽��� �г� �ʱ�ȭ
				cLP.messagePanel.inputMessageNoTF.setText("");
				cLP.messagePanel.messageContentTA.setText("");
				cLP.messagePanel.inputIdTF.setText("");
				cLP.messagePanel.messageWriteTA.setText("");
				cLP.messagePanel.messageSendBtn.setEnabled(false);
//				cLP.messagePanel.tempMessageLengthLabel.setText("0");
        		// ������ �α׾ƿ��� ȿ���� ������
    			new Thread(){
    				@Override
    				public void run(){
    					System.out.println("<<MainFrame>> ������ �α׾ƿ� ȿ���� ������ ����");
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
        
		// =============================================== adminLoginMenuBarPanel �� ===========================================/*/
        
		// ---------------------------------------------- memberLoginMenuBarPanel ���� ----------------------------------------------//
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
		
		 // ��ǰ��� ��ư
		memberShowBookListBtn = new JButton("��ǰ���");
		memberShowBookListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					member.showSaleBoardListByGUI(userList, boardList, bookList, cLP.saleBoardListPanel.table);
				} catch (Exception e1) {
					System.out.println("[ERROR] <<MainFrame  ��ǰ��� ��ư Ŭ�� �̺�Ʈ>>  showSaleBoardListByGUI �޼ҵ� : "+e1.getMessage());
					e1.printStackTrace();
				} // ��ǰ��� ���̺� ����ȭ
				cLP.setVisible(true);
				cLP.cL.show(cLP, "saleBoardList");
//        		if(firstClickSaleBoardListCheck){
//        			// �ڵ� ��ǰ ��� ������
//        			new Thread(){
//            			@Override
//            			public void run(){
//            				System.out.println("<<MainFrame>>  �ڵ� ��ǰ��� ������ ����");
//            				try {
//            					Thread.sleep(3000);
//            				} catch (InterruptedException e) { e.printStackTrace(); }
//            				Admin ad = new Admin();
//            				Member tempM = ad.findMemberByUserNo(userList, 2);
//            				Book tempB = new Book(bookList.size(),"������Ǫ, �ູ�� ���� ���� �־�","������ Ǫ(����)","������",10000,2);
//            				SaleBoard tempSB = new SaleBoard(boardList.size(),"�츮 ���� �ູ�ؿ�",tempM,tempB);
//            				bookList.add(tempSB.saleBook);
//            				boardList.add(tempSB);
//            				tempM.showSaleBoardListByGUI(userList, boardList, bookList, cLP.saleBoardListPanel.table);
//            				// ��ǰ��Ͻ� ȿ���� ������
//            				new Thread() {
//            					@Override
//            					public void run() {
//            						System.out.println("<<Memeber>> ��ǰ��� ȿ���� ������ ����");
//            						try {
//            							File file = new File(Music.class.getResource("..\\music\\registerBook.mp3").toURI());
//            							FileInputStream fis = new FileInputStream(file);
//            							BufferedInputStream bis = new BufferedInputStream(fis);
//            							Player player = new Player(bis);
//            							player.play();
//            						} catch (Exception e2) { }
//            					}
//            				}.start();
//            				System.out.println("<<MainFrame>>  �ڵ� ��ǰ��� ������ ����");
//            			}
//            		}.start();
//            		firstClickSaleBoardListCheck=false;
//        		}
			}
		});
		memberMenuBarPanel.add(memberShowBookListBtn);
		
		memberRegisterBookBtn = new JButton("��ǰ���");
		memberRegisterBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLP.setVisible(true);
				cLP.cL.show(cLP, "registerBook");
			}
		});
		memberMenuBarPanel.add(memberRegisterBookBtn);
		
		// �Ǹ����� ��ǰ ��ư
		memberShowSellingListBtn = new JButton("�Ǹ����� ��ǰ");
		memberShowSellingListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showMySellingListGUI(userList, boardList, cLP.mySellingListPanel.table);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "mySellingList");
			}
		});
		memberMenuBarPanel.add(memberShowSellingListBtn);
		
		memberShowBuyListBtn = new JButton("���Ÿ��");
		memberShowBuyListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showBuyListGUI(userList, cLP.myBuyListPanel.table);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "myBuyList");
			}
		});
		memberMenuBarPanel.add(memberShowBuyListBtn);
		
		memberShowSellListBtn = new JButton("�ǸŸ��");
		memberShowSellListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showSellListGUI(userList, cLP.mySellListPanel.table);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "mySellList");
			}
		});
		memberMenuBarPanel.add(memberShowSellListBtn);
		
		memberShowWishListBtn = new JButton("���ø���Ʈ");
		memberShowWishListBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.showWishListGUI(member.findLoginMember(userList), cLP.wishListPanel.table); // ���ø���Ʈ ���̺� ����ȭ
				cLP.setVisible(true);
				cLP.cL.show(cLP, "wishList");
			}
		}); // ���ø���Ʈ ��ư Ŭ�� �̺�Ʈ
		memberMenuBarPanel.add(memberShowWishListBtn);
		
		memberMessageBtn = new JButton("�޽���");
		memberMessageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.showReceiveMessageList(data, cLP.messagePanel.receiveMessageTable);
        		user.showSendMessageList(data, cLP.messagePanel.sendMessageTable);
				cLP.setVisible(true);
        		cLP.cL.show(cLP, "message");
			}
		});
		memberMenuBarPanel.add(memberMessageBtn);
		
		memberEventBtn = new JButton("�̺�Ʈ");
		memberEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLP.setVisible(true);
				cLP.cL.show(cLP, "event");
			}
		});
		memberMenuBarPanel.add(memberEventBtn);
		
		memberMyInfoBtn = new JButton("���� ����");
		memberMyInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Member loginM = member.findLoginMember(userList);
				member.myInfoGUI(loginM,cLP.myInfoPanel.memberInfoTA);
				cLP.setVisible(true);
				cLP.cL.show(cLP, "myInfo");
			}
		});
		memberMenuBarPanel.add(memberMyInfoBtn);
		
		memberLogoutBtn = new JButton("�α׾ƿ�");
		memberLogoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�α׾ƿ��� �޽��� �г� �ʱ�ȭ
				cLP.messagePanel.inputMessageNoTF.setText("");
				cLP.messagePanel.messageContentTA.setText("");
				cLP.messagePanel.inputIdTF.setText("");
				cLP.messagePanel.messageWriteTA.setText("");
				cLP.messagePanel.messageSendBtn.setEnabled(false);
//				cLP.messagePanel.tempMessageLengthLabel.setText("0");
				
				//�α׾ƿ��� �̺�Ʈ �г� �ʱ�ȭ
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
				
				//�α׾ƿ��� ���������г� �ʱ�ȭ
				cLP.myInfoPanel.chargeCoinTF.setText("");
				cLP.myInfoPanel.refundCoinTF.setText("");

				//�α׾ƿ��� ��ǰ����г� �ʱ�ȭ
				cLP.memberRegisterBookPanel.bookNameTF.setText("");
				cLP.memberRegisterBookPanel.authorTF.setText("");
				cLP.memberRegisterBookPanel.genreTF.setText("");
				cLP.memberRegisterBookPanel.priceTF.setText("");
				cLP.memberRegisterBookPanel.introduceBookTA.setText("");
				
				//�α׾ƿ��� ��ǰ��� �󼼺��� �ʱ�ȭ
				cLP.saleBoardListPanel.saleBoardDetailPanel.saleBoardInfoTA.setText("");
				cLP.saleBoardListPanel.saleBoardDetailPanel.inputBoardNoTF.setText("");
				cLP.saleBoardListPanel.saleBoardDetailPanel.saleBoardInfoTA.setText("");
				cLP.saleBoardListPanel.saleBoardDetailPanel.buyBtn.setEnabled(false);
				cLP.saleBoardListPanel.saleBoardDetailPanel.addWishBtn.setEnabled(false);
				
				//�α׾ƿ��� ���ø���Ʈ �󼼺��� �ʱ�ȭ
				cLP.wishListPanel.wishListDetailPanel.inputBoardNoTF.setText("");
				cLP.wishListPanel.wishListDetailPanel.saleBoardInfoTA.setText("");
				cLP.wishListPanel.wishListDetailPanel.buyBtn.setEnabled(false);
				
				// ��� �α׾ƿ��� ȿ���� ������
    			new Thread(){
    				@Override
    				public void run(){
    					System.out.println("<<MainFrame>> ��� �α׾ƿ� ȿ���� ������ ����");
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

		// =============================================== memberLoginMenuBarPanel �� ===========================================//
        
		frame.setVisible(true);
		// �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ� frame �� �ڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡڡ�//
		
		// ���� ��� ������
//        Music introMusic = new Music("mainMusic.mp3",true); // Music thread ��ü ����
//        introMusic.start(); // ������ ����
//        System.out.println("<<MainFrame>> ������� ��� ������ ����");
        
        
        // �ڵ� ��ǰ��� ������ ����
        new Thread(){
			public void run(){
				setName("�ڵ� ��ǰ��� ������");
				System.out.println("<<MainFrame>> �ڵ� ��ǰ��� ������ ����");
				autoRegisterBookTime=8000;
//				autoRegisterBookTime=100;
				while(true){
					try { Thread.sleep(autoRegisterBookTime); } catch (InterruptedException e) { e.printStackTrace(); }
					try {
						registerBook();
					} catch (Exception e) {
						System.out.println("[ERROR] �ڵ���ǰ��Ͼ����� ������");
						System.out.println(e.getMessage());
					}
				}
			}
			
			public void registerBook(){
				Random r = new Random();
				SaleBoard tempSB;
				Book saleBook = bookList.get(r.nextInt(bookList.size()));
				String boardContent = "�� å�� �̸��� "+saleBook.bookName+"�̰� �����̴� "+saleBook.author+"�Դϴ�. "
						+"�׸��� �帣�� "+saleBook.genre+"�Դϴ�. ���� å�Դϴ�. ���� ���� ��Ź����� ~ :)";
				User boardWriteUser;
				while(true){
					boardWriteUser = userList.get(r.nextInt(userList.size()));
					if(boardWriteUser.userNo!=0 && boardWriteUser.userNo!=LOGIN_STATE[1]){
						break;
					}
				}
				tempSB = new SaleBoard(boardList.size(), boardContent, boardWriteUser, saleBook);
				member.registerBookByGUI(userList, boardList, bookList, tempSB, cLP.saleBoardListPanel, cLP.mySellingListPanel);
				System.out.println("<<�ڵ���ǰ��� ������>>  �Խñ۹�ȣ: "+tempSB.boardNo+" / "+tempSB.boardWriteUser.id+"(��)�� "+tempSB.saleBook.bookName+"�� �ڵ� ��ǰ�����. �ð� : "+timeToString(tempSB.boardWriteTime));
			}
			// Calendar�� �ú��ʷ� ��ȯ �޼ҵ�
			public String timeToString(Calendar time) {
				String timeToString = (time.get(Calendar.HOUR_OF_DAY)) + "�� "
						+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
				return timeToString;
			}
		}.start();
		
        // �ڵ� ��ǰ���� ������ ����
		new Thread(){
			public void run(){
				setName("�ڵ� ��ǰ���� ������");
				System.out.println("<<MainFrame>> �ڵ� ��ǰ���� ������ ����");
				autoBuyBookTime=12000;
//				autoBuyBookTime=210;
				while(true){
					try { Thread.sleep(autoBuyBookTime); } catch (InterruptedException e) { e.printStackTrace(); }
					try {
						buyBook();
					} catch (Exception e) {
						System.out.println("[ERROR] �ڵ���ǰ���ž����� ������");
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
				System.out.println("<<�ڵ���ǰ���� ������>>  �Խñ۹�ȣ: "+tempSB.boardNo+" / "+buyM.id+"(��)�� "+sellM.id+"(��)�� �Ǹ��ϴ� "+tempB.bookName+"�� �ڵ� ��ǰ������. "
						+ "�ð� : "+timeToString(Calendar.getInstance()));
			}
			
			// Calendar�� �ú��ʷ� ��ȯ �޼ҵ�
			public String timeToString(Calendar time) {
				String timeToString = (time.get(Calendar.HOUR_OF_DAY)) + "�� "
						+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
				return timeToString;
			}
			
		}.start();
		
		new Thread(){
			public void run(){
				System.out.println("<<MainFrame>>  1�п� �ѹ��� �ڵ���ǰ���� �ֱ�� �ڵ���ǰ��� �ֱ⸦ 7�� ~ 14���� �������� �ٲپ��ִ� ������");
				Random r = new Random();
				int[] autoTimeArr = {7000,8000,9000,10000,11000,12000,13000,14000};
//				int[] autoTimeArr = {700,800,900,1000,1100,1200,1300,1400};
				while(true){
					try { Thread.sleep(60000); } catch (InterruptedException e) { e.printStackTrace(); }
//					try { Thread.sleep(60000); } catch (InterruptedException e) { e.printStackTrace(); }
					
					autoRegisterBookTime = autoTimeArr[r.nextInt(8)];
					autoBuyBookTime = autoTimeArr[r.nextInt(8)];
					System.out.println("<<�ڵ���ǰ���/�ڵ���ǰ���� �ֱ� ���� ������>> �ڵ� ��ǰ ��� �ֱⰡ "+autoRegisterBookTime+"ms�� ���Ͽ����ϴ�.");
					System.out.println("<<�ڵ���ǰ���/�ڵ���ǰ���� �ֱ� ���� ������>> �ڵ� ��ǰ ���� �ֱⰡ "+autoBuyBookTime+"ms�� ���Ͽ����ϴ�.");
					if(autoBuyBookTime>autoRegisterBookTime){
						System.out.println("<<�ڵ���ǰ���/�ڵ���ǰ���� �ֱ� ���� ������>> ��ǰ����� �� �������ϴ�.");
					}else if(autoBuyBookTime<autoRegisterBookTime){
						System.out.println("<<�ڵ���ǰ���/�ڵ���ǰ���� �ֱ� ���� ������>> ��ǰ���Ű� �� �������ϴ�.");						
					}else if(autoBuyBookTime==autoRegisterBookTime){
						System.out.println("<<�ڵ���ǰ���/�ڵ���ǰ���� �ֱ� ���� ������>> ��ǰ���ſ� ��ǰ ��� �ð��� �����ϴ�.");
					}
				}
			}
		}.start();
        
	} // ���� ������ �ʱ�ȭ �޼ҵ�
} // ���� ������ Ŭ����
