package Text.Text04;

import java.time.Year;

public class Text04 {
    public static void main(String[] args) {
        MyDate a=new MyDate();
        boolean flag=a.isLeapYear();
        System.out.println(flag);
        a.set(2020,11,24);
        a.puls(2,3,20);

    }
}
class MyDate{
    int year;
    int month;
    int day;
     boolean isLeapYear(){
         int time0=2020;
        if(year%4==0&&year%100!=0||year%400==0){
            System.out.println("今年是闰年.");
        }
        return true;

    }
    void set(int y,int m,int d){
         year=y;
         month=m;
         day=d;
        System.out.println("新日期为："+y+"年"+m+"月"+d+"日");
    }
    void puls(int years,int months,int days){
         year+=years;
         month+=months;
         day+=days;
         if(month>12){
             year++;
             month-=12;
         }
         if(month==1||month==3||month==5||
                 month==7||month==8||month==10||month==12){
             if(day>31){
                 month+=1;
                 day-=31;
             }
         }
             if(month==4||month==6||month==9||month==11){
                if(day>30){
                    month+=1;
                    day-=30;
                }
             }
             if(month==2){
                 if(year%4==0&&year%100==0||year%400==0){
                     if(day>29){
                         month+=1;
                         day-=29;
                     }

                 }
                 else if(day>28){
                     month+=1;
                     day-=28;
                 }
             }
        System.out.println("2年3月20天后是："+year+"年"+month+"月"+day+"日");

         }

    }





