import greenfoot.*;  


public class Keanu extends Friendly
{
    private PlayWorld playWorld;
    private String dialogFile;
    private int maxSlide;
    private int nrDialog;
    GifImage img=new GifImage("npc/friendly/keanu_idle.gif");
    
    public Keanu(PlayWorld pw,Scroller scrl, String dialogFileref, int nrDialog) {
        super(pw,scrl,dialogFileref);
        playWorld=pw;
        this.nrDialog=nrDialog;
        dialogFile=dialogFileref;
        
    }
    
    public void addDialogs(){
        getWorld().addObject(new Dialog(this, "Keanu",  nrDialog), 512, 400);
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
