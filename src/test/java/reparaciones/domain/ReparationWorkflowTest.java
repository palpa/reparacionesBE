package reparaciones.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reparaciones.domain.ReparationStateDescription.ReparationStateDescriptionBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparationWorkflowTest {
	
	private static String INITIAL_STATE_NAME = "nameInitialState";
	private static String FINAL_STATE_NAME = "nameFinalState";
	private static String NAME_WORKFLOW = "nameOfmyWorkflow";
	

	public static class FactoryReparationWorkflowTest{
		
		
		
		@Test
		public void shuldCreateAReparationWorflowWhenUseAFactory(){			
			ReparationWorkflow workflow = ReparationWorkflow.newInstance(NAME_WORKFLOW);
			
			assertNotNull(workflow);
			assertEquals(workflow.getName(), NAME_WORKFLOW);			
		}
		
	}
	
	public static class AddTransitionsToWorkflow{
		
		private static ReparationWorkflow workflow;
		private static String WORKFLOW_NAME = "nameOfmyWorkflow";
		private static int TREANSITIONTS_QUANTITY = 5;
		
		
		
		@BeforeClass
		public static void initialize(){
			
			workflow = ReparationWorkflow.newInstance(WORKFLOW_NAME);
		 
			for (int index = 0; index < TREANSITIONTS_QUANTITY; index ++){
				
				ReparationStateDescription initialState = ReparationStateDescriptionBuilder.newInstance(INITIAL_STATE_NAME + "_" + index).build();  
				ReparationStateDescription finalState =  ReparationStateDescriptionBuilder.newInstance(FINAL_STATE_NAME + "_" + index).build();
				
				ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);		
				
				workflow.addReparationStateDescriptionTransition(transition);
			}			
			
		}
		
		@Test
		public void shouldGetNumberOfTransitionTest(){
			assertEquals(TREANSITIONTS_QUANTITY, workflow.getNumberOfTransitions());
		}		
		
		@Test
		public void shouldAddATransitionToWorkflowTest(){
			
			ReparationStateDescription initialState = ReparationStateDescriptionBuilder.newInstance(INITIAL_STATE_NAME).build();  
			ReparationStateDescription finalState =  ReparationStateDescriptionBuilder.newInstance(FINAL_STATE_NAME).build();
			
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			assertEquals(workflow.getNumberOfTransitions(), TREANSITIONTS_QUANTITY + 1);
			
		}
		
	}
	
	public static class RemoveTransitionOfTheWorkflow{
		
		private static ReparationWorkflow workflow;
		private static String WORKFLOW_NAME = "workflowName";
		private static String INITAL_STATE_NAME = "initialStateName";
		private static String FINAL_STATE_NAME = "finalStateName";
		
		@Before
		public void initialize(){
			workflow = ReparationWorkflow.newInstance(WORKFLOW_NAME);
		}
		
		@Test
		public void shouldReturnFalseWhenRemoveTransitionOfTheWorkflowAndItIsEmpty(){		
			
			Boolean resultRemove = workflow.removeTransition(0);
			
			assertEquals(resultRemove, false);
			
		}
		
		@Test
		public void shouldRemoveTransitionOfTheWorkflowAndReturnTrueWhenItHaveElements(){
			
			ReparationStateDescription initialState = ReparationStateDescriptionBuilder.newInstance(INITAL_STATE_NAME).build();
			ReparationStateDescription finalState = ReparationStateDescriptionBuilder.newInstance(FINAL_STATE_NAME).build();
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			Boolean removedResult = workflow.removeTransition(0);
			
			assertEquals(removedResult, true);
			assertEquals(workflow.getNumberOfTransitions(), 0);
			
		
		}
		
		@Test
		public void shouldReturnFalseWhenTryToRemoveTransitionOfTheWorkflowWithALargestIndex(){
			
			ReparationStateDescription initialState = ReparationStateDescriptionBuilder.newInstance(INITAL_STATE_NAME).build();
			ReparationStateDescription finalState = ReparationStateDescriptionBuilder.newInstance(FINAL_STATE_NAME).build();
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			int indexToDelete = workflow.getNumberOfTransitions();
			
			Boolean deleteResult = workflow.removeTransition(indexToDelete);
			
			assertEquals(deleteResult, false);
		}
	}
}
