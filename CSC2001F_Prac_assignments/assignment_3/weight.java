//Tokelo Makoloane
//MKLTOK002

import java.util.*;
import java.nio.file.*;

public class weight
{
   static weight2  C = new  weight2();
   static int collisions;             
   static int hashTableSize = 37;    
   static String [] hashTableArray;    
  
   public weight() 
   {
      hashTableArray = new String [hashTableSize];
      for ( int i=0; i<hashTableSize; i++ )
         hashTableArray[i] = "";
      collisions = 0;
   }
   
   public static void insert(String line, int h)
   {
      while (! hashTableArray[h].equals (""))
      {
         h = (h+1) % hashTableSize;
         collisions++;
      }
      hashTableArray[h] = line;
   }
   
   public static void main(String[] args)
   {
      C.permute();    //calls the purmutation method from the weight2 class
      int indexx;
      ArrayList<String> hash = new ArrayList<>(10);
      weight l = new weight();
      List<String> lines = null;
      List<String> linesmiss = null;
   
      try {
         lines = Files.readAllLines (Paths.get ("names36.txt"));
      } catch (Exception e) {
         System.out.println (e.toString ());
      }   
      
      //this loop is to iterate through the arraylist filled with possible permutations
      for ( int j=0; j<C.array.size(); j++ )
      {
         String arr = new String(C.array.get(j));  //creates a new string from the char array
      
         weight p = new weight();
           
         for(int i=0;i<lines.size();i++){
            String line = lines.get(i);
            int val = 0;
            for ( int k=0; k<9; k++ ){
               val += Integer.parseInt(arr.substring(k,k+1)) * line.charAt (k);  //hashfunction
            }
            int h = val % hashTableSize;   //hash value
            insert(line,h);
         }  
         if(collisions==0){
            hash.add(arr);
         }
         else{ 
            collisions=0;
         }
      }
      for(int i =0; i<hash.size();i++)
      {
         System.out.println(hash.get(i));
      }         
   }
}