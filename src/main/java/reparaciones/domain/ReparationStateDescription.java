package reparaciones.domain;

public class ReparationStateDescription {

	private Boolean finalState;
	private String name;

	private ReparationStateDescription() {
		throw new AssertionError();
	}

	private ReparationStateDescription(String name) {
		this.name = name;
	}

	public Boolean FinalState() {
		return finalState;
	}

	public String getName() {
		return name;
	}

	public void setFinalState(Boolean finalState) {
		this.finalState = finalState;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ReparationStateDescription newInstance(String name) {
		return new ReparationStateDescription(name);
	}
}
