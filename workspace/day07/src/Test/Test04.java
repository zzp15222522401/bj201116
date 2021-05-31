package Test;
/*
* 描述：猴子吃桃子问题，猴子第一天摘下若干个桃子，当即吃了快一半，还不过瘾，又多吃了一个。第二天又将仅剩下的桃子吃掉了一半，
* 又多吃了一个。以后每天都吃了前一天剩下的一半多一个。到第十天，只剩下一个桃子。试求第一天共摘了多少桃子？
* */
public class Test04 {
    public static void main(String[] args) {
        Tao a=new Tao();
        int sum=a.tao(0);
        System.out.println(sum);
    }
}
class Tao{
    int tao(int x){
        if(x==10){
            return 1;
        }
        else
            return (tao(x+1)+1)*2;
    }

}

class Test10 {
    public static void main(String[] args) {
        Other o = new Other();
        new Test10().addOne(o);
        System.out.println(o.i);
    }

    public void addOne(Other o){
        o.i++;
    }
}
class Other{
     int i=5;
}