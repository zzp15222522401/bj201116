

/*
//��������һ����ֵ���ж������ֵ�ܷ�5������
//�������������ֵ���������������������


class CeShi1{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		int x=input.nextInt();
		if(x%5==0){
			System.out.println(x);
		}
		else
			System.out.println("��������ܱ�5����");
	
	
	}
	
	
}*/
/*�ڶ��������⣺
��С���μ�Java���ԣ����͸�������Ⱥ��ɳ�ŵ�������

�ɼ�Ϊ100��ʱ������һ��BMW��

�ɼ�Ϊ(80��99]ʱ������һ̨iphone7plus��

���ɼ�Ϊ[60,80]ʱ������һ�� iPad��

����ʱ��ʲô����Ҳû�С�

��Ӽ���������С������ĩ�ɼ����������ж�
*/
/*
class CeShi1_1{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		double cj=input.nextDouble();
		if(cj==100){
			System.out.println("����һ��BMW");
		}
		else{
			if(cj>80&&cj<=99){
				System.out.println("����һ̨ipone7plus");
			}
			else if(cj>=60&&cj<=80){
				System.out.println("����һ��iPad");
			}
			else if(cj>0&&cj<60){
				System.out.println("nothing");
			}
			else{
				System.out.println("�ɼ�����");
			}
		}
		
	}
	
}
*/

//�����������⣺
//��д�����ɼ����������������ֱ�������num1��num2��num3��
//�����ǽ�������(ʹ�� if-else if-else),���Ҵ�С���������
/*
class CeShi1_2{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
			System.out.print("");
			int num1=input.nextInt();
			int num2=input.nextInt();
			int num3=input.nextInt();
			if(num1<num2&&num2<num3){
				System.out.println(num1+" "+num2+" "+num3);
			}
				
			else if(num1<num3&&num3<num2){
				System.out.println(num1+" "+num3+" "+num2);
			}
			else if(num2<num1&&num1<num3){
				System.out.println(num2+" "+num1+" "+num3);
			}
			else if(num2<num3&&num3<num1){
				System.out.println(num2+" "+num3+" "+num1);
			}
			else if(num3<num2&&num2<num1){
				System.out.println(num3+" "+num2+" "+num1);
			}
			else
				System.out.println(num3+" "+num1+" "+num2);
	}
}

*/
/*���ĵ������⣺
��Ҷ�֪�����д󵱻飬Ů�󵱼ޡ���ôŮ���ҳ�Ҫ��Ů������ȻҪ���һ�����������ߣ�180cm���ϣ������Ƹ�1ǧ�����ϣ�˧���ǡ�

�������������ͬʱ���㣬�򣺡���һ��Ҫ�޸���!!!��

���������������Ϊ���������򣺡��ްɣ����ϲ��㣬�������ࡣ��

������������������㣬�򣺡����ޣ���
*/
/*
class CeShi1_3{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		//System.out.print("�Ƹ���");
		//System.out.print("˧��");
		double gao=input.nextDouble();
		double qian=input.nextDouble();
		boolean zx=input.nextBoolean();
		if(gao>=180&&qian>=1000&&zx)
			System.out.println("��һ��Ҫ�޸���������");
		else if(gao>=180||qian>=1000||zx)
			System.out.println("�ްɣ����ϲ��㣬�������ࡣ");
		else
			System.out.println("����!");
		
		
	}
}
*/
/*�������뿪��һ�����Ʊ����Ϸ����������ز���һ����λ���Ĳ�Ʊ��
��ʾ�û�����һ����λ����Ȼ��������Ĺ����ж��û��Ƿ���Ӯ��

1)����û��������ƥ���Ʊ��ʵ��˳�򣬽���10 000��Ԫ��

2)����û��������������ƥ���Ʊ���������֣���˳��һ�£����� 3 000��Ԫ��

3)����û������һ�����ֽ�����˳�������ƥ���Ʊ��һ�����֣�����1 000��Ԫ��

4)����û������һ�����ֽ������˳�������ƥ���Ʊ��һ�����֣�����500��Ԫ��

5)����û����������û��ƥ���κ�һ�����֣����Ʊ���ϡ�*/
/*
class CeShi1_4{
	public static void main(String[] args){
		int y=(int)(Math.random()*90+10);
		System.out.println("�����н�����Ϊ��"+y);
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		int x=input.nextInt();
		//int y=(int)Math.random()*90+10;
		int x1=x/10;
		int x2=x%10;
		int y1=y/10;
		int y2=y%10;
		if(x==y){
			System.out.println("����10000��Ԫ");
		}
		else {
			if(x1==y2&&x2==y1){
			System.out.println("����3000��Ԫ");
		}
		else if(x1==y1||x2==y2){
			System.out.println("����1000��Ԫ");
		}
		else if(x1==y2||x2==y1){
			System.out.println("����500��Ԫ");
		}
		else
			System.out.println("��Ʊ����");
		
		}
		
	}
	
}
*/

/*ʹ�� switch �Ѱ���������תΪ��Ҽ���������������顢½���⡢�ơ�������
�����Ķ���� ��other��*/

class CeShi1_5{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("��������һ�����ֽ���ת��\t");
		int num=input.nextInt();
		switch(num){
			case 1:
			System.out.println("Ҽ");
			break;
			case 2:
			System.out.println("��");
			break;
			case 3:
			System.out.println("��");
			break;
			case 4:
			System.out.println("��");
			break;
			case 5:
			System.out.println("��");
			break;
			case 6:
			System.out.println("½");
			break;
			case 7:
			System.out.println("��");
			break;
			case 8:
			System.out.println("��");
			break;
			case 9:
			System.out.println("��");
			break;
			default:
			System.out.println("other");
			break;
		}
	}
}