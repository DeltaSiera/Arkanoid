package rain.entity.mob;

import rain.Game;
import rain.Graphics.Screen;
import rain.Graphics.Sprite;
import rain.PosException;
import rain.input.Keyboard;

import javax.swing.*;
import java.awt.*;

public class GameObject extends Mob {
    private Keyboard input;

    public GameObject(double x, double y, Keyboard input, Game game) throws PosException {
        if( x < 0 || y < 0 )
            throw new PosException("Bad inout");
        rectangle = new Rectangle((int)x,(int)y,192,1);
        this.input = input;
    }

    public void update() {
        int xa = 0;
        if (input.right) {
            xa += 20;
            move(xa, 0);
        }
        if (input.left) {
            xa -= 20;
            move(xa, 0);
        }
        if (xa == 0) {
            move(0, 0);
        }
    }

    public void render(Screen screen, int i) {

        if(i == 0 || i == 1 || i == 2 || i ==3|| i ==4|| i ==5) {

            screen.renderObject(rectangle.getX(), rectangle.getY(), Sprite.spriteHashMap.get("paddle1"));
            screen.renderObject(rectangle.getX() + 64, rectangle.getY(), Sprite.spriteHashMap.get("paddle2"));
            screen.renderObject(rectangle.getX()+64+64, rectangle.getY(), Sprite.spriteHashMap.get("paddle3"));
        }

        else if(i ==6 || i == 7 || i == 8 || i == 9 || i ==10 || i ==11) {

            screen.renderObject(rectangle.getX(), rectangle.getY(), Sprite.spriteHashMap.get("paddle4"));
            screen.renderObject(rectangle.getX() + 64, rectangle.getY(), Sprite.spriteHashMap.get("paddle5"));
            screen.renderObject(rectangle.getX() + 64+64, rectangle.getY(), Sprite.spriteHashMap.get("paddle6"));
        }

        else if( i == 12 ||i== 13 || i == 14 || i == 15 || i == 16 || i == 17) {

            screen.renderObject(rectangle.getX(), rectangle.getY(), Sprite.spriteHashMap.get("paddle7"));
            screen.renderObject(rectangle.getX() + 64, rectangle.getY(), Sprite.spriteHashMap.get("paddle8"));
            screen.renderObject(rectangle.getX() + 64+64, rectangle.getY(), Sprite.spriteHashMap.get("paddle9"));
        }else if( i == 18 ||i== 19 || i == 20 || i == 21 || i == 22 || i == 23) {

            screen.renderObject(rectangle.getX(), rectangle.getY(), Sprite.spriteHashMap.get("paddle10"));
            screen.renderObject(rectangle.getX() + 64, rectangle.getY(), Sprite.spriteHashMap.get("paddle11"));
            screen.renderObject(rectangle.getX() + 64+64, rectangle.getY(), Sprite.spriteHashMap.get("paddle12"));
        }
    }

    @Override
    public double left() {
        return rectangle.getX();
    }

    @Override
    public double right() {
        return rectangle.getX()+rectangle.getWidth()-20;
    }

    @Override
    public double top() {
        return rectangle.getY();
    }

    @Override
    public double bottom() {
        return rectangle.getY() + rectangle.getHeight();
    }

}
