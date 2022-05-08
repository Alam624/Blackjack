package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;

/**
 * 
 * You should change this class for Task 2
 *
 */
public class Dealer extends Participant {

	private DealerStrategy strategy;

	public Dealer(String name) {
		super(name);
	}

	public void SetStrategy(DealerStrategy strategy) {
		this.strategy = strategy;
	}

	public DealerStrategy getStrategy() {
		return strategy;
	}

	@Override
	public Action decideAction(Hand hand) {
		return this.strategy.Action(hand);
	}

}
