/**class Demo{
public static void main(String[] args){
  	int[] arr = new int[520];
  	System.out.println(arr);//[I@5f150435
	}
}*/
//  º∆À„1-3+5-7+9-11+.....-101
class Text1{
	public static void main(String[] args){
		int sum=0;
		int count=2;
		int i=1;
		while(i<=101){
			if(count%2==0){
				sum+=i;
			}else{
				sum-=i;
			}
			count+=1;
			i+=2;
		}
			System.out.println(sum);
				
		
		
	}
	
	
}
