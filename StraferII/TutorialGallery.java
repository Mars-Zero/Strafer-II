import greenfoot.*;  
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorialGallery extends Pause{
    
    
    /**
    * This method loads all the names tutorials and puts them in a hashmap
    */
    public HashMap<String,List<String>> tutorialsInFolder;
 
    
           
           
            public TutorialGallery(){
               // types.add("Items");types.add("Combat");types.add("Mechanics");
                
            }
            
            
    public void loadImages() {
        File director = new File("UI/tutorial/tutorialSlides");
        File[] allFiles=director.listFiles();
        tutorialsInFolder=new HashMap<>();
        try {
            director.createNewFile();
            for(int i=0; i<allFiles.length; i++)
            {
                GreenfootImage image =new GreenfootImage(allFiles[i].getPath());
                String type=SaveSystem.getTipStringFiles(allFiles[i].getName());
                if(tutorialsInFolder.containsKey(type)) {
                    //tutorialsInFolder.get(type).add(image);
                }
                else{
                   List<GreenfootImage> l=new ArrayList<>();
                   l.add(image);
                   //tutorialsInFolder.put(type, l);    
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * This method retrieves which tutorials are unlocked by the player
     */
    public List<String> retrieveTutorials(String key)
    {
        
        return tutorialsInFolder.get(key);
    }
    public void act(){
        
    }    
}
