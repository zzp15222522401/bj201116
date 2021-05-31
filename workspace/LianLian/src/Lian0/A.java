package Lian0;

import javax.swing.plaf.FontUIResource;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        double a=Math.random();//随机生成一个(0,1]的小数；
        System.out.println(a);

        String str="123456";
        int x= Integer.parseInt(str);//包装
        System.out.println(x);

         int a1=(int)123.9;//强转
        System.out.println(a1);

       /* System.out.println("请输入想要输入的内容：");
        char a0=input.next().charAt(4);
        System.out.println(a0);*/
        int i=0;
        do {
            System.out.println("?");
            i++;
            //先运行do语句中的代码一次，然后判断while条件，成立继续运行do,不成立就执行while下的语句。
            }
        while (i<10);{
            System.out.println("You Out");

        }

        /*int []arrs={1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9};
        for (int j = 0; j <arrs.length ; j++) {
            if(arrs[j]==5){
                System.out.println(j);
            }

        }*/
        /*System.out.println("::::");
        int q=input.nextInt();
        System.out.println(":::");
        int w=input.nextInt();
        int e=(q>w?q:w);
        System.out.println(e);
*/
        /*int []arr={1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9};
        for (int j = 0; j <arr.length-1 ; j++) {
            for (int k = 0; k <arr.length-j-1 ; k++) {      //排序，注意数组长度，不要超出数组长度，
                                                            // 不然会出现空指针
                if(arr[k]>arr[k+1]){
                    int temp=arr[k];
                    arr[k]=arr[k+1];
                    arr[k+1]=temp;
                }
            }
        }
        for (int i1 : arr) {
            System.out.println(i1);     //数组打印需要循环遍历，打印每一个数组元素
        }*/
        //int [][]arr=new int[10][];
            /*int [][]arr=new int[][]{{1,2,3,4},{2,3,4,5},{3,4,5,6},{4,5,6,7}};
            for (int j = 0; j <arr.length ; j++) {
            for (int k = 0; k <arr[j].length ; k++) {
                System.out.println(arr[j][k]);
            }
        }*/

            //
       int[][]arrs=new int[9][];
            for (int k = 0; k <arrs.length ; k++) {
                arrs[k]=new int[k+1];}
                for (int l = 0; l <arrs.length ; l++) {
                    for (int j = 0; j <arrs[l].length; j++) {
                        arrs[l][j]=l+1;

                        }
                    }
        for (int j = 0; j<arrs.length ; j++) {
            for (int k = 0; k <arrs[j].length ; k++) {
                System.out.print(arrs[j][k]);
            }
            System.out.println();
        }
                }
            }





