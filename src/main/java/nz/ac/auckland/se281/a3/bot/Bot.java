package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {

	private Strategy strategy;

	public Bot(String name) {
		super(name);
	}

	public void SetStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public Action decideAction(Hand hand) {
		return this.strategy.Action(hand);
	}

	@Override
	public int makeABet() {
		return strategy.Bet();
	}

}
