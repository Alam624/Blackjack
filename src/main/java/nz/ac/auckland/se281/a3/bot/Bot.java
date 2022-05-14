package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {

	private Strategy strategy;
	private int bet;

	public Bot(String name) {
		super(name);
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public int getCurrentBet() {
		return bet;
	}

	@Override
	public Action decideAction(Hand hand) {
		return this.strategy.action(hand);
	}

	@Override
	public int makeABet() {
		bet = this.strategy.bet();
		return bet;
	}

}
