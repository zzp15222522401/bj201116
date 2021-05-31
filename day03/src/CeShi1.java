

/*
//任意输入一个数值，判断这个数值能否被5整除，
//能整除则输出数值，不能则输出不能整除。


class CeShi1{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		int x=input.nextInt();
		if(x%5==0){
			System.out.println(x);
		}
		else
			System.out.println("这个数不能被5整除");
	
	
	}
	
	
}*/
/*第二个测试题：
岳小鹏参加Java考试，他和父亲岳不群达成承诺：如果：

成绩为100分时，奖励一辆BMW；

成绩为(80，99]时，奖励一台iphone7plus；

当成绩为[60,80]时，奖励一个 iPad；

其它时，什么奖励也没有。

请从键盘输入岳小鹏的期末成绩，并加以判断
*/
/*
class CeShi1_1{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		double cj=input.nextDouble();
		if(cj==100){
			System.out.println("奖励一辆BMW");
		}
		else{
			if(cj>80&&cj<=99){
				System.out.println("奖励一台ipone7plus");
			}
			else if(cj>=60&&cj<=80){
				System.out.println("奖励一个iPad");
			}
			else if(cj>0&&cj<60){
				System.out.println("nothing");
			}
			else{
				System.out.println("成绩有误");
			}
		}
		
	}
	
}
*/

//第三个测试题：
//编写程序：由键盘输入三个整数分别存入变量num1、num2、num3，
//对它们进行排序(使用 if-else if-else),并且从小到大输出。
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
/*第四道测试题：
大家都知道，男大当婚，女大当嫁。那么女方家长要嫁女儿，当然要提出一定的条件：高：180cm以上；富：财富1千万以上；帅：是。

如果这三个条件同时满足，则：“我一定要嫁给他!!!”

如果三个条件中有为真的情况，则：“嫁吧，比上不足，比下有余。”

如果三个条件都不满足，则：“不嫁！”
*/
/*
class CeShi1_3{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		//System.out.print("财富：");
		//System.out.print("帅否：");
		double gao=input.nextDouble();
		double qian=input.nextDouble();
		boolean zx=input.nextBoolean();
		if(gao>=180&&qian>=1000&&zx)
			System.out.println("我一定要嫁给他！！！");
		else if(gao>=180||qian>=1000||zx)
			System.out.println("嫁吧，比上不足，比下有余。");
		else
			System.out.println("不嫁!");
		
		
	}
}
*/
/*假设你想开发一个玩彩票的游戏，程序随机地产生一个两位数的彩票，
提示用户输入一个两位数，然后按照下面的规则判定用户是否能赢。

1)如果用户输入的数匹配彩票的实际顺序，奖金10 000美元。

2)如果用户输入的所有数字匹配彩票的所有数字，但顺序不一致，奖金 3 000美元。

3)如果用户输入的一个数字仅满足顺序情况下匹配彩票的一个数字，奖金1 000美元。

4)如果用户输入的一个数字仅满足非顺序情况下匹配彩票的一个数字，奖金500美元。

5)如果用户输入的数字没有匹配任何一个数字，则彩票作废。*/
/*
class CeShi1_4{
	public static void main(String[] args){
		int y=(int)(Math.random()*90+10);
		System.out.println("本次中将号码为："+y);
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		int x=input.nextInt();
		//int y=(int)Math.random()*90+10;
		int x1=x/10;
		int x2=x%10;
		int y1=y/10;
		int y2=y%10;
		if(x==y){
			System.out.println("奖金10000美元");
		}
		else {
			if(x1==y2&&x2==y1){
			System.out.println("奖金3000美元");
		}
		else if(x1==y1||x2==y2){
			System.out.println("奖金1000美元");
		}
		else if(x1==y2||x2==y1){
			System.out.println("奖金500美元");
		}
		else
			System.out.println("彩票作废");
		
		}
		
	}
	
}
*/

/*使用 switch 把阿拉伯数字转为“壹、贰、叁、肆、伍、陆、柒、捌、玖”；
其它的都输出 “other”*/

class CeShi1_5{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("随意输入一个数字进行转换\t");
		int num=input.nextInt();
		switch(num){
			case 1:
			System.out.println("壹");
			break;
			case 2:
			System.out.println("贰");
			break;
			case 3:
			System.out.println("叁");
			break;
			case 4:
			System.out.println("肆");
			break;
			case 5:
			System.out.println("伍");
			break;
			case 6:
			System.out.println("陆");
			break;
			case 7:
			System.out.println("柒");
			break;
			case 8:
			System.out.println("捌");
			break;
			case 9:
			System.out.println("玖");
			break;
			default:
			System.out.println("other");
			break;
		}
	}
}