class LianXi1{
	public static void main(String[] args){
int i = 2;
i *= i++;

int j = 2;
j *= j+1; 

int k = 2;
k *= ++k;

System.out.println("i=" + i);//i=4
System.out.println("j=" + j);//i=6
System.out.println("k=" + k);
	}
}