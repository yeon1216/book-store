package event;

import user.Member;

public class MiniGame extends Event{
	public String gameName; // 게임이름
	
	// 기본 생성자
	public MiniGame() { }

	// 생성자
	public MiniGame(int eventNo, String gameName) {
		super(eventNo);
		this.gameName = gameName;
	}
	
} // MiniGame 클래스
