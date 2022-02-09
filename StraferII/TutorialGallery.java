import greenfoot.*;  
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorialGallery extends Pause{
    
    
    /**
    * This method loads all the image tutorials and puts them in a hashmap
    */
    public HashMap<String,List<GreenfootImage>> tutorialsImages;
    ArrayList<String> types=new ArrayList<String>();
    
           
           
            public TutorialGallery(){
                types.add("Items");types.add("Combat");types.add("Mechanics");
                
            }
            
            
    public void loadImages() {
        File director = new File("UI/tutorial/tutorialSlides");
        File[] allFiles=director.listFiles();
        tutorialsImages=new HashMap<>();
        try {
            director.createNewFile();
            for(int i=0; i<allFiles.length; i++)
            {
                GreenfootImage image =new GreenfootImage(allFiles[i].getPath());
                String type=SaveSystem.getTipStringFiles(allFiles[i].getName());
                if(tutorialsImages.containsKey(type)) {
                    tutorialsImages.get(type).add(image);
                }
                else{
                   List<GreenfootImage> l=new ArrayList<>();
                   l.add(image);
                   tutorialsImages.put(type, l);    
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * This method retrieves which tutorials are unlocked by the player
     */
    public HashMap<String,List<GreenfootImage>> retrieveTutorials()
    {
        HashMap<String,List<GreenfootImage>> tutor=new HashMap<>();
        for(int i=0; i<types.size(); i++){
            tutor.put(types.get(i),tutorialsImages.get(types.get(i)));
        }
        return tutor;
    }
    public void act(){
        
    }    
}
