package me.caleb;
import java.util.*;

//code commenting challenge next

public class dotComBust {
    //1 declare and initialize the variables we will need
    private GameHelper helper = new GameHelper();
    private ArrayList dotComList = new ArrayList();
    private int numOfGuesses = 0;

    private void setUpGame() {
        //2make three dot com objects, give em names and stick them in the array list
        DotCom one = new DotCom();
        one.setName("pets.com");
        DotCom two = new DotCom();
        two.setName("etoys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);

        //3print brief instructions for user
        System.out.println("Your goal is to sink three dot coms");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in as few guesses as possible.");

        for(int i = 0; i < dotComList.size(); i++){ //4repeat with all the dotcoms in the list
            ArrayList newLocation = helper.placeDotCom(3);//5 ask the helper for a dot com location
            DotCom dotComToSet = (DotCom) dotComList.get(i);//6 get a reference to a dot com from a list of dot coms
            dotComToSet.setLocationCells(newLocation);//7 call the setter method on this dot com to give it the location you got from the helper
        }
    }


    private void startPlaying(){
        while( !dotComList.isEmpty() ){//8 as long as the dot com list is not empty
            String userGuess = helper.getUserInput("Enter a Guess");//9 ask the user for a guess
            checkUserGuess(userGuess); //10call checkUserGuess method
        }
        finishGame(); //11 call finish game method
    }

    private void checkUserGuess(String userGuess){
        numOfGuesses++; //12 increment number of user guesses
        String result = "miss"; //13 assume its a miss unless told otherwise

        for(int i = 0; i < dotComList.size(); i++ ){ //14 repeat with all the dot coms in the list
            DotCom dotComToTest = (DotCom) dotComList.get(i); //15 get a dotcom out of the dotcoms list
            result = dotComToTest.checkYourself(userGuess);  //16 ask the dot com to check the user guess looking for a hit or kill
            if(result.equals("hit") ) {
                break; //17 get out of the loop early no point in testing the others
            }
            if(result.equals("kill") ){
                dotComList.remove(i);
                break; //18 the guys dead so take him out of the dot coms list then get out of the loop
            }
        }

        System.out.println(result); //19 print the result for the user
    }

    private void finishGame(){
        System.out.println("All dot coms are dead your stock is now worthless"); //20 print a message telling the user how they did
        if(numOfGuesses <= 18){
            System.out.println("It only took you " + numOfGuesses + "guesses!");
            System.out.println("You got out before your options sank");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + "guesses..");
        }
    }

    public static void main(String[] args){
        dotComBust game = new dotComBust(); //create the game object
        game.setUpGame(); //tell the game object to set pu the game
        game.startPlaying(); //tell the game to start the main game play loop(keep asking the user for input and guesses)
    }
}
