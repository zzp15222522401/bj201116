public class LianXi {
        public static void main(String[] args) {
            Father f = new Son();
            f.test();
            //只看编译时类型
        }
    }
    class Father{
        public static void test(){
            System.out.println("father");
        }
    }
    class Son extends Father{
        public static void test(){
            System.out.println("son");
        }
    }

