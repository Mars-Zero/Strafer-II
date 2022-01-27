import greenfoot.*;  
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorialGallery extends Pause{
    
    
    /**
    * This method loads all the image tutorials and puts them in a hashmap
    */
    public static  HashMap<String,List<GreenfootImage>> imageTutorials;
   
    public static void loadImages() {
        File director = new File("tutorials");
        File[] allFiles=director.listFiles();
        imageTutorials=new HashMap<>();
        try {
            director.createNewFile();
            for(int i=0; i<allFiles.length; i++)
            {
                GreenfootImage image =new GreenfootImage(allFiles[i].getPath());
                String type=SaveSystem.getTipString(allFiles[i].getName());
                if(imageTutorials.containsKey(type)) {
                    imageTutorials.get(type).add(image);
                }
                else{
                   List<GreenfootImage> l=new ArrayList<>();
                   l.add(image);
                   imageTutorials.put(type, l);    
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void act(){
        
    }    
}
