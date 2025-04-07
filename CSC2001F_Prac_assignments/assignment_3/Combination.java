// Java implementation of the approach
import java.util.*;
public class Combination {

	static Set<String> hash_Set = new HashSet<>();
   static ArrayList<char[]> array = new ArrayList<>();

	// Recursive function to generate
	// permutations of the string
	static void Permutation(String str, String ans)
	{

		// If string is empty
		if (str.length() == 0) {

			// Add the generated permutation to the
			// set in order to avoid duplicates
			hash_Set.add(ans);
			return;
		}

		for (int i = 0; i < str.length(); i++) {

			// ith character of str
			char ch = str.charAt(i);

			// Rest of the string after excluding
			// the ith character
			String ros = str.substring(0, i)
						+ str.substring(i + 1);

			// Recurvise call
			Permutation(ros, ans + ch);
		}
	}

// 	// Driver code
    public static void permute()
    {
 		String s = "214324204";
// // 
// // 		// Generate permutations
 		Permutation(s, "");
// // 
// // 		// Print the generated permutations
 		hash_Set.forEach((n) -> array.add(n.toCharArray()));
       //System.out.println(hash_Set.size());
// //       System.out.println(array.size());
 	}
}
