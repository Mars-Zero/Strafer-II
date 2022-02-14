import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class Loader {
    public static int[][] load(File fin) {
        int[][] mat = new int[WorldData.maxLengthWorld][WorldData.maxWidthWorld];
        try {
            Scanner scan = new Scanner(fin);
            for (int i = 0; i < WorldData.maxLengthWorld; i++) {
                for (int j = 0; j < WorldData.maxWidthWorld && scan.hasNextInt(); j++) {

                    mat[i][j]=scan.nextInt();
                    
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
        return mat;
    }

}
