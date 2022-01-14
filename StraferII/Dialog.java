import greenfoot.*;

public class Dialog extends Menu {

    protected int nrSlide;
    protected int maxSlide;
    String img;
    Npc npc;
    GreenfootImage text;
    protected boolean addedButon = false;
    protected boolean addedText = false;

    public Dialog(Friendly npcref, String imgref, int maxSlide, String fileref) { ///???? cum incarcam text si de unde idk ne trebuie parser maybe
        WorldData.PAUZA = true;
        img = imgref;
        npc = npcref;
        nrSlide = 0;
        addedButon = false;
        addedText = false;
        setImage("UI/dialog/dialog" + img + ".png");
        this.maxSlide = maxSlide;

    }

    public String getLine(int nr) {//nr slideului

        String line = "test doi zece";
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
        return nrSlide == maxSlide ? true : false;
    }

    public boolean isAddedText() {
        return addedText;
    }

    public void setAddedText(boolean addedText) {
        this.addedText = addedText;
    }
}
