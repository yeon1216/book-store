package etc;

public class Book {
	public int bookNo; // å ��ȣ
	public String bookName; // å �̸�
	public String author; // ������
	public String genre; // �帣
	public int price; // ����
	public int bookState; //å�� ����
	
	public Book() {
	} // Book �⺻ ������

	public Book(int bookNo, String bookName, String author, String genre, int price, int bookState) {
		super();
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.author = author;
		this.genre = genre;
		this.price = price;
		this.bookState = bookState;
	} // Book ������
	
} // Book Ŭ����
