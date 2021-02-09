//Name: Aaliya Hussain
//Date: 3/6/19

/* 
   Assignment:  This hashing program results in collisions.
   You are to implement three different collision schemes: linear 
   probing, rehashing, and chaining.  Then implement a search 
   algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  "));//20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));               //15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Rehashing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: 
            table = new HashtableRehash(arrayLength);
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems && i < arrayLength; i++)
         table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
      while( itemNumber != -1 )
      {
         String key = "Item" + itemNumber;
         int index = table.indexOf(key); 
         if( index >= 0)    //found it
            System.out.println(key + " found  at index " + index);
         else
            System.out.println(key + " not found!");
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));                           
      } 
      System.exit(0);
   }
}

/*********************************************/
interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}
/***************************************************/ 

class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
  
   public HashtableLinearProbe(int size)//constructor
   {
      array = new Object[size];             
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int linearProbe(int index)
   {      
      for(int k = index+1; k < array.length; k++)
         if(array[k] == null)
            return k;
      for(int k = 0; k < index; k++)
         if(array[k] == null)
            return k;
      return index;
   }
   
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a linear probe manner
         {
            if(index < array.length-1)
            {
               index++;
               System.out.println("Looking at index " + index);
            }
            else
               break;
         }
      }
      index = Math.abs(obj.hashCode() % array.length);
      for(int k = 0; k < index; k++)
         if(array[k].equals(obj))
            return k;
      //not found
      return -1;
   }
}

/*****************************************************/
class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant;  
   
   public HashtableRehash(int size) //constructor
   {
      array = new Object[size];    
      int i = 1;
      while(!relPrime(size, i))
         i++;
      constant = i;                  
   }
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index] == null)  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         while(array[index] != null)
            index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int rehash(int index)
   {
      return Math.abs((index+constant) % array.length);
   }
   
   public boolean relPrime(int a, int b)
   {
      int c = 0;
      int d = 0;
      if(a > b)
      {
         c = a;
         d = b;
      }
      else
      {
         c = b;
         d = a;
      }
      for(int k = 1; k < c; k++)
         if(c % (d*k) == 0)
            return false;
      return true;
   }
   
   public  int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      int i = 0;
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            if(i < array.length)
            {
               index = rehash(index);
               System.out.println("Looking at index " + index);
               i++;
            }
            else
               break;
         }
      }
      //not found
      return -1;
   }
}

/********************************************************/
class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   
   public HashtableChaining(int size)
   {
      //instantiate the array
      array = new LinkedList[size];
      //instantiate the LinkedLists
      for(int k = 0; k < size; k++)
         array[k] = new LinkedList();
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  
   
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      int i = 0;
      if( !array[index].isEmpty() )
      {
         if(array[index].get(i).equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            while(array[index].size() > i)
               if(array[index].get(i).equals(obj))
                  return index;
               else
                  i++;
         }
      }
      //not found
      return -1;
   }
}