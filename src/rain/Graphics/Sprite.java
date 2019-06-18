package rain.Graphics;

import java.util.TreeMap;

public class Sprite {
    public final int SIZE;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;
    public static TreeMap<String, Sprite> spriteHashMap = new TreeMap<>();
    public static void fillHashmap(){
        spriteHashMap.put("paddle1", (new Sprite(64,0,4,SpriteSheet.tiles)));
        spriteHashMap.put("paddle2", (new Sprite(64,1,4,SpriteSheet.tiles)));
        spriteHashMap.put("paddle3", (new Sprite(64,2,4,SpriteSheet.tiles)));
        spriteHashMap.put("paddle4", (new Sprite(64,0,5,SpriteSheet.tiles)));
        spriteHashMap.put("paddle5", (new Sprite(64,1,5,SpriteSheet.tiles)));
        spriteHashMap.put("paddle6", (new Sprite(64,2,5,SpriteSheet.tiles)));
        spriteHashMap.put("paddle7", (new Sprite(64,0,6,SpriteSheet.tiles)));
        spriteHashMap.put("paddle8", (new Sprite(64,1,6,SpriteSheet.tiles)));
        spriteHashMap.put("paddle9", (new Sprite(64,2,6,SpriteSheet.tiles)));
        spriteHashMap.put("paddle10", (new Sprite(64,0,7,SpriteSheet.tiles)));
        spriteHashMap.put("paddle11", (new Sprite(64,1,7,SpriteSheet.tiles)));
        spriteHashMap.put("paddle12", (new Sprite(64,2,7,SpriteSheet.tiles)));
        spriteHashMap.put("ball", (new Sprite(32, 0,4, SpriteSheet.tiles)));
        spriteHashMap.put("blackTile",(new Sprite(16,0x000000)));
        spriteHashMap.put("whiteTile",(new Sprite(16,0xffffff)));
        spriteHashMap.put("brick1",(new Sprite(64,6,0,SpriteSheet.tiles)));
        spriteHashMap.put("brick2",(new Sprite(64,7,0,SpriteSheet.tiles)));
        spriteHashMap.put("brick3",(new Sprite(64,6,1,SpriteSheet.tiles)));
        spriteHashMap.put("brick4",(new Sprite(64,7,1,SpriteSheet.tiles)));
        spriteHashMap.put("brickHit1", (new Sprite(64,8,0,SpriteSheet.tiles)));
        spriteHashMap.put("brickHit2", (new Sprite(64,9,0,SpriteSheet.tiles)));
        spriteHashMap.put("brickHit3", (new Sprite(64,8,1,SpriteSheet.tiles)));
        spriteHashMap.put("brickHit4", (new Sprite(64,9,1,SpriteSheet.tiles)));
        spriteHashMap.put("bonus", (new Sprite(64,0,3,SpriteSheet.tiles)));
        spriteHashMap.put("laserpaddle1", (new Sprite(64,0,1,SpriteSheet.tiles)));
        spriteHashMap.put("flameball1", (new Sprite(64,0,8,SpriteSheet.tiles)));
        spriteHashMap.put("flameball2", (new Sprite(64,0,9,SpriteSheet.tiles)));
        spriteHashMap.put("flameball3", (new Sprite(64,1,8,SpriteSheet.tiles)));
        spriteHashMap.put("flameball4", (new Sprite(64,1,9,SpriteSheet.tiles)));
        spriteHashMap.put("flameball5", (new Sprite(64,2,8,SpriteSheet.tiles)));
        spriteHashMap.put("flameball6", (new Sprite(64,2,9,SpriteSheet.tiles)));
    }


    public Sprite(int size, int color){
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(color);
    }

    private void setColour(int colour) {
        for (int i=0;  i< SIZE*SIZE; i++){
            pixels[i]=colour;
        }
    }

    public Sprite(int size, int x, int y,SpriteSheet sheet){
        this.SIZE = size;
        pixels = new int[SIZE*SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }
    private void load(){
        for(int y = 0; y <SIZE; y++){
            for(int x= 0; x < SIZE; x++){
                pixels[x+y*SIZE] = sheet.pixels[(x + this.x) + (y+this.y)*sheet.SIZE];
            }
        }
    }
}
