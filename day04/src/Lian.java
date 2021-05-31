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
    // 定义数组，存储3个元素
    int[] arr = new int[3];
    //数组索引进行赋值
    arr[0] = 5;
    arr[1] = 6;
    arr[2] = 7;
    //输出3个索引上的元素值
    System.out.println(arr[0]);
    System.out.println(arr[1]);
    System.out.println(arr[2]);
    //定义数组变量arr2，将arr的地址赋值给arr2
    int[] arr2 = arr;
    arr2[1] = 9;
	System.out.println(arr[0]);
    System.out.println(arr[1]);
	System.out.println(arr[2]);
	
	*/
//输入5名同学的成绩，在全部展示。
/*	
	double[] cj=new double[5];
	
	Scanner input=new Scanner(System.in);
	
	for(int i=0;i<5;i++){
		System.out.println("输入第"+(i+1)+"名同学的java成绩：");
		double a=input.nextDouble();
		cj[i]=a;
	}
	for(int i=0;i<5;i++){
	System.out.println(cj[i]);
	}
	
	*/
	
	
	/*
	定义一个数组，求得数组的最大值并显示最大值的索引位，
	求得数组的和
	数组中存在偶数的个数。
	求反
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
	System.out.println("最大值为"+max+"索引位置为第"+b+"位");
	System.out.println("和为"+sum);
	System.out.println("均值为"+(double)sum/x);
System.out.println("偶数个数为"+count);
}
}

/*
随意输入数字存储到数组中，要求数组容量不够进行扩容，然后重新存储。
	
		
	
	
	}
}*/




