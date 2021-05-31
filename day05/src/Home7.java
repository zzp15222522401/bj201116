/*1、请使用二维数组存储如下数据，并遍历显示
        1
        2 2
        3 3 3
        4 4 4 4
        5 5 5 5 5*/
public class Home7 {
    public static void main(String[] args) {
        int[][] arrs=new int[5][];
        for (int i = 0; i <arrs.length ; i++) {
            arrs[i]=new int[i+1];
        }
        for (int i = 0; i <arrs.length ; i++) {
            for (int j = 0; j <arrs[i].length ; j++) {
                arrs[i][j]=i+1;
                System.out.print(arrs[i][j]);
            }
            System.out.println();
        }

    }

}
