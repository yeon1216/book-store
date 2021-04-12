package etc;

import user.Member;

public class Coupon {
	public int couponNo; // 쿠폰번호
	public String couponName; // 쿠폰 이름
	public int discountPrice; // 할인금액
	public Member couponMember; // 쿠폰 소지자
//	public Calendar expirationDate; // 유효기간
	
	public Coupon() {
	} // Coupon 기본 생성자

	public Coupon(int couponNo, String couponName, int discountPrice, Member couponMember) {
		this.couponNo = couponNo;
		this.couponName = couponName;
		this.discountPrice = discountPrice;
		this.couponMember = couponMember;
	} // Counpon 생성자
	
} // Coupon 클래스
