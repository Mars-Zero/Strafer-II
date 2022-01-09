import greenfoot.*;

/**
 * CLASS: Fps
 * AUTHOR: danpost (greenfoot.org username)
 * VERSION: January 15, 2014
 * 
 * DESCRIPTION: An actor class that show how many frames the scenario is running at the current speed.
 * 
 * INSTRUCTIONS: As with any actor, create and add into world after including the class in your project.
 * 
 * CONTROLS:
 * 
 * * Left click on actor reduces speed of scenario
 * * Right click on actor increases speed of scenario
 * * Hovering mouse over actor will have it display current speed setting
 * 
 * NOTE: Using the speed slider in the application will result in a false speed display until
 * the actor is clicked on.
 */
public class Fps extends UI
{
    int frames, speed = 50; // frame counter and speed setting
    boolean showSpeed, set, go; // controls

    /**
     * Sets the initial scenario speed and creates the initial image
     */
    public Fps()
    {
        Greenfoot.setSpeed(speed); // set the scenario speed to setting of field
        updateImage(); // set initial image of fps text
    }

    /**
     * Checks for mouse clicks and tracks frames per second of scenario
     */
    public void act()
    {
        this.setLocation(150, 100);
        // detect mouse moving onto actor
        if (!showSpeed && Greenfoot.mouseMoved(this))
        {
            showSpeed = true;
            updateImage(); // to show speed
        }
        // detect mouse moving off of actor
        if (showSpeed && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) showSpeed = false;
        
        // check for mouse clicks to change the scenario speed
        int d = 0; // field to hold any change in speed
        if (Greenfoot.mouseClicked(this))
        { // actor clicked on; determine direction of change in speed by which mouse button was clicked
            MouseInfo mouse = Greenfoot.getMouseInfo();
            d = mouse.getButton()-2; // mouse button is one or three; 'd' becomes one or negative one
        }
        if (d*d == 1 && speed+d > 0 && speed+d <= 100)
        { // change requested and speed will stay within range
            // reset frame counter and control fields
            frames = 0;
            set = false;
            go = false;
            // change the speed
            speed += d;
            Greenfoot.setSpeed(speed);
            // update the display texts
            updateImage();
        }
        
        // process fps timer controls
        int millis = (int)(System.currentTimeMillis()%1000); // get fractional part current second
        // code to begin the timing
        if (!set && !go)
        { // time has not begun and we are not set to begin
            if (millis > 100) set = true; // we are set to begin if past first tenth of a second
            return;
        }
        if (set && !go)
        { // time has not begun, but we are set to begin
            if (millis < 100) { go = true; set = false; } // zero tick, unset and begin time
            return;
        }
        // code to run the timing
        frames++; // count this frame
        if (!set && go)
        { // must wait for a tenth of a second before looking for first the next tick
            if (millis > 100) set = true; // reset after a tenth of a second past last tick
            return;
        }
        if (set && go)
        { // looking for next tick (next second to start)
            if (millis < 100)
            { // next tick detected
                set = false; // not looking for tick
               updateImage(); // update text display of fps actor
                frames = 0; // reset the frames counter
            }
        }
    }

    /**
     * Updates the image; shows scenario speed if mouse hovers on this actor else shows fps rate
     */
    private void updateImage()
    {
        if (showSpeed) setImage(new GreenfootImage("Speed: "+speed, 30, Color.BLUE, new Color(0, 0, 0, 0)));
        else setImage(new GreenfootImage("FPS: "+frames+"\n sx"+Scroller.scrolledX+"  sy"+Scroller.scrolledY, 30, Color.BLACK, new Color(0, 0, 0, 0)));
    }
}