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
	
	String adInfo; // ������
	Adversity ad;
	ImmediateLotto iLotto;
	CardGame cardGame;
	
	/**
	 * Create the panel.
	 */
	public EventPanel(Data data, JPanel cardLayoutPanel, MyInfoPanel myInfoPanel) {
		
		this.setBounds(14, 60, 1246, 613);
		this.setLayout(null);
		
		///////////////////////////////////////////////// �̺�Ʈ ī�� ���̾ƿ� �г� ����
		
		eventCardLayoutPanel = new JPanel();
		eventCardLayoutPanel.setBounds(222, 89, 910, 463);
		eventCardLayoutPanel.setVisible(false);
		add(eventCardLayoutPanel);
		cl =new CardLayout();
		eventCardLayoutPanel.setLayout(cl);
		
		// ���� �г�
		adP = new AdversityPanel(cardLayoutPanel,eventCardLayoutPanel, adInfo);
		eventCardLayoutPanel.add(adP,"adversity");
		
		// �ζ� �г�
		iP = new LottoPanel(data,myInfoPanel);
		eventCardLayoutPanel.add(iP,"lotto");
		
		// ī����� �г�
		cGP = new CardGamePanel(data,myInfoPanel);
		eventCardLayoutPanel.add(cGP,"cardGame");
		
		///////////////////////////////////////////////// �̺�Ʈ ī�� ���̾ƿ� �г� ��
		
		JLabel lblNewLabel_5 = new JLabel("�̺�Ʈ");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel_5.setBounds(35, 35, 144, 18);
		add(lblNewLabel_5);
		
		
		// ������ ��ư
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
					// �����̺�Ʈ�� �����ϰ� ���� �ð��� ������ �ٽ� �����̺�Ʈ�� ������ �� �ְ� ���ִ� ������
					new Thread(){
						@Override
						public void run(){
							System.out.println("<<EventPanel>> "+loginM.id+"�� �����̺�Ʈ�� �����ϰ� ���� �ð��� ������ �ٽ� �����̺�Ʈ�� ������ �� �ְ� ���ִ� ������ ����");
							long startT = System.currentTimeMillis();
							while(true){
								long currentT=System.currentTimeMillis() - startT;
								if(currentT>60000){
									loginM.isParticipationAdversity=false;
									break;
								}
							}
							System.out.println("<<EventPanel>> "+loginM.id+"�� �����̺�Ʈ�� �����ϰ� ���� �ð��� ������ �ٽ� �����̺�Ʈ�� ������ �� �ְ� ���ִ� ������ ����");
						}
					}.start();
				}else{
					JOptionPane.showMessageDialog(null, "���� �а� 1���� ������ �����̺�Ʈ�� �ٽ� ������ �� �ֽ��ϴ�.");
				}
			}
		});
		showAdBtn.setBounds(35, 89, 105, 27);
		add(showAdBtn);

		// �ζǰ��� ��ư
		JButton lottoBtn = new JButton("\uC989\uC11D\uB85C\uB610");
		lottoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventCardLayoutPanel.setVisible(true);
				cl.show(eventCardLayoutPanel, "lotto");
			}
		});
		lottoBtn.setBounds(35, 152, 105, 27);
		add(lottoBtn);
		
		// ī����� ��ư
		JButton cardGameBtn = new JButton("\uCE74\uB4DC\uAC8C\uC784");
		cardGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventCardLayoutPanel.setVisible(true);
				cl.show(eventCardLayoutPanel, "cardGame");
			}
		});
		cardGameBtn.setBounds(35, 222, 105, 27);
		add(cardGameBtn);
		
		// �ڷΰ��� ��ư
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
		
		
		
	} // �̺�Ʈ �г� ������
} // �̺�Ʈ Ŭ����
