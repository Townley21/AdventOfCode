package cti.BrandonTownley.Day2;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

// then press Enter. You can now see whitespace characters in your code.
public class Main
{
    public static void main(String[] args) throws IOException
    {
        String input = "src/main/resources/Day2Input.txt";

        BufferedReader r = new BufferedReader(new FileReader(input));
        String l;
        int lcount = 1;
        int redMax = 12;
        int greenMax = 13;
        int blueMax = 14;
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


            for (int i = 0; i < l.length(); i++)
            {
                if (Character.isDigit(l.charAt(i)))
                {

                }
            }

            lcount++;
            System.out.println(l);

        }
    }
}