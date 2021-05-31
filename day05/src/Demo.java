public class Demo{
    public static void main(String[] args) {
//        冒泡排序
/*        debug必须要会
                1.打断点
                2.右键，debug.run程序
                3.单步运行
                4.寻找下一个断点按钮
*/
/*        int[] arrs={1,41,17,9,23,45,50,2};
        for (int i = 0; i <arrs.length-1 ; i++) {
            for (int j = 0; j < arrs.length-1-j; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int temp = arrs[j];
                    arrs[j] = arrs[j + 1];
                    arrs[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < arrs.length; i++) {
             System.out.println(arrs[i]);
        }
        */
/*      选择排序：{1,41,17,9,23,45,50,2}
        1-->先找到最大值的索引位，然后放在最后一位
        2-->找到第二大的索引位置，放在倒数第二位
        ......
        找到最小的（n）的索引位置，放到0位置上。
 */
/*



* 简单顺序排序
*
*
* */
        int[] arrs={10,20,1,5,200,13,900,50};

        for (int j = 0; j <arrs.length-1 ; j++) {
            int index=-1;
            int max=0;
            for (int i = 0; i <arrs.length-j ; i++) {
                if(arrs[i]>max){
                    max=arrs[i];
                    index=i;
                }
            }
            if(index!=arrs.length-1-j){
                int temp=arrs[arrs.length-1-j];
                arrs[arrs.length-1-j]=arrs[index];
                arrs[index]=temp;
            }


            }
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(arrs[i]);
        }


    }
}
