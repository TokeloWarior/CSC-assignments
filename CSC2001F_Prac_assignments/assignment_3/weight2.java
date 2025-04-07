//MKLTOK002
//Tokelo Makoloane

//a program to check all the permutations of the given string of numbers

import java.util.*;

public class weight2
{
   public static Set<String> SET = new HashSet<>();
   public static ArrayList<char[]> array = new ArrayList<>();
   
	/**The main function that recursively find
	   all repeated permutations of the given string.
	   It uses data[] to store all permutations one by one
   **/
	public static void allrepeatedRecur(String str, char[] data,int last, int index)
	{
		int length = str.length();

		// One by one fix all characters at the given index and recur for the subsequent indexes
		for (int i = 0; i < length; i++)
		{
			/** Fix the ith character at index and if
			    this is not the last index then
			    recursively call for higher indexes
         **/
			data[index] = str.charAt(i);

			// If this is the last index then print
			// the string stored in data[]
			if (index == last){
				SET.add(new String(data));
         }
			else
				allrepeatedRecur(str, data, last,index + 1);
		}
	}

	/** This function sorts input string, allocate memory
	    for data(needed for allrepeatedRecur()) and calls
	    allreapeatedRecur() for printing all permutations
   **/
	public static void Permutation(String string)
	{
		int length = string.length();

		// Create a temp array that will be used by
		// allLexicographicRecur()
		char[] data = new char[length + 1];
		char[] temp = string.toCharArray();

		// Sort the input string so that we get all
		// output strings in lexicographically sorted order
		Arrays.sort(temp);
		string = new String(temp);

		// Now print all permutations
		allrepeatedRecur(string, data, length - 1, 0);
	}

	// Driver Code
	public static void permute()
	{
		String str = "012341234";
		Permutation(str);
      SET.forEach((n) -> array.add(n.toCharArray()));
      System.out.println(array.size());
      //System.out.println(array);
      
	}
}
