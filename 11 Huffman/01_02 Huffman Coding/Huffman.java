//Name: Aaliya Hussain        
//Date: 4/13/19
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
      HashMap<String, Integer> ft = new HashMap<>();
      String tl = "";
      for(int k = 0; k < message.length(); k++)
      {
         tl = message.substring(k, k+1);
         if(!ft.containsKey(tl))
            ft.put(tl, new Integer(1));
         else
            ft.put(tl, new Integer(ft.get(tl)+1));
      }
      PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<>();
      for(String s : ft.keySet())
         pq.add(new HuffmanTreeNode(s, ft.get(s)));
      HuffmanTreeNode l = null;
      HuffmanTreeNode r = null;
      HuffmanTreeNode p = null;
      while(pq.size() > 1)
      {
         l = pq.remove();
         r = pq.remove();
         p = new HuffmanTreeNode("", l.getFreq()+r.getFreq(), l, r);
         pq.add(p);
      }
      HuffmanTreeNode t = null;
      if(ft.size() > 1)
         t = pq.remove();
      else
         t = new HuffmanTreeNode("", ft.get(message), new HuffmanTreeNode(message, ft.get(message)), null);
      PrintStream sc = new PrintStream(new FileOutputStream("scheme." + middlePart + ".txt"));
      PrintStream me = new PrintStream(new FileOutputStream("message." + middlePart + ".txt"));
      HashMap<String, String> sch = new HashMap<>();
      for(String s : ft.keySet())
      {
         sc.println(s+path(t, "", s));
         sch.put(s, path(t, "", s));
      }
      String tm = "";
      for(int k = 0; k < message.length(); k++)
         tm += sch.get(message.substring(k, k+1));
      me.print(tm);
   }
   private static String path(HuffmanTreeNode root, String p, String l)
   {
      if(root == null)
         return "";
      if(root.getLetter().equals(l))
         return p;
      return path(root.getLeft(), p+"0", l)+path(root.getRight(), p+"1", l);
   }
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private String letter;
   private Integer freq;
   private HuffmanTreeNode left;
   private HuffmanTreeNode right;
   
   public HuffmanTreeNode(String l, Integer f)
   {
      letter = l;
      freq = f;
   }
   
   public HuffmanTreeNode(String l, Integer f, HuffmanTreeNode lf, HuffmanTreeNode rg)
   {
      letter = l;
      freq = f;
      left = lf;
      right = rg;
   }
   
   public String getLetter()
   {
      return letter;
   }
   
   public Integer getFreq()
   {
      return freq;
   }  
   
   public HuffmanTreeNode getLeft()
   {
      return left;
   }
   
   public HuffmanTreeNode getRight()
   {
      return right;
   }
   
   public void setLetter(String l)
   {
      letter = l;
   }
   
   public void setFreq(Integer f)
   {  
      freq = f;
   }
   
   public void setLeft(HuffmanTreeNode l)
   {
      left = l;
   }
   
   public void setRight(HuffmanTreeNode r)
   {
      right = r;
   }
   
   public int compareTo(HuffmanTreeNode other)
   {
      int mf = freq;
      int of = other.freq;
      if(mf > of)
         return 1;
      if(mf < of)
         return -1;
      return 0;
   }
}
