
/*�˳���Ϊ���ܸ�����Ϣ
���г���ֱ������������Ա����䡢��ߡ����ء���飿


*/
class LianXi{
	public static void main(String[] args){
			java.util.Scanner input=new java.util.Scanner(System.in);
			//System.out.print(" ");
			System.out.print("��־��");
			String name=input.next();
			System.out.print("��");
			char gender=input.next().charAt(0);
			System.out.print("25��");
			double height=input.nextDouble();
			System.out.println("176cm");
			int age=input.nextInt();
			System.out.print("66.3kg");
			double weight=input.nextDouble();
			System.out.print("false");
			boolean isMarry=input.nextBoolean();
			System.out.println("������"+name);
			System.out.println("�Ա�"+gender);
			System.out.println("���䣺"+age);
			System.out.println("���أ�"+weight);
			System.out.println("�Ƿ��飺"+(isMarry?"��":"��"));
	}	
}


/*
class LianXi1{
	public static void main(String[] args){
		//�������ȡʲô�����������ʲô.
		//���磺����ȡ��input���������input.
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("������������");
		String name = input.next();
		
		System.out.print("���������䣺");
		int age = input.nextInt();
		
		System.out.print("�������Ա�");
		//input.next()�õ��ַ��������������뼸���ַ���
		//.charAt(0)�����ַ�����ȡ��һ���ַ���(0)��ʾȡ��һ���ַ���(1)��ʾȡ�ڶ����ַ�
		//charAt(index)��Ҳ��һ���������ӵڶ������ʿ�ʼ����ĸ��д������A�Ǵ�д
		char gender = input.next().charAt(0);
		
		System.out.print("���������أ�");
		double weight = input.nextDouble();
		
		System.out.print("true");
		boolean isMarry = input.nextBoolean();
		
		System.out.println("������" + name);
		System.out.println("���䣺" + age);
		System.out.println("�Ա�" + gender);
		System.out.println("���أ�" + weight);
		System.out.println("���" + (isMarry?"��":"��"));
	}
}

java.util.Scanner input=new java.util.Scanner(System.in)*/


/*
class LianXi2{
	public static void main(String[] args){
		int a=100,b=50;
		if(a>=b){
			
			System.out.println("a���ڵ���b");
		}
		else 
		{
			System.out.println("aС��b");
		}
		
		
		
	}
	
	
}*/








/*�˳���Ϊ���㺯��ֵ
�������º�����x��y�Ĺ�ϵ�������£�
��1��x>=3��         y = 2x + 1;
��2��-1<=x<3��   y = 2x;
��3��x<-1��          y = 2x �C 1;
�Ӽ�������x��ֵ�������y��ֵ�����
*/
/*
class LianXi3{
	public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print("");
		int x=input.nextInt();
		int y;
		if(x>=3){
		y=2*x+1;
		}
		else if(x>=-1&x<3){	
		y=2*x;
		}
		else {
		y=2*x-1;
		}
		System.out.println("y��ֵΪ��"+y);
	}
}
*/	
	
	
	
	/*public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);
    System.out.print(5);
    int x = input.nextInt();
    int y;
    if (x>= 3) {
      	y = 2 * x + 1;
    } else if (x >= -1 && x < 3) {
      	y = 2 * x;
    } else  {
      	y = 2 * x - 1;
    }
    System.out.println("y��ֵ�ǣ�"+y);
}
}*/


/*
class LianXi4{	
	public static void main(String[] args) {	
    int score=89;
    if(score<0 || score>100){
      	System.out.println("��ĳɼ��Ǵ����");
    }else if(score>=90 && score<=100){
      	System.out.println("��ĳɼ���������");
    }else if(score>=80 && score<90){
      	System.out.println("��ĳɼ����ں�");
    }else if(score>=70 && score<80){
      	System.out.println("��ĳɼ�������");
    }else if(score>=60 && score<70){
      	System.out.println("��ĳɼ����ڼ���");
    }else {
      	System.out.println("��ĳɼ����ڲ�����");
    }	
}
*/
	/*public static void main(String[] args){
		java.util.Scanner input=new java.util.Scanner(System.in);
		System.out.print(69.9);
		double x=input.nextDouble();
		if(x>100 || x<0){
			System.out.println("�ɼ���Ч");
			
		}
		else if(x>=90 && x<=100){
			System.out.println("�ɼ�����");
			
		}
		else if(x>=80 && x<90){
			System.out.println("�ɼ���");
		}
		else if(x>=70 && x<80){
			System.out.println("�ɼ���");
			
		}
		else if(x>=60 && x<70){
			System.out.println("�ɼ�����");
			
		}
		else{
			System.out.println("�ɼ�������");
			
		}
		
	}
	
}*/