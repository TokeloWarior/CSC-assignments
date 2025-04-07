//MKLTOK002

import java.util.Scanner;

/**main method to perform and implement the AVL tree**/

public class AVLExperiment
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        storeResults results = new storeResults();
        Randomisations random = new Randomisations("vaccinations.csv",args);
        
        random.shuffle();   //shuffle the elements of the file.
        
        ImplementAVL read = new ImplementAVL(random.getFilename());
        int index =0;
        
        read.ReadFile();
        
        System.out.println("Enter the date:");
        String date = input.nextLine();
        
        System.out.println("Enter the list of countries (end with an empty line):");
        do
        {
            String country = input.nextLine();
             
            if(country.equals("")) { break;}
            
            String string = country+","+date;
            BinaryTreeNode Node = read.find(string);
            if(Node==null){results.addNode(country+" = <Not Found>");}       //if there is no country and date to be found  it will return n
            else if(read.IfNodeExists(Node,Node.data)){
                index = Node.data.lastIndexOf(",");
                results.addNode(country+" = "+(Node.data).substring(index+1));        //if there is a add it to the linked list to store res
            }
            else
            {
                results.addNode(country+" = <Not Found>");
            }
        }while(true);      //to keep asking the user to input the countries until inputting nothing. 
        
       // System.out.println("The number of comparisons is: "+FileBSTApp.count());
        //System.out.println();
        System.out.println("Results:");
        results.display();         
    }   
}