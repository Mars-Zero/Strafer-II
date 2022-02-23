
import greenfoot.*;
import java.util.Arrays;
import java.util.Collections;

public class Animation {

    private Object animated; // the World or Actor object that to be animated
    private GreenfootImage[] frames; // the image set currently being used for this animated object
    private int cycleActs; // number of acts to complete a cycle of images
    private boolean active; // on-off switch
    private int timer; // internal timer
    private int cycleCount; // to count animation cycles
    private int cyclesToRun; // number of times the animation is to cycle through its images
    private int scalar;

    /**
     * creates an animation object that uses the given images for the given
     * object;
     *
     * @param object the object to be animated
     * @param imgSet the images to be used in the animation; a 'null' value or
     * an empty array will invalidate the animation
     */
    public Animation(Object object, GreenfootImage[] imgSet) {
        scalar = 1;
        animated = object;
        frames = imgSet;
        if (frames != null && frames.length != 0) {
            setFrame(0);
        }
    }

    /**
     * internal method that updates the image of the animation
     */
    private void setFrame(int index) {
        if (animated instanceof Actor) {
            ((Actor) animated).setImage(frames[index]);
        } else if (animated instanceof World) {
            ((World) animated).setBackground(frames[index]);
        }
    }

    /**
     * if animating, runs the timer and updates the image when needed
     *
     * @return a flag indicating the current animation cycle has completed
     */
    int tmr = 0;

    public void run() {
        tmr++;
        if (tmr >= scalar) {
            tmr = 0;
            if (cycleActs == 0 || !active) {
                return;
            }

            int inFrame = timer * frames.length / cycleActs;
            timer = (timer + 1) % cycleActs;
            int outFrame = timer * frames.length / cycleActs;
            if (inFrame != outFrame) {
                setFrame(outFrame);
            }

        }
        if (timer == 0 && cyclesToRun > 0 && (++cycleCount == cyclesToRun*scalar )) {
            cycleCount = 0;
            active=false;
        }
    }

    /**
     * sets the number of times the animation will cycle through its images
     *
     * @param count the number of times to cycle through the images of the
     * animation
     */
    public void setCycleCount(int count) {
        cyclesToRun = count < 0 ? 0 : count;
    }

    /**
     * sets the images and speed regulator value (the time, in act cycles, it
     * takes for the set of images to be shown once)
     *
     * @param imgSet the images to be used in the animation
     * @param actsInAnimation number of act cycles to complete one animation
     * cycle in
     */
    public void setAnimation(GreenfootImage[] imgSet, int actsInAnimation) {
        setCycleActs(actsInAnimation);
        setFrames(imgSet);
    }

    /**
     * sets the images to be used in the animation
     *
     * @param imgSet the images to be used in the animation
     */
    public void setFrames(GreenfootImage[] imgSet) {
        frames = imgSet;
        timer = 0;
        cycleCount = 0;
        if (imgSet != null && imgSet.length != 0) {
            setFrame(0);
        } else {
            active = false;
        }
    }

    /**
     * returns the images currently set to be used in the animation
     *
     * @return the array of images currently set to the animation
     */
    public GreenfootImage[] getFrames() {
        return frames;
    }

    /**
     * sets the speed regulator value (the time, in act cycles, it takes for the
     * set of images to be shown once)
     *
     * @param acts the number of act cycles to complete one animation cycle in
     */
    public void setCycleActs(int acts) {
        cycleActs = (acts < 0 ? 0 : acts);
    }

    /**
     * returns the speed regulator value
     *
     * @return the number of act cycles that one animation cycle is currently
     * set to run in
     */
    public int getCycleActs() {
        return cycleActs;
    }

    /**
     * sets the active state of the animation; it is only set active if field
     * values are set properly
     *
     * @param state a true/false value indicating which active state to set the
     * animation in
     */
    public void setActiveState(boolean state) {
        if (!(animated instanceof Actor || animated instanceof World)) {
            return;
        }
        if (frames == null || frames.length == 0) {
            return;
        }
        active = state;
    }

    /**
     * returns the active state of the animation
     *
     * @return the current active state the animation is currrently in
     */
    public boolean isActive() {
        return active;
    }

    /**
     * reverses the order of images in the animation set (allows the animation
     * to be run backwards)
     */
    public void reverseImageOrder() {
        Collections.reverse(Arrays.asList(frames));
    }

    public int getScalar() {
        return scalar;
    }

    public void setScalar(int scalar) {
        this.scalar = scalar;
    }
}


/*
public class Clasa(){
    
    private Animation animation;
    private int nrframeuri;
  public Clasa()
    {
        java.util.List<GreenfootImage> imgs = new GifImage("gif.gif").getImages();
        GreenfootImage[] images = new GreenfootImage[imgs.size()];
        for (int i=0; i<imgs.size(); i++) images[i] = (GreenfootImage)imgs.get(i);
        animation = new Animation(this, images);
        animation.setCycleActs(nrframeuri);
        animation.run();
        animation.setActiveState(true);
    }

    public void act()
    {
        animation.run();
    }
}
 */
