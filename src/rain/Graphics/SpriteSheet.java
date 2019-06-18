package rain.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private String path;

    final int SIZE;
    int[] pixels;
    static SpriteSheet tiles = new SpriteSheet("/spritesheet.png",1024);

    public SpriteSheet(String path, int size)  {
        this.path = path;
        this.SIZE = size;
        pixels = new int [SIZE*SIZE];
        load();

    }

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
            {
                int w = image.getWidth();
                int h = image.getHeight();
                image.getRGB(0,0,w,h,pixels,0,w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
