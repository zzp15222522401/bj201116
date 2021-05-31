/*

1. 定义一个int类型变量a,变量b,都赋值为20.
2. 定义boolean类型变量bo , 判断++a 是否被3整除,并且a++ 是否被7整除,将结果赋值给bo
3. 输出a的值,bo的值.
4. 定义boolean类型变量bo2 , 判断b++ 是否被3整除,并且++b 是否被7整除,将结果赋值给bo2
5. 输出b的值,bo2的值.

*/

class Test5_1{
	public static void main(String[] args){
		int a=20;
		int b=20;
		boolean bo=((++a%3)==0)&&((a++%7)==0);
		System.out.println("a的值为:"+a);
		System.out.println("bo的值为："+bo);
		boolean bo2=((b++%3)==0)&&((++b%7)==0);//把b先赋值给b++，然后b+1,
												//此时bo2=b++=20.双与(左边是false就不看右边，直接输出false)
		System.out.println("b的值为:"+b);
		System.out.println("bo2的值为："+bo2);
		
		}
	
}