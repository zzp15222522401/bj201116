
/*
* 随机验证码。

- 随机生成十组六位字符组成的验证码。
- 验证码由大小写字母、数字字符组成。


 */


import java.util.Random;

public class Home4 {
    public static void main(String[] args) {
        String[] arr = new String[10];
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "";
            for (int j = 0; j < 6; j++) {
                int num;
                while (true) {
                    num = rand.nextInt(123);            //随机生成0-1小数*123取到（0-123）的数值，判断数值范围转换成char值
                    //数字[48,57]  大写字母[65,90]  小写字母[97,122]
                    if (num >= 48 && num <= 57) {
                        break;
                    } else if (num >= 65 && num <= 90) {
                        break;
                    } else if (num >= 97 && num <= 122) {
                        break;
                    }
                }
                arr[i] += (char) num;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println("随机验证码：" + arr[i]);
        }
    }
}

