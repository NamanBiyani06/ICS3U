//Wordle Clone for ICS3U
//Naman Biyani
//March 8, 2022

//imports
import java.util.Scanner;
import java.util.ArrayList; 
import java.io.FileNotFoundException;
import java.io.File; 
import java.util.Arrays; 


public class Main {
  
  public static void main(String[] args)
  throws InterruptedException
    {
    //variables needed as static after iterations of game
    int wins = 0;
    int losses = 0;
    int winStreak = 0;
    boolean keepPlaying = true;
    
    //clear console function
    clearScreen();
        
    //instructions
    instructions();

    while(keepPlaying == true)
      {
    //reading the text file and creating an array with wordle word list
    String[] words = fileReader(); 

    //getting wordle 
    String wordle = wordleRandomizer(words);
      
    //converting wordle to character array for ease of access to letters
    char[] wordleArray = wordle.toCharArray();
    
    //input guesses
    boolean control = false;
    int guessCount = 0; 
        
    //string to contain colours to use when printing win or lose message
    //0 for grey, 1 for yellow, 2 for green
    String colours = "";
    
    while(control == false && guessCount<6)
      {
        //getting guess as a character array
        char[] guessArray = guessValidater(words); 
        
        //taking away one guess
        guessCount++;

        //checking individual letters and outputting their colours
        //getting the colours string which will be passes back into the method on the next loop
        colours = letterChecker(wordleArray, guessArray, wordle, colours);

        //checking if guess is correct
        if(Arrays.equals(guessArray, wordleArray))
        {
          control = true;
          wins++;
          winStreak++;
          keepPlaying = winMessage(colours, guessCount, wins, losses, keepPlaying, winStreak);
          
        }

        //checking if user has made 6 guesses
        if(guessCount>=6)
        {
          losses++;
          winStreak = 0;
          keepPlaying = loseMessage(wordle, wins, losses, keepPlaying, colours, guessCount);
        }

      }
    }
  }

  //clearing the screen before wordle starts
  public static void clearScreen() 
    {  
      System.out.print("\033[H\033[2J");  
      System.out.flush();  
    }  

  //Instructions
  //NOTE - add time delay type writer animation
  public static void instructions()
  throws InterruptedException
    {
      String[] instructions = 
      {"• Guess the WORDLE in six tries.", 
      "\n• Each guess must be a valid five-letter word.",
      "\n• Hit the enter button to submit.",
      "\n• After each guess, the color of the tiles will change to show how close your guess was to the word."
      };
      
      for(int i = 0; i<instructions.length; i++)
        {
          for(int j = 0; j<instructions[i].length(); j++)
            {
              System.out.print(instructions[i].charAt(j));
              Thread.sleep(50);
            }
          Thread.sleep(300);
        }
      System.out.println();
    }
  
  //reading the wordle words list
  public static String[] fileReader()
    {
    
      ArrayList<String> wordsAL = new ArrayList<String>();
  
      String[] words; 
      
      try
      {
      File wordleWords = new File("WordleWords.txt");
      Scanner reader = new Scanner(wordleWords);
      // checking end of file
      while (reader.hasNextLine()) 
        {
          // adding each string to arraylist
          wordsAL.add(reader.nextLine().toUpperCase());
        }
        words = wordsAL.toArray(new String[]{});
        reader.close(); 
      }
      catch (FileNotFoundException e) 
      {
        System.out.println("An error occurred.");
        e.printStackTrace();
        words = new String[0];
      }
  
      return words;
    
    }

  //getting randomized wordle
  public static String wordleRandomizer(String[] words)
    {
      String wordle = words[(int)Math.floor(Math.random()*words.length)];
      System.out.println("\u001B[1m       WORDLE");
      System.out.println();
  
      return wordle;
      
    }

  //inputting guess
  public static char[] guessValidater(String[] words)
  throws InterruptedException
    {
      //declaring scanner class
      Scanner input = new Scanner(System.in); 
      
      String guess = ""; 
      char[] guessArray; 
      boolean control2 = false; 
      
      while(control2 == false)
        {
          guess = (input.nextLine()).toUpperCase(); 
          //move up
          System.out.print(String.format("\033[%dA", 1)); 
          //erase line content
          System.out.print("\033[2K"); 
          
          for(int i = 0; i<words.length; i++)
            {
              if(guess.equals(words[i]) && guess.length() == 5)
              {
                control2 = true; 
              }
            }
          
          if(control2 == false)
          {
            System.out.println(ANSI_RED+"\"" + guess.toLowerCase() + "\" is not a real 5 letter word bozo!"+ANSI_RESET);
            Thread.sleep(1500);
            //move up
            System.out.print(String.format("\033[%dA", 1)); 
            //erase line content
            System.out.print("\033[2K"); 
          }   
        }

      //converting guess to a character array for ease of access
      guessArray = guess.toCharArray();
      
      return guessArray;
      
    }

  //printing the guess' result
  public static String letterChecker(char[] wordleArray, char[] guessArray, String wordle, String colours)
  throws InterruptedException
    {
      char[] wordleArrayNew = new char[5];
      for(int i = 0; i<5; i++)
        {
          wordleArrayNew[i] = wordleArray[i];
        }
      char[] guessArrayInfo = new char[5];
      for(int i = 0; i<5; i++)
        {
          guessArrayInfo[i] = guessArray[i];
        }
      
      for(int i = 0; i<5; i++)
        {
          if(wordleArrayNew[i] == guessArrayInfo[i])
          {
            wordleArrayNew[i] = '1';
            guessArrayInfo[i] = '2';
          }
        }
      for(int i = 0; i<5; i++)
        {
          for(int j = 0; j<5; j++)
            {
              if(wordleArrayNew[i] == guessArrayInfo[j])
              {
                wordleArrayNew[i] = '3';
                guessArrayInfo[j] = '4';
              }
            }
        }
      for(int j = 0; j<5; j++)
        {
          if(guessArrayInfo[j] != '2' && guessArrayInfo[j] != '4')
          {
            guessArrayInfo[j] = '5';
          }
        }

      
      for(int i = 0; i<5; i++)
        {
          Thread.sleep(650);
          if(guessArrayInfo[i] == '2')
          {
            System.out.print(ANSI_GREEN_BACKGROUND + " " +  guessArray[i] + " " +  ANSI_RESET);
            colours = colours + "2";
          }
          if(guessArrayInfo[i] == '4')
          {
            System.out.print(ANSI_YELLOW_BACKGROUND + " " +  guessArray[i] + " " +  ANSI_RESET);
            colours = colours + "1";
          }
          if(guessArrayInfo[i] == '5')
          {
            System.out.print(ANSI_BRIGHT_BLACK_BACKGROUND + " " + guessArray[i] + " " +  ANSI_RESET);
            colours = colours + "0";
          }
          System.out.print(" ");
        }
      System.out.println();
      System.out.println(" -   -   -   -   -");

      colours = colours + " ";
      
      return colours;
      
    }
  
  //win method
  //NOTE - FIX WINRATE
  public static boolean winMessage(String colours, int guessCount, int wins, int losses, boolean keepPlaying, int winStreak)
  throws InterruptedException 
    {
      System.out.println();
      System.out.println("    " + ANSI_GREEN_BACKGROUND + "\u001B[1m You Won! " + ANSI_RESET);
      System.out.println();

      //winrate
      double winRate = Math.round((wins / (wins+losses) * 100) * 10) / 10.0;
      System.out.println("  Win Rate: " + winRate + "%");
      System.out.println("   Win Streak: " + winStreak);
      System.out.println();
      
      //turning colours into a array
      String[] coloursArray = colours.split(" ");
      
      //printing colour grid
      for(int i = 0; i<guessCount; i++)
        {
          System.out.print((i+1) + " ");
          for(int j = 0; j<5; j++)
            {
              if(coloursArray[i].charAt(j) == '2')
              {
                System.out.print(ANSI_GREEN_BACKGROUND + "  " + ANSI_RESET);
              }
              else if(coloursArray[i].charAt(j) == '1')
              {
                System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_RESET);
              }
              else if(coloursArray[i].charAt(j) == '0')
              {
                System.out.print(ANSI_BRIGHT_BLACK_BACKGROUND + "  " + ANSI_RESET);
              }
              System.out.print(" ");
            }
          System.out.println();
          System.out.println("  -- -- -- -- --  ");
        }
      //keep playing prompt
      Scanner input = new Scanner(System.in);
      System.out.println();
      System.out.println("Would you like to keep playing?");
      System.out.print("Press ");
      System.out.print(ANSI_GREEN_BACKGROUND + "'r'" + ANSI_RESET);
      System.out.print(" to generate a new wordle or press "); 
      System.out.print(ANSI_RED_BACKGROUND + "any other key" + ANSI_RESET);
      System.out.println(" to quit:");
      
      char choice = input.nextLine().charAt(0);
      if(choice != 'r')
      {
        keepPlaying = false;
      }
      else
      {
        //clear the console for the new game
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
      }
      return keepPlaying;
    }

  //lose method
  public static boolean loseMessage(String s, int wins, int losses, boolean keepPlaying, String colours, int guessCount)
  throws InterruptedException 
    {
      System.out.println();
      System.out.println("    " + ANSI_RED_BACKGROUND + "\u001B[1m You Lost! " + ANSI_RESET);
      System.out.println();
      
      System.out.println("The Word was " + ANSI_GREEN_BACKGROUND + s + ANSI_RESET);
      System.out.println();

      //winrate
      double winRate = Math.round(((double)wins / (double)(wins+losses + 0.0) * 100.0) * 10.0) / 10.0;
      System.out.println(" Win Rate: " + winRate + "%");
      System.out.println(ANSI_RED + " Win Streak Reset!" + ANSI_RESET);
      System.out.println();
      
      //turning colours into a array
      String[] coloursArray = colours.split(" ");
      
      //printing colour grid
      for(int i = 0; i<guessCount; i++)
        {
          System.out.print((i+1) + " ");
          for(int j = 0; j<5; j++)
            {
              if(coloursArray[i].charAt(j) == '2')
              {
                System.out.print(ANSI_GREEN_BACKGROUND + "  " + ANSI_RESET);
              }
              else if(coloursArray[i].charAt(j) == '1')
              {
                System.out.print(ANSI_YELLOW_BACKGROUND + "  " + ANSI_RESET);
              }
              else if(coloursArray[i].charAt(j) == '0')
              {
                System.out.print(ANSI_BRIGHT_BLACK_BACKGROUND + "  " + ANSI_RESET);
              }
              System.out.print(" ");
            }
          System.out.println();
          System.out.println("  -- -- -- -- --  ");
        }
      
      //keep playing prompt
      Scanner input = new Scanner(System.in);
      System.out.println();
      System.out.println("Would you like to keep playing?");
      System.out.print("Press ");
      System.out.print(ANSI_GREEN_BACKGROUND + "'r'" + ANSI_RESET);
      System.out.print(" to generate a new wordle or press "); 
      System.out.print(ANSI_RED_BACKGROUND + "any other key" + ANSI_RESET);
      System.out.println(" to quit:");
      char choice = input.nextLine().charAt(0);
      if(choice != 'r')
      {
        keepPlaying = false;
      }
      else
      {
        //clear the console for the new game
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
      }
      return keepPlaying;
    }


  //Declaring the background color
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_BRIGHT_BLACK_BACKGROUND = "\033[0;100m";
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_YELLOW_BACKGROUND = "\033[0;103m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String ANSI_RED = "\u001B[31m";
  //Declaring ANSI_RESET so that we can reset the color
  public static final String ANSI_RESET = "\u001B[0m";
}
