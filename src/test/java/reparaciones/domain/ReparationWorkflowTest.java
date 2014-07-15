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

	public static class FactoryReparationWorkflowTest{
		
		private static String NAME_WORKFLOW = "nameOfmyWorkflow";
		
		@Test
		public void createAReparationWorflow(){			
			ReparationWorkflow workflow = ReparationWorkflow.newInstance(NAME_WORKFLOW);
			
			assertNotNull(workflow);
			assertEquals(workflow.getName(), NAME_WORKFLOW);			
		}
		
	}
	
	public static class AddTransitionsToWorkflow{
		
		private static ReparationWorkflow workflow;
		private static String NAME_WORKFLOW = "nameOfmyWorkflow";
		private static int QUANTITY_OF_TRANSITION = 5;
		private static String NAME_INITIAL_STATE = "nameInitialState";
		private static String NAME_FINAL_STATE = "nameFinalState";
		
		
		@BeforeClass
		public static void initialize(){
			
			workflow = ReparationWorkflow.newInstance(NAME_WORKFLOW);
		 
			for (int index = 0; index < QUANTITY_OF_TRANSITION; index ++){
				
				ReparationStateDescription initialState = ReparationStateDescription.newInstance(NAME_INITIAL_STATE + "_" + index);  
				ReparationStateDescription finalState =  ReparationStateDescription.newInstance(NAME_FINAL_STATE + "_" + index);
				
				ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);		
				
				workflow.addReparationStateDescriptionTransition(transition);
			}			
			
		}
		
		@Test
		public void getNumberOfTransitionTest(){
			assertEquals(QUANTITY_OF_TRANSITION, workflow.getNumberOfTransitions());
		}		
		
		@Test
		public void addATransitionToWorkflowTest(){
			
			ReparationStateDescription initialState = ReparationStateDescription.newInstance(NAME_INITIAL_STATE);  
			ReparationStateDescription finalState =  ReparationStateDescription.newInstance(NAME_FINAL_STATE);
			
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			assertEquals(workflow.getNumberOfTransitions(), QUANTITY_OF_TRANSITION + 1);
			
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
		public void removeTransitionOfTheWorkflowWhenTheWorkflowIsEmpty(){		
			
			Boolean resultRemove = workflow.removeTransition(0);
			
			assertEquals(resultRemove, false);
			
		}
		
		@Test
		public void removeTransitionOfTheWorkflowWithElements(){
			
			ReparationStateDescription initialState = ReparationStateDescription.newInstance(INITAL_STATE_NAME);
			ReparationStateDescription finalState = ReparationStateDescription.newInstance(FINAL_STATE_NAME);
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			Boolean removedResult = workflow.removeTransition(0);
			
			assertEquals(removedResult, true);
			assertEquals(workflow.getNumberOfTransitions(), 0);
			
		
		}
		
		@Test
		public void removeTransitionOfTheWorkflowWithLargestIndex(){
			
			ReparationStateDescription initialState = ReparationStateDescription.newInstance(INITAL_STATE_NAME);
			ReparationStateDescription finalState = ReparationStateDescription.newInstance(FINAL_STATE_NAME);
			ReparationStateDescriptionTransition transition = ReparationStateDescriptionTransition.newInstance(initialState, finalState);
			
			workflow.addReparationStateDescriptionTransition(transition);
			
			int indexToDelete = workflow.getNumberOfTransitions();
			
			Boolean deleteResult = workflow.removeTransition(indexToDelete);
			
			assertEquals(deleteResult, false);
		}
	}
}
