package Demo;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Demo {
    public String name;
    private int age;
    String gender;

    public void setname(String n) {
        name = n;

    }

    public String getname() {
        return name;
    }
    public void setgender(String g) {
        gender = g;

    }

    public String getgender() {
        return gender;
    }

    public String get(){
        return name+gender;
    }
    public void setage(int a ) {
        if (a < 50 && a > 20) {
            age = a;
        } else {
            age = -11111111;
            System.out.println("你已超龄");
        }
    }
    public int getage() {
        return age;
    }

    public static void main(String[] args) {
        /*int []arrs={1,2,3,4,5,8};
        String a= Arrays.toString(arrs);
        System.out.println(a);
        int a1=Arrays.binarySearch(arrs,3);
        System.out.println(a1);*/



        //下面的代码是数组的删除和增加
        // srcpos表示原数组从哪里开始复制，destpos表示新数组从哪里开始（将原数组数据复制过来），
        // length表示新数组的长度
        int[] arr2 = {1,2,3,4,5};
        //往前移动元素
        System.arraycopy(arr2, 1, arr2, 0, 4);
        //最后位置恢复为默认值
        arr2[arr2.length-1] = 0;
        System.out.println(Arrays.toString(arr2));
        /*arr2 = Arrays.copyOf(arr2, arr2.length+5);
        System.arraycopy(arr2, 0, arr2, 1, 5);
        //在[0]插入10
        arr2[0] = 10;
        System.out.println(Arrays.toString(arr2));*/
    }



}



