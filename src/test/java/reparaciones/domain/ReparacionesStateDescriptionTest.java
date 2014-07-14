package reparaciones.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reparaciones.domain.ReparationStateDescription;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparacionesStateDescriptionTest {

	public static class FactoryReparacionesStateDescription{
		
		private static final String REPARATION_STATE_NAME = "reparationStateName";
		
		
		@Test
		public void createAReparacionesStateDescription() {

			ReparationStateDescription repStateDescrInstance = ReparationStateDescription
					.newInstance(REPARATION_STATE_NAME);

			assertEquals(repStateDescrInstance.getName(), REPARATION_STATE_NAME);

		}
		
	}

}
