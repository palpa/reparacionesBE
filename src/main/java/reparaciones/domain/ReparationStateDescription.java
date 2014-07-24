package reparaciones.domain;

public class ReparationStateDescription {

	private final Boolean finalState;
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

	public Boolean FinalState() {
		return finalState;
	}

	public String getName() {
		return name;
	}	
	
	public ReparationStateType getType() {
		return type;
	}
	
	public static class ReparationStateDescriptionBuilder {
		private Boolean finalState;
		private final String name;
		private ReparationStateType type;
		
		private ReparationStateDescriptionBuilder(String name){
			this.name = name;
		}
		
		private Boolean getFinalState() {
			return finalState;
		}

		private String getName() {
			return name;
		}

		private ReparationStateType getType() {
			return type;
		}
		
		public ReparationStateDescriptionBuilder finalState(Boolean isFinalState){
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
		
		public static ReparationStateDescriptionBuilder newInstance(String name){
			return new ReparationStateDescriptionBuilder(name);
		}
	}	
	
}
