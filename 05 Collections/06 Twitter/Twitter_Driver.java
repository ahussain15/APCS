//Name: Aaliya Hussain
//Date: 12/18/18

import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.*;
import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.7.jar

public class Twitter_Driver
{
   private static PrintStream consolePrint;

   public static void main (String []args) throws TwitterException, IOException
   {
      consolePrint = System.out; // this preserves the standard output so we can get to it later      
   
      // PART III - Connect
      // set classpath, edit properties file
          
      TJTwitter bigBird = new TJTwitter(consolePrint);
      
   	// Part III - Tweet
      // Create and set a String called message below
   	// Uncomment this line to test, but then recomment so that the same
   	// tweet does not get sent out over and over.
   
      /*
      String message="Goooooooooooooooooooooooooooooo Colonials!";
      bigBird.tweetOut(message);
      */
       
   
      // PART III - Test
      // Choose a public Twitter user's handle 
     
      Scanner scan = new Scanner(System.in);
      consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
      String twitter_handle = scan.next();
       
      // Find and print the most popular word they tweet 
      while (!twitter_handle.equals("done"))
      {
         bigBird.queryHandle(twitter_handle);
         consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.getMostPopularWord()+ ".");
         consolePrint.println("The word appears " + bigBird.getFrequencyMax() + " times.");
         consolePrint.println();
         consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
         twitter_handle = scan.next();
      }
      
      // PART IV
      bigBird.investigate();
      
   }        
}        
      
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private int numberOfTweets; 
   private List<String> terms;
   private String popularWord;
   private int frequencyMax;
  
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      // Connects to Twitter and performs authorizations.
      twitter = TwitterFactory.getSingleton(); 
      consolePrint = console;
      statuses = new ArrayList<Status>();
      terms = new ArrayList<String>();
   }
	
   public List<String> getTerms()
   {
      return terms;
   }
	
   public int getNumberOfTweets()
   {
      return numberOfTweets;
   }
	
   public String getMostPopularWord()
   {
      return popularWord;
   }
	
   public int getFrequencyMax()
   {
      return frequencyMax;
   }
	   
  /******************  Part III - Tweet *******************/
  /** 
   * This method tweets a given message.
   * @param String  a message you wish to Tweet out
   */
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);
   }

   
  /******************  Part III - Test *******************/
  /** 
   * This method queries the tweets of a particular user's handle.
   * @param String  the Twitter handle (username) without the @sign
   */
   @SuppressWarnings("unchecked")
   public void queryHandle(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      terms.clear();
      fetchTweets(handle);
      splitIntoWords();	
      removeCommonEnglishWords();
      sortAndRemoveEmpties();
      mostPopularWord(); 
   }
	
   /** 
    * This method fetches the most recent 2,000 tweets of a particular user's handle and 
    * stores them in an arrayList of Status objects.  Populates statuses.
    * @param String  the Twitter handle (username) without the @sign
    */
   public void fetchTweets(String handle) throws TwitterException, IOException
   {
      // Creates file for dedebugging purposes
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); 
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      numberOfTweets = statuses.size();
      fileout.println("Number of tweets = " + numberOfTweets);
   }   

   /** 
    * This method takes each status and splits them into individual words.   
    * Store the word in terms.  
    */
   public void splitIntoWords()
   {
      String[] temp;
      for(int k = 0; k < statuses.size(); k++)
      {
         temp = removePunctuation(statuses.get(k).getText().trim()).split(" ");
         for(int j = 0; j < temp.length; j++)
            terms.add(temp[j]);
      }
   }

   /** 
     * This method removes common punctuation from each individual word.
     * This method changes everything to lower case.
     * Consider reusing code you wrote for a previous lab.     
     * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
     * @ param String  the word you wish to remove punctuation from
     * @ return String the word without any punctuation, all lower case  
     */
   public String removePunctuation( String s )
   {
      if(s.equals(""))
         return s;
      String punct = "[.,\"!?$:;]";
      String ret = s.trim().toLowerCase();
      String[] temp = ret.split(punct);
      ret = "";
      for(String k : temp)
         ret += k;
      return ret;   
   }

   /** 
    * This method removes common English words from the list of terms.
    * Remove all words found in commonWords.txt  from the argument list.    
    * The count will not be given in commonWords.txt. You must count the number of words in this method.  
    * This method should NOT throw an excpetion.  Use try/catch.   
    */
   @SuppressWarnings("unchecked")
   public void removeCommonEnglishWords()
   {  
      Scanner infile;
      try
      {
         infile = new Scanner(new File("commonWords.txt"));
         int numWords = 0;
         while(infile.hasNext())
         {
            numWords++;
            infile.next();
         }      
         String[] words = new String[numWords];
         infile = new Scanner(new File("commonWords.txt"));
         int c = 0;
         while(infile.hasNext())
         {
            words[c] = infile.next();
            c++;
         }
         ListIterator<String> li = terms.listIterator();
         while(li.hasNext())
            if(isPresent(words, li.next()))
               li.remove(); 
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found.");
      }
   }
   private boolean isPresent(String[] ar, String str)
   {
      for(int k = 0; k < ar.length; k++)
         if(ar[k].equalsIgnoreCase(str))
            return true;
      return false;
   }

   /** 
    * This method sorts the words in terms in alphabetically (and lexicographic) order.  
    * You should use your sorting code you wrote earlier this year.
    * Remove all empty strings while you are at it.  
    */
   @SuppressWarnings("unchecked")
   public void sortAndRemoveEmpties()
   {
      sort(terms);
      ListIterator<String> li = terms.listIterator();
      while(li.hasNext())
         if(li.next().equals(""))
            li.remove();
   }
   
   private void sort(List<String> array)
   {
      int maxPos;
      for(int k = 0; k < array.size(); k++)		
      {
         maxPos = findMax(array, array.size() - k - 1);
         swap(array, maxPos, array.size() - k - 1);
      }
   }
   
   private int findMax(List<String> array, int upper)
   {
      int maxPos = 0;
      for(int j = 1; j <= upper; j++)			
         if(array.get(j).compareTo(array.get(maxPos)) > 0)	
            maxPos = j;					
      return maxPos;
   }
   
   private void swap(List<String> array, int a, int b)
   {
      String temp = array.get(a);				
      array.set(a, array.get(b));
      array.set(b, temp);
   }
   
   /** 
	 * This method calculates the word that appears the most times
    * Consider case - should it be case sensitive?  The choice is yours.
    * @post will popopulate the frequencyMax variable with the frequency of the most common word 
    */
   @SuppressWarnings("unchecked")
   public void mostPopularWord()
   {
      int temp = 0;
      int mpop = 0;
      String current = terms.get(0);
      String max = "";
      for(int k = 0; k < terms.size(); k++)
      {
         if(!terms.get(k).equalsIgnoreCase(current))
         {
            current = terms.get(k);
            temp = 0;
         }
         temp++;
         if(temp > mpop)
         {
            max = current;
            mpop = temp;
         }
      }
      frequencyMax = mpop;
      popularWord = max;
   }

  /******************  Part IV *******************/
   ArrayList<String> diseases;
  ArrayList<GeoLocation> locations;
  int locationIndex;
  int radiusInt;
  String dateString;
  int count = 0;
  
   public void investigate ()
   {
      //Enter your code here
      input();
      locations = getGeos();
      diseases = getDiseases();
      
      for( String disease : diseases){
         count = investigateOne(locations.get(locationIndex),disease,radiusInt,dateString);
         System.out.println(disease + " " + count);
      }
   }
     
  /** 
   * This method determines how many people in Arlington, VA 
   * tweet about the Miami Dolphins.  Hint:  not many. :(
   */
   
   public int input(){
      Scanner scan = new Scanner(System.in);
      consolePrint.print("New York City, New York: 1" + "\n" +"Miami, Florida: 2" + "\n" +  "Seattle, Washington: 3" + "\n"+  "Washington DC: 4" + "\n" + "Houston, Texas: 5" + "\n" + "Please choose a location (1-5): ");
      int locationNum = Integer.parseInt(scan.next());
      locationIndex =  locationNum-1;
      consolePrint.print("Input Radius(1-25): ");
      int radius = Integer.parseInt(scan.next());
      radiusInt = radius;
      consolePrint.print("Input date in this format(YYYY-MM-DD): ");
      String date = scan.next().trim();
      dateString = date;
      return locationNum-1;
   }
   
   
   public int investigateOne(GeoLocation coordinates, String disease, int radius, String date){
      int tempInt= 0;
      Query query = new Query(disease);
      query.setCount(100);
      query.setGeoCode(coordinates, radius, Query.MILES);
      query.setSince(date);
      try {
         QueryResult result = twitter.search(query);
         return result.getTweets().size() ;

      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      return tempInt;
   }
   
   private ArrayList<String> getDiseases()
   {
      Scanner infile;
      try
      {
         infile = new Scanner(new File("diseases"));
         ArrayList<String> ar = new ArrayList<String>();
         ListIterator<String> li = ar.listIterator();
         while(infile.hasNext())
            li.add(infile.nextLine());
         return ar;
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found!");
      }
      return null;
   }
   
   private ArrayList<GeoLocation> getGeos()
   {
      Scanner infile;
      try
      {
         infile = new Scanner(new File("locations.txt"));
         String temp1 = "";
         while(infile.hasNext())
            temp1 = temp1 + infile.nextLine() + "; ";
         String[] temp2 = temp1.split("[,;]");
         ArrayList<Double> coors = new ArrayList<Double>();
         ListIterator<Double> li = coors.listIterator();
         for(int k = 0; k < temp2.length; k++)
            if(!temp2[k].equals("") && !temp2[k].equals(" "))
               li.add(Double.parseDouble(temp2[k]));
         ArrayList<GeoLocation> geos = new ArrayList<GeoLocation>();
         ListIterator<GeoLocation> li2 = geos.listIterator();
         for(int k = 0; k < coors.size(); k += 2)
            li2.add(new GeoLocation(coors.get(k), coors.get(k+1)));
         return geos;
      }
      catch(FileNotFoundException e)
      {
         System.out.println("File not found!");
      }
      return null;
   }
 
}