/*��x,y,z�е����ֵ��ʹ����Ԫ�������
1. ��������int���ͱ���,x,y,z�����⸳ֵ����ֵ
2. ����һ��int���ͱ���max���ȴ洢x��y�е����ֵ��ʹ����Ԫ�������
3. �ٴζ�max��ֵ��������������max��z�е����ֵ��ʹ����Ԫ�������
4. ������
*/
class Test8_1{
	public static void main(String[] args){
		int x=3;
		int y=4;
		int z=5;
		int max=(x>y?x:y);
		max=(max>z?max:z);
		System.out.println("3��4��5�����ֵ��"+max);
		
		}

}