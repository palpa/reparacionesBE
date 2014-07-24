package reparaciones.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparationStateDescriptionTransitionTests {

	public static class FactoryReparacionesStateDescriptionTransition{
		
		private static String INITIAL_STATE_NAME = "initialStateName";
		private static String FINAL_STATE_NAME = "finalStateName";
		private static ReparationStateDescription initialState;
		private static ReparationStateDescription finalState;
		
		@BeforeClass
		public static void initialize(){
			 initialState = ReparationStateDescription.getBuilder(INITIAL_STATE_NAME) .build();					 
			 finalState = ReparationStateDescription.getBuilder(FINAL_STATE_NAME).build();
		}
		
		@Test
		public void shouldCreateReparacionesStateDescriptionTreansitionWhenUseAFactory() {
			ReparationStateDescriptionTransition reparationTransition = ReparationStateDescriptionTransition
					.newInstance(initialState, finalState);

			assertNotNull(reparationTransition);
			assertNotNull(reparationTransition.getInitialState());
			assertNotNull(reparationTransition.getFinishState());			
		}
		
	}
	

}
