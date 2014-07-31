package reparaciones.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparacionesStateDescriptionTest {

	public static class BuilderReparacionesStateDescription{
		
		private static final String REPARATION_STATE_NAME = "reparationStateName";
		private static final String REPARATION_STATE_TYPE_NAME = "stateTypeName";
		
		
		@Test
		public void shouldCreateAReparacionesStateDescriptionWhenUseABuilder() throws Exception {

			//Create a reparation type
			
			ReparationStateType stateType = ReparationStateType.getBuilder(REPARATION_STATE_TYPE_NAME).build();			

			//Create a reparation description
			ExpectedException.none();
			ReparationStateDescription stateDescriptionInstace = ReparationStateDescription.getBuilder(REPARATION_STATE_NAME)
																						   .type(stateType)
																						   .build();			
			assertNotNull(stateDescriptionInstace);	
			assertNotNull(stateDescriptionInstace.getType());
			assertEquals(stateDescriptionInstace.getName(), REPARATION_STATE_NAME);

		}
		
	}

}
