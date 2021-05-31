package jicheng;

public class dog extends bean {
    private String a;
    private String b;

    public dog(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public dog(){
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void eat(){
        System.out.println("吃");
    }
}
