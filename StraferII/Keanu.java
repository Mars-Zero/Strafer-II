import greenfoot.*;  


public class Keanu extends Friendly
{
    private PlayWorld playWorld;
    private String dialogFile;
    private int maxSlide;
    GifImage img=new GifImage("npc/friendly/keanu_idle.gif");
    
    public Keanu(PlayWorld pw,Scroller scrl,int maxSlideref, String dialogFileref) {
        super(pw,scrl,dialogFileref);
        playWorld=pw;
        dialogFile=dialogFileref;
        maxSlide=maxSlideref;
    }
    
    public void addDialogs(){
        getWorld().addObject(new Dialog(this, "Keanu", maxSlide, dialogFile), 512, 400);
    }
    
    private int timer=0;
    public void act() {
        
        if (!WorldData.PAUZA) {
            timer ++;
            if (isTouching(Player.class) && !WorldData.addedDialogs&& timer>=180 ){
                timer=0;
                this.addDialogs();
                WorldData.addedDialogs=true;
            }
            setImage(img.getCurrentImage());
        }
    }    
}
