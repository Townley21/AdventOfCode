//========================================================================
//
//                       U N C L A S S I F I E D
//
//========================================================================
//  Copyright (c) 2023 Chesapeake Technology International Corp.
//  ALL RIGHTS RESERVED
//  This material may be reproduced by or for the U.S. Government
//  pursuant to the copyright license under the clause at
//  DFARS 252.227-7013 (OCT 1988).
//========================================================================
//  SBIR Data Rights Statement
//  Contract Number: N68335-13-C-0258
//
// Expiration of SBIR Data Rights Period:
//     5 years after completion of final contract modification
//
// The Government's rights to use, modify, reproduce, release, perform,
//  display, or disclose technical data or computer software marked with
//  this legend are restricted during the period shown as provided in
//  paragraph (b)(4) of the Rights in Noncommercial Technical Data and
//  Computer Software--Small Business Innovative Research (SBIR) Program
//  clause contained in the above identified contract. No restrictions
//  apply after the expiration date shown above. Any reproduction of
//  technical data, computer software, or portions thereof marked with
//  this legend must also reproduce the markings.
//========================================================================
package Day3.Part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * TODO: Insert Class Description Here!
 *
 * @author Copyright 2023 Chesapeake Technology International Corp.
 * @since
 */
public class Main
{
    /**
     * 467..114..
     * ...*......
     * ..35..633.
     * ......#...
     * 617*......
     * .....+.58.
     * ..592.....
     * ......755.
     * ...$.*....
     * .664.598..
     */

    public static void main(String[] args) throws IOException {
        String input = "src/main/resources/Day3Input.txt";

        BufferedReader r = new BufferedReader(new FileReader(input));
        String l;
        boolean valid = false;
        int total = 0;
        String j = "";
        String k = "";
        String c = "";
        String l0 = "";
        String l1 = r.readLine();
        String l2 = r.readLine();
        int lineCountDebug = 1;
        while (l1 != null)
        {
            for (int i = 0; i < l1.length(); i++)
            {
                if (Character.isDigit(l1.charAt(i)))
                {
                    String n = String.valueOf(l1.charAt(i));
                    if (j.isEmpty())
                    {
                        j = n;
                        if (!valid)
                            valid = checkForSpecialsInArea(i, l0, l1, l2);

                    } else if (k.isEmpty())
                    {
                        k = n;
                        if (!valid)
                            valid = checkForSpecialsInArea(i, l0, l1, l2);
                    } else if (c.isEmpty())
                    {
                        c = n;
                        if (!valid)
                            valid = checkForSpecialsInArea(i, l0, l1, l2);
                    }
                }

                if (l1.charAt(i) == '.')
                {
                    if (!valid)
                    {
                        j = "";
                        k = "";
                        c = "";
                    }
                    else
                    {
                        total += Integer.parseInt(j.concat(k).concat(c));
                        j = "";
                        k = "";
                        c = "";
                        valid = false;
                    }
                }

                if((!j.isEmpty() || !k.isEmpty() || !c.isEmpty()) && (l1.charAt(i) == '\0' || isSpecialChar(l1.charAt(i))))
                {
                    total += Integer.parseInt(j.concat(k).concat(c));
                    j = "";
                    k = "";
                    c = "";
                    valid = false;
                }

            }

            l0 = l1;
            l1 = l2;
            l2 = r.readLine();
            lineCountDebug++;
        }

        System.out.println(total);
    }

    private static boolean isSpecialChar(char c)
    {
        switch (c)
        {
            case '*':
            case '/':
            case '#':
            case '%':
            case '&':
            case '+':
            case '$':
            case '@':
            case '-':
            case '=':
                return true;
        }
        return false;
    }

    private static boolean checkForSpecialsInArea(int i, String l0, String l1, String l2)
    {
        //check same line
        if (i > 0 && i != l1.length() - 1) {
            if (isSpecialChar(l1.charAt(i - 1)))
                return true;

            if (isSpecialChar(l1.charAt(i + 1)))
                return true;
        }

        if (i == l1.length() - 1)
        {
            if (isSpecialChar(l1.charAt(i - 1)))
                return true;
        }


        //Check Below
        if (l2 != null)
        {
            //middle element
            if (i > 0 && i != l1.length() - 1)
            {
                for (int x = i - 1; x < i + 2; x++)
                {
                    if (isSpecialChar(l2.charAt(x)))
                    {
                        return true;
                    }
                }
            }

            //first element
            if (i == 0)
            {
                for (int x = i; x < i + 2; x++)
                {
                    if (isSpecialChar(l2.charAt(x)))
                    {
                        return true;
                    }
                }
            }

            //last element
            if (i == l1.length() - 1)
            {
                for (int x = i - 1; x < i + 1; x++)
                {
                    if (isSpecialChar(l2.charAt(x)))
                    {
                        return true;
                    }
                }
            }
        }

        if (!l0.isEmpty())
        {
            //middle element
            if (i > 0 && i != l1.length() - 1)
            {
                for (int x = i - 1; x < i + 2; x++)
                {
                    if (isSpecialChar(l0.charAt(x)))
                    {
                        return true;
                    }
                }
            }

            //first element
            if (i == 0)
            {
                for (int x = i; x < i + 2; x++)
                {
                    if (isSpecialChar(l0.charAt(x)))
                    {
                        return true;
                    }
                }
            }

            //last element
            if (i == l1.length() - 1)
            {
                for (int x = i - 1; x < i + 1; x++)
                {
                    if (isSpecialChar(l0.charAt(x)))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
