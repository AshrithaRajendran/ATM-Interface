package Game.jsp;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		Random random=new Random();
		int lowerRange=1;
		int upperRange=100;
		int generateNumber=random.nextInt(upperRange-lowerRange+1)+lowerRange;
		int maxAttempts=5;
		int currentRound=1;
		int score=0;
		System.out.println("welcome to Guess the Number");
	while(currentRound<=3){//you can adjust the number of rounds as needed
		System.out.println("Guess a number between"+lowerRange+"and"+upperRange+": ");
	for(int attempts=1;attempts<=maxAttempts; attempts++)
		{
		int userGuess=scn.nextInt();
		if(userGuess==scn.nextInt())
		{
			System.out.println("congratulation ! you guessed the correct number");
			score+=10-attempts;  //score based on attempts
			break;
		}
		else if(userGuess< generateNumber)
		{
			System.out.println("too low!try again");
		}
		else
		{
			System.out.println("too high! try again");
		}
		
		if(attempts==maxAttempts)
		{
			System.out.println("Sorry,youhave out of attempts.The correct number is:"+generateNumber);
		}
		}
		
		currentRound++;
		generateNumber=random.nextInt(upperRange-lowerRange+1)+lowerRange;
	}
	System.out.println("\nGAMEOVER! your totalScore is:"+score);
	
	scn.close();
	}
}

