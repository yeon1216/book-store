package gui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import event.Adversity;
import event.CardGame;
import event.ImmediateLotto;
import system.Data;
import user.Member;

public class EventPanel extends JPanel {
	public JPanel eventCardLayoutPanel;
	CardLayout cl;
	
	public AdversityPanel adP;
	public LottoPanel iP;
	public CardGamePanel cGP;
	
	String adInfo; // 광고내용
	Adversity ad;
	ImmediateLotto iLotto;
	CardGame cardGame;
	
	/**
	 * Create the panel.
	 */
	public EventPanel(Data data, JPanel cardLayoutPanel, MyInfoPanel myInfoPanel) {
		
		this.setBounds(14, 60, 1246, 613);
		this.setLayout(null);
		
		///////////////////////////////////////////////// 이벤트 카드 레이아웃 패널 시작
		
		eventCardLayoutPanel = new JPanel();
		eventCardLayoutPanel.setBounds(222, 89, 910, 463);
		eventCardLayoutPanel.setVisible(false);
		add(eventCardLayoutPanel);
		cl =new CardLayout();
		eventCardLayoutPanel.setLayout(cl);
		
		// 광고 패널
		adP = new AdversityPanel(cardLayoutPanel,eventCardLayoutPanel, adInfo);
		eventCardLayoutPanel.add(adP,"adversity");
		
		// 로또 패널
		iP = new LottoPanel(data,myInfoPanel);
		eventCardLayoutPanel.add(iP,"lotto");
		
		// 카드게임 패널
		cGP = new CardGamePanel(data,myInfoPanel);
		eventCardLayoutPanel.add(cGP,"cardGame");
		
		///////////////////////////////////////////////// 이벤트 카드 레이아웃 패널 끝
		
		JLabel lblNewLabel_5 = new JLabel("이벤트");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 35, 144, 18);
		add(lblNewLabel_5);
		
		
		// 광고보기 버튼
		JButton showAdBtn = new JButton("\uAD11\uACE0\uBCF4\uAE30");
		showAdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Member member = new Member();
				Member loginM = member.findLoginMember(data.userList);
				if(data.eventList.get(0) instanceof Adversity){			
					ad = (Adversity)data.eventList.get(0);
				}
				if(!loginM.isParticipationAdversity){
					String adInfo = "\n\n < "+ad.companyName+" >\n"
							+"\n "+ad.adversityContent +"\n\n";
					eventCardLayoutPanel.setVisible(true);
					JOptionPane.showMessageDialog(null, adInfo);
					
					loginM.chargeCoin(data.userList, loginM, "100", myInfoPanel.memberInfoTA);
					loginM.isParticipationAdversity=true;
					// 광고이벤트에 참여하고 일정 시간이 지나면 다시 광고이벤트에 참여할 수 있게 해주는 쓰레드
					new Thread(){
						@Override
						public void run(){
							System.out.println("<<EventPanel>> "+loginM.id+"님 광고이벤트에 참여하고 일정 시간이 지나면 다시 광고이벤트에 참여할 수 있게 해주는 쓰레드 시작");
							long startT = System.currentTimeMillis();
							while(true){
								long currentT=System.currentTimeMillis() - startT;
								if(currentT>60000){
									loginM.isParticipationAdversity=false;
									break;
								}
							}
							System.out.println("<<EventPanel>> "+loginM.id+"님 광고이벤트에 참여하고 일정 시간이 지나면 다시 광고이벤트에 참여할 수 있게 해주는 쓰레드 종료");
						}
					}.start();
				}else{
					JOptionPane.showMessageDialog(null, "광고를 읽고 1분이 지나야 광고이벤트에 다시 참여할 수 있습니다.");
				}
			}
		});
		showAdBtn.setBounds(35, 89, 105, 27);
		add(showAdBtn);

		// 로또게임 버튼
		JButton lottoBtn = new JButton("\uC989\uC11D\uB85C\uB610");
		lottoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventCardLayoutPanel.setVisible(true);
				cl.show(eventCardLayoutPanel, "lotto");
			}
		});
		lottoBtn.setBounds(35, 152, 105, 27);
		add(lottoBtn);
		
		// 카드게임 버튼
		JButton cardGameBtn = new JButton("\uCE74\uB4DC\uAC8C\uC784");
		cardGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventCardLayoutPanel.setVisible(true);
				cl.show(eventCardLayoutPanel, "cardGame");
			}
		});
		cardGameBtn.setBounds(35, 222, 105, 27);
		add(cardGameBtn);
		
		// 뒤로가기 버튼
		JButton cancelBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutPanel.setVisible(false);
				setVisible(false);
				iP.a.setText("?");
				iP.b.setText("?");
				iP.c.setText("?");
				iP.d.setText("?");
				iP.e.setText("?");
				iP.f.setText("?");
				iP.aTF.setText("");
				iP.bTF.setText("");
				iP.cTF.setText("");
				iP.dTF.setText("");
				iP.eTF.setText("");
				iP.fTF.setText("");
				iP.bettingCoinTF.setText("");
				iP.setVisible(false);
				cGP.a.setText("?");
				cGP.b.setText("?");
				cGP.c.setText("?");
				cGP.d.setText("?");
				cGP.e.setText("?");
				cGP.f.setText("?");
				cGP.inputTF.setText("");
				cGP.bettingCoinTF.setText("");
				cGP.setVisible(false);
			}
		});
		cancelBtn.setBounds(35, 285, 105, 27);
		add(cancelBtn);
		
		
		
	} // 이벤트 패널 생성자
} // 이벤트 클래스
