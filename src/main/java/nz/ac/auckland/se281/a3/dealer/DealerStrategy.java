package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public interface DealerStrategy {

	/**
	 * Method used to determine what action the dealer will perform: either HIT or
	 * HOLD. The way this is calculated will change based on what strategy the
	 * dealer has been assigned.
	 * 
	 * @param dealerHand The current hand that the dealer has
	 * @return Either Action.HIT or Action.HOLD
	 */
	public Action action(Hand dealerHand);
}
