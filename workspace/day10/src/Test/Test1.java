package Test;

public class Test1 {
    public static void main(String[] args) {
        Person [] person=new Person[3];
        person[0]=new Chinnese();
        person[1]=new American();
        person[2]=new Indian();
        for (int i = 0; i < person.length; i++) {
            person[i].eat();
        }
    }
}
class Chinnese {
    public void eat(){
        System.out.println("用筷子吃饭");
    }
}
class American {
    public void eat(){
        System.out.println("用刀叉吃饭");
    }
}
class Indian {
    public void eat(){
        System.out.println("用手抓饭");
    }
}
