package event;

import user.Member;

public class Adversity extends Event{
	public String companyName; // 회사명
	public String adversityContent; //광고내용
	public boolean isRead; // 광고를 읽었는지 여부
	public boolean isAleady; // 광고이벤트 참여여부
	
	// 기본생성자
	public Adversity() { }

	// 생성자
	public Adversity(int eventNo, String companyName, String adversityContent) {
		super(eventNo);
		this.companyName = companyName;
		this.adversityContent = adversityContent;
		this.isRead = false;
		this.isAleady = false;
	}
	
} // Adversity 클래스
