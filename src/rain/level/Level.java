package rain.level;

import rain.Game;
import rain.Graphics.Screen;
import rain.level.tile.Tile;

import java.util.TimerTask;

public class Level {
    public static int score = 0;
    protected int width, height;
    protected int[] tiles;
    protected Game game;

    public Level(int width, int height, Game game){
        this.width= width;
        this.height = height;
        this.game = game;
        tiles = new int[width*height];
    }

    public void startLevel(){
        generateLevel();
    }
    public Level(String path){
        loadLevel(path);
    }

    private void loadLevel(String path) {

    }


    protected void generateLevel() {

    }
    public void update(){

    }
    private void time(){

    }
    public void render(int xScroll, int yScroll, Screen screen, int currLevel){
        screen.setOffset(xScroll,yScroll);
        int x0 = xScroll >> 4;
        int x1 = ((xScroll + screen.width+32) >> 4);
        int y0 = yScroll >> 4;
        int y1 = ((yScroll + screen.height+32) >> 4);


        for(int y = y0; y < y1; y++){
            for(int x = x0; x < x1; x++){
                getTile(x,y,currLevel).render(x,y,screen);
            }
        }
    }

    private Tile getTile(int x, int y, int currLevel) {

        //if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        //if (tiles[x + y * width] == 0) return Tile.grass;
        return Tile.blackTile;
    }
}
