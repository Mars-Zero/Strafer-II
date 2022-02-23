import greenfoot.*;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TutorialFolder extends TutorialGallery{
    
    /**
     * The tutorials in this folder
     */
    private ArrayList<Tutorial> tutorials;
    
    public TutorialFolder(ArrayList<Tutorial> mappedTutorials){
        tutorials=mappedTutorials;
        displayTutorials();
    }
    
    private void displayTutorials(){
        //aici ar trebui sa dea display la pozele de tutorial
        //trebuie sa se afle distantate
        //te las pe tine sa alegi distanta de spatiere la fel ca in gallery
        Picture thumbnail;
        int distPx=200;//ar trebui sa fie pixeli
        int distPy=200;
        final int deltaPixX=200;
        final int deltaPixY=500;
        for(int i=0; i<tutorials.size(); i++){
            //pui imaginea in lume
            thumbnail=new Picture("UI/tutorial/thumbnail/thumbnail"+tutorials.get(i).getImgName()+".png");
            getWorld().addObject(thumbnail,distPx , distPy);
           // getWorld().addObject(new Buton("Open"), i, i);
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
