//Name: Aaliya Hussain
//Date: 2/14/19

import java.util.*;

/* Practice with a Binary Search Tree. Uses TreeNode.
 * Prompt the user for an input string.  Build a BST 
 * from the letters. Display it as a sideways tree.  
 * Prompt the user for a target and delete that node, 
 * if it exists. Show the updated tree.
 */
public class BinarySearchTreeDelete
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
                           //Case 1a:     ECSBPWANR 
                           //Case 1b:     N
                           //Case 2a:     SNTPOR    
                           //Case 2b:     HBRNVJSZIK  
                           //case 2c:     NFAKG
                           //case 2d:     NSPQX
                           //Case 3.a:    DBNACFSEJHM 
                           //Case 3.b:    DBNACFSEJH 
                           //on the handout: HDJAGKFEOLTMNSU
      TreeNode root = buildTree( null, s );
      System.out.println( display(root, 0) );
      System.out.print("Delete? ");
      String target = sc.next();
      if( contains( root, target ) )
      {
         root = delete( root, target );
         System.out.println("\n" + target+" deleted.");
      }
      else
         System.out.println("\n" + target+" not found");
      System.out.println( display(root, 0) );
   }
   
   public static TreeNode buildTree(TreeNode t, String s)
   {
      for(int k = 0; k < s.length(); k++)
         t = insert(t, "" + s.charAt(k));
      return t;
   }
	
   /* Recursive algorithm to build a BST:  if the node is 
    * null, insert the new node.  Else, if the item is less, 
    * set the left node and recur to the left.  Else, if the 
    * item is greater, set the right node and recur to the right.   
	 */
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"") <= 0)
         t.setLeft(insert(t.getLeft(), s));
      else
         t.setRight(insert(t.getRight(), s));
      return t;
   }
   
   private static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current != null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   
   public static TreeNode delete(TreeNode current, String target)
   {
      TreeNode root = current;  //don't lose the root!
      TreeNode parent = null;
      while(current !=null)
      {
         int compare = target.compareTo((String)current.getValue());
         if(compare < 0)
         {
            parent = current;
            current = parent.getLeft();
            continue;
         }
         if(compare > 0)
         {
            parent = current;
            current = parent.getRight();
            continue;
         }
         if(compare == 0)
            break;
      }
      if(parent == null)
         parent = current;
      //Case 1 a.
      if(current.getLeft() == null && current.getRight() == null && current != root)
      {
         if(parent.getLeft() == current)
            parent.setLeft(null);
         if(parent.getRight() == current)
            parent.setRight(null);
         current = null;
         return root;
      }
      //Case 1 b.
      if(current.getLeft() == null && current.getRight() == null && current == root)
      {
         root = current = null;
         return root;
      }
      //Case 2 a.
      if(current.getLeft() != null && current.getRight() == null && current != root)
      {
         if(parent.getLeft() == current)
            parent.setLeft(current.getLeft());
         if(parent.getRight() == current)
            parent.setRight(current.getLeft());
      }
      //Case 2 b.
      if(current.getLeft() == null && current.getRight() != null && current != root)
      {
         if(parent.getLeft() == current)
            parent.setLeft(current.getRight());
         if(parent.getRight() == current)
            parent.setRight(current.getRight());
      }
      //Case 2 c.
      if(current.getLeft() != null && current.getRight() == null && current == root)
      {
         root = current = current.getLeft();
         parent = null;
         return root;
      }
      //Case 2 d.
      if(current.getLeft() == null && current.getRight() != null && current == root)
      {
         root = current = current.getRight();
         parent = null;
         return root;
      }
      //Case 3
      if(current.getLeft() != null && current.getRight() != null)
      {
         TreeNode m = max(current.getLeft());
         while(parent.getLeft() != m && parent.getRight() != m)
         {
            int compare = ((String)parent.getValue()).compareTo((String)m.getValue());
            if(compare > 0)
               parent = parent.getLeft();
            if(compare < 0)
               parent = parent.getRight();
            if(compare == 0)
               break;      
         }
         current.setValue(m.getValue());
         //Case 3 a.
         if(m.getLeft() == null && m.getRight() == null)
         {
            parent.setRight(null);
         }
         //Case 3 b.
         if(parent == current)
         {
            if(parent.getLeft() == m)
               parent.setLeft(m.getLeft());
            if(parent.getRight() == m)
               parent.setRight(m.getRight());
         }
         else
            parent.setRight(m.getLeft());
         m = null;
      }
      return root;  //never reached
   }
   
   public static TreeNode max(TreeNode t)
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t;
      return max(t.getRight());
   }
}