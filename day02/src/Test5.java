/*

1. ����һ��int���ͱ���a,����b,����ֵΪ20.
2. ����boolean���ͱ���bo , �ж�++a �Ƿ�3����,����a++ �Ƿ�7����,�������ֵ��bo
3. ���a��ֵ,bo��ֵ.
4. ����boolean���ͱ���bo2 , �ж�b++ �Ƿ�3����,����++b �Ƿ�7����,�������ֵ��bo2
5. ���b��ֵ,bo2��ֵ.

*/

class Test5_1{
	public static void main(String[] args){
		int a=20;
		int b=20;
		boolean bo=((++a%3)==0)&&((a++%7)==0);
		System.out.println("a��ֵΪ:"+a);
		System.out.println("bo��ֵΪ��"+bo);
		boolean bo2=((b++%3)==0)&&((++b%7)==0);//��b�ȸ�ֵ��b++��Ȼ��b+1,
												//��ʱbo2=b++=20.˫��(�����false�Ͳ����ұߣ�ֱ�����false)
		System.out.println("b��ֵΪ:"+b);
		System.out.println("bo2��ֵΪ��"+bo2);
		
		}
	
}