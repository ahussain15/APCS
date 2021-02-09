//Name: Aaliya Hussain
//Date: 1/17/19

import java.util.*;

public class McRonald1000
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   public static Queue<Integer> ar = new LinkedList<Integer>();
   public static int c = 0;
   public static double rtwt = 0.0;
   public static int lwt = 0;
   public static int lq = 0;
   
   public static void main(String[] args)
   {
      int tc = 0;
      int ld = 0;
      int olwt = 0;
      int twt = 0;
      int olq = 0;
      int sa = 0;
      for(int k = 0; k < 1000; k++)
      {
         day();
         tc += c;
         if(c > ld)
            ld = c;
         twt += rtwt;
         sa += rtwt / c;
         rtwt = 0;
         if(lq > olq)
            olq = lq;
         if(lwt > olwt)
            olwt = lwt;
         lq = 0;
         c = 0;
      }
      System.out.println("Total customers served = " + tc);
      System.out.println("Average wait time = " + (sa/1000.0));
      System.out.println("Longest wait time = " + olwt);
      System.out.println("Longest queue = " + olq);
      System.out.println("Average served per day = " + (tc/1000.0));
      System.out.println("Largest day = " + ld);
   }
   
   public static void day()
   {
      int nc = 0;
      int wt = 0;
      int cc = -1;
      int t = 0;
      int k = 0;
      for(k = 1; k < TIME; k++)
      {
         cc--;
         nc = (int)(Math.random() * 5 + 1);
         if(nc == 1)
         {
            ar.add(k);
            c++;
         }
         if(!ar.isEmpty())
         {
            wt = (int)(Math.random() * 6 + 2);
            if(cc < 0)
               cc = wt;
            if(cc == 0)
            {
               t = ar.remove();
               rtwt += k - t;
               if((k-t) > lwt)
                  lwt = k-t;
            }
         }
         if(ar.size() > lq)
            lq = ar.size();
      }
      while(!ar.isEmpty())
      {
         k++;
         cc--;
         if(!ar.isEmpty())
         {
            wt = (int)(Math.random() * 6 + 2);
            if(cc < 0)
               cc = wt;
            if(cc == 0)
            {
               t = ar.remove();
               rtwt += k - t;
               if((k-t) > lwt)
                  lwt = k-t;
            }
         }
      }
   }
}