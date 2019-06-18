package rain.entity.mob;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;
import rain.entity.Entity;

import java.awt.*;

public class FlameBall extends Entity {
    private boolean hitted = false;

    public FlameBall(double x, double y) {
        rectangle= new Rectangle((int)x,(int)y,63,64);
    }

    public boolean isHitted() {
        return hitted;
    }

    public void Hit() {
        hitted = true;
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
        return rectangle.getY() + rectangle.getHeight();
    }
    public void move(){
        rectangle.setLocation((int)rectangle.getX(),(int)rectangle.getY()-3);
    }
    public void render(Screen screen, int i) {

        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
            screen.renderObject(rectangle.getX(), rectangle.getY(), Sprite.spriteHashMap.get("flameball1"));
            screen.renderObject(rectangle.getX(), rectangle.getY()+64, Sprite.spriteHashMap.get("flameball2"));

        } else if (i == 6 || i == 7 || i == 8 || i == 9 || i == 10 || i == 11) {
            screen.renderObject(rectangle.getX(), rectangle.getY(), Sprite.spriteHashMap.get("flameball3"));
            screen.renderObject(rectangle.getX(), rectangle.getY()+64, Sprite.spriteHashMap.get("flameball4"));

        } else if (i == 12 || i == 13 || i == 14 || i == 15 || i == 16 || i == 17) {
            screen.renderObject(rectangle.getX(), rectangle.getY(), Sprite.spriteHashMap.get("flameball5"));
            screen.renderObject(rectangle.getX(), rectangle.getY()+64, Sprite.spriteHashMap.get("flameball6"));
        }
    }
}
