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
	} // Admin �⺻ ������

	public Admin(int memberNo, String id, String password) {
		super(memberNo, id, password);
	} // Admin ������

	// ������ȣ�� ��� ã�� �޼ҵ�
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
	
	// �Խñ� ��ȣ�� å ã��
	public Board findBoardByBoardNo(ArrayList<Board> boardList, int boardNo){
		Board findBoard = null;
		for (int i = 0; i < boardList.size(); i++) {
			if(boardList.get(i).boardNo==boardNo){
				findBoard = boardList.get(i);
			}
		}
		return findBoard;
	}

	// å ��ȣ�� å ã��
	public Book findBookByBookNo(ArrayList<Book> bookList, int bookNo){
		Book findBook = null;
		for (int i = 0; i < bookList.size(); i++) {
			if(bookList.get(i).bookNo==bookNo){
				findBook = bookList.get(i);
			}
		}
		return findBook;
	} // å ��ȣ�� å ã�� �޼ҵ�
	
	// �ֿܼ� �������Ʈ ����
	public synchronized void showMemberList(ArrayList<User> userList) {
		this.pageNamePrint("ȸ�����");
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
				String memberInfo = "��� ��ȣ : " + tempM.userNo + " / ���̵� : " + tempM.id + " / �̸�  : " + tempM.name
						+ " / ��ȭ��ȣ : " + tempM.phone + " / �µ� : " + tempM.grade;
				if (tempM.grade == 0) {
					memberInfo = "��� ��ȣ : " + tempM.userNo + " / ���̵� : " + tempM.id + " / �̸�  : " + tempM.name
							+ " / ��ȭ��ȣ : " + tempM.phone + " / �µ� : �򰡹��� ����";
				}
				System.out.print("<Admin>               ");
				System.out.println(memberInfo);
			}
		}
		this.showMemberDetail(userList);
	} // �������Ʈ ��� �޼ҵ�
	
	// �ֿܼ� ����� ����	
	public synchronized void showMemberDetail(ArrayList<User> userList) {
	// �ʿ� ��ü ����
	Scanner sc = new Scanner(System.in);
	Admin admin = new Admin();
	while(true){
		System.out.println();
		System.out.print("<Admin>               ");
		System.out.print("Ȯ���Ͻ� ����� ��ȣ�� �Է����ּ���(�ٸ�Ű: �ڷΰ���) : ");
		int memberNo = sc.nextInt();
		if(0<memberNo &&memberNo<userList.size()) {
			Member tempM = new Member();
			tempM = admin.findMemberByUserNo(userList, memberNo);
			this.pageNamePrint("ȸ�� �󼼺���");
			System.out.print("<Admin>               ");
			System.out.println(">> �����ȣ: " + tempM.userNo);
			System.out.print("<Admin>               ");
			System.out.println(">> ���̵�: " + tempM.id);
			System.out.print("<Admin>               ");
            System.out.println(">> �̸�: "+tempM.name);
            System.out.print("<Admin>               ");
            System.out.println(">> ��ȭ��ȣ: "+tempM.phone);
            System.out.print("<Admin>               ");
            System.out.println(">> �������: "+tempM.birthday);
            System.out.print("<Admin>               ");
            System.out.println(">> ����: "+tempM.gender);
            System.out.print("<Admin>               ");
            System.out.println(">> �ּ�: "+tempM.address);
            if(tempM.grade==0){
                System.out.print("<Admin>               ");
                System.out.println(">> ����: �򰡹��� ����");
            }else{
                System.out.print("<Admin>               ");
                System.out.println(">> ����: "+tempM.grade);
            }
            if(tempM.isPossibleDeal){
            	System.out.print("<Admin>               ");
            	System.out.println(">> �ŷ����� ����: ����");
            	System.out.println();
                System.out.print("<Admin>               ");
                System.out.print("����� �ŷ��� ������Ű�ðڽ��ϱ�? (0:�ŷ�����, �ٸ�Ű:�ڷΰ���) : ");
                int select = sc.nextInt();
                if(select==0){
                	// �ŷ�����
                	tempM.isPossibleDeal=false;
                	System.out.print("<Admin>               ");
    				System.out.println(tempM.id+"���� �ŷ��� �������׽��ϴ�.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }else{
                	System.out.print("<Admin>               ");
    				System.out.println("�ڷΰ��⸦ �����̽��ϴ�.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }
            }else if(!tempM.isPossibleDeal){
            	System.out.print("<Admin>               ");
            	System.out.println(">> �ŷ����� ����: �Ұ�");
            	System.out.println();
                System.out.print("<Admin>               ");
                System.out.print("����� �ŷ������� ������Ű�ðڽ��ϱ�? (0:�ŷ����� ����, �ٸ�Ű:�ڷΰ���) : ");
                int select = sc.nextInt();
                if(select==0){
                	// �ŷ����� ����
                	tempM.isPossibleDeal=true;
                	System.out.print("<Admin>               ");
    				System.out.println(tempM.id+"���� �ŷ������� �������׽��ϴ�.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }else{
                	System.out.print("<Admin>               ");
    				System.out.println("�ڷΰ��⸦ �����̽��ϴ�.");
    				System.out.println();
    				this.showMemberList(userList);
    				break;
                }
            }
            
		}else{
			System.out.print("<Admin>               ");
			System.out.println("�ڷΰ��⸦ �����̽��ϴ�.");
			System.out.println();
			break;
		}
	}
} // ��� �󼼺��� �޼ҵ�

	// �ֿܼ� �ŷ�����Ʈ ����
	public void showDealList(ArrayList<Deal> dealList){
			pageNamePrint("�ŷ����");
			Deal tempD = null;
			for (int i = 0; i < dealList.size(); i++) {
				tempD = dealList.get(i);
				String dealTimeToString = (tempD.dealTime.get(Calendar.YEAR))+"�� "+(tempD.dealTime.get(Calendar.MONTH)+1)+"�� "+(tempD.dealTime.get(Calendar.DAY_OF_MONTH))+"�� "+(tempD.dealTime.get(Calendar.HOUR_OF_DAY))+"�� "+(tempD.dealTime.get(Calendar.MINUTE))+"�� "+(tempD.dealTime.get(Calendar.SECOND))+"��";
		        String dealInfo ="�ŷ� ��ȣ: "+tempD.dealNo+" / �ŷ� ����: "+dealTimeToString+" / å ����: "+tempD.saleBoard.saleBook.bookName+" / ������: "+tempD.buyMember.id+" / ������ �ŷ� ������: "+tempD.buyUserSatification+" / �Ǹ���: "+tempD.sellMember.id+" / �Ǹ��� �ŷ� ������: "+tempD.sellUserSatification;
		        if(tempD.sellUserSatification==0 && tempD.buyUserSatification==0){
		        	dealInfo ="�ŷ� ��ȣ: "+tempD.dealNo+" / �ŷ� ����: "+dealTimeToString+" / å ����: "+tempD.saleBoard.saleBook.bookName+" / ������: "+tempD.buyMember.id+" / ������ �ŷ� ������: �򰡾��� / �Ǹ���: "+tempD.sellMember.id+" / �Ǹ��� �ŷ� ������: �򰡾���";
		        }else if(tempD.sellUserSatification==0 && tempD.buyUserSatification!=0){
		        	dealInfo ="�ŷ� ��ȣ: "+tempD.dealNo+" / �ŷ� ����: "+dealTimeToString+" / å ����: "+tempD.saleBoard.saleBook.bookName+" / ������: "+tempD.buyMember.id+" / ������ �ŷ� ������: "+tempD.buyUserSatification+" / �Ǹ���: "+tempD.sellMember.id+" / �Ǹ��� �ŷ� ������: �򰡾���";
		        }else if(tempD.sellUserSatification!=0 && tempD.buyUserSatification==0){
		        	dealInfo ="�ŷ� ��ȣ: "+tempD.dealNo+" / �ŷ� ����: "+dealTimeToString+" / å ����: "+tempD.saleBoard.saleBook.bookName+" / ������: "+tempD.buyMember.id+" / ������ �ŷ� ������: �򰡾��� / �Ǹ���: "+tempD.sellMember.id+" / �Ǹ��� �ŷ� ������: "+tempD.sellUserSatification;
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
		} // ��� �ŷ�����Ʈ ���� �޼ҵ�

	// �ֿܼ� ������ �̸� ��� �޼ҵ�
	public void pageNamePrint(String pageName){
		System.out.println();
		System.out.print("<Admin>               ");
		System.out.println("�ߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡޡߡ�");
		System.out.print("<Admin>               ");
		System.out.println(pageName+" �������Դϴ�.");
		System.out.println();
	} // ������ �̸� ���
	
	// �������Ʈ ����
	public void showMemberListByGUI(ArrayList<User> userList, JTable table){
		Vector<String> headVector = new Vector<String>();
		headVector.add("�����ȣ");
		headVector.add("���̵�");
		headVector.add("�̸�");
		headVector.add("��ȭ��ȣ");
		headVector.add("�µ�");
		
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
					rowVector.add("�򰡹��� ����");
				}else{
					rowVector.add(String.valueOf(tempM.grade));
				}
				tableDataVector.add(rowVector);
			}
		}
		table.setModel(new DefaultTableModel(tableDataVector,headVector));
	} // �������Ʈ �����ֱ� GUI

	// ��� �� ����
	public void showMemberDetailGUI(ArrayList<User> userList, String userNo, JTextArea memberInfoTA){
		Member tempM = findMemberByUserNo(userList, Integer.parseInt(userNo));
		String grade;
		if(tempM.grade==0){
			grade="�򰡹�������";
		}else{
			grade = String.valueOf(tempM.grade);
		}
		String possibleDeal;
		if(tempM.isPossibleDeal){
			possibleDeal = "�ŷ�����";
		}else{
			possibleDeal = "�ŷ��Ұ�";
		}
		String str = "  �����ȣ  :   "+tempM.userNo
				+"\n  ���̵� :   "+tempM.id
				+"\n  �̸� :   "+tempM.name
				+"\n  ��ȭ��ȣ :   "+tempM.phone
				+"\n  ������� :   "+tempM.phone
				+"\n  ���� :   "+tempM.gender
				+"\n  �ּ� :   "+tempM.address
				+"\n  �µ� :   "+grade
				+"\n  �ŷ����ɿ��� :   "+possibleDeal;
		memberInfoTA.setText(str);
	} //��� �󼼺��� GUI �޼ҵ�

	// �ŷ�����/���� �޼ҵ� 
	public void dealPossibleCheck(ArrayList<User> userList, String userNo, JTextArea memberInfoTA){
		Member tempM = findMemberByUserNo(userList, Integer.parseInt(userNo));
		if(tempM.isPossibleDeal){
			tempM.isPossibleDeal=false;
		}else{
			tempM.isPossibleDeal=true;
		}
		this.showMemberDetailGUI(userList, userNo, memberInfoTA);
	}
	
	// �ŷ�����Ʈ ����
	public void showDealListByGUI(ArrayList<Deal> dealList, JTable table) {
		Vector<String> headVector = new Vector<String>();
		headVector.add("�ŷ���ȣ");
		headVector.add("�ŷ�����");
		headVector.add("å ����");
		headVector.add("������   (�����ȣ)");
		headVector.add("������ �ŷ� ������");
		headVector.add("�Ǹ���   (�����ȣ)");
		headVector.add("�Ǹ��� �ŷ� ������");
		
		Vector<Vector> tableDataVector = new Vector<Vector>();
		for (int i = 0; i < dealList.size(); i++) {
			Deal tempD = dealList.get(i);
			Vector<String> rowVector = new Vector<String>();
			rowVector.add(String.valueOf(tempD.dealNo));
			String dealTimeToString = timeToString(tempD.dealTime); 
//					(tempD.dealTime.get(Calendar.YEAR))+"�� "+(tempD.dealTime.get(Calendar.MONTH)+1)+"�� "+(tempD.dealTime.get(Calendar.DAY_OF_MONTH))+"�� "+(tempD.dealTime.get(Calendar.HOUR_OF_DAY))+"�� "+(tempD.dealTime.get(Calendar.MINUTE))+"�� "+(tempD.dealTime.get(Calendar.SECOND))+"��";
			rowVector.add(dealTimeToString);
			rowVector.add(tempD.saleBoard.saleBook.bookName);
			rowVector.add(tempD.buyMember.id+"     ("+tempD.buyMember.userNo+")");
			if(tempD.buyUserSatification==0){
				rowVector.add("�򰡾���");
			}else{
				rowVector.add(String.valueOf(tempD.buyUserSatification));
			}
			rowVector.add(tempD.sellMember.id+"     ("+tempD.sellMember.userNo+")");
			if(tempD.sellUserSatification==0){
				rowVector.add("�򰡾���");
			}else{
				rowVector.add(String.valueOf(tempD.sellUserSatification));
			}
			tableDataVector.add(rowVector);
			
		}
		table.setModel(new DefaultTableModel(tableDataVector,headVector));
	} // �ŷ�����Ʈ �����ֱ� GUI
	
	// Calendar�� ����Ͻú��ʷ� ��ȯ �޼ҵ�
	public String timeToString(Calendar time) {
//		String timeToString = (time.get(Calendar.YEAR)) + "�� " + (time.get(Calendar.MONTH) + 1) + "�� "
//				+ (time.get(Calendar.DAY_OF_MONTH)) + "�� " + (time.get(Calendar.HOUR_OF_DAY)) + "�� "
//				+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
		String timeToString = (time.get(Calendar.MONTH) + 1) + "�� "
				+ (time.get(Calendar.DAY_OF_MONTH)) + "�� " + (time.get(Calendar.HOUR_OF_DAY)) + "�� "
				+ (time.get(Calendar.MINUTE)) + "�� " + (time.get(Calendar.SECOND)) + "��";
		return timeToString;
	}

} // Admin Ŭ����
