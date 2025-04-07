import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.utils.Scanner;

public class readFile
{
   Scanner content;
   public readFile(){
      content = null;
   }
   
   public String Readfile(String filename)
   {
      try
      {
         content = new Scanner(new FileInputStream(filename));
         
      }catch (FileNotFoundException e){
         System.out.println("File morestuff.txt was not found");
         System.out.println("or could not be opened.");
         System.exit(0);   
      }finally{
         
      }
   }
}