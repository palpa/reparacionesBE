package reparaciones.domain;

public class ReparationStateDescriptionTransition {

	private String name;
	private ReparationStateDescription initialState;
	private ReparationStateDescription finalState;

	private ReparationStateDescriptionTransition() {
		throw new AssertionError();
	}

	private ReparationStateDescriptionTransition(
			ReparationStateDescription initialState,
			ReparationStateDescription finalState) {
		this.initialState = initialState;
		this.finalState = finalState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ReparationStateDescription getInitialState() {
		return initialState;
	}

	public void setInitialState(ReparationStateDescription initialState) {
		this.initialState = initialState;
	}

	public ReparationStateDescription getFinishState() {
		return finalState;
	}

	public void setFinishState(ReparationStateDescription finalState) {
		this.finalState = finalState;
	}

	public static ReparationStateDescriptionTransition newInstance(
			ReparationStateDescription intialState,
			ReparationStateDescription finalState) {

		return new ReparationStateDescriptionTransition(intialState, finalState);
	}

}
