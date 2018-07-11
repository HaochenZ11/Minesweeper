package codes;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedGrid lg=new LinkedGrid(9);
		Scanner input = new Scanner (System.in);
		int playAgain=1;
		boolean error=false;
		lg.setRow();
		lg.setColumn();
		System.out.println("===========================");
		System.out.println("  Welcome to Minesweeper!");
		System.out.println("===========================");
		System.out.println("");
		while (playAgain == 1)//loops main game until player chooses to not play again
		{
			lg.setGrid();
			lg.display();//initial display hides data 
			lg.sweep();
			lg.endGame();
			do //error trap for play again input
			{	error =false;
				System.out.println("Would you like to play again?");
				System.out.println("1. Yes");
				System.out.println("2. No");
				try
				{
					playAgain = input.nextInt();
					if (playAgain > 2 || playAgain < 1)//if input is out of range
					{
						System.out.println("Invalid Entry.");
						System.out.println();
					}	
				}
				catch (Exception e)//if input not an integer
				{
					error=true;
					System.out.println("Invalid Entry.");
					System.out.println();
					input.nextLine();
				}
			}while (playAgain > 2 || playAgain < 1 || error == true);
		
			System.out.println();
			if(playAgain==2)
			{	System.out.println("Thank You for Playing!");
				System.out.println("=============");
				System.out.println("  Wins:   "+ lg.wins());
				System.out.println("  Losses: "+ lg.losses());
				System.out.println("=============");
			}
		}
	}

}
