
import greenfoot.*;
import java.util.List;

public class Buton extends UI {

    GreenfootImage img0, img1;
    private String img = "";

    boolean clicked = false;
    Object obj;

    public Buton(String imgref, Object menuref) {
        img = imgref;
        obj = menuref;
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
                        if (obj instanceof Dialog) {
                            Dialog dialog = (Dialog) obj;
                            dialog.setNrSlide(dialog.getNrSlide() + 1);
                            dialog.setAddedText(false);
                            getWorld().removeObjects(getWorld().getObjects(Text.class));
                            if (dialog.isLastSlide()) {
                                this.getWorld().addObject(new Buton("X", dialog), this.getX(), this.getY());
                                this.getWorld().removeObject(this);
                            }
                        }

                        if (obj instanceof Tutorial) {
                            Tutorial tutorial = (Tutorial) obj;
                            tutorial.setNrSlide(tutorial.getNrSlide() + 1);
                            tutorial.setAddedPicture(false);
                            getWorld().removeObject(tutorial.getPicture());
                            if (tutorial.getNrSlide() > 0) {
                                if (!tutorial.isAddedButonBack()) {
                                    this.getWorld().addObject(new Buton("Back", tutorial), 100, this.getY());
                                }
                            }
                            if (tutorial.isLastSlide()) {
                                this.getWorld().addObject(new Buton("X", tutorial), this.getX(), this.getY());
                                this.getWorld().removeObject(this);
                            }
                        }

                        break;

                    }
                    case "Back": {

                        if (obj instanceof Tutorial) {
                            Tutorial tutorial = (Tutorial) obj;
                            tutorial.setNrSlide(tutorial.getNrSlide() - 1);
                            tutorial.setAddedPicture(false);
                            getWorld().removeObject(tutorial.getPicture());
                            if (tutorial.isFirstSlide()) {
                                this.getWorld().removeObject(this);
                            }

                            /*List butoaneGasite=null;
                                 butoaneGasite = getWorld().getObjects(Buton.class);
                                for (Object but : butoaneGasite) {System.out.println("A");
                                    if (but instanceof Buton) {
                                        Buton butt = (Buton) but;
                                        if (butt.getImg() == "X") {
                                            getWorld().addObject(new Buton("Next", obj), this.getX(), this.getY());
                                            getWorld().removeObject(butt);
                                        }
                                    }
                                }*/
                        }

                        break;
                    }
                    case "X": {
                        if (obj instanceof Dialog) {
                            WorldData.PAUZA = false;
                            getWorld().removeObject((Actor) obj);
                            WorldData.addedDialogs = false;

                            Dialog dialog = (Dialog) obj;
                            dialog.setAddedText(false);

                            getWorld().removeObjects(getWorld().getObjects(Text.class));
                        }
                        if (obj instanceof Tutorial) {
                            
                            Tutorial tutorial = (Tutorial) obj;
                            if (!tutorial.isInPause()) {        //daca e direct in lume 
                                WorldData.PAUZA = false;
                            }
                            
                            getWorld().removeObject((Actor) obj);
                            WorldData.addedDialogs = false;

                            tutorial.setAddedPicture(false);
                            
                            if (!tutorial.isLastSlide()) {
                                getWorld().addObject(new Buton("Next", obj), this.getX(), this.getY());
                                getWorld().removeObject(this);
                            }
                            
                            getWorld().removeObject(tutorial.getPicture());
                        }

                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        break;
                    }
                    case "Open": {

                        break;
                    }
                    case "Resume": {

                        WorldData.PAUZA = false;
                        Player.toggledPause = false;
                        if (obj instanceof Menu) {
                            getWorld().removeObject((Menu) obj);
                        }

                        getWorld().removeObjects(getWorld().getObjects(Buton.class));
                        break;

                    }
                    case "Map": {

                    }
                    case "Main Menu": {
                        getWorld().addObject(new MainMenu(), ConstantVariables.MainMenuX, ConstantVariables.MainMenuY);

                        Player.toggledPause = false;
                        if (obj instanceof Menu) {
                            getWorld().removeObject((Menu) obj);
                        }
                        getWorld().removeObjects(getWorld().getObjects(Buton.class));

                        /////se da save
                        break;
                    }
                    case "Tutorials": {
                        //tutorialGallery
                        break;
                    }
                    case "New Game": {
                        //save file nou
                        //inceputu jocului

                        break;
                    }
                    case "Continue": {
                        WorldData.PAUZA = false;
                        Player.toggledPause = false;
                        if (obj instanceof Menu) {
                            getWorld().removeObject((Menu) obj);
                        }
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

    public String getImg() {
        return img;
    }

}
