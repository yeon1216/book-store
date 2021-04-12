package etc;

public class Book {
	public int bookNo; // 책 번호
	public String bookName; // 책 이름
	public String author; // 지은이
	public String genre; // 장르
	public int price; // 가격
	public int bookState; //책의 상태
	
	public Book() {
	} // Book 기본 생성자

	public Book(int bookNo, String bookName, String author, String genre, int price, int bookState) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.price = price;
		this.bookState = bookState;
	} // Book 생성자
	
} // Book 클래스
