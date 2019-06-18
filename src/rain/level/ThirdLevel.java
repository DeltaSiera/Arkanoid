package rain.level;

import rain.Game;
import rain.entity.mob.Brick;
import rain.entity.mob.StrongerBrick;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ThirdLevel extends Level {
    public ThirdLevel(int width, int height, Game game) {
        super(width, height, game);
    }

    @Override
    public void startLevel() {
        generateLevel();
    }

    @Override
    protected void generateLevel() {
        ArrayList<Brick> list= new ArrayList<>();
        IntStream.range(0,5).forEach(i -> list.add(new StrongerBrick(25 + (128 * i), 10)));
        IntStream.range(0,5).forEach(i -> list.add(new StrongerBrick(25 + (128 * i), 70)));
        IntStream.range(0,5).forEach(i -> list.add(new StrongerBrick(25 + (128 * i), 130)));
        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 5; j++){
                list.add(new Brick(25 + 128*j,190+64*i));
            }
        }
        game.setBrickArrayList(list);
    }
}
