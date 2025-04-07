import java.util.*;

class HashTableFunctions
{

   // hash function weights
   // 9 integers, each in the range 0-5 to act as weights for the characters in the keys
   int[] weights = {4,2,0,3,1,0,1,2,4};
   
   // returns True if the hash table contains string s
   // return False if the hash table does not contain string s
   boolean find ( String s, int h, int hashTableSize, String [] hashTableArray )
   {  
      HashTable hash = new HashTable ();
      int index = hash.hash(s);
      boolean bool = false;
      while(!hashTableArray[index].equals("")){
         if(hashTableArray[index].equals(s)){
            bool = true;
         }
         index = (index+1) % hash.hashTableSize;
      }
      return bool; 
   }
}
