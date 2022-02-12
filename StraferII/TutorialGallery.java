import greenfoot.*;  
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorialGallery extends Pause{
    
    
    /**
     * A map that contains all the tutorials unlocked by the player
     * Key- the category of the tutorial
     * Value- a list of all the tutorials mapped at the key
     */
    public static HashMap<String,List<Tutorial>> tutorialsInFolder=new HashMap<>();
 
    
           
           
    public TutorialGallery(){
       // types.add("Items");types.add("Combat");types.add("Mechanics");
                
    }
            
            
    
    
    /**
     * This method retrieves which tutorials are unlocked by the player at the given key
     */
    public static List<Tutorial> retrieveTutorials(String key)
    {
        return tutorialsInFolder.get(key);
    }
    public void act(){
        
    }    
}
