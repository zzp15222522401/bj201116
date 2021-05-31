package Text.Text07;

public class Text07 {
    public static void main(String[] args) {
        MyArrays a=new MyArrays();
        int[] arrs={4,5,2,6,1};
        System.out.println("排序前：");
        a.print(arrs);

        a.sort(arrs);
        System.out.println("排序后：");
        a.print(arrs);
        int x=a.indexOf(arrs,13);
            System.out.println(x);
        int[] arrs1=a.copy(arrs,3);
            System.out.println();
        a.print(arrs1);
    }
}
