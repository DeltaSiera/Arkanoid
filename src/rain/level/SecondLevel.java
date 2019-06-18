package rain.level;

import rain.Game;
import rain.entity.mob.Brick;
import rain.entity.mob.StrongerBrick;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class SecondLevel extends Level {
    public SecondLevel(int width, int height, Game game) {
        super(width, height, game);
    }

    @Override
    public void startLevel() {
        generateLevel();
    }

    @Override
    protected void generateLevel() {
        ArrayList<Brick> list= new ArrayList<>();
        //IntStream.range(0,11).forEach(i -> list.add(new StrongerBrick(10 + (128 * i), 10)));
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                list.add(new StrongerBrick(15 + 124*j,10+64*i));
            }
        }
        game.setBrickArrayList(list);
    }
}
