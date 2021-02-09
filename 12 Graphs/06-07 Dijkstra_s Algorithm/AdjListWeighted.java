//Name: Aaliya Hussain 
//Date: 6/6/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 * and Graphs7: Dijkstra with Cities
 */

class Edge 
{
   //public fields not common on AP exam
   public final wVertex target;  
   public final double weight;   
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   public wVertex(String argName)
   {
      name = argName;
      adjacencies = new ArrayList<>();
   }
   
   public double getMinDistance()
   {
      return minDistance;
   }
   public String getName()
   {
      return name;
   }
   public void setMinDistance(double d)
   {
      minDistance = d;
   }
   public ArrayList<Edge> getAdjacencies()
   {
      return adjacencies;
   }
   public void addEdge(wVertex v, double weight)
   {
      adjacencies.add(new Edge(v, weight));
   }
   public int compareTo(wVertex other)
   {
      return (int)(minDistance - other.minDistance);
   } 
   public wVertex getPrevious()
   {
      return previous;
   }
   public void setPrevious(wVertex v)
   {
      previous = v;
   }
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */
 
interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}


public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
   public AdjListWeighted()
   {
   }
   
   public List<wVertex> getVertices()
   {
      return vertices;
   }
   public wVertex getVertex(int i)
   {
      return vertices.get(i);
   }
   public wVertex getVertex(String vertexName)
   {
      return vertices.get(nameToIndex.get(vertexName));
   }
   public Map<String, Integer> getNameToIndex()
   {
      return nameToIndex;
   }
   public void addVertex(String v)
   {
      vertices.add(new wVertex(v));
      nameToIndex.put(v, vertices.size()-1);
   }
   public void addEdge(String source, String target, double weight)
   {
      getVertex(source).addEdge(new wVertex(target), weight);
   }
   public void minimumWeightPath(String vertexName)
   {
      minimumWeightPath(getVertex(vertexName));
   }
   public void minimumWeightPath(wVertex source)
   {
      PriorityQueue<wVertex> pq = new PriorityQueue<>();
      pq.add(source);
      source.setMinDistance(0.0);
      for(Edge e : source.getAdjacencies())
         e.target.setMinDistance(Double.POSITIVE_INFINITY);
      wVertex temp;
      while(!pq.isEmpty())
      {
         temp = pq.remove();
         for(Edge e : temp.getAdjacencies())
         {
            getVertex(e.target.getName()).setMinDistance(Math.min(e.target.getMinDistance(), e.weight+temp.getMinDistance()));
            if(!pq.contains(getVertex(e.target.getName()))) 
               pq.add(getVertex(e.target.getName()));
            else
               pq.remove(getVertex(e.target.getName()));
            if(getVertex(e.target.getName()).getMinDistance() == e.weight+temp.getMinDistance())
               getVertex(e.target.getName()).setPrevious(temp);
         }
      }  
   }
   
   public List<String> getShortestPathTo(wVertex v)
   {
      List<String> ret = new ArrayList<String>();
      wVertex p = v;
      while(p != null)
      {
         ret.add(0, p.getName());
         p = p.getPrevious();
      }
      return ret;
   }
   
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException
   {
      AdjListWeighted ret = new AdjListWeighted();
      Scanner c = new Scanner(vertexNames);
      Scanner ce = new Scanner(edgeListData);
      int n = c.nextInt();
      String temp = "";
      while(c.hasNext())
         ret.addVertex(c.next());
      c.close();
      String[] temp2;
      while(ce.hasNext())
      {
         temp = ce.nextLine();
         temp2 = temp.split(" ");
         ret.addEdge(temp2[0], temp2[1], Double.parseDouble(temp2[2]));
      }
      ce.close();
      return ret;
   }
}   


