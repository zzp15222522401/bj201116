package Text.Text07;

public class MyArrays {
    void sort(int[]arrs){
        for (int i = 0; i <arrs.length-1 ; i++) {
            for (int j = 0; j <arrs.length-i-1; j++) {
                if(arrs[j]>arrs[j+1]){
                    int temp=arrs[j];
                    arrs[j]=arrs[j+1];
                    arrs[j+1]=temp;
                }
            }
        }
    }
    int indexOf(int[]arr,int value){

        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]==value){
                return i;
            }
        }
        return -1;

    }

    int[]copy(int[]arr,int len){
        int[] arrs=new int[len];
        for (int i = 0; i <arr.length&&i<arrs.length ; i++) {
                arrs[i]=arr[i];
        }
        return arrs;
    }
    void print(int[]arr){
        System.out.print("[");
        for (int i = 0; i <arr.length ; i++) {
            if(i==0){
                System.out.print(arr[i]);
            }else{
                System.out.print(","+arr[i]);
            }
        }
        System.out.print("]");
        }

}
