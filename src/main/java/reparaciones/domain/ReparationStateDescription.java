package reparaciones.domain;

public class ReparationStateDescription {

	private final boolean finalState;
	private final String name;
	private final ReparationStateType type;


	private ReparationStateDescription() {
		throw new AssertionError();
	}

	private ReparationStateDescription(ReparationStateDescriptionBuilder builder) {
		this.finalState = builder.getFinalState();
		this.name = builder.getName();
		this.type = builder.getType();
	}
	
	public static ReparationStateDescriptionBuilder getBuilder(String name){
		return new ReparationStateDescriptionBuilder(name);
	}

	public boolean FinalState() {
		return finalState;
	}

	public String getName() {
		return name;
	}	
	
	public ReparationStateType getType() {
		return type;
	}	
	
	public static class ReparationStateDescriptionBuilder {
		private boolean finalState;
		private final String name;
		private ReparationStateType type;
		
		private ReparationStateDescriptionBuilder(String name){
			this.name = name;
		}
		
		private boolean getFinalState() {
			return finalState;
		}

		private String getName() {
			return name;
		}

		private ReparationStateType getType() {
			return type;
		}
		
		public ReparationStateDescriptionBuilder finalState(boolean isFinalState){
			this.finalState = finalState;
			return this;
		}
		
		public ReparationStateDescriptionBuilder type(ReparationStateType type){
			this.type = type;
			return this;
		}
		
		public ReparationStateDescription build(){
			return new ReparationStateDescription(this);
		}		
	
	}	
	
}
