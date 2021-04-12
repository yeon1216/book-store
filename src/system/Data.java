package system;

import java.util.ArrayList;

import board.Board;
import board.SaleBoard;
import etc.Book;
import etc.Deal;
import etc.Message;
import event.Adversity;
import event.CardGame;
import event.Event;
import event.ImmediateLotto;
import user.Admin;
import user.Member;
import user.User;

public class Data {
	public ArrayList<User> userList;
	public ArrayList<Book> bookList;
	public ArrayList<Board> boardList;
	public ArrayList<Deal> dealList;
	public ArrayList<Event> eventList;
	public ArrayList<Message> messageList;
	
	public Data(){
		
		// ����� å�� ��� ���� ����Ʈ
		userList = new ArrayList<>();
    	Admin admin = new Admin(0,"admin","123");
    	userList.add(admin);
    	Member member1 = new Member(1,"member1","123123","����","01011111111","19990406","����","���� ������");
    	member1.coin=1000000;
    	userList.add(member1);
    	Member member2 = new Member(2,"member2","123123","å����","01022222222","19891107","����","���� ���α�");
    	userList.add(member2);
    	Member member3 = new Member(3,"member3","123123","�輺��","01033333333","19931216","����","���� �̼���");
    	userList.add(member3);
    	Member member4 = new Member(4,"member4","123123","������","01044444444","19660708","����","�λ� �ؿ��");
    	userList.add(member4);
    	Member member5 = new Member(5,"member5","123123","å����","01055555555","19780719","����","��� ���ֽ�");
    	userList.add(member5);
    	Member member6 = new Member(6,"member6","123123","�輺��","01066666666","19931216","����","���� �̼���");
    	userList.add(member6);
    	Member member7 = new Member(7,"member7","123123","�Ǹ�¯","01077777777","19660725","����","���� ������");
    	userList.add(member7);
    	Member member8 = new Member(8,"member8","123123","���å","01088888888","19470830","����","��õ ������");
    	userList.add(member8);
    	
    	// ����� å���� ��� å ����Ʈ
        bookList = new ArrayList<>();
        Book book0 = new Book(0,"�װ� ������ �����̴� �԰� �;�","�鼼��", "������", 10000, 1);
        bookList.add(book0);
        Book book1 = new Book(1,"������ ����","�迵��", "������", 9000, 3);
        bookList.add(book1);
        Book book2 = new Book(2,"���μ��� �ѱ��� ����� 10","���μ�", "����", 9000, 2);
        bookList.add(book2);
        Book book3 = new Book(3,"���� ���� ������ ��","���ӽ� Ŭ����", "�ڱ���", 7000, 2);
        bookList.add(book3);
        Book book4 = new Book(4,"���󾲱��� ����","�ۼ���", "�ڱ���", 7000, 3);
        bookList.add(book4);
        Book book5 = new Book(5,"�̷��� �ռ����� 7���� ����","�ٱ�ġ ����Ĺ�", "�ڱ���", 11000, 1);
        bookList.add(book5);
        Book book6 = new Book(6,"����� ���","Ȳ����", "�Ҽ�", 9000, 2);
        bookList.add(book6);
        Book book7 = new Book(7,"������ ������ �ʴɷ�","�尭��", "�Ҽ�", 10000, 1);
        bookList.add(book7);
        Book book8 = new Book(8,"���� �÷��̸���Ʈ 2","�ȶ���", "�Ҽ�", 13000, 1);
        bookList.add(book8);
        Book book9 = new Book(9,"���� �÷��̸���Ʈ 2","�ȶ���", "�Ҽ�", 8000, 3);
        bookList.add(book9);
        Book book10 = new Book(10,"������ �Ϸ�","������", "�Ҽ�", 7000, 3);
        bookList.add(book10);
        Book book11 = new Book(11,"������ �Ϸ�","������", "�Ҽ�", 12000, 1);
        bookList.add(book11);
        Book book12 = new Book(12,"�װ� ������ �����̴� �԰� �;�","�鼼��", "������", 7000, 3);
        bookList.add(book12);
        Book book13 = new Book(13,"������ ����","�迵��", "������", 12000, 1);
        bookList.add(book13);
        Book book14 = new Book(14,"���μ��� �ѱ��� ����� 10","���μ�", "����", 10000, 1);
        bookList.add(book14);
        Book book15 = new Book(15,"����� ���","Ȳ����", "�Ҽ�", 11000, 1);
        bookList.add(book15);
        Book book16 = new Book(16,"������ ������ �ʴɷ�","�尭��", "�Ҽ�", 7000, 3);
        bookList.add(book16);
        Book book17 = new Book(17,"�̰� ������","�ֹ��", "����", 10000, 2);
        bookList.add(book17);
        Book book18 = new Book(18,"�̰� ������","�ֹ��", "����", 8000, 3);
        bookList.add(book18);
        Book book19 = new Book(19,"�̰� ������","�ֹ��", "����", 7000, 3);
        bookList.add(book19);
        Book book20 = new Book(20,"������ ������ ���","������", "�Ҽ�", 12000, 1);
        bookList.add(book20);
        Book book21 = new Book(21,"������ ������ ���","������", "�Ҽ�", 8000, 2);
        bookList.add(book21);
        Book book22 = new Book(22,"ö����","�ֺ�Ź", "�Ҽ�", 12000, 1);
        bookList.add(book22);
        Book book23 = new Book(23,"�̰� ������","�ֹ��", "����", 12000, 1);
        bookList.add(book23);
        Book book24 = new Book(24,"����� �Ѵٴ� ��","���� ũ��","����",14000,1);
        bookList.add(book24);
        Book book25 = new Book(25,"����� �Ѵٴ� ��","���� ũ��","����",10000,2);
        bookList.add(book25);
        Book book26 = new Book(26,"����� �Ѵٴ� ��","���� ũ��","����",8000,2);
        bookList.add(book26);
        Book book27 = new Book(27,"90����� �´�","������","����",12000,1);
        bookList.add(book27);
        Book book28 = new Book(28,"90����� �´�","������","����",5000,3);
        bookList.add(book28);
        Book book29 = new Book(29,"90����� �´�","������","����",7000,3);
        bookList.add(book29);
        Book book30 = new Book(30,"������ ����","�迵��", "������", 9000, 2);
        bookList.add(book30);
        Book book31 = new Book(31,"������ ����","�迵��", "������", 7000, 3);
        bookList.add(book31);
        Book book32 = new Book(32,"�������� ��õ�ϴ� ���� �޴� 56","������","�丮",9000,1);        
        bookList.add(book32);
        Book book33 = new Book(33,"�������� ��õ�ϴ� ���� �޴� 56","������","�丮",5000,3);        
        bookList.add(book33);
        Book book34 = new Book(34,"�������� ��õ�ϴ� ���� �޴� 56","������","�丮",8000,3);        
        bookList.add(book34);
        Book book35 = new Book(35,"����, ����","������","�Ҽ�",12000,1);        
        bookList.add(book35);
        Book book;
        book = new Book(bookList.size(),"����, ����","������","�Ҽ�",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"����, ����","������","�Ҽ�",6000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"����, ����","������","�Ҽ�",8000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�������� ������ ��","���ӽ� Ŭ����","�ڱ���",14000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"�������� ������ ��","���ӽ� Ŭ����","�ڱ���",9000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"������ ��߰�","��Ƽ�� ����","�ڱ���",11000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"������ ��߰�","��Ƽ�� ����","�ڱ���",9000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"������ ��߰�","��Ƽ�� ����","�ڱ���",6000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"������ ��߰�","��Ƽ�� ����","�ڱ���",7000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�޲ٴ� �ٶ���2","������","�ڱ���",1500,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�޲ٴ� �ٶ���2","������","�ڱ���",5000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"�޲ٴ� �ٶ���2","������","�ڱ���",7000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"�޲ٴ� �ٶ���2","������","�ڱ���",2000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�޲ٴ� �ٶ���1","������","�ڱ���",10000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"�޲ٴ� �ٶ���1","������","�ڱ���",8000,1);        
        bookList.add(book); //50
        book = new Book(bookList.size(),"�ǰݴ��� �ڵ��� ���� ����","�����","��ȸ����",14000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ǰݴ��� �ڵ��� ���� ����","�����","��ȸ����",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ǰݴ��� �ڵ��� ���� ����","�����","��ȸ����",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ǰݴ��� �ڵ��� ���� ����","�����","��ȸ����",3000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ǰݴ��� �ڵ��� ���� ����","�����","��ȸ����",6000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ٸ��� �ƴ϶� Ʋ�� �̴ϴ�","���ٿ�","��ȸ����",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ٸ��� �ƴ϶� Ʋ�� �̴ϴ�","���ٿ�","��ȸ����",5000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ٸ��� �ƴ϶� Ʋ�� �̴ϴ�","���ٿ�","��ȸ����",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ٸ��� �ƴ϶� Ʋ�� �̴ϴ�","���ٿ�","��ȸ����",3000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"�ٸ��� �ƴ϶� Ʋ�� �̴ϴ�","���ٿ�","��ȸ����",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"����Ʈ ���� ��ȸ","�Ƴ��� �⸮�ٶ�ٽ�","��ȸ����",15000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"����Ʈ ���� ��ȸ","�Ƴ��� �⸮�ٶ�ٽ�","��ȸ����",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"����Ʈ ���� ��ȸ","�Ƴ��� �⸮�ٶ�ٽ�","��ȸ����",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"����Ʈ ���� ��ȸ","�Ƴ��� �⸮�ٶ�ٽ�","��ȸ����",7000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"����Ʈ ���� ��ȸ","�Ƴ��� �⸮�ٶ�ٽ�","��ȸ����",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���ĵ� �̾����� �ʽ��ϴ�","��������","��ȸ����",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���ĵ� �̾����� �ʽ��ϴ�","��������","��ȸ����",11000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���ĵ� �̾����� �ʽ��ϴ�","��������","��ȸ����",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���ĵ� �̾����� �ʽ��ϴ�","��������","��ȸ����",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���ĵ� �̾����� �ʽ��ϴ�","��������","��ȸ����",8000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���������� ����","������","��ȸ����",12000,1);        
        bookList.add(book); // 70
        book = new Book(bookList.size(),"���������� ����","������","��ȸ����",10000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���������� ����","������","��ȸ����",9000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���������� ����","������","��ȸ����",6000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���������� ����","������","��ȸ����",8000,2);        
        bookList.add(book); 
        book = new Book(bookList.size(),"���������� ����","������","��ȸ����",6000,3);        
        bookList.add(book); // 75
        book = new Book(bookList.size(),"������ �����","�ֳ� �����ʵ�","��ȸ����",16000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"������ �����","�ֳ� �����ʵ�","��ȸ����",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"������ �����","�ֳ� �����ʵ�","��ȸ����",7000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"������ �����","�ֳ� �����ʵ�","��ȸ����",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���, ���, ȯ��","������","��ȸ����",8000,2);        
        bookList.add(book); // 80
        book = new Book(bookList.size(),"���, ���, ȯ��","������","��ȸ����",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���, ���, ȯ��","������","��ȸ����",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���, ���, ȯ��","������","��ȸ����",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���, ���, ȯ��","������","��ȸ����",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���θӸ� ������","�ֽ���","�����θ�",14000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���θӸ� ������","�ֽ���","�����θ�",11000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���θӸ� ������","�ֽ���","�����θ�",4000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���θӸ� ������","�ֽ���","�����θ�",8000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ���ΰ� ������ ���� ���δ� ���۵ȴ�","������","�����θ�",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ���ΰ� ������ ���� ���δ� ���۵ȴ�","������","�����θ�",11000,1);        
        bookList.add(book); // 90
        book = new Book(bookList.size(),"���� ���ΰ� ������ ���� ���δ� ���۵ȴ�","������","�����θ�",10000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ���ΰ� ������ ���� ���δ� ���۵ȴ�","������","�����θ�",9000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ���ΰ� ������ ���� ���δ� ���۵ȴ�","������","�����θ�",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ������ ��ó ���� �ʴ� ����","�̴ٶ�","�����θ�",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ������ ��ó ���� �ʴ� ����","�̴ٶ�","�����θ�",10000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ������ ��ó ���� �ʴ� ����","�̴ٶ�","�����θ�",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ������ ��ó ���� �ʴ� ����","�̴ٶ�","�����θ�",11000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� ������ ��ó ���� �ʴ� ����","�̴ٶ�","�����θ�",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� �ɸ� ����","�����","�����θ�",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� �ɸ� ����","�����","�����θ�",14000,1);        
        bookList.add(book); // 100
        book = new Book(bookList.size(),"���� �ɸ� ����","�����","�����θ�",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� �ɸ� ����","�����","�����θ�",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"���� �ɸ� ����","�����","�����θ�",7000,2);        
        bookList.add(book);
        
        // ����� å���� ��� �Խ� �� ����Ʈ
        boardList = new ArrayList<>();
        SaleBoard saleBoard0 = new SaleBoard(0, "�� å�� �����̸� �����ϴ� ����鿡�� �帳�ϴ�.",member1,book0);
        boardList.add(saleBoard0);
        saleBoard0.isSale=true;
        SaleBoard saleBoard1 = new SaleBoard(1, "������ �� �������� ��å���� �ˎ����ϴ�.",member2,book1);
        boardList.add(saleBoard1);
        saleBoard1.isSale=true;
        SaleBoard saleBoard2 = new SaleBoard(2, "���縦 �˾ƺ��ƿ�.",member3,book2);
        boardList.add(saleBoard2);
        SaleBoard saleBoard3 = new SaleBoard(3, "������ ���� �߿��մϴ�. �� å���� �˾ƺ��ƿ�.",member1,book3);
        boardList.add(saleBoard3);
        SaleBoard saleBoard4 = new SaleBoard(4, "����Ʈ���� �۰����� �����ϰ� 1õ ���� ���� �۰��� ���� ���ѹα� 1ȣ å���� ��ġ �ۼ����� å�� �Ͼ����",member7,book4);
        boardList.add(saleBoard4);
        SaleBoard saleBoard5 = new SaleBoard(5, "���� ����� ���������� ������ ����Ͻ� �����鿡�� ���� ����� �����ؿ� �����Դϴ�.",member2,book5);
        boardList.add(saleBoard5);
        
        // ����� å�� ��� �ŷ� ����Ʈ
        dealList = new ArrayList<>(); 
        Deal deal0 = new Deal(0,saleBoard0,member6,member1);
        dealList.add(deal0);
        member6.buyList.add(deal0);
        member1.sellList.add(deal0);
        Deal deal1 = new Deal(1,saleBoard1,member5,member3);
        dealList.add(deal1);
        member4.buyList.add(deal1);
        member3.sellList.add(deal1);
        
        // �޽��� ����Ʈ
        messageList = new ArrayList<>();
        Message m0 = new Message(0,userList.get(0),userList.get(1),userList.get(1).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m0);
        Message m1 = new Message(1,userList.get(0),userList.get(2),userList.get(2).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m1);
        Message m2 = new Message(2,userList.get(0),userList.get(3),userList.get(3).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m2);
        Message m3 = new Message(3,userList.get(0),userList.get(4),userList.get(4).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m3);
        Message m4 = new Message(4,userList.get(0),userList.get(5),userList.get(5).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m4);
        Message m5 = new Message(5,userList.get(0),userList.get(6),userList.get(6).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m5);
        Message m6 = new Message(6,userList.get(0),userList.get(7),userList.get(7).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m6);
        Message m7 = new Message(7,userList.get(0),userList.get(8),userList.get(8).id+"�� ����� å�忡 ���Ű��� ȯ���մϴ�.");
        messageList.add(m7);
        Message m8 = new Message(8,userList.get(1),userList.get(0),"�����ڴ� ���ǵ帱���� �ֽ��ϴ�.");
        messageList.add(m8);
        
        // �̺�Ʈ ����Ʈ
        eventList = new ArrayList<>(); 
        Adversity ad1 = new Adversity(0,"�����",
        		" ���ʺ����� ���� �ڵ�����"
        		+ "\n ������ 119�� ���� �������! ������� ��3,636���� (2015�� 8�� ~ 2019�� 5�� 1�� ����)"
        		+"\n"
        		+"\n >> �ּ� : ���� Ư���� ���۱� ��絿 ������"
        		+"\n"
        		+"\n >> ����ó : 010-7255-****"
        		+"\n"
        		+"\n >> ����� ��"
        		+"\n ������ �����ϰ� ����� ���ôº� �������̴� ���� ����������. "
        		+"\n�������� �������� �����. ������ ���Ʊ� �ϴ°� �ƴմϴ�."
        		+"\n"
        		+"\n >> ���Թ�� : û��    -->  �����ý�û    -->  ������û"
        		+"\n"
        		+"\n >> Ŀ��ŧ�� : ����(�ڹ� , �ȵ���̵�, php)  -->  ����1�ܰ�    -->  ����2�ܰ�"
        		+"\n");
        eventList.add(ad1);
        int[] tempArr={0,0,0,0,0,0};
        ImmediateLotto iLotto1 = new ImmediateLotto(1, "�ٷηζ�", tempArr);
        eventList.add(iLotto1);
        char[] cardArr={'A','B','C','D','E','F'};
        CardGame cardGame = new CardGame(2, "ī�����", cardArr);
        eventList.add(cardGame);
        
        
        
        // ������ʹ� ������ ����
        member1.wishList.add(saleBoard2);
        member1.wishList.add(saleBoard5);
        
        member4.isPossibleDeal = false;
        
        
	} // Data ������
} // Data Ŭ����
