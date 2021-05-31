package jicheng;

public class person extends bean{
    private String speak;
    private String sikao;

    public person() {
    }

    public person(String speak, String sikao) {
        this.speak = speak;
        this.sikao = sikao;
    }

    public person(String name, int age, char gender, double tizhong, String speak, String sikao) {
        super(name, age, gender, tizhong);
        this.speak = speak;
        this.sikao = sikao;
    }

    public String getSpeak() {
        return speak;
    }

    public void setSpeak(String speak) {
        this.speak = speak;
    }

    public String getSikao() {
        return sikao;
    }

    public void setSikao(String sikao) {
        this.sikao = sikao;
    }

    public void sikao(){
        System.out.println("思考");

    }
    public void say(){
        System.out.println(super.getName());
    }
}
