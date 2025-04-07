//Tokelo Makoloane
//MKLTOK002
//2022-02-17

import java.util.Scanner;

//a class with main method to use FileBSTApp.java and CountryBSTApp.java
public class VaccineBSTApp
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);        //creating scanner object
        FileBSTApp fileStream = new FileBSTApp();      //creating FileBSTApp object
        CountryBSTApp results = new CountryBSTApp();   //creating CountryBSTApp object
        int index = 0;
        
        fileStream.ReadFile("vaccinations.csv");     //one of the FileBSTApp class methods to insert all the contents of a file to BST
        
        System.out.println("Enter the date:");
        String date = input.nextLine();            //assigning input from user to 'date' variable
        
        System.out.println("Enter the list of countries (end with an empty line):");
        do
        {
            String country = input.nextLine();
             
            if(country.equals("")) { break;}
            
            String string = country+","+date;
            BinaryTreeNode Node = fileStream.find(string);
            if(Node==null){results.addNode(country+" = <Not Found>");}       //if there is no country and date to be found  it will return not found
            else if(fileStream.ifNodeExists(Node,Node.data)){
                index = Node.data.lastIndexOf(",");
                results.addNode(country+" = "+(Node.data).substring(index+1));        //if there is a add it to the linked list to store results
            }
            else
            {
                results.addNode(country+" = <Not Found>");             
            }
        }while(true);      //to keep asking the user to input the countries until inputting nothing. 
       
       // FileBSTApp COUNT = new FileBSTApp(); 
        System.out.println("The number of comparisons is: "+FileBSTApp.count());
        System.out.println();
        System.out.println("Results:");
        results.display(); 
    }
}