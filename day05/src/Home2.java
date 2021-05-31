//案例：从键盘输入本组学员的人数，和本组学员的成绩，用数组存储成绩，然后实现从高到低排序
/*如果有5名学员，


*/

import java.util.Scanner;

public class Home2 {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.print("请输入本组学员人数：");
        int x=input.nextInt();
        int[] arrs=new int[x];      //定义数组长度即学员人数（x）
        for (int i = 0; i <arrs.length; i++) {
            System.out.println("请输入第"+(i+1)+"名学员成绩：");
            int cj=input.nextInt();
            arrs[i]=cj;         //给数组赋值
        }                       //arrs数组此时已经赋完值，下面进行排序
        for (int a = 0; a <arrs.length ; a++) {
            System.out.println(arrs[a]);
        }
        for (int i = 0; i <arrs.length ; i++) {             //先进行循环找出数组最大值放置最左边
            for (int j = 0; j <arrs.length-1-i ; j++) {      //依次两两对比把大值放置左边
            if(arrs[j]<arrs[j+1]) {
               int temp= arrs[j+1];
               arrs[j+1]=arrs[j];
               arrs[j]=temp;
            }
            }
        }
        System.out.println("由高到低成绩为：");
        for (int i = 0; i <arrs.length ; i++) {
            System.out.println(arrs[i]);
        }
    }
}
