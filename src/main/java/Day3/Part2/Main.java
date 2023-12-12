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
package Day3.Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: Insert Class Description Here!
 *
 * @author Copyright 2023 Chesapeake Technology International Corp.
 * @since
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        String input = "src/main/resources/Day3Input.txt";
        Map<String, Integer> gearMap = new HashMap<String, Integer>();

        BufferedReader r = new BufferedReader(new FileReader(input));
        String l0 = null;
        String l1 = r.readLine();
        String l2 = r.readLine();
        int lCount = 0;
        int total = 0;
        while (l1 != null)
        {
            int lineTotal = 0;
            lineTotal = parseLine(l0, l1, l2, lCount, gearMap);
            total += lineTotal;
            l0 = l1;
            l1 = l2;
            l2 = r.readLine();
            lCount++;
        }
        System.out.println(gearMap.size());
        System.out.println(total);
    }

    private static int parseLine(String l0, String l1,  String l2, int lCount, Map<String, Integer> gearMap)
    {
        String loc = null;
        int lineTotal = 0;
        String a = "";
        String b = "";
        String c = "";
        String parsedNum;
        for (int i = 0 ; i < l1.length(); i++)
        {
            char n = l1.charAt(i);

            if (Character.isDigit(n))
            {
                parsedNum = String.valueOf(n);

                if (a.isEmpty())
                {
                    a = parsedNum;
                    loc = (loc == null) ? checkForGears(l0, l1, l2, lCount, i) : loc;
                } else if (b.isEmpty())
                {
                    b = parsedNum;
                    loc = (loc == null) ? checkForGears(l0, l1, l2, lCount, i) : loc;
                } else if (c.isEmpty())
                {
                    c = parsedNum;
                    loc = (loc == null) ? checkForGears(l0, l1, l2, lCount, i) : loc;
                }
            }

            if (l1.charAt(i) == '.' || isSpecialChar(l1.charAt(i)))
            {
                //check to see if we have number and a loc
                if ((!a.isBlank() || !b.isBlank() || !c.isBlank()) && loc != null)
                {
                    //we have a number and a gear let's see if we have located this before
                    if (gearMap.get(loc) != null)
                    {
                        parsedNum = "";
                        //reuse this value because why not
                        if (!a.isBlank())   parsedNum = parsedNum.concat(a);
                        if (!b.isBlank())   parsedNum = parsedNum.concat(b);
                        if (!c.isBlank())   parsedNum = parsedNum.concat(c);
                        lineTotal += gearMap.get(loc) * Integer.parseInt(parsedNum);
                        a = "";
                        b = "";
                        c = "";
                        loc = null;
                    } else
                    {
                        parsedNum = "";
                        if (!a.isBlank())   parsedNum = parsedNum.concat(a);
                        if (!b.isBlank())   parsedNum = parsedNum.concat(b);
                        if (!c.isBlank())   parsedNum = parsedNum.concat(c);
                        gearMap.put(loc, Integer.parseInt(parsedNum));
                        a = "";
                        b = "";
                        c = "";
                        loc = null;
                    }
                } else
                {
                    a = "";
                    b = "";
                    c = "";
                    loc = null;
                }


            }
        }
        return lineTotal;
    }

    private static String checkForGears(String l0, String l1, String l2, int lCount, int index)
    {
        String r = null;

        //Check Below
        if (l2 != null)
        {
            r = (r == null) ? checkBelow(l2, lCount, index): r;
        }

        //check Above
        if (l0 != null)
        {
            r = (r == null) ? checkAbove(l0, lCount, index): r;
        }

        //Check Directly Left
        if (index > 0)
        {
            r = (r == null) ? checkLeft(l1, lCount, index) : r;
        }

        //Check Directly Right
        if (index != l1.length() - 1)
        {
            r = (r == null) ? checkRight(l1, lCount, index) : r;
        }

        //Check Only Lower Right Diagonal
        if (index != l1.length() - 1 && l2 != null)
        {
            r = (r == null) ? checkLowerRightDiagonal(l2, lCount, index) : r;
        }

        //Check Only Lower Left Diagonal
        if (index > 0 && l2 != null)
        {
            r = (r == null) ? checkLowerLeftDiagonal(l2, lCount, index) : r;
        }

        //Check Only Upper Diagonally Right
        if (index == 0 && l0 != null)
        {
            r = (r == null) ? checkUpperRightDiagonal(l0, lCount, index) : r;
        }

        //Check Only Upper Left Diagonal
        if (l0 != null && index != 0)
        {
            r = (r == null) ? checkUpperLeftDiagonal(l0, lCount, index) : r;
        }

        return r;
    }

    private static String checkBelow(String l2, int lCount, int index)
    {
        if (l2.charAt(index) == '*')
        {
            return String.valueOf(lCount + 1) + index;
        }
        return null;
    }

    private static String checkAbove(String l0, int lCount, int index)
    {
        if (l0.charAt(index) == '*')
        {
            return String.valueOf(lCount - 1) + index;
        }
        return null;
    }

    private static String checkRight(String l1, int lCount, int index)
    {
        if (l1.charAt(index + 1) == '*')
        {
            return String.valueOf(lCount) + (index + 1);
        }
        return null;
    }

    private static String checkLeft(String l1, int lCount, int index)
    {
        if (l1.charAt(index - 1) == '*')
        {
            return String.valueOf(lCount) + (index - 1);
        }
        return null;
    }

    private static String checkLowerRightDiagonal(String l2, int lCount, int index)
    {
        if (l2.charAt(index + 1) == '*')
        {
            return String.valueOf(lCount + 1) + (index + 1);
        }
        return null;
    }

    private static String checkUpperRightDiagonal(String l0, int lCount, int index)
    {
        if (l0.charAt(index + 1) == '*')
        {
            return String.valueOf(lCount - 1) + (index + 1);
        }
        return null;
    }

    private static String checkLowerLeftDiagonal(String l2, int lCount, int index)
    {
        if (l2.charAt(index - 1) == '*')
        {
            return String.valueOf(lCount + 1) + (index - 1);
        }
        return null;
    }

    private static String checkUpperLeftDiagonal(String l0, int lCount, int index)
    {
        if (l0.charAt(index - 1) == '*')
        {
            return String.valueOf(lCount - 1) + (index - 1);
        }
        return null;
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
}
