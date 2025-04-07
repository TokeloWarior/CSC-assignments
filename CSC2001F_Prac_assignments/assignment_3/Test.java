import java.util.*;
import java.nio.file.*;

class Test
{
   public static HashTableFunctions Func = new HashTableFunctions();
   public static ArrayList<int[]> array = new ArrayList<int[]>(386420489);
   int index5 = 0;
   public Test ()
   {
   }
   
   public static void allLexicographicRecur(String str, int[] data,int last, int index)
	{
		int length = str.length();

		// One by one fix all characters at the given index
		// and recur for the subsequent indexes
		for (int i = 0; i < length; i++)
		{

			// Fix the ith character at index and if
			// this is not the last index then
			// recursively call for higher indexes
			data[index] = Integer.parseInt(str.substring(i,i+1));

			// If this is the last index then print
			// the string stored in data[]
			if (index == last)
				array.add(data);
			else
				allLexicographicRecur(str, data, last,index + 1);
		}
	}

	// This function sorts input string, allocate memory
	// for data(needed for allLexicographicRecur()) and calls
	// allLexicographicRecur() for printing all permutations
	static void allLexicographic(String str)
	{
		int length = str.length();

		// Create a temp array that will be used by
		// allLexicographicRecur()
		int[] data = new int[length+1];
		char[] temp = str.toCharArray();

		// Sort the input string so that we get all
		// output strings in lexicographically sorted order
		Arrays.sort(temp);
		str = new String(temp);

		// Now print all permutations
		allLexicographicRecur(str, data, length - 1, 0);
	}
   
   public void run ( int threshold )
   {
      HashTable h = new HashTable ();
      HashTableFunctions wow = new HashTableFunctions();
      List<String> lines = null;
      List<String> linesmiss = null;
   
      try {
         lines = Files.readAllLines (Paths.get ("names36.txt"));
         linesmiss = Files.readAllLines (Paths.get ("namesmiss.txt"));
      } catch (Exception e) {
         System.out.println (e.toString ());
      }   
      
      for ( int i=0; i<lines.size (); i++ )
      {
         h.insert (lines.get (i));
      }
      
      System.out.println ("Collision Threshold: " + h.getCollisions());
      do{
            new HashTableFunctions(array.get(index5));
         
            if (h.getCollisions () == 0)
               System.out.println(Arrays.toString(Func.weights));
            else{
               // System.out.println("not met the threshold");
//               System.out.println(Arrays.toString(Func.weights));
              index5++;
            }
     }while(index5<array.size());        
   }

   public static void main ( String [] args )
   {
       allLexicographic("214120113");
      (new Test()).run(Integer.valueOf (10));
   }
}
