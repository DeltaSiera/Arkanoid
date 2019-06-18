package rain.input;

import rain.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class Keyboard implements KeyListener {
    private boolean[] keys = new boolean[120];
    public boolean up;
    private boolean down;
    public boolean left;
    public boolean right;
    public boolean space;
    private final AtomicBoolean canShoot = new AtomicBoolean(true);
    private boolean yes;
    private Game game;

    public Keyboard(Game game) {
        this.game = game;
    }

    public  void update(){
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE];
        yes = keys[KeyEvent.VK_Y];
    }
    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (canShoot.compareAndSet(true, false)) {
                game.addFlameBall();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            canShoot.set(true);
        }
    }
}
