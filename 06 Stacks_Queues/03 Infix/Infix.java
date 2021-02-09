//Name: Aaliya Hussain
//Date: 1/13/19

import java.util.*;

public class Infix
{
   public static final String OPS = "*/+-";
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("( -5 + 15 ) - 6 / 3");
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");
      infixExp.add("8 + -1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + Postfix.eval(pf));  //Postfix must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<String> st = new Stack<String>();
      String result = "";
      String temp = "";
      String peek = "";
      for(int k = 0; k < infixParts.size(); k++)
      {
         temp = infixParts.get(k);
         if(OPS.indexOf(temp) < 0 && !temp.equals("(") && !temp.equals(")"))
         {
            result += temp + " ";
            continue;
         }
         if(temp.equals("("))
            st.push(temp);
         if(temp.equals(")"))
         {
            temp = "";
            while(!st.peek().equals("("))
               result += st.pop() + " ";
            st.pop();
            continue;
         }
         if(OPS.indexOf(temp) > -1)
         {
            if(!st.isEmpty())
            {
               peek = st.peek();
               while(!(st.isEmpty() || peek.equals("(") || !isLower(temp.charAt(0), peek.charAt(0))))
               {
                  result += st.pop() + " ";
                  if(!st.isEmpty())
                     peek = st.peek();
               }
               if(peek.equals("("))
               {
                  st.push(temp);
                  continue;
               }
               if(!isLower(temp.charAt(0), peek.charAt(0)))
               {
                  st.push(temp);
                  continue;
               }
               
            }
            if(st.isEmpty())
            {
               st.push(temp);
               continue;
            }
         }
      }
      while(!st.isEmpty())
         result += st.pop() + " ";
      return result;
   }
   
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      if((OPS.indexOf(c1) == 2 || OPS.indexOf(c1) == 3) && (OPS.indexOf(c2) == 1 || OPS.indexOf(c2) == 0))
         return true;
      if(OPS.indexOf(c1) == OPS.indexOf(c2))
         return true;
      if((OPS.indexOf(c2) == 1 || OPS.indexOf(c2) == 0) && (OPS.indexOf(c1) == 0 || OPS.indexOf(c1) == 1))
         return true;
      if((OPS.indexOf(c1) == 2 || OPS.indexOf(c1) == 3) && (OPS.indexOf(c2) == 3 || OPS.indexOf(c2) == 2))
         return true;
      return false;
   }
   
   //static class Postfix
  // {
      //public static final String OPS = "+-*/^%!";
   //   public static int eval(String pf)
    /*  {
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
   }*/
}
	
/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 
***********************************************/