package Test;
/*
* 用递归实现不死神兔：故事得从西元1202年说起，话说有一位意大利青年，名叫斐波那契。
在他的一部著作中提出了一个有趣的问题：假设一对刚出生的小兔一个月后就能长成大兔，
再过一个月就能生下一对小兔，并且此后每个月都生一对小兔，没有发生死亡，
问：现有一对刚出生的兔子2年后(24个月)会有多少对兔子?
* */
public class Test03 {
    public static void main(String[] args) {
        Tu a=new Tu();
        int x=a.sums(24);
        System.out.println(x);
    }
}
class Tu{

    int sums(int n){
        if(n==1||n==2){
            return 1;
        }
        else
            return sums(n-1)+sums(n-2);

    }

}

