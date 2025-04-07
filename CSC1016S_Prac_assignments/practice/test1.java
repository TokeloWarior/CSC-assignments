import java.util.*;
import java.io.*;

public class test1
{   
   public static void main(String[] args)
   {
      System.out.println("Enter file name:");
      Scanner input  = new Scanner(System.in);
      String n = input.nextLine();
      Scanner inputFile = null;
      try 
      {
         inputFile = new Scanner(new FileInputStream(n));
      }
      catch(FileNotFoundException e)
      {
         System.out.println("file is not found, please re-run the program");
         System.exit(0);
      }
      catch(Exception e){System.exit(0);}
      
      while(inputFile.hasNextLine())
      {
         String action = inputFile.nextLine().toLowerCase();
         if(action.equals("start")){
            break;
         }
         else{
            System.out.println(action);
         }
      }

   }
}