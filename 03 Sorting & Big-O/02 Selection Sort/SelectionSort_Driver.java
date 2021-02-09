//Name: Aaliya Hussain     
//Date: 10/26/18
import java.util.*;
import java.io.*;
public class SelectionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Selection.sort(array);
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
   
      Selection.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
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
//*********************************************
//Name: Aaliya Hussain
//Date: 10/26/18
class Selection
{
   public static void sort(double[] array)
   {
      for(int k = array.length-1; k >= 0; k--)
         swap(array, findMax(array, k), k);
   }
   private static int findMax(double[] array, int upper)//"upper" controls where the inner														//loop of the Selection Sort ends
   {
      double temp = array[0];
      int pos = 0;
      for(int k = 1; k <= upper; k++)
         if(array[k] > temp)
         {
            temp = array[k];
            pos = k;
         }
      return pos;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }   	
   
	/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
    public static void sort(Comparable[] array)
   {
      for(int k = array.length-1; k >= 0; k--)
         swap(array, findMax(array, k), k);
   }
   @SuppressWarnings("unchecked")
    public static int findMax(Comparable[] array, int upper)
   {
      Comparable temp = array[0];
      int pos = 0;
      for(int k = 0; k <= upper; k++)
         if(array[k].compareTo(temp) > 0)
         {
            temp = array[k];
            pos = k;
         }
      return pos;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}

