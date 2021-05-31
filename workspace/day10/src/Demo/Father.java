package Demo;

public abstract class Father {
    private String name;
    private int age;
    public abstract void eat();
    public abstract void run();

    public Father() {
    }

    public Father(String name, int age) {
        this.name = name;
        this.age = age;
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
    public String get(){
        return "姓名"+name+"年龄"+age;
    }
}
