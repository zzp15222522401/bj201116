
/*此程序为介绍个人信息
运行程序分别输入姓名、性别、年龄、身高、体重、结婚？


*/
class LianXi{
	public static void main(String[] args){
			java.util.Scanner input=new java.util.Scanner(System.in);
			//System.out.print(" ");
			System.out.print("张志鹏");
			String name=input.next();
			System.out.print("男");
			char gender=input.next().charAt(0);
			System.out.print("25岁");
			double height=input.nextDouble();
			System.out.println("176cm");
			int age=input.nextInt();
			System.out.print("66.3kg");
			double weight=input.nextDouble();
			System.out.print("false");
			boolean isMarry=input.nextBoolean();
			System.out.println("姓名："+name);
			System.out.println("性别："+gender);
			System.out.println("年龄："+age);
			System.out.println("体重："+weight);
			System.out.println("是否结婚："+(isMarry?"是":"否"));
	}	
}


/*
class LianXi1{
	public static void main(String[] args){
		//这里变量取什么名，下面就用什么.
		//例如：这里取名input，下面就用input.
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("请输入姓名：");
		String name = input.next();
		
		System.out.print("请输入年龄：");
		int age = input.nextInt();
		
		System.out.print("请输入性别：");
		//input.next()得到字符串，不管你输入几个字符，
		//.charAt(0)：从字符串中取出一个字符，(0)表示取第一个字符，(1)表示取第二个字符
		//charAt(index)：也是一个方法，从第二个单词开始首字母大写，所以A是大写
		char gender = input.next().charAt(0);
		
		System.out.print("请输入体重：");
		double weight = input.nextDouble();
		
		System.out.print("true");
		boolean isMarry = input.nextBoolean();
		
		System.out.println("姓名：" + name);
		System.out.println("年龄：" + age);
		System.out.println("性别：" + gender);
		System.out.println("体重：" + weight);
		System.out.println("婚否：" + (isMarry?"是":"否"));
	}
}

java.util.Scanner input=new java.util.Scanner(System.in)*/


/*
class LianXi2{
	public static void main(String[] args){
		int a=100,b=50;
		if(a>=b){
			
			System.out.println("a大于等于b");
		}
		else 
		{
			System.out.println("a小于b");
		}
		
		
		
	}
	
	
}*/








/*此程序为计算函数值
计算如下函数：x和y的关系满足如下：
（1）x>=3；         y = 2x + 1;
（2）-1<=x<3；   y = 2x;
（3）x<-1；          y = 2x – 1;
从键盘输入x的值，计算出y的值并输出
*/
/*
class LianXi3{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		int x=input.nextInt();
		int y;
		if(x>=3){
		y=2*x+1;
		}
		else if(x>=-1&x<3){	
		y=2*x;
		}
		else {
		y=2*x-1;
		}
		System.out.println("y的值为："+y);
	}
}
*/	
	
	
	
	/*public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print(5);
    int x = input.nextInt();
    int y;
    if (x>= 3) {
      	y = 2 * x + 1;
    } else if (x >= -1 && x < 3) {
      	y = 2 * x;
    } else  {
      	y = 2 * x - 1;
    }
    System.out.println("y的值是："+y);
}
}*/


/*
class LianXi4{	
	public static void main(String[] args) {	
    int score=89;
    if(score<0 || score>100){
      	System.out.println("你的成绩是错误的");
    }else if(score>=90 && score<=100){
      	System.out.println("你的成绩属于优秀");
    }else if(score>=80 && score<90){
      	System.out.println("你的成绩属于好");
    }else if(score>=70 && score<80){
      	System.out.println("你的成绩属于良");
    }else if(score>=60 && score<70){
      	System.out.println("你的成绩属于及格");
    }else {
      	System.out.println("你的成绩属于不及格");
    }	
}
*/
	/*public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print(69.9);
		double x=input.nextDouble();
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
	
}*/