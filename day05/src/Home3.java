//案例：从键盘输入本组学员的人数，和本组学员的姓名，用数组存储姓名，
// 然后再从键盘输入一个姓名，查找它是否在之前的数组中，如果存在，就显示它的下标
//在数组中寻找某一个数值（或者字符），编码中定义索引值index和count;
import javax.swing.plaf.FontUIResource;
import java.util.Scanner;

public class Home3 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        System.out.println("请输入本组学员人数：");
        int x=input.nextInt();
        String[] str=new String[x];
//        int count=0;
        int index=-1;
        for (int i = 0; i <str.length ; i++) {
            System.out.println("第"+(i+1)+"名学员的姓名:");
            String str1=input.next();
            str[i]=str1;
        }
//        定义数组长度并且给数组赋值(把名字录入数组)；
        System.out.println("输入你想要寻找的姓名：");
        String str2=input.next();
        for (int i = 0; i <str.length ; i++) {      //查找数组中是否存在这个姓名，如果有，就把姓名所在索引位赋给index
            if(str2.equals(str[i])){
                index=i;
                break;                              //找到第一个就停止，后面又重名不考虑。
            }
        }
        if(index==-1){
            System.out.println("不存在此人");
        }
        else{
            System.out.println(index);
        }
    }
}
