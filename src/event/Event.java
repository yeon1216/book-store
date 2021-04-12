package event;

import user.Member;

public class Event {
	
	int eventNo; // 이벤트 번호
	Member participantMember; // 이벤트 참여자
	
	// 기본 생성자
	public Event() {
	}

	// 생성자
	public Event(int eventNo) {
		this.eventNo = eventNo;
	}
	
} // Event 클래스
