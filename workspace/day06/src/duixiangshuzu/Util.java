package duixiangshuzu;

public class Util {
//    public static User methon;
    int count=0;
    User [] users=new User[5];
    void aaa(User user1){
    users[count++]=user1;
    }


    static User putin(String username, String password){
        int count=0;
        User [] users=new User[5];
        User x=null;
        for (int i = 0; i <count ; i++) {
            if(username.equals(users[i].id)&&password.equals(users[i].pass)) {
//                            System.out.println("登陆成功");
                x=users[i];
                break;
            }
        }
        return x;
    }

}
