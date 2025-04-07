//MKLTOK002

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

/**
    A class to implement the AVL tree using the contents of the randomised file..
    of the vaccinations.csv file
**/

public class ImplementAVL
{
    private String filename;    // an instance variable to store the name of the randomised file;
    private BinaryTreeNode root;
    private String line = "";
    private String[] lines = new String[3];
    private Scanner content = null;
    
    public ImplementAVL(String filename)
    {
        this.filename = filename;
    }
    
    //this method will get the height of the tree as elements will be added in a tree.
    private int getHeight(BinaryTreeNode node)
    {
        if(node != null){
         return node.height;
        }
        return -1; 
    }
    private int balanceFactor(BinaryTreeNode node)
    {
      return getHeight(node.right) - getHeight(node.left);
    }
    
    //this is to just get the maximum of two integers, than using the math builtin function
    private int maximum(int leftbound, int rightbound)
    {
        return (leftbound > rightbound) ? leftbound : rightbound;
    }
    
    private void fixHeight(BinaryTreeNode node)
    {
       node.height = maximum(getHeight(node.left),getHeight(node.right)) + 1;
    }
    
    //method to rotate the nodes to the right if the AVL tree conditions are not met
    private BinaryTreeNode rotateRight(BinaryTreeNode node)
    {
        BinaryTreeNode leftNode = node.left;
        
        //swaping links from the right
        node.left = leftNode.right;
        leftNode.right = node;
        fixHeight(node);
        fixHeight(leftNode); 
        
        //updating heights of (sub)tree(s)
        return leftNode;
    }
    
    //method to rotate the nodes to the left if the AVL tree conditions are not met
    private BinaryTreeNode rotateLeft(BinaryTreeNode node)
    {
        BinaryTreeNode rightNode = node.right;
                
        //swapping links from the left
         node.right = rightNode.left;
        rightNode.left = node;
        
        //updating heights of (sub)tree(s)
        fixHeight(node);
        fixHeight(rightNode);
        return rightNode;
    }
        
    //method to Double Rotate the tree don't meet the AVL tree conditions
    private BinaryTreeNode balance(BinaryTreeNode node)
    {
         fixHeight(node);
        if(balanceFactor(node) == 2)       //checks if the difference between heights of left and right subtrees is 2.....
        {
            if(balanceFactor(node.right) < 0)   //if it is 2, checks height in the right subtree, if is less than 0,
                node.right = rotateRight(node.right);  //rotate in away that the new node will be the one smallest in the right subtree
            return rotateLeft(node);   //then return the rotated node from the right to left.
        }
        //then this one do the opposite of the above process
        if(balanceFactor(node) == -2)
        {
            if(balanceFactor(node.left) > 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        return node;
    }
    
    //a method to insert elements in the AVL tree
    public void insert(String d)
    {//System.out.println("inside");
        root = insert(d, root);
    }
    //same insert method from the BST, the difference is that is balance everytime adding an element in the AVL tree
    private BinaryTreeNode insert(String d, BinaryTreeNode node)
    {
       
        if(node == null){
            return new BinaryTreeNode(d);
        }
         int index = d.lastIndexOf(",");
        int index2 = node.data.lastIndexOf(",");
        if((d.substring(0,index)).compareTo(node.data.substring(0,index2))<=0){             //if string d is less than the node data, create a
            node.left = insert(d,node.left);
        }
        else{                                 //else if it is not less create a right link
            node.right = insert(d,node.right);        //if the left link has no node it will assign it a node recur 
        }
        return balance(node);
    }
    
    //a method to find the specific data according to the inputed keys by the user
    public BinaryTreeNode find(String d)
    {
        if(root == null)
            return null;
        else
            return find(d,root);
    }
    //same find method from the BSTApp 
    private BinaryTreeNode find(String d, BinaryTreeNode node)
    {
        int index3 = node.data.lastIndexOf(",");                            //this will find the last index of the comma
        int comp = d.compareTo((node.data).substring(0,index3));        //it will return a 0 if the x and the node.data are equal, -# if x 

        if(comp==0)
        {
            return node;
        }
        else if(comp<0)
        {
            return (node.left == null) ? null : find(d,node.left);      //if it does return a negetive number visit the left side of the tre
        }
        else{
            return (node.right==null) ? null : find(d,node.right);      //if it does return a positive number visit the right side of the tr
        }
    }
    
    //a method to check a respective node exist
    public boolean IfNodeExists(BinaryTreeNode node, String key)
    {
        /**
         if the node is null return false
         else if the element in that node is equal to the string we searching for return true
         else if is not found search again the left tree recursively to check which one is equal to it.
         if the key is found return true.
         then if is not found on the left tree search the right tree
        **/
        if(node == null){
            return false;
        }
        if(node.data == key){
            return true;
        }
        
        boolean search1 = IfNodeExists(node.left,key);
        if(search1){
            return true;
        }
        
        boolean search2 = IfNodeExists(node.right,key);
        return search2;
    }    
    
    //a method to read the contents of a file and using the insert method to add the contents os a file line by line in a BST  
    public void ReadFile()
    {
        try
        {
            content = new Scanner(new FileInputStream(filename));        //it creates a bridge between the file in java to read its contents
        }
        catch(FileNotFoundException e){                            //catch an exception if the file is not found.
            System.out.println("File "+filename+" could not open or could not be found");
            System.exit(0);
        }

        while(content.hasNextLine())                     //keeps iterating the file until there are no lines to read
        {
            line = content.nextLine();                   //it will pass the reference of String of lines in a file to that of 'line', it wil
            lines = line.split(",");                     // this will split every word separated by a comma on a line it reads, to an array
            if(lines.length<3){                          // this is to check if every line from the file has three keys, country, date and v
               line = line.concat("0");                 // append '0' to the last of the String line.
               insert(line);                            // after doing everything it will insert to the BST.
            }
            insert(line);
        }
    } 
}