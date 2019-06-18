package rain.level.tile;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;

public class Tile {
    public int x,y;
    public Sprite sprite;

    public static Tile grass = new GrassTile(Sprite.spriteHashMap.get("grass"));
    public static Tile blackTile = new VoidTile(Sprite.spriteHashMap.get("blackTile"));
    public static Tile whiteTile = new VoidTile(Sprite.spriteHashMap.get("whiteTile"));


    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void render(int x, int y, Screen screen){

    }

    public boolean solid(){
        return false;
    }
}
