package event;

import user.Member;

public class MiniGame extends Event{
	public String gameName; // �����̸�
	
	// �⺻ ������
	public MiniGame() { }

	// ������
	public MiniGame(int eventNo, String gameName) {
		super(eventNo);
		this.gameName = gameName;
	}
	
} // MiniGame Ŭ����
