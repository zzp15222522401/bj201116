package duixiangshuzu;

public class demo1 {
    public static void main(String[] args) {


        Demo1_1[] users = new Demo1_1[5];//users数组的默认值是null

        //赋值
        Demo1_1 user = new Demo1_1();
        user.id = 1;
        user.username = "root";
        user.password = "admin";
        user.name = "你是谁的谁";
        user.age = 18;

        users[0] = user;

        //遍历
        for (Demo1_1 user1 : users) {
            if (user1 != null) {
                System.out.println("编号：" + user1.id);
                System.out.println("用户名：" + user1.username);
                System.out.println("密码：" + user1.password);
                System.out.println("昵称：" + user1.name);
            }
        }
    }
}
