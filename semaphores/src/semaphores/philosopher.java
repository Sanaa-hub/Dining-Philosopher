package semaphores;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class philosopher extends Thread{

	
	 public int PhilosopherNumber;
	    public chopstick leftFork;
	    public chopstick rightFork;

	   philosopher(int num, chopstick left, chopstick right) 
	   {
		   PhilosopherNumber = num;
	      leftFork = left;
	      rightFork = right;
	    }

	    public void run()
	    {
	      System.out.println("Philosopher " +PhilosopherNumber + " join the table");

	     for (; ;) 
	     {
	        collectChopsticks();
	     
	        try 
	        {
			  eat();
			} 
	        catch (InterruptedException e) 
	        {
				System.out.println(e);
			}

	        releaseChopsticks();
	      }
	    }

	    private void releaseChopsticks() {

	        leftFork.release();
	        System.out.println( "Philosopher " +PhilosopherNumber + " return 1st chopstick");

	        rightFork.release();
	        System.out.println( "Philosopher " +PhilosopherNumber + " return 2nd chopsticks");

	        System.out.println( PhilosopherNumber + " return both chopsticks and ended eating");
	        System.out.println("\n");

		}

		private void collectChopsticks() {

	    	leftFork.grab();
	        System.out.println("Philosopher "+PhilosopherNumber + " got 1st chopsticks ");

	        rightFork.grab();
	        System.out.println("Philosopher "+PhilosopherNumber + " got 2nd chopsticks ");

	        System.out.println("Philosopher "+PhilosopherNumber + " got 2 chopsticks, its starting eating ");
		}

		void eat() throws InterruptedException 
	    {

	        Thread.sleep(1000);
	    
	    }

	    }
  
