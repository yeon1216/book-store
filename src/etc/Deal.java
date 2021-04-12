package etc;

import java.util.Calendar;

import board.SaleBoard;
import user.Member;

public class Deal {
	public int dealNo; // �ŷ���ȣ
	public Calendar dealTime; // �ŷ�����
	public SaleBoard saleBoard; // �Ǹ� �Խñ�
	public Member buyMember; // ������
	public Member sellMember; // �Ǹ���
	public int sellUserSatification; // �Ǹ��� �ŷ� ������
	public int buyUserSatification; // ������ �ŷ� ������

	public Deal() {
	} // Deal �⺻ ������

	public Deal(int dealNo, SaleBoard saleBoard, Member buyMember, Member sellMember) {
		super();
		this.dealNo = dealNo;
		this.dealTime = Calendar.getInstance();
		this.saleBoard = saleBoard;
		this.buyMember = buyMember;
		this.sellMember = sellMember;
		this.sellUserSatification = 0;
		this.buyUserSatification = 0;
	} // Deal ������

} // Deal Ŭ����
