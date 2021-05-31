package LianXi;
//判断两个数是否相等
public class util {
    boolean flag(int a,int b){
        System.out.println("int");
        return(a==b);

        }
    //求最大值
    double flag(double a,double b){
        return(a>b?a:b);
    }
    int flag(int...a){
        System.out.println("int1");
        return 2;
    }
   String lianjie(char a,String...str){
        String str1="";
        if(str.length==0){
            System.out.println("1");
        }else{
            for (int i = 0; i <str.length ; i++) {
               // System.out.print(str[i]+a);   最后一次还有符号
                if(i==str.length-1){
                    str1+=str[i];
                }
                else{
                    str1+=str[i]+a;
                }
            }
        }
        return str1;
   }
}
