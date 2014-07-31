package reparaciones.domain;

import java.util.ArrayList;
import java.util.List;

import reparaciones.domain.ReparationStateDescriptionTransition;

public class ReparationWorkflow {	
	
	private final String name;	
	private List<ReparationStateDescriptionTransition> transitions;
	
	private ReparationWorkflow(){
		throw new AssertionError();
	}
	
	private ReparationWorkflow(String name){
		this.name = name;
		this.transitions = new ArrayList<ReparationStateDescriptionTransition>();
	}
	
	public static ReparationWorkflow newInstance(String name){
		return new ReparationWorkflow(name);
	}

	public String getName() {
		return name;
	}

	public List<ReparationStateDescriptionTransition> getTransitions() {
		return transitions;
	}

	public void setTransitions(
			List<ReparationStateDescriptionTransition> transitions) {
		this.transitions = transitions;
	}
	
	public void addReparationStateDescriptionTransition(ReparationStateDescriptionTransition transition){
		this.transitions.add(transition);
	}
	
	public int getNumberOfTransitions(){
		return this.transitions.size();
	}
	
	public ReparationStateDescriptionTransition getTransitionAt(int index){
		return this.transitions.get(index);
	}
	
	public boolean removeTransition(int index){
		
		boolean result = false;
		
		if(index <= this.transitions.size() -1 ){
			ReparationStateDescriptionTransition objectRemoved = this.transitions.remove(index);
			
			if(objectRemoved != null){			
				result = true;
			}
		}
		
		return result;
	}	
}
