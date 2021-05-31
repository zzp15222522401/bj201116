/*
* - 使用二维数组打印一个 10 行杨辉三角.
  1
  1 1
  1 2 1
  1 3 3  1
  1 4 6  4  1
  1 5 10 10 5 1
   ....
- 开发提示
1. 第一行有 1 个元素, 第 n 行有 n 个元素
2. 每一行的第一个元素和最后一个元素都是 1
3. 从第三行开始, 对于非第一个元素和最后一个元素的元素.
* yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
* */

import java.util.Scanner;

/*public class Home5 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        //System.out.println("输入有多少行：");
      //  int l=input.nextInt();
        System.out.println("输入有多少列：");
        int n=input.nextInt();
        int[][] yanghui=new int[10][];
        for (int i = 0; i <10 ; i++) {
            for (int j =0 ; j <n-1 ; j++) {
                if(i==j||j==0){
                    yanghui[i][j]=1;
                }
                else
                yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
            }
        }
        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <n ; j++) {
                System.out.println(yanghui[i][j]);
            }

        }
    }
}
*/
public class Home5 {
    public static void main(String[] args){
        //(1)先确定行数
        int[][] yanghui = new int[10][];

        //(2)再确定每一行的列数
        //第一行有 1 个元素, 第 n 行有 n 个元素
        for(int i=0; i<yanghui.length; i++){
            yanghui[i] = new int[i+1];
        }

        //(3)再确定元素
        for(int i=0; i<yanghui.length; i++){
            //每一行的第一个和最后一个元素都是1
            yanghui[i][0] = 1;
            yanghui[i][i] = 1;

            //中间的元素
            for(int j=1; j<yanghui[i].length-1; j++){
                yanghui[i][j] = yanghui[i-1][j-1] + yanghui[i-1][j];
            }

        }

        //(4)打印显示
        for(int i=0; i<yanghui.length; i++){
            for(int j=0; j<yanghui[i].length; j++){
                System.out.print(yanghui[i][j] + "\t");
            }
            System.out.println();
        }
    }
}