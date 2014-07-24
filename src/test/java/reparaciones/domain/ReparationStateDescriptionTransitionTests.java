package reparaciones.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reparaciones.domain.ReparationStateDescription.ReparationStateDescriptionBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparationStateDescriptionTransitionTests {

	public static class FactoryReparacionesStateDescriptionTransition{
		
		private static String INITIAL_STATE_NAME = "initialStateName";
		private static String FINAL_STATE_NAME = "finalStateName";
		private static ReparationStateDescription initialState;
		private static ReparationStateDescription finalState;
		
		@BeforeClass
		public static void initialize(){
			 initialState = ReparationStateDescriptionBuilder.newInstance(INITIAL_STATE_NAME).build();
			 finalState = ReparationStateDescriptionBuilder.newInstance(FINAL_STATE_NAME).build();
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
