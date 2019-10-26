package BackEnd;

public class Arco 
{
   private int peso;
   private boolean existe;
   
   public Arco ( )
   {
   }
   
   public Arco ( int p )
   {
      peso = p;
      existe = true;
   }
   
   public int getPeso()
   {
      return peso;    
   }
   
   public void setPeso(int p)
   {
      peso = p;    
   }
   
   public boolean exists()
   {
       return existe;
   }
   
   public void set(boolean e)
   {
       existe = e;
   }
   
   @Override
   public String toString()
   {
      return "Peso: " + peso;    
   }
}
