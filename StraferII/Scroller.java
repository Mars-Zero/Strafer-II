
import greenfoot.*;

public class Scroller {

    private World world; // view window world
    private GreenfootImage scrollImage; // scrolling image

    private boolean limited; // flag to indicate whether scrolling is limited or not
    public static int scrolledX, scrolledY; // current scrolled distances
    private int scrollMaxWide, scrollMaxHigh; // if limited, dimensions of scrolling area else of image to wrap

    /**
     * This constructor is for a limited scrolling world; If 'image' is smaller
     * than the given total scrolling area, it will be tiled If 'image' is null,
     * the background will not change
     *
     * @param viewWorld the world that scrolling will be performed on
     * @param image the background image that will be tiled, if needed, to fill
     * the scrolling area
     * @param wide the width of the visible area encompassed through scrolling;
     * the given value must be at least equal to the width of 'viewWorld' and is
     * given in world cells (not in pixels)
     * @param high the height of the visible area encompassed through scrolling;
     * the given value must be at least equal to the height of 'viewWorld' and
     * is given in world cells (not in pixels)
     */
    public Scroller(World viewWorld, GreenfootImage image, int wide, int high) {

        scrolledX = 0;
        scrolledY = 0;

        this.scrollMaxWide = wide;
        this.scrollMaxHigh = high;
        limited = true;
        world = viewWorld;
        if (image != null) {
            // create an image as large as scrolling area; tiled, if needeed
            scrollImage = new GreenfootImage(wide * world.getCellSize(), high * world.getCellSize());
            for (int x = 0; x < wide * world.getCellSize(); x += image.getWidth()) {
                for (int y = 0; y < high * world.getCellSize(); y += image.getHeight()) {
                    scrollImage.drawImage(image, x, y);
                }
            }
            // set initial background image
            scroll(0, 0);
        }
    }

    /**
     * performs scrolling on 'world' by the given distances along the horizontal
     * and vertical; if 'limited' is false, requested distances are actual
     * scrolling distances; if 'limited' is true, the distances may be adjusted
     * due to the limits of scrolling
     *
     * @param dsx the requested distance to shift everything horizontally
     * @param dsy the requested distance to shift everything vertically
     */
    public void scroll(int dsx, int dsy) {
        // adjust scroll amounts and scroll background image
        if (limited) {
            // calculate limits of scrolling
            int maxX = scrollMaxWide - world.getWidth();
            int maxY = scrollMaxHigh - world.getHeight();
            // apply limits to distances to scroll
            if (scrolledX + dsx < 0) {
                dsx = -scrolledX;
            }
            if (scrolledX + dsx >= maxX) {
                dsx = maxX - scrolledX;
            }
            if (scrolledY + dsy < 0) {
                dsy = -scrolledY;
            }
            if (scrolledY + dsy >= maxY) {
                dsy = maxY - scrolledY;
            }
            // update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;
            // scroll background image
            if (scrollImage != null) {
                world.getBackground().drawImage(
                        scrollImage,
                        -scrolledX,
                        -scrolledY
                );
            }
        }

        // adjust position of all actors (that can move with 'setLocation')
        for (Object obj : world.getObjects(null)) {
            Actor actor = (Actor) obj;
            actor.setLocation(actor.getX() - dsx, actor.getY() - dsy);
        }

    }

    public int getScrolledX() {
        return scrolledX;
    }

    public int getScrolledY() {
        return scrolledY;
    }

    public int getScrollMaxWide() {
        return scrollMaxWide;
    }

    public void setScrollMaxWide(int scrollMaxWide) {
        this.scrollMaxWide = scrollMaxWide;
    }

    public int getScrollMaxHigh() {
        return scrollMaxHigh;
    }

    public void setScrollMaxHigh(int scrollMaxHigh) {
        this.scrollMaxHigh = scrollMaxHigh;
    }

    public GreenfootImage getScrollImage() {
        return scrollImage;
    }

    public void setScrollImage(GreenfootImage scrollImage) {
        this.scrollImage = scrollImage;
    }
}
