/*
1. 定义两个int类型变量a1和a2,分别赋值10,11,判断变量是否为偶数,拼接输出结果
2. 定义两个int类型变量a3和a4,分别赋值12,13,判断变量是否为奇数,拼接输出结果
*/

class Test4_1{
	public static void main(String[] args){
			int a1=10;
			int a2=11;
			//a1%2==0;
			//a2%2==0;
			System.out.println("a1是偶数？"+(a1%2==0));
			System.out.println("a2是偶数？"+(a2%2==0));
			int a3=12;
			int a4=13;
			System.out.println("a3是偶数？"+(a3%2==1));
			System.out.println("a4是偶数？"+(a4%2==1));
		
	}
	
	
	
}