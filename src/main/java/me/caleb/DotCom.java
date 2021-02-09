package me.caleb;
import java.util.*;

public class DotCom { //dot coms instance variables
    private ArrayList locationCells;  //an ArrayList of cell locations
    private String name; //the dotcoms name

    public void setLocationCells(ArrayList loc){
        locationCells = loc; //a setter method that updates the dotcoms location (random location provided by helper method placeDotCom())
    }

    public void setName(String n){
        name = n; //ya know just a basic setter method
    }

    public String checkYourself(String userInput){
        String status = "miss";
        int index= locationCells.indexOf(userInput); //the arrayList indexof method in action if the user guess i sone of the entires is the array list it will return its location if not just -1

        if(index >= 0){
            locationCells.remove(index); //using remove method to delete an entry
            if(locationCells.isEmpty() ){  //using the is empty method to see if all locations have been guessed
                status= "kill";
                System.out.println("Ouch! You sunk " + name + " : ( " ); //tell the user when a dotcom has been sunk
            } else {
                status = "hit";
            }
        }
        return status; //return miss, hit, or kill
    }
}
