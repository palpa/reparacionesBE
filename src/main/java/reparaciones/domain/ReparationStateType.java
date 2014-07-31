package reparaciones.domain;

import java.util.Date;

import reparaciones.helpers.ConstantsExceptionHelper;


public class ReparationStateType {
	
	private final String name;
	private Date creationDate;
	private Date finishDate;
	
	private ReparationStateType(ReparationStateTypeBuilder builder) throws IllegalArgumentException{
		this.name = builder.getName();
		this.creationDate = builder.getCreationDate();
		this.finishDate = builder.getFinishDate();	
		
	}
	
	public static ReparationStateTypeBuilder getBuilder(String nameOfReparationStateType){
		return new ReparationStateTypeBuilder(nameOfReparationStateType);
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
		
		public ReparationStateType build() throws IllegalArgumentException{			
			this.checkAssignedDatesDates();
			return new ReparationStateType(this);
		}
		
		private void checkAssignedDatesDates() throws IllegalArgumentException {
			
			//Check if the creation date isn't less or equals than finish date
			if (this.haveDates()) {
				if (this.getCreationDate().after(this.getFinishDate())) {
					throw new IllegalArgumentException(ConstantsExceptionHelper.ERROR_CREATE_DATE_BIGGER_THAN_FINISH_DATE);
				}
			}				
		}		
		
		public boolean haveDates(){			
			boolean result = false;
			
			if((this.getCreationDate() != null) && (this.getFinishDate() != null)){
				result = true;
			}			
			return result;
		}
		
	}	
}
