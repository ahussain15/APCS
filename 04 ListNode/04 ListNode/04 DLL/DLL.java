//Name: Aaliya Hussain
//Date: 11/27/18

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head.getNext();
      for(int k = 0; k < index; k++)
         pointer = pointer.getNext();
      DLNode temp = new DLNode(obj, pointer.getPrev(), pointer);
      pointer.getPrev().setNext(temp);
      pointer.setPrev(temp);
      size++;          
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head.getNext();
      for(int k = 0; k < index; k++)
         pointer = pointer.getNext();
      return pointer.getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head.getNext();
      for(int k = 0; k < index; k++)
         pointer = pointer.getNext();
      Object temp = pointer.getValue();
      pointer.setValue(obj);
      return temp;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head.getNext();
      for(int k = 0; k < index; k++)
         pointer = pointer.getNext();
      Object temp = pointer.getValue();
      pointer.getPrev().setNext(pointer.getNext());
      pointer.getNext().setPrev(pointer.getPrev());
      size--;
      return temp;
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      head.setNext(new DLNode(obj, head, head.getNext()));
      head.getNext().getNext().setPrev(head.getNext());
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      DLNode pointer = head.getNext();
      for(int k = 1; k < size; k++)
         pointer = pointer.getNext();
      pointer.setNext(new DLNode(obj, pointer, head));
      pointer.getNext().setPrev(pointer);
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return get(size-1);
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head.getNext() == head)
         return null;
      Object temp = head.getNext().getValue();
      remove(0);
      return temp;
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head.getNext() == head)
         return null;
      Object temp = getLast();
      remove(size-1);
      return temp;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String ret = "[" + head.getNext().getValue() + ", ";
      for(int k = 1; k < size-1; k++)
         ret += get(k) + ", ";
      ret += get(size-1) + "]";
      return ret;
   }
}