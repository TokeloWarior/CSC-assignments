import java.util.*;

class Question1
{
   public static void main ( String args [] )
   {
      System.out.println (new Shape (new Shape ("Pentagon", "Blue")));
      System.out.println (new Circle (new Circle ("Circle", "Purple",  3)));
      System.out.println (new Rectangle (new Rectangle ("Rectangle", "Red", 6, 8)));
      System.out.println (new neww (new Rectangle ("Rectangle", "Red", 6, 8)).get());

   }
}
