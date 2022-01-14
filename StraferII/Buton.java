
import greenfoot.*;


public class Buton extends UI {

    
    GreenfootImage img0, img1;
    private String img = "";
    boolean clicked = false;
    Menu menu;
    Dialog dialog;
    public Buton(String imgref, Menu menuref) {
        img = imgref;
        menu = menuref;
        img0 = new GreenfootImage("UI/buton/" + img + "0.png");
        img1 = new GreenfootImage("UI/buton/" + img + "1.png");
        setImage(img0);

    }
    
    public Buton(String imgref,Dialog dialogref){
        img = imgref;
        dialog=dialogref;
        img0 = new GreenfootImage("UI/buton/" + img + "0.png");
        img1 = new GreenfootImage("UI/buton/" + img + "1.png");
        setImage(img0);
    }
    
    protected void checkHover() {

        if (Greenfoot.mouseMoved(this)) {
            setImage(img1);

        }
        if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            setImage(img0);
        }

    }

    protected void checkClick() {

        if (Greenfoot.mouseClicked(this)) {

            if (Greenfoot.getMouseInfo().getButton() == 1) {
                switch (img) {
                    case "Next": {
                       
                        dialog.setNrSlide(dialog.getNrSlide()+1);
                        dialog.setAddedText(false);
                        getWorld().removeObjects(getWorld().getObjects(Text.class));
                        if(dialog.isLastSlide()){
                            this.getWorld().addObject(new Buton("X",dialog),this.getX(),this.getY());
                            this.getWorld().removeObject(this);
                        }
                        break;
                     
                    }
                    case "X": {
                        WorldData.PAUZA=false;
                        getWorld().removeObject(menu);
                        getWorld().removeObject(dialog);
                        WorldData.addedDialogs=false;
                         dialog.setAddedText(false);
                        getWorld().removeObjects(getWorld().getObjects(Text.class));
                        getWorld().removeObject(this);
                        break;
                    }
                    case "Resume": {
                        
                        WorldData.PAUZA = false;
                        Player.toggledPause=false;
                        getWorld().removeObject(menu);
                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        break;

                    }
                    case "Map":{
                        
                    }
                    case "Main Menu":{
                        getWorld().addObject(new MainMenu(),ConstantVariables.MainMenuX,ConstantVariables.MainMenuY);
                        
                        Player.toggledPause=false;
                        getWorld().removeObject(menu);
                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        
                        
                        
                        /////se da save
                        
                        
                        break;
                    }
                    case "Tutorials":{
                        
                    }
                    case "New Game":{
                        //save file nou
                        //inceputu jocului
                        
                        break;
                    }
                    case "Continue":{
                         System.out.print(img);
                        WorldData.PAUZA = false;
                        Player.toggledPause=false;
                        getWorld().removeObject(menu);
                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        //da load
                        break;
                    }
                    default: {

                    }
                }

            }
        }

    }

    public void act() {
        checkClick();
        checkHover();
    }

}
