package rain.entity.mob;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;

import java.awt.*;

public class Bonus extends Mob {
    private boolean used = false;
    public Bonus(int x, int y) {
        this.rectangle = new Rectangle(x,y,64,64);
    }

    public void isUsed(){
        used = true;
    }

    public boolean wasUsed(){
        return used;
    }
    @Override
    public double left() {
        return 0;
    }

    @Override
    public double right() {
        return 0;
    }

    @Override
    public double top() {
        return 0;
    }

    @Override
    public double bottom() {
        return 0;
    }

    @Override
    public void render(Screen screen) {
        Sprite bonusSprite = Sprite.spriteHashMap.get("bonus");
        screen.renderObject(rectangle.getX(),rectangle.getY(),bonusSprite);
    }
}
