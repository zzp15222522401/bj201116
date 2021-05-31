package Lian1;
//int []arrs={1,52,48,95,2,35,18,62,95,6}   选择排序
// 先挑选最大的放在最右边
// 然后找第二大的放在倒数第二位
// 以此类推寻找arrs.length-1次
// 判断找打的最大值是不是在我们需要放置的位置，不在的话我们要进行换位
// 进行小->大
public class Demo1 {
    public static void main(String[] args) {
        int [] arrs={1,52,48,2,35,18,62,95,6};

        for (int j = 0; j <arrs.length-1 ; j++) {
            int max = arrs[0];
            int index = -1;
            for (int i = 0; i <arrs.length-j ; i++) {
                if (arrs[i] > max) {
                    max = arrs[i];
                    index = i;

                }
            }
            if (index != arrs.length -j- 1) {
                int temp = arrs[index];
                arrs[index] = arrs[arrs.length -j- 1];
                arrs[arrs.length -j- 1] = temp;
            }


        }

       // System.out.println(index);
        for (int a = 0; a <arrs.length ; a++) {
            System.out.println(arrs[a]);

        }

    }

}
