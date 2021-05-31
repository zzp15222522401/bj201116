package com.atguigu.test.test2;

public class Body {
    private boolean live=true;

    public Body() {
    }

    public Body(boolean live) {
        this.live = live;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    class Heart{
        void jump(){
            if(live)
                System.out.println("心脏跳动");
            else
                System.out.println("心脏停止跳到");
        }
    }
}
class Test{
    public static void main(String[] args) {
        Body body=new Body();
        Body.Heart heart=new Body().new Heart();
        heart.jump();
        body.setLive(false);
        heart.jump();
    }
}
