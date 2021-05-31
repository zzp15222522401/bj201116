/*
给定一个年份，判断是否是闰年

闰年的判断标准是：

?       1）可以被4整除，但不可被100整除

?       2）可以被400整除

- 编写步骤：
  1. 定义类Test9
  2. 定义main方法
  3. 定义一个int类型变量year，随意赋值一个年份
  4. 定一个一个boolean类型变量，用来保存这个年份是否是闰年的结果
  5. 输出结果
*/

class Test9_1{
	public static void main(String[] args){
		int year=2021;
		boolean a=year%4==0&&year%100!=0||year%400==0;
		System.out.println("2021年是否是闰年："+(a?"是闰年":"不是闰年"));
		
		
	}
	
	
}