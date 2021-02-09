//Name: Aaliya Hussain
//Date: 2/8/19

import java.util.*;

/*  Driver for a binary expression tree class.
 *  Input: a postfix string, space delimited tokens. 
 */
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 /");
      postExp.add("20.0 3.0 -4.0 + *");
      postExp.add("2 3 + 5 / 4 5 - *");
      postExp.add("5.6");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         System.out.println( tree.display() );
         System.out.print("Infix order:  ");
         System.out.println( tree.inorderTraverse() );
         System.out.print("Prefix order:  ");
         System.out.println( tree.preorderTraverse() );
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}

/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
class BXT
{
   private TreeNode root;
   
   public BXT()
   {
      root = null;
   }
    
   public void buildTree(String str)
   {
      String[] ar = str.split(" ");
      Stack<TreeNode> st = new Stack<TreeNode>();
      TreeNode num1 = new TreeNode(null);
      TreeNode num2 = new TreeNode(null);
      if(ar.length == 1)
      {
         root = new TreeNode(ar[0]);
         return;
      }
      for(String s : ar)
      {
         if(!isOperator(s))
            st.push(new TreeNode(s));
         else
         {
            num1 = st.pop();
            num2 = st.pop();
            st.push(new TreeNode(s, num2, num1));
         }
      }
      root = st.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null)
         return 0;
      if(!isOperator((String)t.getValue()))
         return Double.parseDouble((String)t.getValue());
      double l = evaluateNode(t.getLeft());
      double r = evaluateNode(t.getRight());
      return computeTerm((String)t.getValue(), l, r);
   }
   
   private double computeTerm(String s, double a, double b)
   {
      String ops = "+-*/^%!";
      int i = ops.indexOf(s)+1;
      double ret = 0;
      switch(i)
      {
         case 1: ret = a + b;
         break;
         case 2: ret = a - b;
         break;
         case 3: ret = a * b;
         break;
         case 4: ret = a / b;
         break;
         case 5: ret = (int)Math.pow(a, b);
         break;
         case 6: ret = a % b;
         break;
      }
      return ret;
   }
   
   private boolean isOperator(String s)
   {
      String ops = "+-*/^%!";
      if(ops.indexOf(s) > -1)
         return true;
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String ret = "";  
      if(t == null)
         return "";
      ret += inorderTraverse(t.getLeft());	       						 		//recurse left
      ret += t.getValue() + " ";      				 					//inorder visit
      ret += inorderTraverse(t.getRight());          								//recurse right
      return ret;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }

}

/***************************************

 Postfix Exp: 14 -5 /
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20.0 3.0 -4.0 + *
 		-4.0
 	+
 		3.0
 *
 	20.0
 Infix order:  20.0 * 3.0 + -4.0 
 Prefix order:  * 20.0 + 3.0 -4.0 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 Postfix Exp: 5.6
 5.6
 Infix order:  5.6 
 Prefix order:  5.6 
 Evaluates to 5.6
 ------------------------
 
 *******************************************/