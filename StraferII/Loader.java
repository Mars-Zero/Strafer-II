import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class Loader {
    public static int[][] load(File fin) {
        int[][] mat = new int[1000][1000];
        try {
            Scanner scan = new Scanner(fin);
            for (int i = 0; i < 1000; i++) {
                for (int j = 0; j < 1000&& scan.hasNextInt(); j++) {

                    mat[i][j]=scan.nextInt();
                    
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
        return mat;
    }

}
