package Test;

public class Book {
    //id 书名 作者  价格  销量 库存
    //- 属性私有化，提供有参和无参构造，get/set
    //- 在com.atguigu.test05.test包中，创建TestBook类，
    // 并创建Book数组，创建多个Book对象，存储到数组中，并遍历显示他们的信息，并按照销量从高到低排序
    //- 注意：使用到Integer的地方用int就OK

    private String id;
    private String title;
    private String auther;
    private double price;
    private int out;
    private int in;

    public Book() {
    }

    public Book(String id, String title, String auther, double price, int out, int in) {
        this.id = id;
        this.title = title;
        this.auther = auther;
        this.price = price;
        this.out = out;
        this.in = in;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }
    public String get(){
        return "id:"+id+"\t书名："+title+"\t作者："+auther+"\t价格："+price
                +"\t卖出："+out+"\t库存："+in;
    }
}

