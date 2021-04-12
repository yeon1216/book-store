package board;

import java.util.Calendar;

import user.User;

public class Board {
	public int boardNo; // �Խñ� ��ȣ
	public String boardContent; // �Խñ� ����
	public Calendar boardWriteTime; // �Խñ� �ۼ� �ð�
	public User boardWriteUser; // �Խñ� �ۼ���
	
	public Board() {
	} // Board �⺻ ������

	public Board(int boardNo, String boardContent, User boardWriteUser) {
		this.boardNo = boardNo;
		this.boardContent = boardContent;
		this.boardWriteTime = Calendar.getInstance();
		this.boardWriteUser = boardWriteUser;
	} // Board ������
	
} // Board Ŭ����
