package codes;

import java.util.Scanner;

public class LinkedGrid {
	public Node root;
	public int number, losses=0, wins=0;
	public LinkedGrid(int dimension) //creates a linked grid
	{
		Node temp = new Node();
		root = temp;
		Node marker = root;//marker is also temp
		
		for (int x=0; x < dimension-1; x++)//creates a first row of linked nodes
		{
			temp = new Node();
			marker.setRight(temp);

			temp.setLeft(marker);
			marker = temp;
		}//links the first row
		marker = root;
		Node rowMarker = marker;
		
		for(int z=1; z < dimension; z++)//creates rows of nodes top to bottom
		{
			temp = new Node();
			rowMarker.setDown(temp);
			temp.setUp(rowMarker);
			marker = temp;//moves the marker down
			rowMarker = temp;
			for(int y=0; y < dimension-1; y++)//creates a row of nodes left to right
			{
				temp = new Node();
				temp.setLeft(marker);
				marker.setRight(temp);
				temp.setUp(marker.getUp().getRight());
				marker.getUp().getRight().setDown(temp);
				marker = temp;//marker moves down the row from left to right
			}		
		}
	}
	public void setRow()//sets the row numbers
	{
		int x=1;
		Node temp=root;
		Node rowMarker = root;
		while(temp!=null)//check at beginning of new row
		{
			while(temp!=null)//check at end of row
			{
				temp.setRowNum(x);
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			x+=1;
			if(rowMarker.getDown() !=null)//
			{
				rowMarker = temp;
			}
		}
	}
	public void setColumn()//assigns column numbers to the nodes
	{
		int x=1;
		Node temp=root;
		Node columnMarker = root;
		while(temp!=null)//check at beginning of new row
		{
			while(temp!=null)//check at end of row
			{
				temp.setColumnNum(x);
				temp = temp.getDown();
			}
			temp = columnMarker.getRight();//moves to next column
			x+=1;
			if(columnMarker.getRight() !=null)
			{
				columnMarker = temp;
			}
		}
	}
	public void setNum(Node n)
	{	int prev;
		try
		{
			prev = n.getUp().getData();
			n.getUp().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		try
		{
			prev = n.getUp().getRight().getData();
			n.getUp().getRight().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		try
		{
			prev = n.getRight().getData();
			n.getRight().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		try
		{
			prev = n.getRight().getDown().getData();
			n.getRight().getDown().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		try
		{
			prev = n.getDown().getData();
			n.getDown().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		try
		{
			prev = n.getDown().getLeft().getData();
			n.getDown().getLeft().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		try
		{
			prev = n.getLeft().getData();
			n.getLeft().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		try
		{
			prev = n.getLeft().getUp().getData();
			n.getLeft().getUp().setData(prev+1);
		}
		catch (Exception e)
		{
			
		}
		
	}
	public void setGrid()
	{	Scanner input = new Scanner(System.in);
		int size = 81, max=9, min=1, randomRow, randomColumn, level=1;
		boolean error;
		Node temp = root;
		Node rowMarker = root;
		do //error trap for level input
		{	error =false;
			System.out.println("Pick a Level: ");
			System.out.println("1. Easy");
			System.out.println("2. Medium");
			System.out.println("3. Hard");
			try
			{
				level = input.nextInt();
				if (level > 3 || level < 1)//if input is out of range
				{
					System.out.println("Invalid Entry.");
					System.out.println();
				}	
			}
			catch (Exception e)//if input is not an integer
			{
				error=true;
				System.out.println("Invalid Entry.");
				System.out.println();
				input.nextLine();
			}
		}while (level > 3 || level < 1 || error == true);
	
		System.out.println();
		while(temp!=null)//sets all grid numbers to initially be 0
		{
			while(temp!=null)
			{	temp.setBomb(false);
				temp.setData(0);
				temp.setSweeped(false);
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)
			{
				rowMarker = temp;
			}
		}
		//random generation of mines
		for(int y = 0; y < level*8; y++)//generates a rough number of mines depending on level
		{	temp=root;
			rowMarker=root;
			randomRow = (int)(Math.random()*(max-min+1) + min);
			randomColumn = (int)(Math.random()*(max-min+1) + min);
			
			while(temp!=null)//plants the mines
			{
				while(temp!=null)
				{
					if(temp.getRowNum()==randomRow && temp.getColumnNum()== randomColumn)
					{	
						temp.setBomb(true);//sets the numbers for a box
						temp.setSweeped(true);//do not need to be sweeped
					}
					temp = temp.getRight();
				}
				temp = rowMarker.getDown();
				if(rowMarker.getDown() !=null)
				{
					rowMarker = temp;
				}
			}
		}	
		temp=root;
		rowMarker=root;
		while(temp!=null)//sets grid numbers (otherwise 0)
		{
			while(temp!=null)
			{
				if(temp.isBomb()==true)
				{
					setNum(temp);
				}
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)
			{
				rowMarker = temp;
			}
		}
	}
	public boolean sweep()//recursive
	{	Scanner input = new Scanner(System.in);//ERROR TRAP INPUTS!!!!
		Node temp = root;
		Node rowMarker = root;
		int columnGuess=0, rowGuess=0;
		boolean error=false;
		do //error trap for column number guess
		{	error =false;
			System.out.println("Guess Column Number: ");
			try
			{
				columnGuess = input.nextInt();
				if (columnGuess > 9 || columnGuess < 1)//input is out of range
				{
					System.out.println("Invalid Entry.");
					System.out.println();
				}	
			}
			catch (Exception e)//input is not an integer
			{
				error=true;
				System.out.println("Invalid Entry.");
				System.out.println();
				input.nextLine();
			}
		}
		while (columnGuess < 1 || columnGuess > 10 || error==true);
		do //error trap for row number guess
		{	error=false;
			System.out.println("Guess Row Number: ");
			try
			{
				rowGuess = input.nextInt();
				if (rowGuess > 9 || rowGuess < 1)
				{
					System.out.println("Invalid Entry.");
					System.out.println();
				}	
			}
			catch (Exception e)
			{
				error=true;
				System.out.println("Invalid Entry.");
				System.out.println();
				input.nextLine();
			}
		}
		while (rowGuess < 1 || rowGuess > 10 || error==true);
	
		while(temp!=null)//goes through grid and looks for inputed box
		{
			while(temp!=null)
			{
				if(temp.getRowNum()== rowGuess && temp.getColumnNum()== columnGuess)//finds the box
				{	temp.setSweeped(true);
					if(temp.isBomb()==true)//a mine is hit
					{	
						losses++;
						System.out.println("You Lose!");
						return true;
					}
					else//box is not a mine
					{
						if(temp.getData()!= 0)//if box has a number clue
						{
							temp.setSweeped(true);
							display();
							if(check()==true)
							{	
								System.out.println("You Win!");
								return true;
							}
							sweep();//recursion
						}
						else//if data is 0
						{
							sweep2(temp);//clears out field
							display();
							if(check()==true)
							{	
								System.out.println("You Win!");
								return true;
							}
							sweep();
						}					
					}
					
				}
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)//if there is another row below
			{
				rowMarker = temp;
			}
		}
		return true;
	}
	public void sweep2(Node temp)
	{	
		number=0;//data of the original parameter temp
		try //try catch loops check for nearby numbered nodes to clear out
		{
			if(temp.getUp().isSweeped()==false)
			{	
				temp.getUp().setSweeped(true);
				if(temp.getUp().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getUp());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
		try //try catch loops check for nearby numbered nodes to clear out
		{
			if(temp.getUp().getRight().isSweeped()==false)
			{
				temp.getUp().getRight().setSweeped(true);
				if(temp.getUp().getRight().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getUp().getRight());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
		try
		{
			if(temp.getRight().isSweeped()==false)
			{	
				temp.getRight().setSweeped(true);
				if(temp.getRight().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getRight());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
		try //try catch loops check for nearby numbered nodes to clear out
		{
			if(temp.getRight().getDown().isSweeped()==false)
			{	
				temp.getRight().getDown().setSweeped(true);
				if(temp.getRight().getDown().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getRight().getDown());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
		try
		{
			if(temp.getDown().isSweeped()==false)
			{
				temp.getDown().setSweeped(true);
				if(temp.getDown().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getDown());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
		try //try catch loops check for nearby numbered nodes to clear out
		{
			if(temp.getDown().getLeft().isSweeped()==false)
			{
				temp.getDown().getLeft().setSweeped(true);
				if(temp.getDown().getLeft().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getDown().getLeft());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
		try
		{
			if(temp.getLeft().isSweeped()==false)
			{
				temp.getLeft().setSweeped(true);
				if(temp.getLeft().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getLeft());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
		try //try catch loops check for nearby numbered nodes to clear out
		{
			if(temp.getLeft().getUp().isSweeped()==false)
			{
				temp.getLeft().getUp().setSweeped(true);
				if(temp.getLeft().getUp().getData()==number)//if box is a connected box that is zero
				{
					sweep2(temp.getLeft().getUp());//recursive code to set other nodes to be true that are the same number
				}		
			}
		}
		catch(Exception e)
		{
			
		}
	}
	public void endGame()
	{
		System.out.println("Game over.");
		Node temp = root;
		Node rowMarker = root;
		while(temp!=null)//displays the uncovered grid
		{
			while(temp!=null)
			{
				if(temp.isBomb()==true)
				{
					System.out.print("# ");
				}
				else
				{
					System.out.print(temp.getData()+" ");
				}
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)//if there is another row below
			{
				rowMarker = temp;
			}
			System.out.println();
		}
		System.out.println();
	}
	public boolean check()//checks if the game is over (whole board is sweeped)
	{
		Node temp = root;
		Node rowMarker = root;
		while(temp!=null)
		{
			while(temp!=null)
			{
				if(temp.isSweeped()==false)
				{
					return false;
				}
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			if(rowMarker.getDown() !=null)//if there is another row below
			{
				rowMarker = temp;
			}
		}
		wins++;
		return true;
	}
	public void display()
	{	
		int counter = 0;
		Node temp = root;
		Node rowMarker = root;
		System.out.print("    ");
		
		for(int x=1; x < 10; x++) //displays column numbers
		{	
			System.out.print(+x);
			System.out.print(" ");
		}
		System.out.println("");
		System.out.println("");
		
			while(temp!=null)
			{	
				for(int y=1; y < 10; y++)//prints out row numbers numbers
				{	System.out.print(+y);
					System.out.print("   ");
					
					while(temp!=null)
					{	if(temp.isSweeped() == false || temp.isBomb()==true)
						{
							System.out.print(temp.getInitial()+" ");//hides the data
						}
						else
						{
							System.out.print(temp.getData()+" "); //box data is revealed
						}
						temp = temp.getRight();
					}
					temp = rowMarker.getDown();
					if(rowMarker.getDown() !=null)//if there is another row below
					{
						rowMarker = temp;
					}
					System.out.println();
				}
			}	
			System.out.println();	
	}
	public int losses()//method passes on losses to Main class
	{
		return losses;
	}
	public int wins()//method passes on wins to Main class
	{
		return wins;
	}
}
