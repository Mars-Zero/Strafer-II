
import greenfoot.*;

public abstract class WorldListener extends Actor {

    Scroller scroller;
    Player player;

    public WorldListener(World world) {
        scroller = world.getObjects(Scroller.class).get(0);
        player = world.getObjects(Player.class).get(0);
    }

    public boolean isChangeWorld() {

        if (player.isAtEdge()) {
            if (scroller.getScrolledX() + WorldData.WIDTH / 2 >= scroller.getWide()) {

            }
            if (scroller.getScrolledX() + WorldData.WIDTH / 2 >= scroller.getWide()) {

            }
        }
        return false;
    }

    public void act() {

    }
}
