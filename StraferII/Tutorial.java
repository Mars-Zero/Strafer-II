import greenfoot.*;  
import java.util.List;


public class Tutorial extends Menu
{
    protected int nrSlide;

    protected int nrDialog;
    String img;
    Npc npc;
    GreenfootImage text;
    protected boolean addedButon = false;
    protected boolean addedText = false;
    List lines ;
    
    
    public Tutorial(Friendly npcref, String imgref,int nrDialog) { 
        WorldData.PAUZA = true;
        img = imgref;
        npc = npcref;
        nrSlide = 0;
        this.nrDialog=nrDialog;
        addedButon = false;
        addedText = false;
        lines=DialogLoader.load(img, nrDialog);
        setImage("UI/dialog/dialog" + img + ".png");
    

    }

    public String getLine(int nr) {//nr slideului

        String line = (String)lines.get(nr);
        /////parsarea
        return line;
    }

    public void displayText(String txt) {
        if (!addedText) {
            getWorld().addObject(new Text(txt, 24), 522, 500);
            addedText = true;
        }
    }

    public void addButon() {
        if (!addedButon) {
            getWorld().addObject(new Buton("Next", this), 730, 470);
            addedButon = true;
        }
    }

    public void act() {
        addButon();
        displayText(getLine(getNrSlide()));
    }

    public int getNrSlide() {
        return nrSlide;
    }

    public void setNrSlide(int nrSlide) {
        this.nrSlide = nrSlide;
    }

    public boolean isLastSlide() {
        return nrSlide == lines.size()-1 ? true : false;
    }  
}
