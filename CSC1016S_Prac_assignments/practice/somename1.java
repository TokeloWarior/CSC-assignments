public class somename1 implements somename
{
   private String j;
   private int L;
   public somename1()
   {
      j = "";
      L=0;
   }
   
   @Override
   public int set(int i)
   {
      L = i;
      return L;
      
   }
   
   @Override
   public String set(String i)
   {
      j = i;
      return j;
   }
}