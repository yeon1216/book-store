package event;

import java.util.Random;

public class ImmediateLotto extends MiniGame{
	
	public int[] numArray; // 숫자배열
	public boolean isAleady; // 로또게임 참여여부
	
	// 생성자
	public ImmediateLotto(int eventNo, String gameName, int[] numArray) {
		super(eventNo, gameName);
		this.numArray = numArray;
		this.isAleady = false;
	}
	
	// 로또번호 생성 메소드
	public void newLottoNumArr(){
		Random r = new Random();
		int[] arr = new int[6];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(6);
		}
		this.numArray = arr;
	}
	
} // ImmediateLotto 클래스
