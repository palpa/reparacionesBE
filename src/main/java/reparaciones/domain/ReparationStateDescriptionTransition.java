package reparaciones.domain;

public class ReparationStateDescriptionTransition {

	private final ReparationStateDescription initialState;
	private final ReparationStateDescription finalState;

	private ReparationStateDescriptionTransition() {
		throw new AssertionError();
	}

	private ReparationStateDescriptionTransition(
			ReparationStateDescription initialState,
			ReparationStateDescription finalState) {
		this.initialState = initialState;
		this.finalState = finalState;
	}

	public ReparationStateDescription getInitialState() {
		return initialState;
	}

	public ReparationStateDescription getFinishState() {
		return finalState;
	}

	public static ReparationStateDescriptionTransition newInstance(
			ReparationStateDescription intialState,
			ReparationStateDescription finalState) {

		return new ReparationStateDescriptionTransition(intialState, finalState);
	}

}
