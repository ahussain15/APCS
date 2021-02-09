//Name: Aaliya Hussain
//Date: 12/4/18

/**
 * Implements the List interface
 * with a backing array of Objects.
 * @override toString()
 */       
public class TJArrayList_Driver
{  
   public static void main(String [] args)
   {
      TJArrayList myList = new TJArrayList();	
   
      myList.add("Banana");
      myList.add("Fig");
      myList.add(2, "Cucumber");
      myList.add(3, "Dates");
      myList.add(0, "Apple");
      System.out.println(myList);
      System.out.println("Size is " + myList.size());
      try
      {
         myList.add(12, "Peach");
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
      System.out.println("Get 2: " + myList.get(2));
      System.out.print("Set at 2: ");
      Object previous = myList.set(2, "Cherry");
      System.out.println(previous +" " +myList);
      Object obj = myList.remove(2);
      System.out.println("Removed " + obj+ " from " + myList);
      System.out.println("Size is " + myList.size());
      System.out.print("Add too many items: ");
      for(int i = 3; i <= 10; i++)
         myList.add(new Integer(i));
      System.out.println(myList);
      System.out.println("Size is " + myList.size());   	
      System.out.println("Contains \"Breadfruit\"?  " + myList.contains("Breadfruit"));
      System.out.println("Contains \"Banana\"?  " + myList.contains("Banana"));
   }
}
   
class TJArrayList
{
   private int size;							//stores the number of objects
   private Object[] myArray;
   
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = new Object[10];
      size = 0;
   }
   
   public int size()
   {
      return size;
   }
 	
   /**
    * Appends obj to end of list. 
    * Increases size.
    * Must be an O(1) operation when size < array.length 
    * and O(n) when it doubles the length of the array.
    * @return true
    */       
   public boolean add(Object obj)
   {
      if(myArray[myArray.length-1] == null)
      {
         myArray[size] = obj;
         size++;
      }
      else
      {
         Object[] temp = new Object[myArray.length*2];
         for(int k = 0; k < myArray.length; k++)
            temp[k] = myArray[k];
         myArray = temp;
         myArray[size] = obj;
         size++;
      }
      return true; 
   }
       
   /**
    * Inserts obj at position index. 
    * Increases size.
    */         
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      if(myArray[myArray.length-1] == null)
      {
         Object[] temp = new Object[size-index];
         for(int k = index; k < size; k++)
            temp[k-index] = myArray[k];
         myArray[index] = obj;
         for(int k = index+1; k <= size; k++)
            myArray[k] = temp[k-1-index];
         size++;
      }
      else
      {
         Object[] temp = new Object[myArray.length*2];
         for(int k = 0; k < myArray.length; k++)
            temp[k] = myArray[k];
         myArray = temp;
         Object[] temp2 = new Object[size-index];
         for(int k = index; k < size; k++)
            temp2[k-index] = myArray[k];
         myArray[index] = obj;
         for(int k = index+1; k <= size; k++)
            myArray[k] = temp2[k-1-index];
         size++;
      }
   }
   
   /**
    * Retrieves the obj at a given index.
    * @return obj at position index 
    */  
   public Object get(int index)
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return myArray[index];
   }
   
   /**
    * Replaces obj at position index. 
    * @return the original object (the one being replaced)
    */        
   public Object set(int index, Object obj)
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      Object temp = myArray[index];
      myArray[index] = obj;
      return temp;
   }
       
   /**
    * Removes node from position index.  
    * Shifts elements to the left. 
    * Decreases size.
    * @return the object at position index
    */       
   public Object remove(int index)
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      Object ret = myArray[index];
      Object[] temp = new Object[size-index-1];
      for(int k = index+1; k < size; k++)
         temp[k-index-1] = myArray[k];
      for(int k = index; k < size-1; k++)
         myArray[k] = temp[k-index];
      myArray[size-1] = null;
      size--;
      return ret;
   }
	
   /**
    * This method compares objects.
    * Must use .equals, not ==. 
    */     
   public boolean contains(Object obj)
   {
      for(int k = 0; k < size; k++)
         if(myArray[k].equals(obj))
            return true;
      return false;
   }
   
 
   /**
    * This method returns a String of objects in the array.
    * Includes the outer square projects and comma separators. 
    */   
   public String toString()
   {
      String temp = "[";
      for(int k = 0; k < size-1; k++)
         temp += myArray[k].toString() + ", ";
      temp += myArray[size-1];
      temp += "]";
      return temp;
   }
}

/*************************************

 [Apple, Banana, Fig, Cucumber, Dates]
 Size is 5
 java.lang.IndexOutOfBoundsException: Index: 12, Size: 5
 Get 2: Fig
 Set at 2: Fig [Apple, Banana, Cherry, Cucumber, Dates]
 Removed Cherry from [Apple, Banana, Cucumber, Dates]
 Size is 4
 Add too many items: [Apple, Banana, Cucumber, Dates, 3, 4, 5, 6, 7, 8, 9, 10]
 Size is 12
 Contains "Breadfruit"?  false
 Contains "Banana"?  true
 
 ************************************/