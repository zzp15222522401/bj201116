//��������һ�����֣������Ƿ���ͬʱ��3��7������
//import java.util.Scanner				����
//	Sscanner input=new Scanner(System.in)�Ӽ�������
/*
import java.util.Scanner;
class Demo1{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.println("��������֣�");
		int a=input.nextInt();
		if(a%3==0&&a%7==0)
			System.out.println("����ͬʱ����");
	}
	
}
*/



import java.util.Scanner;
class Demo2{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.println("���������ĳɼ���");
		int cj=input.nextInt();
		if(cj==100){
			System.out.println("�������Ƿ���Ů���ѣ�(0-û�У�1-��)");
			int a=input.nextInt();
			if(a==0){
				System.out.println("����һ��Ů����");
			}
		}
		else{
			System.out.println("���������Ĵ�����(��λ����)��");
			int b=input.nextInt();
			if(b>100){
				System.out.println("ȥѰ��Ů����");
			}
			else{
				System.out.println("����Ǯ");
			}
		}
	}
}



/*import java.util.Scanner;
public class Demo3 {
    public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
        int time = input.nextInt();
        System.out.println("�����ʱ��㣺");
        switch (time) {
            case 0:
                System.out.println("���Ϻ�");
                break;
            case 1:
                System.out.println("���Ϻ�");
                break;
            case 2:
                System.out.println("���Ϻ�");
                break;
            case 3:
                System.out.println("���Ϻ�");
                break;
            case 4:
                System.out.println("���Ϻ�");
                break;
            case 5:
                System.out.println("���Ϻ�");
                break;
            case 6:
                System.out.println("���Ϻ�");
                break;
            case 7:
                System.out.println("�����");
                break;
            case 8:
                System.out.println("�����");
                break;
            case 9:
                System.out.println("�����");
                break;
            case 10:
                System.out.println("�����");
                break;
            case 11:
                System.out.println("�����");
                break;
            case 12:
                System.out.println("�����");
                break;
            case 13:
                System.out.println("�����");
                break;
            case 14:
                System.out.println("�����");
                break;
            case 15:
                System.out.println("�����");
                break;
            case 16:
                System.out.println("�����");
                break;
            case 17:
                System.out.println("�����");
                break;
            case 18:
                System.out.println("�����");
                break;
            case 19:
                System.out.println("�����");
                break;
            case 20:
                System.out.println("�����");
                break;
            case 21:
                System.out.println("���Ϻ�");
                break;
            case 22:
                System.out.println("���Ϻ�");
                break;
            case 23:
                System.out.println("���Ϻ�");
                break;

            default:
                System.out.println("�������ʱ�䲻��");
                break;
        }
    }
}*/
/*
import java.util.Scanner;
class Demo4{
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);

        String str1="";
       
        while(!str.equals("q")){
			//�ַ����ıȶԣ���ʽ������1(�ַ���).equals("����2")
            System.out.println("���������������");
            String str=input.next();
            
        }
        
    }*/
	/*
	��ʾ��
	*
	**
	***
	****
	*****
	******
	n����n��*�ţ�
	
	
	*//*
	import java.util.Scanner;
class Demo5{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.print("��Ҫ����*�ţ�");
		int a=input.nextInt();
		System.out.print("��Ҫ����*�ţ�");
		int b=input.nextInt();
		
		int a1=0;
		
		while(a1<a){
			int b1=0;
			while(b1<a1+1){
				System.out.print("*");
				b1++;
			}
			
			System.out.println("");
			a1++;
			
		
		
		}
	}
}
*/
/*
��ʾ��
	*
   ***
  *****
 *******
*//*
 class Demo1 {
    public static void main(String[] args) {
        //���ݸ����ķ��������һ����������
        int max = 5;
        //��������
        for(int i = 1; i<=5 ; i++) {
            //���ƿո�
            for (int j = 1; j <= max - i; j++)
                System.out.print(" ");
                //��������
                for (int k = 1; k <= i * 2 - 1; k++)
                    System.out.print("*");
                    System.out.println();    
        }
    }
}


/*
import java.util.Scanner;
class Demo6{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.print("����*�ĵ��������Σ�");
		int a=input.nextInt();
		//System.out.print("��Ҫ���У�");
		//int b=input.nextInt();
		int a1=0;
		while(a1<a){
			int b2=0;
				while(b2<a-1-a1){
					System.out.print(" ");
				b2++;
				}
			int b1=0;
			while(b1<a1*2+1){
				System.out.print("*");
				b1++;
			}
			System.out.println("");
			a1++;
		}
	}
}
*/

/*
ʵ�ֵ�½�������ʾ����
("��ӭ�����й�Ƚ���ϵͳ	1 ע��	2 ��¼	3 �˳�")
*/

/*
import java.util.Scanner;
class Demo7{
    public static void main(String[] args){
        String user="55555";
        String pass="666666";
        boolean flag=true;
        while(true){
            System.out.println("��ӭ�����й�Ƚ���ϵͳ	1 ע��	2 ��¼	3 �˳�");
            Scanner input=new Scanner(System.in);
            System.out.println("��ѡ��");
            int a=input.nextInt();
            switch(a){
                case 1:
                    //System.out.println("ע��");
                    Scanner input1=new Scanner(System.in);
                    System.out.println("��������Ҫע����˺�user:");
                    String user2=input1.next();
					//boolean a1=user.equals(user2);
                    System.out.println("��������pass:");
                    String pass2=input1.next();
					//boolean a2=(pass.equals(pass2);
					System.out.println("ע�����");
                    break;
                case 2:
                    //System.out.println("��¼\�����������˺�����:");
					Scanner input2=new Scanner(System.in);
                    System.out.println("��¼�˺�user:");
                    String user1=input2.next();
                    System.out.println("����pass:");
                    String pass1=input2.next();
                    if(user.equals(user1)&&pass.equals(pass1)){
					System.out.println("��½�ɹ���");
					
					
					}
					else{
						System.out.println("��½ʧ�ܣ�");
                        continue;
						}
                    break;
                case 3:
                    System.out.println("��ӭ�´�����������");
					flag=false;
                    break;
					
            }
				//flag=false;
        }


    }
}
*/


