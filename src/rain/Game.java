package rain;

import rain.Graphics.Screen;
import rain.Graphics.Sprite;
import rain.entity.Entity;
import rain.entity.mob.*;
import rain.input.Keyboard;
import rain.level.FirstLevel;
import rain.level.Level;
import rain.level.SecondLevel;
import rain.level.ThirdLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Game extends Canvas implements Runnable, ActionListener {
    private AtomicInteger counterPaddle = new AtomicInteger(0);
    private AtomicInteger counterFlame = new AtomicInteger(0);
    public static int WIDTH = 700;
    public static int HEIGHT = WIDTH /6*8;
    private Login login;
    private Ball ball;
    private Keyboard keyboard;

    private Thread thread;
    private GameObject paddle;
    private boolean running = false;
    private Screen screen;
    private ArrayList<Brick> brickArrayList;
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
    private ArrayList<Level> stageArrayList;
    private int level;
    private Bonus bonus;
    private ArrayList<FlameBall> flameBalls;
    public void setBrickArrayList(ArrayList<Brick> brickArrayList) {
        if(brickArrayList != null){
            this.brickArrayList = brickArrayList;
        }

    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Game() {
        flameBalls = new ArrayList<>();
        ball = new Ball(450,HEIGHT-64);
        bonus = new Bonus(500,500);
        stageArrayList = new ArrayList<>();
        brickArrayList = new ArrayList<>();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        keyboard = new Keyboard(this);
        addKeyListener(keyboard);
        screen = new Screen(WIDTH,HEIGHT);

        try {
            paddle = new GameObject((double)400, (double)(HEIGHT -64),keyboard, this);
        } catch (PosException e) {
            System.out.println(e.getMessage());
        }

        generateLevels();

    }
    public synchronized void start(){
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }
    public synchronized void stop(){

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        login.setScore(Level.score);
    }

    private void generateLevels(){
        stageArrayList.add(new FirstLevel(16,16, this));
        stageArrayList.add(new SecondLevel(16,16,this));
        stageArrayList.add(new ThirdLevel(16,16,this));
    }

    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        boolean notNull = brickArrayList.stream().allMatch(Objects::nonNull)  &&
                stageArrayList.stream().allMatch(Objects::nonNull)&&
                ball != null  &&
                paddle != null;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (running && notNull){
            login.setScore(Level.score);
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                update();
                updates++;
                delta--;
                counterPaddle.incrementAndGet();
                counterFlame.addAndGet(1);
                checkBounds();
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer+= 1000;
                String title = "Arkanoid";
                //frame.setTitle(title + "  |  " +  updates + " ups " + " | " + frames  + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    private boolean checkCollision(Entity entity, Ball ball,String dir) {
        if(ball.getRectangle().intersects(entity.getRectangle())) {
            switch (dir) {
                case "top":
                    return ball.bottom() > entity.top() && ball.bottom() < (entity.top() + 25);
                case "right":
                    return ball.left() < entity.right() && ball.left() > entity.right() - 50;
                case "bottom":
                    return ball.top() < entity.bottom() && ball.top() >entity.bottom() - 25;
                case "left":
                    return ball.right() > entity.left() && ball.right() < entity.left() + 50;
            }
        }
        return false;
    }
    private boolean checkCollisionObjecteBall(Entity entity, Entity other) {
        if(entity != null && other != null)
            return other.getRectangle().intersects(entity.getRectangle());
        return false;
    }

    private void update(){
        checkStage();

        ball.move();
        boolean collision = false;
        if(checkCollisionObjecteBall(paddle,ball)){
            ball.changeDir(1);
            collision = true;
        }

        if(ball.isWasBottom() && !collision){
            Level.score = 0;
            stop();
        }
        if(checkCollisionObjecteBall(bonus, ball)){
            bonus.isUsed();
        }

        brickArrayList.forEach(brick -> {
            if (checkCollision(brick, ball, "top")) {
                ball.changeDir(1);
                brick.hit();
            } else if (checkCollision(brick, ball, "right")) {
                ball.changeDir(2);
                brick.hit();
            } else if (checkCollision(brick, ball, "bottom")) {
                ball.changeDir(3);
                brick.hit();
            } else if (checkCollision(brick, ball, "left")) {
                ball.changeDir(4);
                brick.hit();
            }
        });

        for (FlameBall flameBall1 : flameBalls) {
            for (Brick brick : brickArrayList) {
                if (checkCollisionObjecteBall(brick, flameBall1)) {
                    brick.hit();
                    flameBall1.Hit();
                }
            }
        }
        /*
        flameBalls = (ArrayList<FlameBall>) flameBalls.parallelStream()
                .filter(flameBall -> !flameBall.isHitted())
                .collect(Collectors.toList());*/
        for (int i =0; i < flameBalls.size(); i++){
            if(flameBalls.get(i).isHitted()){
                flameBalls.remove(flameBalls.get(i));
                i--;
            }
        }
        brickArrayList = (ArrayList<Brick>) brickArrayList.parallelStream()
                .filter(brick1 -> !brick1.isDead())
                .collect(Collectors.toList());


        keyboard.update();
        paddle.update();
    }
    private void render(){

        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        stageArrayList.forEach(stage -> stage.render(0,0,screen,level));

        paddle.render(screen, counterPaddle.get() % 24);

        if(!bonus.wasUsed())
            bonus.render(screen);
        ball.rennder(screen);
        brickArrayList.forEach(brick1 -> brick1.render(screen));

        for (int i = 0; i < flameBalls.size() && flameBalls.size() != 0 ; i++) {
            //System.out.println(flameBalls.size());
            flameBalls.get(i).render(screen, counterFlame.get() % 17);
            flameBalls.get(i).move();

        }
        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
        Graphics g = bs.getDrawGraphics();
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.PLAIN,50));

        g.dispose();
        bs.show();
    }
    private void checkBounds(){
        for(int i =0; i < flameBalls.size(); i++){
            if(flameBalls.get(i).getRectangle().getY() <= 0) {
                flameBalls.remove(i);
                i--;
            }
        }
    }
    private void checkStage(){
        if(brickArrayList.size() == 0){
            level++;
            flameBalls.clear();
            if(level > stageArrayList.size()){
                running = false;
            }else
                stageArrayList.get(level-1).startLevel();
        }
    }

    /*public static void main(String[] args) {
      JFrame frame = new JFrame();
        Game game = new Game();
        Sprite.fillHashmap();
        frame.setResizable(false);
        frame.setTitle("Arkanoid");
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

       SwingUtilities.invokeLater(game::start);
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void addFlameBall() {
        if (flameBalls.size() <= 16) {
            flameBalls.add(new FlameBall(paddle.left(), paddle.top() + 10));
            flameBalls.add(new FlameBall(paddle.right() - 50, paddle.top()));
        }else{
            System.out.println("Per daug");
        }
    }
}
