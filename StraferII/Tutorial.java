
import greenfoot.*;

/**
 * O clasa pentru meniurile de tutorial, folosita si pentru cutsceneuri
 */
public class Tutorial extends Menu {

    private int nrSlide;
    private int nrSlideMax;

    /**
     * Numele folderului in care sunt slideurile unui tutorial aferent adica
     * numele tutorialului
     */
    private String img;
    private String tip;
    private Picture picture = new Picture("UI/tutorial/tutorial.png");

    private boolean addedButon = false;
    private boolean addedButonBack = false;
    private boolean inPause;
    private boolean addedPicture = false;

    public Tutorial(String tipref, String imgref, int slideref, boolean inPauseref) {
        WorldData.PAUZA = true;
        nrSlideMax = slideref;
        tip = tipref;
        nrSlide = 0;
        img = imgref;
        addedButon = false;
        addedPicture = false;
        this.inPause = inPauseref;

        setImage("UI/tutorial/tutorial.png");
        
        
    }

    public void displayPicture() {

        if (!addedPicture) {
            getWorld().addObject(picture, WorldData.menuX, WorldData.menuY);
            updateImage();

            addedPicture = true;
        }

    }

    public void addButon() {
        if (!addedButon) {
            getWorld().addObject(new Buton("Next", this), 945, 485);
            addedButon = true;
        }
    }

    public void act() {
        addButon();
        displayPicture();
    }

    public void updateImage() {
        if (tip == "Cutscene") {
            picture.setImageName("cutscene/" + img + ".png");
        } else {
            picture.setImageName("UI/tutorial/tutorialSlides/" + tip + "/" + img + "/" + tip + "#" + "tutorial" + img + nrSlide + ".png");
        }
    }

    public String toString() {
        String str = tip + " " + img + " " + nrSlideMax;
        return str;
    }

    public int getNrSlideMax() {
        return nrSlideMax;
    }

    public int getNrSlide() {
        return nrSlide;
    }

    public void setNrSlide(int nrSlide) {
        this.nrSlide = nrSlide;
    }

    public String getImgName() {
        return img;
    }

    public String getTip() {
        return tip;
    }

    public boolean isLastSlide() {
        return nrSlide == nrSlideMax - 1 ? true : false;
    }

    public boolean isFirstSlide() {
        return nrSlide == 0 ? true : false;
    }

    public boolean isAddedPicture() {
        return addedPicture;
    }

    public void setAddedPicture(boolean addedPicture) {
        this.addedPicture = addedPicture;
    }

    public boolean isAddedButonBack() {
        return addedButonBack;
    }

    public boolean isInPause() {
        return inPause;
    }

    public Picture getPicture() {
        return picture;
    }
}
