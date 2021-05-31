package Lian0;

//当方法中访问变量时，如果没有this.，super.，那么都是遵循就近原则，找最近声明的
//当进行实例化对象时（也就是new对象时），会进行类加载，如果是子类new会优先进行父类的类加载，然后进行
public class B {
        public static void main1(String[] args){
            Son s = new Son();
            System.out.println(s.getNum());//10

            Daughter d = new Daughter();
            System.out.println(d.getNum());//20
        }
    }

    class Father{
        protected int num = 10;
        public int getNum(){
            return num;
        }
    }
    class Son extends Father{
        private int num = 20;
        /*public int getNum(){
            return num;
        }*/
    }
    class Daughter extends Father{
        private int num = 20;
        public int getNum(){
            return num;
        }
    }

