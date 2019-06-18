package rain.entity.mob;

import rain.Game;
import rain.Graphics.Screen;
import rain.Graphics.Sprite;
import rain.entity.Entity;

import java.awt.*;

public class Ball extends Entity{
        private double  ball_Xvelocity = 9, //greitis x ašies atžvilgiu
                        ball_Yvelocity = -9; //greitis y ašies atžvilgiu
        private boolean wasBottom = false;  //ar, kamuoliukas palietė apačią
            public Ball(int xPos, int yPos) {
           rectangle = new Rectangle(xPos,yPos, 28,28);

        }
        public boolean isWasBottom() {
            return wasBottom;
        }

        public void move(){
            if(rectangle.getX() >= Game.WIDTH-32){
                ball_Xvelocity = -9;

            }
            if(rectangle.getX() < 0 ){
                ball_Xvelocity = 9;
            }

            if(rectangle.getY() >= Game.HEIGHT-10){
                ball_Yvelocity = -9;
                wasBottom = true;
            }
            if (rectangle.getY() < 16){
                ball_Yvelocity = 9;
            }
            rectangle.setLocation(new Point((int)(rectangle.getX()+ball_Xvelocity),
                                            (int)(rectangle.getY()+ball_Yvelocity)));
        }


        public void changeDir() {
            if(ball_Yvelocity > 0 ||(ball_Yvelocity <0)) ball_Yvelocity *=-1;

        }
        public void changeDir(int state) {
            if(state == 1){
                if((ball_Xvelocity > 0 && ball_Yvelocity > 0) || ball_Xvelocity < 0 && ball_Yvelocity > 0)
                    ball_Yvelocity *= -1;
            }
            if(state == 2){
                if(ball_Xvelocity < 0 && ball_Yvelocity < 0 || ball_Xvelocity < 0 && ball_Yvelocity >0 )
                    ball_Xvelocity *= -1;
            }
            if(state == 3){
                if(ball_Xvelocity > 0 && ball_Yvelocity < 0 || ball_Xvelocity < 0 && ball_Yvelocity < 0)
                    ball_Yvelocity *= -1;
            }
            if(state == 4){
                if(ball_Xvelocity > 0 && ball_Yvelocity > 0 || ball_Xvelocity > 0 && ball_Yvelocity < 0)
                    ball_Xvelocity *= -1;
            }
        }
        public void rennder(Screen screen){
            Sprite ball = Sprite.spriteHashMap.get("ball");
            screen.renderObject(rectangle.getX(), rectangle.getY(), ball);
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
}
