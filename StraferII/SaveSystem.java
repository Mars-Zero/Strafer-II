
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class SaveSystem {

    PlayWorld playWorld;
    WorldListener worldListener;
    
    
    
    /**
     * The name of the directory where all files are saved
     */
    private static String directoryName = "saves";

    /**
     * This method processes the string and returns it's type
     *
     * @param String that needs to be processed
     * @return the type of the item
     * @throws Exception
     */
    public static String getTipString(String str) throws Exception {
        Pattern patt = Pattern.compile("^(.+?):");
        Matcher matcher = patt.matcher(str);
        if (matcher.find()) {
            return matcher.group(); // you can get it from desired index as well
        } else {
            return null;
        }
    }

    /**
     * This method processes the string and returns it's type Only for files
     *
     * @param String that needs to be processed
     * @return the type of the item
     * @throws Exception
     */
    public static String getTipStringFiles(String str) throws Exception {
        Pattern patt = Pattern.compile("^(.+?)#");
        Matcher matcher = patt.matcher(str);
        if (matcher.find()) {
            return matcher.group(); // you can get it from desired index as well
        } else {
            return null;
        }
    }

    /**
     * This method processes the string and returns it's content
     *
     * @param String that needs to be processed
     * @return the content of the item
     * @throws Exception
     */
    private static String getContentString(String str) throws Exception {
        Pattern patt = Pattern.compile("[^:]*$");
        Matcher matcher = patt.matcher(str);
        if (matcher.find()) {
            return matcher.group(); // you can get it from desired index as well
        } else {
            return null;
        }
    }

    /**
     * The load method It will be given the player and it will load the list of
     * all the items and other proccesses
     */
    public static void load(int saveNumber, Player playerref) {
        File director = new File(SaveSystem.directoryName);
        File file = new File(director.getAbsoluteFile(), "save" + saveNumber + ".txt");
        List<Item> iteme = new ArrayList<>();
        //List<Tutorial> tutor = new ArrayList<>();
        HashMap<String,List<String>> tutorialArguments=new HashMap<String,List<String>>();
        
        Player player = playerref;
        int ScrolledX=0, ScrolledY=0;
        int WorldSection=0;
        try {
            file.createNewFile();
            Scanner fin = new Scanner(file, "UTF-8");
            while (fin.hasNext()) {
                String str = fin.nextLine();
                String tip = getTipString(str);
                if (tip.equalsIgnoreCase("Item:")) {
                    //Item itm = new Item(getContentString(str));
                    //iteme.add(itm);
                } else if (tip.equalsIgnoreCase("Tutorial:")) {

                    String[] arr = getContentString(str).split(" ");
                    if(arr.length>0){
                        Tutorial tut = new Tutorial(arr[0],arr[1],Integer.parseInt(arr[2]),false);
                        tutorialArguments.get(arr[0]);
                    }
      
                } else if (tip.equalsIgnoreCase("PlayerX:")) {
                    player.setWorldX(Integer.parseInt(getContentString(str)));
                } else if (tip.equalsIgnoreCase("PlayerY:")) {
                    player.setWorldY(Integer.parseInt(getContentString(str)));
                } else if (tip.equalsIgnoreCase("ScrolledX:")) {
                    ScrolledX = Integer.parseInt(getContentString(str));
                } else if (tip.equalsIgnoreCase("ScrolledY:")) {
                    ScrolledY = Integer.parseInt(getContentString(str));
                } else if (tip.equalsIgnoreCase("WorldSection:")) {
                    WorldSection = Integer.parseInt(getContentString(str));
                }
                
            }
         
            Scroller.scrolledX=ScrolledX;
            Scroller.scrolledY=ScrolledY;
            WorldListener.worldSection=WorldSection;
         
            player.setLocation(player.getWorldX(),player.getWorldY());
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The save method; It will be given all the items and other proccesses that
     * must be saved
     */
    public static void save(int saveNumber, List<Item> iteme, List<Tutorial> tutor,
             Player juc, int ScrolledY, int ScrolledX,
            int WorldSection) {
        File director = new File(SaveSystem.directoryName);
        if (!director.isDirectory()) {
            director.mkdir();
        }
        File file = new File(director.getAbsolutePath(), "save" + saveNumber + ".txt");
        try {
            if (file.exists()) {
                // sterg continutul save-ului trecut
                PrintWriter writer = new PrintWriter(file);
                writer.print("");
                writer.close();
            } else {
                file.createNewFile();
            }

            PrintStream fout = new PrintStream(new FileOutputStream(file, true), true, Charset.forName("UTF-8"));
            fout.flush();

            for (Item it : iteme) {
                fout.println("Item:" + it.toString());
            }
            for (Tutorial tut : tutor) {
                fout.println("Tutorial:" + tut.toString());
            }
            fout.println("PlayerX:" + new Integer(juc.getWorldX()).toString());
            fout.println("PlayerY:" + new Integer(juc.getWorldY()).toString());
            fout.println("ScrollX:" + new Integer(ScrolledX).toString());
            fout.println("ScrollY:" + new Integer(ScrolledY).toString());
            fout.println("WorldSection:" + new Integer(WorldSection).toString());
            fout.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @return The list of all saveFiles
     */
    public static List<File> getNumberOfSaveFiles() {
        List<File> fisiere = new ArrayList<>();
        File f = new File(SaveSystem.directoryName);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("save") && name.endsWith("txt");
            }
        });

        for (File fis : matchingFiles) {
            fisiere.add(fis);
        }

        return fisiere;
    }
}
