
import greenfoot.*;

public class PlayWorld extends World {

    int WIDE, HIGH;
    int originalX = 30, originalY = 200;

    private Scroller scroller;
    Player player;
    private WorldListener worldListener;

    public PlayWorld() {
        super(WorldData.WIDTH, WorldData.HIGHT, 1, false); //width, height, cellsize, daca sunt actorii restricted la lume

        setPaintOrder(Buton.class, Menu.class, Floor.class, Item.class, Lantern.class, Light.class, Player.class, Npc.class);

        WIDE = WorldData.WIDTH;
        HIGH = WorldData.HIGHT;
        addPlayer();
        WorldData.addedDialogs = false;
        addMainMenu();

    }

    private void addMainMenu() {
        addObject(new MainMenu(), ConstantVariables.MainMenuX, ConstantVariables.MainMenuY);
    }

    public void addPlayer() {
        GreenfootImage background = new GreenfootImage("images/test/map.png");//imi pun fundalul
        scroller = new Scroller(this, background, 8192, 8192);
        player = new Player();

        addObject(player, originalX, originalY);

        player.setWorldX(originalX);
        player.setWorldY(originalY);

        scroll();

        worldListener = new WorldListener(this);
        addObject(worldListener, 1, 1);

        addObject(new Fps(), 150, 50);
    }
    
    
   
    //adauga obiectele pe toata mapa, nu doar pe suprafata de display
    protected <T extends Actor> void initObject(T actor, int x, int y) {
        this.addObject(actor, x - Scroller.scrolledX, y - Scroller.scrolledY);
    }

    
    ///scrolleaza lumea
    public void scroll() {
        if (player != null) {
            int dsX = player.getX() - WIDE / 2;
            int dsY = player.getY() - HIGH / 2;
            scroller.scroll(dsX, dsY);

        }
    }

    public Scroller getScroller() {
        return scroller;
    }

    public void act() {
        scroll();

    }

   
}
