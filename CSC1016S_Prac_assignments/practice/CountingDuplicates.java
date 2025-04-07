public class CountingDuplicates 
{
   public static long squareDigits(int n) {
    String m = Integer.toString(n);
    String k="";
    for(int i = 0;i<m.length();i++){
      k += Integer.toString(Integer.parseInt(m.substring(i,i+1))*Integer.parseInt(m.substring(i,i+1)));
    }
    return Long.parseLong(k);
  }   
  public static void main(String[] args)
   {
      System.out.println(squareDigits(67897543));
   }
}