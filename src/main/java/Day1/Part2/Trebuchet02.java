package Day1.Part2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Trebuchet02 {
    public static void Solve() throws FileNotFoundException {
        File f = new File("Day1Input.txt");
        Scanner s = new Scanner(f);
        int total = 0;
        String[] nums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        while (s.hasNext())
        {
            boolean foundFirst = false;
            int j = 0;
            int k = 0;
            String l = s.nextLine();
            for (int i = 0; i < l.length(); i++) {

                for (int q = 0; q < nums.length; q++)
                {
                    int b = i + nums[q].length();
                    if (b < l.length() && l.substring(i, b).equals(nums[q]))
                    {
                        if (!foundFirst)
                        {
                            j = q + 1;
                            foundFirst = true;
                        }
                        else
                        {
                            k = q + 1;
                        }
                    }

                }
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