public class pio
{
   public static int sum(int n, int m)
   {
      if(n==m)
         return n;
      else
      {
         int mid = (n+m)/2;
         return sum(n, mid) + sum(mid+1,m);
      }
   }
   
   public static void main(String[] args)
   {
      System.out.println(sum(3,6));
   }
}