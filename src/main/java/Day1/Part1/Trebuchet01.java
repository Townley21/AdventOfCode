package Day1.Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trebuchet01 {
    public static void main(String[] a) throws FileNotFoundException {
        File f = new File(a[0]);
        Scanner s = new Scanner(f);
        int total = 0;
        while (s.hasNext())
        {
            boolean foundFirst = false;
            int j = 0;
            int k = 0;
            String l = s.nextLine();
            for (int i = 0; i < l.length(); i++) {

                if (Character.isDigit(l.charAt(i)) && !foundFirst) {
                    j = Character.getNumericValue(l.charAt(i));
                    foundFirst = true;
                }

                if (Character.isDigit(l.charAt(i))) {
                    k = Character.getNumericValue(l.charAt(i));
                }
            }
            total += j * 10 + k;
        }
        System.out.println(total);
    }
}