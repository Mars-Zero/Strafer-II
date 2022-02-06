import greenfoot.*; 


public class GameOver extends Menu{
   
    PlayWorld playWorld;

    boolean butoanead = false;

    public GreenfootSound sound = new GreenfootSound("sounds/music/Rename.mp3");

    public GameOver(PlayWorld playWorldref) {

        WorldData.PAUZA = true;
        setImage("UI/menu/gameOver.png");
        playWorld=playWorldref;

    }

    private void addButoane() {
        getWorld().addObject(new Buton("Continue", this), 355, 475);
        getWorld().addObject(new Buton("Main Menu", this), 655, 475);
    }
    

    public void act() {
       
        if (!butoanead) {
            addButoane();
            butoanead = true;
            sound.playLoop();
            sound.setVolume(60);
        }
    }

    public GreenfootSound getSound() {
        return sound;
    }

    public void setSound(GreenfootSound sound) {
        this.sound = sound;
    }
}
