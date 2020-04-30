package com.sana.actorModel;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class Philosopher extends UntypedActor {
	
	 public static Props mkProps(String PhilosopherName, ActorRef PWaiter) {
	        return Props.create(Philosopher.class, PhilosopherName, PWaiter);
	    }

	    private String name;
	    private ActorRef waiter;

	    
	    private Philosopher(String PhilosopherName, ActorRef PWaiter) {
	        name = PhilosopherName;
	        waiter = PWaiter;

	        PWaiter.tell(new MethodsHeader.Start(PhilosopherName), getSelf());
	    }

	    
	    @Override
	    public void onReceive(Object message) throws Exception 
	    {
	        if (message instanceof MethodsHeader.Think) 
	        {
	            System.out.println(name + "Started Thinking");
	            Thread.sleep(1000);

	            waiter.tell(new MethodsHeader.Hungry(), getSelf());

	        }
	        else 
	        	if (message instanceof MethodsHeader.Eat) 
	        	{
	            System.out.println(name + "Started eating");
	            Thread.sleep(1000);
	            System.out.println(name + "Ended Eating ");
	            System.out.println("\n");
	            waiter.tell(new MethodsHeader.FinishEat(), getSelf());
	        }
	    }



}
