import greenfoot.*;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorialFolder extends TutorialGallery{
    
    /**
     * The tutorials in this folder
     */
    private List<Tutorial> tutorials;
    
    public TutorialFolder(List<Tutorial> mappedTutorials){
        tutorials=mappedTutorials;
        displayTutorials();
    }
    
    private void displayTutorials(){
        //aici ar trebui sa dea display la pozele de tutorial
        //trebuie sa se afle distantate
        //te las pe tine sa alegi distanta de spatiere la fel ca in gallery
        long distPx=200;//ar trebui sa fie pixeli
        long distPy=200;
        final long deltaPixX=200;
        final long deltaPixY=500;
        for(int i=0; i<tutorials.size(); i++){
            //pui imaginea in lume
            //imagine.setLocation(distPy,distPx);
            if(i==5){
                //trec pe randul urmator
                distPy+=deltaPixY;
                distPx=200;
            }
            distPx+=deltaPixX;
            
        }
    }
    public void act() {
        
        
    }    
}
