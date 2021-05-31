/*
1. 定义一个int类型变量hours，赋值为89
2. 定义一个int类型变量day，用来保存89小时中天数的结果
3. 定义一个int类型变量hour，用来保存89小时中不够一天的剩余小时数的结果
4. 输出结果
*/

class Test6_1{
	public static void main(String[] args){
		int hours=89;
		int day=89/24;
		int hour=89%24;
		System.out.println("89个小时是"+day+"天"+hour+"小时");
		
		
	}
	
	
}