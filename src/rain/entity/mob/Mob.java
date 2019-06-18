package rain.entity.mob;

import rain.Game;
import rain.Graphics.Sprite;
import rain.entity.Entity;

import java.awt.*;

public abstract class Mob extends Entity {
    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;
    public void move(int xa, int ya){
        if(xa > 0) dir = 1;
        if(xa < 0) dir = 3;

        if(!collision()) {
            rectangle.setLocation(new Point((int)rectangle.getX()+xa,(int)rectangle.getY()));
            rectangle.setLocation(new Point((int)rectangle.getX(),(int)rectangle.getY()+ya));
        }
    }
    public void update(){

    }

    private boolean collision(){
        return false;
    }

    public void render(){

    }
}
