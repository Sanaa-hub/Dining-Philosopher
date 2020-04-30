package com.sana.actorModel;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.Arrays;

public class Waiter extends UntypedActor
{
	 public static Props mkProps(int forkCount)
	 {
	        return Props.create(Waiter.class, forkCount);
	 }

	    private enum Chopsticks 
	    {
	    	available, unavailable 
	    	
	    }
	    
	    private Chopsticks[] ch;
	    private ArrayList<ActorRef> Actors;

	    private Waiter(int ChopsticksNo)
	    {
	    	ch = new Chopsticks[ChopsticksNo];
	        Arrays.fill(ch, Chopsticks.available);
	        Actors = new ArrayList<ActorRef>(ChopsticksNo);
	    }
	    

	    @Override
	    public void onReceive(Object message) throws Exception 
	    {
	        if (message instanceof MethodsHeader.Start) 
	        {
	            String name = ((MethodsHeader.Start) message).getPhilosopherName();
	            Actors.add(getSender());
	            getSender().tell(new MethodsHeader.Think(), getSelf());

	        }  
	        
	        else if (message instanceof MethodsHeader.Hungry)
	        {
	            int seat = Actors.indexOf(getSender());
	
	                int leftChopsticks = seat;
	                int rightChopsticks = (seat + 1) % ch.length;
	                if (ch[leftChopsticks].equals(Chopsticks.available) && ch[rightChopsticks].equals(Chopsticks.available)) 
	                {
	                	ch[leftChopsticks] = Chopsticks.unavailable;
	                	ch[rightChopsticks] = Chopsticks.unavailable;
	                    getSender().tell(new MethodsHeader.Eat(), getSelf());
	                }
	                else 
	                {
	                    getSender().tell(new MethodsHeader.Think(), getSelf());
	                }
	            
	        } 
	        else 
	        	if (message instanceof MethodsHeader.FinishEat) 
	        	{
	        		int seat = Actors.indexOf(getSender());
	        		int leftChopsticks = seat;
	        		int rightChopsticks = (seat + 1) % ch.length;
	        		ch[leftChopsticks] = Chopsticks.available;
	        		ch[rightChopsticks] = Chopsticks.available;
	        		getSender().tell(new MethodsHeader.Think(), getSelf());
	        }

	    }
	
	

}
