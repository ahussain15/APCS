//Name: Aaliya Hussain
//Date: 11/27/18

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");  
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      ListNode head = null;
      Scanner infile = new Scanner(f);
      String[] store = new String[n];
      for(int k = 0; k < n; k++)
         store[k] = infile.next();
      head = insert(head, store[n-1]);
      for(int k = 0; k < n-1; k++)
         head = insert(head, store[k]);
      return head; 
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      if(p == null)
      {
         p = new ListNode(obj, p);
         p.setNext(p);
      }
      else
      {
         ListNode n = new ListNode(obj, p);
         ListNode t = p;
         while(t.getNext().getValue() != p.getValue())
            t = t.getNext();
         t.setNext(n);
      }
      return p;
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      print(p);
      System.out.println();
      for(int k = 1; k < n; k++)
      {
         p = remove(p, count);
         print(p);
         System.out.println();
      }
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      ListNode t = p;
      if(count == 1)
      {
         t.setNext(t.getNext().getNext());
         return t;
      }
      t = t.getNext();
      for(int k = 1; k < count-1; k++)
         t = t.getNext();
      t.setNext(t.getNext().getNext());
      return t;
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      Object first = p.getValue();
      ListNode temp = p.getNext();
      if(first != null)
      {
         while(!first.equals(temp.getValue()))
         {
            System.out.print(temp.getValue() + " ");
            temp = temp.getNext();
         }
         System.out.print(p.getValue());
      }   
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      ListNode t = p.getNext();
      for(int k = 1; k < pos; k++)
         t = t.getNext();
      t.setValue(obj);
   }
}

