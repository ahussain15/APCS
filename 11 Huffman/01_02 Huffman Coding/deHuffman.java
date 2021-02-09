//Name: Aaliya Hussain              
//Date: 4/9/19
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode temp = new TreeNode(null);
      TreeNode t = temp;
      String line = "";
      String ch = "";
      String bit = "";
      while(huffLines.hasNext())
      {
         line = huffLines.nextLine();
         ch = line.substring(0, 1);
         for(int k = 1; k < line.length(); k++)
         {
            bit = line.substring(k, k+1);
            if(bit.equals("0"))
            {
               if(t.getLeft() == null)
                  t.setLeft(new TreeNode(""));
               t = t.getLeft();
            }
            if(bit.equals("1"))
            {
               if(t.getRight() == null)
                  t.setRight(new TreeNode(""));
               t = t.getRight();
            }
            if(k == line.length()-1)
            {
               t.setValue(ch);
               t = temp;
            }
         }
         
      }
      return t;
   }
   
   public static String dehuff(String text, TreeNode root)
   {
      String ret = "";
      String bit = "";
      TreeNode t = root;
      for(int k = 0; k < text.length() && t != null; k++)
      {
         bit = text.substring(k, k+1);
         if(!t.equals("") && t.getLeft() == null && t.getRight() == null)
         {
            ret += t.getValue();
            t = root;
         }
         if(k == text.length()-1)
         {
            if(bit.equals("0"))
               t = t.getLeft();
            if(bit.equals("1"))
               t = t.getRight();
            ret += t.getValue();
         }
         if(bit.equals("0"))
            t = t.getLeft();
         if(bit.equals("1"))
            t = t.getRight();
      }
      return ret;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
