//Name: Aaliya Hussain
//Date: 3/7/19

import java.util.*;
import java.io.*;

public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fn));
      ArrayList<String> lines = new ArrayList<String>();
      TreeSet<String> lows;
      TreeSet<String> caps;
      TreeSet<String> others;
      String lowsplit = "[ABCDEFGHIJKLMNOPQRSTUVWXYZ., \"!?:;]+\\s*";
      String capsplit = "[abcdefghijklmnopqrstuvwxyz., \"!?:;]+\\s*";
      String othersplit = "[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \"]+\\s*";
      String lowcom = "";
      String capcom = "";
      String othercom = "";
      while(infile.hasNextLine())
         lines.add(infile.nextLine());
      for(int k = 0; k < lines.size(); k++)
      {
         System.out.println();
         System.out.println(lines.get(k));
         lows = new TreeSet<String>();
         caps = new TreeSet<String>();
         others = new TreeSet<String>();
         lows = add(input(lines.get(k), lowsplit), lows);
         lowcom += input(lines.get(k), lowsplit) + " ";
         caps = add(input(lines.get(k), capsplit), caps);
         capcom += input(lines.get(k), capsplit) + " ";
         others = add(input(lines.get(k), othersplit), others);
         othercom += input(lines.get(k), othersplit) + " ";
         System.out.println("Lower Case: " + lows);
         System.out.println("Upper Case: " + caps);
         System.out.println("Other: " + others);
      }
      System.out.println();
      System.out.println("Common Lower Case: " + common(lowcom));
      System.out.println("Common Upper Case: " + common(capcom));
      System.out.println("Common Other: " + common(othercom));
   }
   public static String input(String line, String split)
   {
      String[] temp = line.split(split);
      String pre = "";
      for(String s : temp)
         pre += s;
      String[] temp2 = pre.split(" ");
      String ret = "";
      for(String s : temp2)
         ret += s;
      return ret;
   }
   public static TreeSet<String> add(String s, TreeSet<String> t)
   {
      for(int k = 0; k < s.length(); k++)
         t.add(s.substring(k, k+1));
      return t;
   }
   
   public static TreeSet<String> common(String s)
   {
      String[] temp = s.split(" ");
      TreeSet<String> ret = new TreeSet<String>();
      TreeSet<String>[] temp2 = new TreeSet[temp.length];
      TreeSet<String> temp3 = new TreeSet<String>();
      for(int k = 0; k < temp.length; k++)
      {
         temp2[k] = new TreeSet<String>();
         for(int j = 0; j < temp[k].length(); j++)
         {
            temp2[k].add(temp[k].substring(j, j+1));
            temp3.add(temp[k].substring(j, j+1));
         }
      }
      Object[] temp4 = temp3.toArray();
      for(int k = 0; k < temp4.length; k++)
         if(allCon(temp2, (String)temp4[k]))
            ret.add((String)temp4[k]);
      return ret;
   }
   
   public static boolean allCon(TreeSet<String>[] t, String s)
   {
      for(int k = 0; k < t.length; k++)
      {
         for(int j = 0; j < t[k].size(); j++)
            if(!t[k].contains(s))
               return false;
      }
      return true;
   }
}

/***********************************
  ----jGRASP exec: java SetsOfLetters_teacher
 
 We, therefore, the Representatives of the united States of 
 Lower Case: [a, d, e, f, h, i, n, o, p, r, s, t, u, v]
 Upper Case: [R, S, W]
 Other: [ , ,]
 
 America, in General Congress, Assembled, appealing to the 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, p, r, s, t]
 Upper Case: [A, C, G]
 Other: [ , ,]
 
 Supreme Judge of the world for the rectitude of our intentions,
 Lower Case: [c, d, e, f, g, h, i, l, m, n, o, p, r, s, t, u, w]
 Upper Case: [J, S]
 Other: [ , ,]
 
 do, in the Name, and by the Authority of the good People of 
 Lower Case: [a, b, d, e, f, g, h, i, l, m, n, o, p, r, t, u, y]
 Upper Case: [A, N, P]
 Other: [ , ,]
 
 these Colonies, solemnly publish and declare, That these United 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, u, y]
 Upper Case: [C, T, U]
 Other: [ , ,]
 
 Colonies are, and of Right ought to be Free and Independent 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, p, r, s, t, u]
 Upper Case: [C, F, I, R]
 Other: [ , ,]
 
 States; that they are Absolved from all Allegiance to the 
 Lower Case: [a, b, c, d, e, f, g, h, i, l, m, n, o, r, s, t, v, y]
 Upper Case: [A, S]
 Other: [ , ;]
 
 British Crown, and that all political connection between them 
 Lower Case: [a, b, c, d, e, h, i, l, m, n, o, p, r, s, t, w]
 Upper Case: [B, C]
 Other: [ , ,]
 
 and the State of Great Britain, is and ought to be totally 
 Lower Case: [a, b, d, e, f, g, h, i, l, n, o, r, s, t, u, y]
 Upper Case: [B, G, S]
 Other: [ , ,]
 
 dissolved; and that as Free and Independent States, they have 
 Lower Case: [a, d, e, h, i, l, n, o, p, r, s, t, v, y]
 Upper Case: [F, I, S]
 Other: [ , ,, ;]
 
 full Power to levy War, conclude Peace, contract Alliances, 
 Lower Case: [a, c, d, e, f, i, l, n, o, r, s, t, u, v, w, y]
 Upper Case: [A, P, W]
 Other: [ , ,]
 
 establish Commerce, and to do all other Acts and Things which 
 Lower Case: [a, b, c, d, e, g, h, i, l, m, n, o, r, s, t, w]
 Upper Case: [A, C, T]
 Other: [ , ,]
 
 Independent States may of right do. And for the support of this 
 Lower Case: [a, d, e, f, g, h, i, m, n, o, p, r, s, t, u, y]
 Upper Case: [A, I, S]
 Other: [ , .]
 
 Declaration, with a firm reliance on the protection of divine 
 Lower Case: [a, c, d, e, f, h, i, l, m, n, o, p, r, t, v, w]
 Upper Case: [D]
 Other: [ , ,]
 
 Providence, we mutually pledge to each other our Lives, our 
 Lower Case: [a, c, d, e, g, h, i, l, m, n, o, p, r, s, t, u, v, w, y]
 Upper Case: [L, P]
 Other: [ , ,]
 
 Fortunes and our sacred Honor.
 Lower Case: [a, c, d, e, n, o, r, s, t, u]
 Upper Case: [F, H]
 Other: [ , .]
 
 Common Lower Case: [d, e, n, o, r, t]
 Common Upper Case: []
 Common Other: [ ]
  ----jGRASP: operation complete.
  ************************************/