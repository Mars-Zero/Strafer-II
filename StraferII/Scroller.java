import greenfoot.*;

public class Scroller
{
    private World world; // view window world
    private GreenfootImage scrollImage; // scrolling image
    private boolean limited; // flag to indicate whether scrolling is limited or not
    private int scrolledX, scrolledY; // current scrolled distances
    private int wide, high; // if limited, dimensions of scrolling area else of image to wrap
   
    /**
     * This constructor is for an unlimited scrolling world;
     * If 'image' is null, the background will not change; else the given image is wrapped
     * 
     * @param viewWorld the world that scrolling will be performed on
     * @param image the background image that will be tiled, if needed, and wrap with scrolling
     */
    public Scroller(World viewWorld, GreenfootImage image)
    {
        world = viewWorld;
        scrollImage = image;
        if (image != null)
        {
            wide = image.getWidth();
            high = image.getHeight();
        }
        scroll(0, 0); // sets initial background image
    }
   
    /**
     * This constructor is for a limited scrolling world;
     * If 'image' is smaller than the given total scrolling area, it will be tiled
     * If 'image' is null, the background will not change
     * 
     * @param viewWorld the world that scrolling will be performed on
     * @param image the background image that will be tiled, if needed, to fill the scrolling area
     * @param wide the width of the visible area encompassed through scrolling;
     * the given value must be at least equal to the width of 'viewWorld' and
     * is given in world cells (not in pixels)
     * @param high the height of the visible area encompassed through scrolling;
     * the given value must be at least equal to the height of 'viewWorld' and
     * is given in world cells (not in pixels)
     */
    public Scroller(World viewWorld, GreenfootImage image, int wide, int high)
    {
        this.wide = wide;
        this.high = high;
        limited = true;
        world = viewWorld;
        if (image != null)
        {
            // create an image as large as scrolling area; tiled, if needeed
            scrollImage = new GreenfootImage(wide*world.getCellSize(), high*world.getCellSize());
            for (int x=0; x<wide*world.getCellSize(); x+= image.getWidth())
                for (int y=0; y<high*world.getCellSize(); y+=image.getHeight())
                    scrollImage.drawImage(image, x, y);
            // set initial background image
            scroll(0, 0);
        }
    }
   
    /**
     * performs scrolling on 'world' by the given distances along the horizontal and vertical;
     * if 'limited' is false, requested distances are actual scrolling distances;
     * if 'limited' is true, the distances may be adjusted due to the limits of scrolling
     *
     * @param dsx the requested distance to shift everything horizontally
     * @param dsy the requested distance to shift everything vertically
     */
    public void scroll(int dsx, int dsy)
    {
        // adjust scroll amounts and scroll background image
        if (limited)
        {
            // calculate limits of scrolling
            int maxX = wide-world.getWidth();
            int maxY = high-world.getHeight();
            // apply limits to distances to scroll
            if (scrolledX+dsx < 0) dsx = -scrolledX;
            if (scrolledX+dsx >= maxX) dsx = maxX-scrolledX;
            if (scrolledY+dsy < 0) dsy = -scrolledY;
            if (scrolledY+dsy >= maxY) dsy = maxY-scrolledY;
            // update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;
            // scroll background image
            if (scrollImage != null)
            {
                world.getBackground().drawImage
                (   
                    scrollImage,
                    -scrolledX*world.getCellSize(),
                    -scrolledY*world.getCellSize()
                );
            }
        }
        else // unlimited image wrapping
        {
            // update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;
            // scroll background image
            if (scrollImage != null)
            {
                // create working variables of scroll positions
                int imageX = scrolledX*world.getCellSize();
                int imageY = scrolledY*world.getCellSize();
                // get near-zero starting positions for drawing 'scrollImage'
                imageX = imageX%wide;
                imageY = imageY%high;
                // adjust negative values as needed
                if (imageX < 0) imageX += wide;
                if (imageY < 0) imageY += high;
                // create image of appropriate size and tile fill 'scrollImage' onto it
                GreenfootImage hold = new GreenfootImage(scrollImage);
                hold.drawImage(scrollImage, -imageX, -imageY);
                if (imageX > 0) hold.drawImage(scrollImage, wide-imageX, -imageY);
                if (imageY > 0) hold.drawImage(scrollImage, -imageX, high-imageY);
                if (imageX > 0 && imageY > 0)
                    hold.drawImage(scrollImage, wide-imageX, high-imageY);
                // set image to background of 'world'
                world.setBackground(hold);
            }
        }
        // adjust position of all actors (that can move with 'setLocation')
        for (Object obj : world.getObjects(null))
        {
            Actor actor = (Actor) obj;
            actor.setLocation(actor.getX()-dsx, actor.getY()-dsy);
        }
    }
   
    /**
     * getter method for the current total scrolled distance horizontally
     *
     * @return the current total offset of horizontal scrolling
     */
    public int getScrolledX()
    {
        return scrolledX;
    }
   
    /**
     * getter method for the current total scrolled distance vertically
     *
     * @return the current total offset of vertical scrolling
     */
    public int getScrolledY()
    {
        return scrolledY;
    }
}