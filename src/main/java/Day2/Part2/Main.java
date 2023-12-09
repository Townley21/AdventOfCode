package Day2.Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// then press Enter. You can now see whitespace characters in your code.
public class Main
{
    public static void main(String[] args) throws IOException
    {
        String input = "src/main/resources/Day2Part2Input";

        BufferedReader r = new BufferedReader(new FileReader(input));
        String l;
        int lcount = 1;
        boolean isValid = true;
        int total = 0;
        int rMax = 0;
        int gMax = 0;
        int bMax = 0;
        while((l = r.readLine()) != null)
        {
            rMax = 0;
            gMax = 0;
            bMax = 0;
            if (lcount < 10)
            {
                l = l.substring(8);
            } else if (lcount < 100)
            {
                l = l.substring(9);
            } else
            {
                l = l.substring(10);
            }

            l.replace("//s", "");
            int j = 0;
            isValid = true;
            for (int i = 0; i < l.length() && isValid; i++)
            {
                if (Character.isDigit(l.charAt(i)))
                {
                    j = Integer.parseInt(String.valueOf(l.charAt(i)));
                    if (Character.isDigit(l.charAt(i + 1)))
                    {
                        j = j * 10 + Integer.parseInt(String.valueOf(l.charAt(i + 1)));
                        i++;
                    }
                }

                if (Character.isAlphabetic(l.charAt(i)))
                {
                    switch (l.charAt(i))
                    {
                        case 'r':
                            if (j > rMax)
                                rMax = j;
                            break;
                        case 'g':
                            if (j > gMax)
                                gMax = j;
                            i += 4;
                            break;
                        case 'b':
                            if (j > bMax)
                                bMax = j;
                            i += 3;
                            break;
                    }

                    if (rMax != 0 && gMax != 0 && bMax != 0 && i == l.length() - 1)
                        total += rMax * gMax * bMax;
                }
            }
        }
        System.out.println(total);
    }
}