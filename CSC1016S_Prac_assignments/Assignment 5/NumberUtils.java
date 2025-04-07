// TOKELO MAKOLOANE
// MKLTOK002
// 23 September 2021

//The NumberUtils class contains a collection of routines for working with integers.
class NumberUtils
{
   private NumberUtils() {
   // A private, empty-bodied constructor prevents NumberUtil objects from being created.
   }
   
   // Given a number that is n digits in length, maps the digits to an array length n.
   public static int[] toArray(int number)
   {
      String int_str = Integer.toString(number);   // this converts int to string.
      String[] strArray = int_str.split("");      // this will split string into an array.
      int[] intArray = new int[int_str.length()];
      
      for (int i = 0; i < strArray.length; i++)
      {
         intArray[i] = Integer.parseInt(strArray[i]);  //thi will convert all the elements of strArray to int then assign them to a new int array.
      }
      return intArray;  
   } 
   
   // Given two numbers, count the quantity of matching digits those with the same value and position.
   public static int countMatches(int numberA, int numberB)
   {
      int count = 0;
      int[] numA_array = NumberUtils.toArray(numberA);
      int[] numB_array = NumberUtils.toArray(numberB);
      
      for (int i = 0; i<numA_array.length; i++) 
      {
         if (numA_array[i] == numB_array[i])
         {
            count++;
         }
      }
      return count;
   }
   
   // Count the quantity of digits that two numbers have in common, regardless of position.
   public static int countIntersect(int numberA, int numberB)
   {
      int count = 0;
      int[] numA_array = NumberUtils.toArray(numberA);
      int[] numB_array = NumberUtils.toArray(numberB);

      for (int i = 0; i<numA_array.length; i++)
      {
         int index = i;                              // this will set index to initial value of i.
         for (int n = 0; n<numB_array.length; n++)
         {
            if (numA_array[index] == numB_array[n]) // this will check linearly that which elements of A equals those of B
            {
               count++;
               break;
            }
            
         }
      }
      return count;
   } 
}