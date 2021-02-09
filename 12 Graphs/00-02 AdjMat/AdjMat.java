//Name: Aaliya Hussain
//Date: 4/25/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 * Graph1 WarshallDriver,
 * and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   
   int edgeCount();
   List<Integer> getNeighbors(int source);
   List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name-->index (for Warshall & Floyd)
   
   public AdjMat(int s)
   {
      grid = new int[s][s];
      for(int r = 0; r < s; r++)
         for(int c = 0; c < s; c++)
            grid[r][c] = 0;
      vertices = new TreeMap<>();
   }
   
   public void addEdge(int source, int target)
   {
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target)
   {
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to)
   {
      return (grid[from][to] != 9999)&&(grid[from][to] != 0);
   }
   public String toString()
   {
      String ret = "";
      for(int r = 0; r < grid.length; r++)
         for(int c = 0; c < grid.length; c++)
         {
            ret += grid[r][c] + " ";
            if(c == grid.length-1)
               ret += "\n";
         }
      return ret;
   }
   public int edgeCount()
   {
      int ec = 0;
      for(int r = 0; r < grid.length; r++)
         for(int c = 0; c < grid.length; c++)
            if(isEdge(r, c))
               ec++;
      return ec;
   }
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> l = new ArrayList<>();
      for(int c = 0; c < grid.length; c++)
         if(isEdge(source, c))
            l.add(new Integer(c));
      return l;
   }
   public boolean isEdge(String from, String to)
   {
      int r = vertices.get(from);
      int c = vertices.get(to);
      return isEdge(r, c);
   }
   public Map<String, Integer> getVertices()
   {
      return vertices;
   }    
   public void readNames(String fileName) throws FileNotFoundException
   {
      Scanner s = new Scanner(new File(fileName));
      int ms = Integer.parseInt(s.nextLine());
      for(int k = 0; k < ms; k++)
         vertices.put(s.nextLine().trim(), k);
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner s = new Scanner(new File(fileName));
      int ms = Integer.parseInt(s.nextLine());
      String[] tl;
      int r = 0;
      while(s.hasNext())
      {
         tl = s.nextLine().trim().split(" ");
         for(int k = 0; k < ms; k++)
            if(!tl[k].equals("0"))
               grid[r][k] = Integer.parseInt(tl[k]);
         r++;
      }
      
   }
   public void displayVertices()
   {
      for(String s : vertices.keySet())
         System.out.println(vertices.get(s) + "-" + s);
      System.out.println();
   }
   public void allPairsReachability()  // Warshall's Algorithm
   {
      int[][] w = grid;
      for(int a = 0; a < grid.length; a++)
         for(int b = 0; b < grid.length; b++)
            if(isEdge(a, b))
               for(int c = 0; c < grid.length; c++)
                  if(isEdge(b, c))
                     w[a][c] = 1;
   }
   public List<String> getReachables(String from)
   {
      allPairsReachability();
      List<String> l = new LinkedList<>();
      for(String s : vertices.keySet())
         if(isEdge(from, s))
            l.add(s);
      return l;
   }
   public int getCost(int from, int to)
   {
      return grid[from][to];
   }
   public int getCost(String from, String to)
   {
      int r = vertices.get(from);
      int c = vertices.get(to);
      return grid[r][c];
   }
   public void allPairsWeighted()
   {
      int[][] w = grid;
      for(int a = 0; a < grid.length; a++)
         for(int b = 0; b < grid.length; b++)
               for(int c = 0; c < grid.length; c++)
                  w[b][c] = Math.min(w[b][a] + w[a][c], w[b][c]);
   }
}
