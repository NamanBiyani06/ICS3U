

//imports
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.event.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.io.*;
import javax.sound.sampled.*;


class SortingAlgorithms
  {
    //SORTING ALGORITHMS
    //Bubble Sort
    //Timer Complexity: (O(n^2))
    public static void bubbleSort() throws InterruptedException
    {
      Main.sortType = "Bubble Sort";
      Timer timer = new Timer(1, null);
      ActionListener timerAction  = new ActionListener()
      {
        int i = 0; 
        int j = 0;
        @Override
        public void actionPerformed(ActionEvent e)
        {
          if(i>=Main.numberOfElements-1)
          {

              Main.sortProgress = "Sorted!";
              System.out.println("Sorted!");
              ((Timer) e.getSource()).stop();
        	  try{
              Main.arrayCheck();
            }
            catch(Exception eee)
              {
                
              }

          }
          if(j>=Main.numberOfElements-1-i)
          {
            j = 0;
            i++;
          }
          if(Main.array[j] > Main.array[j+1])
          {
            Sound.beep();
            Main.swap[j] = 2;
            Main.swap[j+1] = 2;
            int temp = Main.array[j];
            Main.array[j] = Main.array[j+1];
            Main.array[j+1] = temp;
            Main.arrayAccesses+=2;
          }
          Main.panel.repaint();
          Main.swap[j] = 1;
          j++;
          if(j!=Main.numberOfElements-1)
          {
            Main.swap[j+1] = 1; 
          }
          Main.panel.repaint();
        }
      };
      timer.addActionListener(timerAction);
      timer.start();
    }
    //Cocktail Sort
    public static void cocktailSort() throws InterruptedException
    {
      Main.sortType = "Cocktail Sort";
  
      Timer timer = new Timer(5, null);
      ActionListener timerAction = new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
          {
  
      int startingIndex = 0;
      int endingIndex = Main.numberOfElements - 1;
      boolean sorted = false;
      int temp;
  
      while(sorted == false)
        {
          sorted = true;
          for(int i = 0; i<endingIndex; i++)
          {
            Main.swap[i] = 2;
            if(Main.array[i] > Main.array[i+1])
            {
              Main.arrayAccesses+=2;
              temp = Main.array[i];
              Main.array[i] = Main.array[i+1];
              Main.array[i+1] = temp;
              sorted = false;
              Main.swap[i] = 2;
              Main.swap[i+1] = 2;
              Main.panel.paintImmediately(Main.panel.getBounds());
              Sound.beep();
            }
            Main.swap[i] = 1;
            Main.swap[i+1] = 1;
          }
        endingIndex--;
    
        if(sorted == true)
        {
            ((Timer) e.getSource()).stop();
          try
          {
            Main.arrayCheck();
          }
          catch(InterruptedException e2)
          {
            e2.printStackTrace();
          }
          Main.sortProgress = "Sorted!";
          System.out.println("Sorted!");
          Main.panel.repaint(); 

        }
    
        sorted = true;
  
        for(int i = endingIndex; i>0; i--)
          {
            Main.swap[i] = 2;
            if(Main.array[i] < Main.array[i-1])
            {
              Main.arrayAccesses+=2;
              temp = Main.array[i];
              Main.array[i] = Main.array[i-1];
              Main.array[i-1] = temp;
              sorted = false;
              Main.swap[i] = 2;
              Main.swap[i+1] = 2;
              Main.panel.paintImmediately(Main.panel.getBounds());
              Sound.beep();
            }
            Main.swap[i] = 1;
            Main.swap[i+1] = 1;
          }
        startingIndex++;
        }
        }
      };
      timer.addActionListener(timerAction);
      timer.start();
    }
    public static void selectionSort() throws InterruptedException
    {
      Main.sortType = "Selection Sort";
      for (int i = 0; i<Main.numberOfElements-1; i++)
        {
          int minValue = i;
          for (int j = minValue; j<Main.numberOfElements; j++)
            {
              if (Main.array[j]<Main.array[minValue])
              {
            	Main.swap[minValue] = 1;
            	minValue = j;  
            	Main.swap[minValue] = 2;
              }             
            }
         Main.swap[i] = 2;
         Main.swap[minValue] = 2;
         int temp = Main.array[i];
         Main.array[i] = Main.array[minValue];
         Main.array[minValue] = temp;
         Main.panel.paintImmediately(Main.panel.getBounds());
         Sound.beep();
         Thread.sleep(20);
         Main.arrayAccesses+= 2;
         Main.swap[i] = 1;
         Main.swap[minValue] = 1;
         Thread.sleep(20);
         Main.panel.paintImmediately(Main.panel.getBounds());

        }
       Main.arrayCheck();
       Main.sortProgress = "Sorted!";
       System.out.println(" Sorted!");
      try{
              Main.arrayCheck();
            }
            catch(Exception eee)
              {
                
              }
    }     
    
    //Quick Sort
    //Time Complexity: (O(nLog(n)))
    public static void quickSort(int low, int high) throws InterruptedException
    {
      Main.sortType = "Quick Sort";
  
      if(low<high)
      {
        int partition = partition(low, high);
  
        quickSort(low, partition - 1);
        quickSort(partition + 1, high);
      }

    }
  
    //partition for quick sort
    public static int partition(int low, int high) throws InterruptedException
    {
      int pivot = Main.array[high];
  
      //Main.swap[high] = 4;
      
      int i = low-1;
  
      for(int j = low; j<= high - 1; j++)
        {
          Main.swap[j] = 2;
          if(Main.array[j] <= pivot)
          {
            i++;
            String file = "beep.wav";
            Main.beep(file);
  
            int temp = Main.array[i];
            Main.array[i] = Main.array[j];
            Main.array[j] = temp;
            Main.arrayAccesses += 2;
            //Main.swap[i] = 2;
            //Main.swap[j] = 2;
            Main.panel.paintImmediately(Main.panel.getBounds()); 
            Thread.sleep(50);
          }
          //Main.swap[i+1] = 1;
          Main.swap[j] = 1;
        }
      int temp_two = Main.array[i + 1];
      Main.array[i+1] = Main.array[high];
      Main.array[high] = temp_two;
      Main.arrayAccesses += 2;
      //Main.swap[i+1] = 2;
      //Main.swap[high] = 2;
      Main.panel.paintImmediately(Main.panel.getBounds());
      //Main.swap[high] = 1;
      return (i + 1);
      
    }
    
    public static void merge(int[] array, int l, int m, int r) throws InterruptedException
    {
      int arrayOneLength=(m-l+1);
      int arrayTwoLength=(r-m);

      int[] L = new int[arrayOneLength];
      int[] R = new int[arrayTwoLength];
      for (int i = 0; i<arrayOneLength; i++)
        {
          L[i] = Main.array[l+i];
        }
      for (int i = 0; i<arrayTwoLength; i++)
        {
          R[i] = Main.array[m+1+i];
        }
  
      int a = 0;
      int b = 0;
  
      int key = l;
  
      while (a<arrayOneLength&&b<arrayTwoLength)
        {
          if (L[a]<R[b])
          {
            Main.array[key] = L[a];
            a++;
            Main.swap[key] = 2;
            Main.panel.paintImmediately(Main.panel.getBounds());
            Thread.sleep(10);
            Sound.beep();
            Main.swap[key] = 1;
            Main.panel.paintImmediately(Main.panel.getBounds());
            Main.arrayAccesses+= 2;            
          }
          else 
          {
            Main.array[key] = R[b];
            b++;
            
            Thread.sleep(10);
            Main.swap[key] = 2;
            Main.panel.paintImmediately(Main.panel.getBounds());
            Sound.beep();
            Main.swap[key] = 1;
            Main.panel.paintImmediately(Main.panel.getBounds());
            Main.arrayAccesses+= 2;
            Sound.beep();
          }
          key++;
        }
  
      while (a<arrayOneLength)
        {
          Main.array[key] = L[a];
          
          a++;
          Sound.beep();
          Main.swap[key] = 2;
          Main.panel.paintImmediately(Main.panel.getBounds());
          Thread.sleep(10);
          Main.swap[key] = 1;
          Main.panel.paintImmediately(Main.panel.getBounds());
          Main.arrayAccesses+= 2;
          key++;
        }
      while (b<arrayTwoLength)
        {
          Main.array[key] = R[b];
          
          b++; 
          Thread.sleep(10);
          Main.swap[key] = 2;
          Main.panel.paintImmediately(Main.panel.getBounds());
          Sound.beep();
          Main.swap[key] = 1;
          Main.panel.paintImmediately(Main.panel.getBounds());
          Main.arrayAccesses+= 2;
          key++;
        }
      if (l == 0&& r == Main.numberOfElements-1) {
    	  Main.arrayCheck();
    	  Main.sortProgress = "Sorted!";
      }
    }
     public static void mergeSort(int[] array, int l, int r) throws InterruptedException
    {
    	 /*
      this method continuously divides the array in half. The parameter uses 
      the array being divided, and l=the left most element while r=the right most element. For example, for an initial array of 128, l will be 0, and r will be 127
      */
      Main.sortType = "Merge Sort";
      /*
      this if statement calls the split over and over again until the 
      split portion's leftmost element is equal to the rightmost element. In
      other words, until the portion only has one element left
      */
      if (r>l)
      {
    /*
    calculates the middle element of the portion 
    (the average of the left and right)
    */
        int m =(r+l)/2;
        /*
     This time, it calls itself twice again, but divides the current portion in half. It achieves this by 
      */
        mergeSort(array, l, m);
        mergeSort(array, m+1, r);
     //this final method merges
        merge(array, l, m, r);
      }
    }
    
    public static void insertionSort() 
    {
      
      Main.sortType = "Insertion Sort";
      Timer timer = new Timer(5, null);
      ActionListener timerAction = new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
          {
            for (int i = 1; i<Main.numberOfElements; i++)
              {
                int j = i-1;
                int k = i;
                
                while (Main.array[k]<Main.array[j])
                {

                  Main.swap[k] = 2;
                  Main.swap[j] = 2;
                  int temp = Main.array[j];
                  Main.array[j] = Main.array[k];
                  Main.array[k] = temp;
                  Main.arrayAccesses+=2;          
                  Sound.beep();
                  Main.panel.paintImmediately(Main.panel.getBounds());
                  Main.swap[k] = 1;
                  Main.swap[j] = 1;
                  Main.panel.paintImmediately(Main.panel.getBounds());
                  j--;
                  k--;              
                  if (j<0)
                  {
                    break;
                  }
  
                }
                if (i==Main.numberOfElements-1)
                {
                  try
                    {
                      Main.arrayCheck();
                    }
                  catch(InterruptedException e2)
                    {
                      e2.printStackTrace();
                    }
                  Main.sortProgress = "Sorted!";
                  System.out.println(" Sorted!");
                  ((Timer) e.getSource()).stop();
                  
                }
              }
  
          }
      };
      timer.addActionListener(timerAction);
      timer.start();
    }
  
    //Bogo Sort
    //Time Complexity: O((N-1)*N!)
    public static void bogoSort() throws InterruptedException
    {
      Main.sortType = "Bogo Sort";
      Timer timer = new Timer(25, null);
  
      ActionListener timerAction = new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            boolean sorted = true;
  
            for(int i = 0; i<Main.numberOfElements-1; i++)
              {
                if(Main.array[i]>Main.array[i+1])
                {
                  sorted = false;
                  for(int j = 0; j<Main.numberOfElements; j++)
                    {
                      Main.swap[j] = 1;
                    }
                  Main.panel.repaint();
                }
                else{
                  Main.swap[i] = 2;
                  Main.swap[i+1] = 2;
                  Main.panel.repaint();
                }
              }
  
            if(sorted)
            {
            	try {
            		Main.arrayCheck();
            	}
            	catch (Exception c)
            	{
            		
            	}
              Main.sortProgress = "Sorted!";
              System.out.println(" Sorted!");
              ((Timer) e.getSource()).stop();
            }
            else 
            {
              //randomizing
              Random rand = new Random();  
           
          		for (int i=0; i<Main.numberOfElements; i++) 
                {
                  Main.arrayAccesses++;
          		    int position = rand.nextInt(Main.numberOfElements);
          		    int temp = Main.array[i];
          		    Main.array[i] = Main.array[position];
          		    Main.array[position] = temp;
          		  }
          		Sound.beep();
              Main.panel.repaint();
            }
            
          }
        };
      timer.addActionListener(timerAction);
      timer.start();
    }
}
