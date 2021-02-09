//Name: Aaliya Hussain
//Date: 1/22/19

import java.util.*;

public class McRonaldVIP
{
   public static final int TIME = 1080;  //18 hrs * 60 min
   public static Queue<Integer> ar = new LinkedList<Integer>();
   public static Queue<Integer> vip = new LinkedList<Integer>();
   public static int c = 0;
   public static int vc = 0;
   public static double rtwt = 0.0;
   public static double vrtwt = 0.0;
   public static int lwt = 0;
   public static int lq = 0;
   
   public static void main(String[] args)
   {
      int nc = 0;
      int nv = 0;
      int wt = 0;
      int vwt = 0;
      int cc = -1;
      int vcc = -1;
      int t = 0;
      int vt = 0;
      int k = 0;
      display(ar, k);
      for(k = 1; k < TIME; k++)
      {
         cc--;
         vcc--;
         nc = (int)(Math.random() * 5 + 1);
         nv = (int)(Math.random() * 100 + 1);
         if(nc == 1)
         {
            ar.add(k);
            c++;
         }
         if(nv == 1)
         {
            vip.add(k);
            vc++;
         }
         if(!vip.isEmpty())
         {
            vwt = (int)(Math.random() * 6 + 2);
            if(vcc < 0)
               vcc = vwt;
            if(vcc == 0)
            {
               vt = vip.remove();
               vrtwt += k - vt;
            }
            if(ar.size() > lq)
               lq = ar.size();
            display(ar, k);
            continue;
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
         display(ar, k);
      }
      while(!vip.isEmpty())
      {
         k++;
         vcc--;
         if(!ar.isEmpty())
         {
            vwt = (int)(Math.random() * 6 + 2);
            if(vcc < 0)
               vcc = vwt;
            if(vcc == 0)
            {
               vt = vip.remove();
               vrtwt += k - vt;
            }
         }
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
         display(ar, k);
      }
      System.out.println("Total customers served = " + c);
      System.out.println("Average wait time = " + (rtwt/c));
      System.out.println("Longest wait time = " + lwt);
      System.out.println("Longest queue = " + lq);
      System.out.println("Number of VIPs served = " + vc);
      System.out.println("Average wait time for VIPs = " + vrtwt/vc);
   }
   
   public static void display(Queue<Integer> q, int min)   //if you are storing arrival times
   //public static void display(Queue<Customer> q, int min) //if you have a Customer class
   {
      System.out.println(min + ": " + vip.toString() + " " + q.toString());
   }
}