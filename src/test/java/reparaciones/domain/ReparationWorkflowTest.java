package reparaciones.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparationWorkflowTest {
	
	private static String INITIAL_STATE_NAME = "nameInitialState";
	private static String FINAL_STATE_NAME = "nameFinalState";
	private static String WORKFLOW_NAME = "nameOfmyWorkflow";
	

	public static class FactoryReparationWorkflowTest{
		
		
		
		@Test
		public void shuldCreateAReparationWorflowWhenUseAFactory(){			
			ReparationWorkflow workflow = ReparationWorkflow.newInstance(WORKFLOW_NAME);
			
			assertNotNull(workflow);
			assertEquals(workflow.getName(), WORKFLOW_NAME);			
		}
		
	}
	
	public static class AddTransitionsToWorkflow{
		
		private static ReparationWorkflow workflow;
		private static int TRANSITIONS_QUANTITY = 5;
		
		
		
		@BeforeClass
		public static void initialize(){
			
			workflow = ReparationWorkflow.newInstance(WORKFLOW_NAME);
		 
			for (int index = 0; index < TRANSITIONS_QUANTITY; index ++){
				
				ReparationStateDescription initialState = ReparationStateDescription.getBuilder(INITIAL_STATE_NAME + "_" + index).build();  
				ReparationStateDescription finalState =  ReparationStateDescription.getBuilder(FINAL_STATE_NAME + "_" + index).build();
				
				ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);		
				
				workflow.addReparationStateDescriptionTransition(transition);
			}			
			
		}
		
		@Test
		public void shouldGetNumberOfTransitionTest(){
			assertEquals(TRANSITIONS_QUANTITY, workflow.getNumberOfTransitions());
		}		
		
		@Test
		public void shouldAddATransitionToWorkflowTest(){
			
			ReparationStateDescription initialState = ReparationStateDescription.getBuilder(INITIAL_STATE_NAME).build();  
			ReparationStateDescription finalState =  ReparationStateDescription.getBuilder(FINAL_STATE_NAME).build();
			
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			assertEquals(workflow.getNumberOfTransitions(), TRANSITIONS_QUANTITY + 1);
			
		}
		
	}
	
	public static class RemoveTransitionOfTheWorkflow{
		
		private static ReparationWorkflow workflow;
		
		@Before
		public void initialize(){
			workflow = ReparationWorkflow.newInstance(WORKFLOW_NAME);
		}
		
		@Test
		public void shouldReturnFalseWhenRemoveTransitionOfTheWorkflowAndItIsEmpty(){		
			
			boolean resultRemove = workflow.removeTransition(0);
			
			assertEquals(resultRemove, false);
			
		}
		
		@Test
		public void shouldRemoveTransitionOfTheWorkflowAndReturnTrueWhenItHaveElements(){
			
			ReparationStateDescription initialState = ReparationStateDescription.getBuilder(INITIAL_STATE_NAME).build();
			ReparationStateDescription finalState = ReparationStateDescription.getBuilder(FINAL_STATE_NAME).build();
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			boolean removedResult = workflow.removeTransition(0);
			
			assertEquals(removedResult, true);
			assertEquals(workflow.getNumberOfTransitions(), 0);
			
		
		}
		
		@Test
		public void shouldReturnFalseWhenTryToRemoveTransitionOfTheWorkflowWithALargestIndex(){
			
			ReparationStateDescription initialState = ReparationStateDescription.getBuilder(INITIAL_STATE_NAME).build();
			ReparationStateDescription finalState = ReparationStateDescription.getBuilder(FINAL_STATE_NAME).build();
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			int indexToDelete = workflow.getNumberOfTransitions();
			
			boolean deleteResult = workflow.removeTransition(indexToDelete);
			
			assertEquals(deleteResult, false);
		}
	}
}
