package Day2.Part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// then press Enter. You can now see whitespace characters in your code.
public class Main
{
    public static void main(String[] args) throws IOException
    {
        String input = "src/main/resources/Day2Input.txt";

        BufferedReader r = new BufferedReader(new FileReader(input));
        String l;
        int lcount = 1;
        boolean isValid = true;
        int total = 0;
        while((l = r.readLine()) != null)
        {
            //Game 1: 13 green, 3 red; 4 red, 9 green, 4 blue; 9 green, 10 red, 2 blue
            //Game 10: 2 red, 2 green, 2 blue; 10 blue, 2 red, 1 green; 2 green, 9 blue, 3 red
            //Game 100: 5 red, 9 green, 2 blue; 9 blue, 6 green, 1 red; 8 blue, 7 green, 3 red
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
                            if (j > 12)
                            {
                                isValid = false;
                            }
                            i += 2;
                            break;
                        case 'g':
                            if (j > 13)
                                isValid = false;
                            i += 4;
                            break;
                        case 'b':
                            if (j > 14)
                                isValid = false;
                            i += 3;
                            break;
                    }
                }

                if (!isValid)
                    break;
            }

            if (isValid)
            {
                total += lcount;
            }
            lcount++;
        }
        System.out.println(total);
    }
}