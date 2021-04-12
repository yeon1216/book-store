package etc;

import java.util.Calendar;

import board.SaleBoard;
import user.Member;

public class Deal {
	public int dealNo; // 거래번호
	public Calendar dealTime; // 거래일자
	public SaleBoard saleBoard; // 판매 게시글
	public Member buyMember; // 구매자
	public Member sellMember; // 판매자
	public int sellUserSatification; // 판매자 거래 만족도
	public int buyUserSatification; // 구매자 거래 만족도

	public Deal() {
	} // Deal 기본 생성자

	public Deal(int dealNo, SaleBoard saleBoard, Member buyMember, Member sellMember) {
		super();
		this.dealNo = dealNo;
		this.dealTime = Calendar.getInstance();
		this.saleBoard = saleBoard;
		this.buyMember = buyMember;
		this.sellMember = sellMember;
		this.sellUserSatification = 0;
		this.buyUserSatification = 0;
	} // Deal 생성자

} // Deal 클래스
