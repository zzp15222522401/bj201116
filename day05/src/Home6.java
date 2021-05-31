import java.util.Scanner;
public class Home6 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("输入数组的行数：");
        int m=input.nextInt();
        System.out.println("输入数组的列数：");
        int n= input.nextInt();
        int[][] arrs=new int[m][n];
        for(int i=0; i<arrs.length; i++){
            for(int j=0; j<arrs[i].length; j++){
                arrs[i][j]=i+j;
                System.out.print(arrs[i][j]);
            }
            System.out.println();
        }
    }
}
