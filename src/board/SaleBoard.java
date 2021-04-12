package board;

import etc.Book;
import user.User;

public class SaleBoard extends Board{
	public Book saleBook; // �Ǹ��� å
	public boolean isSale; // å �Ǹ� ����
	
	public SaleBoard() {
	} // SaleBoard �⺻ ������
	
	public SaleBoard(int boardNo, String boardContent, User boardWriteUser, Book saleBook) {
		super(boardNo, boardContent, boardWriteUser);
		this.saleBook = saleBook;
		this.isSale = false;
	} // SaleBoard ������

} // SaleBoard Ŭ����
