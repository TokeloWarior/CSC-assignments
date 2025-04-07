//MKLTOK002

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Random;
import java.io.FileInputStream;
import java.io.IOException;

/**
*   Class used to randomise the contents of the file, to be added in a AVL tree...
    to test if AVL trees do balance the tree regardless of whether the data is sorted
**/

public class Randomisations
{
    private String filename;
    private String[] shuffles = new String[1];
    private String newFilename;
    private Scanner content = null;
    private String[] Array = new String[9919];
    private int index = 0;
    private int counts = 0;
    
    public Randomisations(String filename, String[] shuffles)
    {
        //allocating respective resreferences....
        this.filename = filename;
        this.shuffles = shuffles;
    }
    
    //method to input contents of a file in an array
    private void InputArray()
    {
        try
        {
            content = new Scanner(new FileInputStream(this.filename));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File "+this.filename+" can't be open or it does not exist. please type in the correct name");
            System.exit(0);
        }
        
        while(content.hasNextLine())
        {
            Array[index] = content.nextLine();
            index++;
        }
    }
    
    //method to shuffle every content of the array and rewrite them on a file
    public void shuffle()
    {
        InputArray();     //calling the method to input items in an array first before shuffling.
        Random rand = new Random();     //creating a Random object from Random class.
        
        while(counts<10)        //it will stop shuffling based on the given number of shuffles
        {
            for(int i = Array.length-1; i>0; i--)
            {
                int j = rand.nextInt(i+1);   //picking a random number between 0-i(i+1 because the iteration starts at the element before last
                
                //Swapp elements in an array with elements at random index.
                String elementAt = Array[i];
                Array[i] = Array[j];       
                Array[j] = elementAt;
            }
            counts++;
        }
        try{
                FileWriter file = new FileWriter("RandomVaccinations.csv");    //FileWriter object to write in a specified file name and type of file

        //a loop to write all the contents of the array line by line to a file
        for(int i=0; i<Array.length; i++)
        {
            file.append(Array[i]);
            file.append("\n");
        }
        
        file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        this.newFilename = "RandomVaccinations.csv";
        
        System.out.println("The file has been randomised");
        System.out.println();
    }
    
    public String getFilename(){return this.newFilename;}
}