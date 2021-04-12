package event;

import java.util.Random;

public class ImmediateLotto extends MiniGame{
	
	public int[] numArray; // ���ڹ迭
	public boolean isAleady; // �ζǰ��� ��������
	
	// ������
	public ImmediateLotto(int eventNo, String gameName, int[] numArray) {
		super(eventNo, gameName);
		this.numArray = numArray;
		this.isAleady = false;
	}
	
	// �ζǹ�ȣ ���� �޼ҵ�
	public void newLottoNumArr(){
		Random r = new Random();
		int[] arr = new int[6];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = r.nextInt(6);
		}
		this.numArray = arr;
	}
	
} // ImmediateLotto Ŭ����
