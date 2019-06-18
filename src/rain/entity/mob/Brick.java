package rain.entity.mob;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;
import rain.entity.Entity;
import rain.level.Level;

import java.awt.*;

public class Brick extends Entity {

    protected boolean hitted = false;
    protected boolean isDead = false;
    protected Sprite spriteBrick1;
    protected Sprite spriteBrick2;
    protected int lives =2;
    public Brick(int x, int y) {
        rectangle = new Rectangle(x,y, 128,64);
    }
    public Brick (Rectangle r){
        this.rectangle = new Rectangle(r);
    }
    public void setHitted(boolean hitted) {
        this.hitted = hitted;
    }

    public void render(Screen screen){

        if(lives <2){
            spriteBrick1 = Sprite.spriteHashMap.get("brickHit1");
            spriteBrick2 = Sprite.spriteHashMap.get("brickHit2");

        } else{
            spriteBrick1 = Sprite.spriteHashMap.get("brick1");
            spriteBrick2 = Sprite.spriteHashMap.get("brick2");
        }
        screen.renderObject(rectangle.getX(),rectangle.getY(),spriteBrick1);
        screen.renderObject(rectangle.getX()+64 ,rectangle.getY(),spriteBrick2);
    }

    public boolean isDead(){
        return isDead;
    }

    public boolean isHitted() {
        return hitted;
    }

    public int getScore(){
        if(hitted)
            Level.score+=1;
        if(isDead) Level.score +=2;
        return 0;
    }
    @Override
    public double left() {
        return rectangle.getX();
    }

    @Override
    public double right() {
        return rectangle.getX()+rectangle.getWidth();
    }

    @Override
    public double top() {
        return rectangle.getY();
    }

    @Override
    public double bottom() {
        return rectangle.getY() +rectangle.getHeight();
    }
    public void hit(){
        hitted = true;
        lives--;
        if(lives < 1) isDead = true;
        getScore();
    }
}
