/**
此程序为测试考试成绩
@author 张志鹏
@version 1.1


*/

public class CeShi{ 
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("输入成绩为：");//此为输入的成绩，可随意。
		double x=input.nextDouble();
		//int x=69;
		if(x>100 || x<0){
			System.out.println("成绩无效");	
		}
		else if(x>=90 && x<=100){
			System.out.println("成绩优秀");	
		}
		else if(x>=80 && x<90){
			System.out.println("成绩好");
		}
		else if(x>=70 && x<80){
			System.out.println("成绩良");	
		}
		else if(x>=60 && x<70){
			System.out.println("成绩及格");	
		}
		else{
			System.out.println("成绩不及格");	
		}
		
	}
}