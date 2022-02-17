
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.InputMismatchException;

abstract class Loader {

    public static int[][] loadMatrix(File fin) {
        int[][] mat = new int[WorldData.maxLengthWorld][WorldData.maxWidthWorld];
        try {
            Scanner scan = new Scanner(fin);
            for (int i = 0; i < WorldData.maxLengthWorld; i++) {

                for (int j = 0; j < WorldData.maxWidthWorld; j++) {
                    int x = scan.nextInt();

                }

//&& scan.hasNextInt()
            }
            System.out.println();
            scan.close();

        } catch (InputMismatchException e) {
            System.out.print(e.getMessage()); //try to find out specific reason.
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return null;
        }
        return mat;
    }

}
