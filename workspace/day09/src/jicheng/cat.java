package jicheng;

public class cat extends bean{
    private String a;
    private double b;

    public cat() {
    }

    public cat(String a, double b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void pa(){
        System.out.println("爬树");
    }
}
