package etc;

import user.Member;

public class Coupon {
	public int couponNo; // ������ȣ
	public String couponName; // ���� �̸�
	public int discountPrice; // ���αݾ�
	public Member couponMember; // ���� ������
//	public Calendar expirationDate; // ��ȿ�Ⱓ
	
	public Coupon() {
	} // Coupon �⺻ ������

	public Coupon(int couponNo, String couponName, int discountPrice, Member couponMember) {
		this.couponNo = couponNo;
		this.couponName = couponName;
		this.discountPrice = discountPrice;
		this.couponMember = couponMember;
	} // Counpon ������
	
} // Coupon Ŭ����
