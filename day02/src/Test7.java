/*
1. ����һ��int���ͱ���week����ֵΪ2
2. �޸�week��ֵ����ԭֵ�����ϼ���100
3. ��Ϊweek��ֵ����100�󳬹������ڵķ�Χ����д�޸�week��ֵ
4. ������������������ʱ��������ֵ����������
*/

class Test7{
	public static void main(String[] args){
		int week=2;
		week+=100;
		week%=7;
		System.out.println("�������ܶ���100�������"+(week==0?"��":week));
		
		
	}
	
	
}

