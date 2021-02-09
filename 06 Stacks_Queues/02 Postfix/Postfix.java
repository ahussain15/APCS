//Name: Aaliya Hussain
//Date: 1/8/19

import java.util.*;

public class Postfix
{
   public static final String OPS = "+-*/^%!";
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %"); 
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static int eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<String> st = new Stack<String>();
      String a = "";
      String b = "";
      String ch = "";
      String temp = "";
      for(int k = 0; k < postfixParts.size(); k++)
      {
         temp = postfixParts.get(k);
         if(!isOperator(temp))
         {
            st.push(temp);
            if(a.equals(""))
               a = temp;
            else
               b = temp;
            continue;
         }
         if(isOperator(temp) && OPS.indexOf(temp) != 6)
         {
            ch = temp;
            a = st.pop();
            b = st.pop();
            st.push(eval(Integer.parseInt(a), Integer.parseInt(b), ch)+"");
         }
         else
         {
            ch = temp;
            a = st.pop();
            int ai = Integer.parseInt(a);
            for(int j = ai-1; j > 1; j--)
               ai *= j;
            st.push(ai+"");
         }
      }
      return Integer.parseInt(st.pop());
   }
   
   public static int eval(int a, int b, String ch)
   {
      int i = OPS.indexOf(ch)+1;
      int ret = 0;
      switch(i)
      {
         case 1: ret = a + b;
         break;
         case 2: ret = b - a;
         break;
         case 3: ret = a * b;
         break;
         case 4: ret = b / a;
         break;
         case 5: ret = (int)Math.pow(b, a);
         break;
         case 6: ret = b % a;
         break;
      }
      return ret;
   }
   
   public static boolean isOperator(String op)
   {
      if(OPS.indexOf(op) > -1)
         return true;
      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/