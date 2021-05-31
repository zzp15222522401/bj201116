package xiangmu.a1;

public class User {
    private int id;
    private  String name;
    private  int age;
    private String phone;
    private  String email;
    private static int ID=1;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public User(String name, int age, String phone, String email) {
        this.id=ID++;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public User(int id, String name, int age, String phone, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", (名字)name='" + name + '\'' +
                ", (年龄)age=" + age +
                ", (电话)phone='" + phone + '\'' +
                ", (邮箱)email='" + email + '\'' +
                '}';
    }
}
