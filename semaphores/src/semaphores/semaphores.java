package semaphores;
import java.util.Scanner;
public class semaphores {
	
	  
      static philosopher ph[] = new philosopher[5];
	  static chopstick ch[] = new chopstick[5];
	  
	  
	   public static void main(String[] args) 
	   
	   {
		
		    for (int i = 0; i < 5; i++)
		    {
		    	ch[i] = new chopstick();
		    }

		    for (int i = 0; i < 5; i++) 
		    {
		    	ph[i] = new philosopher(i, ch[i], ch[(i + 1) % 5]);
		    	ph[i].start();
		    }

		    
		for ( ; ; )
		  {
		      try 
		      {

		    	Thread.sleep(3000);
		        boolean deadlock = true;
		        for (chopstick c : ch)
		        {
		          if (c.available())
		          {
		            deadlock = false;
		            break;
		          }
		        }
		        
		        if (deadlock==true)
		        {
		          Thread.sleep(3000);
		          System.out.println("Deadlock occuredddddddd");
		          break;
		        }
		      }
		      catch (Exception e)
		      {
		        System.out.println(e);
		      }
		    }


	   }


}
