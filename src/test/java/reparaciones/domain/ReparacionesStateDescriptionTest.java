package reparaciones.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reparaciones.domain.ReparationStateDescription.ReparationStateDescriptionBuilder;
import reparaciones.domain.ReparationStateType.ReparationStateTypeBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparacionesStateDescriptionTest {

	public static class BuilderReparacionesStateDescription{
		
		private static final String REPARATION_STATE_NAME = "reparationStateName";
		private static final String REPARATION_STATE_TYPE_NAME = "stateTypeName";
		
		
		@Test
		public void shouldCreateAReparacionesStateDescriptionWhenUseABuilder() throws Exception {

			//Create a reparation type
			ReparationStateTypeBuilder stateTypeBuilder = ReparationStateTypeBuilder.newInstance(REPARATION_STATE_TYPE_NAME);
			ReparationStateType stateType = stateTypeBuilder.build();
			
			//Create a reparation description
			ReparationStateDescriptionBuilder repStateDescrBuilder = ReparationStateDescriptionBuilder.newInstance(REPARATION_STATE_NAME);
			repStateDescrBuilder.type(stateType);
			
			ExpectedException.none();
			ReparationStateDescription stateDescriptionInstace = repStateDescrBuilder.build();
			
			assertNotNull(stateDescriptionInstace);	
			assertNotNull(stateDescriptionInstace.getType());
			assertEquals(stateDescriptionInstace.getName(), REPARATION_STATE_NAME);

		}
		
	}

}
