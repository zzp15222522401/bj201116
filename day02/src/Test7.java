/*
1. 定义一个int类型变量week，赋值为2
2. 修改week的值，在原值基础上加上100
3. 因为week的值加上100后超过了星期的范围，重写修改week的值
4. 输出结果，在输出结果的时候考虑特殊值，例如周日
*/

class Test7{
	public static void main(String[] args){
		int week=2;
		week+=100;
		week%=7;
		System.out.println("今天是周二，100天后是周"+(week==0?"日":week));
		
		
	}
	
	
}

