
import greenfoot.*;

public class MainMenu extends Menu {

    GifImage background=new GifImage("fundal/title.gif");

    boolean butoanead = false;

    public MainMenu() {
        
        WorldData.PAUZA = true;
        setImage("fundal/titleScreen1.png");
    }

    private void addButoane() {
        getWorld().addObject(new Buton("Continue", this), 805, 225);
         getWorld().addObject(new Buton("New Game", this), 805, 380);
       
    }

    public void act() {
        setImage(background.getCurrentImage());
        if (!butoanead) {
            addButoane();
            butoanead = true;
        }
    }
}
