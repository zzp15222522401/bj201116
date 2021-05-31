/*求x,y,z中的最大值（使用三元运算符）
1. 定义三个int类型变量,x,y,z，随意赋值整数值
2. 定义一个int类型变量max，先存储x与y中的最大值（使用三元运算符）
3. 再次对max赋值，让它等于上面max与z中的最大值（使用三元运算符）
4. 输出结果
*/
class Test8_1{
	public static void main(String[] args){
		int x=3;
		int y=4;
		int z=5;
		int max=(x>y?x:y);
		max=(max>z?max:z);
		System.out.println("3，4，5中最大值是"+max);
		
		}

}