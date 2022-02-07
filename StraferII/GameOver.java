import greenfoot.*; 


public class GameOver extends Menu{
   
    PlayWorld playWorld;

    boolean butoanead = false;

    public GreenfootSound music = new GreenfootSound("sounds/music/Rename.mp3");

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
            music.playLoop();
            music.setVolume(60);
        }
    }

    public GreenfootSound getMusic() {
        return music;
    }

    public void setMusic(GreenfootSound music) {
        this.music = music;
    }
}
