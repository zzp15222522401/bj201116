public class Lian{
	public static void main(String[] args){
	/*	int sum=0;
		int count=2;
		int i=1;
		while(i<=101){
			if(count%2==0){
				sum+=i;
			}else{
				sum-=i;
			}
			count+=1;
			i+=2;
		}
			System.out.println(sum);
				
		
		
	}
	

/*
class Lian1{
	public static void main(String[] args){
		int sum=0;
		int count=2;
		for(int i=1;i<=101;i+=2){
			if(count%2==0){
				sum+=i;
			}else{
				sum-=i;
			}
			count+=1;
			
			
		}
		System.out.println(sum);
	}
	
}
*/

/*
import java.util.Scanner;
class Lian2{
	public static void main(String[] args){
		for(int i=1;i<=100;i++){
			if(i%7==0||i%10==7)
				continue;
			System.out.println(i);
		}
	}*/
	
	/*
	public static void main(String[] args) {
    // �������飬�洢3��Ԫ��
    int[] arr = new int[3];
    //�����������и�ֵ
    arr[0] = 5;
    arr[1] = 6;
    arr[2] = 7;
    //���3�������ϵ�Ԫ��ֵ
    System.out.println(arr[0]);
    System.out.println(arr[1]);
    System.out.println(arr[2]);
    //�����������arr2����arr�ĵ�ַ��ֵ��arr2
    int[] arr2 = arr;
    arr2[1] = 9;
	System.out.println(arr[0]);
    System.out.println(arr[1]);
	System.out.println(arr[2]);
	
	*/
//����5��ͬѧ�ĳɼ�����ȫ��չʾ��
/*	
	double[] cj=new double[5];
	
	Scanner input=new Scanner(System.in);
	
	for(int i=0;i<5;i++){
		System.out.println("�����"+(i+1)+"��ͬѧ��java�ɼ���");
		double a=input.nextDouble();
		cj[i]=a;
	}
	for(int i=0;i<5;i++){
	System.out.println(cj[i]);
	}
	
	*/
	
	
	/*
	����һ�����飬�����������ֵ����ʾ���ֵ������λ��
	�������ĺ�
	�����д���ż���ĸ�����
	��
	*/

	int[] num={300,20,13,5,68,126,22,48,201};
	int x=num.length;
	int max=num[0];
	int b=0;
	int sum=0;
	int count=0;
	for(int a=1;a<x;a++){
		if(num[a]>max){
			max=num[a];
			b=a;
		}
	}
	for(int i=0;i<x;i++){
		sum+=num[i];
		if(num[i]%2==0){
			count++;
		}
	}
	System.out.println("���ֵΪ"+max+"����λ��Ϊ��"+b+"λ");
	System.out.println("��Ϊ"+sum);
	System.out.println("��ֵΪ"+(double)sum/x);
System.out.println("ż������Ϊ"+count);
}
}

/*
�����������ִ洢�������У�Ҫ���������������������ݣ�Ȼ�����´洢��
	
		
	
	
	}
}*/




