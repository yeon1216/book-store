package user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import board.Board;
import etc.Book;
import etc.Deal;

public class Admin extends User {
	public Admin() {
	} // Admin 기본 생성자

	public Admin(int memberNo, String id, String password) {
		super(memberNo, id, password);
	} // Admin 생성자

	// 유저번호로 멤버 찾는 메소드
	public Member findMemberByUserNo(ArrayList<User> userList, int userNo) {
		Member findMember = null;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Member) {
				Member tempMember = (Member) userList.get(i);
				if (tempMember.userNo == userNo) {
					findMember = tempMember;
				}
			}
		}
		return findMember;
	}
	
	// 게시글 번호로 책 찾기
	public Board findBoardByBoardNo(ArrayList<Board> boardList, int boardNo){
		Board findBoard = null;
		for (int i = 0; i < boardList.size(); i++) {
			if(boardList.get(i).boardNo==boardNo){
				findBoard = boardList.get(i);
			}
		}
		return findBoard;
	}

	// 책 번호로 책 찾기
	public Book findBookByBookNo(ArrayList<Book> bookList, int bookNo){
		Book findBook = null;
		for (int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).bookNo==bookNo){
				findBook = bookList.get(i);
			}
		}
		return findBook;
	} // 책 번호로 책 찾는 메소드
	
	// 콘솔용 멤버리스트 보기
	public synchronized void showMemberList(ArrayList<User> userList) {
		this.pageNamePrint("회원목록");
		Member tempM = null;
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i) instanceof Member) {
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tempM = (Member) userList.get(i);
				String memberInfo = "멤버 번호 : " + tempM.userNo + " / 아이디 : " + tempM.id + " / 이름  : " + tempM.name
						+ " / 전화번호 : " + tempM.phone + " / 온도 : " + tempM.grade;
				if (tempM.grade == 0) {
					memberInfo = "멤버 번호 : " + tempM.userNo + " / 아이디 : " + tempM.id + " / 이름  : " + tempM.name
							+ " / 전화번호 : " + tempM.phone + " / 온도 : 평가받지 않음";
				}
				System.out.print("<Admin>               ");
				System.out.println(memberInfo);
			}
		}
		this.showMemberDetail(userList);
	} // 멤버리스트 출력 메소드
	
	// 콘솔용 멤버상세 보기	
	public synchronized void showMemberDetail(ArrayList<User> userList) {
	// 필요 객체 생성
	Scanner sc = new Scanner(System.in);
	Admin admin = new Admin();
	while(true){
		System.out.println();
		System.out.print("<Admin>               ");
		System.out.print("확인하실 멤버의 번호를 입력해주세요(다른키: 뒤로가기) : ");
		int memberNo = sc.nextInt();
		if(0<memberNo &&memberNo<userList.size()) {
			Member tempM = new Member();
			tempM = admin.findMemberByUserNo(userList, memberNo);
			this.pageNamePrint("회원 상세보기");
			System.out.print("<Admin>               ");
			System.out.println(">> 멤버번호: " + tempM.userNo);
			System.out.print("<Admin>               ");
			System.out.println(">> 아이디: " + tempM.id);
			System.out.print("<Admin>               ");
            System.out.println(">> 이름: "+tempM.name);
            System.out.print("<Admin>               ");
            System.out.println(">> 전화번호: "+tempM.phone);
            System.out.print("<Admin>               ");
            System.out.println(">> 생년월일: "+tempM.birthday);
            System.out.print("<Admin>               ");
            System.out.println(">> 성별: "+tempM.gender);
            System.out.print("<Admin>               ");
            System.out.println(">> 주소: "+tempM.address);
            if(tempM.grade==0){
                System.out.print("<Admin>               ");
                System.out.println(">> 평점: 평가받지 않음");
            }else{
                System.out.print("<Admin>               ");
                System.out.println(">> 평점: "+tempM.grade);
            }
            if(tempM.isPossibleDeal){
            	System.out.print("<Admin>               ");
            	System.out.println(">> 거래가능 여부: 가능");
            	System.out.println();
                System.out.print("<Admin>               ");
                System.out.print("멤버의 거래를 정지시키시겠습니까? (0:거래정지, 다른키:뒤로가기) : ");
                int select = sc.nextInt();
                if(select==0){
                	// 거래정지
                	tempM.isPossibleDeal=false;
                	System.out.print("<Admin>               ");
    				System.out.println(tempM.id+"님의 거래를 정지시켰습니다.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }else{
                	System.out.print("<Admin>               ");
    				System.out.println("뒤로가기를 누르셨습니다.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }
            }else if(!tempM.isPossibleDeal){
            	System.out.print("<Admin>               ");
            	System.out.println(">> 거래가능 여부: 불가");
            	System.out.println();
                System.out.print("<Admin>               ");
                System.out.print("멤버의 거래정지를 해제시키시겠습니까? (0:거래정지 해제, 다른키:뒤로가기) : ");
                int select = sc.nextInt();
                if(select==0){
                	// 거래정지 해제
                	tempM.isPossibleDeal=true;
                	System.out.print("<Admin>               ");
    				System.out.println(tempM.id+"님의 거래정지를 해제시켰습니다.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }else{
                	System.out.print("<Admin>               ");
    				System.out.println("뒤로가기를 누르셨습니다.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }
            }
            
		}else{
			System.out.print("<Admin>               ");
			System.out.println("뒤로가기를 누르셨습니다.");
			System.out.println();
			break;
		}
	}
} // 멤버 상세보기 메소드

	// 콘솔용 거래리스트 보기
	public void showDealList(ArrayList<Deal> dealList){
			pageNamePrint("거래목록");
			Deal tempD = null;
			for (int i = 0; i < dealList.size(); i++) {
				tempD = dealList.get(i);
				String dealTimeToString = (tempD.dealTime.get(Calendar.YEAR))+"년 "+(tempD.dealTime.get(Calendar.MONTH)+1)+"월 "+(tempD.dealTime.get(Calendar.DAY_OF_MONTH))+"일 "+(tempD.dealTime.get(Calendar.HOUR_OF_DAY))+"시 "+(tempD.dealTime.get(Calendar.MINUTE))+"분 "+(tempD.dealTime.get(Calendar.SECOND))+"초";
		        String dealInfo ="거래 번호: "+tempD.dealNo+" / 거래 일자: "+dealTimeToString+" / 책 제목: "+tempD.saleBoard.saleBook.bookName+" / 구매자: "+tempD.buyMember.id+" / 구매자 거래 만족도: "+tempD.buyUserSatification+" / 판매자: "+tempD.sellMember.id+" / 판매자 거래 만족도: "+tempD.sellUserSatification;
		        if(tempD.sellUserSatification==0 && tempD.buyUserSatification==0){
		        	dealInfo ="거래 번호: "+tempD.dealNo+" / 거래 일자: "+dealTimeToString+" / 책 제목: "+tempD.saleBoard.saleBook.bookName+" / 구매자: "+tempD.buyMember.id+" / 구매자 거래 만족도: 평가안함 / 판매자: "+tempD.sellMember.id+" / 판매자 거래 만족도: 평가안함";
		        }else if(tempD.sellUserSatification==0 && tempD.buyUserSatification!=0){
		        	dealInfo ="거래 번호: "+tempD.dealNo+" / 거래 일자: "+dealTimeToString+" / 책 제목: "+tempD.saleBoard.saleBook.bookName+" / 구매자: "+tempD.buyMember.id+" / 구매자 거래 만족도: "+tempD.buyUserSatification+" / 판매자: "+tempD.sellMember.id+" / 판매자 거래 만족도: 평가안함";
		        }else if(tempD.sellUserSatification!=0 && tempD.buyUserSatification==0){
		        	dealInfo ="거래 번호: "+tempD.dealNo+" / 거래 일자: "+dealTimeToString+" / 책 제목: "+tempD.saleBoard.saleBook.bookName+" / 구매자: "+tempD.buyMember.id+" / 구매자 거래 만족도: 평가안함 / 판매자: "+tempD.sellMember.id+" / 판매자 거래 만족도: "+tempD.sellUserSatification;
		        }
		        try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.print("<Admin>               ");
		        System.out.println(dealInfo);
			}
			System.out.println();
		} // 모든 거래리스트 보기 메소드

	// 콘솔용 페이지 이름 출력 메소드
	public void pageNamePrint(String pageName){
		System.out.println();
		System.out.print("<Admin>               ");
		System.out.println("◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇◆◇");
		System.out.print("<Admin>               ");
		System.out.println(pageName+" 페이지입니다.");
		System.out.println();
	} // 페이지 이름 출력
	
	// 멤버리스트 보기
	public void showMemberListByGUI(ArrayList<User> userList, JTable table){
		Vector<String> headVector = new Vector<String>();
		headVector.add("멤버번호");
		headVector.add("아이디");
		headVector.add("이름");
		headVector.add("전화번호");
		headVector.add("온도");
		
		Vector<Vector> tableDataVector = new Vector<Vector>();
		for (int i = 0; i < userList.size(); i++) {
			if(userList.get(i) instanceof Member){
				Member tempM = (Member)userList.get(i);
				Vector<String> rowVector = new Vector<String>();
				rowVector.add(String.valueOf(tempM.userNo));
				rowVector.add(tempM.id);
				rowVector.add(tempM.name);
				rowVector.add(tempM.phone);
				if(tempM.grade==0){
					rowVector.add("평가받지 않음");
				}else{
					rowVector.add(String.valueOf(tempM.grade));
				}
				tableDataVector.add(rowVector);
			}
		}
		table.setModel(new DefaultTableModel(tableDataVector,headVector));
	} // 멤버리스트 보여주기 GUI

	// 멤버 상세 보기
	public void showMemberDetailGUI(ArrayList<User> userList, String userNo, JTextArea memberInfoTA){
		Member tempM = findMemberByUserNo(userList, Integer.parseInt(userNo));
		String grade;
		if(tempM.grade==0){
			grade="평가받지않음";
		}else{
			grade = String.valueOf(tempM.grade);
		}
		String possibleDeal;
		if(tempM.isPossibleDeal){
			possibleDeal = "거래가능";
		}else{
			possibleDeal = "거래불가";
		}
		String str = "  멤버번호  :   "+tempM.userNo
				+"\n  아이디 :   "+tempM.id
				+"\n  이름 :   "+tempM.name
				+"\n  전화번호 :   "+tempM.phone
				+"\n  생년월일 :   "+tempM.phone
				+"\n  성별 :   "+tempM.gender
				+"\n  주소 :   "+tempM.address
				+"\n  온도 :   "+grade
				+"\n  거래가능여부 :   "+possibleDeal;
		memberInfoTA.setText(str);
	} //멤버 상세보기 GUI 메소드

	// 거래중지/해제 메소드 
	public void dealPossibleCheck(ArrayList<User> userList, String userNo, JTextArea memberInfoTA){
		Member tempM = findMemberByUserNo(userList, Integer.parseInt(userNo));
		if(tempM.isPossibleDeal){
			tempM.isPossibleDeal=false;
		}else{
			tempM.isPossibleDeal=true;
		}
		this.showMemberDetailGUI(userList, userNo, memberInfoTA);
	}
	
	// 거래리스트 보기
	public void showDealListByGUI(ArrayList<Deal> dealList, JTable table) {
		Vector<String> headVector = new Vector<String>();
		headVector.add("거래번호");
		headVector.add("거래일자");
		headVector.add("책 제목");
		headVector.add("구매자   (멤버번호)");
		headVector.add("구매자 거래 만족도");
		headVector.add("판매자   (멤버번호)");
		headVector.add("판매자 거래 만족도");
		
		Vector<Vector> tableDataVector = new Vector<Vector>();
		for (int i = 0; i < dealList.size(); i++) {
			Deal tempD = dealList.get(i);
			Vector<String> rowVector = new Vector<String>();
			rowVector.add(String.valueOf(tempD.dealNo));
			String dealTimeToString = timeToString(tempD.dealTime); 
//					(tempD.dealTime.get(Calendar.YEAR))+"년 "+(tempD.dealTime.get(Calendar.MONTH)+1)+"월 "+(tempD.dealTime.get(Calendar.DAY_OF_MONTH))+"일 "+(tempD.dealTime.get(Calendar.HOUR_OF_DAY))+"시 "+(tempD.dealTime.get(Calendar.MINUTE))+"분 "+(tempD.dealTime.get(Calendar.SECOND))+"초";
			rowVector.add(dealTimeToString);
			rowVector.add(tempD.saleBoard.saleBook.bookName);
			rowVector.add(tempD.buyMember.id+"     ("+tempD.buyMember.userNo+")");
			if(tempD.buyUserSatification==0){
				rowVector.add("평가안함");
			}else{
				rowVector.add(String.valueOf(tempD.buyUserSatification));
			}
			rowVector.add(tempD.sellMember.id+"     ("+tempD.sellMember.userNo+")");
			if(tempD.sellUserSatification==0){
				rowVector.add("평가안함");
			}else{
				rowVector.add(String.valueOf(tempD.sellUserSatification));
			}
			tableDataVector.add(rowVector);
			
		}
		table.setModel(new DefaultTableModel(tableDataVector,headVector));
	} // 거래리스트 보여주기 GUI
	
	// Calendar를 년월일시분초로 반환 메소드
	public String timeToString(Calendar time) {
//		String timeToString = (time.get(Calendar.YEAR)) + "년 " + (time.get(Calendar.MONTH) + 1) + "월 "
//				+ (time.get(Calendar.DAY_OF_MONTH)) + "일 " + (time.get(Calendar.HOUR_OF_DAY)) + "시 "
//				+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
		String timeToString = (time.get(Calendar.MONTH) + 1) + "월 "
				+ (time.get(Calendar.DAY_OF_MONTH)) + "일 " + (time.get(Calendar.HOUR_OF_DAY)) + "시 "
				+ (time.get(Calendar.MINUTE)) + "분 " + (time.get(Calendar.SECOND)) + "초";
		return timeToString;
	}

} // Admin 클래스
