// TOKELO MAKOLOANE
// MKLTOK002
// 23 September 2021

// CowsAndBulls implements the logic for a cows and bulls guessing game the player has.
class CowsAndBulls
{
   //Constants
   public final static int NUM_DIGITS = 4;
   public final static int MAX_VALUE = 9876;
   public final static int MIN_VALUE = 1234;
   public final static int MAX_GUESSES = 10;
   
   int mysteryNum =0;
   int guessesRemaining = MAX_GUESSES;
   Result results = null;
   int count =0;
   /** 
    Create a CowsAndBulls game using the given randomisation seed value to generate
    a mystery number of NUM_DIGITS length, and that gives the player MAX_GUESSES guesses.
   **/
   public CowsAndBulls(int seed)
   {
      NumberPicker pickedNum = new NumberPicker(seed, 1, 9);
      for (int i = 0; i<NUM_DIGITS; i++){
         mysteryNum = (mysteryNum * 10) + pickedNum.nextInt();
      }
   }
   
   // Obtain the number of guesses remaining.
   public int guessesRemaining()
   {
      return guessesRemaining;
   }
   
   /** 
     Evaluates a guess that the mystery number is guessNumber, returning the outcome in the form
     of a Result object. Decrements guesses remaining.
   **/ 
   public Result guess(int guessNumber)
   {  guessesRemaining--;
      int Bulls = NumberUtils.countMatches(guessNumber,mysteryNum);
      int intersect = NumberUtils.countIntersect(guessNumber,mysteryNum);
      int matches = NumberUtils.countMatches(guessNumber,mysteryNum);
      int Cows = intersect - matches;
      
      results = new Result(Cows, Bulls);
      
      return results;
   }
   
   // End the game, returning the secretNumber.
   public int giveUp()
   {
      return mysteryNum; 
   }
   
   // Returns true if the secret number has been guessed, or there are no more guesses. 
   public boolean gameOver()
   {count++;
      if (results!=null) {
         if (results.isCorrect() == true || guessesRemaining <= 0){
            return true;
         }
         return false;
      }
      else if (count > 1) {
         return true;
      }
      else{         
         return false;
      }
      
   }
}
