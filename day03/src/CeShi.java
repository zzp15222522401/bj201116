/**
�˳���Ϊ���Կ��Գɼ�
@author ��־��
@version 1.1


*/

public class CeShi{ 
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("����ɼ�Ϊ��");//��Ϊ����ĳɼ��������⡣
		double x=input.nextDouble();
		//int x=69;
		if(x>100 || x<0){
			System.out.println("�ɼ���Ч");	
		}
		else if(x>=90 && x<=100){
			System.out.println("�ɼ�����");	
		}
		else if(x>=80 && x<90){
			System.out.println("�ɼ���");
		}
		else if(x>=70 && x<80){
			System.out.println("�ɼ���");	
		}
		else if(x>=60 && x<70){
			System.out.println("�ɼ�����");	
		}
		else{
			System.out.println("�ɼ�������");	
		}
		
	}
}