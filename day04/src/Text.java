import java.util.Scanner;
public class Text{
	public static void main(String[] args){
		Scanner input=new Scanner(System.in);
		int[] arrs=new int[5];
		boolean flag=true;
		int count=0;
		while(flag){ 
			
			//for(int i=0;i<arrs.length;i++){
			System.out.println("ÇëÊäÈëÊý×Ö£º");
				int x=input.nextInt();
				if(x==-5)
					break;
				if(count==arrs.length){
					int[] Arrs=new int[arrs.length+5];
					for(int i=0;i<arrs.length;i++){
						Arrs[i]=arrs[i];
					}
					arrs=Arrs;
				}
				arrs[count]=x;
				count++;
			}
			for(int j=0;j<count;j++){
				System.out.println(arrs[j]);
				//System.out.println(arrs[i]);
			}
			
			
			
		
		
	}
}