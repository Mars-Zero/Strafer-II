
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class WorldData {

    public static boolean PAUZA;
    public static final int WIDTH = 1024, HIGHT = 576;
    public static final int FPS = 60;

    public static int menuX = 512; //  pt adaugat meniuri in mijlocu ecranului
    public static int menuY = 288;
//

    //player state
    public static boolean addedDialogs = false;
    public static boolean isFighting=false;
    public static boolean isWalking=false;
//

//
    public static int saveFileNumber = -1;

//
    public static int nrEvent=1;
    public static String objective="";
    public static int objectiveX=1000,objectiveY=1000;
    public static int objectiveWS=22;
//
    
    //iteme obtinute
    static List<String> items = new ArrayList<>();
    public static boolean hasSword = false;
    public static boolean hasLaser = false;
    public static boolean hasBlackHole = false;
    public static boolean hasLantern = false;
    public static boolean hasPortalGun = false;
    public static boolean hasIceLock = false;

//
    //inamici intalniti
    public static boolean metDroid=false;
    public static boolean metGoblin=false;
    public static boolean metDolpatian=false;
    public static boolean metSchrodingersCat=false;
     public static boolean metStroke=false;
    
//
     
     //worldsection
    public static int worldSection = 11;
    public static int worldSectionShortNumber = 6;
    public static final int maxWidthWorld = 128;
    public static final int maxLengthWorld = 128;

    public static boolean[][] visitedWorldSections = {{false, false, false, false}, {false, false, false, false}, {false, false, false, false}};

    public static int[][][] worldSectionMatrix = new int[worldSectionShortNumber + 1][maxLengthWorld + 1][maxWidthWorld + 1];

    //nr maxim de sectiuni de pe o linie
        public static final int numberOfCollumns = 3;

    // nr scurt pt un nr de sectiune (ex ws11->1)
    public static int getWorldSectionShort(int world) {
        int col = world % 10;
        int lin = world / 10;
        int sol = (lin - 1) * numberOfCollumns + col;
        if (sol < 0) {
            sol = 1;
        }
        return sol;
    }

    
    
    //incarca matricele de pathfind pt fiecare mapa 
    public static void loadWorldMatrices() {

        String director = new String("maps/");
        for (int i = 1; i <= 1; i++) {

            worldSectionMatrix[i] = Loader.loadMatrix(new File(director + i + ".txt"));
        }
    }

    //
    
    //
    public static List<String> tutorials = new ArrayList<>();
    
    public static int dialogIndex=0;
    public static int[] dialogSuccesion =new int[]{0,3,6,9,12,15,17,18,20,22,23,25} ;//index e nr dialog care e in numele txt-ului cu dialogul
                                                            //valoarea e nr eventului la care e dialogul asta
    public static boolean talked=false; //verifica daca un dialog a fost accesat odata
    
    

    public static void reset() {//pt save file nou

        worldSection = 11;
        worldSectionShortNumber = 6;
        for(int i=0;i<3;i++){
            for(int j=0;j<=3;j++){
                visitedWorldSections[i][j]=false;
            }
        }
        hasSword = false;
        hasLaser = false;
        hasBlackHole = false;
        hasLantern = false;
        hasPortalGun = false;
        hasIceLock = false;
        items.clear();
        tutorials.clear();
        dialogIndex=0;
        talked=false;
        nrEvent=1;
        
        
    }

}
