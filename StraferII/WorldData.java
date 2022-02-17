import java.io.File;
import java.io.FileNotFoundException;

public class WorldData {

    public static boolean PAUZA;
    public static final int WIDTH = 1024, HIGHT = 576;
    public static final int FPS = 60;
    
    public static boolean addedDialogs = false;

    public static int menuX = 512; //  pt adaugat meniuri in mijlocu ecranului
    public static int menuY = 288;

    
    
    public static boolean[][] visitedWorldSections={{false,false,false,false},{false,false,false,false},{false,false,false,false}};
    
    public static final int worldSectionNumber=6;
    public static final int maxWidthWorld=128;
    public static final int maxLengthWorld=128;
    
    public static int[][][] worldSectionMatrix=new int[worldSectionNumber+1][maxLengthWorld+1][maxWidthWorld+1];
    
    /**
     * Numarul maxim de sectiuni de pe o linie
     */
    public static final int numberOfCollumns=3;
    /**
     * This method returns the specific code for the world you need
     */
    public static int getWorldSectionShort(int world)
    {
        int col=world%10;
        int lin=world/10;
        int sol=(lin-1)*numberOfCollumns+col;
        if(sol<0){
            sol=1;
        }
        return sol;
    }
    
    /**
     * This method loads the matrixes of the worlds
     */
    public static void loadWorldMatrices(){
        
       
        String director=new String("maps/");
        for(int i=1; i<=1; i++){
             
            worldSectionMatrix[i]=Loader.loadMatrix(new File(director+i+".txt"));
        }
    }
}
