//Tokelo Makoloane
//MKLTOK002

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileBSTApp
{
  //initialized instance variables.
   Scanner content = null;  
   String line = null;
   String[] lines = null;
   int index = 0;
   int index2 = 0;
   int index3 = 0;
   private static int count = 0;
   private BinaryTreeNode root;       //this is a root node, going to be used for assigning links to its subroots
   
   //for instrumentation
   public static int count(){
      return count;
   }

   //a method to read the contents of a file and using the insert method to add the contents os a file line by line in a BST  
   public void ReadFile(String filename)
   {
        try
        {
            content = new Scanner(new FileInputStream(filename));        //it creates a bridge between the file in java to read its contents or overwrite
        }
        catch(FileNotFoundException e){                            //catch an exception if the file is not found.
            System.out.println("File "+filename+" could not open or could not be found");
            System.exit(0);
        }
 
        while(content.hasNextLine())                     //keeps iterating the file until there are no lines to read
        {
            line = content.nextLine();                   //it will pass the reference of String of lines in a file to that of 'line', it will do that line by line
            lines = line.split(",");                     // this will split every word separated by a comma on a line it reads, to an array
            if(lines.length<3){                          // this is to check if every line from the file has three keys, country, date and vacNumb. if doesn't have vacNumb...... 
               line = line.concat("0");                 // append '0' to the last of the String line.
               insert(line);                            // after doing everything it will insert to the BST.
            }
            insert(line);
        }
   } 
   
   //method to insert elements in to a Binary Search Tree
   public void insert(String d)
   {
       if(root == null) {
           root = new BinaryTreeNode(d);   //if a node is null, meaning if there is no key(element) it will create a root
       }
       else{
           insert(d,root);   //if the node is not empty, then call the other insert method to assign a respective position a root
       }
   }
   
   public void insert(String d, BinaryTreeNode node)
   {
       index = d.lastIndexOf(",");
       index2 = node.data.lastIndexOf(",");
       if((d.substring(0,index)).compareTo(node.data.substring(0,index2))<=0)             //if string d is less than the node data, create a left link
       {
           if(node.left == null)
           {
               node.left = new BinaryTreeNode(d);     //if the left link has no node it will assign it a node recursively
           }
           else{
               insert(d,node.left);            //if it has a node already, it will create another link depending on the data
           }
       }
       else                                  //else if it is not less create a right link
       {
           if(node.right == null)              
           {
               node.right = new BinaryTreeNode(d);      //if the left link has no node it will assign it a node recursively
           }
           else{
               insert(d,node.right);                
           }
       }
   }
   
   //a method to find a specific country with date and country recursively
   public  BinaryTreeNode find(String X)                
   {  
      if(root == null){return null;}           //if there is no tree yet formed then return null
      else 
         return find(X,root);                  //else start searching the root of the tree before anything.
   }       
   
   public BinaryTreeNode find(String x, BinaryTreeNode node)
   {
          index3 = node.data.lastIndexOf(",");                            //this will find the last index of the comma
         int comp = x.compareTo((node.data).substring(0,index3));        //it will return a 0 if the x and the node.data are equal, -# if x is less than node.data and +# if greater
         
         if(comp==0)
         {
            count++;
            return node;
         }
         else if(comp<0)
         {
            count++;
            return (node.left == null) ? null : find(x,node.left);      //if it does return a negetive number visit the left side of the tree
         }
         else{
            count++;
            return (node.right==null) ? null : find(x,node.right);      //if it does return a positive number visit the right side of the tree
         }
   }
   
   //a method to check if a certain node really do exists.
   public boolean ifNodeExists(BinaryTreeNode node, String key)
   {
   
      /*
         if the node is null return false
         else if the element in that node is equal to the string we searching for return true
         else if is not found search again the left tree recursively to check which one is equal to it.
         if the key is found return true.
         then if is not found on the left tree search the right tree
      */
   
      if(node == null){
         return false;
      }
      if(node.data == key){
         count++;
         return true;
      }
      
      boolean search1 = ifNodeExists(node.left,key);
      if(search1){                          
         return true;
      }
      
      boolean search2 = ifNodeExists(node.right,key);
      return search2;
   }
}