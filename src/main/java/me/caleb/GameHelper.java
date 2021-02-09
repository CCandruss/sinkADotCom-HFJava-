package me.caleb;
import java.io.*;
import java.util.*;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int[] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.println(prompt + " ");
        try {
            BufferedReader is = new BufferedReader( new InputStreamReader(System.in) );
            inputLine = is.readLine();
            if(inputLine.length()== 0 ) return null;
        }
        catch(IOException e) {
            System.out.println("IOException : " + e);
        }
        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize){


        ArrayList alphaCells = new ArrayList(); /* alphaCells contains the three location based on coordinate */
        /* eg: - a0 is grid number 1 and a6 is grid number 7    */


        String temp = null;
        int[] coords = new int[comSize];    /*  coords array contains the three location(Based on grid count)
                                                covered by the corresponding DotCom */

        int attempts = 0;
        boolean success = false;    /* Success denotes whether the game has successfully placed the DotCom  */
        int location;

        /** comCount is used to help alternate between vertical and horizontal placing of dotComs ***/

        /** If comCount is 2, then incr = 1, then location is incremented by 1, thus moving horizontally **/
        /** Thus if a character is paced at (2,3) then next char will be at (2, 4) **/

        /** And if comOunt is not 2, then we move vertically by setting incr to gridLength which is 7 **/
        comCount++;
        int incr = 1;
        if( (comCount % 2) == 1){
            incr = gridLength;
        }

        while(!success & attempts++ < 200) {

            location = (int)(Math.random() * gridSize); /* Get random location between 0 and gridSize (49 in this case) */
            // System.out.println("try" + location);
            int x = 0;
            success = true;

            while(success && x < comSize) {

                if(grid[location] == 0) {   /*location in the grid is not occupied yet, so place the dotcom starting chars at this location */

                    coords[x++] = location; /* Add this location as used location for this DotCom in coords array   */

                    location += incr;       /* Increment location horizontally or vertically    */

                    if(location >= gridSize){    /* If location is greater than gridSize, we are out of range.. Break    */
                        success = false;
                    }
                    if(x > 0 && (location % gridLength == 0) ) { /* If location is at the end of any row (multiple of 7), break  */
                        success = false;
                    }
                }
                else{
                    System.out.println("used" + location);
                    success = false;
                }

                /* If success is true and x == comSize, then coords array is filled with all three location */
                /* If success is false, continue with the outer while loop, as the DotCom has still not been placed */

            }//end of inner while loop
        }//end of outer while loop

        int x = 0;
        int row = 0;
        int column = 0;
        System.out.println("\n");

        /* Now we need to populate the alphaCells arraylist with actual Coordinal locations */

        while(x < comSize) {

            grid[coords[x]] = 1;    /* First set the location in grid corresponding to each value in coords[] array to 1    */

            row = (int)(coords[x] / gridLength);    /* Get the row value for that coords location   */
            /* If coords[x] = 8, then it is 2nd row (0th is the first)..
             * (8 / 7 = 1) (As each row has max 7 grids)    */
            column = coords[x] % gridLength;        /* If coords[x] = 8, then column is 2nd (8 % 7 = 1) */

            /** Remember (0, 0) is 1st row 1st column, so (1, 1) is 2nd row 2nd column  */


            temp = String.valueOf(alphabet.charAt(column));     /* Label this coord location with the help of alphabet string defined above */
            /* Row 0 is a, Row 1 is b   */

            alphaCells.add(temp.concat(Integer.toString(row))); /* Concat row value with column value   */
            /* May be a0    */
            x++;

        }
        return alphaCells;  /* Return Arraylist containing the three coordinate location for the corresponding DOTCom   */
    }// end of method placeDotCom(int comSize)


}
