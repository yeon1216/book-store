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
						JOptionPane.showMessageDialog(null, "���� �� ������ �Է����ּ���.");
						return;
					}else if(input.length()==0){
						JOptionPane.showMessageDialog(null, "������ ĭ�� ��ȣ�� �Է����ּ���.");
						return;
					}else if(Integer.parseInt(bettingCoin)>loginM.coin){
						JOptionPane.showMessageDialog(null, "������ �����մϴ�.");
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
						JOptionPane.showMessageDialog(null, "�����մϴ�. �����Ͽ����ϴ�.\n\n"+chargeCoin + "������ ������ϴ�.");
						loginM.myInfoGUI(loginM, myInfoPanel.memberInfoTA);
					}else{
						loginM.coin -= Integer.parseInt(bettingCoin);
						JOptionPane.showMessageDialog(null, "��Ÿ���׿�. �ٽ� �������ּ���.\n\n"+bettingCoin + "������ �Ҿ����ϴ�.");
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
					// ī������̺�Ʈ�� �����ϰ� ���� �ð��� ������ �ٽ� ī������̺�Ʈ�� ������ �� �ְ� ���ִ� ������
					new Thread(){
						@Override
						public void run(){
							Member loginM = new Member();
							loginM = loginM.findLoginMember(data.userList);
							System.out.println("<<CardGamePanel>> "+loginM.id+"�� ī������̺�Ʈ�� �����ϰ� ���� �ð��� ������ �ٽ� ī������̺�Ʈ�� ������ �� �ְ� ���ִ� ������ ����");
							long startT = System.currentTimeMillis();
							while(true){
								long currentT=System.currentTimeMillis() - startT;
								if(currentT>60000){
									loginM.isParticipationCardGame=false;
									break;
								}
							}
							System.out.println("<<CardGamePanel>> "+loginM.id+"�� ī������̺�Ʈ�� �����ϰ� ���� �ð��� ������ �ٽ� ī������̺�Ʈ�� ������ �� �ְ� ���ִ� ������ ����");
						}
					}.start();
				}else{
					JOptionPane.showMessageDialog(null, cardGame.gameName+ "�� �����ϰ� 1���� ������ �ٽ� ������ �� �ֽ��ϴ�.");
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
		
		lblNewLabel_2 = new JLabel("ī�����");
		lblNewLabel_2.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_2.setBounds(14, 12, 93, 18);
		add(lblNewLabel_2);
	} // �г� ������
} // �г� Ŭ����
