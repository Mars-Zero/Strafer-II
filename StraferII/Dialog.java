import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Dialog extends Menu{
    
    protected int nrSlide;
    protected int maxSlide;
    String img;
    Npc npc;
    GreenfootImage text;
    protected boolean addedButon=false;
    
    public Dialog(Friendly npcref,String imgref,int maxSlide,String fileref){ ///???? cum incarcam text si de unde idk ne trebuie parser maybe
        WorldData.PAUZA=true;
        img=imgref;
        npc=npcref;
        nrSlide=0;
        addedButon=false;
        setImage("UI/dialog/dialog"+ img+".png");
        this.maxSlide=maxSlide;
        
    }
    
    public void displayText(String txt){
        text=new GreenfootImage(txt,24,Color.BLACK,null);
        text.setFont(new Font("fonts/pixelFont.tff",24));
    }
    
    public void addButon(){
        if(!addedButon){
            getWorld().addObject(new Buton("Next",this),700,300);
            addedButon=true;
        }
    }
    
    public void act() {
        addButon();
      
    }    
    
    
    public int getNrSlide() {
        return nrSlide;
    }

    public void setNrSlide(int nrSlide) {
        this.nrSlide = nrSlide;
    }
    public boolean isLastSlide(){
        return nrSlide==maxSlide? true:false;
    }
    
}
