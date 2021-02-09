//Name: Aaliya Hussain
//Date: 2/26/19

import java.util.*;
import java.io.*;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberOfWidgets = 10;
   public static void main( String[] args ) 
   {
   // Day one 
      tree = new BSTobject<String>();
      tree = build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
   	
   // Day two
   // Your assignment the second day is to finish the BSTobject class.  
   // Specifically, prompt the user for a string, put the characters into 
   // a BST, call toString on this tree, and print the size of the tree.
      tree = new BSTobject<String>();
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
      tree = build(tree, s);
      System.out.print(tree.toString());
      System.out.println("Size: " + tree.size());
   // Day two, Widgets			
   // Next, fill your BST with 10 Widget objects from widgets.txt.  Display 
   // the tree. Then prompt the user to enter cubits and hands.  If the tree 
   // contains that Widget, delete it, of course restoring the BST order. 
   // Display the new tree and its size. If the tree does not contain that 
   // Widget, print "Not found".*/
      treeOfWidgets = new BSTobject<Widget>();
      treeOfWidgets = build(treeOfWidgets, new File("widgets.txt"));
      System.out.println(treeOfWidgets.toString());
      System.out.println("Size: " + treeOfWidgets.size());
      System.out.print("Input cubits: ");
      int c = Integer.parseInt(sc.nextLine());
      System.out.print("Input hands: ");
      int h = Integer.parseInt(sc.nextLine());
      if(treeOfWidgets.contains(new Widget(c, h)))
      {
         treeOfWidgets.delete(new Widget(c, h));
         System.out.print(treeOfWidgets.toString());
         System.out.println("Size: " + treeOfWidgets.size());
      }
      else
         System.out.println("Not found");
   // Day three -- AVL tree  -----------------------
   }
  
/* Build the tree for Strings, Day 1
 */
   public static BSTobject<String> build(BSTobject<String> tree, String str)
   {
      tree = new BSTobject<String>();
      for(int k = 0; k < str.length(); k++)
         tree.add(str.substring(k, k+1));
      return tree;
   }

/* Build the tree for Widgets, Day 2
 */
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.out.println("File not found.");
      }        
      tree = new BSTobject<Widget>();
      for(int i = 0; i < numberOfWidgets; i++)   
      {
         if(infile.hasNext())
            tree.add(new Widget(Integer.parseInt(infile.next()), Integer.parseInt(infile.next())));
      }
      return tree;
   }
}
interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );             //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );          //returns the object, not a Node<E>
   public int size();
   public String toString();
}

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
// Declare 2 fields
   private Node<E> root;
   private int size; 

// Create a default constructor
   public BSTobject()
   {
      root = null;
      size = 0;
   }

//instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }

//recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t == null)
         return new Node(obj);
      if(((Comparable)obj).compareTo((Comparable)t.getValue()) < 0)
         t.setLeft(add(t.getLeft(), obj));
      else
         t.setRight(add(t.getRight(), obj));
      return t;
   }

/* Implement the interface here.  Use TreeNode as an example,
 * but root is a field. You need add, contains, isEmpty, 
 * delete, size, and toString.  
 */
   public E delete(E target)
   {
      size--;
      Node<E> current = root;
      Node<E> parent = null;
      while(current !=null)
      {
         int compare = ((Comparable)target).compareTo((Comparable)current.getValue());
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
         return target;
      }
      //Case 1 b.
      if(current.getLeft() == null && current.getRight() == null && current == root)
      {
         root = current = null;
         return target;
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
         return target;
      }
      //Case 2 d.
      if(current.getLeft() == null && current.getRight() != null && current == root)
      {
         root = current = current.getRight();
         parent = null;
         return target;
      }
      //Case 3
      if(current.getLeft() != null && current.getRight() != null)
      {
         Node<E> m = max(current.getLeft());
         while(parent.getLeft() != m && parent.getRight() != m)
         {
            int compare = ((Comparable)parent.getValue()).compareTo((Comparable)m.getValue());
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
      return target;
   }
   public Node<E> max(Node<E> t)
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t;
      return max(t.getRight());
   }
   public boolean contains(E el)
   {
      return find(root, el);
   }
   public boolean find(Node<E> n, E el)
   {
      if(n == null)
         return false;
      if(((Comparable)el).compareTo((Comparable)n.getValue()) < 0)
         return find(n.getLeft(), el);
      if(((Comparable)el).compareTo((Comparable)n.getValue()) > 0)
         return find(n.getRight(), el);
      return true;
   }
   public boolean isEmpty()
   {
      return (size == 0);
   }
   public int size()
   {
      return size;
   }
   public String toString()
   {
      return display(root, 0);
   }
   public String display(Node<E> t, int level)
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
 
/* Private inner class 
 */  
   private class Node<E>
   {
   // 3 fields 
      private E e;
      private Node<E> left, right;
   
   // 2 constructors, one-arg and three-arg
      public Node(E el)
      {
         this(el, null, null);
      }
      public Node(E el, Node<E> l, Node<E> r)
      {
         e = el;
         left = l;
         right = r;
      }
   
   //methods--Use TreeNode as an example. See Quick Reference Guide.
      public E getValue()
      {
         return e;
      }
      public Node<E> getLeft()
      {
         return left;
      }
      public Node<E> getRight()
      {
         return right;
      }
      public void setValue(E el)
      {
         e = el;
      }
      public void setLeft(Node<E> l)
      {
         left = l;
      }
      public void setRight(Node<E> r)
      {
         right = r;
      }
   
   }
}

