package xiangmu;

public class Customer {
    private int id;
    private String name;
    private char gender;
    private int age;
    private int phone;
    private String email;
    private static int a=0;
    public Customer() {
    }

    public Customer(String name, char gender, int age, int phone, String email) {
        this.id = a++;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public Customer(int id, String name, char gender, int age, int phone, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

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
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getinfo(){
        return "编号："+id+"\t姓名"+name+"\t性别："+gender+"\t年龄："+age+"\t电话："+phone+"\t邮箱："+email;
    }
}
