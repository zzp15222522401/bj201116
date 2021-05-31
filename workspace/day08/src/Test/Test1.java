package Test;
public class Test1 {
    public static <Test1lean> void main(String[] args) {
        /*Test1bean a=new Test1bean("方片","5");
        a.showCard();*/


        //id 书名 作者  价格  销量 库存
        //创建Book数组，创建多个Book对象，存储到数组中，并遍历显示他们的信息，并按照销量从高到低排序
        Book[] Book=new Book[5];
        Book[0]=new Book("111","西游记","吴承恩",45,10000,15000);
        Book[1]=new Book("222","水浒传","施耐庵",30,8000,18000);
        Book[2]=new Book("333","红楼梦","曹雪芹",35,5000,20000);
        Book[3]=new Book("444","三国","罗贯中",60,12000,8000);
        Book[4]=new Book("555","动漫","爱谁谁",20,1000,5000);
        System.out.println("遍历信息");
        for (int i = 0; i <Book.length ; i++) {
            System.out.println(Book[i].get());
        }
        System.out.println("--------------------------------");
            //对数组进行排序（按销量的多少，本题就是out值），
            // Book中的元素属于Book类，所以需要定义一个Book型变量用来交换。
        for (int i = 0; i <Book.length ; i++) {
            for (int j = 0; j <Book.length-1 ; j++) {
                if(Book[j].getOut()<Book[j+1].getOut()){
                    Book temp=Book[j];
                    Book[j]=Book[j+1];
                    Book[j+1]=temp;
            }

            }
        }
        //遍历输出按销量从高到低
        for (int i = 0; i <Book.length ; i++) {

                System.out.println(Book[i].get());

        }
    }
}
