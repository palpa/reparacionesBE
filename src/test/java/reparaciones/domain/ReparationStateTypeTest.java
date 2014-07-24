package reparaciones.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reparaciones.helpers.ConstantsExceptionHelper;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class ReparationStateTypeTest {
	private final static String STATE_TYPE_NAME = "nameOfStateType";

	
	public static class BuilderReparationStateType {
	
		@Rule
		public ExpectedException exception = ExpectedException.none();
		
		@Test
		public void shouldCreateAReparationStateTypeWhenUseABuilder() throws Exception {
			
			//Create a creation date.
			Date creationDate = new Date();
			
			//Create a finish dale as the creation day plus 1 day.
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(creationDate);
			calendar.add(Calendar.DATE,1);			
			Date finishDate = calendar.getTime();
			
			//Create a state type.
			exception.none();
			ReparationStateType steteType = ReparationStateType.getBuilder(STATE_TYPE_NAME)
															   .creationDate(creationDate)
															   .finishDate(finishDate)
															   .build();			
		
			assertNotNull(steteType);
			assertEquals(steteType.getName(), STATE_TYPE_NAME);
			assertEquals(steteType.getCreationDate(), creationDate);
			assertEquals(steteType.getFinishDate(), finishDate);
			
		}
	}

	public static class DateHandlingInReparationStateType{
		
		@Rule
		public ExpectedException exception = ExpectedException.none();
		
		@Test
		public void shouldThrowIlegarArgumentExceptionWhenCreationDateIsBiggerThanFinishDate() throws Exception{	
				
						//Create a creation date
		    			Date creationDate = new Date();
		    			
		    			//Create a finish date as the creation date less one day
		    			Calendar calendar = Calendar.getInstance();
		    			calendar.setTime(creationDate);
		    			calendar.add(Calendar.DATE, -1);
		    			Date finishDate = calendar.getTime();
		    			
		    		
		    			exception.expect(IllegalArgumentException.class);
		    			exception.expectMessage(ConstantsExceptionHelper.ERROR_CREATE_DATE_BIGGER_THAN_FINISH_DATE);
		    			
		    			ReparationStateType.getBuilder(STATE_TYPE_NAME)
		    									  .creationDate(creationDate)
		    									  .finishDate(finishDate)
		    									  .build();		    			

		}
		
	}
}
