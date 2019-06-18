package rain.Graphics;

import rain.level.tile.Tile;

import java.util.Random;

public class Screen {
    public int width,
                height;
    public int[] pixels;
    private final int MAP_SIZE = 64;
    private int xOffset, yOffset;
    private int[] tiles = new int[MAP_SIZE*MAP_SIZE];
    private Random random = new Random();

    public void clear(){
        for(int i = 0; i <pixels.length; i++){
            pixels[i]=0;
        }
    }

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width*height];

        for(int i = 0;  i < MAP_SIZE*MAP_SIZE; i++){
            tiles[i] = random.nextInt(0xffffff);
            tiles[0]=0;
        }

    }

    public void renderTile(int xp, int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y< tile.sprite.SIZE; y++){
            int ya = yp + y;
            for(int x = 0; x < tile.sprite.SIZE; x++){
                int xa = x + xp;
                if(xa < -tile.sprite.SIZE || xa >= width || ya < 0  || ya >= height) break;
                if(xa <0) xa = 0;
                pixels[xa+ya*width] = tile.sprite.pixels[x + y*tile.sprite.SIZE];
            }
        }
    }

    public  void renderObject(double xp, double yp, Sprite sprite){
        xp -= xOffset;
        yp -= yOffset;
        for(int y = 0; y< sprite.SIZE; y++){
            double ya = yp + y;
            for(int x = 0; x < sprite.SIZE; x++){
                double xa = x + xp;
                if(xa < -sprite.SIZE || xa >= width || ya < 0  || ya >= height) break;
                if(xa <0) xa = 0;
                int color = sprite.pixels[x + y*sprite.SIZE];
                if(color != 0xffFDE6E7 && color!=0xffffffff && color != 0xff000000)
                pixels[(int)xa+(int)ya*width] = color;
            }
        }
    }


    public void setOffset(int xOffset, int yOffset){
        this.xOffset =xOffset;
        this.yOffset = yOffset;
    }
}
