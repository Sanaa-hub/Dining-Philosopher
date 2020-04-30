package semaphores;

import java.util.concurrent.Semaphore;

public class chopstick {
	
	
	   Semaphore semaphore = new Semaphore(1);

	   public void grab() 
	   {
		     try 
		     {
		    	 semaphore.acquire();
		     }
		     
		     catch (Exception ex) 
		     {
		        System.out.println(ex);;
		     }
		}
	   
	   public void release() 
	   {
		   semaphore.release();
	   }

	   
	  public boolean available() 
	   {
		  if (semaphore.availablePermits() > 0)
		  {
			  return true;
		  }

		  else
		  {
			  return false;
		  }


}
}