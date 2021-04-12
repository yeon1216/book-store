package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import event.CardGame;
import event.ImmediateLotto;
import system.Data;
import user.Member;

public class CardGamePanel extends JPanel {
	public JTextField a;
	public JTextField b;
	public JTextField c;
	public JTextField d;
	public JTextField e;
	public JTextField f;
	public JTextField bettingCoinTF;
	private JLabel lblNewLabel;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel lblNewLabel_1;
	public JTextField inputTF;
	private JLabel lblNewLabel_2;
	
	CardGame cardGame;
	

	/**
	 * Create the panel.
	 */
	public CardGamePanel(Data data, MyInfoPanel myInfoPanel) {
		setBounds(222, 89, 910, 463);
		setLayout(null);
		
		a = new JTextField();
		a.setEditable(false);
		a.setText("?");
		a.setBounds(206, 177, 56, 24);
		add(a);
		a.setColumns(10);
		
		b = new JTextField();
		b.setEditable(false);
		b.setText("?");
		b.setColumns(10);
		b.setBounds(276, 177, 56, 24);
		add(b);
		
		c = new JTextField();
		c.setEditable(false);
		c.setText("?");
		c.setColumns(10);
		c.setBounds(348, 177, 56, 24);
		add(c);
		
		d = new JTextField();
		d.setEditable(false);
		d.setText("?");
		d.setColumns(10);
		d.setBounds(418, 177, 56, 24);
		add(d);
		
		e = new JTextField();
		e.setEditable(false);
		e.setText("?");
		e.setColumns(10);
		e.setBounds(491, 177, 56, 24);
		add(e);
		
		f = new JTextField();
		f.setEditable(false);
		f.setText("?");
		f.setColumns(10);
		f.setBounds(561, 177, 56, 24);
		add(f);
		
		JLabel label = new JLabel("\uCF54\uC778 \uBC30\uD305");
		label.setBounds(206, 293, 62, 18);
		add(label);
		
		JButton checkBtn = new JButton("\uD655 \uC778");
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				Member loginM = new Member();
				loginM = loginM.findLoginMember(data.userList);
				String bettingCoin =bettingCoinTF.getText().trim();
				String input = inputTF.getText().trim();
				
				if(data.eventList.get(2) instanceof CardGame){
					cardGame = (CardGame)data.eventList.get(2);
				}
				if(!loginM.isParticipationCardGame){
					if(bettingCoin.length()==0){
						JOptionPane.showMessageDialog(null, "배팅 할 코인을 입력해주세요.");
						return;
					}else if(input.length()==0){
						JOptionPane.showMessageDialog(null, "선택할 칸의 번호를 입력해주세요.");
						return;
					}else if(Integer.parseInt(bettingCoin)>loginM.coin){
						JOptionPane.showMessageDialog(null, "코인이 부족합니다.");
						return;
					}
					
					cardGame.mixCard();
					char[] cardArr = cardGame.cardArr;
					a.setText(String.valueOf(cardArr[0]));
					b.setText(String.valueOf(cardArr[1]));
					c.setText(String.valueOf(cardArr[2]));
					d.setText(String.valueOf(cardArr[3]));
					e.setText(String.valueOf(cardArr[4]));
					f.setText(String.valueOf(cardArr[5]));
					
					if(cardArr[Integer.parseInt(input)]=='A'){
						int chargeCoin = Integer.parseInt(bettingCoin)*2;
						loginM.coin += chargeCoin;
						JOptionPane.showMessageDialog(null, "축하합니다. 성공하였습니다.\n\n"+chargeCoin + "코인을 얻었습니다.");
						loginM.myInfoGUI(loginM, myInfoPanel.memberInfoTA);
					}else{
						loginM.coin -= Integer.parseInt(bettingCoin);
						JOptionPane.showMessageDialog(null, "안타깝네요. 다시 도전해주세요.\n\n"+bettingCoin + "코인을 잃었습니다.");
						loginM.myInfoGUI(loginM, myInfoPanel.memberInfoTA);
					}
					
					a.setText("?");
					b.setText("?");
					c.setText("?");
					d.setText("?");
					e.setText("?");
					f.setText("?");
					inputTF.setText("");
					bettingCoinTF.setText("");
					setVisible(false);
					
					loginM.isParticipationCardGame=true;
					// 카드게임이벤트에 참여하고 일정 시간이 지나면 다시 카드게임이벤트에 참여할 수 있게 해주는 쓰레드
					new Thread(){
						@Override
						public void run(){
							Member loginM = new Member();
							loginM = loginM.findLoginMember(data.userList);
							System.out.println("<<CardGamePanel>> "+loginM.id+"님 카드게임이벤트에 참여하고 일정 시간이 지나면 다시 카드게임이벤트에 참여할 수 있게 해주는 쓰레드 시작");
							long startT = System.currentTimeMillis();
							while(true){
								long currentT=System.currentTimeMillis() - startT;
								if(currentT>60000){
									loginM.isParticipationCardGame=false;
									break;
								}
							}
							System.out.println("<<CardGamePanel>> "+loginM.id+"님 카드게임이벤트에 참여하고 일정 시간이 지나면 다시 카드게임이벤트에 참여할 수 있게 해주는 쓰레드 종료");
						}
					}.start();
				}else{
					JOptionPane.showMessageDialog(null, cardGame.gameName+ "을 참여하고 1분이 지나야 다시 참여할 수 있습니다.");
				}
			}
		});
		checkBtn.setBounds(666, 117, 105, 84);
		add(checkBtn);
		
		bettingCoinTF = new JTextField();
		bettingCoinTF.setBounds(307, 290, 116, 24);
		add(bettingCoinTF);
		bettingCoinTF.setColumns(10);
		
		lblNewLabel = new JLabel("0");
		lblNewLabel.setBounds(206, 150, 31, 18);
		add(lblNewLabel);
		
		label_2 = new JLabel("1");
		label_2.setBounds(276, 150, 31, 18);
		add(label_2);
		
		label_3 = new JLabel("3");
		label_3.setBounds(418, 150, 31, 18);
		add(label_3);
		
		label_4 = new JLabel("2");
		label_4.setBounds(348, 150, 31, 18);
		add(label_4);
		
		label_5 = new JLabel("5");
		label_5.setBounds(561, 150, 31, 18);
		add(label_5);
		
		label_6 = new JLabel("4");
		label_6.setBounds(491, 150, 31, 18);
		add(label_6);
		
		lblNewLabel_1 = new JLabel("\uBA87\uBC88 \uCE78\uC5D0 \uCE74\uB4DC A\uAC00 \uC788\uC744\uAE4C\uC694?");
		lblNewLabel_1.setBounds(206, 99, 254, 18);
		add(lblNewLabel_1);
		
		inputTF = new JTextField();
		inputTF.setBounds(501, 96, 116, 24);
		add(inputTF);
		inputTF.setColumns(10);
		
		lblNewLabel_2 = new JLabel("카드게임");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_2.setBounds(14, 12, 93, 18);
		add(lblNewLabel_2);
	} // 패널 생성자
} // 패널 클래스
