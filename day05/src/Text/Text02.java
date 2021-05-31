package Text;

public class Text02 {
    public static void main(String[] args) {
        MyDate rq=new MyDate();
        rq.year=1995;
        rq.month=12;
        rq.day=25;
        System.out.println(rq.year+"年"+rq.month+"月"+rq.day+"日");
        MyDate rq1=new MyDate();
        rq1.year=2020;
        rq1.month=11;
        rq1.day=16;
        System.out.println(rq1.year+"年"+rq1.month+"月"+rq1.day+"日");
        MyDate rq2=new MyDate();
        rq2.year=2018;
        rq2.month=6;
        rq2.day=20;
        System.out.println(rq2.year+"年"+rq2.month+"月"+rq2.day+"日");
    }
}
