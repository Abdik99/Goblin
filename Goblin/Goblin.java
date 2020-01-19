import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 *  The Goblin Game is made here using BetterArray. 
 *  @author Abdikarim Abdirahman
 */


class Goblin {
	/**
	 *  Used to take in input for step.
	 */
	private Scanner userIn;
	/**
	 *  The files name for init() that is made in the constructor that we read from.
	 */
	private String dictFileName;
	/**
	 * The number of letter each word needs in the dictionary.
	 */
	private int numLetters;
	/**
	 * The number of guesses allowed per round.
	 */
	private int numGuesses;
	/**
	 * I made this for reading the file in innit() because userIn uses System.out.
	 */
	private Scanner userOut;
	/**
	 * The array I used to check put the dictionary in.
	 */
	private BetterArray<String> dict=new BetterArray<>();
	/**
	 *  The DEFAULT_CAPACITY is the size when the capacity at the start.
	 */
	private BetterArray<Character> guess=new BetterArray<>();
	/**
	 *  what getCurrentWord() returns which the corrected guesses in it.
	 */
	private BetterArray<Character> nulls=new BetterArray<>();
	/**
	 *  A array that is used in bestPartition() to store each partition.
	 */
	private BetterArray<String> temp;
	/**
	 *  A 2d array that stores all the partition which helps find the best partition.
	 */
	private static BetterArray<BetterArray<String>> dof2;
	/**
	 *  used in getCurrentWords to make nulls are in the array nulls when nothing is guessed.
	 */
	int count=0;



	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!

	/**
	 *  returns the the words in the dictionary.
	 *  @return dict.clone() it returns a clone of the dictionary
	 */
	public BetterArray<String> getWords() {
		return dict.clone();
	}

	/**
	 *  returns the the guesses so far.
	 *  @return guess.clone() it returns a clone of the guesses so far
	 */

	public BetterArray<Character> getGuesses() {
		return guess.clone();
	}

	/**
	 *  set getCurrentWord() when there is no guesses.
	 */
	public void setter()
	{
		for (int i=0;i<numLetters;i++)
		{
			nulls.append(null);
		}
	}

	/**
	 *  returns the the guesses so far using count to first set the nulls.
	 *  @return guess.clone() it returns a clone of the guesses so far
	 */
	public BetterArray<Character> getCurrentWord() {
		if (count==0)
		{
			setter();
			count++;
		}
		return nulls.clone();
	}


	/**
	 *  gets the numbers of guesses left.
	 *  @return numGuesses it returns the numbers of guesses left.
	 */
	public int getGuessesRemaining() {
		return numGuesses;
	}



	/**
	 *  a helper method it checks if a letter is in a word for.
	 *  @param letter the letter you are checking
	 *  @param sent the word you check if it is in
	 *  @return true if it is in.
	 */

	public boolean isitin(String letter,String sent[])
	{
		for (int i=0;i<sent.length;i++) 
		{
			if (letter.equals(sent[i]))
			{
				return true;
			}
		}
		return false;

	}

	/**
	 *  a helper method it checks if there are repeating letters in a word.
	 *  @param sent the word you are checking for repeats
	 *  @return true if it does not repeat.
	 */

	public  boolean repeat(String sent)
	{

		String letters []= new String [sent.length()];
		String word []= sent.split("");

		int index=0;
		for (int i=0;i<word.length;i++) 
		{
			if (!(isitin(word[i],letters)))
			{
				letters[index]=word[i];
				index++;
			}

			else
			{
				return false;
			}



		}
		return true;
	}

	/**
	 *  a helper method checks the worda for dictionary.
	 *  @param word the word you are checking
	 *  @return bool it is allowed to be in the dictionary
	 */

	public boolean check(String word)
	{
		boolean bool =false; 

		if (word.length()==numLetters && repeat(word)==true)
		{
			bool=true;
		} 

		return bool;
	} 


	/**
	 *  reads the file and uses check to make sure the letters do not repeat and the words are big enough.
	 *  @return bool if it is read is true or if it is not or if dictionary is empty or if a word is less than 2
	 */


	public boolean init() {


		boolean bool=true;
		int check=0;
		int check1=0;

		int index=0;
		try
		{
			File fin = new File(dictFileName);   
			userOut = new Scanner(fin);
			String word="";
			while(userOut.hasNextLine())
			{
				word=userOut.nextLine();
				if (check(word))
				{
					if (word.length()>=2) 
					{
						dict.append(word);
					}
				}

			}
		}
		catch (FileNotFoundException error)
		{
			System.out.println("Goblin lost his dictionary! You win!");
			bool=false;
		}
		if (dict.size()==0 && bool==true)
		{
			System.out.println("Goblin can't find enough words! You win!");
			bool=false;

		} 


		return bool;
	}



	/**
	 *  a helper method that tells you where a letter is in a word.
	 *  @param word the word you are looking at 
	 *  @param letter the letter you are looking for 
	 *  @return index where the letter is 
	 */


	public int where(String word,char letter)
	{
		int index=0;

		for (int i=1;i<word.length()+1;i++)
		{
			if (word.charAt(i-1)==letter)
			{
				index=i; 
			}      
		}   
		return index;
	}

	/**
	 *  a helper method that is used in bestPartition and looks for the biggest Partition.
	 *  @param arr the word you are looking at 
	 *  @return index the index of the biggest partiton
	 */


	public int checs(BetterArray<BetterArray<String>> arr)
	{
		int max=arr.get(0).size();
		int index=0;
		for (int i=0;i<arr.size();i++)
		{
			if (arr.get(i).size()>max)
			{
				max=arr.get(i).size();
				index=i;
			}
		}



		return index;
	}

	/**
	 *  gets the best partition and gets the biggest partition.
	 *  @param guess the guess 
	 *  @return index where the best partition is. 
	 */


	public int bestPartition(char guess) {
		dof2= new BetterArray<BetterArray<String>>();
		int ans=0;
		for (int a=0;a<numLetters+1;a++)
		{
			temp= new BetterArray<String>();
			for (int i=0;i<dict.size();i++)
			{ 

				int num=0;
				if (where(dict.get(i),guess)==a)
				{

					temp.append(dict.get(i));

				}

			}
			dof2.append(temp);

		}
		ans=checs(dof2)-1;

		dict=dof2.get(ans+1);

		return ans;
	}


	/**
	 * helper method checks if the user get the right word.
	 * @return bool if all there are none nulls aka the right word
	 */

	public boolean done() 
	{
		boolean bool=true;
		String full="";
		for (int i=0;i<nulls.size();i++)
		{

			if (nulls.get(i)==null)
			{
				bool=false;

			}
		}

		return bool;
	}



	/**
	 *  checks if a word has already been guessed.
	 *  @param word the word you are checking  
	 *  @return bool if it has already been guessed or not. 
	 */
	public  boolean already(String word)
	{
		boolean bool=true;
		for (int i=0;i<guess.size();i++)
		{
			if (guess.get(i)==word.charAt(0)) 
			{
				bool=false;
			}
		}
		return bool;
	}


	/**
	 * checks if a letter has is the right size and asks again if it is not.
	 * @param word the letter you are checking/
	 * @return word a new word that is the right size. 
	 */
	public String lencheck(String word)
	{

		while (word.length()==0||word.length()>1)
		{
			System.out.print("Goblin says \"One letter! Guess a single letter\": ");
			word=this.userIn.nextLine();   
		}

		return word;

	}

	/**
	 * takes in guesses and uses all the method to play the game.
	 * where the game is mostly played.
	 * @return true determines if the turn/game is done or not. 
	 */
	public boolean step() {

		boolean bool=true;
		String word="";
		getCurrentWord();

		while(bool)
		{  

			System.out.print("Goblin says \"Guess a letter\": ");

			bool=this.userIn.hasNextLine();

			word=this.userIn.nextLine();
			if  (word.length()==0||word.length()>1)
			{
				word=lencheck(word);
			}

			while (!(already(word)))
			{
				System.out.print("Goblin says \"You already guessed: " + getGuesses().toString() + "\nGuess a new letter"+"\""+": ");
				word=this.userIn.nextLine();

				if (word.length()==0||word.length()>1)
				{
					word=lencheck(word);
				}

			}
			char letter=word.charAt(0);
			guess.append(letter); 
			int num=bestPartition(letter);

			if (num!=-1 && (!done()))
			{
				nulls.replace(num,letter);
				System.out.println( "Goblin says \"Yeah, yeah, it's like this: "+ getCurrentWord().toString().replaceAll("null","-").replaceAll("[ ,]","")+"\"");
				if (done())
				{
					return false;
				}
				return true;
			}
			if (num==-1 && (!done()) && numGuesses!=0)
			{
				numGuesses--;

				System.out.println( "Goblin says \"No dice! "+getGuessesRemaining()+" wrong guesses left...\"");

				if (numGuesses==0)
				{
					return false;
				}

				if (numGuesses!=0)
				{
					return true;
				}

			}
		}
		return false;
	}

	/**
	 *  where the game is finish and tells you who won.
	 */
	public void finish() {
		if (numGuesses==0)
		{
			System.out.println("Goblin says \"I win! I was thinking of the word '"+getWords().get(0)+"'");
			System.out.println("Your stuff is all mine... I'll come back for more soon!\"");
		}

		if (done())
		{
			System.out.println("Goblin says \"You win... here's your stuff. But I'll get you next time!\"");
		}
	}

	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	/**
	 * takes in guesses and uses all the method to play the game.
	 * @param dictFileName the dictionary file name
	 * @param numLetters the number of letters 
	 * @param numGuesses the number of guesses
	 */

	public Goblin(String dictFileName, int numLetters, int numGuesses) {
		this.userIn = new Scanner(System.in);
		this.dictFileName = dictFileName;
		this.numLetters = numLetters;
		this.numGuesses = numGuesses;
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 *  main method.
	 *  @param args main method
	 */

	public static void main(String[] args)
	{
		Goblin g1= new Goblin("../dictionary-mini.txt", 3, 10);
		Goblin g2 = new Goblin("../dictionary-mini.txt", 6, 6);
		Goblin g3 = new Goblin("../dictionary.txt", 1, 6);
		Goblin g4 = new Goblin("banana.txt", 3, 3);


		if(g1.init() && g2.init() && !g3.init() && !g4.init()) {
			System.out.println("Yay 1");
		}

		if(g1.getGuessesRemaining() == 10 && g1.getWords().size() == 1
				&& g2.getGuessesRemaining() == 6 && g2.getWords().size() == 5
				&& g1.getGuesses().size() == 0 && g2.getCurrentWord().size() == 6) {
			System.out.println("Yay 2");
		}

		BetterArray<Character> g1word = g1.getCurrentWord();
		if(g1word.get(0) == null  && g1word.get(1) == null && g1word.get(2) == null) {
			System.out.println("Yay 3");
		}

		//remember what == does on objects... not the same as .equals()
		if(g1.getWords() != g1.getWords() && g1.getGuesses() != g1.getGuesses()
				&& g1.getCurrentWord() != g1.getCurrentWord()) {
			System.out.println("Yay 4");
		}







		if(g2.bestPartition('l') == -1 && g2.getWords().size() == 3
				&& g2.bestPartition('p') == 0 && g2.getWords().size() == 2
				&& g2.bestPartition('n') == -1 && g2.getWords().size() == 1) {
			System.out.println("Yay 5");
		}





	}




}
