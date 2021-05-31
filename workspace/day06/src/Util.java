

public class Util {
    void util() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    void paixu(int[] arrs, String str) {
        if (str.equals("升序")) {
            for (int i = 0; i < arrs.length - 1; i++) {
                for (int j = 0; j < arrs.length - 1 - i; j++) {
                    if (arrs[j] > arrs[j + 1]) {
                        int temp = arrs[j];
                        arrs[j] = arrs[j + 1];
                        arrs[j + 1] = temp;//由小到大
                    }
                }
            }
        } else if (str.equals("降序")) {
            for (int i = 0; i < arrs.length - 1; i++) {
                for (int j = 0; j < arrs.length - 1 - i; j++) {
                    if (arrs[j] < arrs[j + 1]) {
                        int temp = arrs[j];
                        arrs[j] = arrs[j + 1];
                        arrs[j + 1] = temp;//由大到小
                    }
                }
            }
        }
    }
        int num(){
        int num1=0;
            for (int i = 1; i <=100; i++) {
                num1+=i;
            }
            return num1;
        }
        int shuzu(int[]arrs){
            int count=0;
            for (int i = 0; i <arrs.length ; i++) {
                if(arrs[i]%2==0){
                    count++;
                }
            }
            return count;
        }

    }




