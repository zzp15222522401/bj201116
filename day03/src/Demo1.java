//随意输入一个数字，看它是否能同时被3和7整除。
//import java.util.Scanner				导包
//	Sscanner input=new Scanner(System.in)从键盘输入
/*
import java.util.Scanner;
class Demo1{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.println("输入的数字：");
		int a=input.nextInt();
		if(a%3==0&&a%7==0)
			System.out.println("可以同时整除");
	}
	
}
*/



import java.util.Scanner;
class Demo2{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.println("请输入您的成绩：");
		int cj=input.nextInt();
		if(cj==100){
			System.out.println("请问您是否有女朋友？(0-没有，1-有)");
			int a=input.nextInt();
			if(a==0){
				System.out.println("奖励一个女朋友");
			}
		}
		else{
			System.out.println("请输入您的存款余额(单位：万)：");
			int b=input.nextInt();
			if(b>100){
				System.out.println("去寻找女朋友");
			}
			else{
				System.out.println("先挣钱");
			}
		}
	}
}



/*import java.util.Scanner;
public class Demo3 {
    public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
        int time = input.nextInt();
        System.out.println("输入的时间点：");
        switch (time) {
            case 0:
                System.out.println("早上好");
                break;
            case 1:
                System.out.println("早上好");
                break;
            case 2:
                System.out.println("早上好");
                break;
            case 3:
                System.out.println("早上好");
                break;
            case 4:
                System.out.println("早上好");
                break;
            case 5:
                System.out.println("早上好");
                break;
            case 6:
                System.out.println("早上好");
                break;
            case 7:
                System.out.println("上午好");
                break;
            case 8:
                System.out.println("上午好");
                break;
            case 9:
                System.out.println("上午好");
                break;
            case 10:
                System.out.println("上午好");
                break;
            case 11:
                System.out.println("上午好");
                break;
            case 12:
                System.out.println("中午好");
                break;
            case 13:
                System.out.println("中午好");
                break;
            case 14:
                System.out.println("中午好");
                break;
            case 15:
                System.out.println("下午好");
                break;
            case 16:
                System.out.println("下午好");
                break;
            case 17:
                System.out.println("下午好");
                break;
            case 18:
                System.out.println("下午好");
                break;
            case 19:
                System.out.println("下午好");
                break;
            case 20:
                System.out.println("下午好");
                break;
            case 21:
                System.out.println("晚上好");
                break;
            case 22:
                System.out.println("晚上好");
                break;
            case 23:
                System.out.println("晚上好");
                break;

            default:
                System.out.println("你输入的时间不对");
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
			//字符串的比对，格式：变量1(字符串).equals("变量2")
            System.out.println("请输入你的姓名：");
            String str=input.next();
            
        }
        
    }*/
	/*
	演示：
	*
	**
	***
	****
	*****
	******
	n行有n个*号；
	
	
	*//*
	import java.util.Scanner;
class Demo5{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		System.out.print("需要几行*号：");
		int a=input.nextInt();
		System.out.print("需要几列*号：");
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
演示：
	*
   ***
  *****
 *******
*//*
 class Demo1 {
    public static void main(String[] args) {
        //根据给定的方法来输出一个等腰三角
        int max = 5;
        //控制行数
        for(int i = 1; i<=5 ; i++) {
            //控制空格
            for (int j = 1; j <= max - i; j++)
                System.out.print(" ");
                //控制星数
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
		System.out.print("几个*的等腰三角形：");
		int a=input.nextInt();
		//System.out.print("需要几列：");
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
实现登陆界面的显示程序
("欢迎来到尚硅谷教育系统	1 注册	2 登录	3 退出")
*/

/*
import java.util.Scanner;
class Demo7{
    public static void main(String[] args){
        String user="55555";
        String pass="666666";
        boolean flag=true;
        while(true){
            System.out.println("欢迎来到尚硅谷教育系统	1 注册	2 登录	3 退出");
            Scanner input=new Scanner(System.in);
            System.out.println("请选择：");
            int a=input.nextInt();
            switch(a){
                case 1:
                    //System.out.println("注册");
                    Scanner input1=new Scanner(System.in);
                    System.out.println("输入你想要注册的账号user:");
                    String user2=input1.next();
					//boolean a1=user.equals(user2);
                    System.out.println("您的密码pass:");
                    String pass2=input1.next();
					//boolean a2=(pass.equals(pass2);
					System.out.println("注册完成");
                    break;
                case 2:
                    //System.out.println("登录\请输入您的账号密码:");
					Scanner input2=new Scanner(System.in);
                    System.out.println("登录账号user:");
                    String user1=input2.next();
                    System.out.println("密码pass:");
                    String pass1=input2.next();
                    if(user.equals(user1)&&pass.equals(pass1)){
					System.out.println("登陆成功！");
					
					
					}
					else{
						System.out.println("登陆失败！");
                        continue;
						}
                    break;
                case 3:
                    System.out.println("欢迎下次再来！！！");
					flag=false;
                    break;
					
            }
				//flag=false;
        }


    }
}
*/


