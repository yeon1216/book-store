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
		
		// 모두의 책장 모든 유저 리스트
		userList = new ArrayList<>();
    	Admin admin = new Admin(0,"admin","123");
    	userList.add(admin);
    	Member member1 = new Member(1,"member1","123123","장사왕","01011111111","19990406","여자","서울 영등포");
    	member1.coin=1000000;
    	userList.add(member1);
    	Member member2 = new Member(2,"member2","123123","책많음","01022222222","19891107","남자","서울 종로구");
    	userList.add(member2);
    	Member member3 = new Member(3,"member3","123123","김성연","01033333333","19931216","남자","서울 이수역");
    	userList.add(member3);
    	Member member4 = new Member(4,"member4","123123","돈많음","01044444444","19660708","남자","부산 해운대");
    	userList.add(member4);
    	Member member5 = new Member(5,"member5","123123","책좋아","01055555555","19780719","여자","경북 상주시");
    	userList.add(member5);
    	Member member6 = new Member(6,"member6","123123","김성연","01066666666","19931216","남자","서울 이수역");
    	userList.add(member6);
    	Member member7 = new Member(7,"member7","123123","판매짱","01077777777","19660725","여자","서울 남성역");
    	userList.add(member7);
    	Member member8 = new Member(8,"member8","123123","모두책","01088888888","19470830","남자","인천 구월동");
    	userList.add(member8);
    	
    	// 모두의 책장의 모든 책 리스트
        bookList = new ArrayList<>();
        Book book0 = new Book(0,"죽고 싶지만 떡볶이는 먹고 싶어","백세희", "에세이", 10000, 1);
        bookList.add(book0);
        Book book1 = new Book(1,"여행의 이유","김영하", "에세이", 9000, 3);
        bookList.add(book1);
        Book book2 = new Book(2,"설민석의 한국사 대모험 10","설민석", "역사", 9000, 2);
        bookList.add(book2);
        Book book3 = new Book(3,"아주 작은 습관의 힘","제임스 클리어", "자기계발", 7000, 2);
        bookList.add(book3);
        Book book4 = new Book(4,"따라쓰기의 기적","송숙희", "자기계발", 7000, 3);
        bookList.add(book4);
        Book book5 = new Book(5,"미래를 앞서가는 7가지 통찰","다구치 요시후미", "자기계발", 11000, 1);
        bookList.add(book5);
        Book book6 = new Book(6,"디디의 우산","황정은", "소설", 9000, 2);
        bookList.add(book6);
        Book book7 = new Book(7,"지극히 사적인 초능력","장강명", "소설", 10000, 1);
        bookList.add(book7);
        Book book8 = new Book(8,"연애 플레이리스트 2","안또이", "소설", 13000, 1);
        bookList.add(book8);
        Book book9 = new Book(9,"연애 플레이리스트 2","안또이", "소설", 8000, 3);
        bookList.add(book9);
        Book book10 = new Book(10,"유라의 하루","김진국", "소설", 7000, 3);
        bookList.add(book10);
        Book book11 = new Book(11,"유라의 하루","김진국", "소설", 12000, 1);
        bookList.add(book11);
        Book book12 = new Book(12,"죽고 싶지만 떡볶이는 먹고 싶어","백세희", "에세이", 7000, 3);
        bookList.add(book12);
        Book book13 = new Book(13,"여행의 이유","김영하", "에세이", 12000, 1);
        bookList.add(book13);
        Book book14 = new Book(14,"설민석의 한국사 대모험 10","설민석", "역사", 10000, 1);
        bookList.add(book14);
        Book book15 = new Book(15,"디디의 우산","황정은", "소설", 11000, 1);
        bookList.add(book15);
        Book book16 = new Book(16,"지극히 사적인 초능력","장강명", "소설", 7000, 3);
        bookList.add(book16);
        Book book17 = new Book(17,"이게 경제다","최배근", "경제", 10000, 2);
        bookList.add(book17);
        Book book18 = new Book(18,"이게 경제다","최배근", "경제", 8000, 3);
        bookList.add(book18);
        Book book19 = new Book(19,"이게 경제다","최배근", "경제", 7000, 3);
        bookList.add(book19);
        Book book20 = new Book(20,"마지막 마음의 기록","나형수", "소설", 12000, 1);
        bookList.add(book20);
        Book book21 = new Book(21,"마지막 마음의 기록","나형수", "소설", 8000, 2);
        bookList.add(book21);
        Book book22 = new Book(22,"철부지","최병탁", "소설", 12000, 1);
        bookList.add(book22);
        Book book23 = new Book(23,"이게 경제다","최배근", "경제", 12000, 1);
        bookList.add(book23);
        Book book24 = new Book(24,"사업을 한다는 것","레이 크록","경제",14000,1);
        bookList.add(book24);
        Book book25 = new Book(25,"사업을 한다는 것","레이 크록","경제",10000,2);
        bookList.add(book25);
        Book book26 = new Book(26,"사업을 한다는 것","레이 크록","경제",8000,2);
        bookList.add(book26);
        Book book27 = new Book(27,"90년생이 온다","임흥택","경제",12000,1);
        bookList.add(book27);
        Book book28 = new Book(28,"90년생이 온다","임흥택","경제",5000,3);
        bookList.add(book28);
        Book book29 = new Book(29,"90년생이 온다","임흥택","경제",7000,3);
        bookList.add(book29);
        Book book30 = new Book(30,"여행의 이유","김영하", "에세이", 9000, 2);
        bookList.add(book30);
        Book book31 = new Book(31,"여행의 이유","김영하", "에세이", 7000, 3);
        bookList.add(book31);
        Book book32 = new Book(32,"백종원이 추천하는 집밥 메뉴 56","백종원","요리",9000,1);        
        bookList.add(book32);
        Book book33 = new Book(33,"백종원이 추천하는 집밥 메뉴 56","백종원","요리",5000,3);        
        bookList.add(book33);
        Book book34 = new Book(34,"백종원이 추천하는 집밥 메뉴 56","백종원","요리",8000,3);        
        bookList.add(book34);
        Book book35 = new Book(35,"진이, 지니","정유정","소설",12000,1);        
        bookList.add(book35);
        Book book;
        book = new Book(bookList.size(),"진이, 지니","정유정","소설",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"진이, 지니","정유정","소설",6000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"진이, 지니","정유정","소설",8000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"아주작은 습관의 힘","제임스 클리어","자기계발",14000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"아주작은 습관의 힘","제임스 클리어","자기계발",9000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"습관의 재발견","스티븐 기즈","자기계발",11000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"습관의 재발견","스티븐 기즈","자기계발",9000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"습관의 재발견","스티븐 기즈","자기계발",6000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"습관의 재발견","스티븐 기즈","자기계발",7000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"꿈꾸는 다락방2","이지성","자기계발",1500,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"꿈꾸는 다락방2","이지성","자기계발",5000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"꿈꾸는 다락방2","이지성","자기계발",7000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"꿈꾸는 다락방2","이지성","자기계발",2000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"꿈꾸는 다락방1","이지성","자기계발",10000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"꿈꾸는 다락방1","이지성","자기계발",8000,1);        
        bookList.add(book); //50
        book = new Book(bookList.size(),"실격당한 자들을 위한 변론","김원영","사회과학",14000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"실격당한 자들을 위한 변론","김원영","사회과학",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"실격당한 자들을 위한 변론","김원영","사회과학",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"실격당한 자들을 위한 변론","김원영","사회과학",3000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"실격당한 자들을 위한 변론","김원영","사회과학",6000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"다른게 아니라 틀린 겁니다","위근우","사회과학",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"다른게 아니라 틀린 겁니다","위근우","사회과학",5000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"다른게 아니라 틀린 겁니다","위근우","사회과학",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"다른게 아니라 틀린 겁니다","위근우","사회과학",3000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"다른게 아니라 틀린 겁니다","위근우","사회과학",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"엘리트 독식 사회","아난드 기리다라다스","사회과학",15000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"엘리트 독식 사회","아난드 기리다라다스","사회과학",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"엘리트 독식 사회","아난드 기리다라다스","사회과학",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"엘리트 독식 사회","아난드 기리다라다스","사회과학",7000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"엘리트 독식 사회","아난드 기리다라다스","사회과학",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"아파도 미안하지 않습니다","조한진희","사회과학",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"아파도 미안하지 않습니다","조한진희","사회과학",11000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"아파도 미안하지 않습니다","조한진희","사회과학",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"아파도 미안하지 않습니다","조한진희","사회과학",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"아파도 미안하지 않습니다","조한진희","사회과학",8000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"개인주의자 선언","문유석","사회과학",12000,1);        
        bookList.add(book); // 70
        book = new Book(bookList.size(),"개인주의자 선언","문유석","사회과학",10000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"개인주의자 선언","문유석","사회과학",9000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"개인주의자 선언","문유석","사회과학",6000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"개인주의자 선언","문유석","사회과학",8000,2);        
        bookList.add(book); 
        book = new Book(bookList.size(),"개인주의자 선언","문유석","사회과학",6000,3);        
        bookList.add(book); // 75
        book = new Book(bookList.size(),"마지막 계승자","애나 파이필드","사회과학",16000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"마지막 계승자","애나 파이필드","사회과학",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"마지막 계승자","애나 파이필드","사회과학",7000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"마지막 계승자","애나 파이필드","사회과학",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"사람, 장소, 환대","김현경","사회과학",8000,2);        
        bookList.add(book); // 80
        book = new Book(bookList.size(),"사람, 장소, 환대","김현경","사회과학",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"사람, 장소, 환대","김현경","사회과학",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"사람, 장소, 환대","김현경","사회과학",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"사람, 장소, 환대","김현경","사회과학",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"공부머리 독서법","최승필","좋은부모",14000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"공부머리 독서법","최승필","좋은부모",11000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"공부머리 독서법","최승필","좋은부모",4000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"공부머리 독서법","최승필","좋은부모",8000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 공부가 끝나면 아이 공부는 시작된다","서안정","좋은부모",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 공부가 끝나면 아이 공부는 시작된다","서안정","좋은부모",11000,1);        
        bookList.add(book); // 90
        book = new Book(bookList.size(),"엄마 공부가 끝나면 아이 공부는 시작된다","서안정","좋은부모",10000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 공부가 끝나면 아이 공부는 시작된다","서안정","좋은부모",9000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 공부가 끝나면 아이 공부는 시작된다","서안정","좋은부모",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"아이 마음에 상처 주지 않는 습관","이다랑","좋은부모",13000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"아이 마음에 상처 주지 않는 습관","이다랑","좋은부모",10000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"아이 마음에 상처 주지 않는 습관","이다랑","좋은부모",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"아이 마음에 상처 주지 않는 습관","이다랑","좋은부모",11000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"아이 마음에 상처 주지 않는 습관","이다랑","좋은부모",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 심리 수업","윤우상","좋은부모",5000,3);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 심리 수업","윤우상","좋은부모",14000,1);        
        bookList.add(book); // 100
        book = new Book(bookList.size(),"엄마 심리 수업","윤우상","좋은부모",12000,1);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 심리 수업","윤우상","좋은부모",8000,2);        
        bookList.add(book);
        book = new Book(bookList.size(),"엄마 심리 수업","윤우상","좋은부모",7000,2);        
        bookList.add(book);
        
        // 모드의 책장의 모든 게시 글 리스트
        boardList = new ArrayList<>();
        SaleBoard saleBoard0 = new SaleBoard(0, "이 책을 떡볶이를 좋아하는 사람들에게 드립니다.",member1,book0);
        boardList.add(saleBoard0);
        saleBoard0.isSale=true;
        SaleBoard saleBoard1 = new SaleBoard(1, "여행을 왜 떠나는지 이책에서 알렫립니다.",member2,book1);
        boardList.add(saleBoard1);
        saleBoard1.isSale=true;
        SaleBoard saleBoard2 = new SaleBoard(2, "역사를 알아보아요.",member3,book2);
        boardList.add(saleBoard2);
        SaleBoard saleBoard3 = new SaleBoard(3, "습관은 아주 중요합니다. 이 책에서 알아보아요.",member1,book3);
        boardList.add(saleBoard3);
        SaleBoard saleBoard4 = new SaleBoard(4, "베스트셀러 작가들을 배출하고 1천 명의 예비 작가를 만든 대한민국 1호 책쓰기 코치 송숙희의 책을 일어보세요",member7,book4);
        boardList.add(saleBoard4);
        SaleBoard saleBoard5 = new SaleBoard(5, "동양 사상의 전문가이자 수많은 비즈니스 리더들에게 동양 사상을 강연해온 저자입니다.",member2,book5);
        boardList.add(saleBoard5);
        
        // 모두의 책장 모든 거래 리스트
        dealList = new ArrayList<>(); 
        Deal deal0 = new Deal(0,saleBoard0,member6,member1);
        dealList.add(deal0);
        member6.buyList.add(deal0);
        member1.sellList.add(deal0);
        Deal deal1 = new Deal(1,saleBoard1,member5,member3);
        dealList.add(deal1);
        member4.buyList.add(deal1);
        member3.sellList.add(deal1);
        
        // 메시지 리스트
        messageList = new ArrayList<>();
        Message m0 = new Message(0,userList.get(0),userList.get(1),userList.get(1).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m0);
        Message m1 = new Message(1,userList.get(0),userList.get(2),userList.get(2).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m1);
        Message m2 = new Message(2,userList.get(0),userList.get(3),userList.get(3).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m2);
        Message m3 = new Message(3,userList.get(0),userList.get(4),userList.get(4).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m3);
        Message m4 = new Message(4,userList.get(0),userList.get(5),userList.get(5).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m4);
        Message m5 = new Message(5,userList.get(0),userList.get(6),userList.get(6).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m5);
        Message m6 = new Message(6,userList.get(0),userList.get(7),userList.get(7).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m6);
        Message m7 = new Message(7,userList.get(0),userList.get(8),userList.get(8).id+"님 모두의 책장에 오신것을 환영합니다.");
        messageList.add(m7);
        Message m8 = new Message(8,userList.get(1),userList.get(0),"관리자님 문의드릴것이 있습니다.");
        messageList.add(m8);
        
        // 이벤트 리스트
        eventList = new ArrayList<>(); 
        Adversity ad1 = new Adversity(0,"팀노바",
        		" 왕초보전문 성인 코딩교육"
        		+ "\n 수료자 119명 전원 취업성공! 연봉평균 약3,636만원 (2015년 8월 ~ 2019년 5월 1일 기준)"
        		+"\n"
        		+"\n >> 주소 : 서울 특별시 동작구 사당동 남성역"
        		+"\n"
        		+"\n >> 연락처 : 010-7255-****"
        		+"\n"
        		+"\n >> 당부의 말"
        		+"\n 가볍게 생각하고 팀노바 오시는분 돈낭비이니 제발 오지마세요. "
        		+"\n맛보려고 오시지도 말고요. 개발자 맛뵈기 하는곳 아닙니다."
        		+"\n"
        		+"\n >> 가입방법 : 청강    -->  웨이팅신청    -->  수강신청"
        		+"\n"
        		+"\n >> 커리큘럼 : 기초(자바 , 안드로이드, php)  -->  응용1단계    -->  응용2단계"
        		+"\n");
        eventList.add(ad1);
        int[] tempArr={0,0,0,0,0,0};
        ImmediateLotto iLotto1 = new ImmediateLotto(1, "바로로또", tempArr);
        eventList.add(iLotto1);
        char[] cardArr={'A','B','C','D','E','F'};
        CardGame cardGame = new CardGame(2, "카드게임", cardArr);
        eventList.add(cardGame);
        
        
        
        // 여기부터는 데이터 세팅
        member1.wishList.add(saleBoard2);
        member1.wishList.add(saleBoard5);
        
        member4.isPossibleDeal = false;
        
        
	} // Data 생성자
} // Data 클래스
