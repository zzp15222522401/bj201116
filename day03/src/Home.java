class Home {
    public static void main(String[] args) {
        //���ݸ����ķ��������һ����������
        int max = 5;
        //��������
        for(int i = 1; i<=5 ; i++) {
            //���ƿո�
            for (int j = 1; j <= max - i; j++)
                System.out.print(" ");
                //��������
                for (int k = 1; k <= i * 2 - 1; k++)
                    System.out.print("*");
                    System.out.println();    
        }
    }
}