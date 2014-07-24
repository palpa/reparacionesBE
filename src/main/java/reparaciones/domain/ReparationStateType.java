package reparaciones.domain;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import reparaciones.helpers.ConstantsExceptionHelper;


public class ReparationStateType {
	
	private final String name;
	private Date creationDate;
	private Date finishDate;
	
	private ReparationStateType(ReparationStateTypeBuilder builder) throws Exception{
		this.name = builder.getName();
		this.checkAndAssignDates(builder);
		
	}
	
	private void checkAndAssignDates(ReparationStateTypeBuilder builder) throws Exception {
		
		//Check if the creation date isn't less or equals than finish date
		if (builder.hasDates()) {
			if (builder.getCreationDate().after(builder.getFinishDate())) {
				throw new IllegalArgumentException(ConstantsExceptionHelper.ERROR_CREATE_DATE_BIGGER_THAN_FINISH_DATE);
			}
		}
		
		this.creationDate = builder.getCreationDate();
		this.finishDate = builder.getFinishDate();		
	}	
	

	public String getName() {
		return name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public static class ReparationStateTypeBuilder {
		
		private final String name;		
		private Date creationDate;
		private Date finishDate;
		
		private String getName() {
			return name;
		}

		private Date getCreationDate() {
			return creationDate;
		}

		private Date getFinishDate() {
			return finishDate;
		}
		
		private ReparationStateTypeBuilder(String name){
			this.name = name;
		}
		
		public ReparationStateTypeBuilder creationDate(Date creationDate){
			this.creationDate = creationDate;
			return this;
		}
		
		public ReparationStateTypeBuilder finishDate(Date finishDate){
			this.finishDate = finishDate;
			return this;
		}
		
		public ReparationStateType build() throws Exception{
			return new ReparationStateType(this);
		}
		
		public static ReparationStateTypeBuilder newInstance(String name){
			return new ReparationStateTypeBuilder(name);
		}
		
		public boolean hasDates(){
			
			boolean result = false;
			
			if((this.getCreationDate() != null) && (this.getFinishDate() != null)){
				result = true;
			}
			
			return result;
		}
		
	}	
}
