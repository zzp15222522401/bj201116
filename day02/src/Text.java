/*class Text{
	public static void main(String[] args){
		int a=100;
		a=++a;
		a=a%3;
		//a=++a;
		System.out.println(a);
		
		
	}
	
	
	
}*/
//�������Լ���ʾ����
 /* class LogicExer1{
	public static void main(String[] args){
		int x = 1;
		int y = 1;

		//x==2 ,x++  false  x = 2 ���Ϊfalse
		//�ұ߼���
		//++y  y==2  y=2  y==2����  �ұ�Ϊtrue
		//false & true ���false
		if(x++==2 & ++y==2){
			x =7;
		}
		System.out.println("x="+x+",y="+y);//x=2,y=2
	}
}
//����ת����ʾ����
/*class Text{
	public static void main(String[] args){
		//int a=100;
		char a='A';
		//double b=a;
		//byte c=(byte)a;
		int b=(int)a;
		System.out.println(a+1);
	
	}

	*/
	//����������ϰ�����������һ����λ�������������λ�ĺ͡�
	
/*class LianXi2{
	public static void main(String[] args){
		double a1=Math.random();
		double a2=a1*9000+1000;
		int a3=(int)a2;			//���������ʱ���ܱ�֤��4λ����0.002562��,a1*10000�п�������λ��
								//a1*9000��֤��һ��С��9000������+1000��֤��һ����λ����
		//System.out.println(a2);
		System.out.println(a1);//�����������
		System.out.println(a2);//���л���
		System.out.println(a3);//ȷ����λ��
		System.out.println(a3/1000%10+a3/100%10+a3/10%10+a3%10);//�����λ�ĺ�
	
		}
		
	}
	*/
	
	
	
	/*��������ϰ1�����㹺�����ѡ���Ǯ�ͻ��֡�
	
	class LianXi{
		public static void main(String[] args){
			int T=245*2;
			int qX=570;
			int qP=320;
			double Z=0.8;
			//double xiaoFei=(T+qiuXie+qiuPai)*Z;
			int jiao=1500;
			int Jf=(int)((T+qX+qP)*Z/100*3);
			System.out.println("�����ܽ�"+(T+qX+qP)*Z);
			System.out.println("ʵ�ʽ�Ǯ��"+jiao);
			System.out.println("��Ǯ��"+(jiao-(T+qX+qP)*Z));
			System.out.println("������֣�"+Jf);
			
			
			
		}
		
		
	}*/
	
	/*
class LianXi2{
		public static void main(String[] args){
	
			int i=5;
			int j=10;
			int k=i++ + ++j;
			System.out.println(k);  //k=16
			int h=i++ + i+j;
			System.out.println(h);//h=24
		}

}
*/
/*
class TestSign{
    public static void main(String[] args){
        int i1 = 10;
        int i2 = 20;
        int i = i1++;
        System.out.print("i="+i);//i=10
        System.out.println("i1="+i1);//i1=11
        i = ++i1;
        System.out.print("i="+i);//i=12
        System.out.println("i1="+i1);//i1=12
        i = i2--;
        System.out.print("i="+i);//i=20
        System.out.println("i2="+i2);//i2=19
        i = --i2;
        System.out.print("i="+i);//i=18	
		System.out.println("i2="+i2);//i2=18
		}

}
*/

class Text1_1{
	public static void main(String[] args){
		
	
	double a=Math.random()*9000+1000;
	int a1=(int)Math.random()*9000+1000;
	System.out.println("������"+a1+"��");
	 if
	((a1/4==0&a1%100!=0)|(a1%400==0)){
		
		System.out.println("����������");
	
	}
	
		else{
	
		System.out.println("������ƽ��");
		}
	
	
		
	}
}

