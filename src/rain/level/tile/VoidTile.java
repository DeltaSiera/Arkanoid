package rain.level.tile;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;

public class VoidTile extends Tile {
    public VoidTile(Sprite voidSprite) {
        super(voidSprite);
    }

    public void render(int x, int y, Screen screen){
        screen.renderTile(x << 4,y <<4,this);
    }
}
