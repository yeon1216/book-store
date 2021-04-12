package event;

import user.Member;

public class Adversity extends Event{
	public String companyName; // ȸ���
	public String adversityContent; //������
	public boolean isRead; // ���� �о����� ����
	public boolean isAleady; // �����̺�Ʈ ��������
	
	// �⺻������
	public Adversity() { }

	// ������
	public Adversity(int eventNo, String companyName, String adversityContent) {
		super(eventNo);
		this.companyName = companyName;
		this.adversityContent = adversityContent;
		this.isRead = false;
		this.isAleady = false;
	}
	
} // Adversity Ŭ����
