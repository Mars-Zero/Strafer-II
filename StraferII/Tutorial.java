
import greenfoot.*;

public class Tutorial extends Menu {

    protected int nrSlide;
    protected int nrSlideMax;

    protected String img;
    protected String tip;

    protected Picture picture = new Picture("UI/tutorial/tutorial.png");

    protected boolean addedButon = false;
    protected boolean addedButonBack = false;
    protected boolean inPause;

    protected boolean addedPicture = false;

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
            getWorld().addObject(picture, 522, 500);
            updateImage();

            addedPicture = true;
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
        displayPicture();
    }

    public void updateImage() {
        picture.setImageName("UI/tutorial/tutorialSlides/" + tip + "/" + img + "/" + tip + "#" + "tutorial" + img + nrSlide + ".png");

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
