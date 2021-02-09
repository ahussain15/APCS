 //Name: Aaliya Hussain   
 //Date: 10/26/18
import java.util.*;
import java.io.*;
public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) ) //original had array rather than arrayStr
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
         System.out.print(abc+" ");
   }
   public static boolean isAscending(double[] a)
   {
      for(int k = 0; k < a.length-1; k++)
         if(a[k] > a[k+1])
            return false;
      return true;
   }
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      for(int k = 0; k < a.length-1; k++)
         if(a[k].compareTo(a[k+1]) > 0)
            return false;
      return true;
   }
}

//**********************************************************
 //Name: Aaliya Hussain      
 //Date: 10/26/18
class Insertion
{
   public static double[] sort(double[] array)
   { 
      int s = 0;
      double swap = 0;
      double temp = 0;
      for(int k = 0; k < array.length; k++)
      {
         s = shift(array, k, array[k]);
         temp = array[k];
         if(s > 0 && k > 0)
            for(int j = 0; j < s; j++)
            {
               swap = temp;
               array[k-j] = array[k-j-1];
               array[k-j-1] = swap;
            }
      }
      return array;
   }
   private static int shift(double[] array, int index, double value)
   {
      int s = 0;
      for(int k = index; k >= 0; k--)
         if(array[k] > value)
            s++;
      return s;
   }
   @SuppressWarnings("unchecked")
    public static Comparable[] sort(Comparable[] array)
   { 
      int s = 0;
      Comparable swap = array[0];
      Comparable temp = array[0];
      for(int k = 0; k < array.length; k++)
      {
         s = shift(array, k, array[k]);
         temp = array[k];
         if(s > 0 && k > 0)
            for(int j = 0; j < s; j++)
            {
               swap = temp;
               array[k-j] = array[k-j-1];
               array[k-j-1] = swap;
            }
      }
      return array;
   }
   @SuppressWarnings("unchecked")
    private static int shift(Comparable[] array, int index, Comparable value)
   {
      int s = 0;
      for(int k = index; k >= 0; k--)
         if(array[k].compareTo(value) > 0)
            s++;
      return s;
   }
}
