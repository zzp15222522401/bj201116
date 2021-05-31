class Home {
    public static void main(String[] args) {
        //根据给定的方法来输出一个等腰三角
        int max = 5;
        //控制行数
        for(int i = 1; i<=5 ; i++) {
            //控制空格
            for (int j = 1; j <= max - i; j++)
                System.out.print(" ");
                //控制星数
                for (int k = 1; k <= i * 2 - 1; k++)
                    System.out.print("*");
                    System.out.println();    
        }
    }
}