package board;

import java.util.Calendar;

import user.User;

public class Board {
	public int boardNo; // 게시글 번호
	public String boardContent; // 게시글 내용
	public Calendar boardWriteTime; // 게시글 작성 시간
	public User boardWriteUser; // 게시글 작성자
	
	public Board() {
	} // Board 기본 생성자

	public Board(int boardNo, String boardContent, User boardWriteUser) {
		this.boardNo = boardNo;
		this.boardContent = boardContent;
		this.boardWriteTime = Calendar.getInstance();
		this.boardWriteUser = boardWriteUser;
	} // Board 생성자
	
} // Board 클래스
