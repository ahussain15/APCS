//Name: Aaliya Hussain
//Date: 3/27/19

import java.text.DecimalFormat;

public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      SIZE = 9;  
      double heap[] = {-1,7.2, 3.4, 6.4, 9.9};
      makeHeap(heap, SIZE);
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      /*SIZE = 100;
      heap = new double[SIZE + 1];
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      int ts = array.length-1;
      while(!isSorted(array) && ts > 0)
      {
         swap(array, 1, ts);
         heapDown(array, 1, ts);
         ts--;
      }
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
      if((2*k) >= size || (2*k+1) >= size)
         return;
      double l = Math.max(array[k], Math.max(array[2*k], array[2*k+1]));
      if(l == array[2*k])
      {
         swap(array, k, 2*k);
         heapDown(array, 2*k, size);
      }
      if(l == array[2*k+1])
      {
         swap(array, k, 2*k+1);
         heapDown(array, 2*k+1, size);
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      for(int k = 1; k < arr.length-1; k++)
         if(arr[k] > arr[k+1])
            return false;
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      DecimalFormat df = new DecimalFormat("0.00");
      for(int k = 1; k < array.length; k++)
         array[k] = Double.parseDouble(df.format(Math.random()*100+1));
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      for(int k = size/2; k >= 1; k--)
         heapDown(array, k, size);
   }
}

