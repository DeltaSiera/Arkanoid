package rain.level;

import rain.Game;
import rain.entity.mob.Brick;
import rain.entity.mob.StrongerBrick;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FirstLevel extends Level {
    public FirstLevel(int width, int height, Game game) {
        super(width, height,game);
        this.game = game;
    }

    @Override
    public void startLevel() {
        generateLevel();
    }

    protected void generateLevel(){
        //IntStream.range(0,18).limit(18).forEach(i -> bricks.add(new Brick(i>8?20 + 128 * (i-9) :20 + 128 * i, i > 8 ? 74 : 10)));
        /*ArrayList<Brick> bricks = (ArrayList<Brick>) IntStream.range(0,5)
                .mapToObj(i -> new Brick(10 + (128*i),10))
                .collect(Collectors.toList());*/
        ArrayList<Brick> bricks = new ArrayList<>();
        IntStream.range(0,5).forEach(i -> bricks.add(new StrongerBrick(25+128*i,0)));
        for(int i =0; i < 3; i++){
            for(int j = 0; j < 5; j++){
                bricks.add(new Brick(new Rectangle(25+128*j,64*(i+1),128,64)));
            }
        }
        game.setBrickArrayList(bricks);
    }
}
