//Name: Aaliya Hussain 
//Date: 5/23/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 * Graphs4: DFS-BFS
 * and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addEdge(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
   
   public Vertex(String n)
   {
      name = n;
      adjacencies = new ArrayList<Vertex>();
   }
   
   public String toString()
   {
      String ret = name + " [";
      for(int k = 0; k < adjacencies.size(); k++)
         if(k == adjacencies.size()-1)
            ret = ret + adjacencies.get(k).getName() + "]";
         else
            ret = ret + adjacencies.get(k).getName() + " ";
      if(ret.indexOf("]") < 0)
         ret += "]";
      return ret;
   }
   public String getName()
   {
      return name;
   }
   public ArrayList<Vertex> getAdjacencies()
   {
      return adjacencies;
   }
   public void addEdge(Vertex v)
   {
      adjacencies.add(v);
   }
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   //List<Vertex> depthFirstRecur(String name);
   //List<Vertex> depthFirstRecur(Vertex v);
   //void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   boolean isReachable(String source, String target);
   boolean isFullyReachable();
}


public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
   
   public List<Vertex> getVertices()
   {
      return vertices;
   }
   public Vertex getVertex(int i)
   {
      return vertices.get(i); 
   }
   public Vertex getVertex(String vertexName)
   {
      return vertices.get(nameToIndex.get(vertexName));
   }
   public Map<String, Integer> getVertexMap()
   {
      return nameToIndex;
   }
   public void addVertex(String v)
   {
      if(!nameToIndex.containsKey(v))
      {
         vertices.add(new Vertex(v));
         nameToIndex.put(v, vertices.size()-1);
      }
   }
   public void addEdge(String source, String target)
   {
      Integer si = nameToIndex.get(source);
      Integer ti = nameToIndex.get(target);
      if(ti == null)
      {
         addVertex(target);
         ti = nameToIndex.get(target);
      }
      vertices.get(si).addEdge(vertices.get(ti));
   }
   public String toString()  //returns all vertices with their edges (omit commas)
   {
      String ret = "";
      for(Vertex v : vertices)
         ret = ret + v.toString() + "\n";
      return ret;
   }
   public List<Vertex> depthFirstSearch(String name)
   {
      return depthFirstSearch(getVertex(name));
   }
   public List<Vertex> depthFirstSearch(Vertex v)
   {
      List<Vertex> ret = new ArrayList<>();
      Stack<Vertex> st = new Stack<>();
      st.push(v);
      Vertex temp = null;
      while(!st.isEmpty())
      {
         temp = st.pop();
         if(!ret.contains(temp))
         {
            ret.add(temp);
            for(Vertex ve : temp.getAdjacencies())
               st.push(ve);
         }
      }
      return ret;
   }
   public List<Vertex> breadthFirstSearch(String name)
   {
      return breadthFirstSearch(getVertex(name));
   }
   public List<Vertex> breadthFirstSearch(Vertex v)
   {
      List<Vertex> ret = new ArrayList<>();
      Queue<Vertex> q = new LinkedList<>();
      q.add(v);
      Vertex temp = null;
      while(!q.isEmpty())
      {
         temp = q.remove();
         if(!ret.contains(temp))
         {
            ret.add(temp);
            for(Vertex ve : temp.getAdjacencies())
               q.add(ve);
         }
      }
      return ret;
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName));
      String[] temp;
      while(sc.hasNext())
      {
         temp = sc.nextLine().split(" ");
         if(!nameToIndex.containsKey(temp[0]))
            addVertex(temp[0]);
         if(!nameToIndex.containsKey(temp[1]))
            addVertex(temp[1]);
         addEdge(temp[0], temp[1]);
      } 
   }
   public int edgeCount()
   {
      int ret = 0;
      for(Vertex v : vertices)
         ret += v.getAdjacencies().size();
      return ret;
   }
   public boolean isReachable(String source, String target)
   {
      return depthFirstSearch(source).contains(getVertex(target));
   }
   public boolean isFullyReachable()
   {
      for(Vertex v : vertices)
         for(Vertex ve : vertices)
            if(!isReachable(v.getName(), ve.getName()))
               return false;
      return true;
   }
}


