package board;

import etc.Book;
import user.User;

public class SaleBoard extends Board{
	public Book saleBook; // 판매할 책
	public boolean isSale; // 책 판매 여부
	
	public SaleBoard() {
	} // SaleBoard 기본 생성자
	
	public SaleBoard(int boardNo, String boardContent, User boardWriteUser, Book saleBook) {
		super(boardNo, boardContent, boardWriteUser);
		this.saleBook = saleBook;
		this.isSale = false;
	} // SaleBoard 생성자

} // SaleBoard 클래스
