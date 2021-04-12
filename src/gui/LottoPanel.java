package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;

import event.ImmediateLotto;
import system.Data;
import user.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class LottoPanel extends JPanel {
	public JTextField a;
	public JTextField b;
	public JTextField c;
	public JTextField d;
	public JTextField e;
	public JTextField f;
	public JTextField aTF;
	public JTextField bTF;
	public JTextField cTF;
	public JTextField dTF;
	public JTextField eTF;
	public JTextField fTF;
	public JTextField bettingCoinTF;

	ImmediateLotto iLotto;
	public JRadioButton testModeCheck;
	
	/**
	 * Create the panel.
	 */
	public LottoPanel(Data data,MyInfoPanel myInfoPanel) {
		setBounds(222, 89, 910, 463);
		setLayout(null);
		
		a = new JTextField();
		a.setEditable(false);
		a.setText("?");
		a.setBounds(208, 118, 56, 24);
		add(a);
		a.setColumns(10);
		
		b = new JTextField();
		b.setEditable(false);
		b.setText("?");
		b.setColumns(10);
		b.setBounds(278, 118, 56, 24);
		add(b);
		
		c = new JTextField();
		c.setEditable(false);
		c.setText("?");
		c.setColumns(10);
		c.setBounds(350, 118, 56, 24);
		add(c);
		
		d = new JTextField();
		d.setEditable(false);
		d.setText("?");
		d.setColumns(10);
		d.setBounds(420, 118, 56, 24);
		add(d);
		
		e = new JTextField();
		e.setEditable(false);
		e.setText("?");
		e.setColumns(10);
		e.setBounds(493, 118, 56, 24);
		add(e);
		
		f = new JTextField();
		f.setEditable(false);
		f.setText("?");
		f.setColumns(10);
		f.setBounds(563, 118, 56, 24);
		add(f);
		
		aTF = new JTextField();
		aTF.setColumns(10);
		aTF.setBounds(208, 177, 56, 24);
		add(aTF);
		
		bTF = new JTextField();
		bTF.setColumns(10);
		bTF.setBounds(278, 177, 56, 24);
		add(bTF);
		
		cTF = new JTextField();
		cTF.setColumns(10);
		cTF.setBounds(350, 177, 56, 24);
		add(cTF);
		
		dTF = new JTextField();
		dTF.setColumns(10);
		dTF.setBounds(420, 177, 56, 24);
		add(dTF);
		
		eTF = new JTextField();
		eTF.setColumns(10);
		eTF.setBounds(493, 177, 56, 24);
		add(eTF);
		
		fTF = new JTextField();
		fTF.setColumns(10);
		fTF.setBounds(563, 177, 56, 24);
		add(fTF);
		
		JLabel label = new JLabel("\uCF54\uC778 \uBC30\uD305");
		label.setBounds(206, 293, 62, 18);
		add(label);
		
		JButton checkBtn = new JButton("\uD655 \uC778");
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				Member loginM = new Member();
				loginM = loginM.findLoginMember(data.userList);
				if(data.eventList.get(1) instanceof ImmediateLotto){
					iLotto = (ImmediateLotto)data.eventList.get(1);
				}
				if(!loginM.isParticipationLotto){
					iLotto.newLottoNumArr();
					
					String iL0 = String.valueOf(iLotto.numArray[0]);
					String iL1 = String.valueOf(iLotto.numArray[1]);
					String iL2 = String.valueOf(iLotto.numArray[2]);
					String iL3 = String.valueOf(iLotto.numArray[3]);
					String iL4 = String.valueOf(iLotto.numArray[4]);
					String iL5 = String.valueOf(iLotto.numArray[5]);
					
					String ip0 = aTF.getText().trim();
					String ip1 = bTF.getText().trim();
					String ip2 = cTF.getText().trim();
					String ip3 = dTF.getText().trim();
					String ip4 = eTF.getText().trim();
					String ip5 = fTF.getText().trim();
					
					String bettingCoin = bettingCoinTF.getText().trim();
					
					if(ip0.length()==0 || ip1.length()==0 || ip2.length()==0 || ip3.length()==0 || ip4.length()==0 || ip5.length()==0){
						JOptionPane.showMessageDialog(null, "예상 번호를 입력해주세요.");
						return;
					}else if(bettingCoin.length()==0){
						JOptionPane.showMessageDialog(null, "배팅 할 코인을 입력해주세요.");
						return;
					}else if(Integer.parseInt(bettingCoin)>loginM.coin){
						JOptionPane.showMessageDialog(null, "코인이 부족합니다.");
						return;
					}
					
					if(testModeCheck.isSelected()){
						a.setText("1");
						b.setText("1");
						c.setText("1");
						d.setText("1");
						e.setText("1");
						f.setText("1");
						if("1".equals(ip0) && "1".equals(ip1) && "1".equals(ip2) && "1".equals(ip3) && "1".equals(ip4) && "1".equals(ip5)){	
							int chargeCoin = Integer.parseInt(bettingCoin)*10;
							loginM.coin += chargeCoin;
							JOptionPane.showMessageDialog(null, "축하합니다. 성공하였습니다.\n\n"+chargeCoin + "코인을 얻었습니다.");
							loginM.myInfoGUI(loginM, myInfoPanel.memberInfoTA);
						}else{
							loginM.coin -= Integer.parseInt(bettingCoin);
							JOptionPane.showMessageDialog(null, "안타깝네요. 다시 도전해주세요.\n\n"+bettingCoin + "코인을 잃었습니다.");
							loginM.myInfoGUI(loginM, myInfoPanel.memberInfoTA);
						}
					}else{
						a.setText(iL0);
						b.setText(iL1);
						c.setText(iL2);
						d.setText(iL3);
						e.setText(iL4);
						f.setText(iL5);
						if(iL0.equals(ip0) && iL1.equals(ip1) && iL2.equals(ip2) && iL3.equals(ip3) && iL4.equals(ip4) && iL5.equals(ip5)){
							int chargeCoin = Integer.parseInt(bettingCoin)*10;
							loginM.coin += chargeCoin;
							JOptionPane.showMessageDialog(null, "축하합니다. 성공하였습니다.\n\n"+chargeCoin + "코인을 얻었습니다.");
							loginM.myInfoGUI(loginM, myInfoPanel.memberInfoTA);
						}else{
							loginM.coin -= Integer.parseInt(bettingCoin);
							JOptionPane.showMessageDialog(null, "안타깝네요. 다시 도전해주세요.\n\n"+bettingCoin + "코인을 잃었습니다.");
							loginM.myInfoGUI(loginM, myInfoPanel.memberInfoTA);
						}
					}
					
					
					a.setText("?");
					b.setText("?");
					c.setText("?");
					d.setText("?");
					e.setText("?");
					f.setText("?");
					aTF.setText("");
					bTF.setText("");
					cTF.setText("");
					dTF.setText("");
					eTF.setText("");
					fTF.setText("");
					bettingCoinTF.setText("");
					setVisible(false);
					
					loginM.isParticipationLotto=true;
					// 바로로또이벤트에 참여하고 일정 시간이 지나면 다시 바로로또이벤트에 참여할 수 있게 해주는 쓰레드
					new Thread(){
						@Override
						public void run(){
							Member loginM = new Member();
							loginM = loginM.findLoginMember(data.userList);
							System.out.println("<<LottoPanel>> "+loginM.id+"님 바로로또이벤트에 참여하고 일정 시간이 지나면 다시 바로로또이벤트에 참여할 수 있게 해주는 쓰레드 시작");
							long startT = System.currentTimeMillis();
							while(true){
								long currentT=System.currentTimeMillis() - startT;
								if(currentT>60000){
									loginM.isParticipationLotto=false;
									break;
								}
							}
							System.out.println("<<LottoPanel>> "+loginM.id+"님 바로로또이벤트에 참여하고 일정 시간이 지나면 다시 바로로또이벤트에 참여할 수 있게 해주는 쓰레드 종료");
						}
					}.start();
				}else{
					JOptionPane.showMessageDialog(null, iLotto.gameName+ "을 참여하고 1분이 지나야 다시 참여할 수 있습니다.");
				}
			}
		});
		checkBtn.setBounds(666, 117, 105, 84);
		add(checkBtn);
		
		bettingCoinTF = new JTextField();
		bettingCoinTF.setBounds(307, 290, 116, 24);
		add(bettingCoinTF);
		bettingCoinTF.setColumns(10);
		
		JLabel label_2 = new JLabel("\uBE48\uCE78\uC5D0 \uAC01\uAC01 0~5\uC758 \uC22B\uC790\uB97C \uCC44\uC6CC\uC8FC\uC138\uC694.");
		label_2.setBounds(208, 72, 292, 18);
		add(label_2);
		
		JLabel lblNewLabel_2 = new JLabel("즉석로또");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_2.setBounds(14, 12, 93, 18);
		add(lblNewLabel_2);
		
		testModeCheck = new JRadioButton("\uB85C\uB610\uBC88\uD638 1,1,1,1,1,1 \uB85C \uB9CC\uB4E4\uAE30");
		testModeCheck.setBounds(666, 428, 234, 27);
		add(testModeCheck);
	} // 패널 생성자
} // 패널 클래스
