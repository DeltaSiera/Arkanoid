package rain.entity;

import rain.Graphics.Screen;
import rain.level.Level;

import java.awt.*;
import java.util.Random;

public abstract class Entity {
    protected Rectangle rectangle;
    protected boolean removed = false;
    protected Level level;
    protected final Random random = new Random();

    public void update(){

    }

    protected void render(Screen screen){ ;
    }
    public void remove(){
        //remove from level
        removed = true;
    }
    public boolean isRemoved(){
        return  removed;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public abstract double left();
    public abstract double right();
    public abstract double top();
    public abstract double bottom();
}
