import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class vaccinePrac 
{
  public static void main(String[] args)
  {
      Scanner input = new Scanner(System.in);
      String[] StringArray = new String[2];
      String[] vaccineInfo = new String[9919];
      String countryInfo = null;
      int index1 = 0;
      int index2 = 0;
      Scanner content = null;
      System.out.println("Enter the file name");
      String filename = input.nextLine();
      System.out.println("Enter the list of countries (end with an empty line)");
      
      try
      {
         content = new Scanner(new FileInputStream(filename));
         
      }catch (FileNotFoundException e){
         System.out.println("File "+filename+" was not found");
         System.out.println("or could not be opened.");
         System.exit(0);   
      }
      finally
      {
         while(content.hasNext())
         {
            System.out.println(content.nextLine());
         }
      }
  }    
}