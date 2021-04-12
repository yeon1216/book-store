package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import event.Adversity;
import system.Data;

public class AdversityPanel extends JPanel {
	public JTextArea adInfoTA;

	/**
	 * Create the panel.
	 */
	public AdversityPanel(JPanel cardLayoutPanel, JPanel eventCardLayoutPanel, String adInfo) {
		
		setBounds(222, 89, 910, 463);
		setLayout(null);
		
//		JLabel lblNewLabel = new JLabel("5\uCD08\uB3D9\uC548 \uAD11\uACE0\uB97C \uC77D\uC73C\uC2DC\uBA74 100\uCF54\uC778\uC744 \uB4DC\uB9BD\uB2C8\uB2E4.");
//		lblNewLabel.setBounds(14, 12, 326, 18);
//		add(lblNewLabel);
		
		
//		adInfoTA = new JTextArea();
//		adInfoTA.setBounds(71, 94, 585, 308);
//		adInfoTA.setText(adInfo);
//		
//		JScrollPane scrollPane = new JScrollPane(adInfoTA);
//		scrollPane.setBounds(71, 94, 585, 308);
//		add(scrollPane);
		
//		JButton cancelBtn = new JButton("뒤로가기");
//		cancelBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				cardLayoutPanel.setVisible(false);
//				eventCardLayoutPanel.setVisible(false);
//				adInfoTA.setText("");
//			}
//		});
//		cancelBtn.setBounds(753, 375, 105, 27);
//		add(cancelBtn);
		
	} // 광고 패널 생성자
} // 광고  패널 클래스
