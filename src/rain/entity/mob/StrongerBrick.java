package rain.entity.mob;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;
import rain.level.Level;

public class StrongerBrick extends Brick {

    public StrongerBrick(int x, int y) {
        super(x, y);
        this.lives = 4;
    }
    public void render(Screen screen){

        if(hitted && lives <= 2){
            spriteBrick1 = Sprite.spriteHashMap.get("brickHit3");
            spriteBrick2 = Sprite.spriteHashMap.get("brickHit4");

        } else{
            spriteBrick1 = Sprite.spriteHashMap.get("brick3");
            spriteBrick2 = Sprite.spriteHashMap.get("brick4");
        }
        screen.renderObject(rectangle.getX() ,rectangle.getY(),spriteBrick1);
        screen.renderObject(rectangle.getX()+64 ,rectangle.getY(),spriteBrick2);
    }

    @Override
    public int getScore() {
        if(hitted)
            Level.score += 3;
        if(isDead) Level.score += 4;
        return 0;
    }
}
