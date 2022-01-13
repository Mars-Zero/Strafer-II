import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Stonks extends Friendly
{
    private PlayWorld playWorld;
    private String dialogFile;
    private int maxSlide;
    GifImage img=new GifImage("npc/friendly/stonks_idle.gif");
    
    public Stonks(PlayWorld pw,Scroller scrl,int maxSlideref, String dialogFileref) {
        super(pw,scrl,dialogFileref);
        playWorld=pw;
        dialogFile=dialogFileref;
        maxSlide=maxSlideref;
    }
    
    public void addDialogs(){
        getWorld().addObject(new Dialog(this, this.getClass().toString(), maxSlide, dialogFile), 512, 400);
    }
    
    public void act() {
        if (!WorldData.PAUZA) {
            if (isTouching(Player.class) && !WorldData.addedDialogs ){
                this.addDialogs();
                WorldData.addedDialogs=true;
            }
            setImage(img.getCurrentImage());
        }
    }       
}
