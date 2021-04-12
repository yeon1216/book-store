package event;

import java.util.Random;

public class CardGame extends MiniGame{
	
	public char[] cardArr; // ī��迭
	public boolean isAleady; // ī����� ��������
	
	// ������
	public CardGame(int eventNo, String gameName, char[] cardArr) {
		super(eventNo, gameName);
		this.cardArr = cardArr;
		this.isAleady = false;
	}
	
	public void mixCard(){
		Random r = new Random();
		boolean[] checkArr = new boolean[6];
		char[] oldCardArr={'A','B','C','D','E','F'};
		char[] newCardArr = new char[6];
		for (int i = 0; i < cardArr.length; i++) {
			int tempNum=r.nextInt(6);
			if(!checkArr[tempNum]){
				newCardArr[i]=oldCardArr[tempNum];
				checkArr[tempNum]=true;
			}else{
				i--;
			}
		}
		this.cardArr = newCardArr;
	}
	
} // ī����� Ŭ����