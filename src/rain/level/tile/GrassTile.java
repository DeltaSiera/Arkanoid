package rain.level.tile;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;

public class GrassTile extends Tile {
    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    public void render(int x, int y, Screen screen){
        //do render here
        screen.renderTile(x << 5,y<<5, this);
    }
}
