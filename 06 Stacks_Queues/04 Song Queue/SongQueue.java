//Name: Aaliya Hussain
//Date: 1/16/19

import java.io.*;
import java.util.*;

public class SongQueue
{
   private static Scanner keyboard;  //use this global Scanner for this lab only
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      keyboard = new Scanner(System.in);
      songQueue = readPlayList();
      printSongList();
      
      String prompt = "Add song (A), Play song (P), Delete song (D), Quit (Q):  ";
      String str = "";
      do{      
         System.out.print(prompt);
         str = keyboard.nextLine().toUpperCase();
         processRequest( str );
         System.out.println();
      }while(!str.equals("Q"));
   }
   
   public static Queue<String> readPlayList() throws IOException
   {
      Scanner infile = new Scanner (new File("songs.txt"));  
      String e = "";
      while(infile.hasNext())
         e += infile.nextLine() + " - ";
      String[] sa = e.split(" - ");
      Queue<String> ret = new LinkedList<String>();
      for(int k = 0; k < sa.length; k++)
         if(k % 2 == 0)
            ret.add(sa[k]);
      return ret;
   }
   
   public static void processRequest(String str)
   {
      if(str.equals("Q"))
      {
         System.out.println("No more music today!");
         System.exit(0);
      }
      if(str.equals("A"))
      {
         System.out.print("Song to add? ");
         songQueue.add(keyboard.nextLine());
         printSongList();
      }
      if(str.equals("P"))
      {
         if(songQueue.isEmpty())
            System.out.println("Empty queue!");
         else
            System.out.println("Now playing: " + songQueue.remove());
         printSongList();
      }
      if(str.equals("D"))
      {
         printSongList();
         System.out.print("Delete which song (exact match)? ");
         String d = keyboard.nextLine();
         if(!inQueue(d))
         {
            System.out.println("Error: Song not in list.");
            printSongList();
            return;
         }
         Queue<String> pointer = songQueue;
         Queue<String> n = new LinkedList<String>();
         String temp = "";
         while(!pointer.isEmpty())
         {
            temp = pointer.remove();
            if(!temp.equals(d))
               n.add(temp);
         }
         songQueue = n;
         printSongList();
      }
   }

   public static void printSongList()
   {
      System.out.println("Your music queue: " + songQueue.toString());
   }
   
   public static Queue<String> getQueue()
   {
      return songQueue;
   }
   
   public static boolean inQueue(String str)
   {
      Queue<String> temp = songQueue;
      String re = "";
      boolean ret = false;
      while(!temp.isEmpty())
         re += temp.remove() + " - ";
      String[] ar = re.split(" - ");
      for(String s1 : ar)
         temp.add(s1);
      while(!temp.isEmpty())
         if(temp.remove().equals(str))
            ret = true;
      for(String s1 : ar)
         temp.add(s1);
      return ret;
   }
}

/*********************************************

 Your music queue: [Really Love, Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Really Love
 Your music queue: [Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Uptown Funk
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 Delete which song (exact match)?  Alright
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Traveller]
 Delete which song (exact match)?  xxx
 Error:  Song not in list.
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  a
 Song to add? Girl Crush
 Your music queue: [Thinking Out Loud, Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Thinking Out Loud
 Your music queue: [Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Traveller
 Your music queue: [Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Girl Crush
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Empty queue!
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  q
 
 No more music today!

**********************************************/