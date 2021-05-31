package xiangmu_mysql;

public class Customer {
   private  String un;
   private  String pw;
    public Customer() {
    }

    public Customer(String un, String pw) {
        this.un = un;
        this.pw = pw;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "un='" + un + '\'' +
                ", pw='" + pw + '\'' +
                '}';
    }
}

