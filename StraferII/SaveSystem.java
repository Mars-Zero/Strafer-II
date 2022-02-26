
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

        //
        Player player = playerref;

        try {

            file.createNewFile();
            Scanner fin = new Scanner(file, "UTF-8");
            while (fin.hasNext()) {
                String str = fin.nextLine();
                String tip = getTipString(str);
                if (tip.equalsIgnoreCase("Item:")) {
                    String itm = new String(getContentString(str));
                    boolean gasit = false;
                    for (String i : WorldData.items) {
                        if (i.equals(itm)) {
                            gasit = true;
                        }
                    }
                    if (!gasit) {
                        WorldData.items.add(itm);
                    }
                    switch (itm) {
                        case "sword": {
                            WorldData.hasSword = true;
                            break;
                        }
                        case "laser": {
                            WorldData.hasLaser = true;
                            break;
                        }
                        case "portalgun": {
                            WorldData.hasPortalGun = true;
                            break;
                        }
                        case "icelock": {
                            WorldData.hasIceLock = true;
                            break;
                        }
                        case "blackhole": {
                            WorldData.hasBlackHole = true;
                            break;
                        }
                        case "lantern": {
                            WorldData.hasLantern = true;
                            break;
                        }
                        default: {
                        }
                    }
                } else if (tip.equalsIgnoreCase("Tutorial:")) {

                    String[] arr = getContentString(str).split(" ");
                    if (arr.length > 0) {
                        Tutorial tut = new Tutorial(arr[0], arr[1], Integer.parseInt(arr[2]), false);
                        if (TutorialGallery.tutorialsInFolder.containsKey(arr[0])) {
                            TutorialGallery.tutorialsInFolder.get(arr[0]).add(tut);
                        } else {
                            ArrayList<Tutorial> startTutorials = new ArrayList<>();
                            startTutorials.add(tut);
                            TutorialGallery.tutorialsInFolder.put(arr[0], startTutorials);
                        }

                    }

                } else if (tip.equalsIgnoreCase("WorldSection:")) {
                    int ws = Integer.parseInt(getContentString(str));
                    WorldListener worldListener = player.getWorld().getObjects(WorldListener.class).get(0);
                    worldListener.setWorldSection(ws);
                    WorldData.worldSection = ws;

                    worldListener.setLoaded(false);
                    worldListener.load();

                } else if (tip.equalsIgnoreCase("PlayerX:")) {
                    player.setWorldX(Integer.parseInt(getContentString(str)));
                    player.setLocation(player.getWorldX() - Scroller.scrolledX, player.getWorldY() - Scroller.scrolledY);

                } else if (tip.equalsIgnoreCase("PlayerY:")) {
                    player.setWorldY(Integer.parseInt(getContentString(str)));
                    player.setLocation(player.getWorldX() - Scroller.scrolledX, player.getWorldY() - Scroller.scrolledY);

                } else if (tip.equalsIgnoreCase("Health:")) {
                    int h = Integer.parseInt(getContentString(str));
                    player.setHp(h);
                    player.getHealthBar().setValue(h);
                } else if (tip.equalsIgnoreCase("WS11:")) {
                    if (getContentString(str).equalsIgnoreCase("true")) {
                        WorldData.visitedWorldSections[1][1] = true;
                    }

                } else if (tip.equalsIgnoreCase("WS12:")) {
                    if (getContentString(str).equalsIgnoreCase("true")) {
                        WorldData.visitedWorldSections[1][2] = true;
                    }

                } else if (tip.equalsIgnoreCase("WS13:")) {
                    if (getContentString(str).equalsIgnoreCase("true")) {
                        WorldData.visitedWorldSections[1][3] = true;
                    }

                } else if (tip.equalsIgnoreCase("WS21:")) {
                    if (getContentString(str).equalsIgnoreCase("true")) {
                        WorldData.visitedWorldSections[2][1] = true;
                    }

                } else if (tip.equalsIgnoreCase("WS22:")) {
                    if (getContentString(str).equalsIgnoreCase("true")) {
                        WorldData.visitedWorldSections[2][2] = true;
                    }

                } else if (tip.equalsIgnoreCase("WS23:")) {
                    if (getContentString(str).equalsIgnoreCase("true")) {
                        WorldData.visitedWorldSections[2][3] = true;
                    }

                } else if (tip.equalsIgnoreCase("DialogIndex:")) {
                    int nr = Integer.parseInt(getContentString(str));
                    WorldData.dialogIndex = nr;

                } else if (tip.equalsIgnoreCase("NrEvent:")) {
                    int nr = Integer.parseInt(getContentString(str));
                    WorldData.nrEvent = nr;
                }

            }

            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * The save method; It will be given all the items and other proccesses that
     * must be saved
     */
    public static void save(int saveNumber, Player player) {
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

            /*
            fout.println("PlayerX:" + new Integer(juc.getWorldX()).toString());
    
             */
            fout.println("PlayerX:" + (player.getX() + Scroller.scrolledX));
            fout.println("PlayerY:" + (player.getY() + Scroller.scrolledY));
            fout.println("Health:" + (player.getHp()));

            fout.println("WorldSection:" + WorldData.worldSection);
            fout.println("WS11:" + WorldData.visitedWorldSections[1][1]);
            fout.println("WS12:" + WorldData.visitedWorldSections[1][2]);
            fout.println("WS13:" + WorldData.visitedWorldSections[1][3]);
            fout.println("WS21:" + WorldData.visitedWorldSections[2][1]);
            fout.println("WS22:" + WorldData.visitedWorldSections[2][2]);
            fout.println("WS23:" + WorldData.visitedWorldSections[2][3]);

            for (String it : WorldData.items) {
                fout.println("Item:" + it);
            }

            fout.println("DialogIndex:" + WorldData.dialogIndex);
           
            fout.println("NrEvent:" + WorldData.nrEvent);

            fout.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @return The list of all saveFiles
     */
    public static List<File> getSaveFiles() {
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

    public static int getNumberOfSaveFiles() {
        File f = new File(SaveSystem.directoryName);
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("save") && name.endsWith("txt");
            }
        });

        int nr = 0;
        for (File fis : matchingFiles) {
            nr++;
        }

        return nr;
    }

}
