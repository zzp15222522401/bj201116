/*
public class Demo {
    public static void main(String[] args) {
        // 创建对象
        Count c = new Count();
        int[] arr = { 1, 4, 62, 431, 2 };
        int sum1 = c.getSum1(arr);
        System.out.println(sum1);

        int sum2 = c.getSum2(arr);
        System.out.println(sum2);
        int sum3 = c.getSum2(1, 4, 62, 431, 2);
        System.out.println(sum3);
    }
}
class Count {
    // 完成数组 所有元素的求和
    // 原始写法
    public int getSum1(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
    // 可变参数写法
    public int getSum2(int... arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }
}*/
public class Demo {
    public static void main(String[] args) {
        Count c = new Count();
        System.out.println(c.max(1));
        System.out.println(c.max(5,3,2,6,10));
    }
}
class Count{
    public int max(int num, int... others){
        int max = num;
        for (int i = 0; i < others.length; i++) {
            if(max < others[i]){
                max = others[i];
            }
        }
        return max;
    }
}
